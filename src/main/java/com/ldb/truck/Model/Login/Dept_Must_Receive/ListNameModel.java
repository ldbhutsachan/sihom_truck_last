package com.ldb.truck.Model.Login.Dept_Must_Receive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListNameModel {
    private String key_id;
    private String listName;
    private String quotation_code;
    private String amount;
    private String price;
    private String unit;
    private Double totalPrice;
    private Double totalPrice1;

}
