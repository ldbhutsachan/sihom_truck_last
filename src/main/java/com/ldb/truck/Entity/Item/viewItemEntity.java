package com.ldb.truck.Entity.Item;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "v_items1")
public class viewItemEntity {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "brand_Name")
    private String brandName;

    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "size")
    private String size;

    @Column(name = "currency")
    private String currency;

    @Column(name = "exchange_rate")
    private Integer exchangeRate;

    @Column(name = "galaty_start_date")
    @Temporal(TemporalType.DATE)
    private Date galatyStartDate;

    @Column(name = "galaty_end_date")
    @Temporal(TemporalType.DATE)
    private Date galatyEndDate;

    @Column(name = "galaty_amt")
    private String galatyAmt;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "totalamt")
    private Float totalamt;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "make_by_id")
    private String makeById;

    @Column(name = "make_date")
    @Temporal(TemporalType.DATE)
    private Date makeDate;

    @Column(name = "approve_by")
    private String approveBy;

    @Column(name = "approve_date")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    @Column(name = "branch_no")
    private Integer branchNo;

    @Column(name = "B_NAME")
    private String bName;

    @Column(name = "itemtypeid")
    private Integer itemtypeid;

    @Column(name = "khid")
    private String khid;

    @Column(name = "khno")
    private String khno;

    @Column(name = "khname")
    private String khname;

    @Column(name = "sole")
    private String sole;

    @Column(name = "solestep")
    private String solestep;

    @Column(name = "blockno")
    private String blockno;

    @Column(name = "itemtype_Name")
    private String itemtype_Name;

    @Column(name = "bor_no")
    private String borNo;

    @Column(name = "bor_name")
    private String borName;

    @Column(name = "bor_location")
    private String blocation;

    @Column(name = "alertqty")
    private Integer alertqty;

    @Column(name = "shopid")
    private Integer shopid;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "ordertype")
    private String ordertype;
}