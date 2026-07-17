package com.ldb.truck.Entity.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_logs_backup", indexes = {
        @Index(name = "idx_original_log_id", columnList = "original_log_id"),
        @Index(name = "idx_backup_created_at", columnList = "createdAt")
})
@Data
@NoArgsConstructor
public class AttendanceLogBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_log_id")
    private Long originalLogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private StaffEntity staff;

    @Column(nullable = false)
    private String checkType;

    @Column(nullable = false)
    private LocalDateTime checkTime;

    private String remark;

    private LocalDateTime createdAt;

    private String ipAddress;

    private String macAddress;

    @Column(name = "backed_up_at")
    private LocalDateTime backedUpAt;

    @PrePersist
    public void prePersist() {
        this.backedUpAt = LocalDateTime.now();
    }
}
