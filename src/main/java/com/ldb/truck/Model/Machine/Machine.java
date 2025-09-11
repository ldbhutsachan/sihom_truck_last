package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Machine {
    private Integer keyId;
    private String mchNo;
    private String mchName;
    private String mchBranchName;
    private String mchModel;
    private String mchProductYear;
    private LocalDateTime createDate;
    private String createBy;
    private String userLogin;
    private String role;
    private String status;
    private String borNo;
    private String borName;
    private String borLocationName;
}
