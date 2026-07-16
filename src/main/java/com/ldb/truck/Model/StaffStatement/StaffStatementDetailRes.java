package com.ldb.truck.Model.StaffStatement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffStatementDetailRes {
    private Integer id;
    private String staffCode;
    private String title;
    private String statementDate;
    private String usdSalary;
    private String lakSalary;
    private String workDay;
    private String amountSalary;
    private String ot;
    private String transportationAllowance;
    private String amountMoney;
    private String bond;
    private String healthInsuranceDeduction;
    private String incomeTax;
    private String totalDeductions;
    private String totalEarningsLak;
    private String saveBy;
    private String createDate;

    // Joined fields
    private String username;
    private String laoName;
    private Long deptId;
    private String deptName;
    private Long posId;
    private String posName;
    private Integer borId;
    private String bName;
}
