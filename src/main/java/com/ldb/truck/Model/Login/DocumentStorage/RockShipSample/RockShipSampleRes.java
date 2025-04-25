package com.ldb.truck.Model.Login.DocumentStorage.RockShipSample;

import com.ldb.truck.Model.Login.DocumentStorage.BouangModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RockShipSampleRes {
    private String status;
    private String message;
    private List<RockShipSampleModel> data;
}
