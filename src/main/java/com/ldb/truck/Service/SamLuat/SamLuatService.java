package com.ldb.truck.Service.SamLuat;

import com.ldb.truck.Entity.SamLuat.SamLuatFileEntity;
import com.ldb.truck.Entity.SamLuat.SamLuatFolderEntity;
import com.ldb.truck.Model.Login.SamLuat.SamLuatRes;
import com.ldb.truck.Repository.SamLuatFileRepository;
import com.ldb.truck.Repository.SamLuatFolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class SamLuatService {

    private static final long MAX_FILE_SIZE = 450L * 1024 * 1024; // 450 MB

    @Value("${samluat.upload.path:uploads/samluat/}")
    private String uploadPath;

    @Autowired
    private SamLuatFolderRepository folderRepo;

    @Autowired
    private SamLuatFileRepository fileRepo;

    // ===== FOLDER =====

    public SamLuatRes createFolder(String name, Long parentId, String createdBy) {
        SamLuatRes res = new SamLuatRes();
        try {
            SamLuatFolderEntity folder = new SamLuatFolderEntity();
            folder.setName(name);
            folder.setParentId(parentId);
            folder.setCreatedBy(createdBy);
            folder.setStatus("A");
            folderRepo.save(folder);
            res.setData(folder);
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public SamLuatRes getAllFolders() {
        SamLuatRes res = new SamLuatRes();
        try {
            List<SamLuatFolderEntity> folders = folderRepo.findByStatusOrderByCreatedDateAsc("A");
            res.setData(buildTree(folders, null));
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    private List<Map<String, Object>> buildTree(List<SamLuatFolderEntity> all, Long parentId) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (SamLuatFolderEntity f : all) {
            boolean match = (parentId == null && f.getParentId() == null)
                    || (parentId != null && parentId.equals(f.getParentId()));
            if (match) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("id", f.getId());
                node.put("name", f.getName());
                node.put("parentId", f.getParentId());
                node.put("createdBy", f.getCreatedBy());
                node.put("createdDate", f.getCreatedDate());
                node.put("children", buildTree(all, f.getId()));
                result.add(node);
            }
        }
        return result;
    }

    public SamLuatRes renameFolder(Long folderId, String newName) {
        SamLuatRes res = new SamLuatRes();
        try {
            SamLuatFolderEntity folder = folderRepo.findById(folderId)
                    .orElseThrow(() -> new Exception("ບໍ່ພົບ Folder"));
            folder.setName(newName);
            folderRepo.save(folder);
            res.setData(folder);
            res.setMessage("ແກ້ໄຂຊື່ Folder ສຳເລັດ");
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public SamLuatRes deleteFolder(Long folderId) {
        SamLuatRes res = new SamLuatRes();
        try {
            deleteRecursive(folderId);
            res.setMessage("ລຶບ Folder ສຳເລັດ");
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    private void deleteRecursive(Long folderId) {
        // ลบ files ใน folder
        List<SamLuatFileEntity> files = fileRepo.findByFolderIdAndStatus(folderId, "A");
        for (SamLuatFileEntity f : files) {
            deletePhysicalFile(f.getFilePath());
            f.setStatus("D");
            fileRepo.save(f);
        }
        // ลบ sub-folders
        List<SamLuatFolderEntity> subs = folderRepo.findByParentIdAndStatus(folderId, "A");
        for (SamLuatFolderEntity sub : subs) {
            deleteRecursive(sub.getId());
        }
        // ลบ folder นี้
        folderRepo.findById(folderId).ifPresent(f -> {
            f.setStatus("D");
            folderRepo.save(f);
        });
    }

    // ===== FILE =====

    public SamLuatRes uploadFile(MultipartFile file, Long folderId, String uploadedBy) {
        SamLuatRes res = new SamLuatRes();
        try {
            if (file.getSize() > MAX_FILE_SIZE) {
                res.setStatus("01");
                res.setMessage("ໄຟລ໌ໃຫຍ່ກວ່າ 200 MB ບໍ່ສາມາດອັບໂຫຼດໄດ້");
                return res;
            }

            Path dir = Paths.get(uploadPath);
            if (!Files.exists(dir)) Files.createDirectories(dir);

            String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path savePath = dir.resolve(uniqueName);
            Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

            SamLuatFileEntity entity = new SamLuatFileEntity();
            entity.setFileName(file.getOriginalFilename());
            entity.setFileType(getExtension(file.getOriginalFilename()));
            entity.setFileSize(file.getSize());
            entity.setFilePath(savePath.toString());
            entity.setFolderId(folderId);
            entity.setCreatedBy(uploadedBy);
            entity.setStatus("A");
            fileRepo.save(entity);

            res.setData(entity);
            res.setMessage("ອັບໂຫຼດໄຟລ໌ສຳເລັດ");
        } catch (IOException e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public SamLuatRes getFilesByFolder(Long folderId) {
        SamLuatRes res = new SamLuatRes();
        try {
            List<SamLuatFileEntity> files = folderId == null
                    ? fileRepo.findByFolderIdIsNullAndStatus("A")
                    : fileRepo.findByFolderIdAndStatus(folderId, "A");
            res.setData(files);
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public SamLuatRes moveFile(Long fileId, Long targetFolderId) {
        SamLuatRes res = new SamLuatRes();
        try {
            SamLuatFileEntity file = fileRepo.findById(fileId)
                    .orElseThrow(() -> new Exception("ບໍ່ພົບໄຟລ໌"));
            file.setFolderId(targetFolderId);
            fileRepo.save(file);
            res.setMessage("ຍ້າຍໄຟລ໌ສຳເລັດ");
            res.setData(file);
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public SamLuatRes renameFile(Long fileId, String newName) {
        SamLuatRes res = new SamLuatRes();
        try {
            SamLuatFileEntity file = fileRepo.findById(fileId)
                    .orElseThrow(() -> new Exception("ບໍ່ພົບໄຟລ໌"));
            file.setFileName(newName);
            fileRepo.save(file);
            res.setData(file);
            res.setMessage("ແກ້ໄຂຊື່ໄຟລ໌ສຳເລັດ");
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public SamLuatRes deleteFile(Long fileId) {
        SamLuatRes res = new SamLuatRes();
        try {
            SamLuatFileEntity file = fileRepo.findById(fileId)
                    .orElseThrow(() -> new Exception("ບໍ່ພົບໄຟລ໌"));
            deletePhysicalFile(file.getFilePath());
            file.setStatus("D");
            fileRepo.save(file);
            res.setMessage("ລຶບໄຟລ໌ສຳເລັດ");
        } catch (Exception e) {
            res.setStatus("01");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public Resource downloadFile(Long fileId) throws Exception {
        SamLuatFileEntity file = fileRepo.findById(fileId)
                .orElseThrow(() -> new Exception("ບໍ່ພົບໄຟລ໌"));
        Path path = Paths.get(file.getFilePath());
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists()) throw new Exception("ໄຟລ໌ບໍ່ມີຢູ່ໃນ Server");
        return resource;
    }

    public String getFileNameById(Long fileId) throws Exception {
        return fileRepo.findById(fileId)
                .orElseThrow(() -> new Exception("ບໍ່ພົບໄຟລ໌"))
                .getFileName();
    }

    private void deletePhysicalFile(String filePath) {
        try {
            if (filePath != null) Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException ignored) {}
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "";
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }
}
