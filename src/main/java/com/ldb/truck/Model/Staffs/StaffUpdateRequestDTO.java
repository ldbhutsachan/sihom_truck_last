package com.ldb.truck.Model.Staffs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StaffUpdateRequestDTO {
    private String username;
    private String phone;
    private String role;
    private String status;
    private String department;   // ✅ เพิ่ม
    private String position;     // ✅ เพิ่ม
    private Integer borId;
    // ✅ เพิ่ม work schedule
    private BigDecimal baseSalary;
    private String  workSchedule;
    private Integer cycleWorkDays;
    private Integer cycleOffDays;
    private String  cycleStartDate;  // รับเป็น String แล้วแปลงใน Service
}
