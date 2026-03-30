package com.ldb.truck.Entity.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="company_staffs")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String staffCode;   // ✅ รหัสพนักงาน เช่น "EMP001"

    @Column(nullable = false)
    private String passwordHash;

    private String phone;

    @Column(nullable = false)
    private String role = "USER";

    @Column(nullable = false)
    private String status = "ACTIVE";

    private String token;

    private LocalDateTime tokenExpiredAt;

    private String  staffImage;      // ✅ เก็บรูป staff

    private String ipAddress;    // ✅ เพิ่ม
    private String macAddress;   // ✅ เพิ่ม

    // ✅ mappedBy = "staff" ให้ตรงกับชื่อ field ใน FaceRecord
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FaceRecord> faceRecords;

    // ✅ mappedBy = "staff" ให้ตรงกับชื่อ field ใน AttendanceLog
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttendanceLog> attendanceLogs;

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
