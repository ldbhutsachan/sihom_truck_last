package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Bansi.TbFinanceBillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FinanceBillPaymentRepository extends JpaRepository<TbFinanceBillPayment, Long> {

    // ดึง payment ทั้งหมดของ finance bill
    List<TbFinanceBillPayment> findByFinanceBillId(Long financeBillId);

    // ดึง payment ตาม bill_no
    List<TbFinanceBillPayment> findByBillNo(String billNo);

    // คำนวณยอดจ่ายแล้วทั้งหมดของ bill_no นั้น
    @Query("SELECT COALESCE(SUM(p.payAmount), 0) FROM TbFinanceBillPayment p WHERE p.billNo = :billNo AND p.payStatus != 'REJECTED'")
    BigDecimal sumPaidAmountByBillNo(@Param("billNo") String billNo);
}
