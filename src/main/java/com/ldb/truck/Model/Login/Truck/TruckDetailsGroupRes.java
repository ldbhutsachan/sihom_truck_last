package com.ldb.truck.Model.Login.Truck;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TruckDetailsGroupRes {
    private String status;
    private String message;
    private List<TruckDetailsGroupDataDetails> data;

    private String carGiveTotal;
    private String carPayTotal;
    private String kumLaiyTotal;

    private String totalPriceNummun;
    private String totalBialieng;

    private String totalRow;
    private String totalFuel;


}
