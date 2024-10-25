package com.ldb.truck.Model.Login.Inventory.Fix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FixModel {
    private String fixId;
    private String header_id;
    private String footer_id;
    private String description;
    private String item_id;
    private String qty_Fix;
    private String total_Price;
}
