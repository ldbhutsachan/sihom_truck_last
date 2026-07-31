package com.ldb.truck.Controller;

import com.ldb.truck.Model.ImageOfCar.ImageOfCarReq;
import com.ldb.truck.Service.ImageOfCarService;
import com.ldb.truck.Model.StaffStatement.StaffStatementBatchReq;
import com.ldb.truck.Model.StaffStatement.StaffStatementReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ldb.truck.Model.Candidate.CandidateVerifyReq;
import com.ldb.truck.Model.Candidate.CandidateStatusUpdateReq;
import com.ldb.truck.Entity.Staff.CandidateProfile;
import com.ldb.truck.Entity.Staff.CandidateEducation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${base_url}")
public class ImageOfCarController {

    private static final Logger log = LogManager.getLogger(ImageOfCarController.class);

    @Autowired
    private ImageOfCarService imageOfCarService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/insertcarimages", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> insertCarImages(
            @RequestParam("toKen") String toKen,
            @RequestParam("carId") Integer carId,
            @RequestParam("imageType") String imageType,
            @RequestPart(value = "files", required = false) MultipartFile[] files,
            @RequestPart(value = "file", required = false) MultipartFile[] singleFile,
            @RequestPart(value = "image", required = false) MultipartFile[] image,
            @RequestPart(value = "images", required = false) MultipartFile[] images) {
        log.info("insertcarimages called with carId: " + carId + " and imageType: " + imageType);
        MultipartFile[] finalFiles = files;
        if (finalFiles == null || finalFiles.length == 0) finalFiles = singleFile;
        if (finalFiles == null || finalFiles.length == 0) finalFiles = image;
        if (finalFiles == null || finalFiles.length == 0) finalFiles = images;
        return new ResponseEntity<>(imageOfCarService.insertCarImages(toKen, carId, imageType, finalFiles),
                HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updatecarimages", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> updateCarImages(
            @RequestParam("toKen") String toKen,
            @RequestParam("id") Long id,
            @RequestParam("imageType") String imageType,
            @RequestPart(value = "files", required = false) MultipartFile[] files,
            @RequestPart(value = "file", required = false) MultipartFile[] singleFile,
            @RequestPart(value = "image", required = false) MultipartFile[] image,
            @RequestPart(value = "images", required = false) MultipartFile[] images) {
        log.info("updatecarimages called for id: " + id + " and imageType: " + imageType);
        MultipartFile[] finalFiles = files;
        if (finalFiles == null || finalFiles.length == 0) finalFiles = singleFile;
        if (finalFiles == null || finalFiles.length == 0) finalFiles = image;
        if (finalFiles == null || finalFiles.length == 0) finalFiles = images;
        return new ResponseEntity<>(imageOfCarService.updateCarImages(toKen, id, imageType, finalFiles),
                HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/showcarimages")
    public ResponseEntity<?> showCarImages(@RequestBody ImageOfCarReq req) {
        log.info("showcarimages called with token: " + req.getToKen() + " and imageType: " + req.getImageType());
        return new ResponseEntity<>(imageOfCarService.showCarImages(req), HttpStatus.OK);
    }
    // --- Staff Statement APIs ---

    @CrossOrigin(origins = "*")
    @PostMapping("/staff-statements/upload")
    public ResponseEntity<?> insertStaffStatements(
            @RequestParam("toKen") String token,
            @ModelAttribute StaffStatementBatchReq req) {
        log.info("insertStaffStatements bulk called");
        return new ResponseEntity<>(imageOfCarService.insertStaffStatements(token, req), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/staff-statements/get")
    public ResponseEntity<?> listStaffStatements(@RequestBody StaffStatementReq req) {
        String tokenStr = req.getToken();
        log.info("listStaffStatements called with staffId: " + req.getStaffId());
        return new ResponseEntity<>(imageOfCarService.listStaffStatements(tokenStr, req), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/staff-statements/update")
    public ResponseEntity<?> updateStaffStatement(
            @RequestParam("toKen") String token,
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {
        log.info("updateStaffStatement called for id: " + id);
        return new ResponseEntity<>(imageOfCarService.updateStaffStatement(token, id, title, files), HttpStatus.OK);
    }

    // --- Candidate Profile APIs ---

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/candidates/save", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> saveCandidates(
            @RequestParam(value = "candidate", required = false) String candidateStr,
            @RequestParam(value = "candidates", required = false) String candidatesStr,
            @RequestParam(value = "data", required = false) String dataStr,
            @RequestPart(value = "image", required = false) MultipartFile[] imageFiles,
            @RequestPart(value = "images", required = false) MultipartFile[] imagesFiles,
            @RequestPart(value = "files", required = false) MultipartFile[] docFiles,
            @RequestPart(value = "file", required = false) MultipartFile[] singleDocFile,
            HttpServletRequest request) {

        String clientIp = getClientIp(request);
        log.info("saveCandidates (form-data) called from IP: " + clientIp);

        MultipartFile[] finalImages = (imageFiles != null && imageFiles.length > 0) ? imageFiles : imagesFiles;
        MultipartFile[] finalDocs = (docFiles != null && docFiles.length > 0) ? docFiles : singleDocFile;

        List<CandidateProfile> candidates = extractCandidatesFromRequest(candidateStr, candidatesStr, dataStr, request);

        return new ResponseEntity<>(imageOfCarService.saveCandidatesWithFiles(candidates, finalImages, finalDocs, clientIp), HttpStatus.OK);
    }



    @CrossOrigin(origins = "*")
    @PostMapping("/candidates/get")
    public ResponseEntity<?> getCandidates(
            @RequestParam(value = "toKen", required = false) String paramToken,
            @RequestParam(value = "startDate", required = false) String paramStartDate,
            @RequestParam(value = "endDate", required = false) String paramEndDate,
            @RequestParam(value = "status", required = false) String paramStatus,
            @RequestBody(required = false) Map<String, String> body) {
        String token = paramToken;
        String startDate = paramStartDate;
        String endDate = paramEndDate;
        String status = paramStatus;
        if (body != null) {
            if (token == null || token.trim().isEmpty()) {
                token = body.get("toKen");
                if (token == null) token = body.get("token");
            }
            if (startDate == null || startDate.trim().isEmpty()) {
                startDate = body.get("startDate");
            }
            if (endDate == null || endDate.trim().isEmpty()) {
                endDate = body.get("endDate");
            }
            if (status == null || status.trim().isEmpty()) {
                status = body.get("status");
            }
        }
        log.info("getCandidates called with startDate: " + startDate + ", endDate: " + endDate + ", status: " + status);
        return new ResponseEntity<>(imageOfCarService.getCandidates(token, startDate, endDate, status), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/candidates/check-status")
    public ResponseEntity<?> checkCandidateStatus(@RequestBody CandidateVerifyReq req, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        log.info("checkCandidateStatus called for email: " + (req != null ? req.getEmail() : "null") + " from IP: " + clientIp);
        return new ResponseEntity<>(imageOfCarService.verifyMyCandidateStatus(req, clientIp), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/candidates/update-status")
    public ResponseEntity<?> updateCandidateStatus(@RequestBody CandidateStatusUpdateReq req) {
        log.info("updateCandidateStatus called for candidate ID: " + (req != null ? req.getId() : "null"));
        return new ResponseEntity<>(imageOfCarService.updateCandidateStatus(req), HttpStatus.OK);
    }

    private List<CandidateProfile> extractCandidatesFromRequest(
            String candidateStr,
            String candidatesStr,
            String dataStr,
            HttpServletRequest request) {

        List<CandidateProfile> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        String rawJson = candidateStr;
        if (rawJson == null || rawJson.trim().isEmpty()) rawJson = candidatesStr;
        if (rawJson == null || rawJson.trim().isEmpty()) rawJson = dataStr;

        if (rawJson != null && !rawJson.trim().isEmpty()) {
            rawJson = rawJson.trim();
            try {
                if (rawJson.startsWith("[")) {
                    return mapper.readValue(rawJson, new TypeReference<List<CandidateProfile>>() {});
                } else if (rawJson.startsWith("{")) {
                    CandidateProfile single = mapper.readValue(rawJson, CandidateProfile.class);
                    list.add(single);
                    return list;
                }
            } catch (Exception e) {
                log.warn("Failed to parse JSON string for candidate, falling back to form fields: " + e.getMessage());
            }
        }

        // Fallback: Read individual form fields
        CandidateProfile candidate = new CandidateProfile();
        candidate.setFullname(request.getParameter("fullname"));
        candidate.setNickname(request.getParameter("nickname"));
        candidate.setNameEng(request.getParameter("nameEng"));
        candidate.setDob(request.getParameter("dob"));
        candidate.setAge(request.getParameter("age"));
        candidate.setGender(request.getParameter("gender"));
        candidate.setWeight(request.getParameter("weight"));
        candidate.setHeight(request.getParameter("height"));
        candidate.setReligion(request.getParameter("religion"));
        candidate.setPosition(request.getParameter("position"));
        candidate.setSalary(request.getParameter("salary"));
        candidate.setRotatePosition(request.getParameter("rotatePosition"));
        candidate.setExperience(request.getParameter("experience"));
        candidate.setInterviewDate(request.getParameter("interviewDate"));
        candidate.setInterviewTime(request.getParameter("interviewTime"));
        candidate.setPobVillage(request.getParameter("pobVillage"));
        candidate.setPobDistrict(request.getParameter("pobDistrict"));
        candidate.setPobProvince(request.getParameter("pobProvince"));
        candidate.setAddrVillage(request.getParameter("addrVillage"));
        candidate.setAddrDistrict(request.getParameter("addrDistrict"));
        candidate.setAddrProvince(request.getParameter("addrProvince"));
        candidate.setPhone(request.getParameter("phone"));
        candidate.setTel2(request.getParameter("tel2"));
        candidate.setEmail(request.getParameter("email"));
        candidate.setIdCardNo(request.getParameter("idCardNo"));
        candidate.setIdIssuedAt(request.getParameter("idIssuedAt"));
        candidate.setIdIssueDate(request.getParameter("idIssueDate"));
        candidate.setIdExpireDate(request.getParameter("idExpireDate"));
        candidate.setResidence(request.getParameter("residence"));
        candidate.setMaritalStatus(request.getParameter("maritalStatus"));
        candidate.setMbDrive(request.getParameter("mbDrive"));
        candidate.setMbLicense(request.getParameter("mbLicense"));
        candidate.setCarDrive(request.getParameter("carDrive"));
        candidate.setCarLicense(request.getParameter("carLicense"));
        candidate.setCarLicenseType(request.getParameter("carLicenseType"));
        candidate.setFatherAlive(request.getParameter("fatherAlive"));
        candidate.setFatherName(request.getParameter("fatherName"));
        candidate.setFatherAge(request.getParameter("fatherAge"));
        candidate.setFatherOcc(request.getParameter("fatherOcc"));
        candidate.setFatherTel(request.getParameter("fatherTel"));
        candidate.setMotherAlive(request.getParameter("motherAlive"));
        candidate.setMotherName(request.getParameter("motherName"));
        candidate.setMotherAge(request.getParameter("motherAge"));
        candidate.setMotherOcc(request.getParameter("motherOcc"));
        candidate.setMotherTel(request.getParameter("motherTel"));
        candidate.setSpouseName(request.getParameter("spouseName"));
        candidate.setSpouseAge(request.getParameter("spouseAge"));
        candidate.setSpouseTel(request.getParameter("spouseTel"));
        candidate.setSpouseOcc(request.getParameter("spouseOcc"));
        candidate.setImage(request.getParameter("image"));
        candidate.setFiles(request.getParameter("files"));

        String educationsJson = request.getParameter("educations");
        if (educationsJson != null && !educationsJson.trim().isEmpty()) {
            try {
                List<CandidateEducation> edus = mapper.readValue(educationsJson, new TypeReference<List<CandidateEducation>>() {});
                candidate.setEducations(edus);
            } catch (Exception e) {
                log.warn("Failed to parse educations JSON parameter: " + e.getMessage());
            }
        }

        String languagesJson = request.getParameter("languages");
        if (languagesJson != null && !languagesJson.trim().isEmpty()) {
            try {
                JsonNode langNode = mapper.readTree(languagesJson);
                candidate.setLanguagesJson(langNode);
            } catch (Exception e) {
                log.warn("Failed to parse languages JSON parameter: " + e.getMessage());
            }
        }

        list.add(candidate);
        return list;
    }

    private String getClientIp(HttpServletRequest request) {
        if (request == null) return null;
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
