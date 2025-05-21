package com.ldb.truck.Entity.Stock;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "stock_item")
public class StockItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Integer stockId;

    @Column(name = "stock_no", length = 50, nullable = true)
    private String stockNo;

    @Column(name = "atm_qty", nullable = false)
    private Integer atmQty;

    @Column(name = "total_price", precision = 10, scale = 2)
    private Float totalPrice;

    @Column(name = "stock_date")
    private String stockDate;

    @Column(name = "currency", length = 200)
    private String currency;

    @Column(name = "exchange_rate")
    private Integer exchangeRate;

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

}
