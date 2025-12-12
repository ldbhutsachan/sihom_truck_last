package com.ldb.truck.Entity.Bansi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "tb_finance")
public class FinanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    @JsonProperty("key_id")
    private Long keyId;

    @Column(name = "supplier_id")
    @JsonProperty("supplier_id")
    private Integer supplierId;

    @Column(name = "finance_bill")
    @JsonProperty("finance_bill")
    private String financeBill;

    @Column(name = "amount_must_pay")
    @JsonProperty("amount_must_pay")
    private String amountMustPay;

    @Column(name = "pay1")
    @JsonProperty("pay1")
    private BigDecimal pay1;

    @Column(name = "pay2")
    @JsonProperty("pay2")
    private BigDecimal pay2;

    @Column(name = "pay3")
    @JsonProperty("pay3")
    private BigDecimal pay3;

    @Column(name = "pay4")
    @JsonProperty("pay4")
    private BigDecimal pay4;

    @Column(name = "pay5")
    @JsonProperty("pay5")
    private BigDecimal pay5;

    @Column(name = "first_date_pay")
    @JsonProperty("first_date_pay")
    private LocalDateTime firstDatePay;

    @Column(name = "last_date_pay")
    @JsonProperty("last_date_pay")
    private LocalDateTime lastDatePay;

    @Column(name = "next_date_pay")
    @JsonProperty("next_date_pay")
    private String nextDatePay;

    @Column(name = "pay_status")
    @JsonProperty("pay_status")
    private String payStatus;

    @Column(name = "currency")
    @JsonProperty("currency")
    private String currency;

    @Column(name = "create_by")
    @JsonProperty("create_by")
    private String createby;

    @Transient
    private String toKen; //สำหรับ token จาก client
}
