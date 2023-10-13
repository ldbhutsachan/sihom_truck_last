package com.ldb.truck.RowMapper.Payment;

import com.ldb.truck.Model.Login.Payment.PrintInvoiceByNo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ldb.truck.Model.Login.Payment.PrintInvoiceByNo;
public class PrintInvoiceOnlyByNoRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        PrintInvoiceByNo data = new PrintInvoiceByNo();
        try {
            data.setKeyId(rs.getString("key_id"));
            data.setPerID(rs.getString("PERFORMANCEBILLNO"));
            data.setCusID(rs.getString("CUSTOMER_ID"));
            data.setCusName(rs.getString("CUSTOMER_NAME"));
            data.setProType(rs.getString("PRO_TYPE"));
            data.setProAmount(rs.getString("PRODUCT_AMOUNT"));
            data.setPriCE(rs.getString("PRICE"));
            data.setTotalPrice(rs.getString("TOTAL_PRICE"));
            data.setStatus(rs.getString("status"));
            data.setInVoiceID(rs.getString("INVOICE_ID"));
            data.setInVoiceDate(rs.getString("INVOICE_DATE"));
            data.setPriCES(rs.getDouble("PRICES"));
            data.setTotalPriceS(rs.getDouble("TOTAL_PRICES"));
            data.setOutDate(rs.getString("OUT_DATE"));
            data.setLaHudPoyLod(rs.getString("LAHUD_POYLOD"));
            data.setViciCleNumber(rs.getString("H_VICIVLE_NUMBER"));
            data.setCurrency(rs.getString("CURRENCY"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

}
