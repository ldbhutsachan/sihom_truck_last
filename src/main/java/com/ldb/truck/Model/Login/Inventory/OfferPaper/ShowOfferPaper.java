package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import com.ldb.truck.Model.Login.Report.sumFooterGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowOfferPaper {
    private String status;
    private String message;
//    private List<OfferPaperModelFaso> dataSum;
    private List<OfferPaperModelFaso> data;
    private sumFooterGroupOfferPaper sumFooter;
    private sumFooterGroupOfferPaper_Paid_Credit sumFooter_Credit;
}
