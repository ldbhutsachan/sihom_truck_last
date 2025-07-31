package com.ldb.truck.Entity.OrderItem;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "v_order_item")
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

    @Column(name = "status", length = 200)
    private String status;

    @Column(name = "token")
    private String toKen;

    @Column(name = "total")
    private Float total;

    @Column(name = "amount")
    private Float amountCurrency;

    @Column(name = "toal_amount")
    private Float totalAmountCurrency;

    @Column(name = "image")
    private String image;

    //===make by
    @Column(name = "saveby", length = 200)
    private String saveBy;

    @Column(name = "savedate")
    @Temporal(TemporalType.DATE)
    private Date saveDate;
    //======edit
    @Column(name = "editby", length = 200)
    private String editBy;

    @Column(name = "editdate")
    @Temporal(TemporalType.DATE)
    private Date editDate;
    //===approve
    @Column(name = "approveby", length = 200)
    private String approveBy;

    @Column(name = "approvedate")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    //===reject
    @Column(name="rejectby")
    private String rejectBy;

    @Column(name= "rejectbyDate")
    @Temporal(TemporalType.DATE)
    private Date rejectDate;

    //*****
    //===approve
    @Column(name = "buyerby", length = 200)
    private String buyerBy;

    @Column(name = "buyerdate")
    @Temporal(TemporalType.DATE)
    private Date buyerDate;
    //****
    //===approve
    @Column(name = "accountby", length = 200)
    private String accountBy;

    @Column(name = "accountdate")
    @Temporal(TemporalType.DATE)
    private Date accountDate;

    //===acept
    @Column(name = "acceptby", length = 200)
    private String acceptBy;

    @Column(name = "acceptdate")
    @Temporal(TemporalType.DATE)
    private Date acceptDate;


    //*****ສາຂາໃດ
    @Column(name="branchno")
    private String branchNo;

    @Column(name="bname")
    private String branchName;

    //*******ບໍ່ໃດ
    @Column(name="borkey")
    private String borkey;

    @Column(name="borname")
    private String borame;

    @Column(name="remark")
    private String remark;

}
