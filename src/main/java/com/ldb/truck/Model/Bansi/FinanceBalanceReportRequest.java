package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceBalanceReportRequest {
    private String token;
    private Long supplierId;
    private Long bigProjectId;
    private Long smallProjectId;
    private Long payTypeId;
    private String billType;        // PAY | RECEIVE
    private String currency;        // LAK | USD | THB
    private String startDate;
    private String endDate;
}
