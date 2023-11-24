package com.ldb.truck.Model.Login.Pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrintBillPaymentHeader {
    //--header for print
    private String printDate;
    private String billNo;
    private String cusId;
    private String cusName;
    private String location;
    private String nextDatePay;


}
