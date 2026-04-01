package com.ldb.truck.Entity.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "company_staffs")
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String staffCode;

    @Column(nullable = false)
    private String passwordHash;

    private String phone;

    @Column(nullable = false)
    private String role = "USER";

    @Column(nullable = false)
    private String status = "ACTIVE";

    private String token;

    private LocalDateTime tokenExpiredAt;

    private String staffImage;

    private String ipAddress;
    private String macAddress;

    private String department;   // แผนก (null ได้)
    private String position;     // ตำแหน่ง (null ได้)

    private Integer borId;

    // ✅ เพิ่ม base salary
    @Column(precision = 15, scale = 2)
    private BigDecimal baseSalary = BigDecimal.ZERO;

    // ✅ เพิ่ม leave quota แต่ละประเภท
    private Integer leaveQuotaSick      = 30;
    private Integer leaveQuotaPersonal  = 7;
    private Integer leaveQuotaMaternity = 90;

    private String    workSchedule;     // MON_FRI หรือ CYCLE
    private Integer   cycleWorkDays;    // เช่น 24
    private Integer   cycleOffDays;     // เช่น 7
    private LocalDate cycleStartDate;   // เช่น 2026-01-01


    // Relations เดิม
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FaceRecord> faceRecords;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttendanceLog> attendanceLogs;

    // ✅ เพิ่ม relation กับ LeaveRequest
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeaveRequest> leaveRequests;

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