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
        sql ="select * from v_dashboard group by A";
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
