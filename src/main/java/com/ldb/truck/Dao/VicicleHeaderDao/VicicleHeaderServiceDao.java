package com.ldb.truck.Dao.VicicleHeaderDao;

import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportHeader;
import com.ldb.truck.Model.Login.Report.ReportHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Repository
public class VicicleHeaderServiceDao implements VicicleHeaderDao {
    private static final Logger log = LogManager.getLogger(VicicleHeaderServiceDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    @Override
    public List<VicicleHeader> listVicicleHeader() {
        try{
            String SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID  ";
            log.info("SQL"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_6"));
                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHis_REASON(rs.getString("his_reson"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));

                    tr.setBat_StartDate(rs.getString("Bat_StartDate"));
                    tr.setBat_EndDate(rs.getString("Bat_EndDate"));
                    tr.setImageTruck(rs.getString("IMAGE_TRUK"));
                    tr.setExCarDate(rs.getString("END_DATE_REGISCAR"));
                    tr.setExCarColor(rs.getString("COLOR_CAR"));
                    tr.setExHangMar(rs.getString("HORSEPOWER"));
                    tr.setBatNo(rs.getString("batNo"));
                    tr.setIdMorFai(rs.getString("ID_MORFAI"));

                    tr.setImageMorFai(rs.getString("IMAGE_MORFAI"));

                    tr.setModalMorfai(rs.getString("MODAL_MORFAI"));
                    tr.setSizeMorfai(rs.getString("SIZE_MORFAI"));
                    tr.setServiceLife(rs.getString("SERVICE_LIFE"));

                    tr.setToBatRowStatus(rs.getString("toBatRow"));
                    tr.setToBatRowGalanty(rs.getString("toBatRowGalanty"));
                    tr.setToBatRowtabienLod(rs.getString("toBatRowtabienLod"));

                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<VicicleHeader> listVicicleHeaderByID(VicicleHeaderReq vicicleHeaderReq) {
        try{
            String SQL="";
            log.info("key:"+vicicleHeaderReq.getKey_id());
                 SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID where a.key_id='"+vicicleHeaderReq.getKey_id()+"'";
            return EBankJdbcTemplate.query(SQL, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_NO_7(rs.getString("LL_TIRE_NO_7"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_DATE_7(rs.getString("LL_TIRE_DATE_7"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setLL_TIRE_KM_7(rs.getString("LL_TIRE_KM_7"));
                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_7(rs.getString("R_TIRE_NO_7"));
                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_DATE_7(rs.getString("R_TIRE_DATE_7"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setR_TIRE_KM_7(rs.getString("R_TIRE_KM_7"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHis_REASON(rs.getString("his_RESON"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KM13(rs.getString("H_KM13"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));
                    tr.setH_KML_13(rs.getString("H_KML_13"));
                    tr.setBat_StartDate(rs.getString("Bat_StartDate"));
                    tr.setBat_EndDate(rs.getString("Bat_EndDate"));
                    tr.setImageTruck(rs.getString("IMAGE_TRUK"));
                    tr.setExCarDate(rs.getString("END_DATE_REGISCAR"));
                    tr.setExCarColor(rs.getString("COLOR_CAR"));
                    tr.setExHangMar(rs.getString("HORSEPOWER"));
                    tr.setBatNo(rs.getString("batNo"));
                    tr.setIdMorFai(rs.getString("ID_MORFAI"));
                    tr.setImageMorFai(rs.getString("IMAGE_MORFAI"));
                    tr.setModalMorfai(rs.getString("MODAL_MORFAI"));
                    tr.setSizeMorfai(rs.getString("SIZE_MORFAI"));
                    tr.setServiceLife(rs.getString("SERVICE_LIFE"));
                    tr.setToBatRowStatus(rs.getString("toBatRow"));
                    tr.setToBatRowGalanty(rs.getString("toBatRowGalanty"));
                    tr.setToBatRowtabienLod(rs.getString("toBatRowtabienLod"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int saveVicicleHeader(VicicleHeaderReq vicicleHeaderReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Bat_StartDateGalaty = sdf.parse(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
        Date Bat_StartDate = sdf.parse(vicicleHeaderReq.getBat_StartDate());
        Date Bat_EndtDate = sdf.parse(vicicleHeaderReq.getBat_EndDate());
        java.sql.Date sqlStartDateGalanty = new java.sql.Date(Bat_StartDateGalaty.getTime());
        java.sql.Date sqlStartDate = new java.sql.Date(Bat_StartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(Bat_EndtDate.getTime());
        String path="http://khounkham.com/images/car/";
        String fileName = vicicleHeaderReq.getImageTruck();
        log.info("path:"+path+fileName);
        log.info("sqlEndDate:"+sqlEndDate);
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "insert into TB_HEADER_TRUCK (H_VICIVLE_NUMBER,H_VICIVLE_GALATY,H_VICIVLE_DATE_GALATY,H_VICIVLE_TNGLOD,H_VICIVLE_BRANCH,H_VICIVLE_YEARLEVEL,H_VICIVLE_BRANCHTYPE,H_VICIVLE_DATEEXPRIRE,H_VICIVLE_LEKJUK, \n" +
                    "H_VICIVLE_LEKTHUNG,H_VICIVLE_GPS,H_VICIVLE_POYPUDNUMFON,H_VICIVLE_MORFAI,H_VICIVLE_BGTOM,H_VICIVLE_JANLARK,H_VICIVLE_FAINAR,H_VICIVLE_FAITHAIY,H_VICIVLE_FAIYKHANG,H_VICIVLE_VENMONGNAR,H_VICIVLE_VENMONGLHG , \n" +
                    "H_VICIVLE_VENKHANG,h_VICIVLE_GLASS,LL_TIRE_NO_1, LL_TIRE_NO_2, LL_TIRE_NO_3,LL_TIRE_NO_4,LL_TIRE_NO_5,LL_TIRE_NO_6,LL_TIRE_NO_7, LL_TIRE_DATE_1, LL_TIRE_DATE_2, LL_TIRE_DATE_3, LL_TIRE_DATE_4,LL_TIRE_DATE_5,LL_TIRE_DATE_6,LL_TIRE_DATE_7, \n" +
                    "LL_TIRE_KM_1 , LL_TIRE_KM_2 , LL_TIRE_KM_3 , LL_TIRE_KM_4 , LL_TIRE_KM_5 ,LL_TIRE_KM_6,LL_TIRE_KM_7,R_TIRE_NO_1,R_TIRE_NO_2,R_TIRE_NO_3,R_TIRE_NO_4,R_TIRE_NO_5,R_TIRE_NO_6,R_TIRE_NO_7, \n" +
                    "R_TIRE_DATE_1, R_TIRE_DATE_2, R_TIRE_DATE_3, R_TIRE_DATE_4, R_TIRE_DATE_5, R_TIRE_DATE_6,R_TIRE_DATE_7, R_TIRE_KM_1,R_TIRE_KM_2,R_TIRE_KM_3,R_TIRE_KM_4,R_TIRE_KM_5,R_TIRE_KM_6,R_TIRE_KM_7,H_LEK_NUMMUNKHG,kim_km," +
                    "H_KM1 ,\n" +
                    "H_KM2 ,\n" +
                    "H_KM3 ,\n" +
                    "H_KM4 ,\n" +
                    "H_KM5 ,\n" +
                    "H_KM6 ,\n" +
                    "H_KM7 ,\n" +
                    "H_KM8 ,\n" +
                    "H_KM9 ,\n" +
                    "H_KM10,\n" +
                    "H_KM11,\n" +
                    "H_KM12,\n" +
                    "H_KM13,\n" +
                    "H_KML_1 ,  \n" +
                    "H_KML_2 ,  \n" +
                    "H_KML_3 ,  \n" +
                    "H_KML_4 ,  \n" +
                    "H_KML_5 ,  \n" +
                    "H_KML_6 ,  \n" +
                    "H_KML_7 ,  \n" +
                    "H_KML_8 ,  \n" +
                    "H_KML_9 ,  \n" +
                    "H_KML_10,  \n" +
                    "H_KML_11,  \n" +
                    "H_KML_12,H_KML_13,Bat_StartDate,Bat_EndDate,IMAGE_TRUK,END_DATE_REGISCAR,COLOR_CAR,HORSEPOWER,batNo,H_STATUS)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y')";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(sqlStartDateGalanty);
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
            paramList.add(vicicleHeaderReq.getKim_KM().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(sqlStartDate);
            paramList.add(sqlEndDate);
            paramList.add(path+fileName);
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());
            paramList.add(vicicleHeaderReq.getBatNo());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateVicicleHeader(VicicleHeaderReq vicicleHeaderReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Bat_StartDateGalaty = sdf.parse(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
        java.sql.Date sqlStartDateGalanty = new java.sql.Date(Bat_StartDateGalaty.getTime());
        Date Bat_StartDate = sdf.parse(vicicleHeaderReq.getBat_StartDate());
        Date Bat_EndtDate = sdf.parse(vicicleHeaderReq.getBat_EndDate());
        java.sql.Date sqlStartDate = new java.sql.Date(Bat_StartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(Bat_EndtDate.getTime());
        String path="http://khounkham.com/images/car/";
        String fileName = vicicleHeaderReq.getImageTruck();
        try {
            String SQL ="update TB_HEADER_TRUCK set H_VICIVLE_NUMBER=?, H_VICIVLE_GALATY=?, H_VICIVLE_DATE_GALATY=?, H_VICIVLE_TNGLOD=?, H_VICIVLE_BRANCH=?, H_VICIVLE_YEARLEVEL=?, \n" +
                    "H_VICIVLE_BRANCHTYPE=?, H_VICIVLE_DATEEXPRIRE=?, H_VICIVLE_LEKJUK=?, H_VICIVLE_LEKTHUNG=?, H_VICIVLE_GPS=?, H_VICIVLE_POYPUDNUMFON=?, \n" +
                    "H_VICIVLE_MORFAI=?, H_VICIVLE_BGTOM=?, H_VICIVLE_JANLARK=?, H_VICIVLE_FAINAR=?, H_VICIVLE_FAITHAIY=?,h_VICIVLE_GLASS=?, H_VICIVLE_FAIYKHANG=?, \n" +
                    "H_VICIVLE_VENMONGNAR=?,H_VICIVLE_VENMONGLHG=?,H_VICIVLE_VENKHANG=?,LL_TIRE_NO_1=?,LL_TIRE_NO_2=?,LL_TIRE_NO_3=?,LL_TIRE_NO_4=?, \n" +
                    "LL_TIRE_NO_5=?,LL_TIRE_NO_6=?,LL_TIRE_NO_7=?,LL_TIRE_DATE_1=?,LL_TIRE_DATE_2=?,LL_TIRE_DATE_3=?,LL_TIRE_DATE_4=?,LL_TIRE_DATE_5=?, \n" +
                    "LL_TIRE_DATE_6=?,LL_TIRE_DATE_7=?,LL_TIRE_KM_1=?,LL_TIRE_KM_2 =?,LL_TIRE_KM_3 =?,LL_TIRE_KM_4 =?,LL_TIRE_KM_5 =?,LL_TIRE_KM_6 =?,LL_TIRE_KM_7 =?, \n" +
                    "R_TIRE_NO_1=?,R_TIRE_NO_2 =?,R_TIRE_NO_3  =?, \n" +
                    "R_TIRE_NO_4=?, \n" +
                    "R_TIRE_NO_5=?, \n" +
                    "R_TIRE_NO_6=?, \n" +
                    "R_TIRE_NO_7=?, \n" +
                    "R_TIRE_DATE_1=?, \n" +
                    "R_TIRE_DATE_2=?, \n" +
                    "R_TIRE_DATE_3=?, \n" +
                    "R_TIRE_DATE_4=?, \n" +
                    "R_TIRE_DATE_5=?, \n" +
                    "R_TIRE_DATE_6=?, \n" +
                    "R_TIRE_DATE_7=?, \n" +
                    "R_TIRE_KM_1=?, \n" +
                    "R_TIRE_KM_2=?, \n" +
                    "R_TIRE_KM_3=?, \n" +
                    "R_TIRE_KM_4=?, \n" +
                    "R_TIRE_KM_5=?, \n" +
                    "R_TIRE_KM_6=?,\n" +
                    "R_TIRE_KM_7=?,\n" +
                    "H_LEK_NUMMUNKHG=?,\n" +
                    "h_STATUS='Y',\n" +
                    "HIS_RESON=?,kim_km=?,H_KM1 =?,\n" +
                    "H_KM2 =?,\n" +
                    "H_KM3 =?,\n" +
                    "H_KM4 =?,\n" +
                    "H_KM5 =?,\n" +
                    "H_KM6 =?,\n" +
                    "H_KM7 =?,\n" +
                    "H_KM8 =?,\n" +
                    "H_KM9 =?,\n" +
                    "H_KM10=?,\n" +
                    "H_KM11=?,\n" +
                    "H_KM12=?,\n" +
                    "H_KM13=?,\n" +
                    "H_KML_1 =?,  \n" +
                    "H_KML_2 =?,  \n" +
                    "H_KML_3 =?,  \n" +
                    "H_KML_4 =?,  \n" +
                    "H_KML_5 =?,  \n" +
                    "H_KML_6 =?,  \n" +
                    "H_KML_7 =?,  \n" +
                    "H_KML_8 =?,  \n" +
                    "H_KML_9 =?,  \n" +
                    "H_KML_10=?,  \n" +
                    "H_KML_11=?,  \n" +
                    "H_KML_12=?,H_KML_13=?,Bat_StartDate=?,Bat_EndDate=?,IMAGE_TRUK=?,END_DATE_REGISCAR=?,COLOR_CAR=?,HORSEPOWER=?,batNo=? where  key_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(sqlStartDateGalanty);
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
            //paramList.add(vicicleHeaderReq.getH_STATUS());
            paramList.add(vicicleHeaderReq.getHis_REASON());
            paramList.add(vicicleHeaderReq.getKim_KM().replace(",",""));

            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(sqlStartDate);
            paramList.add(sqlEndDate);

            paramList.add(path+fileName);
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());
            paramList.add(vicicleHeaderReq.getBatNo());

            paramList.add(vicicleHeaderReq.getKey_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    } @Override
    public int updateVicicleHeaderUppicHaveData(VicicleHeaderReq vicicleHeaderReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Bat_StartDateGalaty = sdf.parse(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
        java.sql.Date sqlStartDateGalanty = new java.sql.Date(Bat_StartDateGalaty.getTime());
        Date Bat_StartDate = sdf.parse(vicicleHeaderReq.getBat_StartDate());
        Date Bat_EndtDate = sdf.parse(vicicleHeaderReq.getBat_EndDate());
        java.sql.Date sqlStartDate = new java.sql.Date(Bat_StartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(Bat_EndtDate.getTime());
        String path="http://khounkham.com/images/car/";
        String fileName = vicicleHeaderReq.getImageTruck();
        try {
            String SQL ="update TB_HEADER_TRUCK set H_VICIVLE_NUMBER=?, H_VICIVLE_GALATY=?, H_VICIVLE_DATE_GALATY=?, H_VICIVLE_TNGLOD=?, H_VICIVLE_BRANCH=?, H_VICIVLE_YEARLEVEL=?, \n" +
                    "H_VICIVLE_BRANCHTYPE=?, H_VICIVLE_DATEEXPRIRE=?, H_VICIVLE_LEKJUK=?, H_VICIVLE_LEKTHUNG=?, H_VICIVLE_GPS=?, H_VICIVLE_POYPUDNUMFON=?, \n" +
                    "H_VICIVLE_MORFAI=?, H_VICIVLE_BGTOM=?, H_VICIVLE_JANLARK=?, H_VICIVLE_FAINAR=?, H_VICIVLE_FAITHAIY=?,h_VICIVLE_GLASS=?, H_VICIVLE_FAIYKHANG=?, \n" +
                    "H_VICIVLE_VENMONGNAR=?,H_VICIVLE_VENMONGLHG=?,H_VICIVLE_VENKHANG=?,LL_TIRE_NO_1=?,LL_TIRE_NO_2=?,LL_TIRE_NO_3=?,LL_TIRE_NO_4=?, \n" +
                    "LL_TIRE_NO_5=?,LL_TIRE_NO_6=?,LL_TIRE_NO_7=?,LL_TIRE_DATE_1=?,LL_TIRE_DATE_2=?,LL_TIRE_DATE_3=?,LL_TIRE_DATE_4=?,LL_TIRE_DATE_5=?, \n" +
                    "LL_TIRE_DATE_6=?,LL_TIRE_DATE_7=?,LL_TIRE_KM_1=?,LL_TIRE_KM_2 =?,LL_TIRE_KM_3 =?,LL_TIRE_KM_4 =?,LL_TIRE_KM_5 =?,LL_TIRE_KM_6 =?,LL_TIRE_KM_7 =?, \n" +
                    "R_TIRE_NO_1=?,R_TIRE_NO_2 =?,R_TIRE_NO_3  =?, \n" +
                    "R_TIRE_NO_4=?, \n" +
                    "R_TIRE_NO_5=?, \n" +
                    "R_TIRE_NO_6=?, \n" +
                    "R_TIRE_NO_7=?, \n" +
                    "R_TIRE_DATE_1=?, \n" +
                    "R_TIRE_DATE_2=?, \n" +
                    "R_TIRE_DATE_3=?, \n" +
                    "R_TIRE_DATE_4=?, \n" +
                    "R_TIRE_DATE_5=?, \n" +
                    "R_TIRE_DATE_6=?, \n" +
                    "R_TIRE_DATE_7=?, \n" +
                    "R_TIRE_KM_1=?, \n" +
                    "R_TIRE_KM_2=?, \n" +
                    "R_TIRE_KM_3=?, \n" +
                    "R_TIRE_KM_4=?, \n" +
                    "R_TIRE_KM_5=?, \n" +
                    "R_TIRE_KM_6=?,\n" +
                    "R_TIRE_KM_7=?,\n" +
                    "H_LEK_NUMMUNKHG=?,\n" +
                    "h_STATUS='Y',\n" +
                    "HIS_RESON=?,kim_km=?,H_KM1 =?,\n" +
                    "H_KM2 =?,\n" +
                    "H_KM3 =?,\n" +
                    "H_KM4 =?,\n" +
                    "H_KM5 =?,\n" +
                    "H_KM6 =?,\n" +
                    "H_KM7 =?,\n" +
                    "H_KM8 =?,\n" +
                    "H_KM9 =?,\n" +
                    "H_KM10=?,\n" +
                    "H_KM11=?,\n" +
                    "H_KM12=?,\n" +
                    "H_KM13=?,\n" +
                    "H_KML_1 =?,  \n" +
                    "H_KML_2 =?,  \n" +
                    "H_KML_3 =?,  \n" +
                    "H_KML_4 =?,  \n" +
                    "H_KML_5 =?,  \n" +
                    "H_KML_6 =?,  \n" +
                    "H_KML_7 =?,  \n" +
                    "H_KML_8 =?,  \n" +
                    "H_KML_9 =?,  \n" +
                    "H_KML_10=?,  \n" +
                    "H_KML_11=?,  \n" +
                    "H_KML_12=?,H_KML_13=?,Bat_StartDate=?,Bat_EndDate=?,END_DATE_REGISCAR=?,COLOR_CAR=?,HORSEPOWER=? where  key_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(sqlStartDateGalanty);
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
            //paramList.add(vicicleHeaderReq.getH_STATUS());
            paramList.add(vicicleHeaderReq.getHis_REASON());
            paramList.add(vicicleHeaderReq.getKim_KM().replace(",",""));

            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(sqlStartDate);
            paramList.add(sqlEndDate);

            //paramList.add(path+fileName);
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());

            paramList.add(vicicleHeaderReq.getKey_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public int delVicicleHeader(VicicleHeaderReq vicicleHeaderReq) {
        int i =0;
        try {
            String SQL = "delete from TB_HEADER_TRUCK where key_id='" + vicicleHeaderReq.getKey_id() + "'";
           i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    @Override
    public int saveHeaderHistroty(VicicleHeaderReq vicicleHeaderReq) {
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "insert into TB_HEADER_TRUCK_HISTORY (H_VICIVLE_NUMBER,H_VICIVLE_GALATY,H_VICIVLE_DATE_GALATY,H_VICIVLE_TNGLOD,H_VICIVLE_BRANCH,H_VICIVLE_YEARLEVEL,H_VICIVLE_BRANCHTYPE,H_VICIVLE_DATEEXPRIRE,H_VICIVLE_LEKJUK, \n" +
                    "H_VICIVLE_LEKTHUNG,H_VICIVLE_GPS,H_VICIVLE_POYPUDNUMFON,H_VICIVLE_MORFAI,H_VICIVLE_BGTOM,H_VICIVLE_JANLARK,H_VICIVLE_FAINAR,H_VICIVLE_FAITHAIY,H_VICIVLE_FAIYKHANG,H_VICIVLE_VENMONGNAR,H_VICIVLE_VENMONGLHG , \n" +
                    "H_VICIVLE_VENKHANG,h_VICIVLE_GLASS,LL_TIRE_NO_1, LL_TIRE_NO_2, LL_TIRE_NO_3,LL_TIRE_NO_4,LL_TIRE_NO_5,LL_TIRE_NO_6, LL_TIRE_DATE_1, LL_TIRE_DATE_2, LL_TIRE_DATE_3, LL_TIRE_DATE_4,LL_TIRE_DATE_5,LL_TIRE_DATE_6, \n" +
                    "LL_TIRE_KM_1 , LL_TIRE_KM_2 , LL_TIRE_KM_3 , LL_TIRE_KM_4 , LL_TIRE_KM_5 ,LL_TIRE_KM_6,R_TIRE_NO_1,R_TIRE_NO_2,R_TIRE_NO_3,R_TIRE_NO_4,R_TIRE_NO_5,R_TIRE_NO_6, \n" +
                    "R_TIRE_DATE_1, R_TIRE_DATE_2, R_TIRE_DATE_3, R_TIRE_DATE_4, R_TIRE_DATE_5, R_TIRE_DATE_6, R_TIRE_KM_1,R_TIRE_KM_2,R_TIRE_KM_3,R_TIRE_KM_4,R_TIRE_KM_5,R_TIRE_KM_6,H_LEK_NUMMUNKHG,H_STATUS,HIS_DATE,HIS_RESON,kim_km,H_KM1 ,\n" +
                    "H_KM2 ,\n" +
                    "H_KM3 ,\n" +
                    "H_KM4 ,\n" +
                    "H_KM5 ,\n" +
                    "H_KM6 ,\n" +
                    "H_KM7 ,\n" +
                    "H_KM8 ,\n" +
                    "H_KM9 ,\n" +
                    "H_KM10,\n" +
                    "H_KM11,\n" +
                    "H_KM12,\n" +
                    "H_KM13,\n" +
                    "H_KML_1 ,  \n" +
                    "H_KML_2 ,  \n" +
                    "H_KML_3 ,  \n" +
                    "H_KML_4 ,  \n" +
                    "H_KML_5 ,  \n" +
                    "H_KML_6 ,  \n" +
                    "H_KML_7 ,  \n" +
                    "H_KML_8 ,  \n" +
                    "H_KML_9 ,  \n" +
                    "H_KML_10,  \n" +
                    "H_KML_11,  \n" +
                    "H_KML_12)\n" +
                    "H_KML_13,END_DATE_REGISCAR,COLOR_CAR,HORSEPOWER,IMAGE_TRUK,batNo)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'N',now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?)";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6());
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
         //   paramList.add(vicicleHeaderReq.getHIS_DATE());
            paramList.add(vicicleHeaderReq.getHis_REASON());
            paramList.add(vicicleHeaderReq.getKim_KM());
            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());
            paramList.add(vicicleHeaderReq.getImageTruck());
            paramList.add(vicicleHeaderReq.getBatNo());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public List<VicicleHeader> ReportHistoryHeader(ReportAllReq vicicleHeaderReq) {
        log.info("start:"+vicicleHeaderReq.getStartDate());
        log.info("end:"+vicicleHeaderReq.getEndDate());
        try {
            String  sql = "SELECT * FROM V_RE_HEADER_HIS WHERE HIS_DATE BETWEEN '"+vicicleHeaderReq.getStartDate()+"' AND '"+vicicleHeaderReq.getEndDate()+"' ";
            return EBankJdbcTemplate.query(sql, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_6"));
                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHIS_DATE(rs.getString("HIS_DATE"));
                    tr.setHis_REASON(rs.getString("HIS_RESON"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));
                    return tr;
                }
            });
        }catch (Exception e ){
                e.printStackTrace();
        }
        return null;
    }
    //header combo1
    @Override
    public List<VicicleHeader> listVicicleHeaderCombox1() {
        try{
            String SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID where a.H_STATUS='Y' ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_6"));
                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHis_REASON(rs.getString("his_reson"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));
                    tr.setToBatRowStatus(rs.getString("toBatRow"));
                    tr.setBat_StartDate(rs.getString("Bat_StartDate"));
                    tr.setBat_EndDate(rs.getString("Bat_EndDate"));
                    tr.setImageTruck(rs.getString("IMAGE_TRUK"));
                    tr.setExCarDate(rs.getString("END_DATE_REGISCAR"));
                    tr.setExCarColor(rs.getString("COLOR_CAR"));
                    tr.setExHangMar(rs.getString("HORSEPOWER"));
                    tr.setBatNo(rs.getString("batNo"));
                    tr.setIdMorFai(rs.getString("ID_MORFAI"));
                    tr.setImageMorFai(rs.getString("IMAGE_MORFAI"));
                    tr.setModalMorfai(rs.getString("MODAL_MORFAI"));
                    tr.setSizeMorfai(rs.getString("SIZE_MORFAI"));
                    tr.setServiceLife(rs.getString("SERVICE_LIFE"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportHeader> listReportHeader(ReportHeaderReq reportHeaderReq) {
        try
        {
        String SQL="select a.LAHUD_POYLOD,b.H_VICIVLE_NUMBER,c.F_CARD_NO,b.H_VICIVLE_BRANCH,COUNT(*) totalRow ,\n" +
                "cast(replace(PRIECENUMNUN, ',', '') as unsigned)*SAINUMMUN AS PRIECENUMNUN,\n" +
                "FEETOTAL,sum(cast(replace(STAFF_BIALIENG, ',', '') as unsigned)) as STAFF_BIALIENG,\n" +
                "sum(cast(replace(staff02_payAll, ',', '') as unsigned)) as staff02_payAll\n" +
                "from TB_DETAILS a \n" +
                "inner join TB_HEADER_TRUCK b on b.KEY_ID  =a.HEADER_ID\n" +
                "INNER JOIN TB_FOOTER_TRUCH c ON c.KEY_ID =a.FOOTER_ID \n" +
                "INNER JOIN TB_PERFORMANCE d ON a.LAHUD_POYLOD=d.PERFORMANCEBILLNO \n" +
                "join STAFF f on f.KEY_ID = a.STAFF_ID_NUM1\n" +
                "join STAFF h on h.KEY_ID =a.STAFF_ID_NUM2  " +
                "where OUT_DATE between '"+reportHeaderReq.getStartDate()+"' and '"+reportHeaderReq.getEndDate()+"'\n" +
                "group by b.H_VICIVLE_NUMBER,c.F_CARD_NO,b.H_VICIVLE_BRANCH";
        return EBankJdbcTemplate.query(SQL, new RowMapper<ReportHeader>() {
            @Override
            public ReportHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                ReportHeader tr =new ReportHeader();
                tr.setLahudPoyLod(rs.getString("LAHUD_POYLOD"));
                tr.setVicicalNo(rs.getString("H_VICIVLE_NUMBER"));
                tr.setFCardNo(rs.getString("F_CARD_NO"));
                tr.setVicicalBranch(rs.getString("H_VICIVLE_BRANCH"));
                tr.setTotalRow(rs.getString("totalRow"));
                tr.setNumMun(rs.getString("PRIECENUMNUN"));
                tr.setFeeTotal(rs.getString("FEETOTAL"));
                tr.setBeeLieng(rs.getString("STAFF_BIALIENG"));
                tr.setBeeLiengAll(rs.getString("staff02_payAll"));
                return tr;
            }
        });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
