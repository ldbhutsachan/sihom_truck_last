package com.ldb.truck.Entity.Stock;

import com.ldb.truck.Entity.OrderItem.OrderAuthEntity;
import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAuthHeader {
    private String billNo;
    private String txnDate;
    private Integer qty;
    private String amount;
    private String status;
    List<OrderAuthEntity> details;
}
