package com.ldb.truck.Entity.ItemPayment;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@Table(name = "v_payment_item")
public class ItemPaymentViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_no")
    private Long paymentNo;

    @Column(name = "payment_saveby")
    private String paymentSaveBy;

    @Column(name = "payment_savedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentSaveDate;

    @Column(name = "payby")
    private String payBy;

    @Column(name = "pay_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;

    @Column(name = "detail_id")
    private Integer detail_id;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "borname")
    private String borname;

    @Column(name = "blocation")
    private String blocation;


    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "unit")
    private String unit;

    @Column(name = "size")
    private String size;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "total")
    private Float total;

    @Column(name = "saveby")
    private String saveBy;

    @Column(name = "savedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveDate;

    @Column(name = "approveby")
    private String approveBy;

    @Column(name = "approvedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDate;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "itemtype_name")
    private String itemTypeName;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "ccy")
    private String ccy;

    @Column(name = "exp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp;


    @Column(name = "totalcal")
    private Float totalcal;

    @Column(name = "qtycal")
    private Integer qtycal;


    @Column(name = "amountscal")
    private Float amountscal;
}
