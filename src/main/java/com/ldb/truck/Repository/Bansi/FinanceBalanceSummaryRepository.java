package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.VFinanceBalanceSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinanceBalanceSummaryRepository
        extends JpaRepository<VFinanceBalanceSummary, Long> {

    // ─── API 1: balance-report ────────────────────────────
    @Query(value =
            "SELECT * FROM v_finance_balance_summary " +
                    "WHERE " +
                    "    (:supplierId     IS NULL OR supplierid          = :supplierId) " +
                    "AND (:bigProjectId   IS NULL OR big_project_id      = :bigProjectId) " +
                    "AND (:smallProjectId IS NULL OR small_project_id    = :smallProjectId) " +
                    "AND (:currency       IS NULL OR currency            = :currency) " +
                    "AND (:startDate      IS NULL OR finance_approve_date >= :startDate) " +
                    "AND (:endDate        IS NULL OR finance_approve_date <= :endDate) " +
                    "ORDER BY supplierid, currency, finance_approve_date",
            nativeQuery = true)
    List<VFinanceBalanceSummary> findByFilter(
            @Param("supplierId")     Long supplierId,
            @Param("bigProjectId")   Long bigProjectId,
            @Param("smallProjectId") Long smallProjectId,
            @Param("currency")       String currency,
            @Param("startDate")      LocalDate startDate,
            @Param("endDate")        LocalDate endDate
    );

    // ─── API 2: balance-summary ───────────────────────────
    @Query(value =
            "SELECT v1.* " +
                    "FROM v_finance_balance_summary v1 " +
                    "INNER JOIN ( " +
                    "   SELECT supplierid, currency, " +
                    "          MAX(finance_approve_date) AS max_date " +
                    "   FROM v_finance_balance_summary " +
                    "   WHERE " +
                    "       (:supplierId     IS NULL OR supplierid     = :supplierId) " +
                    "   AND (:bigProjectId   IS NULL OR big_project_id = :bigProjectId) " +
                    "   AND (:smallProjectId IS NULL OR small_project_id = :smallProjectId) " +
                    "   AND ( " +
                    "       :currency IS NULL OR :currency = '' " +
                    "       OR UPPER(TRIM(currency)) = UPPER(TRIM(:currency)) " +
                    "   ) " +
                    "   AND ( " +
                    "       :endDate IS NULL OR :endDate = '' " +
                    "       OR DATE(finance_approve_date) <= DATE(:endDate) " +
                    "   ) " +
                    "   GROUP BY supplierid, currency " +
                    ") v2 " +
                    "ON  v1.supplierid           = v2.supplierid " +
                    "AND v1.currency             = v2.currency " +
                    "AND v1.finance_approve_date = v2.max_date " +
                    "ORDER BY v1.supplierid, v1.currency",
            nativeQuery = true)
    List<VFinanceBalanceSummary> findLatestBySupplierWithFilter(
            @Param("supplierId")     Long supplierId,
            @Param("bigProjectId")   Long bigProjectId,
            @Param("smallProjectId") Long smallProjectId,
            @Param("currency")       String currency,
            @Param("endDate")        String endDate
    );
}