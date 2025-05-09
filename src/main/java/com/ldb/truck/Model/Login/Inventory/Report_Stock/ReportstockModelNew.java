package com.ldb.truck.Model.Login.Inventory.Report_Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportstockModelNew {
    private String item_id;
    private String item_name;
    private String unit;
    private String date_offer;
    private String nameOfferMan;
    private Double qtyOut;
    private Double qtyIn;
    private Double real_totalmonney;
    private String currency;
    private String img;
    private String dateFix;
    private Double CurrentQty;
    private Double QtyStock;
    private String DescriptionFix;
    private String Branch;
    private String CarNumber;
    private String OfferCode;
    private String NewStatus;
    private String ApproveStatus;
    private String FixDetail;
    private String DescriptionOffer;

//    private Double qty_stock;
//    private Double yodyokma;

}
