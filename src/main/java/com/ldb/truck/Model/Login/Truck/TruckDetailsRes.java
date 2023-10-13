package com.ldb.truck.Model.Login.Truck;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TruckDetailsRes {
    private String status;
    private String message;
    private List<TruckDetails> data;
}
