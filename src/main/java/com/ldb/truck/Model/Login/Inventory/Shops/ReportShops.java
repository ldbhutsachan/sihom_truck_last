package com.ldb.truck.Model.Login.Inventory.Shops;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportShops {
   private String pocode;
   private String oFFER_CODE;
   private String item_name;
   private String qty_offer;
   private String total;
   private String paid;
   private String tid;
   private String cUR;
   private String shop_name;
   private String dateCreatePO;
   private String timeToPay;
   private String StatusNy;
   private Float moneyRate;
//   private String toKen;
//   private String userId;

}
