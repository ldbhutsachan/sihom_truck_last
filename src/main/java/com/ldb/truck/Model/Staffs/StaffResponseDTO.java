package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StaffResponseDTO {
    private Long id;
    private String staffCode;
    private String username;
    private String laoName;
    private String phone;
    private String role;
    private String status;
    private String staffImage;
    private Integer borId;
    private String borName;
    private Long deptId;
    private String departmentName;
    private Long PosId;
    private String positionNAME;
    private String gender;
    private String address;
    private LocalDate birthDate;

    private LocalDate startworkDate;
    private BigDecimal salary;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
