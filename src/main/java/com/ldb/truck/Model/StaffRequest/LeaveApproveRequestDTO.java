package com.ldb.truck.Model.StaffRequest;

import lombok.Data;

@Data
public class LeaveApproveRequestDTO {
    private String token;       // token ของ HR
    private Long leaveId;       // id ของ leave_request
    private String status;      // APPROVED หรือ REJECTED
    private String reason;      // เหตุผล (optional สำหรับ REJECTED)
}
