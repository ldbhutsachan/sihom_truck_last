package com.ldb.truck.RowMapper.Pay;

import com.ldb.truck.Model.Login.Pay.Pay;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pay data = new Pay();
        try {
        data.setBillNo(rs.getString("BILLNO"));
        data.setPayDate(rs.getString("PAY_DATE"));
        data.setInvoiceNo(rs.getString("INVOICE_NO"));
        data.setPaymentType(rs.getString("PAYMENT_TYPE"));
        data.setBankName(rs.getString("BANKNAME"));
        data.setRefNo(rs.getString("REF"));
        data.setAmount(rs.getString("AMOUNT"));
        data.setPayAmount(rs.getString("PAY_AMOUNT"));
        data.setStatus(rs.getString("PAY_STATUS"));
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
}
