package com.ldb.truck.RowMapper.OweMapper;
import com.ldb.truck.Model.Login.Owe.Owe;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class OweMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Owe data = new Owe();
        try{
            data.setBillNo(rs.getString("BILLNO"));
            data.setPayDate(rs.getString("PAY_DATE"));
            data.setInvoiceNo(rs.getString("INVOICE_NO"));
            data.setPaymentType(rs.getString("PAYMENT_TYPE"));
            data.setBankName(rs.getString("BANKNAME"));
            data.setRefNo(rs.getString("REF"));
            data.setCusId(rs.getString("CUSTOMER_ID"));
            data.setCusName(rs.getString("CUSTOMER_NAME"));
            data.setMoBile(rs.getString("MOBILE1"));
            data.setAmount(rs.getString("AMOUNT"));
            data.setPayAmount(rs.getString("PAY_AMOUNT"));
            data.setStatus(rs.getString("PAY_STATUS"));
            data.setTotalDay(rs.getString("totalDay"));
            data.setPaymentAmounts(rs.getDouble("PAY_AMOUNTS"));
            data.setNoPaymentAmount(rs.getString("NOPAYAMOUNT"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
