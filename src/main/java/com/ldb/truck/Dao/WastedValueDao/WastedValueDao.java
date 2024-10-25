package com.ldb.truck.Dao.WastedValueDao;

import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import com.ldb.truck.Model.Login.WastedValue.WastedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WastedValueDao {

    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

//
//    @Override
//    public  WastedValue (WastedValueReq wastedValueReq) {
//        try
//        {
//            String sql="select * from CEHCK_PAY_STATFF a inner join LOGIN b ON a.userId =b.KEY_ID  where b.BRANCH ='"+wastedValueReq.getBranch()+"' ";
//            return  EBankJdbcTemplate.query(sql, new RowMapper<WastedValue>() {
//                @Override
//                public WastedValue mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    WastedValue tr =new WastedValue();
//                    tr.setAdd_feeOvertime1(rs.getString("add_feeOvertime1"));
//                    tr.setAdd_feeJumPo2(rs.getString("add_feeJumPo2"));
//                    tr.setAdd_feePolish3(rs.getString("add_feePolish3"));
//                    tr.setAdd_feeTaxung4(rs.getString("add_feeTaxung4"));
//                    tr.setAdd_feeTiew5(rs.getString("add_feeTiew5"));
//                    tr.setAdd_feesing(rs.getString("add_feesing"));
//                    tr.setAdd_feesaphan(rs.getString("add_feesaphan"));
//                    tr.setAdd_feeyoktu(rs.getString("add_feeyoktu"));
//                    tr.setAdd_feecontrainer(rs.getString("add_feecontrainer"));
//                    tr.setAdd_feepayang(rs.getString("add_feepayang"));
//
//                    return tr;
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

}
