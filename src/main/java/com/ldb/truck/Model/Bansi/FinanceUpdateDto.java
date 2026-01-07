package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceUpdateDto {
    private String toKen;
    private String financeBill;
    private String pay;           // ค่าจาก client
    private String payDate;
    private String nextDatePay;   // yyyy-MM-dd หรือ yyyy-MM-dd'T'HH:mm:ss
    private String currency;
}
