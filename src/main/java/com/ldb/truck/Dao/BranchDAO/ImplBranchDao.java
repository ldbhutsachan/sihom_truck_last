package com.ldb.truck.Dao.BranchDAO;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Branch.Branch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImplBranchDao implements BranchDao{
    private static final Logger log = LogManager.getLogger(ImplBranchDao.class);

    //==================connnection==================
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    //================================================
    String query="";

    @Override
    public List<Branch> getBranch(BrachReq brachReq) {
      log.info("show get info:======999999:"+brachReq.getUserId());
      log.info("show get info:======branch:"+brachReq.getBranchNo());
        try{
            query="select a.KEY_ID ,a.B_NAME ,a.B_TEL ,a.B_LOCATION ,a.EMAIL ,a.userId ,b.USER_LOGIN ,a.createDate\n" +
                    "from LOGIN b inner join TB_BRANCH a on a.userId  =b.KEY_ID where a.key_id ='"+brachReq.getBranchNo()+"'";
            return EBankJdbcTemplate.query(query, new RowMapper<Branch>() {
                @Override
                public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Branch tr = new Branch();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setB_name(rs.getString("B_NAME"));
                    tr.setB_tel(rs.getString("B_TEL"));
                    tr.setLocation(rs.getString("B_LOCATION"));
                    tr.setEmail(rs.getString("EMAIL"));
                    tr.setUserName(rs.getString("userId"));
                    tr.setCreateDate(rs.getString("createDate"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveDataBranch(BrachReq brachReq) {
        try{
        query="insert into TB_BRANCH (B_NAME,B_TEL,B_LOCATION,EMAIL,userId,createDate) VALUES (?,?,?,?,?,now())";
            List<String> paraList = new ArrayList<>();
            paraList.add(brachReq.getB_name());
            paraList.add(brachReq.getB_tel());
            paraList.add(brachReq.getLocation());
            paraList.add(brachReq.getEmail());
            paraList.add(brachReq.getUserId());
            return EBankJdbcTemplate.update(query,paraList.toArray());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateDataBranch(BrachReq brachReq) {
        try{
            query="update TB_BRANCH set B_NAME=?,B_TEL=?,B_LOCATION=?,EMAIL=?,userId=?,createDate=now() WHERE KEY_ID=?";
            List<String> paraList = new ArrayList<>();
            paraList.add(brachReq.getB_name());
            paraList.add(brachReq.getB_tel());
            paraList.add(brachReq.getLocation());
            paraList.add(brachReq.getEmail());
            paraList.add(brachReq.getUserId());

            paraList.add(brachReq.getKey_id());
            return EBankJdbcTemplate.update(query,paraList.toArray());
        }catch (Exception e){e.printStackTrace();}
        return -1;
    }

    @Override
    public int delDataBranch(BrachReq brachReq) {
        try{
            query="delete  FROM  TB_BRANCH  WHERE KEY_ID=?";
            List<String> paraList = new ArrayList<>();
            paraList.add(brachReq.getKey_id());
            return EBankJdbcTemplate.update(query,paraList.toArray());
        }catch (Exception e){e.printStackTrace();}
        return -1;
    }
    //=================================================

}
