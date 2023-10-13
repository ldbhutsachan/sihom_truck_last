package com.ldb.truck.RowMapper.OweMapper;

import com.ldb.truck.Model.Login.Owe.OwePayBack;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwePayBackMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        OwePayBack data = new OwePayBack();
        try{
            data.setCusId(rs.getString("CUSTOMER_ID"));
            data.setCusName(rs.getString("CUSTOMER_NAME"));
            data.setBillNo(rs.getString("BILLNO"));
            data.setPayDate(rs.getString("PAY_DATE"));
            data.setPayAmount(rs.getString("PAY_AMOUNT"));
            data.setPayStatus(rs.getString("PAY_STATUS"));
            data.setCalTotalDate(rs.getString("CALTOTALDATE"));
            data.setDayAmountCheck01(rs.getDouble("payAmount01"));
            data.setDayAmountCheck02(rs.getDouble("payAmount02"));
            data.setDayAmountCheck03(rs.getDouble("payAmount03"));
            data.setDayAmountCheck04(rs.getDouble("payAmount04"));
            data.setCurrency(rs.getString("currency"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
