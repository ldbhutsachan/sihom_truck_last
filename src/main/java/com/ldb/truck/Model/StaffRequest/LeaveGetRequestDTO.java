package com.ldb.truck.Model.StaffRequest;

import lombok.Data;

@Data
public class LeaveGetRequestDTO {
    private String token;       // ✅ required
    private String staffId;     // optional — "all" หรือ id เช่น "26"
    private String status;      // optional — PENDING, APPROVED, REJECTED
    private String startDate;   // optional — "2026-04-01"
    private String endDate;     // optional — "2026-04-30"
    private Integer borId;      // optional — bor_id
}