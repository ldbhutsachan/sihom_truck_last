package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.util.List;

@Data
public class MachineResponse {
    private String status;
    private String message;
    private List<Machine> data;
}
