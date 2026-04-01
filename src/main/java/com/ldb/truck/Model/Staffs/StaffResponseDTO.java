package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StaffResponseDTO {
    private Long id;
    private String staffCode;
    private String username;
    private String phone;
    private String role;
    private String status;
    private String staffImage;
    private String department;   // ✅ เพิ่ม
    private String position;     // ✅ เพิ่ม
    private Integer borId;      // ✅ เพิ่ม
    private String borName;     // ✅ เพิ่ม
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
