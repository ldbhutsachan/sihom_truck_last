package com.ldb.truck.Entity.RequestItem;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "request_item_details")
public class RequestItemEbtity {

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

    @Column(name = "status", length = 200)
    private String status;

    @Column(name = "token")
    private String toKen;

    @Column(name = "type")
    private String type;

    @Column(name = "bor_no")
    private String borNo;

    @Column(name = "note")
    private String note;



    @Column(name = "rejectby", length = 200)
    private String rejectby;

    @Column(name = "rejectDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rejectDate;
}
