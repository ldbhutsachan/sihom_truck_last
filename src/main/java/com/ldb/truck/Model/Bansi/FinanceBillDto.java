package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class FinanceBillDto {
    private String financeBill;
    private BigDecimal amountMustPay;
    private BigDecimal paid;
    private LocalDateTime dateCreate;
    private String nextDatePay;
    private String payStatus;
    private String currency;
    private String typeOf;

    private List<BillNoDetailDto> billNos = new ArrayList<>();
}

