package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class StaffQueryRequestDTO {
    private String staff;   // "all" หรือ "1", "2", "3" //FOR SHOW
    private String staffId;  // FOR UPDATE
    private String token;
}
