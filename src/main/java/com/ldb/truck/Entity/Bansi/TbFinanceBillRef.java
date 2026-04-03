package com.ldb.truck.Entity.Bansi;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

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
}
