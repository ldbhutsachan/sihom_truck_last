package com.ldb.truck.Model.Login.Owe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Owe {
    private String billNo;
    private String payDate;
    private String invoiceNo;
    private String paymentType;
    private String bankName;
    private String refNo;
    private String cusId;
    private String cusName;
    private String moBile;
    private String amount;
    private String payAmount;
    private String status;
    private String totalDay;
    private Double paymentAmounts;
    private String noPaymentAmount;
}
