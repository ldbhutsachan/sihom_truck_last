package com.ldb.truck.Entity.OrderItem;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "order_item_details")
public class OrderItemReportEntity {

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
    private Float price;



    @Column(name = "saveby", length = 200)
    private String saveBy;

    @Column(name = "savedate")
    @Temporal(TemporalType.DATE)
    private Date saveDate;

    @Column(name = "editby", length = 200)
    private String editBy;

    @Column(name = "editdate")
    @Temporal(TemporalType.DATE)
    private Date editDate;

    @Column(name = "approveby", length = 200)
    private String approveBy;

    @Column(name = "approvedate")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    @Column(name = "status", length = 200)
    private String status;

    @Column(name = "token")
    private String toKen;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(name = "buyer_date")
    @Temporal(TemporalType.DATE)
    private Date buyerDate;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "account_date")
    @Temporal(TemporalType.DATE)
    private Date accountDate;


    @Column(name = "real_qty")
    private int realQty;

    @Column(name = "real_currency")
    private String realCurrency;

    @Column(name = "real_exchange_rate")
    private int realExchangeRate;

    @Column(name = "r_price")
    private Float rPrice;

    @Column(name = "real_price")
    private Float realPrice;



}
