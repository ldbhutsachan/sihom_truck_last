package com.ldb.truck.Model.Login.Inventory.Report_Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportstockReq {
    private String EndDateItemIn_out;
    private String StartDateItemIn_out;

//    private String startDateIn;
//    private String endDateIn;

    private String item_id;
    private String toKen;
    private String branch;
    private String branch_id;
    private String userId;
}
