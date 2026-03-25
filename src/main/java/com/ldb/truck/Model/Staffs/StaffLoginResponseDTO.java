package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class StaffLoginResponseDTO {
//    private boolean success;
//    private String message;
    private String username;
    private String token;
    private String role;
    private LocalDateTime tokenExpiredAt;
}
