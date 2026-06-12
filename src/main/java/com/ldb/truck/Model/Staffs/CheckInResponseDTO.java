package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CheckInResponseDTO {
    private boolean success;
    private String message;
    private String notimessage;
    private String staffCode;
    private String username;
    private String checkType;           // ✅ เพิ่ม
    private LocalDateTime checkTime;
}
