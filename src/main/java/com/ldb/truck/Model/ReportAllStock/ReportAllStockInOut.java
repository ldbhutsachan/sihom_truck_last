package com.ldb.truck.Model.ReportAllStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAllStockInOut {

    private String detailsId;
    private String itemId;
    private String image;
    private String itemName;
    private String unit;

    private int raisedAmt;
    private int inAmt;
    private int outAmt;
    private int closingAmt;


    private String dateIn;
    private String dateOut;

    private String InByUser;
    private String OutByUser;

    private String usingType;
    private String usingWith;

    private String type;





}
