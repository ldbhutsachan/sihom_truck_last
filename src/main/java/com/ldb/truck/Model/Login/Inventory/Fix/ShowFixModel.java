package com.ldb.truck.Model.Login.Inventory.Fix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowFixModel {
    private  String fixId;
    private  String h_VICIVLE_NUMBER;
    private  String f_BRANCH;
    private  String qty_Fix;
    private  String total_Price;
    private  String add_on;
    private  String description;
    private  String dateFix;
    private  String fix_Detail;
    private  String location_fix;
    private  String branch_inventory;
    private  String detail_A_lai;
    private  String img;
    private  Double finalTotalPrice;
}
