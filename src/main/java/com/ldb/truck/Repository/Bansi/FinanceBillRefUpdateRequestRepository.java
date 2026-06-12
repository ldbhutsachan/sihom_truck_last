package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.TbFinanceBillRefUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceBillRefUpdateRequestRepository
        extends JpaRepository<TbFinanceBillRefUpdateRequest, Long> {

    // ดึงทั้งหมดตาม status
    List<TbFinanceBillRefUpdateRequest> findByStatus(String status);

    // ดึงตาม financeBillNo
    List<TbFinanceBillRefUpdateRequest> findByFinanceBillNo(String financeBillNo);

    // ดึงตาม requestBy (ACCOUNTANT เห็นของตัวเอง)
    List<TbFinanceBillRefUpdateRequest> findByRequestBy(Long requestBy);

    // ดึงตาม refId
    List<TbFinanceBillRefUpdateRequest> findByRefId(Long refId);
}
