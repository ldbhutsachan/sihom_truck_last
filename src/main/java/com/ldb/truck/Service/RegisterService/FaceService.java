package com.ldb.truck.Service.RegisterService;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Staff.AttendanceLog;
import com.ldb.truck.Entity.Staff.StaffEntity;
import com.ldb.truck.Model.Login.Login.LoginReq;
import com.ldb.truck.Model.Staffs.*;
import com.ldb.truck.Repository.Staffs.AttendanceLogRepository;
import com.ldb.truck.Repository.Staffs.UserRepository;
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

    //register staff
    public StaffRegisterResponseDTO registerStaff(StaffRegisterRequestDTO dto,
                                                  String fileUrl) throws IOException {

        // เช็ค username ซ้ำ
//        if (userRepository.existsByUsername(dto.getUsername())) {
//            throw new RuntimeException("Username นี้ถูกใช้งานแล้ว");
//        }

        // เช็ค staffCode ซ้ำ
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
    public Object getStaff(StaffQueryRequestDTO dto) {

        // Step 1: หา requester จาก token
        StaffEntity requester = userRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        // Step 2: เช็ค token หมดอายุหรือยัง
        if (requester.getTokenExpiredAt() != null
                && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token หมดอายุแล้ว กรุณา Login ใหม่");
        }

        // Step 3: ถ้า role = USER → ดูได้แค่ข้อมูลตัวเอง
        if (requester.getRole().equals("USER")) {
            return mapToDTO(requester);
        }

        // Step 4: ถ้า role = ADMIN → เช็คว่าจะดูทั้งหมดหรือดูคนเดียว
        if (dto.getStaffId().equalsIgnoreCase("all")) {
            // ADMIN ดูทั้งหมด
            return userRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } else {
            // ADMIN ดูคนเดียวตาม id
            Long id = Long.parseLong(dto.getStaffId());
            StaffEntity staff = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ไม่พบ Staff id: " + id));
            return mapToDTO(staff);
        }
    }

    // update staff service
    public StaffResponseDTO updateStaff(StaffQueryRequestDTO query,
                                        StaffUpdateRequestDTO dto,
                                        MultipartFile image) throws IOException {

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

        // Step 4: ✅ เช็คว่า token กับ staffId เป็นคนเดียวกันไหม (เฉพาะ USER)
        if (requester.getRole().equals("USER")) {

            // token กับ staffId ต้องตรงกันในตาราง
            if (!requester.getId().equals(staff.getId())) {
                throw new RuntimeException(" Can not update becuase Token and Staff ID not match ");
            }

            // USER ห้ามเปลี่ยน role
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
        if (dto.getPhone() != null)  staff.setPhone(dto.getPhone());
        if (dto.getRole() != null)   staff.setRole(dto.getRole());

        //if role user keep default status else keep the input
        if (requester.getRole().equals("USER")){
            if (dto.getStatus() != null) staff.setStatus(staff.getStatus());
        }else {
            if (dto.getStatus() != null) staff.setStatus(dto.getStatus());
        }

        // Step 6: อัปเดตรูปภาพถ้ามีส่งมา
        if (image != null && !image.isEmpty()) {
            String fileName = mediaUploadService.uploadMedia(image);
            String fileUrl = "http://khounkham.com/images/batery/" + fileName;
            staff.setStaffImage(fileUrl);
        }

        StaffEntity saved = userRepository.save(staff);
        return mapToDTO(saved);
    }
    // Helper method แปลง Entity → DTO
    private StaffResponseDTO mapToDTO(StaffEntity staff) {
        return new StaffResponseDTO(
                staff.getId(),
                staff.getStaffCode(),
                staff.getUsername(),
                staff.getPhone(),
                staff.getRole(),
                staff.getStatus(),
                staff.getStaffImage(),
                staff.getCreatedAt(),
                staff.getUpdatedAt()
        );
    }

    //check-in and check-out service
    public CheckInResponseDTO checkIn(CheckInRequestDTO dto) {

        // Step 1: หา staff จาก staffCode
        StaffEntity staff = userRepository.findByStaffCode(dto.getStaffCode())
                .orElseThrow(() -> new RuntimeException("ບໍ່ເຫັນ Staff Code: " + dto.getStaffCode()));

        // Step 2: เช็คว่า staff active อยู่ไหม
        if (!staff.getStatus().equals("ACTIVE")) {
            throw new RuntimeException("Staff ນີ້ບໍ່ໄດ້ໃຊ້ງານແລ້ວ");
        }

        // Step 3: กำหนดช่วงเวลาวันนี้
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay   = startOfDay.plusDays(1).minusSeconds(1);

        // Step 4: หา CHECK_IN ของวันนี้
        Optional<AttendanceLog> existingCheckIn =
                attendanceLogRepository
                        .findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeDesc(
                                staff.getId(), "CHECK_IN", startOfDay, endOfDay);

        // Step 5: หา CHECK_OUT ของวันนี้
        Optional<AttendanceLog> existingCheckOut =
                attendanceLogRepository
                        .findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeDesc(
                                staff.getId(), "CHECK_OUT", startOfDay, endOfDay);

        AttendanceLog log;
        String message;
        String notimessage = "";  // default ว่าง

        LocalTime now = LocalTime.now();  // เวลาปัจจุบัน

        if (existingCheckIn.isEmpty()) {
            // ✅ ยังไม่มี CHECK_IN วันนี้เลย → INSERT CHECK_IN ใหม่
            log = new AttendanceLog();
            log.setStaff(staff);
            log.setCheckType("CHECK_IN");
            log.setCheckTime(LocalDateTime.now());
            message = "Check-in ສຳເລັດ";

            // ✅ เช็คว่า check-in หลัง 08:01 ไหม → สาย
            if (now.isAfter(LocalTime.of(8, 1))) {
                notimessage = "Check-in Late";
            } else {
                notimessage = "Check-in on time";
            }

        } else if (existingCheckOut.isEmpty()) {
            // ✅ มี CHECK_IN แล้ว แต่ยังไม่มี CHECK_OUT → INSERT CHECK_OUT ใหม่
            log = new AttendanceLog();
            log.setStaff(staff);
            log.setCheckType("CHECK_OUT");
            log.setCheckTime(LocalDateTime.now());
            message = "Check-out ສຳເລັດ";

            // ✅ เช็คว่า check-out ก่อน 17:00 ไหม → ออกก่อนเวลา
            if (now.isBefore(LocalTime.of(17, 0))) {
                notimessage = "Check-out before the time";
            } else {
                notimessage = "Check-out on time";
            }

        } else {
            // ✅ มีทั้ง CHECK_IN และ CHECK_OUT แล้ว → อัปเดตเวลา CHECK_OUT ล่าสุด
            log = existingCheckOut.get();
            log.setCheckTime(LocalDateTime.now());
            message = "Check-out ສຳເລັດ";

            // ✅ เช็คเวลา CHECK_OUT ที่อัปเดตด้วย
            if (now.isBefore(LocalTime.of(17, 0))) {
                notimessage = "Check-out before the time";
            } else {
                notimessage = "Check-out on time";
            }
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
                    log.getStaff().getStaffCode(),
                    log.getStaff().getUsername(),
                    log.getCheckTime().toLocalDate(),
                    null,
                    null,
                    null,   // checkInStatus
                    null    // checkOutStatus
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
}
