package com.ldb.truck.Entity.Stock;

import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Entity.OrderItem.V_order_item_details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class V_OrderItemHeader {
    private String billNo;
    private String txnDate;
    private Integer qty;
    private String amount;
    private String status;
    List<V_order_item_details> details;
}
