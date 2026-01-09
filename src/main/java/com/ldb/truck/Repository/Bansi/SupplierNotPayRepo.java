package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Model.Bansi.SupplierNotPayDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SupplierNotPayRepo {

    private final JdbcTemplate jdbcTemplate;

    public SupplierNotPayRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SupplierNotPayDto> findSupplierNotPay(
            String startDate,
            String endDate,
            String typeOf,
            String supplierId
    ) {

        // ตรวจสอบค่า ถ้า "" ให้เปลี่ยนเป็น null
        startDate = (startDate == null || startDate.trim().isEmpty()) ? null : startDate;
        endDate = (endDate == null || endDate.trim().isEmpty()) ? null : endDate;
        typeOf = (typeOf == null || typeOf.trim().isEmpty()) ? null : typeOf;
        supplierId = (supplierId == null || supplierId.trim().isEmpty()) ? null : supplierId;

        String sql =
                "SELECT " +
                        " key_id, " +
                        " bill_No, " +
                        " finance_bill, " +
                        " supplier_id, " +
                        " supplier_name, " +
                        " type_of, " +
                        " amount_must_pay, " +
                        " pay1, " +
                        " next_date_pay4, " +
                        " pay_status, " +
                        " currency, " +
                        " create_by, " +
                        " create_date " +
                        "FROM v_finace_supplier " +
                        "WHERE (? IS NULL OR create_date >= ?) " +
                        "AND (? IS NULL OR create_date <= ?) " +
                        "AND (? IS NULL OR type_of = ?) " +
                        "AND (? IS NULL OR supplier_id = ?) " +
                        "AND pay_status != 'DONE' " +
                        "ORDER BY create_date DESC";

        return jdbcTemplate.query(
                sql,
                new Object[]{
                        startDate, startDate,
                        endDate, endDate,
                        typeOf, typeOf,
                        supplierId, supplierId
                },
                (rs, rowNum) -> mapRow(rs)
        );
    }

    private SupplierNotPayDto mapRow(ResultSet rs) throws SQLException {
        SupplierNotPayDto dto = new SupplierNotPayDto();
        dto.setKeyId(rs.getLong("key_id"));
        dto.setBillNo(rs.getString("bill_No"));
        dto.setFinanceBill(rs.getString("finance_bill"));
        dto.setSupplierId(rs.getLong("supplier_id"));
        dto.setSupplierName(rs.getString("supplier_name"));
        dto.setTypeOf(rs.getString("type_of"));
        dto.setAmountMustPay(rs.getBigDecimal("amount_must_pay"));
        dto.setPay1(rs.getBigDecimal("pay1"));
        dto.setNextDatePay4(rs.getString("next_date_pay4"));
        dto.setPayStatus(rs.getString("pay_status"));
        dto.setCurrency(rs.getString("currency"));
        dto.setCreateBy(rs.getString("create_by"));
        dto.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        return dto;
    }
}
