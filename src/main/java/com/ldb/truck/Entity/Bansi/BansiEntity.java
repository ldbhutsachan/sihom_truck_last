package com.ldb.truck.Entity.Bansi;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "request_item_type")
public class BansiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Long reqId;

    @Column(name = "req_name")
    private String reqName;

    @Column(name = "bansi")
    private String bansi;

    @Column(name = "item_typeid", nullable = true) // ตรงกับ DB
    private Long item_typeid;
    @Transient
    private String toKen; // สำหรับส่ง token จาก client
}
