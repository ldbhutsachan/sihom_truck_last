package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinanceBillRefUpdateRequestDto {
    private String toKen;
    private String financeBillNo;   // เลขที่ Finance Bill
    private Long refId;             // id ของ tb_finance_bill_ref
    private BigDecimal newAmount;   // ยอดที่ต้องการแก้เป็น
    private String remark;          // เหตุผล
}
