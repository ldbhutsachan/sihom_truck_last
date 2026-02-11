package com.ldb.truck.Model.Login.Inventory.Report_Stock;

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
public class ReportstockRes {
    private String status;
    private String message;
//    private List<ReportstockModel> data;
    private List<ReportStockGroupRes> data;
    private sumFooterGroup sumFooter;
    private sumFooterGroup2 sumFooter2;
}
