package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;
@Data
public class ReportAccountingRes {
    private String status;
    private String message;
    private List<AccountingReportModel> data;
    private double sumUsd;
    private double sumLak;
    private double sumThb;
}


