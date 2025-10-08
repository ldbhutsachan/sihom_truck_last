package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MachineDetails {
        private Integer detail_id;
        private Integer mch_id;
        private String mch_no;
        private String mch_name;
        private String mch_branch_name;
        private String mch_model;
        private String mch_product_year;
        private String ROLE;
        private String borNo;
        private String borName;
        private String boreLocateName;
        private Integer item_id;
        private String item_name;
        private String unit;
        private String currency;
        private Double price;
        private Integer qty;
        private String request_by;
        private LocalDateTime request_Date;
        private String status;

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
