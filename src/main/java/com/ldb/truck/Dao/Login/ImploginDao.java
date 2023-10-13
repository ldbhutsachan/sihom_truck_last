package com.ldb.truck.Dao.Login;

import com.ldb.truck.Model.Login.Login.GetUserLoginOut;
import com.ldb.truck.Model.Login.Login.LoginOut;
import com.ldb.truck.Model.Login.Login.LoginReq;
import com.ldb.truck.RowMapper.Login.GetUserLoginOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class ImploginDao implements loginDao {

    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    @Override
    public List<LoginOut> getAllCustomer() {
        return null;
    }
    @Override
    public List<GetUserLoginOut> Login(LoginReq loginReq) {

        List<GetUserLoginOut> data = new ArrayList<>();
        try {
            String SQL = "select USER_LOGIN,ROLE,USERID ,STATUS ,TOKEN  from LOGIN   WHERE STATUS = 'A' AND USER_LOGIN = '"+loginReq.getUser()+"' AND PASSOWORD =  '"+loginReq.getPassword()+"'  ";
            //System.out.println(SQL);
            data = EBankJdbcTemplate.query(SQL , new GetUserLoginOutMapper());
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
    @Override
    public int StoreUser(LoginReq loginReq) {
        return 0;
    }
    @Override
    public int UpdateUser(LoginReq loginReq) {
        return 0;
    }

    @Override
    public int DeleteUser(LoginReq loginReq) {
        return 0;
    }
    @Override
    public List<GetUserLoginOut> getInfoUserLogin(LoginReq loginReq) {
        try {
            String SQL = " SELECT  T2.STAFT_NAME , T1.ROLE , T2.STAFT_ID FROM LOGIN T1 INNER JOIN STAFF T2 \n" +
                    "ON T1.STAFT_ID = T2.KEY_ID  WHERE T2.STATUS = 'A' AND T1.STATUS = 'A' AND T1.USER_LOGIN = '"+loginReq.getUser()+"' ";
                    return  EBankJdbcTemplate.query(SQL, new RowMapper<GetUserLoginOut>() {
                        @Override
                        public GetUserLoginOut mapRow(ResultSet rs, int rowNum) throws SQLException {
                            GetUserLoginOut tr =new GetUserLoginOut();
                            tr.setRole(rs.getString("ROLE"));
                            tr.setStaftId(rs.getString("STAFT_ID"));
                            tr.setStaftName(rs.getString("STAFT_NAME"));
                            return tr;
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
