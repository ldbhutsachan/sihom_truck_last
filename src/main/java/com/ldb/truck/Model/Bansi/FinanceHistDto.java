package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceHistDto {
    private Long keyId;
    private String financeBill;
    private String payAmount;
    private String datePay;
    private String createDate;
}
