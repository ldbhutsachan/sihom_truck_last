package com.ldb.truck.Model.Machine;

import lombok.Data;
import java.util.List;

@Data
public class MachineReportResposne {
    private String status;
    private String message;
    private List<MachineReport> data;
}
