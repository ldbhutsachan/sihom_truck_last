package com.ldb.truck.RowMapper.Pay;
import com.ldb.truck.Model.Login.Pay.Bank;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BankMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bank data = new Bank();
        try {
            data.setBankName(rs.getString("BANKNAME"));
        }catch (Exception e){
        e.printStackTrace();
        }
        return data;
    }
}
