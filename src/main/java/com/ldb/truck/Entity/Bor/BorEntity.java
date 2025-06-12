package com.ldb.truck.Entity.Bor;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Table(name = "tb_bors") // Ensure this matches your actual DB table name
public class BorEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "key_id") // Primary key
        private Long keyId;

        @Column(name = "b_name")
        private String bName;

        @Column(name = "b_tel")
        private String bTel;

        @Column(name = "location")
        private String location;

        @Column(name = "email")
        private String email;

        @Column(name = "user_id")
        private String userId;

        @Column(name = "create_date")
        private Date createDate; // Consider using LocalDateTime

        @Column(name = "sort_name")
        private String sortName;

        @Column(name = "brand_no")
        private String brandNo;

        @Column(name = "status")
        private String status;

        @Column(name = "update_by")
        private String updateBy;

        @Column(name = "update_date")
        private Date updateDate;
    }