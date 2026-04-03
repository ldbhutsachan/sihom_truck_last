package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinanceBillPaymentDto {
    private String toKen;
    private Long financeBillId;
    private Long financeBillRefId;
    private String billNo;
    private BigDecimal payAmount;
    private String payDate;
    private String payMethod;       // TRANSFER | CASH | CHEQUE
    private String bankAccountName;
    private String bankAccountNo;
    private String bankName;
    private String slipFile;
    private String remark;
}
