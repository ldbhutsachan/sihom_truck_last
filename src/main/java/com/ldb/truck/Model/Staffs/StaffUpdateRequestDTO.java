package com.ldb.truck.Model.Staffs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StaffUpdateRequestDTO {
    private String username;
    private String laoname;
    private String phone;
    private String role;
    private String status;
    private Long  deptId;
    private Long  posId;
    private Integer borId;
    private String gender;
    private String address;
    private String startWorkDate;
    private String birthDate;
    // add work schedule
    private BigDecimal baseSalary;
    private String  workSchedule;
    private Integer cycleWorkDays;
    private Integer cycleOffDays;
    private String  cycleStartDate;  // รับเป็น String แล้วแปลงใน Service
}
