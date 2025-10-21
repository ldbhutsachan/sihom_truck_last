package com.ldb.truck.Model.Borcar;

import lombok.Data;

@Data
public class BorcarReq {
    private String token;
    private String car_id;
    private String borNo;
    private String startDate;
    private String endDate;
}
