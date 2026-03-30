package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class CheckInRequestDTO {
    private String staffCode;   // ส่งมาจาก Frontend หลัง AI ตรวจใบหน้า
    private String ipAddress;
    private String macAddress;
}
