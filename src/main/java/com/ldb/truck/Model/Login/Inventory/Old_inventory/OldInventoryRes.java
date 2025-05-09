package com.ldb.truck.Model.Login.Inventory.Old_inventory;

import com.ldb.truck.Model.Login.Inventory.Fix.FixModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OldInventoryRes {
    private String status;
    private String message;
    private List<OldInventoryModel> data;
    private Map<String, String> currencyTotals;
}
