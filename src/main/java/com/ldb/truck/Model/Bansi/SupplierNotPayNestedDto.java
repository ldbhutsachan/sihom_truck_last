package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
public class SupplierNotPayNestedDto {

    private Long keyId;
    private Long supplierId;
    private String supplierName;

    // 🔥 แยกตาม typeOf
    private CurrencySummaryDto PAY = new CurrencySummaryDto();
    private CurrencySummaryDto RECEIVE = new CurrencySummaryDto();

    private List<FinanceBillDto> financeBills = new ArrayList<>();
}



