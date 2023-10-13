package com.ldb.truck.RowMapper;

import com.ldb.truck.Model.Login.product.ProductOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper  implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductOut data = new ProductOut();

        try {
            data.setId(rs.getString(""));
            data.setProDateTime(rs.getString(""));
            data.setProId(rs.getString(""));

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
