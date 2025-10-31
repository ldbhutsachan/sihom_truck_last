package com.ldb.truck.Entity.Bansi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pay_type")
public class PayTypeEntity {  // ชื่อ class ควรขึ้นต้นด้วยตัวใหญ่ และ camelCase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Long pid;

    @Column(name = "type_name")
    @JsonProperty("type_name")  // บอกให้ Jackson map จาก JSON key "type_name"
    private String typeName;

    @Column(name = "date_create")
    private LocalDateTime dateCreate; // เปลี่ยนเป็น LocalDateTime

    @Column(name = "req_id", nullable = true)
    @JsonProperty("req_id")
    private Long reqId;


    @Transient
    private String toKen; // สำหรับส่ง token จาก client
}
