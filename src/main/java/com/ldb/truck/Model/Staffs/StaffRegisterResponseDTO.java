package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class StaffRegisterResponseDTO {
    private boolean success;
    private String message;
    private Long userId;
    private String staffCode;
    private String username;
    private String password;
    private LocalDateTime createdAt;
}
