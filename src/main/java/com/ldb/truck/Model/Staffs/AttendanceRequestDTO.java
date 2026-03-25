package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class AttendanceRequestDTO {
    private String staffCode;   // "all" หรือ "EMP001" — ADMIN ส่ง "all" ได้
    private String startDate;   // "2026-03-01" (optional)
    private String endDate;     // "2026-03-31" (optional)
    private String token; // staffCode ของคนที่ขอดูข้อมูล (ใช้เช็ค role)
}
