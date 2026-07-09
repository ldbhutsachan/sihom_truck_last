package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class LeaveRequestDTO {
    private Long id;                // สำหรับอัปเดต
    private String token;           // token ของ staff ที่ขอลา
    private String leaveType;       // SICK, PERSONAL, MATERNITY, UNPAID
    private String startDate;       // "2026-04-01"
    private String endDate;         // "2026-04-03"
    private String leaveTitle;
    private String reason;          // เหตุผล (optional)
    private String contact;
    private String relationship;
    private String halfDay;         //  เพิ่มใหม่ — "MORNING", "AFTERNOON", null=เต็มวัน
    private org.springframework.web.multipart.MultipartFile[] files;
}
