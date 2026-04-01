package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class LeaveRequestDTO {
    private String token;           // token ของ staff ที่ขอลา
    private String leaveType;       // SICK, PERSONAL, MATERNITY, UNPAID
    private String startDate;       // "2026-04-01"
    private String endDate;         // "2026-04-03"
    private String reason;          // เหตุผล (optional)
}
