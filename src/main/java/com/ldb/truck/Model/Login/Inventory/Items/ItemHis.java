package com.ldb.truck.Model.Login.Inventory.Items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemHis {
    private String item_name;
    private String img;
    private Double item_qty;
    private String date_import;
    private String item_id;
}
