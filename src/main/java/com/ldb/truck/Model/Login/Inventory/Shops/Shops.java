package com.ldb.truck.Model.Login.Inventory.Shops;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shops {
   private Integer shop_id;
   private String shop_name;
   private String address;
   private String phone;
   private String country;
   private String currency;
   private String amount_money;
   private String branch;
   private String toKen;
   private String userId;

}
