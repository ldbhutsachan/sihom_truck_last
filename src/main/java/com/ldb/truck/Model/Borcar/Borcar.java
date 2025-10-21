package com.ldb.truck.Model.Borcar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borcar {
    private Integer key_id;
    private String car_id;
    private String car_number;
//    private String license_plate_end;
//    private String license_plate_start;
    private String saveby_name;
    private String savedate;
    private String approveby;
    private String approvedate;
    private String bor_no;
    private String bor_name;
    private String bill_no;
    private String item_id;
    private String item_name;
    private String unit;
    private String price;
    private String currency;
    private String qty;
    private String total;

}
