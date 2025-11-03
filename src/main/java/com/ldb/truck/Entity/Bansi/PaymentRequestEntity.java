package com.ldb.truck.Entity.Bansi;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_accounting") // ✅ ใช้ชื่อตารางตามจริงใน DB
public class PaymentRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    private Long keyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "pay_typeid")
    private Long payTypeId;

    @Column(name = "supplierid")
    private Long supplierId;

    @Column(name = "bill_No")
    private String billNo;


    @Column(name = "title")
    private String title;

    @Column(name = "currency")
    private String currency;

    @Column(name = "exchange_rate")
    private String exchangeRate;

    @Column(name = "date")
    private String date;

    @Column(name = "datermine_date")
    private String datertimeDate;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "reference")
    private String reference;

    @Column(name = "remark")
    private String remark;

    @Column(name = "internal_remark")
    private String internalRemark;

    @Column(name = "tag")
    private String tag;

    @Column(name = "file")
    private String file;

    @Transient
    private String toKen; //สำหรับ token จาก client
}
