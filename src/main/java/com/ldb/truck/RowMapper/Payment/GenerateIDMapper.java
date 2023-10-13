package com.ldb.truck.RowMapper.Payment;

import org.springframework.jdbc.core.RowMapper;
import com.ldb.truck.Model.Login.Payment.GenerateInvoiceID;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerateIDMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenerateInvoiceID data = new GenerateInvoiceID();
        try{
            data.setINVOICE_ID(rs.getString("INVOICE_ID"));
            data.setPrintDate(rs.getString("printDate"));
        }catch (Exception e){
            e.printStackTrace();
           return data;
        }
        return data;
    }
}
