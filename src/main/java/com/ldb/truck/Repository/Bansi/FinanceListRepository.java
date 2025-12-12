package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.FinanceListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceListRepository extends JpaRepository<FinanceListEntity, Long> {

    @Query(value =
            "SELECT * FROM v_accounting_finance " +
                    "WHERE (:supplierId IS NULL OR :supplierId = '' OR supplierid = :supplierId) " +
                    "AND (:payTypeId IS NULL OR :payTypeId = '' OR pay_type_id = :payTypeId)",
            nativeQuery = true)
    List<FinanceListEntity> searchFinance(
            @Param("supplierId") String supplierId,
            @Param("payTypeId") String payTypeId
    );
}

