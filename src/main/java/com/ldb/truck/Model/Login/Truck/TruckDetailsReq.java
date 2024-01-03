package com.ldb.truck.Model.Login.Truck;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TruckDetailsReq {
private String carLodNo;
private String startDate;
private String endDate;
}
