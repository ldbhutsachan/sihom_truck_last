package com.ldb.truck.Model.Machine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    private Integer keyId;
    private String mchNo;
    private String mchName;
    private String price;
    private String currency;
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

    private Integer time_fix;
    private Integer time_fix_monitor;
    private Integer time_oil_fix;
    private Integer time_oil_fix_mo;
    private Integer totalFixMo;
    private Integer totalFixMoOil;

    private  String image;
    private String date_in;

    private String status_mo;
    private String status_oil_mo;


    // ✅ List ของ tools ที่จะ return ไปให้ client
    private List<Tool> tools = new ArrayList<>();

    // ✅ Inner class Tool
    @Data
    @NoArgsConstructor // จำเป็นสำหรับ Jackson
    @AllArgsConstructor
    public static class Tool {
        private String tool_name;
        private int qty;
        private int id;
        private String mch_no;
        private String unit;
    }
}
