package com.ldb.truck.Dao.User;

import com.ldb.truck.Model.Login.User.UserLogin;
import com.ldb.truck.Model.Login.User.UserReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Repository
public class UseDao implements UserImplDao {
    private static final Logger log = LogManager.getLogger(UseDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL="";
    @Override
    public List<UserLogin> listUser(UserReq userReq) {
        try{
//            SQL = "select a.KEY_ID,a.USER_LOGIN,a.PASSOWORD ,a.ROLE,a.DATE_INSERT,a.STATUS ,b.STAFT_NAME ,\n" +
//                    "b.STAFT_SURNAME ,b.VILLAGE ,b.DISTRICT ,b.PROVINCE ,b.MOBILE1  from LOGIN a inner join STAFF b on a.STAFT_ID =b.KEY_ID";
        SQL = "select a.KEY_ID,a.USER_LOGIN,a.PASSOWORD,a.ROLE,a.DATE_INSERT,a.STATUS,a.TOKEN,a.BRANCH  from LOGIN a  where a.BRANCH='"+userReq.getBranch()+"'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<UserLogin>() {
                @Override
                public UserLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UserLogin tr =new UserLogin();
                        tr.setKey_Id(rs.getString("KEY_ID"));
                        tr.setUserLogin(rs.getString("USER_LOGIN"));
                      //  tr.setUserSurName(rs.getString("STAFT_SURNAME"));
                        tr.setPassWord(rs.getString("PASSOWORD"));
                        tr.setRole(rs.getString("ROLE"));
                        tr.setCreateDate(rs.getString("DATE_INSERT"));
                        tr.setStatus(rs.getString("STATUS"));
                        tr.setToKen(rs.getString("TOKEN"));
                        tr.setBranch(rs.getString("BRANCH"));
                        //tr.setStaffName(rs.getString("STAFT_NAME"));
                      //  tr.setVillName(rs.getString("VILLAGE"));
                       // tr.setDisName(rs.getString("DISTRICT"));
                      // tr.setProName(rs.getString("PROVINCE"));
                      //  tr.setMobile(rs.getString("MOBILE1"));
                        return tr;
                    }
                });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int storeUser(UserReq userReq) {
        try{
            String fulldata = userReq.getUser_Login();
            String toKen = encryptionPassword(fulldata);
            log.info("Gen Token pass:"+toKen);
           // SQL ="insert into LOGIN (USER_LOGIN,PASSOWORD,ROLE,DATE_INSERT,STATUS,STAFT_ID,userId) VALUES (?,?,?,now(),?,?,?) ";
            SQL ="insert into LOGIN (USER_LOGIN,PASSOWORD,ROLE,DATE_INSERT,STATUS,STAFT_ID,userId,TOKEN,BRANCH,SaveById) VALUES (?,?,?,now(),?,?,?,?,?,?) ";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(userReq.getUser_Login());
            paramList.add(userReq.getPassWord());
            paramList.add(userReq.getRole());
            paramList.add(userReq.getStatus());
            paramList.add(userReq.getStaff_Id());
            paramList.add(userReq.getUserId());
            paramList.add(toKen);
            paramList.add(userReq.getBranch());
            paramList.add(userReq.getSaveById());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int editUser(UserReq userReq) {
        try{
            SQL ="update LOGIN set USER_LOGIN=?,PASSOWORD=?,ROLE=?,STATUS=?,BRANCH=?,STAFT_ID=? where KEY_ID='"+userReq.getKey_Id()+"'";
            log.info("show:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(userReq.getUser_Login());
            paramList.add(userReq.getPassWord());
            paramList.add(userReq.getRole());
            paramList.add(userReq.getStatus());
            paramList.add(userReq.getBranch());
            paramList.add(userReq.getStaff_Id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delUser(UserReq userReq) {
        try{
            SQL ="delete from LOGIN where KEY_ID='"+userReq.getKey_Id()+"' ";
            return EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public String encryptionPassword(String password) {
        String generatedPassword = null;
        String salt = "123456789025699888";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
