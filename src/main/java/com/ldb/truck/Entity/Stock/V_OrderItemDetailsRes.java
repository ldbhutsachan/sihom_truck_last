package com.ldb.truck.Entity.Stock;

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
public class V_OrderItemDetailsRes {
    private String status;
    private String message;
    List<V_OrderItemHeader> dataResponse;
}
