package com.ldb.truck.Model.Machine;

import lombok.Data;

@Data
public class MachineReq {
    private String toKen;
    private Integer keyId;
    private String mchNo;
    private String mchName;
    private String mchBranchName;
    private String mchModel;
    private String mchProductYear;
    private String createBy;
    private String status;
    private String borNo;

    private Integer time_fix;
    private Integer time_fix_monitor;
    private Integer time_oil_fix;
    private Integer time_oil_fix_mo;

}
