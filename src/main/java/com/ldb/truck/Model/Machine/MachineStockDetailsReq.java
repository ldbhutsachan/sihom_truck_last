package com.ldb.truck.Model.Machine;

import lombok.Data;

@Data
public class MachineStockDetailsReq {
    private String toKen;
    private String itemId; // key_id
    private String billNo;
    private String status;

    private String startDate;
    private String endDate;
}
