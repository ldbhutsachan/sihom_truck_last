package com.ldb.truck.Model.Login.Inventory.Old_inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OldInventoryModel {

    private String key_id;
    private String itemName_Oldwarehouse;
    private String qty_Oldwarehouse;
    private String selectedType_Oldwarehouse;
    private String vehicle_Oldwarehouse;
    private String vehiclefooter_Oldwarehouse;
    private Double price_Oldwarehouse;
    private String ImportExpirationDate_Oldwarehouse;
    private String description_Oldwarehouse;
    private String image_Oldwarehouse;
    private String cur;
}
