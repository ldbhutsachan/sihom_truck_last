package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoveToStockReq {

    private String qty_offer,qty_offer1,qty_offer2,qty_offer3,qty_offer4,qty_offer5,qty_offer6,qty_offer7,qty_offer8,qty_offer9;
    private String item_id,item_id1,item_id2,item_id3,item_id4,item_id5,item_id6,item_id7,item_id8,item_id9;
    private String offer_CODE;
    private String stock_status;
    private String stockStatus;
    private String userId;
    private String branch;
    private String branch_id;
    private String toKen;
    private String dateImport;
    private String ItemName,ItemName2,ItemName3,ItemName4,ItemName5,ItemName6,ItemName7,ItemName8,ItemName9;

    private String size,size1,size2,size3,size4,size5,size6,size7,size8,size9;
    private String brand,brand1,brand2,brand3,brand4,brand5,brand6,brand7,brand8,brand9;
    private String ber,ber1,ber2,ber3,ber4,ber5,ber6,ber7,ber8,ber9;

}
