package com.ldb.truck.Entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqItem {
    private int item;
    private int qty;
    private int realQty;

    private String currency;
    private int exchangeRate;
    private Float realPrice;
    private Float price;
    private int shopeId;
    private String orderType;
}
