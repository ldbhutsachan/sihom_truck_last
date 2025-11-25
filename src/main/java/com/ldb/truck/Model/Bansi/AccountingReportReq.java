package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class AccountingReportReq {
    private String toKen;
    private String big_project_id;
    private String small_project_id;
    private String pay_type_id;
    private String type_of_pay;
    private String startDate;
    private String endDate;
}
