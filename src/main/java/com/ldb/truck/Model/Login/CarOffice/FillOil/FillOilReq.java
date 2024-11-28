package com.ldb.truck.Model.Login.CarOffice.FillOil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FillOilReq {
     private String dateFill;
     private String key_id;
     private String carId;
     private String price;
     private String lekmai;
     private String userId;
     private String toKen;
     private String branch;
}
