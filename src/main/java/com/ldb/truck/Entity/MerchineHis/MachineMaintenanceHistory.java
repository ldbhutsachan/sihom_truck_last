package com.ldb.truck.Entity.MerchineHis;


import com.ldb.truck.enums.MaintenanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_machine_maintenance_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineMaintenanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "machine_key_id", nullable = false)
    private Integer machineKeyId;

    @Column(name = "mch_no")
    private String mchNo;

    @Column(name = "maintenance_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaintenanceType maintenanceType;

    @Column(name = "machine_mileage")
    private BigDecimal machineMileage;

    @Column(name = "date_change")
    private LocalDate dateChange;

    @Column(name = "date_next")
    private LocalDate dateNext;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "changed_by", nullable = false)
    private String changedBy;

    @Column(name = "remark", columnDefinition = "TEXT")
    private String remark;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }
}