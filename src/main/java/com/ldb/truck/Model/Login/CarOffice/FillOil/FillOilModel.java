package com.ldb.truck.Model.Login.CarOffice.FillOil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FillOilModel {
     private String dateFill;
     private String lekmai_dif;
     private String key_id;
     private String carId;
     private Double price;
     private String lekmai;
}
