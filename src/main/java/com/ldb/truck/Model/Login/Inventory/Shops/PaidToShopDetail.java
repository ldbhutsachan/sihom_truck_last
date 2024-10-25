package com.ldb.truck.Model.Login.Inventory.Shops;

import com.ldb.truck.Model.Login.Inventory.OfferPaper.OfferPaperModelFaso;
import com.ldb.truck.Model.Login.Inventory.OfferPaper.sumFooterGroupOfferPaper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaidToShopDetail {
    private String status;
    private String message;
    private List<PaidToShopDetailModel> data;
}
