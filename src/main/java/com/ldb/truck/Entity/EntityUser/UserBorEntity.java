package com.ldb.truck.Entity.EntityUser;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter // เอาไว้ดึงค่า (อ่านค่า) ของตัวแปร private
@Setter // เอาไว้กำหนดค่า (แก้ไขค่า) ของตัวแปร private
@Entity  // บอก JPA ว่า class นี้คือ Entity ที่ map กับ table
@Table(name = "`LOGIN`") // ตัวพิมพ์ใหญ่ตรงกับ DB
public class UserBorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KEY_ID")
    private Integer keyId;

    @Column(name = "USER_LOGIN")
    private String userLogin;

    @Column(name = "PASSOWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "DATE_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInsert;

    @Column(name = "USERID")
    private String userId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "BRANCH")
    private String branch;

   @Column(name = "savebyid")   // ต้องตรงกับ DB จริง
   private String saveById;     // ชื่อตัวแปร Java จะใช้ camelCase ได้



    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "SPRIT_ROLE")
    private String spritRole;

    @Column(name = "bor_no")
    private String borNo;
}


