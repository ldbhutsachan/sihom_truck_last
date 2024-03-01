package com.ldb.truck.Dao.ProfileDao;

import com.ldb.truck.Dao.Performance.PerformanceDao;
import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.Profile.ProfileReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Repository
public class ProfileDao {
    private static final Logger log = LogManager.getLogger(ProfileDao.class);
    //get bill No  ----T-10001 like
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    public List<Profile> getProfileInfo(BrachReq profileReq){
        log.info("get data info:"+profileReq.getToKen());
        try{
            String SQL = "select b.KEY_ID as userId ,b.USER_LOGIN as userName ,b.ROLE,b.BRANCH as branchNo ,a.B_NAME as banchName \n" +
                    "from LOGIN b inner join TB_BRANCH a on a.KEY_ID  =b.BRANCH  where token='"+profileReq.getToKen()+"'";
            log.info("SQL:"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<Profile>() {
                @Override
                public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Profile tr = new Profile();
                    tr.setUserId(rs.getString("userId"));
                    tr.setUserName(rs.getString("userName"));
                    tr.setRole(rs.getString("ROLE"));
                    tr.setBranchNo(rs.getString("branchNo"));
                    tr.setBranchName(rs.getString("banchName"));
                    return tr;
                }
            });
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
    }

    public List<Profile> getProfileInfoByToken(String toKen){
        log.info("get data info:"+toKen);
        try{
            String SQL = "select b.KEY_ID as userId ,b.USER_LOGIN as userName ,b.ROLE,b.BRANCH as branchNo ,a.B_NAME as banchName \n" +
                    "from LOGIN b inner join TB_BRANCH a on a.KEY_ID  =b.BRANCH  where token='"+toKen+"'";
            log.info("SQL:"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<Profile>() {
                @Override
                public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Profile tr = new Profile();
                    tr.setUserId(rs.getString("userId"));
                    tr.setUserName(rs.getString("userName"));
                    tr.setRole(rs.getString("ROLE"));
                    tr.setBranchNo(rs.getString("branchNo"));
                    tr.setBranchName(rs.getString("banchName"));
                    return tr;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
