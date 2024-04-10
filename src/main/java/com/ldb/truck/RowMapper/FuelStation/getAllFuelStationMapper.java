package com.ldb.truck.RowMapper.FuelStation;

import com.ldb.truck.Model.Login.FuelStation.FuelStationOut;
import com.ldb.truck.Model.Login.customer.CustomerOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getAllFuelStationMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        FuelStationOut data = new FuelStationOut();
        try {

            data.setFuelStationId(rs.getString("FUEL_STATION_ID"));
            data.setFuelStationName(rs.getString("FUEL_STATION_NAME"));
            data.setAddress(rs.getString("ADDRESS"));
            data.setVillage(rs.getString("VILLAGE"));
            data.setDistrict(rs.getString("DISTRICT"));
            data.setProvince(rs.getString("PROVICNE"));
            data.setMobile(rs.getString("MOBILE"));
            data.setEmail(rs.getString("EMAIL"));
            data.setId(rs.getInt("KEY_ID"));
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
