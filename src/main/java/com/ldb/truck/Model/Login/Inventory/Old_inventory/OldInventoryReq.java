package com.ldb.truck.Model.Login.Inventory.Old_inventory;

import com.ldb.truck.Model.Login.Inventory.Fix.FixModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OldInventoryReq {

    private String itemName_Oldwarehouse;
    private String qty_Oldwarehouse;
    private String selectedType_Oldwarehouse;
    private String vehicle_Oldwarehouse;
    private String vehiclefooter_Oldwarehouse;
    private String price_Oldwarehouse;
    private String ImportExpirationDate_Oldwarehouse;
    private String description_Oldwarehouse;
    private String image_Oldwarehouse;
    private String toKen;
    private String userId;
    private String branch;
    private String key_id;
}
