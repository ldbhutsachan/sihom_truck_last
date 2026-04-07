package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.FinanceListEntity;
import com.ldb.truck.Entity.Bansi.FinanceListEntityAgain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FinanceListRepositoryagain extends JpaRepository<FinanceListEntityAgain, Long> {
    @Query(value =
            "SELECT * FROM v_accounting_finance_again " +
                    "WHERE (NULLIF(:supplierId, '') IS NULL OR supplierid = :supplierId) " +
                    "AND (NULLIF(:payTypeId, '') IS NULL OR pay_type_id = :payTypeId) " +
                    "AND (NULLIF(:typeOf, '') IS NULL OR type_of = :typeOf) " +
                    "AND (NULLIF(:currency, '') IS NULL OR currency = :currency) " +
                    "AND (NULLIF(:paystatus, '') IS NULL OR pay_status = :paystatus) " +
                    "AND (NULLIF(:startDate, '') IS NULL OR basi_approve_date >= :startDate) " +
                    "AND (NULLIF(:endDate, '') IS NULL OR basi_approve_date < DATE_ADD(:endDate, INTERVAL 1 DAY)) " +
                    "ORDER BY basi_approve_date DESC",
            nativeQuery = true)
    List<FinanceListEntityAgain> searchFinance(
            @Param("supplierId") String supplierId,
            @Param("payTypeId") String payTypeId,
            @Param("typeOf") String typeOf,
            @Param("currency") String currency,
            @Param("paystatus") String paystatus,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );



}
