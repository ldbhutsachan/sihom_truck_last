package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class AdjustmentRequestDTO {
    private String token;           // token ของ staff
    private String requestDate;     // วันที่ขอแก้ไข เช่น "2026-04-01"
    private String checkInTime;     // เช่น "2026-04-01T08:00:00" (optional)
    private String checkOutTime;    // เช่น "2026-04-01T17:00:00" (optional)
    private String reason;          // เหตุผล
}
