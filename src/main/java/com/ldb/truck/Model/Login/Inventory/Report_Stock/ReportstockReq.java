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
    private String startDate;
    private String endDate;
    private String item_id;
    private String toKen;
    private String branch;
    private String userId;
}
