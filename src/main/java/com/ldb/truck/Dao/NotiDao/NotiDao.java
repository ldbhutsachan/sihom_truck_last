package com.ldb.truck.Dao.NotiDao;
import com.ldb.truck.Model.Login.Noti.*;
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
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class NotiDao implements NotiDaoIn{
    private static final Logger log = LogManager.getLogger(NotiDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL="";
    @Override
    public List<NotiDetails> notiDetails(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
            SQL="select count(DETAILSTATUS) as DETAILSTATUS from V_NOTI_TB_DETAILS where DETAILSTATUS >='7'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiDetails>() {
                @Override
                public NotiDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiDetails tr =new NotiDetails();
                    tr.setDetailsStatus(rs.getDouble("DETAILSTATUS"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<NotiInvoice> notiInvoice(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
            SQL="select count(inVoiceStatus) as inVoiceStatus from V_NOTI_INVOICE where inVoiceStatus >='7'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiInvoice>() {
                @Override
                public NotiInvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiInvoice tr =new NotiInvoice();
                    tr.setInvoiceStatus(rs.getDouble("inVoiceStatus"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<NotiPerFormace> noTiPer(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
            SQL="select count(perstatus) as PERSTATUS from V_NOTI_PERFORMANCE where perstatus>='7'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiPerFormace>() {
                @Override
                public NotiPerFormace mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiPerFormace tr =new NotiPerFormace();
                    tr.setPerStatus(rs.getDouble("PERSTATUS"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NotiDetails> notiDetal(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
//            SQL="select COUNT(*) AS TOTAL_DETAILS from TB_DETAILS where D_STATUS='N'";
            SQL="select COUNT(*) AS TOTAL_DETAILS from TB_DETAILS a inner join LOGIN b on a.userId = b.KEY_ID where a.D_STATUS='N' and b.BRANCH ='"+noticeReq.getBranch()+"' ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiDetails>() {
                @Override
                public NotiDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiDetails tr =new NotiDetails();
                    tr.setDetailsStatus(rs.getDouble("TOTAL_DETAILS"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NotiInvoice> Invoice(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
//            SQL="select COUNT(*) AS TOTAL_INVOICE from INVOICE  where STATUS='N'";
            SQL="select COUNT(*) AS TOTAL_INVOICE from INVOICE a inner join LOGIN b on a.userId = b.KEY_ID where a.STATUS='N' and b.BRANCH ='"+noticeReq.getBranch()+"' ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiInvoice>() {
                @Override
                public NotiInvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiInvoice tr =new NotiInvoice();
                    tr.setInvoiceStatus(rs.getDouble("TOTAL_INVOICE"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NotiPerFormace> noPer(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
//            SQL="SELECT COUNT(*) AS TOTAL_PERFORMANCE FROM TB_PERFORMANCE WHERE  STATUS='N'";
            SQL="SELECT COUNT(*) AS TOTAL_PERFORMANCE FROM TB_PERFORMANCE a inner join LOGIN b on a.userId = b.KEY_ID WHERE  a.STATUS='N' and b.BRANCH ='"+noticeReq.getBranch()+"' ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiPerFormace>() {
                @Override
                public NotiPerFormace mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiPerFormace tr =new NotiPerFormace();
                    tr.setPerStatus(rs.getDouble("TOTAL_PERFORMANCE"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OweNoti> oweNoti(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
//            SQL="SELECT COUNT(*) AS TOTAL_OWE FROM PAYMENT where PAY_STATUS='O'";
            SQL="SELECT COUNT(*) AS TOTAL_OWE FROM PAYMENT a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH ='"+noticeReq.getBranch()+"' AND a.PAY_STATUS='0'";


            return EBankJdbcTemplate.query(SQL, new RowMapper<OweNoti>() {
                @Override
                public OweNoti mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OweNoti tr =new OweNoti();
                    tr.setTotal_owe(rs.getDouble("TOTAL_OWE"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<notiPay> notiPayList(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
//            SQL="select COUNT(*) AS PAY_STATUS  from PAYMENT where PAY_STATUS='N'";
            SQL="select COUNT(*) AS PAY_STATUS  from PAYMENT a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH ='"+noticeReq.getBranch()+"' AND a.PAY_STATUS='N'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<notiPay>() {
                @Override
                public notiPay mapRow(ResultSet rs, int rowNum) throws SQLException {
                    notiPay tr =new notiPay();
                    tr.setTotal_pay(rs.getDouble("PAY_STATUS"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<NotiFuel> NotiFuelP(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
            SQL="select COUNT(*) AS FUEL_STATUS  from TB_DETAILS a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH ='"+noticeReq.getBranch()+"' AND a.FUEL_STATUS='P'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiFuel>() {
                @Override
                public NotiFuel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiFuel tr =new NotiFuel();
                    tr.setTotal_FuelPaid(rs.getDouble("FUEL_STATUS"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<NotiFuel> NotiFuelUP(NoticeReq noticeReq) {
        try {
            log.info("sql:"+SQL);
            SQL="select COUNT(*) AS FUEL_STATUS  from TB_DETAILS a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH ='"+noticeReq.getBranch()+"' AND a.FUEL_STATUS='UP'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<NotiFuel>() {
                @Override
                public NotiFuel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NotiFuel tr =new NotiFuel();
                    tr.setTotal_FuelUnpaid(rs.getDouble("FUEL_STATUS"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //==================================>show noti in box<===========================================================

}
