package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;

@Data
public class FinanceBillApproveDto {
    private String toKen;
    private List<Long> detailIds; // ✅ เปลี่ยนจาก Long → List<Long>
    private String action;  // APPROVED | REJECTED
    private String remark;
}