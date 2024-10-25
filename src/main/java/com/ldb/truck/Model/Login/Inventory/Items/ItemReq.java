package com.ldb.truck.Model.Login.Inventory.Items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemReq {
   private Integer item_id;
   private String itemName;
   private String unit;
   private String unit_price;
   private Integer qty;
   private String img;
   private String branch;
   private String toKen;
   private String userId;

}
