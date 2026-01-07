package com.ldb.truck.Entity.Bansi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "v_accounting_finance")
public class FinanceListEntity {

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

    @Column(name = "pay_type_id")
    @JsonProperty("pay_type_id")
    private String payTypeId;

    @Column(name = "pay_type")
    @JsonProperty("pay_type")
    private String payType;

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

    @Column(name = "finance_approve_date")
    @JsonProperty("finance_approve_date")
    private String  financeApproveDate;

    @Column(name = "date_create")
    @JsonProperty("date_create")
    private String dateCreate;

    @Transient
    private String toKen; // สำหรับส่ง token จาก client

    @Transient
    private String startDate;

    @Transient
    private String endDate;

}
