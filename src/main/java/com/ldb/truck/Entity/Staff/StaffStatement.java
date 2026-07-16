package com.ldb.truck.Entity.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff_statement")
public class StaffStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "staff_code")
    private String staffCode;

    @Column(name = "title")
    private String title;

    @Column(name = "statement_date")
    private String statementDate;

    @Column(name = "usd_salary")
    private String usdSalary;

    @Column(name = "lak_salary")
    private String lak_salary;

    @Column(name = "work_day")
    private String workDay;

    @Column(name = "amount_salary")
    private String amount_salary;

    @Column(name = "ot")
    private String ot;

    @Column(name = "transportation_allowance")
    private String transportationAllowance;

    @Column(name = "amount_money")
    private String amountMoney;

    @Column(name = "bond")
    private String bond;

    @Column(name = "health_insurance_deduction")
    private String healthInsuranceDeduction;

    @Column(name = "income_tax")
    private String incomeTax;

    @Column(name = "total_deductions")
    private String totalDeductions;

    @Column(name = "total_earnings_lak")
    private String totalEarningsLak;

    @Column(name = "save_by")
    private String saveBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
