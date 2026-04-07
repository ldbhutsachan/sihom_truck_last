package com.ldb.truck.Entity.Bansi;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "tb_finance_bill")
public class TbFinanceBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "finance_bill_no", unique = true)
    private String financeBillNo;

    @Column(name = "title")
    private String title;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;


    @Column(name = "currency")
    private String currency;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name = "remark")
    private String remark;

}