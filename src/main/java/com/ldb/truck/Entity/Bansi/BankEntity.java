package com.ldb.truck.Entity.Bansi;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="tb_bank")
public class BankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    @JsonProperty("b_id")   // 👈 เพิ่มบรรทัดนี้
    private Long bId;

    @Column(name ="account_name")
    private String accountName;

    @Column (name ="account_no")
    private String accountNo;

    @Column (name = "bank_name")
    private String bankName;

    @Column (name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "user_id")
    private String userId;

    @Transient
    private String toKen; // สำหรับส่ง token จาก client

}
