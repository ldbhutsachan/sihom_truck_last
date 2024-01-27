package com.ldb.truck.RowMapper.ExpensesBook;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBook;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ExpensesBookMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExpensesBook data = new ExpensesBook();
        try{
            data.setKey_id(rs.getString("key_id"));
            data.setExPType(rs.getString("EXPENSESTYPE"));
            data.setExPName(rs.getString("EXPNAME"));
            data.setPerAmount(rs.getString("PERAMOUNT"));
            data.setToTal(rs.getString("TOTAL"));
            data.setAmount(rs.getString("AMOUNT"));
            data.setExpDate(rs.getString("EXPDATE"));
            data.setCDate(rs.getString("C_DATE"));
            data.setRef_NO(rs.getString("REF_NO"));
        }catch (Exception e){
            e.printStackTrace();}
        return data;
    }
}
