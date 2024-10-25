package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferPaper {
    private String KEY_ID;
//    private String item_id;
//    private String header_id;
//    private String footer_id;
//    private String shop_id;
    private String unit_price;
    private String qty_offer;
//    private String noNameShop;
    private String totalMoney;
    private String description;
    private String offerManName;
    private String job;
//    private String userId;
    private String offer_CODE;

}
