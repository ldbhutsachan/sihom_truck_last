package com.ldb.truck.RowMapper.Pay;

import com.ldb.truck.Model.Login.Pay.PayTxnDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayTxnMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        PayTxnDetails data = new PayTxnDetails();
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
            data.setTotalDay(rs.getString("TOTALDAY"));
            data.setPaymentAmounts(rs.getDouble("PAY_AMOUNTS"));
            data.setNoPayAmount(rs.getString("NOPAY_AMOUNT"));
            data.setCurrency(rs.getString("CURRENCY"));
            data.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
