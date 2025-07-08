package com.ldb.truck.Model.ReportAllStock;

import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAllStock {
    private String image;
    private String itemName;

    private String itemId;

    private String type;
    private String typeName;

    private String borNo;

    private String borName;
    private String blocation;

    private String txnDateIn;
    private String txnDateOut;

    private Integer amt;
    private Double price;

    private Integer total;
    private Integer amtIn;
    private Double priceIn;
    private String totalIn;

    private Integer amtOut;
    private Double priceOut;
    private String totalOut;


    private String userStockIn;
    private String userStockOut;

    private String reqName;
    private String borType;

}
