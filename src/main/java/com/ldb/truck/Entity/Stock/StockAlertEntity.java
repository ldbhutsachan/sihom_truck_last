package com.ldb.truck.Entity.Stock;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "v_alert_stock")
public class StockAlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "itemtypeName")
    private String itemtypeName;

    @Column(name = "khno")
    private String khno;

    @Column(name = "khname")
    private String khname;

    @Column(name = "sole")
    private double sole;

    @Column(name = "blockno")
    private String blockno;

    @Column(name = "unit")
    private String unit;

    @Column(name = "image")
    private String image;


    @Column(name="qty")
    private Integer qty;

    @Column(name = "price")
    private Double price;
}
