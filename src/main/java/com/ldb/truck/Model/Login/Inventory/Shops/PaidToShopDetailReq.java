package com.ldb.truck.Model.Login.Inventory.Shops;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaidToShopDetailReq {
    private String toKen;
    private String branch;
    private String userId;
    private String offer_code;
    private String po_code;
}
