package com.ldb.truck.Model.IncomePay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class incomePayReq {
    private String  startDate;
    private String endDate;
    private String status;

}
