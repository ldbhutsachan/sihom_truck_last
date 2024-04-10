package com.ldb.truck.Model.Login.FuelStation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FuelStationReq {
        private int id;
        private String fuelStationId;
        private String toKen;
        private String userId;
        private String branch;
        private String fuelStationName;
        private String address;
        private String village;
        private String district;
        private String province;
        private String mobile;
        private String email;

        private String del;
        private String key_id;

//        private String dels;
//        private String keyIds;
        private List<String> dels;
        private List<String> keyIds;

}
