package com.ldb.truck.Entity.ItemPayment;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "payment_item")
public class ItemPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_no")
    private Long paymentNo;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "saveby")
    private String savebBy;

    @Column(name = "savedate")
    @Temporal(TemporalType.DATE)
    private Date saveDate;


    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "ccy")
    private String ccy;

    @Column(name = "status")
    private String status;

    @Column(name = "total")
    private Float total;

    @Column(name = "qty")
    private Integer qty;


}
