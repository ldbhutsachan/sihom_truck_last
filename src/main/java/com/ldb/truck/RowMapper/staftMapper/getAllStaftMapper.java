package com.ldb.truck.RowMapper.staftMapper;

import com.ldb.truck.Model.Login.staft.staftOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getAllStaftMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        staftOut data = new staftOut();

        try {

            data.setId(rs.getString("KEY_ID"));
            data.setStaftId(rs.getString("STAFT_ID"));
            data.setName(rs.getString("STAFT_NAME"));
            data.setSurname(rs.getString("STAFT_SURNAME"));
            data.setIdCard(rs.getString("ID_CARD"));
            data.setLicenceId(rs.getString("LICENCE_ID"));
            data.setVerBy(rs.getString("VERIFY_BY"));
            data.setLicenceExp(rs.getString("LICENCE_ID_EXP"));
            data.setVaillage(rs.getString("VILLAGE"));
            data.setProvince(rs.getString("PROVINCE"));
            data.setDistrict(rs.getString("DISTRICT"));
            data.setMobile(rs.getString("MOBILE1"));
            data.setMobile1(rs.getString("MOBILE2"));
            data.setGender(rs.getString("GENDER"));
            data.setGenderStatus(rs.getString("GENDER_STATUS"));
            data.setDateTime(rs.getString("DATE_INSERT"));
            data.setUserId(rs.getString("USERID"));
            data.setImageStaff(rs.getString("IMAGE_STAFF"));
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
}
