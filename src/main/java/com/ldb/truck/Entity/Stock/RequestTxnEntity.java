package com.ldb.truck.Entity.Stock;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "v_request_item_fix")
public class RequestTxnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer detailId;

    @Column(name = "bill_no", length = 50, nullable = true)
    private String billNo;

    @Column(name = "stockno", length = 50, nullable = true)
    private String stockno;

    @Column(name = "barcode", length = 50, nullable = true)
    private String barcode;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @Column(name = "item_name", length = 255)
    private String itemName;

    @Column(name = "unit", precision = 10, scale = 2)
    private Float unit;

    @Column(name = "size")
    private Integer size;

    @Column(name = "currency", length = 200)
    private String currency;

    @Column(name = "exchange_rate")
    private Integer exchangeRate;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price")
    private Double price;

    @Column(name = "saveby", length = 200)
    private String saveBy;

    @Column(name = "savedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveDate;

    @Column(name = "editby", length = 200)
    private String editBy;

    @Column(name = "editdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

    @Column(name = "approveby", length = 200)
    private String approveBy;

    @Column(name = "approvedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDate;

    @Column(name = "stkh_id")
    private Integer stkhId;

    @Column(name = "token")
    private String toKen;

    @Column(name = "bname", length = 200)
    private String branchName;

    @Column(name = "tel", length = 50)
    private String branchTel;

    @Column(name = "brandid")
    private Integer brandId;

    @Column(name = "brand_name", length = 255)
    private String brandName;

    @Column(name = "itemtypeid")
    private Integer itemtypeId;

    @Column(name = "itemtype_name", length = 255)
    private String itemTypeName;

    @Column(name = "khid")
    private Integer khId;

    @Column(name = "khno", length = 50)
    private String khNo;

    @Column(name = "khname", length = 255)
    private String khName;

    @Column(name = "sole")
    private String sole;

    @Column(name = "solestep")
    private String soleStep;

    @Column(name = "blockno", length = 50)
    private String blockNo;

    @Column(name = "status", length = 200)
    private String status;

    @Column(name = "type", length = 200)
    private String type;

    @Column(name = "bor_no", length = 200)
    private String borNo;

    @Column(name = "total")
    private Float total;

    @Column(name = "image")
    private String image;


    @Column(name = "b_name", length = 200)
    private String bName;

    @Column(name = "blocation", length = 200)
    private String blocation;

}
