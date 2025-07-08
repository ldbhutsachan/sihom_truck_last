package com.ldb.truck.Service;


import com.ldb.truck.Model.ReportItemInOutModel.ReportItemInOutModel;
import com.ldb.truck.Model.ReportItemInOutModel.ReportItemInOutModelReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class ReportItemInOutService {
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    private List<ReportItemInOutModel> getReportItemInOutModel(ReportItemInOutModelReq req){
            String startDate  = req.getStartDate();
            String endDate  = req.getEndDate();
            String itemId = req.getItemId();
            String conDate = "";
            String conItem = "";
            if(!"".equals(startDate)){
                conDate = "\nand  ";
            }else {
                conDate="";
            }
            if(!"all".equals(itemId)){
                conItem ="\n";
            }else {
                conItem = "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("select * from ");
            sb.append(conDate);
            sb.append(conItem);
            String sql = sb.toString();
            return EBankJdbcTemplate.query(sql, new RowMapper<ReportItemInOutModel>() {
                @Override
                public ReportItemInOutModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportItemInOutModel tr = new ReportItemInOutModel();
                    tr.setItemId(rs.getString(""));
                    return tr;
                }
            });
    }




}
