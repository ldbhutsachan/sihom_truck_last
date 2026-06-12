package com.ldb.truck.Model.Login.Login;

import lombok.Data;

@Data
public class LoginReq {
    private String user;
    private String password;
    private String role;
    private String userId;
    private String branch;
    private String system;    // ✅ เพิ่ม "NORMAL" หรือ "CHECK-INOUT"
}