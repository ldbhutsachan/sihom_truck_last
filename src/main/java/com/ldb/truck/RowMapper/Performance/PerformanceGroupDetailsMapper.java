package com.ldb.truck.RowMapper.Performance;

import com.ldb.truck.Model.Login.Performance.performaceGroupDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceGroupDetailsMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        performaceGroupDetails data = new performaceGroupDetails();
        try {
            data.setPerFORMANCEREDATE(rs.getString("perFORMANCEREDATE"));
            data.setLAHUD_POYLOD(rs.getString("lAHUD_POYLOD"));
            data.setPerFORMANCEDATE(rs.getString("perFORMANCEDATE"));
            data.setIN_DATE(rs.getString("iN_DATE"));
            data.setCUSTOMER_ID(rs.getString("cUSTOMER_ID"));
            data.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
            data.setPRO_NAME(rs.getString("pRO_NAME"));
            data.setPRO_TYPE(rs.getString("pRO_TYPE"));
            data.setTO_CUSTOMER(rs.getString("tO_CUSTOMER"));
            data.setPerFORMANCENO(rs.getString("perFORMANCENO"));
            data.setTOTAL_PRICE(rs.getString("tOTAL_PRICE"));
            //CURRENCY
            data.setCurrency(rs.getString("CURRENCY"));
            data.setStaff_Cur(rs.getString("STAFF_BIALIENG_CUR"));
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
