package com.ldb.truck.Model.Login.Owe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwePayBack {
    private String cusId;
    private String cusName;
    private String billNo;
    private String payDate;
    private String payAmount;
    private String payStatus;
    private String calTotalDate;
    private Double dayAmountCheck01;
    private Double dayAmountCheck02;
    private Double dayAmountCheck03;
    private Double dayAmountCheck04;
    private String currency;
}
