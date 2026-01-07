package com.ldb.truck.Entity.Bansi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_finance_pay")
public class FinanceHisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    @JsonProperty("key_id")
    private Long keyId;

    @Column(name = "finance_bill")
    @JsonProperty("finance_bill")
    private String financeBill;

    @Column(name = "pay_amount")
    @JsonProperty("pay_amount")
    private BigDecimal payAmount;

    @Column(name = "date_pay")
    @JsonProperty("date_pay")
    private String datePay;

    @Column(name = "create_date")
    @JsonProperty("create_date")
    private LocalDateTime createDate;
}
