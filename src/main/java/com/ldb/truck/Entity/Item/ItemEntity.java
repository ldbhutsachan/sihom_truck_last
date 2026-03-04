package com.ldb.truck.Entity.Item;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "item_inventory")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId; // Auto-increment primary key

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

//    @Column(name = "supplier_id", nullable = false)
//    private Integer supplierId;
    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @Column(name = "item_name", length = 255, nullable = false)
    private String item_name;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "currency", nullable = false)
    private String currency; // Enum mapping

    @Column(name = "exchange_rate")
    private Integer exchangeRate;

    @Column(name = "galaty_start_date", nullable = false)
    private String galatyStartDate;

    @Column(name = "galaty_end_date", nullable = false)
    private String galatyEndDate;

    @Column(name = "galaty_amt", length = 50, nullable = false)
    private Integer galatyAmt;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "make_by_id", length = 50, nullable = false)
    private String makeById;

    @Column(name = "make_date",length = 150,nullable = false)
    private Date makeDate;

    @Column(name = "approve_by", length = 50)
    private String approveBy;

    @Column(name = "approve_date")
    private Date approveDate;

//    @Column(name = "branch_no", nullable = false)
//    private String branchNo;

    @Column(name = "itemtypeid", nullable = false)
    private Integer itemtypeid;

    @Column(name = "houseid", nullable = false)
    private Integer houseid;

    @Column(name = "alertqty", nullable = false)
    private Integer alertqty;


    @Column(name = "ordertype", length = 50)
    private String orderType;


}
