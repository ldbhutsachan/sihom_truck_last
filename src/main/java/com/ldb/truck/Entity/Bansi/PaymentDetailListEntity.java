package com.ldb.truck.Entity.Bansi;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_accounting_list")
public class PaymentDetailListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "key_id")
    private Long keyId;
    @Column(name = "bill_No")
    private String billNo;

    @Column(name = "list_name")
    private String listName;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private BigDecimal  price;

    @Column(name = "reduce")
    private Integer reduce;

    @Column(name = "reduce_status")
    private String reduceStatus;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "tax_status")
    private String taxStatus;

    @Column(name = "usd_price")
    private Float usdPrice;

}
