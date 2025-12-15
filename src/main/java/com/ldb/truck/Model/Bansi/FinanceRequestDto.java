package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;

@Data
public class FinanceRequestDto {
    private String toKen;
    private Integer supplierId;
    private String financeBill;
    private String amountMustPay;
    private String pay;
    private String currency;
    private String firstDatePay;
    private String nextDatePay;

//    private List<BillListDto> billList;
    private List<String> billList;

}
