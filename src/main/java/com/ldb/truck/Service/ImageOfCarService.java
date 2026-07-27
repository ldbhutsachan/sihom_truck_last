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

import com.ldb.truck.Model.Candidate.CandidateVerifyReq;
import com.ldb.truck.Model.Candidate.CandidateStatusUpdateReq;
import com.ldb.truck.Model.Candidate.CandidateStatusSummary;
import com.ldb.truck.Entity.Staff.CandidateEducation;
import com.ldb.truck.Entity.Staff.CandidateProfile;
import com.ldb.truck.Entity.Staff.StaffEntity;
import com.ldb.truck.Repository.Staffs.CandidateProfileRepository;
import com.ldb.truck.Repository.Staffs.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    @Autowired
    private CandidateProfileRepository candidateProfileRepository;

    @Autowired
    private RateLimiterService rateLimiterService;

    @Autowired
    private UserRepository userRepository;

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
                list = staffStatementDao.searchStaffStatements(requesterStaffId, null, null, req.getStartDate(),
                        req.getEndDate());
            } else if ("ADMIN".equalsIgnoreCase(role) || "HR".equalsIgnoreCase(role)) {
                // ADMIN or HR role: filter by whatever is provided
                list = staffStatementDao.searchStaffStatements(parsedStaffId, req.getBorId(), req.getDeptId(),
                        req.getStartDate(), req.getEndDate());
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

    @Transactional
    public DataResponse saveCandidates(List<CandidateProfile> candidates, String clientIp) {
        DataResponse response = new DataResponse();
        try {
            // 1. Rate Limiting Check (Anti-DDoS / Anti-Spam)
            if (clientIp != null && !rateLimiterService.isAllowed(clientIp)) {
                response.setStatus("01");
                response.setMessage("Too many submission requests. Please wait a minute and try again.");
                response.setDataResponse(null);
                return response;
            }

            // 2. Validate Empty Data
            if (candidates == null || candidates.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Data is empty");
                response.setDataResponse(null);
                return response;
            }

            // 3. Batch Size Limit Check (Max 10 per request)
            if (candidates.size() > 10) {
                response.setStatus("01");
                response.setMessage("Batch size limit exceeded. Maximum 10 candidates per submission allowed.");
                response.setDataResponse(null);
                return response;
            }

            // 4. Sanitize and Validate Fields
            for (CandidateProfile candidate : candidates) {
                if (candidate.getFullname() == null || candidate.getFullname().trim().isEmpty()) {
                    response.setStatus("01");
                    response.setMessage("Fullname is required.");
                    response.setDataResponse(null);
                    return response;
                }

                if (candidate.getFullname() != null && !candidate.getFullname().trim().isEmpty()
                        && candidate.getEmail() != null && !candidate.getEmail().trim().isEmpty()) {
                    boolean exists = candidateProfileRepository.existsByFullnameIgnoreCaseAndEmailIgnoreCase(
                            candidate.getFullname().trim(), candidate.getEmail().trim());
                    if (exists) {
                        response.setStatus("01");
                        response.setMessage("ລະບົບພົບວ່າທ່ານເຄີຍສະໝັກໄປແລ້ວ ໂດຍໃຊ້ຊື່ ແລະ ອີເມວນີ້");
                        response.setDataResponse(null);
                        return response;
                    }
                }

                sanitizeCandidateProfile(candidate);

                if (candidate.getStatus() == null || candidate.getStatus().trim().isEmpty()) {
                    candidate.setStatus("WAIT");
                }
                if (candidate.getCreatedAt() == null) {
                    candidate.setCreatedAt(LocalDateTime.now());
                }

                if (candidate.getEducations() != null) {
                    for (CandidateEducation edu : candidate.getEducations()) {
                        sanitizeCandidateEducation(edu);
                        edu.setCandidate(candidate);
                    }
                }
            }

            List<CandidateProfile> savedList = candidateProfileRepository.saveAll(candidates);
            response.setStatus("00");
            response.setMessage("ທ່ານໄດ້ກອບໃບສະໝັກສຳເລັດແລ້ວ");
            response.setDataResponse(savedList);
        } catch (Exception e) {
            log.error("Error saving candidate data: ", e);
            response.setStatus("05");
            response.setMessage("Error saving data: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    public DataResponse saveCandidatesWithFiles(
            List<CandidateProfile> candidates,
            MultipartFile[] imageFiles,
            MultipartFile[] docFiles,
            String clientIp) {

        if (candidates != null && !candidates.isEmpty()) {
            // Upload multi image files
            if (imageFiles != null && imageFiles.length > 0) {
                List<String> imageUrls = new ArrayList<>();
                for (MultipartFile file : imageFiles) {
                    if (file != null && !file.isEmpty()) {
                        try {
                            String url = mediaUploadService.uploadMedia(file);
                            if (url != null && !url.trim().isEmpty()) {
                                imageUrls.add(url.trim());
                            }
                        } catch (Exception e) {
                            log.error("Error uploading candidate image: ", e);
                        }
                    }
                }
                if (!imageUrls.isEmpty()) {
                    String combinedImages = String.join(",", imageUrls);
                    for (CandidateProfile c : candidates) {
                        if (c.getImage() == null || c.getImage().trim().isEmpty()) {
                            c.setImage(combinedImages);
                        } else {
                            c.setImage(c.getImage() + "," + combinedImages);
                        }
                    }
                }
            }

            // Upload multi document/other files
            if (docFiles != null && docFiles.length > 0) {
                List<String> docUrls = new ArrayList<>();
                for (MultipartFile file : docFiles) {
                    if (file != null && !file.isEmpty()) {
                        try {
                            String url = mediaUploadService.uploadMedia(file);
                            if (url != null && !url.trim().isEmpty()) {
                                docUrls.add(url.trim());
                            }
                        } catch (Exception e) {
                            log.error("Error uploading candidate document/file: ", e);
                        }
                    }
                }
                if (!docUrls.isEmpty()) {
                    String combinedDocs = String.join(",", docUrls);
                    for (CandidateProfile c : candidates) {
                        if (c.getFiles() == null || c.getFiles().trim().isEmpty()) {
                            c.setFiles(combinedDocs);
                        } else {
                            c.setFiles(c.getFiles() + "," + combinedDocs);
                        }
                    }
                }
            }
        }

        return saveCandidates(candidates, clientIp);
    }

    public DataResponse saveCandidates(List<CandidateProfile> candidates) {
        return saveCandidates(candidates, null);
    }

    public DataResponse updateCandidateStatus(CandidateStatusUpdateReq req) {
        DataResponse response = new DataResponse();
        try {
            if (req == null || req.getToken() == null || req.getToken().trim().isEmpty()) {
                response.setStatus("01");
                response.setMessage("Unauthorized: Token is required");
                response.setDataResponse(null);
                return response;
            }

            var tokenCheck = userRepository.findByToken(req.getToken());
            if (!tokenCheck.isPresent()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing token");
                response.setDataResponse(null);
                return response;
            }

            StaffEntity user = tokenCheck.get();
            String userRole = user.getRole();
            if (userRole == null || (!"ADMIN".equalsIgnoreCase(userRole) && !"HR".equalsIgnoreCase(userRole))) {
                response.setStatus("01");
                response.setMessage("Access denied: Only ADMIN or HR role can update candidate status");
                response.setDataResponse(null);
                return response;
            }

            List<Long> targetIds = req.getEffectiveCandidateIds();
            if (targetIds == null || targetIds.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Candidate ID is required");
                response.setDataResponse(null);
                return response;
            }

            List<CandidateProfile> updatedList = new ArrayList<>();
            for (Long targetId : targetIds) {
                Optional<CandidateProfile> candidateOpt = candidateProfileRepository.findById(targetId);
                if (candidateOpt.isPresent()) {
                    CandidateProfile candidate = candidateOpt.get();

                    if (req.getStatus() != null && !req.getStatus().trim().isEmpty()) {
                        candidate.setStatus(req.getStatus().trim().toUpperCase());
                    }

                    if (req.getDateInterview() != null && !req.getDateInterview().trim().isEmpty()) {
                        candidate.setDateInterview(req.getDateInterview().trim());
                        candidate.setInterviewDate(req.getDateInterview().trim());
                    }

                    if (req.getInterviewTime() != null && !req.getInterviewTime().trim().isEmpty()) {
                        candidate.setInterviewTime(req.getInterviewTime().trim());
                    }

                    candidate.setApproveBy(user.getUsername());
                    updatedList.add(candidate);
                }
            }

            if (updatedList.isEmpty()) {
                response.setStatus("01");
                response.setMessage("No candidate found for provided IDs: " + targetIds);
                response.setDataResponse(null);
                return response;
            }

            List<CandidateProfile> savedCandidates = candidateProfileRepository.saveAll(updatedList);

            response.setStatus("00");
            response.setMessage("Candidate status updated successfully for " + savedCandidates.size() + " candidate(s)");
            response.setDataResponse(savedCandidates.size() == 1 ? savedCandidates.get(0) : savedCandidates);
        } catch (Exception e) {
            log.error("Error updating candidate status: ", e);
            response.setStatus("05");
            response.setMessage("Error: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    public DataResponse getCandidates(String token) {
        return getCandidates(token, null, null, null);
    }

    public DataResponse getCandidates(String token, String startDateStr, String endDateStr) {
        return getCandidates(token, startDateStr, endDateStr, null);
    }

    public DataResponse getCandidates(String token, String startDateStr, String endDateStr, String status) {
        DataResponse response = new DataResponse();
        try {
            // 1. Require Token Authentication to view sensitive candidate data
            if (token == null || token.trim().isEmpty()) {
                response.setStatus("01");
                response.setMessage("Unauthorized: Missing or invalid token");
                response.setDataResponse(null);
                return response;
            }
            var tokenCheck = userRepository.findByToken(token);
            if (!tokenCheck.isPresent()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing token");
                response.setDataResponse(null);
                return response;
            }
            StaffEntity user = tokenCheck.get();
            String userRole = user.getRole();
            if (userRole == null || (!"ADMIN".equalsIgnoreCase(userRole) && !"HR".equalsIgnoreCase(userRole))) {
                response.setStatus("01");
                response.setMessage("Access denied: Only ADMIN or HR role can view candidate data");
                response.setDataResponse(null);
                return response;
            }

            LocalDateTime startDateTime = parseStartDateTime(startDateStr);
            LocalDateTime endDateTime = parseEndDateTime(endDateStr);
            String filterStatus = (status != null && !status.trim().isEmpty()) ? status.trim() : null;

            List<CandidateProfile> list = candidateProfileRepository.filterCandidates(filterStatus, startDateTime, endDateTime);

            // Compute summary counts across all candidate records in scope (regardless of status filter)
            List<CandidateProfile> allInScope = candidateProfileRepository.filterCandidates(null, startDateTime, endDateTime);
            CandidateStatusSummary summary = new CandidateStatusSummary();
            long countWait = 0;
            long countInProgress = 0;
            long countOk = 0;
            long countBlackList = 0;
            long countFail = 0;

            if (allInScope != null) {
                summary.setCountAll(allInScope.size());
                for (CandidateProfile c : allInScope) {
                    String st = c.getStatus() != null ? c.getStatus().trim().toUpperCase() : "WAIT";
                    if ("WAIT".equalsIgnoreCase(st)) {
                        countWait++;
                    } else if ("IN-PROGRESS".equalsIgnoreCase(st) || "IN_PROGRESS".equalsIgnoreCase(st) || "INPROGRESS".equalsIgnoreCase(st)) {
                        countInProgress++;
                    } else if ("OK".equalsIgnoreCase(st)) {
                        countOk++;
                    } else if ("BLACK-LIST".equalsIgnoreCase(st) || "BLACKLIST".equalsIgnoreCase(st) || "BLACK_LIST".equalsIgnoreCase(st)) {
                        countBlackList++;
                    } else if ("FAIL".equalsIgnoreCase(st) || "FAILED".equalsIgnoreCase(st)) {
                        countFail++;
                    } else {
                        countWait++;
                    }
                }
            }
            summary.setCountWait(countWait);
            summary.setCountInProgress(countInProgress);
            summary.setCountOk(countOk);
            summary.setCountBlackList(countBlackList);
            summary.setCountFail(countFail);

            response.setStatus("00");
            response.setMessage("Success");
            response.setDataResponse(list);
            response.setSumFooter(summary);
        } catch (Exception e) {
            log.error("Error getting candidate data: ", e);
            response.setStatus("05");
            response.setMessage("Error: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    private LocalDateTime parseStartDateTime(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        dateStr = dateStr.trim();
        try {
            if (dateStr.length() == 10) {
                return LocalDate.parse(dateStr).atStartOfDay();
            } else if (dateStr.contains("T")) {
                return LocalDateTime.parse(dateStr);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return LocalDateTime.parse(dateStr, formatter);
            }
        } catch (Exception e) {
            log.warn("Invalid startDate format: " + dateStr, e);
            return null;
        }
    }

    private LocalDateTime parseEndDateTime(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        dateStr = dateStr.trim();
        try {
            if (dateStr.length() == 10) {
                return LocalDate.parse(dateStr).atTime(LocalTime.MAX);
            } else if (dateStr.contains("T")) {
                return LocalDateTime.parse(dateStr);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return LocalDateTime.parse(dateStr, formatter);
            }
        } catch (Exception e) {
            log.warn("Invalid endDate format: " + dateStr, e);
            return null;
        }
    }

    public DataResponse verifyMyCandidateStatus(CandidateVerifyReq req, String clientIp) {
        DataResponse response = new DataResponse();
        try {
            if (clientIp != null && !rateLimiterService.isAllowed(clientIp)) {
                response.setStatus("01");
                response.setMessage("Too many requests. Please wait a minute and try again.");
                response.setDataResponse(null);
                return response;
            }

            if (req == null || req.getEmail() == null || req.getEmail().trim().isEmpty()
                    || req.getAddrDistrict() == null || req.getAddrDistrict().trim().isEmpty()
                    || req.getReligion() == null || req.getReligion().trim().isEmpty()) {
                response.setStatus("01");
                response.setMessage("ກະລຸນາປ້ອນ Email, ເມືອງ (addrDistrict) ແລະ ສາສະໜາ (religion) ໃຫ້ຄົບຖ້ວນ");
                response.setDataResponse(null);
                return response;
            }

            String email = req.getEmail().trim();
            String addrDistrict = req.getAddrDistrict().trim();
            String religion = req.getReligion().trim();

            List<CandidateProfile> matches = candidateProfileRepository
                    .findByEmailIgnoreCaseAndAddrDistrictIgnoreCaseAndReligionIgnoreCase(email, addrDistrict, religion);

            if (matches == null || matches.isEmpty()) {
                response.setStatus("01");
                response.setMessage("ບໍ່ພົບຂໍ້ມູນການສະໝັກຂອງທ່ານ ກະລຸນາກວດສອບຂໍ້ມູນທີ່ປ້ອນຄືນໃໝ່");
                response.setDataResponse(null);
                return response;
            }

            CandidateProfile profile = matches.get(matches.size() - 1);
            response.setStatus("00");
            response.setMessage("ທ່ານໄດ້ກອບໃບສະໝັກເປັນທີ່ຮຽບຮ້ອຍແລ້ວ ລໍຖ້າທາງເຮົາຈະຕິດຕໍ່ຫາ ຂໍຂອບໃຈ");
            response.setDataResponse(profile);
        } catch (Exception e) {
            log.error("Error verifying candidate status: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    private void sanitizeCandidateProfile(CandidateProfile p) {
        p.setFullname(cleanStr(p.getFullname(), 100));
        p.setNickname(cleanStr(p.getNickname(), 50));
        p.setNameEng(cleanStr(p.getNameEng(), 100));
        p.setDob(cleanStr(p.getDob(), 20));
        p.setAge(cleanStr(p.getAge(), 10));
        p.setGender(cleanStr(p.getGender(), 20));
        p.setWeight(cleanStr(p.getWeight(), 10));
        p.setHeight(cleanStr(p.getHeight(), 10));
        p.setReligion(cleanStr(p.getReligion(), 50));
        p.setPosition(cleanStr(p.getPosition(), 100));
        p.setSalary(cleanStr(p.getSalary(), 50));
        p.setRotatePosition(cleanStr(p.getRotatePosition(), 100));
        p.setExperience(cleanStr(p.getExperience(), 255));
        p.setInterviewDate(cleanStr(p.getInterviewDate(), 20));
        p.setInterviewTime(cleanStr(p.getInterviewTime(), 20));
        p.setPobVillage(cleanStr(p.getPobVillage(), 100));
        p.setPobDistrict(cleanStr(p.getPobDistrict(), 100));
        p.setPobProvince(cleanStr(p.getPobProvince(), 100));
        p.setAddrVillage(cleanStr(p.getAddrVillage(), 100));
        p.setAddrDistrict(cleanStr(p.getAddrDistrict(), 100));
        p.setAddrProvince(cleanStr(p.getAddrProvince(), 100));
        p.setPhone(cleanStr(p.getPhone(), 30));
        p.setTel2(cleanStr(p.getTel2(), 30));
        p.setEmail(cleanStr(p.getEmail(), 100));
        p.setIdCardNo(cleanStr(p.getIdCardNo(), 50));
        p.setIdIssuedAt(cleanStr(p.getIdIssuedAt(), 100));
        p.setIdIssueDate(cleanStr(p.getIdIssueDate(), 20));
        p.setIdExpireDate(cleanStr(p.getIdExpireDate(), 20));
        p.setResidence(cleanStr(p.getResidence(), 100));
        p.setMaritalStatus(cleanStr(p.getMaritalStatus(), 50));
        p.setMbDrive(cleanStr(p.getMbDrive(), 50));
        p.setMbLicense(cleanStr(p.getMbLicense(), 50));
        p.setCarDrive(cleanStr(p.getCarDrive(), 50));
        p.setCarLicense(cleanStr(p.getCarLicense(), 50));
        p.setCarLicenseType(cleanStr(p.getCarLicenseType(), 50));
        p.setFatherAlive(cleanStr(p.getFatherAlive(), 20));
        p.setFatherName(cleanStr(p.getFatherName(), 100));
        p.setFatherAge(cleanStr(p.getFatherAge(), 10));
        p.setFatherOcc(cleanStr(p.getFatherOcc(), 100));
        p.setFatherTel(cleanStr(p.getFatherTel(), 30));
        p.setMotherAlive(cleanStr(p.getMotherAlive(), 20));
        p.setMotherName(cleanStr(p.getMotherName(), 100));
        p.setMotherAge(cleanStr(p.getMotherAge(), 10));
        p.setMotherOcc(cleanStr(p.getMotherOcc(), 100));
        p.setMotherTel(cleanStr(p.getMotherTel(), 30));
        p.setSpouseName(cleanStr(p.getSpouseName(), 100));
        p.setSpouseAge(cleanStr(p.getSpouseAge(), 10));
        p.setSpouseTel(cleanStr(p.getSpouseTel(), 30));
        p.setSpouseOcc(cleanStr(p.getSpouseOcc(), 100));
    }

    private void sanitizeCandidateEducation(CandidateEducation e) {
        e.setLevel(cleanStr(e.getLevel(), 100));
        e.setInstitute(cleanStr(e.getInstitute(), 150));
        e.setYear(cleanStr(e.getYear(), 20));
        e.setGpa(cleanStr(e.getGpa(), 20));
        e.setMajor(cleanStr(e.getMajor(), 100));
    }

    private String cleanStr(String val, int maxLen) {
        if (val == null)
            return null;
        String s = val.trim();
        // Remove script or html injection
        s = s.replaceAll("(?i)<script.*?>.*?</script>", "")
                .replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        if (s.length() > maxLen) {
            s = s.substring(0, maxLen);
        }
        return s;
    }
}
