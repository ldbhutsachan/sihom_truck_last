package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderReq {
    private String kEY_ID;
    private String pocode;
    private String offer_CODE;
    private String total;
    private String paid;
    private String tid;
    private String cur;
    private String dateCreatePO;
    private String statusPO;
    private String statusStock;
    private String userId;
    private String branch;
    private String toKen;
    private String date;

}
