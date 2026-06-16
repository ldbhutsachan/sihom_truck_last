package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.SamLuat.SamLuatRes;
import com.ldb.truck.Service.SamLuat.SamLuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("${base_url}")
@CrossOrigin(origins = "*")
public class SamLuatController {

    @Autowired
    private SamLuatService samLuatService;

    // ===== FOLDER =====

    /** ສ້າງ Folder (ຮາກ ຫຼື Folder ຍ່ອຍ) */
    @PostMapping("/samluat/createFolder.service")
    public SamLuatRes createFolder(
            @RequestParam("name") String name,
            @RequestParam(value = "parentId", required = false) Long parentId,
            @RequestParam(value = "createdBy", defaultValue = "samluat01") String createdBy) {
        return samLuatService.createFolder(name, parentId, createdBy);
    }

    /** ດຶງ Folder ທັງໝົດ (Tree Structure) */
    @GetMapping("/samluat/getFolders.service")
    public SamLuatRes getAllFolders() {
        return samLuatService.getAllFolders();
    }

    /** ລຶບ Folder (ລວມ Folder ຍ່ອຍ + ໄຟລ໌ຂ້າງໃນ) */
    @PostMapping("/samluat/deleteFolder.service")
    public SamLuatRes deleteFolder(@RequestParam("folderId") Long folderId) {
        return samLuatService.deleteFolder(folderId);
    }

    /** ແກ້ໄຂຊື່ Folder */
    @PostMapping("/samluat/renameFolder.service")
    public SamLuatRes renameFolder(@RequestParam("folderId") Long folderId,
                                   @RequestParam("newName") String newName) {
        return samLuatService.renameFolder(folderId, newName);
    }

    // ===== FILE =====

    /** ອັບໂຫຼດໄຟລ໌ (ສູງສຸດ 200 MB) */
    @PostMapping(value = "/samluat/uploadFile.service", consumes = {"multipart/form-data"})
    public SamLuatRes uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folderId", required = false) Long folderId,
            @RequestParam(value = "uploadedBy", defaultValue = "samluat01") String uploadedBy) {
        return samLuatService.uploadFile(file, folderId, uploadedBy);
    }

    /** ດຶງໄຟລ໌ຕາມ Folder */
    @GetMapping("/samluat/getFiles.service")
    public SamLuatRes getFiles(
            @RequestParam(value = "folderId", required = false) Long folderId) {
        return samLuatService.getFilesByFolder(folderId);
    }

    /** ຍ້າຍໄຟລ໌ໄປ Folder ອື່ນ */
    @PostMapping("/samluat/moveFile.service")
    public SamLuatRes moveFile(
            @RequestParam("fileId") Long fileId,
            @RequestParam(value = "targetFolderId", required = false) Long targetFolderId) {
        return samLuatService.moveFile(fileId, targetFolderId);
    }

    /** ລຶບໄຟລ໌ */
    @PostMapping("/samluat/deleteFile.service")
    public SamLuatRes deleteFile(@RequestParam("fileId") Long fileId) {
        return samLuatService.deleteFile(fileId);
    }

    /** ແກ້ໄຂຊື່ File */
    @PostMapping("/samluat/renameFile.service")
    public SamLuatRes renameFile(@RequestParam("fileId") Long fileId,
                                 @RequestParam("newName") String newName) {
        return samLuatService.renameFile(fileId, newName);
    }

    /** ດາວໂຫຼດໄຟລ໌ (attachment) */
    @GetMapping("/samluat/downloadFile.service/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        try {
            Resource resource = samLuatService.downloadFile(fileId);
            String fileName = samLuatService.getFileNameById(fileId);
            String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encoded)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** Preview ໄຟລ໌ inline (ສຳລັບ PDF, ຮູບ, ວິດີໂອ) */
    @GetMapping("/samluat/previewFile.service/{fileId}")
    public ResponseEntity<Resource> previewFile(@PathVariable Long fileId) {
        try {
            Resource resource = samLuatService.downloadFile(fileId);
            String fileName = samLuatService.getFileNameById(fileId);
            String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
            String ext = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase() : "";
            MediaType contentType = getMediaType(ext);
            return ResponseEntity.ok()
                    .contentType(contentType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename*=UTF-8''" + encoded)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private MediaType getMediaType(String ext) {
        switch (ext) {
            case "pdf":  return MediaType.APPLICATION_PDF;
            case "jpg": case "jpeg": return MediaType.IMAGE_JPEG;
            case "png":  return MediaType.IMAGE_PNG;
            case "gif":  return MediaType.IMAGE_GIF;
            case "webp": return MediaType.parseMediaType("image/webp");
            case "svg":  return MediaType.parseMediaType("image/svg+xml");
            case "mp4":  return MediaType.parseMediaType("video/mp4");
            case "webm": return MediaType.parseMediaType("video/webm");
            case "mov":  return MediaType.parseMediaType("video/quicktime");
            case "txt": case "log": case "md": return MediaType.TEXT_PLAIN;
            case "json": return MediaType.APPLICATION_JSON;
            case "xml":  return MediaType.APPLICATION_XML;
            case "html": return MediaType.TEXT_HTML;
            default:     return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
