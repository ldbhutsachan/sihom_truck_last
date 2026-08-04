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

    @Column(nullable = false, unique = true)
    private String lao_name;

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

    private String cvFile;

//    private String ipAddress;
    private String macAddress;
    private String work_place;


    private Integer borId;
//    private Long  dept_id;
    @Column(name = "dept_id")
    private Long dept_id;

    @Column(name = "pos_id")
    private Long pos_id;
//    private Long  pos_id;

    private String gender;
    private String address;
    private LocalDate  startwork_date;
    private LocalDate  birth_date;

    //  เพิ่ม base salary
    @Column(precision = 15, scale = 2)
    private BigDecimal baseSalary = BigDecimal.ZERO;

    //  เพิ่ม leave quota แต่ละประเภท
    private Integer leaveQuotaSick      = 15;
    private Integer leaveQuotaAnnual  = 15;
    private Integer leaveQuotaCasual    = 3;
    private Integer leaveQuotaAccident  = 30;

    private String    workSchedule;     // MON_FRI หรือ CYCLE
    private Integer   cycleWorkDays;    // เช่น 24
    private Integer   cycleOffDays;     // เช่น 7
    private LocalDate cycleStartDate;   // เช่น 2026-01-01



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