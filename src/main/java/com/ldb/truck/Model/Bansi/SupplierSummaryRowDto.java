package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplierSummaryRowDto {
    private Long supplierId;
    private String supplierName;
    private String typeOf;
    private String currency;
    private BigDecimal sumMustPay;
    private BigDecimal sumPay;
}
