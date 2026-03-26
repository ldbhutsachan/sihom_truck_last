package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    private  String image;
    //add new
    private String price;
    private String currency;
//    private String date_in;
    private LocalDate date_in;
    private String remark; // เพิ่มตรงนี้

    // สำหรับ tools
    private List<ToolReq> tools;

    @Data
    public static class ToolReq {
        private Long id;
        private String toolName;
        private Integer qty;
        //add new
        private String unit;
    }
}
