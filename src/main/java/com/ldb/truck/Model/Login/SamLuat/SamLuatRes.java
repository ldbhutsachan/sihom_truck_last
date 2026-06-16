package com.ldb.truck.Model.Login.SamLuat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SamLuatRes {
    private String status = "00";
    private String message = "success";
    private Object data;
}
