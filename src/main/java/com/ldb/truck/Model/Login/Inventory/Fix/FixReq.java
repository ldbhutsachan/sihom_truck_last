package com.ldb.truck.Model.Login.Inventory.Fix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FixReq {
    private String header_id;
    private String footer_id;
    private String description;
    private String item_id;
    private String qty_Fix;
    private String total_Price;
    private String toKen;
    private String branch;
    private String branch_inventory;
    private String userId;
    private String sangkao_status;
    private String dateFix;

    private String startDate;
    private String endDate;
    private String fixId;
    private String add_on;

    private String location_fix;
    private String fix_Detail;
    private String item_name;
    private String key_id;
    private String status;
    private String New_status;
}
