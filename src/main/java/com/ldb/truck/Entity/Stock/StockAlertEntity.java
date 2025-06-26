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
    private String sole;

    @Column(name = "blockno")
    private String blockno;

    @Column(name = "unit")
    private String unit;

    @Column(name = "image")
    private String image;


    @Column(name="qty")
    private Integer qty;

    @Column(name = "price")
    private Float price;

    @Column(name = "branch_no")
    private String branchNo;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "bor_no")
    private String borNo;

    @Column(name = "bor_name")
    private String borName;

    @Column(name = "bor_location")
    private String bLocation;

    @Column(name = "alertqty")
    private Integer alertqty;

    @Column(name = "qtytotal")
    private Integer qtytotal;
}
