package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Bansi.TbFinanceBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FinanceBillRepository extends JpaRepository<TbFinanceBill, Long> {

    // ดึงทั้งหมดตาม status
    List<TbFinanceBill> findByBillStatus(String billStatus);

    // ดึงตาม createdBy (ACCOUNTANT เห็นแค่ของตัวเอง)
    List<TbFinanceBill> findByCreatedBy(Long createdBy);

    // ดึงตาม createdBy + status
    List<TbFinanceBill> findByCreatedByAndBillStatus(Long createdBy, String billStatus);

    // เช็คเลขที่ซ้ำ
    Optional<TbFinanceBill> findByFinanceBillNo(String financeBillNo);

    // Generate เลขที่ล่าสุดของปีนั้น
    @Query("SELECT MAX(f.financeBillNo) FROM TbFinanceBill f WHERE f.financeBillNo LIKE :prefix%")
    Optional<String> findLastBillNoByPrefix(@Param("prefix") String prefix);

}