package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Model.Bansi.SupplierNotPayDto;
import com.ldb.truck.Model.Bansi.SupplierSummaryRowDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class SupplierNotPayRepo {

    private final JdbcTemplate jdbcTemplate;

    public SupplierNotPayRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ==============================
    // 1️⃣ Detail: FinanceBills
    // ==============================
    public List<SupplierNotPayDto> findSupplierNotPay(
            String startDate,
            String endDate,
            String typeOf,
            String supplierId,
            String show
    ) {
        // ตรวจสอบค่า ถ้า "" ให้เปลี่ยนเป็น null
        startDate = (startDate == null || startDate.trim().isEmpty()) ? null : startDate;
        endDate = (endDate == null || endDate.trim().isEmpty()) ? null : endDate;
        typeOf = (typeOf == null || typeOf.trim().isEmpty()) ? null : typeOf;
        supplierId = (supplierId == null || supplierId.trim().isEmpty()) ? null : supplierId;
        show = (show == null || show.trim().isEmpty()) ? "all" : show;

        String sql =
                "SELECT key_id, bill_no, finance_bill, supplier_id, supplier_name,\n" +
                        "       type_of, amount_must_pay, pay1, next_date_pay4, pay_status, currency,\n" +
                        "       create_by, create_date, big_project, small_project, pay_type, bank_account_name, bank_account_no, title\n" +
                        "FROM v_finace_supplier\n" +
                        "WHERE (? = 'all' OR (? = '1' AND pay_status = 'DONE') OR (? = '2' AND pay_status = 'IN-PROGRESS'))\n" +
                        "AND create_date >= IFNULL(?, create_date)\n" +
                        "AND create_date <  IFNULL(DATE_ADD(?, INTERVAL 1 DAY), create_date)\n" +
                        "AND (? IS NULL OR type_of = ?)\n" +
                        "AND (? IS NULL OR supplier_id = ?)\n" +
                        "ORDER BY supplier_id, finance_bill\n";

        return jdbcTemplate.query(sql, new Object[]{
                show, show, show,         // สำหรับเงื่อนไข pay_status
                startDate,                // IFNULL start
                endDate,                  // IFNULL end
                typeOf, typeOf,           // type_of
                supplierId, supplierId    // supplier_id
        }, (rs, rowNum) -> {
            SupplierNotPayDto dto = new SupplierNotPayDto();
            dto.setKeyId(rs.getLong("key_id"));
            dto.setBigProject(rs.getString("big_project"));
            dto.setSmallProject(rs.getString("small_project"));
            dto.setTypePay(rs.getString("pay_type"));
            dto.setBankName(rs.getString("bank_account_name"));
            dto.setBankNo(rs.getString("bank_account_no"));
            dto.setBillNo(rs.getString("bill_no"));
            dto.setTitle(rs.getString("title"));
            dto.setFinanceBill(rs.getString("finance_bill"));
            dto.setSupplierId(rs.getLong("supplier_id"));
            dto.setSupplierName(rs.getString("supplier_name"));
            dto.setTypeOf(rs.getString("type_of"));
            dto.setAmountMustPay(rs.getBigDecimal("amount_must_pay"));
            dto.setPaid(rs.getBigDecimal("pay1"));
            dto.setNextDatePay(rs.getString("next_date_pay4"));
            dto.setPayStatus(rs.getString("pay_status"));
            dto.setCurrency(rs.getString("currency"));
            dto.setCreateBy(rs.getString("create_by"));
            dto.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            return dto;
        });
    }
    private SupplierNotPayDto mapRow(ResultSet rs) throws SQLException {
        SupplierNotPayDto dto = new SupplierNotPayDto();
        dto.setKeyId(rs.getLong("key_id"));
        dto.setBigProject(rs.getString("big_project"));
        dto.setSmallProject(rs.getString("small_project"));
        dto.setTypePay(rs.getString("pay_type"));
        dto.setBankName(rs.getString("bank_account_name"));
        dto.setBankNo(rs.getString("bank_account_no"));
        dto.setBillNo(rs.getString("bill_no"));
        dto.setTitle(rs.getString("title"));
        dto.setFinanceBill(rs.getString("finance_bill"));
        dto.setSupplierId(rs.getLong("supplier_id"));
        dto.setSupplierName(rs.getString("supplier_name"));
        dto.setTypeOf(rs.getString("type_of"));
        dto.setAmountMustPay(rs.getBigDecimal("amount_must_pay"));
        dto.setPaid(rs.getBigDecimal("pay1"));
        dto.setNextDatePay(rs.getString("next_date_pay4"));
        dto.setPayStatus(rs.getString("pay_status"));
        dto.setCurrency(rs.getString("currency"));
        dto.setCreateBy(rs.getString("create_by"));
        dto.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        return dto;
    }



}
