package com.ldb.truck.Model.Login.Report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAllReq {
    //private String staff_Id;
    private String startDate;
    private String endDate;
    private String status;
    private String toKen;
    private String userId;
    private String branch;
    private String status_fuel;
    private String fuelStationId;
    private String productId;

}
