package com.ldb.truck.Service;

import com.ldb.truck.Model.Staffs.CheckInRequestDTO;
import com.ldb.truck.Service.RegisterService.FaceService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HikvisionService {

    private final FaceService faceService;
    private final ObjectMapper objectMapper;
    public Object processEvent(String rawData) {
        try {
            System.out.println("=== RAW DATA FROM HIKVISION ===");
            System.out.println(rawData);
            System.out.println("================================");
            // ✅ Log ดู JSON จริงที่ Hikvision ส่งมา
            System.out.println("Raw data from Hikvision: " + rawData);

            // Parse JSON
            JsonNode root = objectMapper.readTree(rawData);

            // ดึง staffCode จาก employeeNoString
            String staffCode = root
                    .path("AccessControllerEvent")
                    .path("employeeNoString")
                    .asText();

            if (staffCode == null || staffCode.isEmpty()) {
                System.out.println("No staffCode found in event");
                return "No staffCode found";
            }

            System.out.println("staffCode: " + staffCode);

            // ส่งต่อไปให้ FaceService
            CheckInRequestDTO dto = new CheckInRequestDTO();
            dto.setStaffCode(staffCode);

            return faceService.checkIn(dto);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
