package com.ldb.truck.Service;

import com.ldb.truck.Entity.Staff.StaffEntity;
import com.ldb.truck.Model.Staffs.CheckInRequestDTO;
import com.ldb.truck.Repository.Staffs.UserRepository;
import com.ldb.truck.Service.RegisterService.FaceService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.truck.Util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HikvisionService {

    private final FaceService faceService;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    //  Default password เดียวกันทุกคน
    private static final String DEFAULT_PASSWORD = "1234";

    //old code
    //    public Object processEvent(String rawData) {
//        try {
//            JsonNode root = objectMapper.readTree(rawData);
//
//            if (root.isArray()) {
//                root = root.get(0);
//            }
//
//            //  ดึง ipAddress และ macAddress จาก root
//            String ipAddress  = root.path("ipAddress").asText();
//            String macAddress = root.path("macAddress").asText();
//            String dateTime   = root.path("dateTime").asText();
//
//            System.out.println("ipAddress: " + ipAddress);
//            System.out.println("macAddress: " + macAddress);
//            System.out.println("dateTime: " + dateTime);
//
//            JsonNode eventNode = root.path("AccessControllerEvent");
//
//            if (eventNode.isMissingNode()) {
//                return "NO EVENT";
//            }
//
//            String staffCode = eventNode.path("employeeNoString").asText();
//            String name      = eventNode.path("name").asText();
//
//            if (staffCode == null || staffCode.isEmpty()) {
//                return "NO STAFF CODE";
//            }
//
//            // เช็คว่ามี staff ใน DB ไหม
//            Optional<StaffEntity> existingStaff = userRepository.findByStaffCode(staffCode);
//
//            if (existingStaff.isEmpty()) {
//                System.out.println("Auto Register: " + staffCode);
//                autoRegisterStaff(staffCode, name, macAddress);  //  ส่ง ip, mac
//            }
//
//            // Check-in / Check-out พร้อมส่ง ip, mac
//            CheckInRequestDTO dto = new CheckInRequestDTO();
//            dto.setStaffCode(staffCode);
//            dto.setIpAddress(ipAddress);
//            dto.setMacAddress(macAddress);
//
//            return faceService.checkIn(dto);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "ERROR: " + e.getMessage();
//        }
//    }
//
//    private void autoRegisterStaff(String staffCode, String name,
//                                   String macAddress) {
//
//        String hashedPassword = PasswordUtil.hashPassword(DEFAULT_PASSWORD);
//
//        String token;
//        do {
//            token = UUID.randomUUID().toString().replace("-", "");
//        } while (userRepository.findByToken(token).isPresent());
//
//        StaffEntity staff = new StaffEntity();
//        staff.setStaffCode(staffCode);
//        staff.setUsername(name != null && !name.isEmpty() ? name : staffCode);
//        staff.setPasswordHash(hashedPassword);
//        staff.setRole("USER");
//        staff.setStatus("ACTIVE");
//        staff.setToken(token);
//        staff.setTokenExpiredAt(LocalDateTime.now().plusMonths(5));
////        staff.setIpAddress(ipAddress);
//        staff.setMacAddress(macAddress);
//
//
//        userRepository.save(staff);
//
//        System.out.println("Auto registered — staffCode: " + staffCode
    ////                + " | ip: " + ipAddress
//                + " | mac: " + macAddress);
//    }

    //  Cache staffCode ใน Memory (thread-safe)
    private Set<String> registeredStaffCodes;

    // =====================================================
    // STARTUP — โหลดเฉพาะ staffCode (ไม่ดึง entity ทั้งหมด)
    // =====================================================
    @PostConstruct
    public void loadStaffCodes() {
        //  แก้ไข: init cache ตรงนี้แทน
        registeredStaffCodes = Collections.synchronizedSet(new HashSet<>());

        try {
            userRepository.findAllStaffCodesByStatus("ACTIVE")
                    .forEach(code -> {
                        if (code != null && !code.isBlank()) {
                            registeredStaffCodes.add(code);
                        }
                    });
            System.out.println(" Loaded " + registeredStaffCodes.size()
                    + " staff codes into cache");
        } catch (Exception e) {
            System.err.println(" Failed to load staff codes: " + e.getMessage());
        }
    }
    // =====================================================
    // PROCESS EVENT
    // =====================================================
    public Object processEvent(String rawData) {
        try {
            JsonNode root = objectMapper.readTree(rawData);

            if (root.isArray()) {
                root = root.get(0);
            }

            String ipAddress  = getTextSafe(root, "ipAddress");
            String macAddress = getTextSafe(root, "macAddress");
            String dateTime   = getTextSafe(root, "dateTime");

            System.out.println("ipAddress : " + ipAddress);
            System.out.println("macAddress: " + macAddress);
            System.out.println("dateTime  : " + dateTime);

            JsonNode eventNode = root.path("AccessControllerEvent");

            if (eventNode.isMissingNode()) {
                System.out.println(" No AccessControllerEvent");
                return "NO EVENT";
            }

            String staffCode = getTextSafe(eventNode, "employeeNoString");
            String name      = getTextSafe(eventNode, "name");

            if (staffCode == null
                    || staffCode.isEmpty()
                    || staffCode.equalsIgnoreCase("null")) {
                System.out.println(" No staff code");
                return "NO STAFF CODE";
            }

            if (!registeredStaffCodes.contains(staffCode)) {
                Optional<StaffEntity> existingStaff =
                        userRepository.findByStaffCode(staffCode);

                if (existingStaff.isEmpty()) {
                    System.out.println(" Auto Register: " + staffCode);
                    autoRegisterStaff(staffCode, name, ipAddress, macAddress);
                } else {
                    registeredStaffCodes.add(staffCode);
                }
            }

            CheckInRequestDTO dto = new CheckInRequestDTO();
            dto.setStaffCode(staffCode);
            dto.setIpAddress(ipAddress);
            dto.setMacAddress(macAddress);

            return faceService.checkIn(dto);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // =====================================================
    // AUTO REGISTER STAFF
    // =====================================================
    private void autoRegisterStaff(String staffCode, String name,
                                   String ipAddress, String macAddress) {
        String hashedPassword = PasswordUtil.hashPassword(DEFAULT_PASSWORD);

        byte[] tokenBytes = new byte[32];
        new SecureRandom().nextBytes(tokenBytes);
        String token = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(tokenBytes);

        StaffEntity staff = new StaffEntity();
        staff.setStaffCode(staffCode);
        staff.setUsername(
                name != null && !name.isEmpty() && !name.equalsIgnoreCase("null")
                        ? name : staffCode);
        staff.setPasswordHash(hashedPassword);
        staff.setRole("USER");
        staff.setStatus("ACTIVE");
        staff.setToken(token);
        staff.setTokenExpiredAt(LocalDateTime.now().plusMonths(5));
        staff.setMacAddress(macAddress);

        try {
            userRepository.save(staff);
            registeredStaffCodes.add(staffCode);
            System.out.println(" Auto registered — staffCode: " + staffCode
                    + " | ip: " + ipAddress + " | mac: " + macAddress);

        } catch (DataIntegrityViolationException e) {
            System.out.println(" Duplicate staffCode, skipping: " + staffCode);
            registeredStaffCodes.add(staffCode);

        } catch (Exception e) {
            System.err.println(" Failed to register: " + staffCode
                    + " | " + e.getMessage());
        }
    }

    // =====================================================
    // HELPER
    // =====================================================
    private String getTextSafe(JsonNode node, String field) {
        if (node == null) return null;
        JsonNode value = node.path(field);
        if (value.isMissingNode() || value.isNull()) return null;
        String text = value.asText().trim();
        return text.isEmpty() ? null : text;
    }

}