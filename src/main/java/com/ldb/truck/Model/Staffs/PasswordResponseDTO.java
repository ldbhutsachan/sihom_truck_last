package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResponseDTO {
    private boolean success;
    private String message;
    private String newPassword;     // แสดงเฉพาะตอน reset เท่านั้น
}
