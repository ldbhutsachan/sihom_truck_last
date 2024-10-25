package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import com.ldb.truck.Model.Login.Inventory.CUR.ReportOfferPaperModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportShowOfferPaper {
    private String status;
    private String message;
    private List<ReportOfferPaperModel> data;
    private sumFooterGroupOfferReportCurrency sumFooter_Currency;
//    private sumFooterGroupOfferReportCurrency sumFooter_CurrencyTHB;
//    private sumFooterGroupOfferReportCurrency sumFooter_CurrencyLAK;
//    private sumFooterGroupOfferReportCurrency sumFooter_CurrencyTHB;
//    private sumFooterGroupOfferReportCurrency sumFooter_CurrencyLAK;
}
