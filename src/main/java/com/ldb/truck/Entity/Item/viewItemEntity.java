package com.ldb.truck.Entity.Item;

import lombok.Data;
import org.hibernate.type.CurrencyType;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;
@Entity
@Data
@Table(name = "v_items") // Ensure this matches your actual table name
public class viewItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private String itemId; // Auto-increment primary key

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @Column(name = "brand_Name", length = 255, nullable = false)
    private String brandName;

    @Column(name = "supplier_id", nullable = false)
    private Integer supplierId;

    @Column(name = "supplier_name", length = 255, nullable = false)
    private String supplierName;

    @Column(name = "barcode", length = 255, nullable = false)
    private String barcode;

    @Column(name = "item_name", length = 255, nullable = false)
    private String item_name;

    @Column(name = "unit", nullable = false)
    private Integer unit;

    @Column(name = "size", nullable = false)
    private Integer size; // Size might need renaming if backticks are causing issues

    @Column(name = "currency", nullable = false)
    private String currency; // Enum mapping

    @Column(name = "exchange_rate")
    private Integer exchangeRate;

    @Column(name = "galaty_start_date")
    @Temporal(TemporalType.DATE)
    private Date galatyStartDate;

    @Column(name = "galaty_end_date")
    @Temporal(TemporalType.DATE)
    private Date galatyEndDate;

    @Column(name = "galaty_amt", length = 50, nullable = false)
    private String galatyAmt;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "make_by_id", length = 50, nullable = false)
    private String makeById;

    @Column(name = "make_date")
    @Temporal(TemporalType.DATE)
    private Date makeDate;

    @Column(name = "approve_by", length = 50)
    private String approveBy;

    @Column(name = "approve_date")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    @Column(name = "branch_no", nullable = false)
    private Integer branchNo;

    @Column(name = "B_NAME", length = 255)
    private String bName;

    @Column(name = "itemtypeid", nullable = false)
    private Integer itemtypeid;

    @Column(name = "khid", nullable = false)
    private String khid;

    @Column(name = "khno", nullable = false)
    private String khno;

    @Column(name = "khname", nullable = false)
    private String khname;

    @Column(name = "sole", nullable = false)
    private String sole;

    @Column(name = "solestep", nullable = false)
    private String solestep;

    @Column(name = "blockno", nullable = false)
    private String blockno;

    @Column(name = "itemtype_Name", nullable = false)
    private String itemtype_Name;

    @Column(name = "toKen", nullable = false)
    private String toKen;
}