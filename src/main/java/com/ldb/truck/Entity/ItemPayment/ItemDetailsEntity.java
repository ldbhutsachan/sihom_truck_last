package com.ldb.truck.Entity.ItemPayment;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;
@Data
@Entity
@Table(name = "v_payment_item_del")
public class ItemDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_no")
    private Long payment_no;

    @Column(name = "bill_no")
    private String bill_no;

    @Column(name = "status")
    private String status;

    @Column(name = "invoice_no")
    private String invoice_no;

    @Column(name = "total")
    private Float total;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "ccy")
    private String ccy;

    @Column(name = "payment_type")
    private String payment_type;

    @Column(name = "rexchange_rate")
    private Float rexchange_rate;

    @Column(name = "savedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date savedate;

    @Column(name = "saveby")
    private String saveby;

    @Column(name = "type")
    private String type;

    @Column(name = "exp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp;
}