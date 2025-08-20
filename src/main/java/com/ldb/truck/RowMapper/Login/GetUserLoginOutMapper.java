package com.ldb.truck.RowMapper.Login;

import com.ldb.truck.Model.Login.Login.GetUserLoginOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserLoginOutMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        GetUserLoginOut data = new GetUserLoginOut ();
        try {
            data.setRole(rs.getString("ROLE"));
            data.setStaftId(rs.getString("USERID"));
            data.setStaftName(rs.getString("USER_LOGIN"));
            data.setStatus(rs.getString("STATUS"));
            data.setToKen(rs.getString("TOKEN"));
            data.setBranch(rs.getString("BRANCH"));
            data.setDepartment(rs.getString("DEPARTMENT"));
            data.setSprit_role(rs.getString("SPRIT_ROLE"));


            data.setBranchNo(rs.getString("branchNo"));
            data.setBranchName(rs.getString("branchName"));
            data.setBorNo(rs.getString("broNo"));
            data.setBorName(rs.getString("borName"));
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
}
