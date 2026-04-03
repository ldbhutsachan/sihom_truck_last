package com.ldb.truck.Controller;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.StaffRequest.DailyAttendanceRequestDTO;
import com.ldb.truck.Model.StaffRequest.LeaveApproveRequestDTO;
import com.ldb.truck.Model.StaffRequest.LeaveGetRequestDTO;
import com.ldb.truck.Model.Staffs.*;
import com.ldb.truck.Service.RegisterService.FaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
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
    public ResponseEntity<DataResponse> getStaff(@RequestBody StaffQueryRequestDTO dto) {
        return ResponseEntity.ok(faceService.getStaff(dto));
    }

    // ✅ อัปเดตข้อมูล staff
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateStaffInfo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataResponse> update(
            @RequestParam("staffId") String staffId,
            @RequestParam(value = "username",   required = false) String username,
            @RequestParam(value = "phone",      required = false) String phone,
            @RequestParam(value = "role",       required = false) String role,
            @RequestParam(value = "status",     required = false) String status,
            @RequestParam(value = "department", required = false) String department,
            @RequestParam(value = "position",   required = false) String position,
            @RequestParam(value = "borId",      required = false) Integer borId,
            @RequestParam(value = "image",      required = false) MultipartFile image,
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
        dto.setDepartment(department);
        dto.setPosition(position);
        dto.setBorId(borId);

        return ResponseEntity.ok(faceService.updateStaff(query, dto, image));
    }

    // ✅ API ใหม่ — HR/ADMIN อัปเดตเงินเดือน + work schedule
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateStuffBy-HR", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataResponse> updateSalary(
            @RequestParam("token") String token,
            @RequestParam("staffId") String staffId,
            @RequestParam(value = "baseSalary",     required = false) BigDecimal baseSalary,
            @RequestParam(value = "workSchedule",   required = false) String workSchedule,
            @RequestParam(value = "cycleWorkDays",  required = false) Integer cycleWorkDays,
            @RequestParam(value = "cycleOffDays",   required = false) Integer cycleOffDays,
            @RequestParam(value = "cycleStartDate", required = false) String cycleStartDate
    ) {
        StaffQueryRequestDTO query = new StaffQueryRequestDTO();
        query.setStaffId(staffId);
        query.setToken(token);

        StaffUpdateRequestDTO dto = new StaffUpdateRequestDTO();
        dto.setBaseSalary(baseSalary);
        dto.setWorkSchedule(workSchedule);
        dto.setCycleWorkDays(cycleWorkDays);
        dto.setCycleOffDays(cycleOffDays);
        dto.setCycleStartDate(cycleStartDate);

        return ResponseEntity.ok(faceService.updateSalarySchedule(query, dto));
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
    //rquest day off
    @CrossOrigin(origins = "*")
    @PostMapping("/requestDayoff")
    public ResponseEntity<DataResponse> requestLeave(
            @RequestBody LeaveRequestDTO dto) {
        return ResponseEntity.ok(faceService.requestLeave(dto));
    }

    //get leaveRequest
    @CrossOrigin(origins = "*")
    @PostMapping("/getLeaveRequest")
    public ResponseEntity<DataResponse> getLeave(
            @RequestBody LeaveGetRequestDTO dto) {
        return ResponseEntity.ok(faceService.getLeave(dto));
    }

    //approve LeaveReques
    @CrossOrigin(origins = "*")
    @PostMapping("/approveLeave")
    public ResponseEntity<DataResponse> approveLeave(
            @RequestBody LeaveApproveRequestDTO dto) {
        return ResponseEntity.ok(faceService.approveLeave(dto));
    }

    //DailyAttendance controller
    @CrossOrigin(origins = "*")
    @PostMapping("/getDailyAttendance")
    public ResponseEntity<DataResponse> getDailyAttendance(
            @RequestBody DailyAttendanceRequestDTO dto) {
        return ResponseEntity.ok(faceService.getDailyAttendance(dto));
    }

}
