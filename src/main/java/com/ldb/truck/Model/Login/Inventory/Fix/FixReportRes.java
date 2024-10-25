package com.ldb.truck.Model.Login.Inventory.Fix;

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
public class FixReportRes {
    private String status;
    private String message;
    private List<FixModelFaso> data;
    private sumFooterGroupFix sumFooter;
}
