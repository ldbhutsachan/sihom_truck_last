package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class PaymentDetailReq {
    private String toKen;
    private String startDate;
    private String endDate;
    private Long itemTypeid;
    private Long req_id;
    private Long pid;
}
