package com.ldb.truck.Dao.PayDao;

import com.ldb.truck.Dao.Details.DetailsServiceDao;
import com.ldb.truck.Model.Login.Pay.*;
import com.ldb.truck.Model.Login.Payment.PayTxnReport;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.RowMapper.Pay.*;
import com.ldb.truck.RowMapper.Payment.InvoiceMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class PayDao  implements PayInDao{
    private static final Logger log = LogManager.getLogger(PayInDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL ="";
    @Override
    public List<Bank> getListBank() {
        List<Bank> result = new ArrayList<>();
         try{
             SQL="select * from BANK";
             result = EBankJdbcTemplate.query(SQL , new BankMapper());
         }catch (Exception e){
             e.printStackTrace();
         }
        return result;
    }
    @Override
    public List<getBillNo> getBillNo() {
        List<getBillNo> result = new ArrayList<>();
        try
        {
            SQL = "select * from AUTO_BILLNO";
            result = EBankJdbcTemplate.query(SQL, new getBillNoMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int storePayment(PayReq payReq) {
        log.info("payReq"+payReq.getBillNo());
        log.info("next:"+payReq.getNextDatePay());
        try{
            SQL= "insert into PAYMENT (BILLNO,PAY_DATE,INVOICE_NO,PAYMENT_TYPE,BANKNAME,REF,AMOUNT,PAY_AMOUNT,PAY_STATUS,nextDatePay) values (?,now(),?,?,?,?,?,?,?,?)";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(payReq.getBillNo());
            paramList.add(payReq.getInvoiceNo());
            paramList.add(payReq.getPaymentType());
            paramList.add(payReq.getBankName());
            paramList.add(payReq.getRefNo());
            paramList.add(payReq.getAmount());
            paramList.add(payReq.getPayAmount());
            paramList.add(payReq.getStatus());
            paramList.add(payReq.getNextDatePay());
            log.info("SQL:"+SQL);
            return EBankJdbcTemplate.update(SQL, paramList.toArray());

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    ///--storePayOwe  test
    @Override
    public int storePayOwe(PayReq payReq) {
        log.info("payReq"+payReq.getBillNo());
        log.info("NOPAYMENT"+payReq.getNoPayAmount());
        log.info("date"+payReq.getNextDatePay());
        try{
            SQL= "insert into PAYMENT_HIS (NOPAYAMOUNT,BILLNO,PAY_DATE,INVOICE_NO,PAYMENT_TYPE,BANKNAME,REF,AMOUNT,PAY_AMOUNT,PAY_STATUS,nextDatePay) values (?,?,now(),?,?,?,?,?,?,?,?)";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(payReq.getNoPayAmount());
            paramList.add(payReq.getBillNo());
            paramList.add(payReq.getInvoiceNo());
            paramList.add(payReq.getPaymentType());
            paramList.add(payReq.getBankName());
            paramList.add(payReq.getRefNo());
            paramList.add(payReq.getAmount());
            paramList.add(payReq.getPayAmount());
            paramList.add(payReq.getStatus());
            paramList.add(payReq.getNextDatePay());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int setInvoiceStatus(PayReq payReq) {
        try {
            SQL = "update INVOICE set status='Y' where INVOICE_ID ='"+payReq.getInvoiceNo()+"' ";
            return EBankJdbcTemplate.update(SQL);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //--update owe payment
    @Override
    public int setPaymentAmoutAndStatus(PayReq payReq) throws ParseException {
        double allPayAmountAll = DecimalFormat.getNumberInstance().parse(payReq.getAllPayAmountAll()).doubleValue();
        log.info("allPayAmountAll:"+allPayAmountAll);
        double payAmount = DecimalFormat.getNumberInstance().parse(payReq.getPayAmount()).doubleValue();
        log.info("payAmount01:"+payAmount);
        double amount = DecimalFormat.getNumberInstance().parse(payReq.getAmount()).doubleValue();
        log.info("amount:"+payReq.getAmount());
        double totalAmount = amount-payAmount;
        log.info("totalAmount:"+totalAmount);
        try {
            if (totalAmount == 0) {
                SQL = "update PAYMENT set nextDatePay='"+payReq.getNextDatePay()+"',PAY_STATUS='Y' ,PAY_AMOUNT="+allPayAmountAll+"+"+payAmount+", PAY_CASH_AMOUNT="+payAmount+" where BILLNO='" + payReq.getBillNo() + "' OR INVOICE_NO='"+payReq.getInvoiceNo()+"'";
                log.info("SQL:"+SQL);
            } else if (totalAmount != 0) {
                SQL = "update PAYMENT set nextDatePay='"+payReq.getNextDatePay()+"',PAY_STATUS='O' ,PAY_AMOUNT="+allPayAmountAll+"+"+payAmount+", PAY_CASH_AMOUNT="+payAmount+" where BILLNO='" + payReq.getBillNo() + "' OR INVOICE_NO='"+payReq.getInvoiceNo()+"'";
            log.info("SQL:"+SQL);
            }
            EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;

    }
    @Override
    public int setPaymentStatus(PayReq payReq) throws ParseException {
        double allPayAmountAll = DecimalFormat.getNumberInstance().parse(payReq.getAllPayAmountAll()).doubleValue();
        log.info("allPayAmountAll:"+allPayAmountAll);
        double payAmount = DecimalFormat.getNumberInstance().parse(payReq.getPayAmount()).doubleValue();
        double amount = DecimalFormat.getNumberInstance().parse(payReq.getAmount()).doubleValue();
        double totalAmount = amount-payAmount;
        try {
            if(payAmount == amount){
                SQL = "update PAYMENT_HIS set nextDatePay='"+payReq.getNextDatePay()+"',PAY_STATUS='N',PAY_AMOUNT='" + payAmount + "' where  BILLNO='" + payReq.getBillNo() + "'  OR INVOICE_NO='"+payReq.getInvoiceNo()+"'" ;
            } else if (payAmount < amount) {
                SQL = "update PAYMENT_HIS set nextDatePay='"+payReq.getNextDatePay()+"',PAY_STATUS='O',PAY_AMOUNT="+allPayAmountAll+"+"+payAmount+"'  where BILLNO='" + payReq.getBillNo() + "' OR  INVOICE_NO='"+payReq.getInvoiceNo()+"'";
            }
            log.info("sql:"+SQL);
            return EBankJdbcTemplate.update(SQL);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<PrintBillPayment> PrintBillNo(PrintBillPaymentReq paymentReq) {
        List<PrintBillPayment> result = new ArrayList<>();
        try {
            SQL = "select * from V_PRINTPAYMENT where BILLNO='" + paymentReq.getBillNo() + "' ";
            log.info("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL,new PrintBillPaymentMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //Revert Payment by bill no
    @Override
    public int revertPaymentByStatusNo(PrintBillPaymentReq paymentReq) {
        try {
            SQL = "update PAYMENT set PAY_STATUS='Reversal' where BILLNO= ? ";
            System.out.println("sql:"+SQL);
            List<String> paralamList = new ArrayList<>();
            paralamList.add(paymentReq.getBillNo());
            EBankJdbcTemplate.update(SQL,paralamList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //-revertPaymentByStatusHis
    @Override
    public int revertPaymentByStatusHis(PrintBillPaymentReq paymentReq) {
        String sql2 ="";
        try {
            sql2="update PAYMENT_HIS set PAY_STATUS='Reversal' where BILLNO= ? ";
            System.out.println("sql:"+SQL);
            List<String> paralamList = new ArrayList<>();
            paralamList.add(paymentReq.getBillNo());
            EBankJdbcTemplate.update(sql2,paralamList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //--listBillPaymentByNoBlack
    @Override
    public List<PrintBillPayment> listBillPaymentByNoBlack(PrintBillPaymentReq paymentReq) {
        List<PrintBillPayment> result = new ArrayList<>();
        try {
            SQL = "select * from V_PAY_BLACK where INVOICE_ID='" + paymentReq.getBillNo() + "' ";
            log.info("sql:"+SQL);
            return  EBankJdbcTemplate.query(SQL, new RowMapper<PrintBillPayment>() {
                @Override
                public PrintBillPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PrintBillPayment data = new PrintBillPayment();
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
                    data.setPayCashAmount(rs.getDouble("PAY_CASH_AMOUNT"));
                    data.setNextDatePay(rs.getString("nextDatePay"));

                    return data;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<PayTxnDetails> listPayment() {
        List<PayTxnDetails> result = new ArrayList<>();
        try {
            SQL = "select * from V_PAYMENTDETAILS  ";
            log.info("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL,new PayTxnMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<PayTxnDetails> LstPaymentByDate(ResFromDateReq resFromDateReq) {
        List<PayTxnDetails> result = new ArrayList<>();
        try {
            SQL = "select * from V_PAYMENTDETAILS   where PAY_DATE between '"+resFromDateReq.getStartDate()+"' and '"+resFromDateReq.getEndDate()+"' ";
            log.info("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL,new PayTxnMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //--LstPaymentByDateAll
    @Override
    public List<PayTxnDetails> LstPaymentByDateAll(ResFromDateReq resFromDateReq) {
        List<PayTxnDetails> result = new ArrayList<>();

        if(resFromDateReq.getStartDate()==null){
            SQL = "select * from V_PAYMENTDETAILS ";
            log.info("SQL"+SQL);
        }else {
            SQL = "select * from V_PAYMENTDETAILS  where PAY_DATE between '"+resFromDateReq.getStartDate()+"' and '"+resFromDateReq.getEndDate()+"'";
            log.info("SQL"+SQL);
        }
        try {
            return  EBankJdbcTemplate.query(SQL, new RowMapper<PayTxnDetails>() {
                @Override
                public PayTxnDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PayTxnDetails data = new PayTxnDetails();
                        data.setBillNo(rs.getString("BILLNO"));
                        data.setPayDate(rs.getString("PAY_DATE"));
                        data.setInvoiceNo(rs.getString("INVOICE_NO"));
                        data.setPaymentType(rs.getString("PAYMENT_TYPE"));
                        data.setBankName(rs.getString("BANKNAME"));
//                        data.setRefNo(rs.getString("REF"));
                        data.setCusId(rs.getString("CUSTOMER_ID"));
                        data.setCusName(rs.getString("CUSTOMER_NAME"));
                        data.setMoBile(rs.getString("MOBILE1"));
                        data.setAmount(rs.getString("AMOUNT"));
                        data.setPayAmount(rs.getString("PAY_AMOUNT"));
                        data.setStatus(rs.getString("PAY_STATUS"));
                        data.setTotalDay(rs.getString("TOTALDAY"));
                        data.setPaymentAmounts(rs.getDouble("PAY_AMOUNTS"));
                        data.setNoPayAmount(rs.getString("NOPAYAMOUNT"));
                        data.setCurrency(rs.getString("CURRENCY"));
                        data.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
                    return data;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //---ຕິດໜີ້
    @Override
    public List<PayTxnDetails> listPaymentOwe() {
        List<PayTxnDetails> result = new ArrayList<>();
        try {
            SQL = "select * from V_PAYMENTDETAILS  WHERE PAY_STATUS='O' ";
            log.info("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL,new PayTxnMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    ///--
    @Override
    public List<PayTxnDetails> v_popupPay() {
        List<PayTxnDetails> result = new ArrayList<>();
        try {
            SQL = "select BILLNO,INVOICE_NO,CUSTOMER_ID,CUSTOMER_NAME,CURRENCY,PAY_STATUS,\n" +
                    "(AMOUNT) as amount,(PAY_AMOUNT) as pay_amount,(NOPAY_AMOUNT) as NOPAY_AMOUNT\n" +
                    "from V_PAYMENTDETAILS  where pay_status in ('O')  \n" +
                    " group by BILLNO,INVOICE_NO,CUSTOMER_ID,CUSTOMER_NAME,CURRENCY,PAY_STATUS,AMOUNT,PAY_AMOUNT,NOPAY_AMOUNT\n" +
                    " order by BILLNO asc";
            log.info("SQL:"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<PayTxnDetails>() {
                @Override
                public PayTxnDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PayTxnDetails data = new PayTxnDetails();
                    data.setBillNo(rs.getString("BILLNO"));
                    data.setInvoiceNo(rs.getString("INVOICE_NO"));
                    data.setCusId(rs.getString("CUSTOMER_ID"));
                    data.setCusName(rs.getString("CUSTOMER_NAME"));
                    data.setStatus(rs.getString("PAY_STATUS"));
                    data.setNoPayAmount(rs.getString("NOPAY_AMOUNT"));
                    data.setPayAmount(rs.getString("pay_amount"));
                    data.setAmount(rs.getString("amount"));
                    data.setCurrency(rs.getString("CURRENCY"));
                    return data;
                }
            });
           // result = EBankJdbcTemplate.query(SQL,new PayTxnMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
