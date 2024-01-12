package com.ldb.truck.Service.DashBoardService;

import com.ldb.truck.Model.DashBoard.DashBoard;
import com.ldb.truck.Model.DashBoard.DashBoardReq;
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
public class DashBoardImpl {
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String sql="";
    public List<DashBoard> DashBoardShow(DashBoardReq dashBoardReq){
        List<DashBoard> result = new ArrayList<>();
        try{
            if (dashBoardReq.getStartDate().equals(null) || dashBoardReq.getEndDate().equals(null)){
                sql ="select  A,createDate,sum(amt_all) as amt_all,sum(amt_Done) as amt_Done,sum(amt_noDone) as amt_noDone," +
                        "sum(amount_No) as amount_No,sum(amount_Done) as amount_Done\n" +
                        "from v_dashboard group by A,createDate";
            }else {
                sql ="select  A,createDate,sum(amt_all) as amt_all,sum(amt_Done) as amt_Done,sum(amt_noDone) as amt_noDone," +
                        "sum(amount_No) as amount_No,sum(amount_Done) as amount_Done\n" +
                        "from v_dashboard where createDate between '"+dashBoardReq.getStartDate()+"' and '"+dashBoardReq.getEndDate()+"' group by A,createDate";

            }

        return EBankJdbcTemplate.query(sql, new RowMapper<DashBoard>() {
            @Override
            public DashBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                DashBoard tr = new DashBoard();
                tr.setTypeGet(rs.getString("A"));
                tr.setAmt_All(rs.getString("amt_all"));
                tr.setAmt_Done(rs.getString("amt_Done"));
                tr.setAmt_NoDone(rs.getString("amt_noDone"));
                tr.setAmount_Done(rs.getString("amount_Done"));
                tr.setAmount_NoDone(rs.getString("amount_No"));
                return tr;
            }
        });
        }catch (Exception e){
        e.printStackTrace();
        }
    return result;
    }
    public List<DashBoard> DashBoardShowfORpAYcAR(DashBoardReq dashBoardReq){
        List<DashBoard> result = new ArrayList<>();
        try{
            if (dashBoardReq.getStartDate().equals(null) || dashBoardReq.getEndDate().equals(null)){
                sql ="SELECT * FROM REPORT_SUM_DASHBOARD_PAY";
            }else {
                sql ="SELECT * FROM REPORT_SUM_DASHBOARD_PAY where createDate between '"+dashBoardReq.getStartDate()+"' and '"+dashBoardReq.getEndDate()+"' group by A,createDate";

            }
        return EBankJdbcTemplate.query(sql, new RowMapper<DashBoard>() {
            @Override
            public DashBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                DashBoard tr = new DashBoard();
                tr.setTypeGet(rs.getString("A"));
                tr.setAmt_All(rs.getString("amt_all"));
                tr.setAmt_Done(rs.getString("amt_Done"));
                tr.setAmt_NoDone(rs.getString("amt_noDone"));
                tr.setAmount_Done(rs.getString("amount_Done"));
                tr.setAmount_NoDone(rs.getString("amount_No"));
                return tr;
            }
        });
        }catch (Exception e){
        e.printStackTrace();
        }
    return result;
    }

}
