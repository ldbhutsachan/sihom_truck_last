package com.ldb.truck.Model.Login.FuelStation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FuelStationOut {
        private int id;
        private String fuelStationId;
        private String fuelStationName;
        private String address;
        private String village;
        private String district;
        private String province;
        private String mobile;
        private String email;
}
