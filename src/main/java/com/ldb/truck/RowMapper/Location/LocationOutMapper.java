package com.ldb.truck.RowMapper.Location;

import com.ldb.truck.Model.Login.location.LocationOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationOutMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        LocationOut data = new LocationOut();

        try {

            data.setId(rs.getString("KEY_ID"));
            data.setProvince(rs.getString("PROVINCE"));
            data.setDetail(rs.getString("DETAIL"));
            data.setStatus(rs.getString("STATUS"));

        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
}
