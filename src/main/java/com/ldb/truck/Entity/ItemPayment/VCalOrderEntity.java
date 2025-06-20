package com.ldb.truck.Entity.ItemPayment;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "v_sum_order_item")
public class VCalOrderEntity {

    @Id
    @Column(name = "bill_no")
    private String billNo; // bill_no is likely provided externally and not auto-generated

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "payment_total")
    private Float paymentTotal;
}