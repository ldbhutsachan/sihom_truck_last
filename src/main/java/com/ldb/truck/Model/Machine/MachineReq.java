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

    private String drillrod_pq3;
    private String  drillrod_hq3;
    private String core_barrelhq3_1_5m;
    private String backReamer;
    private String caphq;
    private String drillbit_hq3;
    private String water_pump;
    private String pipewrench24;
    private String pipewrench36;
    private String pipewrench48;
    private String monkey_wrench_hq3;
    private String rodpuller;
    private String adapter3in1_hq;
    private String lifting_plug_hq;
    private String circuit_breaker;
    private String led_light;
    private String  fuel;

}