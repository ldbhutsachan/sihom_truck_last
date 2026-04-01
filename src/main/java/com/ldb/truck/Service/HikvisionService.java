package com.ldb.truck.Service;

import com.ldb.truck.Entity.Staff.StaffEntity;
import com.ldb.truck.Model.Staffs.CheckInRequestDTO;
import com.ldb.truck.Repository.Staffs.UserRepository;
import com.ldb.truck.Service.RegisterService.FaceService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.truck.Util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HikvisionService {

    private final FaceService faceService;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    // ✅ Default password เดียวกันทุกคน
    private static final String DEFAULT_PASSWORD = "1234";

    public Object processEvent(String rawData) {
        try {
            JsonNode root = objectMapper.readTree(rawData);

            if (root.isArray()) {
                root = root.get(0);
            }

            // ✅ ดึง ipAddress และ macAddress จาก root
            String ipAddress  = root.path("ipAddress").asText();
            String macAddress = root.path("macAddress").asText();
            String dateTime   = root.path("dateTime").asText();

            System.out.println("ipAddress: " + ipAddress);
            System.out.println("macAddress: " + macAddress);
            System.out.println("dateTime: " + dateTime);

            JsonNode eventNode = root.path("AccessControllerEvent");

            if (eventNode.isMissingNode()) {
                return "NO EVENT";
            }

            String staffCode = eventNode.path("employeeNoString").asText();
            String name      = eventNode.path("name").asText();

            if (staffCode == null || staffCode.isEmpty()) {
                return "NO STAFF CODE";
            }

            // เช็คว่ามี staff ใน DB ไหม
            Optional<StaffEntity> existingStaff = userRepository.findByStaffCode(staffCode);

            if (existingStaff.isEmpty()) {
                System.out.println("Auto Register: " + staffCode);
                autoRegisterStaff(staffCode, name, ipAddress, macAddress);  // ✅ ส่ง ip, mac
            }

            // Check-in / Check-out พร้อมส่ง ip, mac
            CheckInRequestDTO dto = new CheckInRequestDTO();
            dto.setStaffCode(staffCode);
            dto.setIpAddress(ipAddress);    // ✅
            dto.setMacAddress(macAddress);  // ✅

            return faceService.checkIn(dto);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    private void autoRegisterStaff(String staffCode, String name,
                                   String ipAddress, String macAddress) {

        String hashedPassword = PasswordUtil.hashPassword(DEFAULT_PASSWORD);

        String token;
        do {
            token = UUID.randomUUID().toString().replace("-", "");
        } while (userRepository.findByToken(token).isPresent());

        StaffEntity staff = new StaffEntity();
        staff.setStaffCode(staffCode);
        staff.setUsername(name != null && !name.isEmpty() ? name : staffCode);
        staff.setPasswordHash(hashedPassword);
        staff.setRole("USER");
        staff.setStatus("ACTIVE");
        staff.setToken(token);
        staff.setTokenExpiredAt(LocalDateTime.now().plusMonths(5));
        staff.setIpAddress(ipAddress);    // ✅
        staff.setMacAddress(macAddress);  // ✅


        userRepository.save(staff);

        System.out.println("Auto registered — staffCode: " + staffCode
                + " | ip: " + ipAddress
                + " | mac: " + macAddress);
    }
}