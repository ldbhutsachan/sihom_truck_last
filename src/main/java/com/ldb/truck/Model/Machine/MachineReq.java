package com.ldb.truck.Model.Machine;

import lombok.Data;
import lombok.Setter;

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

}
