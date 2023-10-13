package com.ldb.truck.RowMapper.Performance;


import com.ldb.truck.Model.Login.Performance.performanceheaderGruop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceGroupHeaderMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        performanceheaderGruop data = new performanceheaderGruop();
        try {
            data.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
            data.setSTAFT_ID(rs.getString("STAFT_ID"));
            data.setSTAFFNAME(rs.getString("STAFFNAME"));
            data.setSTAFT_ID1(rs.getString("STAFT_ID1"));
            data.setSTAFFNAME1(rs.getString("STAFFNAME1"));
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
