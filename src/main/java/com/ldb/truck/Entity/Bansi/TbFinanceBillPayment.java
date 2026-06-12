package com.ldb.truck.Entity.Bansi;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_finance_bill_payment")
public class TbFinanceBillPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "finance_bill_id")
    private Long financeBillId;

    @Column(name = "finance_bill_ref_id")
    private Long financeBillRefId;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "pay_amount", precision = 10, scale = 5)
    private BigDecimal payAmount;

    @Column(name = "pay_date")
    private LocalDateTime payDate;

    @Column(name = "pay_method")
    private String payMethod;         // โอน, เงินสด, เช็ค

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "bank_account_no")
    private String bankAccountNo;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "slip_file")
    private String slipFile;

    @Column(name = "remark")
    private String remark;

    @Column(name = "pay_status")
    private String payStatus ;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "date_create")
    private LocalDateTime dateCreate = LocalDateTime.now();
}