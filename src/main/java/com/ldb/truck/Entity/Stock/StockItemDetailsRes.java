package com.ldb.truck.Entity.Stock;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockItemDetailsRes {
   private String status;
   private String message;

   private String logo;
   List<GroupStockItemHeader> dataResponse;
}