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

    private String itemId;
    private String image;
    private String itemName;
    private String unit;
    private String size;

    private int raisedAmt;
    private int inAmt;
    private int outAmt;
    private int closingAmt;

    private String dateIn;
    private String dateOut;

    private String borkey;
    private String borname;

    private String houseNo;
    private String houseName;


}
