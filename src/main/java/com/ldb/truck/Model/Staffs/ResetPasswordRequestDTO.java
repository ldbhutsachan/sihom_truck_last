package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
    private String token;       // token ของ ADMIN
    private String staffCode;   // staffCode ของคนที่จะ reset
}
