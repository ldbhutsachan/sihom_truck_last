package com.ldb.truck.Entity.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "attendance_adjustments")
public class AttendanceAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private StaffEntity staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private StaffEntity approvedBy;

    @Column(nullable = false)
    private LocalDate requestDate;      // วันที่ขอแก้ไข

    private LocalDateTime checkInTime;  // เวลา CHECK_IN ที่ขอแก้ไข (null ได้)
    private LocalDateTime checkOutTime; // เวลา CHECK_OUT ที่ขอแก้ไข (null ได้)

    private String reason;

    @Column(nullable = false)
    private String status = "PENDING";  // PENDING, APPROVED, REJECTED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}