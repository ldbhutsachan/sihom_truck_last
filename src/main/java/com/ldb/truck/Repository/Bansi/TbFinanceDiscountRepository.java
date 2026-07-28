package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.TbFinanceDiscount;
import com.ldb.truck.Model.Bansi.FinanceDiscountListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbFinanceDiscountRepository extends JpaRepository<TbFinanceDiscount, Long> {
    List<TbFinanceDiscount> findByBillId(Long billId);
    List<TbFinanceDiscount> findByBillNo(String billNo);
    Optional<TbFinanceDiscount> findByBillIdAndBillNo(Long billId, String billNo);
    boolean existsByBillId(Long billId);
    boolean existsByBillNo(String billNo);

    @Query(value =
            "SELECT " +
                    "   fd.id AS id, " +
                    "   fd.bill_id AS billId, " +
                    "   fd.bill_no AS billNo, " +
                    "   fd.discount_type AS discountType, " +
                    "   fd.discount_percent AS discountPercent, " +
                    "   fd.discount_amount AS discountAmount, " +
                    "   fd.amount_before AS amountBefore, " +
                    "   fd.amount_after AS amountAfter, " +
                    "   fd.remark AS remark, " +
                    "   fd.create_date AS createDate, " +
                    "   fd.create_by AS createBy, " +
                    "   va.big_project_id AS bigProjectId, " +
                    "   va.big_project AS bigProject, " +
                    "   va.small_project_id AS smallProjectId, " +
                    "   va.small_project AS smallProject, " +
                    "   va.pay_type_id AS payTypeId, " +
                    "   va.pay_type AS payType, " +
                    "   va.pay_typegroup_id AS payTypegroupId, " +
                    "   va.pay_typegroup_name AS payTypegroupName, " +
                    "   va.type_of AS typeOf, " +
                    "   va.supplierid AS supplierId, " +
                    "   va.supplier_name AS supplierName, " +
                    "   va.bank_account_name AS bankAccountName, " +
                    "   va.bank_account_no AS bankAccountNo, " +
                    "   va.bank_name AS bankName, " +
                    "   va.currency AS currency, " +
                    "   va.exchange_rate AS exchangeRate, " +
                    "   va.original_lak_price AS originalLakPrice, " +
                    "   va.original_usd_price AS originalUsdPrice, " +
                    "   va.price AS price, " +
                    "   va.usd_price AS usdPrice " +
                    "FROM tb_finance_discount fd " +
                    "LEFT JOIN v_accounting_finance va ON fd.bill_no = va.bill_No " +
                    "WHERE (:startDate IS NULL OR :startDate = '' OR fd.create_date >= :startDate) " +
                    "  AND (:endDate IS NULL OR :endDate = '' OR fd.create_date < DATE_ADD(:endDate, INTERVAL 1 DAY)) " +
                    "  AND (:billNo IS NULL OR :billNo = '' OR fd.bill_no = :billNo) " +
                    "  AND (:billId IS NULL OR fd.bill_id = :billId) " +
                    "ORDER BY fd.create_date DESC",
            nativeQuery = true)
    List<FinanceDiscountListProjection> findDiscountList(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("billNo") String billNo,
            @Param("billId") Long billId
    );
}
