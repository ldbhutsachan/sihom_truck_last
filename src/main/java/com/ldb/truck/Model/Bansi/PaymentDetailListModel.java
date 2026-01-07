package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class PaymentDetailListModel {
    private Long id;
    private Long keyId;
    private String listName;
    private Double qty;
    private String unit;
    private Double price;
    private Double usd_price;
    private Double reduce;
    private String reduceStatus;
    private Double tax;
    private String taxStatus;
    private String bill_No;
}
