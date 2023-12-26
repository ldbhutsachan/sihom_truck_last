package com.ldb.truck.Dao.ExchangeRate;

import com.ldb.truck.Dao.NotiDao.NotiDao;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRate;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRateReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Repository
public class ExchangeRateDao implements ExchangeRateImplDao{
    private static final Logger log = LogManager.getLogger(ExchangeRateDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL="";
    @Override
    public List<ExchangeRate> listExchangeRate(ExchangeRateReq exchangeRateReq) {
        List<ExchangeRate> result =new ArrayList<>();
        try{
            SQL="select a.KEY_ID,a.USERID,a.EXCHANGERATE,a.TXN_USD,a.TXN_THB,a.TXN_CNY from EXCHANGE a ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<ExchangeRate>() {
                @Override
                public ExchangeRate mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ExchangeRate tr = new ExchangeRate();
                    tr.setKey_Id(rs.getString("KEY_ID"));
                    tr.setUserId(rs.getString("USERID"));
                    tr.setExchangeDate(rs.getString("EXCHANGERATE"));
                    tr.setTxn_Usd(rs.getString("TXN_USD"));
                    tr.setTxn_Thb(rs.getString("TXN_THB"));
                    tr.setTxn_Cny(rs.getString("TXN_CNY"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int storeExchange(ExchangeRateReq exchangeRateReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(exchangeRateReq.getExchangeDate());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try{
            SQL="INSERT INTO EXCHANGE (USERID,EXCHANGERATE,TXN_USD,TXN_THB,TXN_CNY) VALUES (?,?,?,?,?)";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(exchangeRateReq.getUserId());
            paramList.add(sqlDate);
            paramList.add(exchangeRateReq.getTxn_Usd());
            paramList.add(exchangeRateReq.getTxn_Thb());
            paramList.add(exchangeRateReq.getTxn_Cny());
            return EBankJdbcTemplate.update(SQL,paramList.toArray());
        }catch (Exception e){
        e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateExchange(ExchangeRateReq exchangeRateReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(exchangeRateReq.getExchangeDate());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try{
            SQL="UPDATE EXCHANGE SET USERID=?,EXCHANGERATE=?,TXN_THB=?,TXN_USD=?,TXN_CNY=?,txn_Cny=? WHERE KEY_ID='"+exchangeRateReq.getKey_Id()+"'";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(exchangeRateReq.getUserId());
            paramList.add(sqlDate);
            paramList.add(exchangeRateReq.getTxn_Thb());
            paramList.add(exchangeRateReq.getTxn_Usd());
            paramList.add(exchangeRateReq.getTxn_Thb());
            paramList.add(exchangeRateReq.getTxn_Cny());
            return EBankJdbcTemplate.update(SQL,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delExchange(ExchangeRateReq exchangeRateReq) {
        try{
            SQL="DELETE FROM EXCHANGE WHERE KEY_ID='"+exchangeRateReq.getKey_Id()+"'";
            return EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
