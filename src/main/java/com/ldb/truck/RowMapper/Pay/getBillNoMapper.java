package com.ldb.truck.RowMapper.Pay;
import com.ldb.truck.Model.Login.Pay.getBillNo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class getBillNoMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        getBillNo data = new getBillNo();
        try {
            data.setBillNo(rs.getString("billNo"));
            data.setPayDate(rs.getString("pay_date"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
