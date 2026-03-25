package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class ChangePasswordRequestDTO {
    private String token;           // token ของตัวเอง
    private String oldPassword;     // password เดิม
    private String newPassword;     // password ใหม่
}
