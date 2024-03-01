package com.ldb.truck.RowMapper.Payment;

import org.springframework.jdbc.core.RowMapper;
import com.ldb.truck.Model.Login.Payment.Invoice;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Invoice data = new Invoice();
        try {
            data.setInVoiceID(rs.getString("INVOICE_ID"));
            data.setInVoiceDate(rs.getString("INVOICE_DATE"));
          //  data.setPerID(rs.getString("PERFORMACE_ID"));
            data.setCusID(rs.getString("CUSTOMER_ID"));
            data.setCusName(rs.getString("CUSTOMER_NAME"));
            data.setRow_Total(rs.getString("ROW_TOTAL"));
            data.setPriceUnit(rs.getString("PRICE_UNIT"));
            data.setInVoiceAmount(rs.getString("INVOICE_AMOUNT"));
            data.setInVoiceStatus(rs.getString("INVOICE_STATUS"));
            data.setTotalDay(rs.getString("s"));
            data.setCurrency(rs.getString("CURRENCY"));
            data.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));

//            data.setRefNO(rs.getString("REF"));
//            data.setPayType(rs.getString("PAYMENT_TYPE"));
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
}
