package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Model.Bansi.FinanceViewDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FinanceViewRepository {

    private final JdbcTemplate jdbcTemplate;

    public FinanceViewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FinanceViewDto> findFinanceByFilter(String financeBill, String payStatus, String startDate, String endDate) {
        String sql = "SELECT * FROM v_finance WHERE 1=1"; // 1=1 ทำให้ต่อ condition ง่าย
        List<Object> params = new ArrayList<>();

        if (financeBill != null && !financeBill.isEmpty()) {
            sql += " AND finance_bill = ?";
            params.add(financeBill);
        }
        if (payStatus != null && !payStatus.isEmpty()) {
            sql += " AND pay_status = ?";
            params.add(payStatus);
        }
        if (startDate != null && !startDate.isEmpty()) {
            sql += " AND first_date_pay >= ?";
            params.add(startDate + " 00:00:00"); // เริ่มต้นวัน
        }
        if (endDate != null && !endDate.isEmpty()) {
            sql += " AND first_date_pay <= ?";
            params.add(endDate + " 23:59:59"); // จบวัน
        }

        RowMapper<FinanceViewDto> rowMapper = (rs, rowNum) -> {
            FinanceViewDto dto = new FinanceViewDto();
            dto.setKeyId(rs.getLong("key_id"));
            dto.setSupplierId(rs.getLong("supplier_id"));
            dto.setSupplierName(rs.getString("supplier_name"));
            dto.setFinanceBill(rs.getString("finance_bill"));
            dto.setAmountMustPay(rs.getDouble("amount_must_pay"));
            dto.setPay1(rs.getDouble("pay1"));
            dto.setNextDatePay(rs.getString("next_date_pay4"));
            dto.setPayStatus(rs.getString("pay_status"));
            dto.setCurrency(rs.getString("currency"));
            dto.setCreateBy(rs.getString("create_by"));
            dto.setBillNo(rs.getString("bill_no"));
            dto.setCreateDate(rs.getString("create_date"));
            return dto;
        };


        return jdbcTemplate.query(sql, rowMapper, params.toArray());
    }
}
