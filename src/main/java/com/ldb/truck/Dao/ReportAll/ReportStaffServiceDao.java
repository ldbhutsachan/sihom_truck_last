package com.ldb.truck.Dao.ReportAll;

import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
@Repository
public class ReportStaffServiceDao implements ReportStaffDao {
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    @Override
    public List<ReportStaff> ReportDetails(@RequestBody ReportStaffReq detailsReq) {
       try
       {
           String sql="";
           if(detailsReq.getStartDate()== null){
               sql="select * from V_RE_STAFF  ";
           }else {
               sql = "select * from V_RE_STAFF where OUT_DATE between '" + detailsReq.getStartDate() + "' and '" + detailsReq.getEndDate() + "' ";
           }
           return  EBankJdbcTemplate.query(sql, new RowMapper<ReportStaff>() {
               @Override
               public ReportStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                   ReportStaff tr =new ReportStaff();
                   tr.setSTAFT_ID(rs.getString("STAFT_ID"));
                   tr.setSTAFT_NAME(rs.getString("STAFT_NAME"));
                   tr.setSTAFT_SURNAME(rs.getString("STAFT_SURNAME"));
                   tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                   tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                   tr.setF_BRANCH (rs.getString("F_BRANCH"));
                   tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                   tr.setPROVINCE(rs.getString("PROVINCE"));
                   tr.setDETAIL(rs.getString("DETAIL"));
                   tr.setOUT_DATE(rs.getString("OUT_DATE"));
                   tr.setIN_DATE(rs.getString("IN_DATE"));
                   tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                   tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                   tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
                   return tr;
               }
           });
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }
    //---Report Staff
    @Override
    public List<ReportStaff> ReportDetailsStaff(@RequestBody ReportStaffReq detailsReq) {
        try
        {
            String sql="";
            if(detailsReq.getStartDate()== null){
                sql="select * from V_RE_STAFF  ";
            }else {
                sql = "select * from V_RE_STAFF where OUT_DATE between '" + detailsReq.getStartDate() + "' and '" + detailsReq.getEndDate() + "' ";
            }
            return  EBankJdbcTemplate.query(sql, new RowMapper<ReportStaff>() {
                @Override
                public ReportStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStaff tr =new ReportStaff();
                    tr.setSTAFT_ID(rs.getString("STAFT_ID"));
                    tr.setSTAFT_NAME(rs.getString("STAFT_NAME"));
                    tr.setSTAFT_SURNAME(rs.getString("STAFT_SURNAME"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setF_BRANCH (rs.getString("F_BRANCH"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setPROVINCE(rs.getString("PROVINCE"));
                    tr.setDETAIL(rs.getString("DETAIL"));
                    tr.setOUT_DATE(rs.getString("OUT_DATE"));
                    tr.setIN_DATE(rs.getString("IN_DATE"));
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setStaff_Cur(rs.getString("STAFF_BIALIENG_CUR"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
