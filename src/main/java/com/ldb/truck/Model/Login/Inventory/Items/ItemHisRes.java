package com.ldb.truck.Model.Login.Inventory.Items;

import com.ldb.truck.Model.Login.Inventory.OfferPaper.ReportStockModel;
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
public class ItemHisRes {

    private String status;
    private String message;
    private List<ItemHis> data;
    private sumFooterItemHis sumFooter;
}
