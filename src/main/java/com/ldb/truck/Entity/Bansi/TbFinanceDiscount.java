package com.ldb.truck.Entity.Bansi;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_finance_discount")
public class TbFinanceDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_id")
    private Long billId;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "amount_before")
    private BigDecimal amountBefore;

    @Column(name = "amount_after")
    private BigDecimal amountAfter;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "create_by")
    private Long createBy;
}
