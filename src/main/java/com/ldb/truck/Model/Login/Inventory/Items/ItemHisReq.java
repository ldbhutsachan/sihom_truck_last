package com.ldb.truck.Model.Login.Inventory.Items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemHisReq {
    private String item_id;
    private String startDate;
    private String endDate;
    private String toKen;
    private String branch;
    private String branch_id;
    private String userId;
}
