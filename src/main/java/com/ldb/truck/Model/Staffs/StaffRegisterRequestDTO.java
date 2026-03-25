package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class StaffRegisterRequestDTO {
    private String staffCode;
    private String username;
    private String password;
    private String phone;
    private String role;
}
