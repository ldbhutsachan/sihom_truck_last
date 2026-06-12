package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LeaveResponseDTO {
    private boolean success;
    private String message;
    private Long leaveId;
    private String staffCode;
    private String username;
    private String leaveType;
    private String startDate;
    private String endDate;
    private Integer totalDays;
    private String status;
    private Integer remainingDays;  // วันลาคงเหลือ
    private LocalDateTime createdAt;
}