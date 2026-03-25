package com.ldb.truck.Controller;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Staffs.*;
import com.ldb.truck.Service.RegisterService.FaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${base_url}")
@RequiredArgsConstructor

public class FaceController {

    private final FaceService faceService;
    private final MediaUploadService mediaUploadService;



    // ✅ consumes = MULTIPART_FORM_DATA_VALUE รองรับ FormData
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/register.service", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StaffRegisterResponseDTO> register(
            @RequestParam("staffCode") String staffCode,
            @RequestParam("username") String username,
            @RequestParam(value = "image", required = false) MultipartFile image  // ✅ optional
    ) throws IOException {

        // แปลง param → DTO
        StaffRegisterRequestDTO dto = new StaffRegisterRequestDTO();
        dto.setStaffCode(staffCode);
        dto.setUsername(username);
        dto.setRole("USER");

        // ✅ Generate password อัตโนมัติ
        String autoPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        dto.setPassword(autoPassword);

        // ✅ อัปโหลดรูปเฉพาะตอนที่ client ส่งมาเท่านั้น
        String fileUrl = null;
        if (image != null && !image.isEmpty()) {
            String fileName = mediaUploadService.uploadMedia(image);
            fileUrl = "http://khounkham.com/images/batery/" + fileName;
        }

        StaffRegisterResponseDTO response = faceService.registerStaff(dto, fileUrl);
        return ResponseEntity.ok(response);
    }

    // ✅ ดูข้อมูล staff (all หรือ by id)
    @CrossOrigin(origins = "*")
    @PostMapping("/getStaff")
    public ResponseEntity<?> getStaff(@RequestBody StaffQueryRequestDTO dto) {
        return ResponseEntity.ok(faceService.getStaff(dto));
    }

    // ✅ อัปเดตข้อมูล staff
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StaffResponseDTO> update(
            @RequestParam("staffId") String staffId,       // ✅ staffId จาก form-data
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("token") String token
            ) throws IOException {

        StaffQueryRequestDTO query = new StaffQueryRequestDTO();
        query.setStaffId(staffId);
        query.setToken(token);

        StaffUpdateRequestDTO dto = new StaffUpdateRequestDTO();
        dto.setUsername(username);
        dto.setPhone(phone);
        dto.setRole(role);
        dto.setStatus(status);

        return ResponseEntity.ok(faceService.updateStaff(query, dto, image));
    }


    //check in Controller
    @CrossOrigin(origins = "*")
    @PostMapping("/checkInCheckOut")
    public ResponseEntity<CheckInResponseDTO> checkIn(
            @RequestBody CheckInRequestDTO dto) {
        return ResponseEntity.ok(faceService.checkIn(dto));
    }

    //getAttendance
    @CrossOrigin(origins = "*")
    @PostMapping("/getCheckIn-CheckOutData")
    public ResponseEntity<AttendanceResponseDTO> getAttendance(
            @RequestBody AttendanceRequestDTO dto) {
        return ResponseEntity.ok(faceService.getAttendance(dto));
    }

    // ✅ ADMIN reset password
    @CrossOrigin(origins = "*")
    @PostMapping("/reset-password")
    public ResponseEntity<PasswordResponseDTO> resetPassword(
            @RequestBody ResetPasswordRequestDTO dto) {
        return ResponseEntity.ok(faceService.resetPassword(dto));
    }

    // ✅ Staff เปลี่ยน password ตัวเอง
    @CrossOrigin(origins = "*")
    @PostMapping("/change-password")
    public ResponseEntity<PasswordResponseDTO> changePassword(
            @RequestBody ChangePasswordRequestDTO dto) {
        return ResponseEntity.ok(faceService.changePassword(dto));
    }

}
