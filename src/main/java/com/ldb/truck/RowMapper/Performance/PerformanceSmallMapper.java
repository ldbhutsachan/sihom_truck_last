package com.ldb.truck.RowMapper.Performance;

import com.ldb.truck.Model.Login.Performance.performance_SmallHeaderGruop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceSmallMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        performance_SmallHeaderGruop data = new performance_SmallHeaderGruop();
        try {
            // data.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
           data.setKeyId(rs.getString("key_id"));
           data.setPrintDate(rs.getString("printDate"));
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
