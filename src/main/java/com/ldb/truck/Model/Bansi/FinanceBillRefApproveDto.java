package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceBillRefApproveDto {
    private String toKen;
    private Long id;            // id ของ request
    private String action;      // APPROVED | REJECTED
    private String approveRemark;
}
