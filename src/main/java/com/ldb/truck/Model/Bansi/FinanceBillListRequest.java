package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceBillListRequest {
    private String token;
    private String billType;
    private Long supplierId;
    private String billStatus;
    private String startDate;
    private String endDate;
}
