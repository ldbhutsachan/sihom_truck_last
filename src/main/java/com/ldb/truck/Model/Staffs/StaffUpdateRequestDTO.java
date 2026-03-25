package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class StaffUpdateRequestDTO {
    private String username;
    private String phone;
    private String role;
    private String status;
}
