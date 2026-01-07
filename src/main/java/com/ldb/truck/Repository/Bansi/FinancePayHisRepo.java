package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Model.Bansi.FinanceHistDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FinancePayHisRepo {

    private final JdbcTemplate jdbcTemplate;

    public FinancePayHisRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // เพิ่ม parameter financeBill จาก client
    public List<FinanceHistDto> findFinancePayHisFilter(String financeBill) {
        String sql = "SELECT key_id AS keyId, finance_bill AS financeBill, pay_amount AS payAmount, " +
                "date_pay AS datePay, create_date AS createDate " +
                "FROM tb_finance_pay WHERE finance_bill = ?";

        return jdbcTemplate.query(sql, new Object[]{financeBill},
                new BeanPropertyRowMapper<>(FinanceHistDto.class));
    }
}
