package com.ldb.truck.Service.staft;

import com.ldb.truck.Entity.Staff.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Staffs.AdjustmentApproveDTO;
import com.ldb.truck.Model.Staffs.AdjustmentRequestDTO;
import com.ldb.truck.Repository.Staffs.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;
    private final AttendanceLogRepository attendanceLogRepository;
    private final AttendanceAdjustmentRepository adjustmentRepository;
    private final WorkShiftRepository  workShiftRepository;
    private final StaffShiftRepository staffShiftRepository;

    //  Create Department
    public DataResponse createDepartment(Map<String, Object> body) {
        DataResponse response = new DataResponse();
        try {
            String token    = (String) body.get("token");
            String deptName = (String) body.get("deptName");
            String description = (String) body.get("description");

            // เช็ค HR/ADMIN
            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));
            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")
                    && !requester.getRole().equals("BORLEADER")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMIN, BORLEADER ເທົ່ານັ້ນ");
            }

            // เช็คซ้ำ
            if (departmentRepository.existsByDeptName(deptName)) {
                throw new RuntimeException("Department ນີ້ມີຢູ່ແລ້ວ: " + deptName);
            }

            Department dept = new Department();
            dept.setDeptName(deptName);
            dept.setDescription(description);
            Department saved = departmentRepository.save(dept);

            response.setStatus("00");
            response.setMessage("ສ້າງ Department ສຳເລັດ");
            response.setDataResponse(saved);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    //  Get Department
    public DataResponse getDepartment(Map<String, Object> body) {
        DataResponse response = new DataResponse();
        try {
            String token = (String) body.get("token");

            userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            List<Department> depts = departmentRepository.findAllByStatus("ACTIVE");

            // ดึง position ของแต่ละ department ด้วย
            List<Map<String, Object>> data = depts.stream().map(dept -> {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("id",          dept.getId());
                item.put("deptName",    dept.getDeptName());
                item.put("description", dept.getDescription());
                item.put("status",      dept.getStatus());
//                item.put("positions",   positionRepository
//                        .findAllByDeptIdAndStatus(dept.getId(), "ACTIVE"));
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

    //  Update Department
    public DataResponse updateDepartment(Map<String, Object> body) {
        DataResponse response = new DataResponse();
        try {
            String token       = (String) body.get("token");
            Long deptId        = Long.parseLong(body.get("deptId").toString());
            String deptName    = (String) body.get("deptName");
            String description = (String) body.get("description");
            String status      = (String) body.get("status");

            // เช็ค HR/ADMIN
            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));
            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")
                    && !requester.getRole().equals("BORLEADER")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMINມ BORLEADER ເທົ່ານັ້ນ");
            }

            Department dept = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("ບໍ່ພົບ Department id: " + deptId));

            if (deptName != null)    dept.setDeptName(deptName);
            if (description != null) dept.setDescription(description);
            if (status != null)      dept.setStatus(status);

            Department saved = departmentRepository.save(dept);

            response.setStatus("00");
            response.setMessage("ອັບເດດ Department ສຳເລັດ");
            response.setDataResponse(saved);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    //  Create Position
    public DataResponse createPosition(Map<String, Object> body) {
        DataResponse response = new DataResponse();
        try {
            String token       = (String) body.get("token");
            Long deptId        = Long.parseLong(body.get("deptId").toString());
            String posName     = (String) body.get("posName");
            String description = (String) body.get("description");

            // เช็ค HR/ADMIN
            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));
            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")
                    && !requester.getRole().equals("BORLEADER")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMINມ BORLEADER ເທົ່ານັ້ນ");
            }

            // เช็คว่า department มีอยู่ไหม
            departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("ບໍ່ພົບ Department id: " + deptId));

            // เช็คซ้ำใน department เดียวกัน
            if (positionRepository.existsByPosNameAndDeptId(posName, deptId)) {
                throw new RuntimeException("This position already exist in the Department");
            }

            Position pos = new Position();
            pos.setDeptId(deptId);
            pos.setPosName(posName);
            pos.setDescription(description);
            Position saved = positionRepository.save(pos);

            response.setStatus("00");
            response.setMessage("ສ້າງ Position ສຳເລັດ");
            response.setDataResponse(saved);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    //  Update Position
    public DataResponse updatePosition(Map<String, Object> body) {
        DataResponse response = new DataResponse();
        try {
            String token       = (String) body.get("token");
            Long posId         = Long.parseLong(body.get("posId").toString());
            String posName     = (String) body.get("posName");
            String description = (String) body.get("description");
            String status      = (String) body.get("status");

            // เช็ค HR/ADMIN
            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));
            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")
                    && !requester.getRole().equals("BORLEADER")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMIN, BORLEADER ເທົ່ານັ້ນ");
            }

            Position pos = positionRepository.findById(posId)
                    .orElseThrow(() -> new RuntimeException("ບໍ່ພົບ Position id: " + posId));

            if (posName != null)     pos.setPosName(posName);
            if (description != null) pos.setDescription(description);
            if (status != null)      pos.setStatus(status);

            Position saved = positionRepository.save(pos);

            response.setStatus("00");
            response.setMessage("ອັບເດດ Position ສຳເລັດ");
            response.setDataResponse(saved);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }
    //  Get Position — ดูทั้งหมด หรือตาม deptId
    public DataResponse getPosition(Map<String, Object> body) {
        DataResponse response = new DataResponse();
        try {
            String token = (String) body.get("token");

            userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            List<Position> positions;

            // ถ้าส่ง deptId มา → ดูเฉพาะ department นั้น
            // ถ้าไม่ส่ง → ดูทั้งหมด
            if (body.get("deptId") != null) {
                Long deptId = Long.parseLong(body.get("deptId").toString());
                positions = positionRepository.findAllByDeptIdAndStatus(deptId, "ACTIVE");
            } else {
                positions = positionRepository.findAllByStatus("ACTIVE");
            }

            response.setStatus("00");
            response.setMessage("success");
            response.setDataResponse(positions);
            response.setSumFooter(positions.size());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }


    // ✅ Staff ขอแก้ไขเวลา
    public DataResponse requestAdjustment(AdjustmentRequestDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา staff จาก token
            StaffEntity staff = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            if (staff.getTokenExpiredAt() != null
                    && staff.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุ กรุณา Login ใหม่");
            }

            // Step 2: เช็คว่าส่งอย่างน้อย 1 อย่าง
            if (dto.getCheckInTime() == null && dto.getCheckOutTime() == null) {
                throw new RuntimeException("ກະລຸນາລະບຸ checkInTime ຫຼື checkOutTime");
            }

            DateTimeFormatter dateFormatter     = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            LocalDate requestDate = LocalDate.parse(dto.getRequestDate(), dateFormatter);

            // Step 3: แปลงเวลา
            LocalDateTime checkInTime  = dto.getCheckInTime()  != null
                    ? LocalDateTime.parse(dto.getCheckInTime(),  dateTimeFormatter) : null;
            LocalDateTime checkOutTime = dto.getCheckOutTime() != null
                    ? LocalDateTime.parse(dto.getCheckOutTime(), dateTimeFormatter) : null;

            // Step 4: บันทึก request
            AttendanceAdjustment adjustment = new AttendanceAdjustment();
            adjustment.setStaff(staff);
            adjustment.setRequestDate(requestDate);
            adjustment.setCheckInTime(checkInTime);
            adjustment.setCheckOutTime(checkOutTime);
            adjustment.setReason(dto.getReason());
            adjustment.setStatus("PENDING");

            AttendanceAdjustment saved = adjustmentRepository.save(adjustment);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("adjustmentId",  saved.getId());
            data.put("staffCode",     staff.getStaffCode());
            data.put("username",      staff.getUsername());
            data.put("requestDate",   saved.getRequestDate().toString());
            data.put("checkInTime",   saved.getCheckInTime()  != null ? saved.getCheckInTime().toString()  : null);
            data.put("checkOutTime",  saved.getCheckOutTime() != null ? saved.getCheckOutTime().toString() : null);
            data.put("reason",        saved.getReason());
            data.put("status",        saved.getStatus());
            data.put("createdAt",     saved.getCreatedAt());

            response.setStatus("00");
            response.setMessage("ສົ່ງຄຳຂໍແກ້ໄຂເວລາສຳເລັດ ລໍຖ້າການອະນຸມັດ");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    // HR อนุมัติ/ปฏิเสธ
    public DataResponse approveAdjustment(AdjustmentApproveDTO dto) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: หา HR จาก token
            StaffEntity hr = userRepository.findByToken(dto.getToken())
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            if (hr.getTokenExpiredAt() != null
                    && hr.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token หมดอายุ กรุณา Login ใหม่");
            }

            // Step 2: เช็ค HR/ADMIN
            if (!hr.getRole().equals("HR") && !hr.getRole().equals("ADMIN")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMIN ເທົ່ານັ້ນ");
            }

            // Step 3: เช็ค status ที่ส่งมา
            if (!dto.getStatus().equals("APPROVED")
                    && !dto.getStatus().equals("REJECTED")) {
                throw new RuntimeException("status ຕ້ອງເປັນ APPROVED ຫຼື REJECTED");
            }

            // Step 4: หา adjustment request
            AttendanceAdjustment adjustment = adjustmentRepository.findById(dto.getAdjustmentId())
                    .orElseThrow(() -> new RuntimeException("ບໍ່ພົບ Adjustment id: " + dto.getAdjustmentId()));

            // Step 5: เช็คว่ายัง PENDING อยู่
            if (!adjustment.getStatus().equals("PENDING")) {
                throw new RuntimeException("Request ນີ້ຖືກດຳເນີນການໄປແລ້ວ status: " + adjustment.getStatus());
            }

            // Step 6: อัปเดต status
            adjustment.setStatus(dto.getStatus());
            adjustment.setApprovedBy(hr);
            if (dto.getStatus().equals("REJECTED") && dto.getReason() != null) {
                adjustment.setReason(dto.getReason());
            }
            adjustmentRepository.save(adjustment);

            // Step 7: ถ้า APPROVED → อัปเดต attendance_logs
            if (dto.getStatus().equals("APPROVED")) {

                LocalDate requestDate = adjustment.getRequestDate();
                LocalDateTime startOfDay = requestDate.atStartOfDay();
                LocalDateTime endOfDay   = requestDate.atTime(23, 59, 59);

                StaffEntity staff = adjustment.getStaff();

                // ── CHECK_IN ──────────────────────────────────
                if (adjustment.getCheckInTime() != null) {
                    Optional<AttendanceLog> existingCheckIn = attendanceLogRepository
                            .findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeAsc(
                                    staff.getId(), "CHECK_IN", startOfDay, endOfDay);

                    if (existingCheckIn.isPresent()) {
                        // มี record → UPDATE
                        AttendanceLog log = existingCheckIn.get();
                        log.setCheckTime(adjustment.getCheckInTime());
                        attendanceLogRepository.save(log);
                    } else {
                        // ไม่มี record → CREATE ใหม่
                        AttendanceLog newLog = new AttendanceLog();
                        newLog.setStaff(staff);
                        newLog.setCheckType("CHECK_IN");
                        newLog.setCheckTime(adjustment.getCheckInTime());
//                        newLog.setIpAddress(staff.getIpAddress());
                        newLog.setMacAddress(staff.getMacAddress());
                        newLog.setRemark("Adjusted by " + hr.getUsername());
                        attendanceLogRepository.save(newLog);
                    }
                }

                // ── CHECK_OUT ─────────────────────────────────
                if (adjustment.getCheckOutTime() != null) {
                    Optional<AttendanceLog> existingCheckOut = attendanceLogRepository
                            .findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeDesc(
                                    staff.getId(), "CHECK_OUT", startOfDay, endOfDay);

                    if (existingCheckOut.isPresent()) {
                        // มี record → UPDATE
                        AttendanceLog log = existingCheckOut.get();
                        log.setCheckTime(adjustment.getCheckOutTime());
                        attendanceLogRepository.save(log);
                    } else {
                        // ไม่มี record → CREATE ใหม่
                        AttendanceLog newLog = new AttendanceLog();
                        newLog.setStaff(staff);
                        newLog.setCheckType("CHECK_OUT");
                        newLog.setCheckTime(adjustment.getCheckOutTime());
//                        newLog.setIpAddress(staff.getIpAddress());
                        newLog.setMacAddress(staff.getMacAddress());
                        newLog.setRemark("Adjusted by " + hr.getUsername());
                        attendanceLogRepository.save(newLog);
                    }
                }
            }

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("adjustmentId",  adjustment.getId());
            data.put("staffCode",     adjustment.getStaff().getStaffCode());
            data.put("username",      adjustment.getStaff().getUsername());
            data.put("requestDate",   adjustment.getRequestDate().toString());
            data.put("checkInTime",   adjustment.getCheckInTime()  != null ? adjustment.getCheckInTime().toString()  : null);
            data.put("checkOutTime",  adjustment.getCheckOutTime() != null ? adjustment.getCheckOutTime().toString() : null);
            data.put("status",        adjustment.getStatus());
            data.put("approvedBy",    hr.getUsername());
            data.put("updatedAt",     adjustment.getUpdatedAt());

            response.setStatus("00");
            response.setMessage(dto.getStatus().equals("APPROVED")
                    ? "ອະນຸມັດການແກ້ໄຂເວລາສຳເລັດ"
                    : "ປະຕິເສດການແກ້ໄຂເວລາສຳເລັດ");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    //  ดูรายการ Adjustment Request
    public DataResponse getAdjustments(Map<String, Object> body) {

        DataResponse response = new DataResponse();

        try {
            String token  = (String) body.get("token");
            String status = (String) body.get("status");

            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            List<AttendanceAdjustment> adjustments;

            if (requester.getRole().equals("USER")) {
                // USER ดูแค่ของตัวเอง
                adjustments = adjustmentRepository
                        .findByStaff_IdOrderByCreatedAtDesc(requester.getId());
            } else if (requester.getRole().equals("BORLEADER")) {
                // BORLEADER ดูแค่ bor ตัวเอง
                adjustments = adjustmentRepository
                        .findByStaff_BorIdOrderByCreatedAtDesc(requester.getBorId());
            } else {
                // HR/ADMIN ดูทั้งหมด หรือกรองตาม status
                if (status != null && !status.isEmpty()) {
                    adjustments = adjustmentRepository
                            .findByStatusOrderByCreatedAtDesc(status);
                } else {
                    adjustments = adjustmentRepository.findAllByOrderByCreatedAtDesc();
                }
            }

            List<Map<String, Object>> data = adjustments.stream().map(adj -> {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("adjustmentId",  adj.getId());
                item.put("staffId",       adj.getStaff().getId());
                item.put("staffCode",     adj.getStaff().getStaffCode());
                item.put("username",      adj.getStaff().getUsername());
                item.put("staffImage",    adj.getStaff().getStaffImage());
                item.put("borId",         adj.getStaff().getBorId());
                item.put("requestDate",   adj.getRequestDate().toString());
                item.put("checkInTime",   adj.getCheckInTime()  != null ? adj.getCheckInTime().toString()  : null);
                item.put("checkOutTime",  adj.getCheckOutTime() != null ? adj.getCheckOutTime().toString() : null);
                item.put("reason",        adj.getReason());
                item.put("status",        adj.getStatus());
                item.put("approvedBy",    adj.getApprovedBy() != null ? adj.getApprovedBy().getUsername() : null);
                item.put("createdAt",     adj.getCreatedAt());
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

    // =====================================================
    // CREATE SHIFT
    // =====================================================
    public DataResponse createShift(Map<String, Object> body) {

        DataResponse response = new DataResponse();

        try {
            String token = (String) body.get("token");

            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMIN ເທົ່ານັ້ນ");
            }

            String shiftName = (String) body.get("shiftName");
            String shiftCode = (String) body.get("shiftCode");

            if (shiftName == null || shiftName.isEmpty()) {
                throw new RuntimeException("ກະລຸນາລະບຸ shiftName");
            }
            if (shiftCode == null || shiftCode.isEmpty()) {
                throw new RuntimeException("ກະລຸນາລະບຸ shiftCode");
            }

            // เช็คซ้ำ
            if (workShiftRepository.findByShiftCode(shiftCode).isPresent()) {
                throw new RuntimeException("shiftCode ນີ້ມີຢູ່ແລ້ວ: " + shiftCode);
            }

            DateTimeFormatter timeFormatter =
                    DateTimeFormatter.ofPattern("HH:mm");

            WorkShift shift = new WorkShift();
            shift.setShiftName(shiftName);
            shift.setShiftCode(shiftCode.toUpperCase());
            shift.setCheckInStart(LocalTime.parse(
                    (String) body.get("checkInStart"), timeFormatter));
            shift.setCheckInEnd(LocalTime.parse(
                    (String) body.get("checkInEnd"), timeFormatter));
            shift.setCheckOutStart(LocalTime.parse(
                    (String) body.get("checkOutStart"), timeFormatter));
            shift.setCheckOutEnd(LocalTime.parse(
                    (String) body.get("checkOutEnd"), timeFormatter));
            shift.setWorkStart(LocalTime.parse(
                    (String) body.get("workStart"), timeFormatter));
            shift.setWorkEnd(LocalTime.parse(
                    (String) body.get("workEnd"), timeFormatter));
            shift.setStatus("ACTIVE");

            WorkShift saved = workShiftRepository.save(shift);

            response.setStatus("00");
            response.setMessage("ສ້າງກະວຽກສຳເລັດ");
            response.setDataResponse(buildShiftMap(saved));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    // =====================================================
    // GET SHIFTS
    // =====================================================
    public DataResponse getShifts(Map<String, Object> body) {

        DataResponse response = new DataResponse();

        try {
            String token = (String) body.get("token");

            userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            List<WorkShift> shifts = workShiftRepository.findAllByStatus("ACTIVE");

            List<Map<String, Object>> data = shifts.stream()
                    .map(this::buildShiftMap)
                    .collect(Collectors.toList());

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

    // =====================================================
    // ASSIGN SHIFT TO STAFF
    // =====================================================
    public DataResponse assignShift(Map<String, Object> body) {

        DataResponse response = new DataResponse();

        try {
            String token = (String) body.get("token");

            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMIN ເທົ່ານັ້ນ");
            }

            Long staffId = Long.parseLong(body.get("staffId").toString());
            Long shiftId = Long.parseLong(body.get("shiftId").toString());
            String effectiveDateStr = (String) body.get("effectiveDate");

            StaffEntity staff = userRepository.findById(staffId)
                    .orElseThrow(() -> new RuntimeException(
                            "ບໍ່ພົບ Staff id: " + staffId));

            WorkShift shift = workShiftRepository.findById(shiftId)
                    .orElseThrow(() -> new RuntimeException(
                            "ບໍ່ພົບ Shift id: " + shiftId));

            LocalDate effectiveDate = LocalDate.parse(
                    effectiveDateStr,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // ✅ ปิดกะเดิมก่อน
            List<StaffShift> currentShifts = staffShiftRepository
                    .findCurrentShifts(staffId, effectiveDate);

            currentShifts.forEach(s -> {
                s.setEndDate(effectiveDate.minusDays(1));
                staffShiftRepository.save(s);
            });

            // ✅ สร้างกะใหม่
            StaffShift staffShift = new StaffShift();
            staffShift.setStaff(staff);
            staffShift.setShift(shift);
            staffShift.setEffectiveDate(effectiveDate);
            staffShift.setEndDate(null);  // null = ยังใช้อยู่
            staffShift.setCreatedBy(requester.getId());

            StaffShift saved = staffShiftRepository.save(staffShift);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("id",            saved.getId());
            data.put("staffId",       staff.getId());
            data.put("staffCode",     staff.getStaffCode());
            data.put("username",      staff.getUsername());
            data.put("shiftId",       shift.getId());
            data.put("shiftName",     shift.getShiftName());
            data.put("shiftCode",     shift.getShiftCode());
            data.put("workStart",     shift.getWorkStart().toString());
            data.put("workEnd",       shift.getWorkEnd().toString());
            data.put("effectiveDate", saved.getEffectiveDate().toString());

            response.setStatus("00");
            response.setMessage("ກຳນົດກະວຽກໃຫ້ Staff ສຳເລັດ");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    // =====================================================
    // GET STAFF SHIFT
    // =====================================================
    public DataResponse getStaffShift(Map<String, Object> body) {

        DataResponse response = new DataResponse();

        try {
            String token = (String) body.get("token");

            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            // กำหนดวันที่
            String dateStr = (String) body.get("date");
            LocalDate targetDate = (dateStr != null && !dateStr.isEmpty())
                    ? LocalDate.parse(dateStr,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : LocalDate.now();

            // ดึง staffId
            Long staffId;
            if (body.get("staffId") != null) {
                staffId = Long.parseLong(body.get("staffId").toString());
            } else {
                staffId = requester.getId();
            }

            // USER ดูได้แค่ตัวเอง
            if (requester.getRole().equals("USER")
                    && !requester.getId().equals(staffId)) {
                throw new RuntimeException("ບໍ່ມີສິດເບິ່ງຂໍ້ມູນຂອງຄົນອື່ນ");
            }

            StaffEntity staff = userRepository.findById(staffId)
                    .orElseThrow(() -> new RuntimeException(
                            "ບໍ່ພົບ Staff id: " + staffId));

            List<StaffShift> shifts = staffShiftRepository
                    .findCurrentShifts(staffId, targetDate);

            if (shifts.isEmpty()) {
                Map<String, Object> data = new LinkedHashMap<>();
                data.put("staffId",   staff.getId());
                data.put("staffCode", staff.getStaffCode());
                data.put("username",  staff.getUsername());
                data.put("date",      targetDate.toString());
                data.put("shift",     null);
                data.put("message",   "ບໍ່ມີກະວຽກທີ່ກຳນົດໄວ້");

                response.setStatus("00");
                response.setMessage("success");
                response.setDataResponse(data);
                return response;
            }

            WorkShift shift = shifts.get(0).getShift();

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("staffId",       staff.getId());
            data.put("staffCode",     staff.getStaffCode());
            data.put("username",      staff.getUsername());
            data.put("date",          targetDate.toString());
            data.put("shiftId",       shift.getId());
            data.put("shiftName",     shift.getShiftName());
            data.put("shiftCode",     shift.getShiftCode());
            data.put("checkInStart",  shift.getCheckInStart().toString());
            data.put("checkInEnd",    shift.getCheckInEnd().toString());
            data.put("checkOutStart", shift.getCheckOutStart().toString());
            data.put("checkOutEnd",   shift.getCheckOutEnd().toString());
            data.put("workStart",     shift.getWorkStart().toString());
            data.put("workEnd",       shift.getWorkEnd().toString());
            data.put("effectiveDate", shifts.get(0).getEffectiveDate().toString());

            response.setStatus("00");
            response.setMessage("success");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    // =====================================================
    // HELPER
    // =====================================================
    private Map<String, Object> buildShiftMap(WorkShift shift) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id",           shift.getId());
        map.put("shiftName",    shift.getShiftName());
        map.put("shiftCode",    shift.getShiftCode());
        map.put("checkInStart", shift.getCheckInStart().toString());
        map.put("checkInEnd",   shift.getCheckInEnd().toString());
        map.put("checkOutStart",shift.getCheckOutStart().toString());
        map.put("checkOutEnd",  shift.getCheckOutEnd().toString());
        map.put("workStart",    shift.getWorkStart().toString());
        map.put("workEnd",      shift.getWorkEnd().toString());
        map.put("status",       shift.getStatus());
        return map;
    }
}
