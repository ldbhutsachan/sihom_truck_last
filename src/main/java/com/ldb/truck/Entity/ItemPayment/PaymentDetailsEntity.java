package com.ldb.truck.Entity.ItemPayment;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "payment_details")
public class PaymentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_no")
    private Long paymentNo;

    @Column(name = "saveby")
    private String saveby;

    @Column(name = "savedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveDate;

    @Column(name = "exp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp;

    @Column(name = "status")
    private String status;

    @Column(name = "ccy")
    private String ccy;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "rexchange_rate")
    private Float rexchangeRate;

    @Column(name = "type")
    private String type;

    @Column(name = "payment_type")
    private String paymentType;



}
