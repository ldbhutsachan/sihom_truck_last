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
    private BigDecimal amountMustPay;

    @Column(name = "pay1")
    @JsonProperty("pay1")
    private BigDecimal pay1;

    @Column(name = "first_date_pay")
    @JsonProperty("first_date_pay")
    private LocalDateTime firstDatePay;


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

    @Column(name = "create_date")
    @JsonProperty("create_date")
    private LocalDateTime createDate;

    @Column(name = "type_of")
    @JsonProperty("type_of")
    private String typeOf;

    @Column(name = "approve_by")
    @JsonProperty("approve_by")
    private String approveby;

    @Column(name = "approve_date")
    @JsonProperty("approve_date")
    private LocalDateTime approveDate;


    @Transient
    private String toKen; //สำหรับ token จาก client
}
