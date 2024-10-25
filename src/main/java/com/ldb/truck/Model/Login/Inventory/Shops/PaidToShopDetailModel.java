package com.ldb.truck.Model.Login.Inventory.Shops;

import com.ldb.truck.Model.Login.Inventory.OfferPaper.OfferPaperModelFaso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaidToShopDetailModel {
    private String pocode;
    private String offcode;
    private String item_name;
    private String qty_offer;
    private String total;
    private String paid,tid,cur,shop_name,dateCreatePO;
    private Float moneyRate;
}
