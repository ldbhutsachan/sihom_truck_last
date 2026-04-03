package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FinanceBillRequestDto {
    private String toKen;
    private String title;
    private BigDecimal totalAmount;
    private String currency;
    private BigDecimal exchangeRate;
    private String remark;
    private List<BillRefDto> billNos;

    @Data
    public static class BillRefDto {
        private Long keyId;
        private String billNo;
        private BigDecimal originalAmount; // ยอดเต็มของ bill_no
        private BigDecimal amount;         // ยอดที่เรียกเก็บครั้งนี้
    }
}
