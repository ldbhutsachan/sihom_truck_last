package com.ldb.truck.RowMapper.ExpensesBook;

import com.ldb.truck.Model.Login.ExpensesBook.ExpenType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ExpenTypeMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExpenType data = new ExpenType();
        try {
            data.setKey_id(rs.getString("key_id"));
            data.setTypeName(rs.getString("TYPENAME"));
            data.setCDate(rs.getString("C_DATE"));
        }catch (Exception e) {
        e.printStackTrace();
        }
        return data;
    }
}
