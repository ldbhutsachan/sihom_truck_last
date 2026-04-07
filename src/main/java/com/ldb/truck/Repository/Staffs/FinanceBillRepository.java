package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Bansi.TbFinanceBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FinanceBillRepository extends JpaRepository<TbFinanceBill, Long> {


//    List<TbFinanceBill> findByFinanceBillId(Long financeBillId);
    // Generate เลขที่ล่าสุดของปีนั้น
    @Query("SELECT MAX(f.financeBillNo) FROM TbFinanceBill f WHERE f.financeBillNo LIKE :prefix%")
    Optional<String> findLastBillNoByPrefix(@Param("prefix") String prefix);

}