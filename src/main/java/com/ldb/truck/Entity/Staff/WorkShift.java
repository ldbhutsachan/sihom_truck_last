package com.ldb.truck.Entity.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Table(name ="work_shifts")
public class WorkShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shiftName;
    private String shiftCode;   // MORNING, AFTERNOON, FULL_DAY, DOUBLE_MORNING, DOUBLE_AFTERNOON

    private LocalTime checkInStart;   // เวลาเริ่ม check-in ได้
    private LocalTime checkInEnd;     // เวลาสิ้นสุด check-in
    private LocalTime checkOutStart;  // เวลาเริ่ม check-out ได้
    private LocalTime checkOutEnd;    // เวลาสิ้นสุด check-out

    private LocalTime workStart;      // เวลาทำงานเริ่ม (คำนวณสาย)
    private LocalTime workEnd;        // เวลาทำงานสิ้นสุด (คำนวณออกก่อน)

    private String status = "ACTIVE";

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
