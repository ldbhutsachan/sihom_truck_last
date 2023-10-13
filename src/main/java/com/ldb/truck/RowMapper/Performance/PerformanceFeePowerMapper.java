package com.ldb.truck.RowMapper.Performance;

import com.ldb.truck.Model.Login.Performance.performance_FeePower;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceFeePowerMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        performance_FeePower data = new performance_FeePower();
        try {
           // data.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
            data.setSaiNumMun(rs.getString("SAINUMMUN"));
            data.setPriceNumMun(rs.getString("PRIECENUMNUN"));
            data.setTotalNumMun(rs.getString("totalNumMun"));
            data.setKimKiLo(rs.getString("KIM_KILO"));
            data.setLaiYaThang(rs.getString("LAIYATHANG"));
            data.setLekMai(rs.getString("LAST_LEKMAI"));
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
