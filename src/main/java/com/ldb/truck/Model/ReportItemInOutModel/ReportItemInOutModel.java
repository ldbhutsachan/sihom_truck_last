package com.ldb.truck.Model.ReportItemInOutModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportItemInOutModel {
    String itemId;
    String itemName;
    String image;
    String unitName;
    String raisedAmt;
    String inAmt;
    String outAmt;
    String Amt;
    String inDate;
    String outDate;
    String userImport;
    String userOut;
    String typeUsage;
    String usageWith;
}
