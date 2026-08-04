package com.ldb.truck.Entity.Bansi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "v_accounting_finance_again")
public class FinanceListEntityAgain {
    @Id
    @Column(name = "key_id")
    @JsonProperty("key_id")
    private Long keyId;

    @Column(name = "supplierid")
    @JsonProperty("supplierid")
    private Integer supplierId;

    @Column(name = "supplier_name")
    @JsonProperty("supplier_name")
    private String supplierName;

    @Column(name = "big_project_id")
    @JsonProperty("big_project_id")
    private Integer bigProjectId;

    @Column(name = "big_project")
    @JsonProperty("big_project")
    private String bigProject;

    @Column(name = "small_project_id")
    @JsonProperty("small_project_id")
    private Integer smallProjectId;

    @Column(name = "small_project")
    @JsonProperty("small_project")
    private String smallproject;

    @Column(name = "pay_type_id")
    @JsonProperty("pay_type_id")
    private String payTypeId;

    @Column(name = "pay_type")
    @JsonProperty("pay_type")
    private String payType;

    @Column(name = "pay_typegroup_id")
    @JsonProperty("pay_typegroup_id")
    private String payTypegroupId;

    @Column(name = "pay_typegroup_name")
    @JsonProperty("pay_typegroup_name")
    private String payTypegroupName;

    @Column(name = "type_of")
    @JsonProperty("type_of")
    private String typeOf;

    @Column(name = "bill_No")
    @JsonProperty("bill_No")
    private String billNo;

    @Column(name = "bill_status")
    @JsonProperty("bill_status")
    private String billStatus;

    @Column(name = "pay_status")
    @JsonProperty("pay_status")
    private String paystatus;

    @Column(name = "bank_account_name")
    @JsonProperty("bank_account_name")
    private String bankAccountName;

    @Column(name = "bank_account_no")
    @JsonProperty("bank_account_no")
    private String bankAccountNo;

    @Column(name = "bank_name")
    @JsonProperty("bank_name")
    private String bankName;

    @Column(name = "currency")
    @JsonProperty("currency")
    private String currency;

    @Column(name = "exchange_rate")
    @JsonProperty("exchange_rate")
    private String exchangeRate;

    @Column(name = "price")
    @JsonProperty("price")
    private Double price;

    @Column(name = "usd_price")
    @JsonProperty("usd_price")
    private Double usdPrice;

    @Column(name = "original_price")
    @JsonProperty("original_price")
    private Double originalPrice;

    @Column(name = "original_lak_price")
    @JsonProperty("original_lak_price")
    private Double originalLakPrice;

    @Column(name = "original_usd_price")
    @JsonProperty("original_usd_price")
    private Double originalUsdPrice;

    @Column(name = "discount_amount")
    @JsonProperty("discount_amount")
    private Double discountAmount;

    @Column(name = "basi_approveby")
    @JsonProperty("basi_approveby")
    private String basiApproveby;

    @Column(name = "basi_approve_date")
    @JsonProperty("basi_approve_date")
    private String basiAapproveDate;

    @Column(name = "auditorby")
    @JsonProperty("auditorby")
    private String auditorBy;

    @Column(name = "auditor_date")
    @JsonProperty("auditor_date")
    private String auditorDate;

    @Column(name = "date_create")
    @JsonProperty("date_create")
    private String dateCreate;

    @Column(name = "paid_amount")
    @JsonProperty("paid_amount")
    private Double paidAmount;

    @Column(name = "next_pay_date")
    @JsonProperty("next_pay_date")
    private String nextPayDate;

    @Column(name = "remaining_amount")
    @JsonProperty("remaining_amount")
    private Double remainingAmount;

    @Transient
    private String status; // ← รับค่าจาก request

    @Transient
    private String toKen; // สำหรับส่ง token จาก client

    @Transient
    private String startDate;

    @Transient
    private String endDate;
}
