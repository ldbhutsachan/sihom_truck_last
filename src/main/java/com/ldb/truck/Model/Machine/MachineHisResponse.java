package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MachineHisResponse {
    private String status;
    private String message;
    private List<MachineHis> data;
    // NEW SUMMARY
    private Double totalDigMetter;
    private Double totalOilLiter;
    private BigDecimal totalTimeTotal;
}
