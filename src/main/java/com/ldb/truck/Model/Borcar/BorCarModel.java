package com.ldb.truck.Model.Borcar;

import lombok.Data;

import java.util.Collection;

@Data
public class BorCarModel {
    private Integer car_id;
    private String car_number;
    private String bor_no;
    private String bor_name;
    private Double sumlak = 0.0;
    private Double sumusd = 0.0;
    private Double sumthb = 0.0;
    Collection<GroupList> groupList = null;

    @Data
    public static class GroupList {
//        private String license_plate_end;
//        private String license_plate_start;
        private String saveby_name;
        private String savedate;
        private String approveby;
        private String approvedate;
//        private String bor_no;
//        private String bor_name;
        private String bill_no;
        private String item_id;
        private String item_name;
        private String unit;
        private String price;
        private String currency;
        private String qty;
        private String total;
    }
}

