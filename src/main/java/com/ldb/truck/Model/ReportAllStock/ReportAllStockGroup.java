package com.ldb.truck.Model.ReportAllStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAllStockGroup {

    private String itemId;
    private String image;
    private String itemName;
    private String unit;
    private double price;
    private double outprice;
    private String currency;

    private int raisedAmt;
    private int inAmt;
    private int outAmt;
    private int closingAmt;

    private String borkey;
    private String borname;
    private String firstDateOut;
    private String lastDateOut;
    private String houseNo;
    private String houseName;

//    private String houseNo;
//    private String houseName;

    private List<ReportAllStockInOut> groupList; // รายการย่อยในแต่ละกลุ่ม
}
