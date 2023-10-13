package com.ldb.truck.RowMapper.Product;

import com.ldb.truck.Model.Login.product.ProductOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper  implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductOut data = new ProductOut();

        try {

            data.setId(rs.getString("KEY_ID"));
            data.setProId(rs.getString("PRO_ID"));
            data.setProName(rs.getString("PRO_NAME"));
            data.setProType(rs.getString("PRO_TYPE"));
            data.setProUrl(rs.getString("URL"));
            data.setProDateTime(rs.getString("DATE_INSERT"));
            data.setUserId(rs.getString("USERID"));

        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return data;
    }
}
