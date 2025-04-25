package com.ldb.truck.Dao.AssetOfficeDAOs;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeModel;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeReq;
import com.ldb.truck.Service.VicicleHeaderService.VicicleHeaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
//++++++++++++++++++++++
@Component
@Repository
public class AssetsOfficeDAOs implements AssetsInterface{
    private static final Logger log = LogManager.getLogger(VicicleHeaderService.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    //List Asset Office
    public List<AssetsOfficeModel > listAssetsOfficeDAOs (AssetsOfficeReq assetsOfficeReq) {
        String SQL;
        try{
            if (assetsOfficeReq.getBranch_id()!= null)
            {
                if (assetsOfficeReq.getCurrency() == null){
                    SQL ="select * from TB_ASSETS WHERE branch_id='"+assetsOfficeReq.getBranch_id()+"'";
                    log.info("SQL: "+SQL);
                }
                else {
                    SQL ="select * from TB_ASSETS WHERE branch_id='"+assetsOfficeReq.getBranch_id()+"' and CURRENCY='"+assetsOfficeReq.getCurrency()+"' ";
                    log.info("SQL: "+SQL);
                }
            }else
            {
                if (assetsOfficeReq.getCurrency() == null){
                    SQL ="select * from TB_ASSETS WHERE BRANCH_OFFICE='"+assetsOfficeReq.getBranch()+"'";
                    log.info("SQL: "+SQL);
                }
                else {
                    SQL ="select * from TB_ASSETS WHERE BRANCH_OFFICE='"+assetsOfficeReq.getBranch()+"' and CURRENCY='"+assetsOfficeReq.getCurrency()+"' ";
                    log.info("SQL: "+SQL);
                }
            }


            return EBankJdbcTemplate.query(SQL, new RowMapper<AssetsOfficeModel>() {
                @Override
                public AssetsOfficeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AssetsOfficeModel tr = new AssetsOfficeModel();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setCode(rs.getString("CODE"));
                    tr.setName(rs.getString("NAME"));
                    tr.setGroup_type(rs.getString("GROUP_TYPE"));
                    tr.setOwner(rs.getString("OWNER"));
                    tr.setQty(rs.getString("QTY"));
                    tr.setBranch_office(rs.getString("BRANCH_OFFICE"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setImg(rs.getString("IMG"));
                    tr.setDepartment(rs.getString("DEPARTMENT"));
                    tr.setBrand(rs.getString("BRAND"));
                    tr.setModel(rs.getString("MODEL"));
                    tr.setLocation_room(rs.getString("LOCATION_ROOM"));
                    tr.setDate_getin(rs.getString("DATE_GETIN"));
                    tr.setUnit(rs.getString("UNIT"));
                    tr.setColors(rs.getString("colors"));
//=====================================================================================================================
                    String priceB4 = rs.getString("price").replaceAll(",","");
                    double priceAfter  = Double.parseDouble(priceB4);
                    tr.setPrice(priceAfter);
//                    tr.setPrice(rs.getDouble("price"));
//=====================================================================================================================
                    tr.setLife_service(rs.getString("LIFE_SERVIECE"));
                    tr.setDateExpire(rs.getString("dateExpire"));
//                    String convert = tr.setPrice(rs.getString("price").replaceAll(",","");
//                    Double contoDouble = Double.valueOf(convert);
//                    tr.setPrice(contoDouble);
                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // list Asset Models
    public List<AssetsOfficeModel> listAssetsOfficeDAOsDetailById (AssetsOfficeReq assetsOfficeReq) {
        try{
            String SQL ="select * from TB_ASSETS WHERE KEY_ID ='"+assetsOfficeReq.getKey_id()+"' ";
            log.info("SQL"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<AssetsOfficeModel>() {
                @Override
                public AssetsOfficeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AssetsOfficeModel tr = new AssetsOfficeModel();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setCode(rs.getString("CODE"));
                    tr.setName(rs.getString("NAME"));
                    tr.setGroup_type(rs.getString("GROUP_TYPE"));
                    tr.setOwner(rs.getString("OWNER"));
                    tr.setQty(rs.getString("QTY"));
                    tr.setBranch_office(rs.getString("BRANCH_OFFICE"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setImg(rs.getString("IMG"));
                    tr.setDepartment(rs.getString("DEPARTMENT"));
                    tr.setBrand(rs.getString("BRAND"));
                    tr.setModel(rs.getString("MODEL"));
                    tr.setLocation_room(rs.getString("LOCATION_ROOM"));
                    tr.setDate_getin(rs.getString("DATE_GETIN"));
                    tr.setUnit(rs.getString("UNIT"));
                    tr.setColors(rs.getString("colors"));
                    tr.setPrice(rs.getDouble("price"));
                    tr.setLife_service(rs.getString("LIFE_SERVIECE"));
                    tr.setDateExpire(rs.getString("dateExpire"));

                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    Del Asset office
    public int delAssetOfficeDAOs (AssetsOfficeReq assetsOfficeReq) {
        String keyId = assetsOfficeReq.getKey_id();
        int i =0;
        try {
            String SQL = "delete from TB_ASSETS where KEY_ID = '" + keyId +"'";
            log.info("SQL:"+SQL);
            i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
//Insert Assets office
public int InsertAssetsOfficeDAOs (AssetsOfficeReq assetsOfficeReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = assetsOfficeReq.getImg();
    log.info("path:"+path+fileName);
    List<AssetsOfficeModel> data = new ArrayList<>();
    try{
//        String SQL = "insert into TB_ASSETS (CODE,NAME,GROUP_TYPE,OWNER,QTY,BRANCH_OFFICE,LIFE_SERVICE,IMG,DEPARTMENT,BRAND,MODEL,LOCATION_ROOM,DATE_GETIN,UNIT) value(?,?,?,?,?,'"+assetsOfficeReq.getBranch()+"','"+assetsOfficeReq.getLife_service()+"',?,'"+assetsOfficeReq.getDepartment()+"','"+assetsOfficeReq.getBranch()+"','"+assetsOfficeReq.getModel()+"','"+assetsOfficeReq.getLocation_room()+"','"+assetsOfficeReq.getDate_getin()+"','"+assetsOfficeReq.getUnit()+"')";
        String SQL = "insert into TB_ASSETS (CODE,NAME,GROUP_TYPE,OWNER,QTY,BRANCH_OFFICE,CURRENCY,IMG,DEPARTMENT,BRAND,MODEL,LOCATION_ROOM,DATE_GETIN,UNIT,colors,price,LIFE_SERVIECE,dateExpire,branch_id) value(?,?,?,?,?,'"+assetsOfficeReq.getBranch()+"',?,?,?,?,?,?,?,?,?,?,?,?,?)";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(assetsOfficeReq.getCode());
        paramList.add(assetsOfficeReq.getName());
        paramList.add(assetsOfficeReq.getGroup_type());
        paramList.add(assetsOfficeReq.getOwner());
        paramList.add(assetsOfficeReq.getQty());
        paramList.add(assetsOfficeReq.getCurrency());
        paramList.add(path + fileName);
        paramList.add(assetsOfficeReq.getDepartment());
        paramList.add(assetsOfficeReq.getBrand());
        paramList.add(assetsOfficeReq.getModel());
        paramList.add(assetsOfficeReq.getLocation_room());
        paramList.add(assetsOfficeReq.getDate_getin());
        paramList.add(assetsOfficeReq.getUnit());
        paramList.add(assetsOfficeReq.getColors());
        paramList.add(assetsOfficeReq.getPrice());
        paramList.add(assetsOfficeReq.getLife_service());
        paramList.add(assetsOfficeReq.getDateExpire());
        paramList.add(assetsOfficeReq.getBranch_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//update asset no pic
    public int UpdateAssetsOfficeDAOs (AssetsOfficeReq assetsOfficeReq) throws ParseException {
        String path="http://khounkham.com/images/car/";
        String fileName = assetsOfficeReq.getImg();
        log.info("path:"+path+fileName);
        List<AssetsOfficeModel> data = new ArrayList<>();
        try{
            String SQL = "update TB_ASSETS set CODE=?,NAME=?,GROUP_TYPE=?,OWNER=?,QTY=?,BRANCH_OFFICE='"+assetsOfficeReq.getBranch()+"',CURRENCY=?,IMG=?,DEPARTMENT=?,BRAND=?,MODEL=?,LOCATION_ROOM=?,DATE_GETIN=?,UNIT=?,colors=?,price=?,LIFE_SERVIECE=?,dateExpire=?,branch_id=? where KEY_ID ='"+assetsOfficeReq.getKey_id()+"'";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(assetsOfficeReq.getCode());
            paramList.add(assetsOfficeReq.getName());
            paramList.add(assetsOfficeReq.getGroup_type());
            paramList.add(assetsOfficeReq.getOwner());
            paramList.add(assetsOfficeReq.getQty());
            paramList.add(assetsOfficeReq.getCurrency());
            paramList.add(path + fileName);
            paramList.add(assetsOfficeReq.getDepartment());
            paramList.add(assetsOfficeReq.getBrand());
            paramList.add(assetsOfficeReq.getModel());
            paramList.add(assetsOfficeReq.getLocation_room());
            paramList.add(assetsOfficeReq.getDate_getin());
            paramList.add(assetsOfficeReq.getUnit());
            paramList.add(assetsOfficeReq.getColors());
            paramList.add(assetsOfficeReq.getPrice());
            paramList.add(assetsOfficeReq.getLife_service());
            paramList.add(assetsOfficeReq.getDateExpire());
            paramList.add(assetsOfficeReq.getBranch_id());
            paramList.add(assetsOfficeReq.getKey_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //update asset have data
    public int updateAssetsOfficeUppicHaveData (AssetsOfficeReq assetsOfficeReq) throws ParseException {

        String path="http://khounkham.com/images/car/";
        String fileName = assetsOfficeReq.getImg();
        log.info("path:"+path+fileName);
//        log.info("sqlEndDate:"+sqlEndDate);
        List<AssetsOfficeModel> data = new ArrayList<>();
        try{
            String SQL = "update TB_ASSETS set CODE=?,NAME=?,GROUP_TYPE=?,OWNER=?,QTY=?,BRANCH_OFFICE='"+assetsOfficeReq.getBranch()+"',CURRENCY=?,DEPARTMENT=?,BRAND=?,MODEL=?,LOCATION_ROOM=?,DATE_GETIN=?,UNIT=?,colors=?,price=?,LIFE_SERVIECE=?,dateExpire=?,branch_id=? where KEY_ID ='"+assetsOfficeReq.getKey_id()+"'";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(assetsOfficeReq.getCode());
            paramList.add(assetsOfficeReq.getName());
            paramList.add(assetsOfficeReq.getGroup_type());
            paramList.add(assetsOfficeReq.getOwner());
            paramList.add(assetsOfficeReq.getQty());
            paramList.add(assetsOfficeReq.getBranch_office());
            paramList.add(assetsOfficeReq.getDepartment());
            paramList.add(assetsOfficeReq.getBrand());
            paramList.add(assetsOfficeReq.getModel());
            paramList.add(assetsOfficeReq.getLocation_room());
            paramList.add(assetsOfficeReq.getDate_getin());
            paramList.add(assetsOfficeReq.getUnit());
            paramList.add(assetsOfficeReq.getColors());
            paramList.add(assetsOfficeReq.getPrice());
            paramList.add(assetsOfficeReq.getLife_service());
            paramList.add(assetsOfficeReq.getDateExpire());
            paramList.add(assetsOfficeReq.getBranch_id());
            paramList.add(assetsOfficeReq.getKey_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
