package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Model.Bansi.FinanceHistDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FinancePayHisRepo {

    private final JdbcTemplate jdbcTemplate;

    public FinancePayHisRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FinanceHistDto> findFinancePayHisFilter(String financeBill) {

        StringBuilder sql = new StringBuilder(
                "SELECT key_id AS keyId, finance_bill AS financeBill, pay_amount AS payAmount, " +
                        "date_pay AS datePay, create_date AS createDate " +
                        "FROM tb_finance_pay WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (financeBill != null && !financeBill.trim().isEmpty()) {
            sql.append(" AND finance_bill = ? ");
            params.add(financeBill);
        }

        return jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                new BeanPropertyRowMapper<>(FinanceHistDto.class)
        );
    }
}
