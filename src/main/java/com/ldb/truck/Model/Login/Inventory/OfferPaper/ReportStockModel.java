package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportStockModel {
    private String item_name;
    private String qty;
    private String img;
    private String item_id;
    private String unit;
    private String size;
    private String brand;
    private String ber;
    private Double totalValue;
    private String unitPirce;
    private String sumUnitWithPrice;
    private String currency;


    private String dateBuy;
    private String shop_name;
    private String img1,img2,img3,img4,img5,img6,img7,img8,img9;
    private String item_name1,item_name2,item_name3,item_name4,item_name5,item_name6,item_name7,item_name8,item_name9;
    private String unit_price,unit_price1,unit_price2,unit_price3,unit_price4,unit_price5,unit_price6,unit_price7,unit_price8,unit_price9;
    private String qty_offer,qty_offer1,qty_offer2,qty_offer3,qty_offer4,qty_offer5,qty_offer6,qty_offer7,qty_offer8,qty_offer9;
    private String totalMoney,totalMoney1,totalMoney2,totalMoney3,totalMoney4,totalMoney5,totalMoney6,totalMoney7,totalMoney8,totalMoney9;
}
