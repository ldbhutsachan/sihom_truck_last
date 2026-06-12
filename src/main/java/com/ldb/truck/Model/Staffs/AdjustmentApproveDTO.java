package com.ldb.truck.Model.Staffs;

import lombok.Data;

@Data
public class AdjustmentApproveDTO {
    private String token;           // token ของ HR
    private Long adjustmentId;      // id ของ request
    private String status;          // APPROVED หรือ REJECTED
    private String reason;          // เหตุผล (optional สำหรับ REJECTED)
}