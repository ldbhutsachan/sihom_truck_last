package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MachineToolHisRequest {
    private String token;
    private Integer toolId;
    private BigDecimal qty;
}