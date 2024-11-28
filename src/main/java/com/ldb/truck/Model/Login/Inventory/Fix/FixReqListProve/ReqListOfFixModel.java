package com.ldb.truck.Model.Login.Inventory.Fix.FixReqListProve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqListOfFixModel {
    private  String key_id;
    private  String header_id;
    private  String footer_id;
    private  String item_name;
    private  String item_id;
    private  String branch_inventory;
    private  String total_Price;
    private  String qty_Fix;
    private  String description;
    private  String dateFix;
    private  String fix_Detail;
    private  String location_fix;
    private  String approve_status;
    private  String new_status;
    private  String h_VICIVLE_NUMBER;
    private  String f_BRANCH;
}
