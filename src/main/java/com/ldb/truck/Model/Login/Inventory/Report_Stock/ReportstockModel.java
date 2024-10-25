package com.ldb.truck.Model.Login.Inventory.Report_Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportstockModel {
    private String item_id,item_name,unit,img,dateOut,dateIn;
    private Double qty_stock,qty_out,qty_in,yordyokma;

}
