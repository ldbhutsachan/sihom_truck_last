package com.ldb.truck.Model.Machine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    private Integer all_Used_Hours;
    private Integer last_engine_Hours;
    private Integer time_fix;
    private Integer time_fix_monitor;
    private Integer totalFixMo;
    private String status_mo;
    private Integer last_hydraulic_Hours;
    private Integer time_oil_fix;
    private Integer time_oil_fix_mo;
    private Integer totalFixMoOil;
    private String status_oil_mo;

    private  String image;
    private String date_in;



    // ✅ List ของ tools ที่จะ return ไปให้ client
    private List<Tool> tools = new ArrayList<>();

    // ✅ Inner class Tool
    @Data
    @NoArgsConstructor // จำเป็นสำหรับ Jackson
    @AllArgsConstructor
    public static class Tool {
        private int id;
        private String tool_name;
        private BigDecimal original_qty;
        private BigDecimal  update_qty;
        private BigDecimal  qty;
        private String status;
        private String mch_no;
        private String unit;
    }
}
