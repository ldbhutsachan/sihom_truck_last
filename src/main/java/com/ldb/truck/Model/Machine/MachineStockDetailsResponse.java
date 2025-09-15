package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.util.List;

@Data
public class MachineStockDetailsResponse {
    private String status;
    private String message;
    private List<MachineStockDetails> data;
}
