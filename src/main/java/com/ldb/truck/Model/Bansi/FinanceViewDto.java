package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceViewDto {
    private Long keyId;
    private Long supplierId;
    private String supplierName;
    private String financeBill;
    private Double amountMustPay;
    private Double pay1;
    private Double pay2;
    private Double pay3;
    private Double pay4;
    private Double pay5;
    private String firstDatePay;
    private String lastDatePay;
    private String nextDatePay;
    private String payStatus;
    private String currency;
    private String createBy;
    private String billNo;
    private String createDate;
    private String token;
    private String startDate;
    private String endDate;
}
