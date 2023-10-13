package com.ldb.truck.RowMapper.Payment;
import com.ldb.truck.Model.Login.Payment.InvoiceDetail;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class InvoiceDetailMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        InvoiceDetail data = new InvoiceDetail();
        try
        {
            data.setKeyId(rs.getString("key_id"));
            data.setCusID(rs.getString("CUSTOMER_ID"));
            data.setCusName(rs.getString("CUSTOMER_NAME"));
            data.setProType(rs.getString("PRO_TYPE"));
            data.setProAmount(rs.getString("PRODUCT_AMOUNT"));
            data.setPriCE(rs.getString("PRICE"));
            data.setTotalPrice(rs.getString("TOTAL_PRICE"));
            data.setStatus(rs.getString("status"));
            data.setInVoiceID(rs.getString("INVOICE_ID"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
