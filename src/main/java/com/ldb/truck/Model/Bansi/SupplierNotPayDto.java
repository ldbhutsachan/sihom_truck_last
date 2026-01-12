package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SupplierNotPayDto {

    private Long keyId;              // key_id
    private String billNo;            // bill_No
    private String financeBill;       // finance_bill
    private Long supplierId;          // supplier_id
    private String supplierName;      // supplier_name
    private String typeOf;            // type_of
    private BigDecimal amountMustPay; // amount_must_pay
    private BigDecimal paid;           // paid
    private String nextDatePay;      // next_date_pay
    private String payStatus;         // pay_status
    private String currency;          // currency
    private String createBy;           // create_by
    private LocalDateTime createDate; // create_date
}
