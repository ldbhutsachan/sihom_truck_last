package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportStockRes {
    private String status;
    private String message;
    private List<ReportStockModel> data;
    private sumFooterGroupTotalValue sumFooter;
}
