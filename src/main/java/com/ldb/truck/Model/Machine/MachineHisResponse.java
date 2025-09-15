package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.util.List;

@Data
public class MachineHisResponse {
    private String status;
    private String message;
    private List<MachineHis> data;
}
