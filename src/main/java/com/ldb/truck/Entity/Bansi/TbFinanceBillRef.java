package com.ldb.truck.Entity.Bansi;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_finance_bill_ref")
public class TbFinanceBillRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "finance_bill_id")
    private Long financeBillId;

    @Column(name = "key_id")
    private Long keyId;              // FK → tb_accounting.key_id

    @Column(name = "bill_no")
    private String billNo;           // FK → tb_accounting.bill_No

    @Column(name = "original_amount")
    private BigDecimal originalAmount;  // ยอดเต็มของ bill_no นั้น

    @Column(name = "amount")
    private BigDecimal amount;          // ยอดที่ ACCOUNTANT ต้องการเรียกเก็บครั้งนี้

    // ─── Status ───────────────────
    @Column(name = "bill_status")
    private String billStatus = "PENDING_CHECK";

    // ─── ACCOUNTANT (ผู้สร้าง) ────────────────────────────
    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "date_create")
    private LocalDateTime dateCreate = LocalDateTime.now();

    // ─── ACCOUNTANTCHECK ──────────────────────────────────
    @Column(name = "accountant_check_by")
    private Long accountantCheckBy;

    @Column(name = "accountant_check_date")
    private LocalDateTime accountantCheckDate;

    @Column(name = "accountant_check_status")
    private String accountantCheckStatus;

    @Column(name = "accountant_check_remark")
    private String accountantCheckRemark;

    // ─── AUDITOR ──────────────────────────────────────────
    @Column(name = "auditor_approve_by")
    private Long auditorApproveBy;

    @Column(name = "auditor_approve_date")
    private LocalDateTime auditorApproveDate;

    @Column(name = "auditor_approve_status")
    private String auditorApproveStatus;

    @Column(name = "auditor_approve_remark")
    private String auditorApproveRemark;

    // ─── FINANCE (Final) ──────────────────────────────────
    @Column(name = "finance_approve_by")
    private Long financeApproveBy;

    @Column(name = "finance_approve_date")
    private LocalDateTime financeApproveDate;

    @Column(name = "finance_approve_status")
    private String financeApproveStatus;

    @Column(name = "finance_approve_remark")
    private String financeApproveRemark;

    // ─── Return ───────────────────────────────────────────
    @Column(name = "return_by")
    private Long returnBy;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "return_remark")
    private String returnRemark;

    @Column(name = "return_to_role")
    private String returnToRole;
}
