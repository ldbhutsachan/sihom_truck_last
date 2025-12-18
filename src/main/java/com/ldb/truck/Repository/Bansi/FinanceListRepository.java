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
                    "AND (:payTypeId IS NULL OR :payTypeId = '' OR pay_type_id = :payTypeId) " +
                    "AND (:typeOf IS NULL OR :typeOf = '' OR type_of = :typeOf) " +
                    "AND (:startDate IS NULL OR :startDate = '' OR finance_approve_date >= :startDate) " +
                    "AND (:endDate IS NULL OR :endDate = '' " +
                    "     OR finance_approve_date < DATE_ADD(:endDate, INTERVAL 1 DAY)) " +
                    "AND COALESCE(pay_status, '') <> 'DONE-PAY' " +
                    "ORDER BY finance_approve_date DESC",
            nativeQuery = true)
    List<FinanceListEntity> searchFinance(
            @Param("supplierId") String supplierId,
            @Param("payTypeId") String payTypeId,
            @Param(("typeOf")) String typeOf,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );



}

