package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceViewDto {
    private Long keyId;
    private Long supplierId;
    private String supplierName;
    private String financeBill;
    private String typeOf;
    private Double amountMustPay;
    private Double pay1;
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
