package com.ldb.truck.Model.Login.Inventory.Shops;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopsMustPayRes {
   private String status;
   private String message;
   private List<ReportShops> data;

}
