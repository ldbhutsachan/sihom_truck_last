package com.ldb.truck.Entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqItem {
    private int item;
    private BigDecimal qty;
    private int realQty;

    private String currency;
    private int exchangeRate;
    private Float realPrice;
    private BigDecimal price;
    private int shopeId;
    private String orderType;
    private String placeBuy;
}
