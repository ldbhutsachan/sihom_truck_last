package com.ldb.truck.Entity.OrderItem;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    private Integer item;
    private Integer qty;
}