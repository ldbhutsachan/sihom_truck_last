package com.ldb.truck.Model.Login.Inventory.Fix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FixModelFaso {
    private String h_VICIVLE_NUMBER;
    private String h_BRANCH;
//    private String f_CARD_NO;
    private String total_Price;
    private String dateFix;
    private String totalTimeFix;
    private Double totalFixCost;
}
