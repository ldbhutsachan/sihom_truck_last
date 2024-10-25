package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import com.ldb.truck.Model.Login.Inventory.Shops.Shops;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferpaperRes {
    private String status;
    private String message;
    private List<OfferPaper> data;
}
