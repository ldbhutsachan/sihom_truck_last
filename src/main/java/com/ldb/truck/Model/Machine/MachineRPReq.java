package com.ldb.truck.Model.Machine;

import lombok.Data;

@Data
public class MachineRPReq {
    private String toKen;
    private String mch_no;
    private String borNo;
    private String startDate;
    private String endDate;
    private String status;
}
