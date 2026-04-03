package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceBillApproveDto {
    private String toKen;
    private Long id;        // finance_bill id
    private String action;  // APPROVED | REJECTED
    private String remark;
}