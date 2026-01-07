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
public class StockItemAuthReq {
    String toKen;
    String userId;
    String billNo;
    String status;
    String remark;
    String orderStatus;
    String role;
    String placeBuy;
    String shopeId;
    String typeOfPay;
    String datePay;
    String itemArriveDate;
    List<StockItemAuthModel> detailId;
}
