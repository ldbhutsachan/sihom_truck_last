package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ItemForAccountingDto {

    private Integer borkey;
    private String borname;

    private Integer housid;
    private String housname;

    private Integer detailId;
    private String billNo;

    private Integer itemId;
    private String itemName;
    private String unit;
    private String size;

    private String currency;
    private Integer exchangeRate;

    private Integer qty;
    private Double toalAmount;

    private String status;

    private String saveby;
    private LocalDateTime savedate;

    private String buyby;
    private LocalDateTime buydate;

    private String editby;
    private LocalDateTime editdate;

    private String approveby;
    private LocalDateTime approvedate;

    private String acceptby;
    private LocalDate acceptdate;

    private String placeBuy;

    private Integer shopId;
    private String shopName;

    private String typeOfOrder;
    private String datePay;
    private String itemArriveDate;
    private String payStatus;

    private String imagefile;
}
