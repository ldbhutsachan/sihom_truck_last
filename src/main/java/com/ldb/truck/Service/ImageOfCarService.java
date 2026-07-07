package com.ldb.truck.Service;

import com.ldb.truck.Dao.ImageOfCarDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.ImageOfCar.ImageOfCar;
import com.ldb.truck.Model.ImageOfCar.ImageOfCarReq;
import com.ldb.truck.Model.ImageOfCar.ImageOfCarRes;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.StaffStatement.StaffStatementBatchReq;
import com.ldb.truck.Model.StaffStatement.StaffStatementReq;
import com.ldb.truck.Model.StaffStatement.StaffStatementRes;
import com.ldb.truck.Model.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageOfCarService {

    private static final Logger log = LogManager.getLogger(ImageOfCarService.class);

    @Autowired
    private ImageOfCarDao imageOfCarDao;

    @Autowired
    private com.ldb.truck.Dao.StaffStatementDao staffStatementDao;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private MediaUploadService mediaUploadService;

    public ImageOfCarRes insertCarImages(String toKen, Integer carId, String imageType, MultipartFile[] imageFile) {
        ImageOfCarRes response = new ImageOfCarRes();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
            if (userProfiles == null || userProfiles.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setData(null);
                return response;
            }
            String userName = userProfiles.get(0).getUserName();

            ImageOfCarReq checkReq = new ImageOfCarReq();
            checkReq.setCarId(carId);
            checkReq.setImageType(imageType);
            List<ImageOfCar> existingImages = imageOfCarDao.listCarImages(checkReq);
            if (existingImages != null && !existingImages.isEmpty()) {
                response.setStatus("01");
                response.setMessage("ຮູບພາບປະເພດ: \" + imageType + \" .ມີຢູ່ແລ້ວ ກະລຸນາອັບເດັບແທນໄດ້ເລີຍ");

                response.setData(null);
                return response;
            }

            String filePath = null;
            String pathAdd = "http://khounkham.com/images/car/";
            if (imageFile != null && imageFile.length > 0) {
                List<String> filePaths = new ArrayList<>();
                Arrays.asList(imageFile).forEach(file -> {
                    String fileName = mediaUploadService.uploadMediacar(file);
                    filePaths.add(pathAdd + fileName);
                });
                log.info("Uploaded files successfully: " + filePaths);
                filePath = StringUtils.join(filePaths, ",");
            } else {
                log.warn("No files uploaded");
            }

            ImageOfCarReq req = new ImageOfCarReq();
            req.setCarId(carId);
            req.setImageType(imageType);

            int result = imageOfCarDao.insertCarImage(req, userName, filePath);
            if (result > 0) {
                response.setStatus("00");
                response.setMessage("Data inserted successfully");
            } else {
                response.setStatus("01");
                response.setMessage("Failed to insert data");
            }
            response.setData(null);
        } catch (Exception e) {
            log.error("Error inserting car image: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred while inserting data");
            response.setData(null);
        }
        return response;
    }

    public ImageOfCarRes updateCarImages(String toKen, Long id, String imageType, MultipartFile[] imageFile) {
        ImageOfCarRes response = new ImageOfCarRes();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
            if (userProfiles == null || userProfiles.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setData(null);
                return response;
            }
            String userName = userProfiles.get(0).getUserName();

            String filePath = null;
            String pathAdd = "http://khounkham.com/images/car/";
            if (imageFile != null && imageFile.length > 0) {
                List<String> filePaths = new ArrayList<>();
                Arrays.asList(imageFile).forEach(file -> {
                    String fileName = mediaUploadService.uploadMediacar(file);
                    filePaths.add(pathAdd + fileName);
                });
                log.info("Uploaded files successfully: " + filePaths);
                filePath = StringUtils.join(filePaths, ",");
            } else {
                log.warn("No files uploaded");
            }

            ImageOfCarReq req = new ImageOfCarReq();
            req.setId(id);
            req.setImageType(imageType);

            int result = imageOfCarDao.updateCarImage(req, userName, filePath);
            if (result > 0) {
                response.setStatus("00");
                response.setMessage("Data updated successfully");
            } else {
                response.setStatus("01");
                response.setMessage("Failed to update data (id may not exist)");
            }
            response.setData(null);
        } catch (Exception e) {
            log.error("Error updating car image: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred while updating data");
            response.setData(null);
        }
        return response;
    }

    public ImageOfCarRes showCarImages(ImageOfCarReq req) {
        ImageOfCarRes response = new ImageOfCarRes();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToKen());
            if (userProfiles == null || userProfiles.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setData(null);
                return response;
            }

            List<ImageOfCar> data = imageOfCarDao.listCarImages(req);
            if (data != null && !data.isEmpty()) {
                response.setStatus("00");
                response.setMessage("Success");
                response.setData(data);
            } else {
                response.setStatus("01");
                response.setMessage("Data not found !!!");
                response.setData(null);
            }
        } catch (Exception e) {
            log.error("Error showing car images: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred while retrieving data");
            response.setData(null);
        }
        return response;
    }

    @Autowired
    private com.ldb.truck.Repository.Staffs.UserRepository userRepository;

    // --- Staff Statement APIs ---

    public DataResponse insertStaffStatements(String token, StaffStatementBatchReq req) {
        DataResponse response = new DataResponse();
        try {
            String userName = null;
            // Only check company_staffs for this API
            var staffOpt = userRepository.findByToken(token);
            if (staffOpt.isPresent()) {
                userName = staffOpt.get().getUsername();
            }

            if (userName == null) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setDataResponse(null);
                return response;
            }

            String pathAdd = "http://khounkham.com/images/car/";

            if (req.getRequests() != null && !req.getRequests().isEmpty()) {
                for (StaffStatementReq item : req.getRequests()) {
                    String filePath = null;
                    if (item.getFiles() != null && item.getFiles().length > 0) {
                        List<String> filePaths = new ArrayList<>();
                        for (MultipartFile file : item.getFiles()) {
                            String fileName = mediaUploadService.uploadMediacar(file);
                            filePaths.add(pathAdd + fileName);
                        }
                        filePath = StringUtils.join(filePaths, ",");
                    }
                    Integer parsedStaffId = null;
                    if (item.getStaffId() != null && !item.getStaffId().trim().isEmpty()) {
                        try {
                            parsedStaffId = Integer.parseInt(item.getStaffId().trim());
                        } catch (NumberFormatException e) {
                            log.error("Invalid staffId format: " + item.getStaffId());
                        }
                    }
                    staffStatementDao.insertStaffStatement(parsedStaffId, item.getTitle(), filePath, userName);
                }
            }

            response.setStatus("00");
            response.setMessage("Staff statements inserted successfully");
            response.setDataResponse(null);
        } catch (Exception e) {
            log.error("Error inserting staff statements: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    public DataResponse listStaffStatements(String token, com.ldb.truck.Model.StaffStatement.StaffStatementReq req) {
        DataResponse response = new DataResponse();
        try {
            var staffOpt = userRepository.findByToken(token);
            if (!staffOpt.isPresent()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing token");
                response.setDataResponse(null);
                return response;
            }

            var staff = staffOpt.get();
            String role = staff.getRole();
            Integer requesterStaffId = staff.getId() != null ? staff.getId().intValue() : null;

            List<StaffStatementRes> list = new ArrayList<>();

            // Parse staffId from Request
            Integer parsedStaffId = null;
            if (req.getStaffId() != null && !req.getStaffId().trim().isEmpty()) {
                try {
                    parsedStaffId = Integer.parseInt(req.getStaffId().trim());
                } catch (NumberFormatException e) {
                    log.error("Invalid staffId format: " + req.getStaffId());
                }
            }

            if ("USER".equalsIgnoreCase(role)) {
                // USER role: only see their own data
                list = staffStatementDao.searchStaffStatements(requesterStaffId, null, null, req.getStartDate(), req.getEndDate());
            } else if ("ADMIN".equalsIgnoreCase(role) || "HR".equalsIgnoreCase(role)) {
                // ADMIN or HR role: filter by whatever is provided
                list = staffStatementDao.searchStaffStatements(parsedStaffId, req.getBorId(), req.getDeptId(), req.getStartDate(), req.getEndDate());
            } else {
                response.setStatus("01");
                response.setMessage("Unauthorized role");
                response.setDataResponse(null);
                return response;
            }

            if (list != null && !list.isEmpty()) {
                response.setStatus("00");
                response.setMessage("Success");
                response.setDataResponse(list);
            } else {
                response.setStatus("01");
                response.setMessage("Data not found !!!");
                response.setDataResponse(null);
            }
        } catch (Exception e) {
            log.error("Error listing staff statements: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    public DataResponse updateStaffStatement(String token, Long id, String title, MultipartFile[] files) {
        DataResponse response = new DataResponse();
        try {
            String userName = null;
            // Only check company_staffs for this API
            var staffOpt = userRepository.findByToken(token);
            if (staffOpt.isPresent()) {
                userName = staffOpt.get().getUsername();
            }

            if (userName == null) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setDataResponse(null);
                return response;
            }

            String filePath = null;
            String pathAdd = "http://khounkham.com/images/car/";
            if (files != null && files.length > 0) {
                List<String> filePaths = new ArrayList<>();
                for (MultipartFile file : files) {
                    String fileName = mediaUploadService.uploadMediacar(file);
                    filePaths.add(pathAdd + fileName);
                }
                filePath = StringUtils.join(filePaths, ",");
            }

            int res = staffStatementDao.updateStaffStatement(id, title, filePath, userName);
            if (res > 0) {
                response.setStatus("00");
                response.setMessage("Updated successfully");
            } else {
                response.setStatus("01");
                response.setMessage("Update failed (id may not exist)");
            }
            response.setDataResponse(null);
        } catch (Exception e) {
            log.error("Error updating staff statement: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }
}
