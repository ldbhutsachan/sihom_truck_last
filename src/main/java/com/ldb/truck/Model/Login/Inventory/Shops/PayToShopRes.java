package com.ldb.truck.Model.Login.Inventory.Shops;

import com.ldb.truck.Model.Login.Inventory.OfferPaper.OfferPaper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayToShopRes {
    private String status;
    private String message;
    private List<PayToShopReq> data;
}
