package com.ldb.truck.Model.StaffStatement;

import lombok.Data;

@Data
public class StaffStatementUpdateReq {
    private Integer id;
    private String token;
    private String title;
    private String statementDate;
    private String usdSalary;
    private String lak_salary;
    private String workDay;
    private String amount_salary;
    private String ot;
    private String transportationAllowance;
    private String amountMoney;
    private String bond;
    private String healthInsuranceDeduction;
    private String incomeTax;
    private String totalDeductions;
    private String totalEarningsLak;
}
