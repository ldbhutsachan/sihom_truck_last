package com.ldb.truck.Entity.ItemPayment;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "v_payment_key")
public class VPaymentKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "max_stock_id")
    private String invoiceNo;
}
