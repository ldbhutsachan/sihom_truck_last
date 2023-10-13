package com.ldb.truck.RowMapper.customer;

import com.ldb.truck.Model.Login.customer.CustomerOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getAllcustomerMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        CustomerOut data = new CustomerOut();

        try {

            data.setAddress(rs.getString("ADDRESS"));
            data.setCustomerId(rs.getString("CUSTOMER_ID"));
            data.setCustomerName(rs.getString("CUSTOMER_NAME"));
            data.setProvince(rs.getString("PROVICNE"));
            data.setDistrict(rs.getString("DISTRICT"));
            data.setVilage(rs.getString("VILLAGE"));
            data.setMobile(rs.getString("MOBILE1"));
            data.setMobile1(rs.getString("MOBILE2"));
            data.setEmail(rs.getString("EMAIL"));
            data.setId(rs.getInt("KEY_ID"));

        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
