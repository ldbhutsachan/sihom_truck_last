package com.ldb.truck.Entity.RequestItem;

import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Entity.Stock.RequestTxnEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestItemHeader {
    private String billNo;
    private String txnDate;
    private Integer qty;
    private Double amount;
    private String status;
    List<RequestTxnEntity> details;
}
