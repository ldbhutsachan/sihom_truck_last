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
public class ShowOfferPaperDetail {
    private String status;
    private String message;
    private List<OfferPaperModelFaso> data;
    private sumFooterGroupOfferPaper sumFooter;
}
