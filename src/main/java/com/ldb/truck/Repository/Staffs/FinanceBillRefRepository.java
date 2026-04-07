package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Bansi.TbFinanceBillRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FinanceBillRefRepository extends JpaRepository<TbFinanceBillRef, Long> {
    // ดึงทั้งหมดตาม status
    List<TbFinanceBillRef> findByBillStatus(String billStatus);

    // ดึงตาม createdBy (ACCOUNTANT เห็นแค่ของตัวเอง)
    List<TbFinanceBillRef> findByCreatedBy(Long createdBy);

    // ดึงตาม createdBy + status
    List<TbFinanceBillRef> findByCreatedByAndBillStatus(Long createdBy, String billStatus);

    // ดึง bill_no ทั้งหมดใน finance bill นี้
    List<TbFinanceBillRef> findByFinanceBillId(Long financeBillId);

    // เช็คว่า bill_no นี้ถูกใช้ใน finance bill อื่นแล้วหรือยัง
    boolean existsByBillNo(String billNo);

    //  เพิ่มตรงนี้ — ดึง ref ทั้งหมดของ bill_no
    List<TbFinanceBillRef> findByBillNo(String billNo);

    // FinanceBillRefRepository.java — เพิ่ม 2 method นี้
    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM TbFinanceBillRef r WHERE r.billNo = :billNo AND r.billStatus = 'APPROVED'")
    BigDecimal sumApprovedAmountByBillNo(@Param("billNo") String billNo);

    // คำนวณยอดที่ถูกใช้ไปแล้วของ bill_no นั้น
    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM TbFinanceBillRef r WHERE r.billNo = :billNo")
    BigDecimal sumUsedAmountByBillNo(@Param("billNo") String billNo);
}