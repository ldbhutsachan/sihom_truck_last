package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportStockDetailReq {

    private String startDate;
    private String endDate;
    private String item_id;
    private String userId;
    private String branch;
    private String branch_id;
    private String toKen;

}
