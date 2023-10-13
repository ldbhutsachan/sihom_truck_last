package com.ldb.truck.RowMapper.Login;


import com.ldb.truck.Model.Login.Login.LoginOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        LoginOut data = new LoginOut();

        try {

            data.setId(rs.getString("KEY_ID"));
            data.setUser(rs.getString("USER_LOGIN"));
            data.setUserId(rs.getString("USERID"));
            data.setRole(rs.getString("ROLE"));
            data.setPassword(rs.getString("PASSOWORD"));
            data.setDateTime(rs.getString("DATE_INSERT"));
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
}
