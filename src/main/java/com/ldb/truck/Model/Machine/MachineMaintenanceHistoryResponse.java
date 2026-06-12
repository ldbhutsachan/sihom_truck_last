package com.ldb.truck.Model.Machine;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class MachineMaintenanceHistoryResponse {

    private Long id;
    private Integer machineKeyId;
    private String mchNo;
    private String maintenanceType;
    private BigDecimal machineMileage;
    private LocalDate dateChange;
    private LocalDate dateNext;
    private String filePath;
    private String changedBy;
    private String remark;
    private LocalDateTime createdDate;
}
