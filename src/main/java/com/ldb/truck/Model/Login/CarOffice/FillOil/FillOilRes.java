package com.ldb.truck.Model.Login.CarOffice.FillOil;

import com.ldb.truck.Model.Login.Inventory.Shops.Shops;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FillOilRes {
    private String status;
    private String message;
    private List<FillOilModel> data;
    private sumFooterGroupHisOil sumFooter;
}
