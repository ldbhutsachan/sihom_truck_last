package com.ldb.truck.Model.Login.AssetsOffice;

import com.ldb.truck.Model.Login.CarOffice.CarOfficeModel;
import com.ldb.truck.Model.Login.Inventory.Report_Stock.sumFooterGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetsOfficeRes {
    private String status;
    private String message;
    private List<AssetsOfficeModel> data;
    private sumFooterGroupAsset resFooter;
    private Map<String, String> currencyTotals;
}
