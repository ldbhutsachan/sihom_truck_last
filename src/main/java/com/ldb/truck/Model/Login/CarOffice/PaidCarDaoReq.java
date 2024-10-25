package com.ldb.truck.Model.Login.CarOffice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaidCarDaoReq {
    private String kEY_ID;
    private String carId;
    private String pdfFile;
    private String cur;
    private String pricePaid;
    private String branch;
    private String userId;
    private String toKen;
}
