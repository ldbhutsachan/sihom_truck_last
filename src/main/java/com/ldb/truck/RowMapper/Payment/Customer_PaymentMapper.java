package com.ldb.truck.RowMapper.Payment;

import com.ldb.truck.Model.Login.Payment.Customer_Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer_PaymentMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer_Payment  data = new Customer_Payment();
        try {
          //  data.setID(rs.getString("ID"));
            data.setPERFORMANCEBILLNO(rs.getString("PERFORMANCEBILLNO"));
            data.setKEY_ID(rs.getString("KEY_ID"));
            data.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
            data.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
            data.setPRO_TYPE(rs.getString("PRO_TYPE"));
            data.setPRODUCT_AMOUNT(rs.getString("PRODUCT_AMOUNT"));
            data.setPRICE(rs.getString("PRICE"));
            data.setTOTAL_PRICE(rs.getString("TOTAL_PRICE"));
            data.setSTATUS(rs.getString("STATUS"));
            data.setCurrency(rs.getString("CURRENCY"));

        }catch (Exception e){

        }
        return data;
    }
}
