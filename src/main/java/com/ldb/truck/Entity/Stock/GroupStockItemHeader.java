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
public class GroupStockItemHeader {
    private String billNo;
    private String txnDate;
    private Double qty;
    private Double amount;
    private String status;
    List<StockTxnEntity> details;
}
