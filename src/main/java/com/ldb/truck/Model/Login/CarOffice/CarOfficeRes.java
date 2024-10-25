package com.ldb.truck.Model.Login.CarOffice;

import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarOfficeRes {
    private String status;
    private String message;
    private List<CarOfficeModel> data;
}
