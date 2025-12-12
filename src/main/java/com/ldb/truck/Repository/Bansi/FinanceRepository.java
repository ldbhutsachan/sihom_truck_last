package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.FinanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<FinanceEntity, Long> {

    // หา last financeBill สำหรับ generate ต่อ
    @Query(value = "SELECT f.finance_bill FROM tb_finance f ORDER BY f.key_id DESC LIMIT 1", nativeQuery = true)
    String findLastFinanceBillNo();

    // นับว่า financeBill นี้มีอยู่ในระบบหรือไม่
    @Query(value = "SELECT COUNT(*) FROM tb_finance WHERE finance_bill = :financeBill", nativeQuery = true)
    int countBill(@Param("financeBill") String financeBill);

    // ⭐ สำคัญ — สำหรับ update finance
    FinanceEntity findByFinanceBill(String financeBill);
}
