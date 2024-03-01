package com.ldb.truck.Model.Login.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationReq {
    private String province;
    private String detail;
    private String id;
    private String userId;
    private String branch;
    private String toKen;

}
