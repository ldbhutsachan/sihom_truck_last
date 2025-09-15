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
  //  a.,a.time_fix_monitor,a.time_oil_fix,a.time_oil_fix_mo
    private Integer time_fix;
    private Integer time_fix_monitor;

    private Integer time_oil_fix;
    private Integer time_oil_fix_mo;

    private Integer totalFixMo;
    private Integer totalFixMoOil;


    private String status_mo;
    private String status_oil_mo;
}
