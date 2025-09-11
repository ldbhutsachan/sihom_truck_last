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

}
