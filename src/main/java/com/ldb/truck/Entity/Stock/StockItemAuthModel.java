package com.ldb.truck.Entity.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockItemAuthModel {
    private Integer itemId;
    private BigDecimal qty;
    private BigDecimal amount;
    private Float total;



    private String currency;
    private BigDecimal realPrice;
    private int exchangeRate;
}
