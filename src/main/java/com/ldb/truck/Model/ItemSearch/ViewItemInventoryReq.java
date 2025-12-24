package com.ldb.truck.Model.ItemSearch;

import lombok.Data;

@Data
public class ViewItemInventoryReq {
    private String itemId;
    private String toKen;
    private String borNo;
    private String khId;
    private String startDate; // yyyy-MM-dd
    private String endDate;   // yyyy-MM-dd
}