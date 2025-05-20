package com.ldb.truck.Model.Login.Inventory.Shops;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayToShopReq {
    private String pocode;
    private String total;
    private Double paid;
    private String tid;
    private String toKen;
    private String branch;
    private String userId;
    private String moneyRate;
    private String offer_CODE;
    private String datePay;
    private String realTotalMoney;
    private String cur;
}
