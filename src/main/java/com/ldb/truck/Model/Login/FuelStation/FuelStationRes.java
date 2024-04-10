package com.ldb.truck.Model.Login.FuelStation;

import com.ldb.truck.Model.Login.customer.CustomerOut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FuelStationRes {
    private String status;
    private String message;
    private List<FuelStationOut> data;
}
