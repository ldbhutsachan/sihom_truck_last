package com.ldb.truck.Entity.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockItemDetailsReq {
    String toKen;
    String userId;
    String billNo;
    String status;
    String pathApi;
    String role;
    List<Long> detailId;
}
