package com.ldb.truck.Entity.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_logs")
@Data
@NoArgsConstructor
public class AttendanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private StaffEntity staff;

    @Column(nullable = false)
    private String checkType;       // "CHECK_IN" / "CHECK_OUT"

    @Column(nullable = false)
    private LocalDateTime checkTime;

    private String remark;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.checkTime == null) this.checkTime = LocalDateTime.now();
    }
}
