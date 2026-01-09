package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data

public class SupplierNotPayNestedDto {
    private Long keyId;
    private Long supplierId;
    private String supplierName;
    private String typeOf;
    private BigDecimal amountMustPay;
    private BigDecimal pay1;
    private String nextDatePay4;
    private String payStatus;
    private String currency;
    private String createBy;
    private LocalDateTime createDate;
    private List<FinanceBillDto> financeBills = new ArrayList<>();
}
