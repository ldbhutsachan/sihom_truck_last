package com.ldb.truck.Dao.OweDao;

import com.ldb.truck.Dao.PayDao.PayInDao;
import com.ldb.truck.Model.Login.Owe.GroupCurr;
import com.ldb.truck.Model.Login.Owe.Owe;
import com.ldb.truck.Model.Login.Owe.OwePayBack;
import com.ldb.truck.Model.Login.Owe.sumOweFooter;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.RowMapper.OweMapper.OweMapper;
import com.ldb.truck.RowMapper.OweMapper.OwePayBackMapper;
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
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class OweDao implements OweImDao {
    private static final Logger log = LogManager.getLogger(OweDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL = "";

    @Override
    public List<Owe> ListOweDetails() {
        List<Owe> result = new ArrayList<>();
        try {
            SQL = "SELECT * FROM V_OWE_WAITPAYMENT WHERE PAY_STATUS='O' order by PAY_DATE asc ";
            result = EBankJdbcTemplate.query(SQL, new OweMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OwePayBack> OweReport(ResFromDateReq resFromDateReq) {
        List<OwePayBack> result = new ArrayList<>();
        try {
            if(resFromDateReq.getStartDate() == null){
                SQL = "select CUSTOMER_ID,CUSTOMER_NAME,BILLNO,PAY_DATE,PAY_AMOUNT,PAY_STATUS,calTotalDate,\n" +
                        "                    (CASE When TOTALDATE='01' OR   TOTALDATE='00'  Then payamount Else 0 END)  as payAmount01,\n" +
                        "                    (CASE When TOTALDATE='02' Then payamount Else 0 END)  as payAmount02,\n" +
                        "                    (CASE When TOTALDATE='03' Then payamount Else 0 END)  as payAmount03,\n" +
                        "                    (CASE When TOTALDATE='04' Then payamount Else 0 END)  as payAmount04,currency\n" +
                        "                    from V_PRINT_REPORT_OWE";
            }else{
                SQL = "select CUSTOMER_ID,CUSTOMER_NAME,BILLNO,PAY_DATE,PAY_AMOUNT,PAY_STATUS,calTotalDate,\n" +
                        "                    (CASE When TOTALDATE='01' OR   TOTALDATE='00'  Then payamount Else 0 END)  as payAmount01,\n" +
                        "                    (CASE When TOTALDATE='02' Then payamount Else 0 END)  as payAmount02,\n" +
                        "                    (CASE When TOTALDATE='03' Then payamount Else 0 END)  as payAmount03,\n" +
                        "                    (CASE When TOTALDATE='04' Then payamount Else 0 END)  as payAmount04,currency\n" +
                        "                    from V_PRINT_REPORT_OWE where PAY_DATE between '"+resFromDateReq.getStartDate()+"' and '"+resFromDateReq.getEndDate()+"'";
            }
            result = EBankJdbcTemplate.query(SQL, new OwePayBackMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<sumOweFooter> getSumfooter(ResFromDateReq resFromDateReq) {
        List<sumOweFooter> result = new ArrayList<>();
        try {
            SQL = "select * from V_SUMFOOTER_DETAILS";
            return EBankJdbcTemplate.query(SQL, new RowMapper<sumOweFooter>() {
                @Override
                public sumOweFooter mapRow(ResultSet rs, int rowNum) throws SQLException {
                    sumOweFooter tr = new sumOweFooter();
                    tr.setCusId(rs.getString("CUSTOMER_ID"));
                    tr.setSumPayAmount01(rs.getString("payAmount01"));
                    tr.setSumPayAmount02(rs.getString("payAmount02"));
                    tr.setSumPayAmount03(rs.getString("payAmount03"));
                    tr.setSumPayAmount04(rs.getString("payAmount04"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //----
    @Override
    public List<GroupCurr> getGroupCurrSumfooter(ResFromDateReq resFromDateReq) {
        List<GroupCurr> result = new ArrayList<>();
        try {
            if(resFromDateReq.getStartDate() == null){
                SQL = "    select CUSTOMER_ID,\n" +
                        "    sum (CASE When CURRENCY='LAK' Then payamount Else 0 END)as lakCurr,\n" +
                        "    sum(CASE When CURRENCY='USD' Then payamount Else 0 END)  as usdCurr,\n" +
                        "    sum(CASE When CURRENCY='THB' Then payamount Else 0 END)  as thbCurr,\n" +
                        "    sum(CASE When CURRENCY='CNY' Then payamount Else 0 END)  as cnyCurr\n" +
                        "    from V_PRINT_REPORT_OWE  group by customer_id ";

            }else {
                SQL = "    select CUSTOMER_ID,\n" +
                        "    sum (CASE When CURRENCY='LAK' Then payamount Else 0 END)as lakCurr,\n" +
                        "    sum(CASE When CURRENCY='USD' Then payamount Else 0 END)  as usdCurr,\n" +
                        "    sum(CASE When CURRENCY='THB' Then payamount Else 0 END)  as thbCurr,\n" +
                        "    sum(CASE When CURRENCY='CNY' Then payamount Else 0 END)  as cnyCurr\n" +
                        "    from V_PRINT_REPORT_OWE   where PAY_DATE between '"+resFromDateReq.getStartDate()+"' and '"+resFromDateReq.getEndDate()+"'  group by customer_id ";
            }
             return EBankJdbcTemplate.query(SQL, new RowMapper<GroupCurr>() {
                @Override
                public GroupCurr mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GroupCurr tr = new GroupCurr();
                    tr.setCusId(rs.getString("CUSTOMER_ID"));
                    tr.setLakCurr(rs.getString("lakCurr"));
                    tr.setUsdCurr(rs.getString("usdCurr"));
                    tr.setThbCurr(rs.getString("thbCurr"));
                    tr.setCnyCurr(rs.getString("cnyCurr"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OwePayBack> OweReportByDate(ResFromDateReq resFromDateReq) {
        List<OwePayBack> result = new ArrayList<>();
        try {
            SQL = "select CUSTOMER_ID,CUSTOMER_NAME,BILLNO,PAY_DATE,PAY_AMOUNT,PAY_STATUS,calTotalDate,\n" +
                    "(CASE When TOTALDATE='01' Then payamount Else 0 END)  as payAmount01,\n" +
                    "(CASE When TOTALDATE='02' Then payamount Else 0 END)  as payAmount02,\n" +
                    "(CASE When TOTALDATE='03' Then payamount Else 0 END)  as payAmount03,\n" +
                    "(CASE When TOTALDATE='04' Then payamount Else 0 END)  as payAmount04\n" +
                    "from V_PRINT_REPORT_OWE WHERE pay_date BETWEEN  '"+resFromDateReq.getStartDate()+"' AND  '"+resFromDateReq.getEndDate()+"' ";
            result = EBankJdbcTemplate.query(SQL, new OwePayBackMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<sumOweFooter> getSumfooterByDate(ResFromDateReq resFromDateReq) {
        List<sumOweFooter> result = new ArrayList<>();
        try {
            SQL = "select * from V_SUMFOOTER_DETAILS  where pay_date BETWEEN  '"+resFromDateReq.getStartDate()+"' AND  '"+resFromDateReq.getEndDate()+"' ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<sumOweFooter>() {
                @Override
                public sumOweFooter mapRow(ResultSet rs, int rowNum) throws SQLException {
                    sumOweFooter tr = new sumOweFooter();
                    tr.setCusId(rs.getString("CUSTOMER_ID"));
                    tr.setSumPayAmount01(rs.getString("payAmount01"));
                    tr.setSumPayAmount02(rs.getString("payAmount02"));
                    tr.setSumPayAmount03(rs.getString("payAmount03"));
                    tr.setSumPayAmount04(rs.getString("payAmount04"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
