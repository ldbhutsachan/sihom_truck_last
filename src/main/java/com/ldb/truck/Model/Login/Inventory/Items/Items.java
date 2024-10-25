package com.ldb.truck.Model.Login.Inventory.Items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    private String item_id;
    private String itemName;
    private String unit;
    private String unit_price;
    private Integer qty;
    private String img;
    private String ber;
    private String size;
    private String brand;
}
