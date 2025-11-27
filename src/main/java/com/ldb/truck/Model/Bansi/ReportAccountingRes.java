package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;
@Data
public class ReportAccountingRes {
    private String status;
    private String message;
    private List<AccountingReportModel> data;
    private double sumReceiveUsd;
    private double sumReceiveLak;
    private double sumReceiveThb;
    private double sumPayUsd;
    private double sumPayLak;
    private double sumPayThb;

}


