package com.ldb.truck.Entity.Bansi;

import lombok.Data;
import net.jcip.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Immutable
@Table(name = "v_finance_balance_summary")
public class VFinanceBalanceSummary {

    @Id
    @Column(name = "row_num")
    private Long rowNum;

    @Column(name = "supplierid")
    private Long supplierid;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "bill_type")
    private String billType;

    @Column(name = "currency")
    private String currency;

    @Column(name = "big_project_id")
    private Long bigProjectId;

    @Column(name = "big_project")
    private String bigProject;

    @Column(name = "small_project_id")
    private Long smallProjectId;

    @Column(name = "small_project")
    private String smallProject;

    @Column(name = "pay_type_id")
    private Long payTypeId;

    @Column(name = "pay_type")
    private String payType;

    @Column(name = "finance_approve_date")
    private LocalDate financeApproveDate;

    @Column(name = "date_in")
    private LocalDate dateIn;

    @Column(name = "date_out")
    private LocalDate dateOut;

    @Column(name = "opening_balance", precision = 20, scale = 5)
    private BigDecimal openingBalance;

    @Column(name = "income", precision = 20, scale = 5)
    private BigDecimal income;

    @Column(name = "outcome", precision = 20, scale = 5)
    private BigDecimal outcome;

    @Column(name = "closing_balance", precision = 20, scale = 5)
    private BigDecimal closingBalance;
}
