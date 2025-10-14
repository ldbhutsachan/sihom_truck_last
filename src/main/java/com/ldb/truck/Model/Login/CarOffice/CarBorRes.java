package com.ldb.truck.Model.Login.CarOffice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBorRes {
    private String status;
    private String message;
    private List<CarBorModel> data;
}
