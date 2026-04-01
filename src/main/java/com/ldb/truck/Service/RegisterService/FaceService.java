package com.ldb.truck.Service.RegisterService;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Staff.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Login.LoginReq;
import com.ldb.truck.Model.StaffRequest.LeaveGetRequestDTO;
import com.ldb.truck.Model.Staffs.*;
import com.ldb.truck.Repository.Staffs.*;
import com.ldb.truck.Util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaceService {
    private final UserRepository userRepository;  // ✅ ใช้ UserRepository ที่มีอยู่แล้ว
    private final MediaUploadService mediaUploadService;
    private final AttendanceLogRepository attendanceLogRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final TbBorRepository tbBorRepository;

    //register staff
    public StaffRegisterResponseDTO registerStaff(StaffRegisterRequestDTO dto,
                                                  String fileUrl) throws IOException {
        if (userRepository.existsByStaffCode(dto.getStaffCode())) {
            throw new RuntimeException("Staff Code ນີ້ຖືກໃຊ້ໄປແລ້ວ");
        }

        // Generate token
        String newToken;
        do {
            newToken = UUID.randomUUID().toString().replace("-", "");
        } while (userRepository.findByToken(newToken).isPresent());

        // ✅ Hash password ก่อนเก็บลง DB
        String plainPassword = dto.getPassword();  // เก็บ plain ไว้ก่อน response กลับ
        String hashedPassword = PasswordUtil.hashPassword(plainPassword);

        // สร้าง staff ใหม่
        StaffEntity staff = new StaffEntity();
        staff.setStaffCode(dto.getStaffCode());
        staff.setUsername(dto.getUsername());
        staff.setPasswordHash(hashedPassword);  // ✅ เก็บ hashed ใน DB
        staff.setRole(dto.getRole() != null ? dto.getRole() : "USER");
        staff.setStatus("ACTIVE");
        staff.setToken(newToken);
//        staff.setTokenExpiredAt(LocalDateTime.now().plusYears(1));
        staff.setTokenExpiredAt(LocalDateTime.now().plusMonths(5));

        if (fileUrl != null) {
            staff.setStaffImage(fileUrl);
        }

        StaffEntity saved = userRepository.save(staff);

        return new StaffRegisterResponseDTO(
                true,
                "ລົງທະບຽນສຳເລັດ",
                saved.getId(),
                saved.getStaffCode(),
                saved.getUsername(),
                plainPassword,      // ✅ ส่ง plain password กลับให้ client รู้
                saved.getCreatedAt()
        );
    }

    //getStaff service
    public DataResponse getStaff(StaffQueryRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา requester จาก token
            StaffEntity requester = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token not found"));

            // Step 2: เช็ค token หมดอายุหรือยัง
            if (requester.getTokenExpiredAt() != null
                    && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุแล้ว กรุณา Login ใหม่");
            }

            // Step 3: ✅ ถ้าไม่ส่ง staff มา → ดูข้อมูลตัวเองเสมอ
            if (dto.getStaffId() == null || dto.getStaffId().isEmpty()) {
                response.setStatus("00");
                response.setMessage("success");
                response.setDataResponse(mapToDTO(requester));
                return response;
            }

            // Step 4: USER → ดูได้แค่ตัวเอง ไม่ว่าจะส่ง staff อะไรมา
            if (requester.getRole().equals("USER")) {
                response.setStatus("00");
                response.setMessage("success");
                response.setDataResponse(mapToDTO(requester));
                return response;
            }

            // Step 5: ADMIN/HR → ดูทั้งหมดหรือคนเดียว
            if (dto.getStaffId().equalsIgnoreCase("all")) {
                response.setStatus("00");
                response.setMessage("success");
                response.setDataResponse(
                        userRepository.findAll()
                                .stream()
                                .map(this::mapToDTO)
                                .collect(Collectors.toList())
                );
            } else {
                Long id = Long.parseLong(dto.getStaffId());
                StaffEntity staff = userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ไม่พบ Staff id: " + id));
                response.setStatus("00");
                response.setMessage("success");
                response.setDataResponse(mapToDTO(staff));
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    // update staff service
    public DataResponse updateStaff(StaffQueryRequestDTO query,
                                    StaffUpdateRequestDTO dto,
                                    MultipartFile image) throws IOException {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา requester จาก token
            StaffEntity requester = userRepository.findByToken(query.getToken())
                    .orElseThrow(() -> new RuntimeException("Token not found"));

            // Step 2: เช็ค token หมดอายุหรือยัง
            if (requester.getTokenExpiredAt() != null
                    && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุแล้ว กรุณา Login ใหม่");
            }

            // Step 3: หา staff ที่จะ update
            Long id = Long.parseLong(query.getStaffId());
            StaffEntity staff = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Not found Staff id: " + id));

            // Step 4: เช็ค permission
            if (requester.getRole().equals("USER")) {
                if (!requester.getId().equals(staff.getId())) {
                    throw new RuntimeException("Cannot update because Token and Staff ID not match");
                }
                if (dto.getRole() != null) {
                    throw new RuntimeException("No right to change Role");
                }
            }

            // Step 5: อัปเดตข้อมูล
            if (dto.getUsername() != null && !dto.getUsername().isEmpty()) {
                if (userRepository.existsByUsername(dto.getUsername())
                        && !dto.getUsername().equals(staff.getUsername())) {
                    throw new RuntimeException("Username has been used");
                }
                staff.setUsername(dto.getUsername());
            }
            if (dto.getPhone() != null)      staff.setPhone(dto.getPhone());
            if (dto.getRole() != null)       staff.setRole(dto.getRole());
            if (dto.getDepartment() != null) staff.setDepartment(dto.getDepartment());
            if (dto.getPosition() != null)   staff.setPosition(dto.getPosition());

            // USER ไม่สามารถเปลี่ยน status ได้
            if (requester.getRole().equals("USER")) {
                if (dto.getStatus() != null) staff.setStatus(staff.getStatus());
            } else {
                if (dto.getStatus() != null) staff.setStatus(dto.getStatus());
            }

            // Step 6: อัปเดตรูปภาพ
            if (image != null && !image.isEmpty()) {
                String fileName = mediaUploadService.uploadMedia(image);
                String fileUrl  = "http://khounkham.com/images/batery/" + fileName;
                staff.setStaffImage(fileUrl);
            }

            // Step 7: อัปเดต borId
            if (dto.getBorId() != null) {
                staff.setBorId(dto.getBorId());
            }

            StaffEntity saved = userRepository.save(staff);

            response.setStatus("00");
            response.setMessage("ອັບເດດຂໍ້ມູນສຳເລັດ");
            response.setDataResponse(mapToDTO(saved));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    public DataResponse updateSalarySchedule(StaffQueryRequestDTO query,
                                             StaffUpdateRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: เช็ค token
            StaffEntity requester = userRepository.findByToken(query.getToken())
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            if (requester.getTokenExpiredAt() != null
                    && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุ กรุณา Login ใหม่");
            }

            // Step 2: เช็ค permission
            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")) {
                throw new RuntimeException("ไม่มีสิทธิ์ เฉพาะ HR หรือ ADMIN เท่านั้น");
            }

            // Step 3: หา staff
            Long id = Long.parseLong(query.getStaffId());
            StaffEntity staff = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ไม่พบ Staff id: " + id));

            // Step 4: อัปเดต baseSalary
            if (dto.getBaseSalary() != null) {
                staff.setBaseSalary(dto.getBaseSalary());
            }

            // Step 5: อัปเดต work schedule
            if (dto.getWorkSchedule() != null) {
                staff.setWorkSchedule(dto.getWorkSchedule());

                if (dto.getWorkSchedule().equals("CYCLE")) {
                    if (dto.getCycleWorkDays() == null)
                        throw new RuntimeException("กรุณาระบุ cycleWorkDays");
                    if (dto.getCycleOffDays() == null)
                        throw new RuntimeException("กรุณาระบุ cycleOffDays");
                    if (dto.getCycleStartDate() == null)
                        throw new RuntimeException("กรุณาระบุ cycleStartDate");

                    staff.setCycleWorkDays(dto.getCycleWorkDays());
                    staff.setCycleOffDays(dto.getCycleOffDays());
                    staff.setCycleStartDate(
                            LocalDate.parse(dto.getCycleStartDate(),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                } else {
                    staff.setCycleWorkDays(null);
                    staff.setCycleOffDays(null);
                    staff.setCycleStartDate(null);
                }
            }

            // Step 6: บันทึก
            StaffEntity saved = userRepository.save(staff);

            // Step 7: ✅ Success response
            Map<String, Object> data = new HashMap<>();
            data.put("staffId",        saved.getId());
            data.put("staffCode",      saved.getStaffCode());
            data.put("username",       saved.getUsername());
            data.put("baseSalary",     saved.getBaseSalary());
            data.put("workSchedule",   saved.getWorkSchedule());
            data.put("cycleWorkDays",  saved.getCycleWorkDays());
            data.put("cycleOffDays",   saved.getCycleOffDays());
            data.put("cycleStartDate", saved.getCycleStartDate());

            response.setStatus("00");
            response.setMessage("ອັບເດດຂໍ້ມູນເງິນເດືອນສຳເລັດ");
            response.setDataResponse(data);

        } catch (Exception e) {
            // ❌ Error response
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }
    // Helper method แปลง Entity → DTO
    private StaffResponseDTO mapToDTO(StaffEntity staff) {
        String borName = null;
        if (staff.getBorId() != null) {
            borName = tbBorRepository.findById(staff.getBorId())
                    .map(TbBorEntity::getBName)
                    .orElse(null);
        }
        return new StaffResponseDTO(
                staff.getId(),
                staff.getStaffCode(),
                staff.getUsername(),
                staff.getPhone(),
                staff.getRole(),
                staff.getStatus(),
                staff.getStaffImage(),
                staff.getDepartment(),
                staff.getPosition(),
                staff.getBorId(),   // ✅ ส่ง borId กลับไป
                borName,            // ✅ ส่งชื่อ Bor กลับไป
                staff.getCreatedAt(),
                staff.getUpdatedAt()
        );
    }

    //check-in and check-out service
    public CheckInResponseDTO checkIn(CheckInRequestDTO dto) {

        StaffEntity staff = userRepository.findByStaffCode(dto.getStaffCode())
                .orElseThrow(() -> new RuntimeException("ไม่พบ Staff Code: " + dto.getStaffCode()));

        if (!staff.getStatus().equals("ACTIVE")) {
            throw new RuntimeException("Staff นี้ไม่ได้ใช้งานแล้ว");
        }

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay   = startOfDay.plusDays(1).minusSeconds(1);

        Optional<AttendanceLog> existingCheckIn =
                attendanceLogRepository
                        .findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeDesc(
                                staff.getId(), "CHECK_IN", startOfDay, endOfDay);

        Optional<AttendanceLog> existingCheckOut =
                attendanceLogRepository
                        .findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeDesc(
                                staff.getId(), "CHECK_OUT", startOfDay, endOfDay);

        AttendanceLog log;
        String message;
        String notimessage = "";
        LocalTime now = LocalTime.now();

        if (existingCheckIn.isEmpty()) {
            log = new AttendanceLog();
            log.setStaff(staff);
            log.setCheckType("CHECK_IN");
            log.setCheckTime(LocalDateTime.now());
            log.setIpAddress(dto.getIpAddress());    // ✅
            log.setMacAddress(dto.getMacAddress());  // ✅
            message = "Check-in ສຳເລັດ";
            notimessage = now.isAfter(LocalTime.of(8, 1)) ? "LATE" : "ON-TIME";

        } else if (existingCheckOut.isEmpty()) {
            log = new AttendanceLog();
            log.setStaff(staff);
            log.setCheckType("CHECK_OUT");
            log.setCheckTime(LocalDateTime.now());
            log.setIpAddress(dto.getIpAddress());    // ✅
            log.setMacAddress(dto.getMacAddress());  // ✅
            message = "Check-out ສຳເລັດ";
            notimessage = now.isBefore(LocalTime.of(17, 0)) ? "EARLY" : "ON-TIME";

        } else {
            log = existingCheckOut.get();
            log.setCheckTime(LocalDateTime.now());
            log.setIpAddress(dto.getIpAddress());    // ✅
            log.setMacAddress(dto.getMacAddress());  // ✅
            message = "Check-out ສຳເລັດ";
            notimessage = now.isBefore(LocalTime.of(17, 0)) ? "EARLY" : "ON-TIME";
        }

        attendanceLogRepository.save(log);

        return new CheckInResponseDTO(
                true,
                message,
                notimessage,
                staff.getStaffCode(),
                staff.getUsername(),
                log.getCheckType(),
                log.getCheckTime()
        );
    }

    //attendanceService
    public AttendanceResponseDTO getAttendance(AttendanceRequestDTO dto) {

        // Step 1: หา requester จาก token แทน staffCode
        StaffEntity requester = userRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new RuntimeException("Token Not found"));

        // Step 2: เช็ค token หมดอายุหรือยัง
        if (requester.getTokenExpiredAt() != null
                && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token หมดอายุแล้ว กรุณา Login ใหม่");
        }

        // Step 3: เช็ค permission
        if (requester.getRole().equals("USER")
                && dto.getStaffCode().equalsIgnoreCase("all")) {
            throw new RuntimeException("Only ADMIN can see all");
        }

        if (requester.getRole().equals("USER")
                && !dto.getStaffCode().equals(requester.getStaffCode())) {
            throw new RuntimeException("No right to see other Staff");
        }

        // Step 4: กำหนดช่วงวันที่
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startDateTime = (dto.getStartDate() != null)
                ? LocalDate.parse(dto.getStartDate(), formatter).atStartOfDay()
                : LocalDate.now().withDayOfMonth(1).atStartOfDay();

        LocalDateTime endDateTime = (dto.getEndDate() != null)
                ? LocalDate.parse(dto.getEndDate(), formatter).atTime(23, 59, 59)
                : LocalDate.now().atTime(23, 59, 59);

        // Step 5: ดึง logs
        List<AttendanceLog> logs;

        if (dto.getStaffCode().equalsIgnoreCase("all")) {
            logs = attendanceLogRepository
                    .findAllByCheckTimeBetweenOrderByCheckTimeAsc(startDateTime, endDateTime);
        } else {
            StaffEntity staff = userRepository.findByStaffCode(dto.getStaffCode())
                    .orElseThrow(() -> new RuntimeException("Not found Staff Code: " + dto.getStaffCode()));
            logs = attendanceLogRepository
                    .findByStaff_IdAndCheckTimeBetweenOrderByCheckTimeAsc(
                            staff.getId(), startDateTime, endDateTime);
        }

        // Step 6: จัดกลุ่มตาม staff + วันที่ แล้วแยก CHECK_IN / CHECK_OUT
        Map<String, AttendanceDayDTO> map = new LinkedHashMap<>();

        for (AttendanceLog log : logs) {
            String key = log.getStaff().getStaffCode()
                    + "_" + log.getCheckTime().toLocalDate();

            map.putIfAbsent(key, new AttendanceDayDTO(
                    log.getId(),
                    log.getStaff().getId(),
                    log.getStaff().getStaffCode(),
                    log.getStaff().getUsername(),
                    log.getCheckTime().toLocalDate(),
                    null,
                    null,
                    null,   // checkInStatus
                    null,    // checkOutStatus
                    log.getIpAddress(),
                    log.getMacAddress()
            ));

            AttendanceDayDTO day = map.get(key);

            if (log.getCheckType().equals("CHECK_IN")) {
                day.setCheckIn(log.getCheckTime());
                day.setCheckInStatus(calculateCheckInStatus(log.getCheckTime()));
            } else {
                day.setCheckOut(log.getCheckTime());
                day.setCheckOutStatus(calculateCheckOutStatus(log.getCheckTime()));
            }
        }

        List<AttendanceDayDTO> result = new ArrayList<>(map.values());
        return new AttendanceResponseDTO(true, "Success Fetching Data", result);
    }
    // ✅ คำนวณ CHECK_IN status
    private String calculateCheckInStatus(LocalDateTime checkInTime) {

        LocalTime checkIn = checkInTime.toLocalTime();
        LocalTime deadline = LocalTime.of(8, 1); // หลัง 08:01 = สาย

        if (checkIn.isAfter(deadline)) {
            // คำนวณนาทีที่สาย
            long minutesLate = java.time.Duration.between(deadline, checkIn).toMinutes();

            if (minutesLate >= 60) {
                long hours = minutesLate / 60;
                long mins  = minutesLate % 60;
                return "LATE " + hours + " hr " + mins + " mins";
            } else {
                return "LATE " + minutesLate + " mins";
            }
        }
        return "ON-TIME";
    }

    // ✅ คำนวณ CHECK_OUT status
    private String calculateCheckOutStatus(LocalDateTime checkOutTime) {

        LocalTime checkOut = checkOutTime.toLocalTime();
        LocalTime endTime  = LocalTime.of(17, 0); // ก่อน 17:00 = ออกก่อนเวลา

        if (checkOut.isBefore(endTime)) {
            // คำนวณนาทีที่ออกก่อนเวลา
            long minutesEarly = java.time.Duration.between(checkOut, endTime).toMinutes();

            if (minutesEarly >= 60) {
                long hours = minutesEarly / 60;
                long mins  = minutesEarly % 60;
                return "EARLY " + hours + " hr " + mins + " mins";
            } else {
                return "EARLY " + minutesEarly + " mins";
            }
        }
        return "ON-TIME";
    }



    //LOGIN SERVICE FOR FACE-DETECTION SYSTEM
    public LoginResDTO login(LoginReq dto) {
        LoginResDTO result = new LoginResDTO();

        // Step 1: หา staff จาก username
        StaffEntity staff = userRepository.findByUsername(dto.getUser())
                .orElseThrow(() -> new RuntimeException("Not found Username"));

        // Step 2: ✅ เช็ค password ด้วย PasswordUtil
        if (!PasswordUtil.verifyPassword(dto.getPassword(), staff.getPasswordHash())) {
            throw new RuntimeException("Incorrect Password");
        }

        // Step 3: เช็ค status
        if (!staff.getStatus().equals("ACTIVE")) {
            throw new RuntimeException("บัญชีนี้ถูกระงับการใช้งาน");
        }

        // Step 4: Generate token ใหม่
        String newToken;
        do {
            newToken = UUID.randomUUID().toString().replace("-", "");
        } while (userRepository.findByToken(newToken).isPresent());

        // Step 5: อัปเดต token และ tokenExpiredAt
//        LocalDateTime newExpiredAt = LocalDateTime.now().plusYears(1);
        LocalDateTime newExpiredAt = LocalDateTime.now().plusDays(5);
        staff.setToken(newToken);
        staff.setTokenExpiredAt(newExpiredAt);
        userRepository.save(staff);

        result.setStatus("00");
        result.setMessage("success");
        result.setData(new StaffLoginResponseDTO(
                staff.getUsername(),
                newToken,
                staff.getRole(),
                newExpiredAt
        ));

        return result;
    }

    // ==============================
// ADMIN reset password
// ==============================
    public PasswordResponseDTO resetPassword(ResetPasswordRequestDTO dto) {

        // Step 1: หา ADMIN จาก token
        StaffEntity admin = userRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        // Step 2: เช็ค token หมดอายุ
        if (admin.getTokenExpiredAt() != null
                && admin.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token หมดอายุแล้ว กรุณา Login ใหม่");
        }

        // Step 3: เช็คว่าเป็น ADMIN
        if (!admin.getRole().equals("ADMIN")) {
            throw new RuntimeException("only ADMIN can reset");
        }

        // Step 4: หา staff ที่จะ reset
        StaffEntity staff = userRepository.findByStaffCode(dto.getStaffCode())
                .orElseThrow(() -> new RuntimeException("not found Staff Code: " + dto.getStaffCode()));

        // Step 5: Generate password ใหม่
        String newPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String hashedPassword = PasswordUtil.hashPassword(newPassword);

        staff.setPasswordHash(hashedPassword);
        userRepository.save(staff);

        return new PasswordResponseDTO(
                true,
                "Reset Password ສຳເລັດ",
                newPassword     // ✅ ส่ง plain password กลับให้ ADMIN รู้
        );
    }

    // ==============================
// Staff เปลี่ยน password ตัวเอง
// ==============================
    public PasswordResponseDTO changePassword(ChangePasswordRequestDTO dto) {

        // Step 1: หา staff จาก token
        StaffEntity staff = userRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        // Step 2: เช็ค token หมดอายุ
        if (staff.getTokenExpiredAt() != null
                && staff.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token หมดอายุแล้ว กรุณา Login ใหม่");
        }

        // Step 3: เช็ค old password
        if (!PasswordUtil.verifyPassword(dto.getOldPassword(), staff.getPasswordHash())) {
            throw new RuntimeException("Password ເກົ່າບໍ່ຖືກຕ້ອງ");
        }

        // Step 4: เช็คว่า new password ไม่เหมือน old password
        if (dto.getOldPassword().equals(dto.getNewPassword())) {
            throw new RuntimeException("Password Must Be Different from old Password");
        }

        // Step 5: Hash และบันทึก password ใหม่
        String hashedPassword = PasswordUtil.hashPassword(dto.getNewPassword());
        staff.setPasswordHash(hashedPassword);
        userRepository.save(staff);

        return new PasswordResponseDTO(
                true,
                "ປ່ຽນ Password ສຳເລັດ",
                null    // ✅ ไม่ส่ง password กลับ เพราะ staff รู้อยู่แล้ว
        );
    }

    //service set dayy oof for staff
    // StaffService.java — เพิ่ม method
//    public Object setDayOff(Map<String, Object> body) {
//
//        String token = (String) body.get("token");
//        List<String> dayOfWeeks = (List<String>) body.get("dayOfWeeks");
//        Long staffId = Long.parseLong(body.get("staffId").toString());
//
//        // เช็ค HR หรือ ADMIN เท่านั้น
//        StaffEntity requester = userRepository.findByToken(token)
//                .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));
//
//        if (!requester.getRole().equals("ADMIN")
//                && !requester.getRole().equals("HR")) {
//            throw new RuntimeException("ไม่มีสิทธิ์ เฉพาะ ADMIN หรือ HR เท่านั้น");
//        }
//
//        // ✅ ลบ pattern เดิมออกก่อน แล้วบันทึกใหม่
//        staffDayOffRepository.deleteByStaffId(staffId);
//
//        dayOfWeeks.forEach(day -> {
//            StaffDayOff dayOff = new StaffDayOff();
//            dayOff.setStaffId(staffId);
//            dayOff.setDayOfWeek(day.toUpperCase()); // MONDAY, TUESDAY...
//            staffDayOffRepository.save(dayOff);
//        });
//
//        return Map.of(
//                "success", true,
//                "message", "ບັນທຶກວັນພັກສຳເລັດ",
//                "staffId", staffId,
//                "dayOfWeeks", dayOfWeeks
//        );
//    }


    //service request day off
    public DataResponse requestLeave(LeaveRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา staff จาก token
            StaffEntity staff = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            // Step 2: เช็ค token หมดอายุ
            if (staff.getTokenExpiredAt() != null
                    && staff.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุ กรุณา Login ใหม่");
            }

            // Step 3: แปลงวันที่
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dto.getStartDate(), formatter);
            LocalDate endDate   = LocalDate.parse(dto.getEndDate(), formatter);

            // Step 4: เช็ควันที่
            if (startDate.isAfter(endDate)) {
                throw new RuntimeException("วันที่เริ่มต้องไม่เกินวันที่สิ้นสุด");
            }

            // Step 5: คำนวณ totalDays ตาม work schedule
            int totalDays = calculateWorkDays(staff, startDate, endDate);

            if (totalDays == 0) {
                throw new RuntimeException("ຊ່ວງທີ່ເລືອກແມ່ນວັນຢຸດທັງໝົດ");
            }

            // Step 6: เช็คซ้อนทับ ทั้ง PENDING และ APPROVED
            List<LeaveRequest> overlapping = leaveRequestRepository
                    .findByStaff_IdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                            staff.getId(), "APPROVED", endDate, startDate);

            List<LeaveRequest> overlappingPending = leaveRequestRepository
                    .findByStaff_IdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                            staff.getId(), "PENDING", endDate, startDate);

            if (!overlapping.isEmpty()) {
                throw new RuntimeException("ຊ່ວງວັນທີຂໍລາຊ້ຳກັບວັນລາທີ່ອະນຸມັດແລ້ວ");
            }

            if (!overlappingPending.isEmpty()) {
                throw new RuntimeException("ຊ່ວງວັນທີຂໍລາຊ້ຳກັບຄຳຂໍທີ່ລໍຖ້າອະນຸມັດຢູ່");
            }

            // Step 7: เช็คโควต้า
            int quota     = getLeaveQuota(staff, dto.getLeaveType());
            int used      = getLeaveUsed(staff.getId(), dto.getLeaveType(), startDate.getYear());
            int remaining = quota - used;

            if (!dto.getLeaveType().equals("UNPAID") && totalDays > remaining) {
                throw new RuntimeException("วันลาไม่พอ คงเหลือ " + remaining + " วัน แต่ขอ " + totalDays + " วัน");
            }

            // Step 8: บันทึก
            LeaveRequest leave = new LeaveRequest();
            leave.setStaff(staff);
            leave.setLeaveType(dto.getLeaveType());
            leave.setStartDate(startDate);
            leave.setEndDate(endDate);
            leave.setTotalDays(totalDays);
            leave.setStatus("PENDING");
            leave.setReason(dto.getReason());

            LeaveRequest saved = leaveRequestRepository.save(leave);

            // Step 9: สร้าง response data
            Map<String, Object> data = new HashMap<>();
            data.put("leaveId",       saved.getId());
            data.put("staffCode",     staff.getStaffCode());
            data.put("username",      staff.getUsername());
            data.put("leaveType",     saved.getLeaveType());
            data.put("startDate",     saved.getStartDate().toString());
            data.put("endDate",       saved.getEndDate().toString());
            data.put("totalDays",     saved.getTotalDays());
            data.put("status",        saved.getStatus());
            data.put("remainingDays", remaining - totalDays);
            data.put("createdAt",     saved.getCreatedAt());

            response.setStatus("00");
            response.setMessage("ສົ່ງຄຳຂໍລາພັກສຳເລັດ ລໍຖ້າການອະນຸມັດ");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }
    private int calculateWorkDays(StaffEntity staff,
                                  LocalDate startDate, LocalDate endDate) {

        String schedule = staff.getWorkSchedule() != null
                ? staff.getWorkSchedule() : "MON_FRI";

        int workDays = 0;
        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {
            if (isWorkDay(staff, current, schedule)) {
                workDays++;
            }
            current = current.plusDays(1);
        }

        return workDays;
    }

    private boolean isWorkDay(StaffEntity staff, LocalDate date, String schedule) {

        java.time.DayOfWeek day = date.getDayOfWeek();

        switch (schedule) {
            case "MON_FRI":
                return day != java.time.DayOfWeek.SATURDAY
                        && day != java.time.DayOfWeek.SUNDAY;

            case "CYCLE":
                return isCycleWorkDay(staff, date);

            default:
                return day != java.time.DayOfWeek.SATURDAY
                        && day != java.time.DayOfWeek.SUNDAY;
        }
    }

    private boolean isCycleWorkDay(StaffEntity staff, LocalDate date) {

        LocalDate startDate = staff.getCycleStartDate();
        if (startDate == null) return true;

        int workDays   = staff.getCycleWorkDays() != null ? staff.getCycleWorkDays() : 24;
        int offDays    = staff.getCycleOffDays()  != null ? staff.getCycleOffDays()  : 7;
        int cycleLength = workDays + offDays;

        long daysSinceStart = java.time.temporal.ChronoUnit.DAYS.between(startDate, date);

        if (daysSinceStart < 0) return true;

        int positionInCycle = (int) (daysSinceStart % cycleLength);

        return positionInCycle < workDays;
    }

    private int getLeaveQuota(StaffEntity staff, String leaveType) {
        switch (leaveType) {
            case "SICK":      return staff.getLeaveQuotaSick()      != null ? staff.getLeaveQuotaSick()      : 30;
            case "PERSONAL":  return staff.getLeaveQuotaPersonal()  != null ? staff.getLeaveQuotaPersonal()  : 7;
            case "MATERNITY": return staff.getLeaveQuotaMaternity() != null ? staff.getLeaveQuotaMaternity() : 90;
            case "UNPAID":    return 999;
            default: throw new RuntimeException("ประเภทวันลาไม่ถูกต้อง: " + leaveType);
        }
    }

    private int getLeaveUsed(Long staffId, String leaveType, int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear   = LocalDate.of(year, 12, 31);

        List<LeaveRequest> used = leaveRequestRepository
                .findByStaff_IdAndLeaveTypeAndStatusAndStartDateBetween(
                        staffId, leaveType, "APPROVED", startOfYear, endOfYear);

        return used.stream().mapToInt(LeaveRequest::getTotalDays).sum();
    }

    //getLeaveRequest
    public DataResponse getLeave(LeaveGetRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา requester จาก token
            StaffEntity requester = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            // Step 2: เช็ค token หมดอายุ
            if (requester.getTokenExpiredAt() != null
                    && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุ กรุณา Login ใหม่");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // แปลงวันที่ถ้ามี
            LocalDate startDate = (dto.getStartDate() != null && !dto.getStartDate().isEmpty())
                    ? LocalDate.parse(dto.getStartDate(), formatter) : null;
            LocalDate endDate = (dto.getEndDate() != null && !dto.getEndDate().isEmpty())
                    ? LocalDate.parse(dto.getEndDate(), formatter) : null;

            List<LeaveRequest> leaves;

            // Step 3: USER → ดูได้แค่ของตัวเอง
            if (requester.getRole().equals("USER")) {
                leaves = leaveRequestRepository.findByFilters(
                        requester.getId(),  // ✅ lock staffId เป็นตัวเอง
                        null,               // borId ไม่ใช้
                        (dto.getStatus() != null && !dto.getStatus().isEmpty())
                                ? dto.getStatus() : null,
                        startDate,
                        endDate
                );

            } else {
                // Step 4: HR/ADMIN → filter ตาม params ทั้งหมด
                Long staffId = (dto.getStaffId() != null
                        && !dto.getStaffId().isEmpty()
                        && !dto.getStaffId().equalsIgnoreCase("all"))
                        ? Long.parseLong(dto.getStaffId()) : null;

                leaves = leaveRequestRepository.findByFilters(
                        staffId,
                        dto.getBorId(),
                        (dto.getStatus() != null && !dto.getStatus().isEmpty())
                                ? dto.getStatus() : null,
                        startDate,
                        endDate
                );
            }

            // Step 5: แปลง Entity → Map
            List<Map<String, Object>> data = leaves.stream().map(leave -> {
                Map<String, Object> item = new HashMap<>();
                item.put("leaveId",   leave.getId());
                item.put("staffId",   leave.getStaff().getId());
                item.put("staffCode", leave.getStaff().getStaffCode());
                item.put("username",  leave.getStaff().getUsername());
                item.put("staffImage", leave.getStaff().getStaffImage());
                item.put("borId",     leave.getStaff().getBorId());
                item.put("leaveType", leave.getLeaveType());
                item.put("startDate", leave.getStartDate().toString());
                item.put("endDate",   leave.getEndDate().toString());
                item.put("totalDays", leave.getTotalDays());
                item.put("status",    leave.getStatus());
                item.put("reason",    leave.getReason());
                item.put("approvedBy", leave.getApprovedBy() != null
                        ? leave.getApprovedBy().getUsername() : null);
                item.put("createdAt", leave.getCreatedAt());
                item.put("updatedAt", leave.getUpdatedAt());
                return item;
            }).collect(Collectors.toList());

            response.setStatus("00");
            response.setMessage("success");
            response.setDataResponse(data);
            response.setSumFooter(data.size());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }
}
