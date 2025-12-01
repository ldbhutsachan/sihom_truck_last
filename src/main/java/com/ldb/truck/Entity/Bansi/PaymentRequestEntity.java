package com.ldb.truck.Entity.Bansi;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name ="date_create")
    private LocalDate dateCreate;

    @Column(name ="data_type")
    private String dataType;

    @Column(name = "bill_status")
    private String billStatus;

    @Transient
    private String toKen; //สำหรับ token จาก client

    // add more for approve billNO
    @Column(name = "basi_approve_date")
    private LocalDateTime basiApproveDate;

    @Column(name = "bansi_approveby")
    private String bansiApproveBy;

    @Column(name = "account_approve_date")
    private LocalDateTime accountApproveDate;

    @Column(name = "account_approveby")
    private String accountApproveBy;

    @Column(name = "final_approve_date")
    private LocalDateTime finalApproveDate;


    @Column(name = "final_approveby")
    private String finalApproveBy;

    @Column(name = "returnby")
    private String returnBy;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

}
