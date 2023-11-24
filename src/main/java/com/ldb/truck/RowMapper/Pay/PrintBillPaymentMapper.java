package com.ldb.truck.RowMapper.Pay;
import com.ldb.truck.Model.Login.Pay.PrintBillPayment;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PrintBillPaymentMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        PrintBillPayment data = new PrintBillPayment();
        try{
            //-
            data.setCusId(rs.getString("CUSTOMER_ID"));
            data.setCusName(rs.getString("CUSTOMER_NAME"));
            data.setLocation(rs.getString("PROVINCE"));
            data.setPrintDate(rs.getString("PAY_DATE"));
            data.setBillNo(rs.getString("BILLNO"));
            data.setProId(rs.getString("PRO_ID"));
            data.setProName_type(rs.getString("PRO_TYPE"));
            data.setPriceUnit(rs.getString("PRODUCT_AMOUNTS"));
            data.setProTotal(rs.getString("PRICES"));
            data.setAmountTotal(rs.getDouble("TOTAL_PRICES"));
            data.setAmountTotalS(rs.getString("TOTAL_PRICE"));
            data.setPayAmount(rs.getDouble("PAY_AMOUNT"));
            data.setNoPayAmount(rs.getDouble("NOPAY_AMOUNT"));
            data.setCurrency(rs.getString("CURRENCY"));
            data.setNextDatePay(rs.getString("nextDatePay"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
