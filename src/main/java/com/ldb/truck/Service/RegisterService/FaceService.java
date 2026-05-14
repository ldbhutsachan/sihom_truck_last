package com.ldb.truck.Service.RegisterService;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Staff.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Login.LoginReq;
import com.ldb.truck.Model.StaffRequest.DailyAttendanceRequestDTO;
import com.ldb.truck.Model.StaffRequest.LeaveApproveRequestDTO;
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
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

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

        //  Hash password ก่อนเก็บลง DB
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

            // Step 3:  ถ้าไม่ส่ง staff มา → ดูข้อมูลตัวเองเสมอ
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
                        .orElseThrow(() -> new RuntimeException("Not found Staff id: " + id));
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
                    throw new RuntimeException("This Username has been used");
                }
                staff.setUsername(dto.getUsername());
            }
            if (dto.getLaoname() != null)      staff.setLao_name(dto.getLaoname());
            if (dto.getPhone() != null)      staff.setPhone(dto.getPhone());
            if (dto.getRole() != null)       staff.setRole(dto.getRole());
            if (dto.getDeptId() != null) staff.setDept_id(dto.getDeptId());
            if (dto.getPosId() != null)   staff.setPos_id(dto.getPosId());
            if(dto.getGender() != null)  staff.setGender(dto.getGender());
            if(dto.getAddress() != null)  staff.setAddress(dto.getAddress());
            if (dto.getBirthDate() != null && !dto.getBirthDate().isBlank()) {
                staff.setBirth_date(
                        LocalDate.parse(dto.getBirthDate(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

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
            //update department and position
            if (dto.getDeptId() != null) {
                staff.setDept_id(dto.getDeptId());
            }
            if (dto.getPosId() != null) {
                staff.setPos_id(dto.getPosId());
            }
            if (dto.getGender() != null) {
                staff.setGender(dto.getGender());
            }
            if (dto.getAddress() != null) {
                staff.setAddress(dto.getAddress());
            }
            // เพิ่มใน updateSalarySchedule
            if (dto.getStartWorkDate() != null && !dto.getStartWorkDate().isBlank()) {
                staff.setStartwork_date(
                        LocalDate.parse(dto.getStartWorkDate(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

            if (dto.getBirthDate() != null && !dto.getBirthDate().isBlank()) {
                staff.setBirth_date(
                        LocalDate.parse(dto.getBirthDate(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            if (dto.getLaoname() != null)      staff.setLao_name(dto.getLaoname());
            if (dto.getUsername() != null && !dto.getUsername().isEmpty()) {
                if (userRepository.existsByUsername(dto.getUsername())
                        && !dto.getUsername().equals(staff.getUsername())) {
                    throw new RuntimeException("This Username has been used");
                }
                staff.setUsername(dto.getUsername());
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
            //  Error response
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }
    // Helper method แปลง Entity → DTO
    private StaffResponseDTO mapToDTO(StaffEntity staff) {

        String borName      = null;
        String deptName     = null;
        String positionName = null;

        if (staff.getBorId() != null) {
            borName = tbBorRepository.findById(staff.getBorId())
                    .map(TbBorEntity::getBName)
                    .orElse(null);
        }

        // ✅ ใช้ getDept_id() ตามชื่อจริงใน Entity
        if (staff.getDept_id() != null) {
            deptName = departmentRepository.findById(staff.getDept_id())
                    .map(Department::getDeptName)
                    .orElse(null);
        }

        // ✅ ใช้ getPos_id() ตามชื่อจริงใน Entity
        if (staff.getPos_id() != null) {
            positionName = positionRepository.findById(staff.getPos_id())
                    .map(Position::getPosName)
                    .orElse(null);
        }

        return new StaffResponseDTO(
                staff.getId(),
                staff.getStaffCode(),
                staff.getUsername(),
                staff.getLao_name(),
                staff.getPhone(),
                staff.getRole(),
                staff.getStatus(),
                staff.getStaffImage(),
                staff.getBorId(),
                borName,
                staff.getDept_id(),
                deptName,
                staff.getPos_id(),
                positionName,
                staff.getWork_place(),
                staff.getGender(),
                staff.getAddress(),
                staff.getBirth_date(),
                staff.getStartwork_date(),
                staff.getBaseSalary(),
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
//            log.setCheckTime(LocalDateTime.now());
            log.setCheckTime(LocalDateTime.now().minusMinutes(2));
            log.setIpAddress(dto.getIpAddress());
            log.setMacAddress(dto.getMacAddress());
            message = "Check-in ສຳເລັດ";
            notimessage = now.isAfter(LocalTime.of(8, 1)) ? "LATE" : "ON-TIME";

        } else if (existingCheckOut.isEmpty()) {
            log = new AttendanceLog();
            log.setStaff(staff);
            log.setCheckType("CHECK_OUT");
            log.setCheckTime(LocalDateTime.now());
            log.setIpAddress(dto.getIpAddress());
            log.setMacAddress(dto.getMacAddress());
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
    public DataResponse getAttendance(AttendanceRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา requester จาก token
            StaffEntity requester = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token Not found"));

            // Step 2: เช็ค token หมดอายุ
            if (requester.getTokenExpiredAt() != null
                    && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token is Expired, Please Login again");
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
                        .findAllByCheckTimeBetweenOrderByCheckTimeAsc(
                                startDateTime, endDateTime);
            } else {
                StaffEntity staff = userRepository.findByStaffCode(dto.getStaffCode())
                        .orElseThrow(() -> new RuntimeException(
                                "Not found Staff Code: " + dto.getStaffCode()));
                logs = attendanceLogRepository
                        .findByStaff_IdAndCheckTimeBetweenOrderByCheckTimeAsc(
                                staff.getId(), startDateTime, endDateTime);
            }

            // ✅ Step 6: โหลด borName ทีเดียว
            Set<Integer> borIds = logs.stream()
                    .map(log -> log.getStaff().getBorId())
                    .filter(id -> id != null)
                    .collect(Collectors.toSet());

            Map<Integer, String> borNameMap = new HashMap<>();
            if (!borIds.isEmpty()) {
                tbBorRepository.findAllById(borIds)
                        .forEach(bor -> borNameMap.put(bor.getKeyId(), bor.getBName()));
            }

            // ✅ Step 7: โหลด department ทีเดียว
            Set<Long> deptIds = logs.stream()
                    .map(log -> log.getStaff().getDept_id())
                    .filter(id -> id != null)
                    .collect(Collectors.toSet());

            Map<Long, String> deptNameMap = new HashMap<>();
            if (!deptIds.isEmpty()) {
                departmentRepository.findAllById(deptIds)
                        .forEach(dept -> deptNameMap.put(dept.getId(), dept.getDeptName()));
            }

            // ✅ Step 8: โหลด position ทีเดียว
            Set<Long> posIds = logs.stream()
                    .map(log -> log.getStaff().getPos_id())
                    .filter(id -> id != null)
                    .collect(Collectors.toSet());

            Map<Long, String> posNameMap = new HashMap<>();
            if (!posIds.isEmpty()) {
                positionRepository.findAllById(posIds)
                        .forEach(pos -> posNameMap.put(pos.getId(), pos.getPosName()));
            }

            // ✅ Step 9: จัดกลุ่ม logs ตาม staffId + วันที่
            Map<String, Map<String, Object>> dayMap = new LinkedHashMap<>();

            for (AttendanceLog log : logs) {
                String key = log.getStaff().getId()
                        + "_" + log.getCheckTime().toLocalDate();

                dayMap.putIfAbsent(key, createDayEntry(log));

                Map<String, Object> day = dayMap.get(key);

                if (log.getCheckType().equals("CHECK_IN")) {
                    day.put("checkIn",       log.getCheckTime().toString());
                    day.put("checkInStatus", calculateCheckInStatus(log.getCheckTime()));
                    day.put("ipAddress",     log.getIpAddress());
                    day.put("macAddress",    log.getMacAddress());
                } else {
                    day.put("checkOut",       log.getCheckTime().toString());
                    day.put("checkOutStatus", calculateCheckOutStatus(log.getCheckTime()));
                }
            }

            // ✅ Step 10: จัดกลุ่มตาม Staff
            Map<Long, Map<String, Object>> staffMap = new LinkedHashMap<>();

            for (AttendanceLog log : logs) {
                Long staffId = log.getStaff().getId();

                if (!staffMap.containsKey(staffId)) {
                    Map<String, Object> staffItem = new LinkedHashMap<>();
                    staffItem.put("staffId",    log.getStaff().getId());
                    staffItem.put("staffCode",  log.getStaff().getStaffCode());
                    staffItem.put("username",   log.getStaff().getUsername());
                    staffItem.put("laoname",   log.getStaff().getLao_name());
                    staffItem.put("staffImage", log.getStaff().getStaffImage());
                    staffItem.put("borId",      log.getStaff().getBorId());
                    staffItem.put("borName",    borNameMap.get(log.getStaff().getBorId()));
                    staffItem.put("deptId",     log.getStaff().getDept_id());
                    staffItem.put("department", deptNameMap.get(log.getStaff().getDept_id()));
                    staffItem.put("posId",      log.getStaff().getPos_id());
                    staffItem.put("position",   posNameMap.get(log.getStaff().getPos_id()));
                    staffItem.put("attendanLog", new ArrayList<>());
                    staffMap.put(staffId, staffItem);
                }
            }

            // ✅ Step 11: ใส่ day entries เข้าไปใน staffMap
            for (Map.Entry<String, Map<String, Object>> entry : dayMap.entrySet()) {
                Long staffId = Long.parseLong(entry.getKey().split("_")[0]);
                Map<String, Object> staffItem = staffMap.get(staffId);
                if (staffItem != null) {
                    ((List<Map<String, Object>>) staffItem.get("attendanLog"))
                            .add(entry.getValue());
                }
            }

            List<Map<String, Object>> result = new ArrayList<>(staffMap.values());

            response.setStatus("00");
            response.setMessage("Success Fetching Data");
            response.setDataResponse(result);
            response.setSumFooter(result.size());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    // Helper method สร้าง day entry
    private Map<String, Object> createDayEntry(AttendanceLog log) {
        Map<String, Object> day = new LinkedHashMap<>();
        day.put("id",             log.getId());
        day.put("date",           log.getCheckTime().toLocalDate().toString());
        day.put("checkIn",        "00");
        day.put("checkInStatus",  "00");
        day.put("checkOut",       "00");
        day.put("checkOutStatus", "00");
        day.put("ipAddress",      null);
        day.put("macAddress",     null);
        return day;
    }

    //  คำนวณ CHECK_IN status
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
        if (!admin.getRole().equals("ADMIN") ||(!admin.getRole().equals("HR")) ||(!admin.getRole().equals("BORLEADER"))) {
            throw new RuntimeException("ONLY ADMIN OR HR OR BORLEADER CAN RESET PASSWORD");
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

    //service request day off
    public DataResponse requestLeave(LeaveRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา staff จาก token
            StaffEntity staff = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token nout found"));

            // Step 2: เช็ค token หมดอายุ
            if (staff.getTokenExpiredAt() != null
                    && staff.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token is expired please Login again");
            }

            // Step 3: แปลงวันที่
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dto.getStartDate(), formatter);
            LocalDate endDate   = LocalDate.parse(dto.getEndDate(), formatter);

            // Step 4: เช็ควันที่
            if (startDate.isAfter(endDate)) {
                throw new RuntimeException("ວັນທີ່ເລີ່ມຕົ້ນຕ້ອງບໍ່ເກີນວັນທີ່ສີ້ນສຸດຂອງການລາ");
            }

            // Step 5: คำนวณ totalDays ตาม work schedule
            // Step 5: คำนวณ totalDays
            double totalDays;

            if (dto.getHalfDay() != null && !dto.getHalfDay().isEmpty()) {
                //  ลาครึ่งวัน
                // startDate และ endDate ต้องเป็นวันเดียวกัน
                if (!startDate.equals(endDate)) {
                    throw new RuntimeException(
                            "ການລາເຄີ່ງວັນ startDate ແລະ endDate ຕ້ອງເປັນວັນດຽວກັນ");
                }
                // เช็คว่าวันนั้นเป็นวันทำงานไหม
                boolean isWorkDay = isWorkDay(staff, startDate,
                        staff.getWorkSchedule() != null
                                ? staff.getWorkSchedule() : "MON_FRI");

                if (!isWorkDay) {
                    throw new RuntimeException("ຊ່ວງທີ່ເລືອກແມ່ນວັນພັກທັງໝົດ");
                }

                totalDays = 0.5;

            } else {
                // ลาเต็มวัน
                totalDays = calculateWorkDays(staff, startDate, endDate);

                if (totalDays == 0) {
                    throw new RuntimeException("ຊ່ວງທີ່ເລືອກແມ່ນວັນຢຸດທັງໝົດ");
                }
            }

            // Step 6: เช็คซ้อนทับ ทั้ง PENDING และ APPROVED
            List<LeaveRequest> overlapping = leaveRequestRepository
                    .findByStaff_IdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                            staff.getId(), "APPROVED", endDate, startDate);

            List<LeaveRequest> overlappingPending = leaveRequestRepository
                    .findByStaff_IdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                            staff.getId(), "PENDING", endDate, startDate);

// ✅ รวม overlapping ทั้งหมด
            List<LeaveRequest> allOverlapping = new ArrayList<>();
            allOverlapping.addAll(overlapping);
            allOverlapping.addAll(overlappingPending);

            if (!allOverlapping.isEmpty()) {
                for (LeaveRequest existing : allOverlapping) {

                    String existingHalfDay = existing.getHalfDay();
                    String newHalfDay      = dto.getHalfDay();

                    //  ถ้าเป็นครึ่งวันทั้งคู่ เช็คว่าซ้ำช่วงเดียวกันไหม
                    boolean bothHalfDay = (existingHalfDay != null && !existingHalfDay.isEmpty())
                            && (newHalfDay      != null && !newHalfDay.isEmpty());

                    if (bothHalfDay) {
                        // MORNING + MORNING = No
                        // AFTERNOON + AFTERNOON = No
                        // MORNING + AFTERNOON = ไม่ซ้ำ
                        if (existingHalfDay.equals(newHalfDay)) {
                            throw new RuntimeException(
                                    "ຊ່ວງວັນທີຂໍລາຊ້ຳກັບຄຳຂໍທີ່ມີຢູ່ແລ້ວ (" + newHalfDay + ")");
                        }
                        // ต่างช่วง → ผ่านได้ครับ

                    } else if (bothHalfDay == false
                            && existingHalfDay == null
                            && newHalfDay == null) {
                        // เต็มวัน + เต็มวัน =No
                        throw new RuntimeException(
                                "ຊ່ວງວັນທີຂໍລາຊ້ຳກັບວັນລາທີ່ມີຢູ່ແລ້ວ");

                    } else {
                        // เต็มวัน + ครึ่งวัน =No
                        // ครึ่งวัน + เต็มวัน = No
                        throw new RuntimeException(
                                "ຊ່ວງວັນທີຂໍລາຊ້ຳກັບວັນລາທີ່ມີຢູ່ແລ້ວ");
                    }
                }
            }

            // Step 7: เช็คโควต้า + กฎแต่ละประเภท
            validateLeaveRequest(staff, dto.getLeaveType(), totalDays, startDate);

            double quota     = getLeaveQuota(dto.getLeaveType());
            double used      = getLeaveUsed(staff.getId(), dto.getLeaveType(), startDate.getYear());
            double remaining = quota - used;

            if (!dto.getLeaveType().equals("UNPAID")
                    && !dto.getLeaveType().equals("MATERNITY")
                    && totalDays > remaining) {
                throw new RuntimeException(
                        "ວັນລາບໍ່ພໍ ຄົງເຫຼືອ " + remaining + " ວັນ ແຕ່ຂໍ " + totalDays + " ວັນ");
            }
            // Step 8: บันทึก
            LeaveRequest leave = new LeaveRequest();
            leave.setStaff(staff);
            leave.setLeaveType(dto.getLeaveType());
            leave.setStartDate(startDate);
            leave.setEndDate(endDate);
            leave.setTotalDays(totalDays);
            leave.setHalfDay(dto.getHalfDay());
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
            data.put("totalDays",     saved.getTotalDays());  // 0.5 หรือ 1, 2, 3...
            data.put("status",        saved.getStatus());     // MORNING, AFTERNOON, null
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
    private double  calculateWorkDays(StaffEntity staff,
                                  LocalDate startDate, LocalDate endDate) {

        String schedule = staff.getWorkSchedule() != null
                ? staff.getWorkSchedule() : "MON_FRI";

        double  workDays = 0;
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
    private void validateLeaveRequest(StaffEntity staff,
                                      String leaveType,
                                      double totalDays,
                                      LocalDate startDate) {
        switch (leaveType) {

            case "ANNUAL":
                // ครั้งละไม่เกิน 5 วัน
                if (totalDays > 5) {
                    throw new RuntimeException(
                            "ລາພັກປະຈຳປີຄັ້ງລະໄດ້ບໍ່ເກີນ 5 ວັນ ແຕ່ຂໍ " + totalDays + " ວັນ");
                }
                break;

            case "CASUAL":
                // ครั้งละไม่เกิน 3 วัน
                if (totalDays > 3) {
                    throw new RuntimeException(
                            "ລາກິດຄັ້ງລະໄດ້ບໍ່ເກີນ 3 ວັນ ແຕ່ຂໍ " + totalDays + " ວັນ");
                }
                // ไม่เกิน 3 ครั้ง/ปี
                int casualCount = getLeaveCount(staff.getId(), "CASUAL", startDate.getYear());
                if (casualCount >= 3) {
                    throw new RuntimeException(
                            "ລາກິດໄດ້ສູງສຸດ 3 ຄັ້ງ/ປີ ໃຊ້ໄປແລ້ວ " + casualCount + " ຄັ້ງ");
                }
                break;

            case "MATERNITY":
                // ครั้งละไม่เกิน 105 วัน
                if (totalDays > 105) {
                    throw new RuntimeException(
                            "ລາເກີດລູກຄັ້ງລະໄດ້ບໍ່ເກີນ 105 ວັນ ແຕ່ຂໍ " + totalDays + " ວັນ");
                }
                // ไม่เกิน 1 ครั้ง/ปี
                int maternityCount = getLeaveCount(staff.getId(), "MATERNITY", startDate.getYear());
                if (maternityCount >= 1) {
                    throw new RuntimeException(
                            "ລາເກີດລູກໄດ້ສູງສຸດ 1 ຄັ້ງ/ປີ");
                }
                break;

            case "MISCARRIAGE":
                // ครั้งละไม่เกิน 30 วัน
                if (totalDays > 30) {
                    throw new RuntimeException(
                            "ລາຫຼຸລູກຄັ້ງລະໄດ້ບໍ່ເກີນ 30 ວັນ ແຕ່ຂໍ " + totalDays + " ວັນ");
                }
                // ไม่เกิน 3 ครั้ง/ปี
                int miscarriageCount = getLeaveCount(staff.getId(), "MISCARRIAGE", startDate.getYear());
                if (miscarriageCount >= 3) {
                    throw new RuntimeException(
                            "ລາຫຼຸລູກໄດ້ສູງສຸດ 3 ຄັ້ງ/ປີ");
                }
                break;

            case "PERSONAL":
                // ครั้งละไม่เกิน 5 วัน
                if (totalDays > 5) {
                    throw new RuntimeException(
                            "ລາສ່ວນຕົວຄັ້ງລະໄດ້ບໍ່ເກີນ 5 ວັນ ແຕ່ຂໍ " + totalDays + " ວັນ");
                }
                // ไม่เกิน 10 ครั้ง/ปี
//                int personalCount = getLeaveCount(staff.getId(), "PERSONAL", startDate.getYear());
//                if (personalCount >= 10) {
//                    throw new RuntimeException(
//                            "ລາສ່ວນຕົວໄດ້ສູງສຸດ 10 ຄັ້ງ/ປີ");
//                }
                break;

            case "SICK":
            case "ACCIDENT":
            case "UNPAID":
                // ไม่มีกฎครั้งละ
                break;

            default:
                throw new RuntimeException("ປະເພດການລາບໍ່ຖືກຕ້ອງ: " + leaveType);
        }
    }
    // นับจำนวนครั้งที่ลาในปีนั้น (สำหรับ CASUAL)
    private int getLeaveCount(Long staffId, String leaveType, int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear   = LocalDate.of(year, 12, 31);

        List<LeaveRequest> leaves = leaveRequestRepository
                .findByStaff_IdAndLeaveTypeAndStatusAndStartDateBetween(
                        staffId, leaveType, "APPROVED", startOfYear, endOfYear);

        return leaves.size();  // นับจำนวนครั้ง
    }

    private double getLeaveQuota(String leaveType) {
        switch (leaveType) {
            case "SICK":      return 15.0;
            case "ANNUAL":    return 15.0;
            case "CASUAL":    return 9.0;
            case "ACCIDENT":  return 30.0;
            case "MATERNITY": return 999.0;
            case "MISCARRIAGE": return 30.0;
            case "PERSONAL":  return 5.0;
            case "UNPAID":    return 999.0;
            default: throw new RuntimeException("ປະເພດການລາບໍ່ຖືກຕ້ອງ: " + leaveType);
        }
    }

    private double getLeaveUsed(Long staffId, String leaveType, int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear   = LocalDate.of(year, 12, 31);

        List<LeaveRequest> used = leaveRequestRepository
                .findByStaff_IdAndLeaveTypeAndStatusAndStartDateBetween(
                        staffId, leaveType, "APPROVED", startOfYear, endOfYear);

        //  sum เป็น double รองรับ 0.5
        return used.stream()
                .mapToDouble(LeaveRequest::getTotalDays)
                .sum();
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
                        requester.getId(),  // lock staffId เป็นตัวเอง
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
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("leaveId",   leave.getId());
                item.put("staffId",   leave.getStaff().getId());
                item.put("staffCode", leave.getStaff().getStaffCode());
                item.put("username",  leave.getStaff().getUsername());
                item.put("staffImage", leave.getStaff().getStaffImage());
                item.put("borId",     leave.getStaff().getBorId());
                item.put("leaveType", leave.getLeaveType());
                item.put("halfDay",    leave.getHalfDay());
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

    //approve leaveRequest
    public DataResponse approveLeave(LeaveApproveRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา HR จาก token
            StaffEntity hr = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            // Step 2: เช็ค token หมดอายุ
            if (hr.getTokenExpiredAt() != null
                    && hr.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุ กรุณา Login ใหม่");
            }

            // Step 3: เช็คว่าเป็น HR หรือ ADMIN เท่านั้น
            if (!hr.getRole().equals("HR") && !hr.getRole().equals("ADMIN")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMIN ເທົ່ານັ້ນ");
            }

            // Step 4: เช็ค status ที่ส่งมาถูกต้องไหม
            if (!dto.getStatus().equals("APPROVED")
                    && !dto.getStatus().equals("REJECTED")) {
                throw new RuntimeException("status ຕ້ອງເປັນ APPROVED ຫຼື REJECTED ເທົ່ານັ້ນ");
            }

            // Step 5: หา leave request
            LeaveRequest leave = leaveRequestRepository.findById(dto.getLeaveId())
                    .orElseThrow(() -> new RuntimeException("ບໍ່ພົບ Leave Request id: " + dto.getLeaveId()));

            // Step 6: เช็คว่ายัง PENDING อยู่ไหม
            if (!leave.getStatus().equals("PENDING")) {
                throw new RuntimeException("Leave Request ນີ້ຖືກດຳເນີນການໄປແລ້ວ status: " + leave.getStatus());
            }

            // Step 7: อัปเดต status
            leave.setStatus(dto.getStatus());
            leave.setApprovedBy(hr);

            // ถ้า REJECTED ให้เก็บเหตุผลด้วย
            if (dto.getStatus().equals("REJECTED") && dto.getReason() != null) {
                leave.setReason(dto.getReason());
            }

            LeaveRequest saved = leaveRequestRepository.save(leave);

            // Step 8: สร้าง response data
            Map<String, Object> data = new HashMap<>();
            data.put("leaveId",      saved.getId());
            data.put("staffId",      saved.getStaff().getId());
            data.put("staffCode",    saved.getStaff().getStaffCode());
            data.put("username",     saved.getStaff().getUsername());
            data.put("staffImage",   saved.getStaff().getStaffImage());
            data.put("leaveType",    saved.getLeaveType());
            data.put("startDate",    saved.getStartDate().toString());
            data.put("endDate",      saved.getEndDate().toString());
//            data.put("totalDays",    saved.getTotalDays());
            data.put("status",       saved.getStatus());
            data.put("reason",       saved.getReason());
            data.put("approvedBy",   hr.getUsername());
            data.put("updatedAt",    saved.getUpdatedAt());

            response.setStatus("00");
            response.setMessage(dto.getStatus().equals("APPROVED")
                    ? "ອະນຸມັດວັນລາພັກສຳເລັດ"
                    : "ປະຕິເສດວັນລາພັກສຳເລັດ");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    //getDailyAttendance
    public DataResponse getDailyAttendance(DailyAttendanceRequestDTO dto) {

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

            // Step 3: กำหนดวันที่
            LocalDate targetDate = (dto.getDate() != null && !dto.getDate().isEmpty())
                    ? LocalDate.parse(dto.getDate(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : LocalDate.now();

            LocalDateTime startOfDay = targetDate.atStartOfDay();
            LocalDateTime endOfDay = targetDate.atTime(23, 59, 59);

            // Step 4: ดึง staff ตาม role + borId
            List<StaffEntity> staffList;

            if (requester.getRole().equals("USER")) {

                staffList = new ArrayList<>();
                staffList.add(requester);

            } else if (requester.getRole().equals("BORLEADER")) {

                if (dto.getBorId() == null || dto.getBorId().isEmpty()) {

                    staffList = new ArrayList<>();
                    staffList.add(requester);

                } else if (dto.getBorId().equalsIgnoreCase("all")) {

                    staffList = userRepository.findAllByBorIdAndStatus(
                            requester.getBorId(),
                            "ACTIVE"
                    );

                } else {

                    throw new RuntimeException("ບໍ່ມີສິດເບິ່ງຂໍ້ມູນ bor ອື່ນ");
                }

            } else {

                if (dto.getBorId() == null || dto.getBorId().isEmpty()) {

                    staffList = new ArrayList<>();
                    staffList.add(requester);

                } else if (dto.getBorId().equalsIgnoreCase("all")) {

                    staffList = userRepository.findAllByStatus("ACTIVE");

                } else {

                    staffList = userRepository.findAllByBorIdAndStatus(
                            Integer.parseInt(dto.getBorId()),
                            "ACTIVE"
                    );
                }
            }

            // Step 5: โหลด borName ทีเดียว
            Set<Integer> borIds = staffList.stream()
                    .map(StaffEntity::getBorId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            Map<Integer, String> borNameMap = new HashMap<>();

            if (!borIds.isEmpty()) {

                tbBorRepository.findAllById(borIds)
                        .forEach(bor ->
                                borNameMap.put(
                                        bor.getKeyId(),
                                        bor.getBName()
                                )
                        );
            }

            // Step 6: โหลด department ทีเดียว
            Set<Long> deptIds = staffList.stream()
                    .map(StaffEntity::getDept_id)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            Map<Long, String> deptNameMap = new HashMap<>();

            if (!deptIds.isEmpty()) {

                departmentRepository.findAllById(deptIds)
                        .forEach(dept ->
                                deptNameMap.put(
                                        dept.getId(),
                                        dept.getDeptName()
                                )
                        );
            }

            // Step 7: โหลด position ทีเดียว
            Set<Long> posIds = staffList.stream()
                    .map(StaffEntity::getPos_id)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            Map<Long, String> posNameMap = new HashMap<>();

            if (!posIds.isEmpty()) {

                positionRepository.findAllById(posIds)
                        .forEach(pos ->
                                posNameMap.put(
                                        pos.getId(),
                                        pos.getPosName()
                                )
                        );
            }

            // Step 8: โหลด attendance logs ทีเดียว
            List<AttendanceLog> allLogs =
                    attendanceLogRepository
                            .findAllByCheckTimeBetweenOrderByCheckTimeAsc(
                                    startOfDay,
                                    endOfDay
                            );

            Map<Long, AttendanceLog> checkInMap = new HashMap<>();
            Map<Long, AttendanceLog> checkOutMap = new HashMap<>();

            for (AttendanceLog log : allLogs) {

                Long staffId = log.getStaff().getId();

                if ("CHECK_IN".equals(log.getCheckType())) {

                    checkInMap.putIfAbsent(staffId, log);

                } else if ("CHECK_OUT".equals(log.getCheckType())) {

                    checkOutMap.put(staffId, log);
                }
            }

            // Step 9: โหลด leave requests ทีเดียว
            List<Long> staffIds = staffList.stream()
                    .map(StaffEntity::getId)
                    .collect(Collectors.toList());

            Map<Long, List<LeaveRequest>> leaveMap = new HashMap<>();

            if (!staffIds.isEmpty()) {

                leaveRequestRepository
                        .findByStaffIdsAndDate(staffIds, targetDate)
                        .forEach(leave -> {

                            leaveMap.computeIfAbsent(
                                    leave.getStaff().getId(),
                                    k -> new ArrayList<>()
                            ).add(leave);

                        });
            }

            // Step 10: Loop staff
            List<Map<String, Object>> data = new ArrayList<>();

            for (StaffEntity staff : staffList) {

                //  create item first
                Map<String, Object> item = new LinkedHashMap<>();

                AttendanceLog checkIn = checkInMap.get(staff.getId());
                AttendanceLog checkOut = checkOutMap.get(staff.getId());

                List<LeaveRequest> leaves = leaveMap.getOrDefault(
                        staff.getId(),
                        new ArrayList<>()
                );

                String status;
                LocalDateTime checkInTime = null;
                LocalDateTime checkOutTime = null;

                String ipAddress = null;
                String macAddress = null;

                // =========================
                // CHECK ATTENDANCE STATUS
                // =========================
                if (checkIn != null) {

                    checkInTime = checkIn.getCheckTime();
                    ipAddress = checkIn.getIpAddress();
                    macAddress = checkIn.getMacAddress();

                    status = checkInTime.toLocalTime()
                            .isAfter(LocalTime.of(8, 1))
                            ? "LATE"
                            : "INTIME";

                    if (checkOut != null) {

                        checkOutTime = checkOut.getCheckTime();
                    }

                } else if (!leaves.isEmpty()) {

                    boolean allApproved = leaves.stream()
                            .allMatch(l ->
                                    "APPROVED".equals(l.getStatus()));

                    boolean anyPending = leaves.stream()
                            .anyMatch(l ->
                                    "PENDING".equals(l.getStatus()));

                    if (allApproved) {

                        status = "ON_LEAVE";

                    } else if (anyPending) {

                        status = "PENDING_LEAVE";

                    } else {

                        status = "ABSENT";
                    }

                } else {

                    status = "ABSENT";
                }
                // STAFF INFO
                item.put("staffId", staff.getId());
                item.put("staffCode", staff.getStaffCode());
                item.put("username", staff.getUsername());
                item.put("laoName", staff.getLao_name());
                item.put("staffImage", staff.getStaffImage());
                item.put("borId", staff.getBorId());
                item.put("borName",
                        borNameMap.get(staff.getBorId()));
                item.put("deptId", staff.getDept_id());
                item.put("department",
                        deptNameMap.get(staff.getDept_id()));
                item.put("posId", staff.getPos_id());
                item.put("position",
                        posNameMap.get(staff.getPos_id()));
                item.put("role", staff.getRole());
                item.put("checkIn",
                        checkInTime != null
                                ? checkInTime.toString()
                                : "00");
                item.put("checkOut",
                        checkOutTime != null
                                ? checkOutTime.toString()
                                : "00");
                item.put("status", status);
                item.put("ipAddress", ipAddress);
                item.put("macAddress", macAddress);
                // LEAVE INFO
                if (!leaves.isEmpty()) {

                    double totalLeaveDays = leaves.stream()
                            .mapToDouble(LeaveRequest::getTotalDays)
                            .sum();
                    boolean allApproved = leaves.stream()
                            .allMatch(l ->
                                    "APPROVED".equals(l.getStatus()));
                    item.put("leaveType",
                            leaves.get(0).getLeaveType());
                    item.put("leaveStatus",
                            allApproved
                                    ? "APPROVED"
                                    : "PENDING");
                    item.put("leaveStart",
                            leaves.get(0)
                                    .getStartDate()
                                    .toString());
                    item.put("leaveEnd",
                            leaves.get(0)
                                    .getEndDate()
                                    .toString());
                    item.put("totalDays", totalLeaveDays);
                    item.put("halfDay",
                            leaves.size() > 1
                                    ? "FULL_DAY"
                                    : leaves.get(0).getHalfDay());
                } else {
                    item.put("leaveType", null);
                    item.put("leaveStatus", null);
                    item.put("leaveStart", null);
                    item.put("leaveEnd", null);
                    item.put("totalDays", null);
                    item.put("halfDay", null);
                }
                data.add(item);
            }
            // Step 11: Footer summary
            long intime = data.stream()
                    .filter(d ->
                            "INTIME".equals(d.get("status")))
                    .count();
            long late = data.stream()
                    .filter(d ->
                            "LATE".equals(d.get("status")))
                    .count();
            long absent = data.stream()
                    .filter(d ->
                            "ABSENT".equals(d.get("status")))
                    .count();
            long onLeave = data.stream()
                    .filter(d ->
                            "ON_LEAVE".equals(d.get("status")))
                    .count();
            long pendingLeave = data.stream()
                    .filter(d ->
                            "PENDING_LEAVE".equals(d.get("status")))
                    .count();
            Map<String, Object> footer =
                    new LinkedHashMap<>();
            footer.put("TOTAL", data.size());
            footer.put("INTIME", intime);
            footer.put("LATE", late);
            footer.put("ABSENT", absent);
            footer.put("ON_LEAVE", onLeave);
            footer.put("PENDING_LEAVE", pendingLeave);
            response.setStatus("00");
            response.setMessage("success");
            response.setDataResponse(data);
            response.setSumFooter(footer);

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }
}
