package com.ldb.truck.Model.Staffs;
import lombok.Data;

@Data
public class LoginResDTO {
    private String status;
    private String message;
    private StaffLoginResponseDTO data;
}
