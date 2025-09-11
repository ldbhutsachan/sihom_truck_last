package com.ldb.truck.Entity.Stock;

import com.ldb.truck.Entity.OrderItem.OrderReqItem;
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
    String remark;
    String pathApi;
    String role;
    List<Long> detailId;

}


