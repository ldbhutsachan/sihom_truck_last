package com.ldb.truck.Entity.OrderItem;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "order_item_details")
public class V_order_item_details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer detailId;

    @Column(name = "bill_no", length = 50, nullable = true)
    private String billNo;

    @Column(name = "barcode", length = 50, nullable = true)
    private String barcode;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "unit", precision = 10, scale = 2)
    private String unit;

    @Column(name = "size")
    private String size;

    @Column(name = "currency", length = 200)
    private String currency;

    @Column(name = "exchange_rate")
    private Integer exchangeRate;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price")
    private Float price;

    @Column(name = "saveby", length = 200)
    private String saveBy;

    @Column(name = "savebyName", length = 200)
    private String savebyName;

    @Column(name = "savedate")
    @Temporal(TemporalType.DATE)
    private Date saveDate;

    @Column(name = "editby", length = 200)
    private String editBy;

    @Column(name = "edditbyName", length = 200)
    private String edditByName;

    @Column(name = "editdate")
    @Temporal(TemporalType.DATE)
    private Date editDate;

    @Column(name = "approveby", length = 200)
    private String approveBy;

    @Column(name = "approvebyName", length = 200)
    private String approvebyName;

    @Column(name = "approvedate")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    @Column(name = "status", length = 200)
    private String status;

    @Column(name = "token")
    private String toKen;

    @Column(name = "total")
    private Float total;

    @Column(name = "image")
    private String image;


    @Column(name="rejectby")
    private String rejectBy;


    @Column(name="rejectbyName")
    private String rejectByName;

    @Column(name= "rejectbyDate")
    @Temporal(TemporalType.DATE)
    private Date rejectDate;


}
