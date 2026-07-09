package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.math.BigDecimal;
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
    private String remark;

    private BigDecimal machine_mileage_now;
    private BigDecimal machine_mileage_next;
    private BigDecimal machine_mileage_hydrolic;
    private LocalDate machine_mileage_status;
    private LocalDate dateChangeLeean;
    private LocalDate dateChangeLeeanNext;
    private LocalDate changeleean_status;
    private LocalDate dateleanGia;
    private LocalDate dateleanGiaNextday;
    private LocalDate leangia_status;
    private LocalDate dateleanFuengThaiy;
    private LocalDate fuengthaiy_status;
    private LocalDate startdate_kongnam;
    private LocalDate enddate_kongnam;
    private LocalDate kongnam_status;
    private LocalDate hydraulic_date;
    private LocalDate hydraulic_nextdate;
    private LocalDate hydraulic_status;
    private LocalDate notifyStatus;

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
