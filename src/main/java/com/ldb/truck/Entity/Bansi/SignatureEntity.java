package com.ldb.truck.Entity.Bansi;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_signature")
public class SignatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private Long sid;

    @Column(name = "user_name") // <-- แก้ตรงนี้
    private String userName;

    @Column(name = "signature")
    private String signature;
}

