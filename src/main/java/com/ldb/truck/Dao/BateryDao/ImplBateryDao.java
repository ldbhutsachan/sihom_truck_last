package com.ldb.truck.Dao.BateryDao;

import com.ldb.truck.Model.Login.Batery.Batery;
import com.ldb.truck.Model.Login.Batery.BateryReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
@Repository

public class ImplBateryDao implements BateryDao {
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;


    String SQL="";

    @Override
    public List<Batery> getBateryAll(BateryReq bateryReq) {
        //imageBatery
        try {
            if(bateryReq.getKeyId()==""){
                SQL= "SELECT * FROM MORFAI";
            }else {
                SQL= "SELECT * FROM MORFAI where key_id='"+bateryReq.getKeyId()+"'";
            }
            return EBankJdbcTemplate.query(SQL, new RowMapper<Batery>() {
                @Override
                public Batery mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Batery tr =new Batery();
                    tr.setKeyId(rs.getString("KEY_ID"));
                    tr.setBatNo(rs.getString("ID_MORFAI"));
                    tr.setModalMorfai(rs.getString("MODAL_MORFAI"));
                    tr.setServiceLife(rs.getString("SERVICE_LIFE"));
                    tr.setSizeMorfai(rs.getString("SIZE_MORFAI"));
                    tr.setImageBatery(rs.getString("IMAGE_MORFAI"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveBatery(BateryReq bateryReq) {
        String path="http://khounkham.com/images/batery/";
        String fileName = bateryReq.getImageBatery();
        try{
            SQL="INSERT INTO MORFAI (ID_MORFAI,IMAGE_MORFAI,MODAL_MORFAI,SIZE_MORFAI,SERVICE_LIFE) VALUES (?,?,?,?,?)";
            List<String> paraList = new ArrayList<>();
            paraList.add(bateryReq.getBatNo());
            paraList.add(path+fileName);
            paraList.add(bateryReq.getModalMorfai());
            paraList.add(bateryReq.getSizeMorfai());
            paraList.add(bateryReq.getServiceLife());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int UpdateBatery(BateryReq bateryReq) {
        String path="http://khounkham.com/images/batery/";
        String fileName = bateryReq.getImageBatery();
        try{
            SQL="UPDATE MORFAI SET ID_MORFAI=?,IMAGE_MORFAI=?,MODAL_MORFAI=?,SIZE_MORFAI=?,SERVICE_LIFE=? WHERE KEY_ID=?";
            List<String> paraList = new ArrayList<>();
            paraList.add(bateryReq.getBatNo());
            paraList.add(path+fileName);
            paraList.add(bateryReq.getModalMorfai());
            paraList.add(bateryReq.getSizeMorfai());
            paraList.add(bateryReq.getServiceLife());
            paraList.add(bateryReq.getKeyId());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int UpdateBateryNoUpdateimage(BateryReq bateryReq) {
        String path="http://khounkham.com/images/batery/";
        String fileName = bateryReq.getImageBatery();
        try{
            SQL="UPDATE MORFAI SET ID_MORFAI=?,MODAL_MORFAI=?,SIZE_MORFAI=?,SERVICE_LIFE=? WHERE KEY_ID=?";
            List<String> paraList = new ArrayList<>();
            paraList.add(bateryReq.getBatNo());

            paraList.add(bateryReq.getModalMorfai());
            paraList.add(bateryReq.getSizeMorfai());
            paraList.add(bateryReq.getServiceLife());
            paraList.add(bateryReq.getKeyId());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int DelBatery(BateryReq bateryReq) {
        try{

                SQL="DELETE FROM  MORFAI  WHERE KEY_ID=?";

            List<String> paraList = new ArrayList<>();
            paraList.add(bateryReq.getKeyId());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
