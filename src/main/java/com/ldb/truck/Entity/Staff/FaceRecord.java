package com.ldb.truck.Entity.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table (name = "face_records")
public class FaceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ เปลี่ยนจาก User → StaffEntity และลบ import twilio ออก
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private StaffEntity staff;   // ✅ field ชื่อ "staff" ไม่ใช่ "user"

    @Column(columnDefinition = "LONGBLOB")
    private byte[] faceImage;

    @Column(columnDefinition = "TEXT")
    private String faceVector;

    @Column(nullable = false)
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
