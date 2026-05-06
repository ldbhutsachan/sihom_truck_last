package com.ldb.truck.Entity.Bansi;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_finance_bill_ref_update_request")
public class TbFinanceBillRefUpdateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "finance_bill_no")
    private String financeBillNo;

    @Column(name = "ref_id")
    private Long refId;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "old_amount", precision = 20, scale = 5)
    private BigDecimal oldAmount;       // ค่าเดิม

    @Column(name = "new_amount", precision = 20, scale = 5)
    private BigDecimal newAmount;       // ค่าที่ขอแก้

    @Column(name = "remark")
    private String remark;

    // Request
    @Column(name = "request_by")
    private Long requestBy;

    @Column(name = "request_by_name")
    private String requestByName;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    // PENDING | APPROVED | REJECTED
    @Column(name = "status")
    private String status = "PENDING";

    // Approve
    @Column(name = "approve_by")
    private Long approveBy;

    @Column(name = "approve_by_name")
    private String approveByName;

    @Column(name = "approve_date")
    private LocalDateTime approveDate;

    @Column(name = "approve_remark")
    private String approveRemark;
}
