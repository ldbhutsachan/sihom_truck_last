package com.ldb.truck.Model.StaffRequest;

import lombok.Data;

@Data
public class DailyAttendanceRequestDTO {
    private String token;       // required
    private String date;        // optional — "2026-04-01" ถ้าไม่ส่ง = วันนี้
    private String borId;      // optional — กรอง bor
}