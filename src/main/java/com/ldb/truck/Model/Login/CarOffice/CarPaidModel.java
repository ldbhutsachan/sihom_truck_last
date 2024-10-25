package com.ldb.truck.Model.Login.CarOffice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarPaidModel {
    private String img;
    private String license_plate;
    private String pdfFile;
    private String cur;
    private String pricePaid;
    private String dateCreate;
}
