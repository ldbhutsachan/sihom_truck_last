package com.ldb.truck.Dao.VicicleFooterDao;

import com.ldb.truck.Dao.VicicleHeaderDao.VicicleHeaderServiceDao;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class VicicleFooterServiceDao  implements VicicleFooterInfDao{
    private static final Logger log = LogManager.getLogger(VicicleFooterServiceDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    @Override
    public List<VicicleFooter> ListVicicleFooter() {
        try {
            String sql = "select * from V_ALL_FOOTER_TRUCH ";
            return EBankJdbcTemplate.query(sql, new RowMapper<VicicleFooter>() {
                @Override
                public VicicleFooter mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleFooter tr =new VicicleFooter();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setF_BRANCH(rs.getString("F_BRANCH"));
                    tr.setF_YEAR(rs.getString("F_YEAR"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setF_DATEEXPRIED(rs.getString("F_DATEEXPRIED"));
                    tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                    tr.setF_LEKKUNZEE(rs.getString("F_LEKKUNZEE"));
                    tr.setF_PAO(rs.getString("F_PAO"));
                    tr.setF_KORKC (rs.getString("F_KORKC"));
                    tr.setF_TOLOCKTU(rs.getString("F_TOLOCKTU"));
                    tr.setF_SO(rs.getString("F_SO"));
                    tr.setF_PABUD (rs.getString("F_PABUD"));
                    tr.setF_FAIKHANG(rs.getString("F_FAIKHANG"));
                    tr.setF_FAITHAIY(rs.getString("F_FAITHAIY"));
                    tr.setF_BGTHOM(rs.getString("F_BGTHOM"));
                    tr.setF_GALATY_NO  (rs.getString("F_GALATY_NO"));
                    tr.setF_GALATY_DEP (rs.getString("F_GALATY_DEP"));
                    tr.setL_TRIES_1(rs.getString("L_TRIES_1"));
                    tr.setL_TRIES_2(rs.getString("L_TRIES_2"));
                    tr.setL_TRIES_3(rs.getString("L_TRIES_3"));
                    tr.setL_TRIES_4(rs.getString("L_TRIES_4"));
                    tr.setL_TRIES_5(rs.getString("L_TRIES_5"));
                    tr.setL_TRIES_6(rs.getString("L_TRIES_6"));
                    tr.setL_TRIES_7(rs.getString("L_TRIES_7"));
                    tr.setL_TRIES_8(rs.getString("L_TRIES_8"));
                    tr.setL_TRIES_DATE_1(rs.getString("L_TRIES_DATE_1"));
                    tr.setL_TRIES_DATE_2(rs.getString("L_TRIES_DATE_2"));
                    tr.setL_TRIES_DATE_3(rs.getString("L_TRIES_DATE_3"));
                    tr.setL_TRIES_DATE_4(rs.getString("L_TRIES_DATE_4"));
                    tr.setL_TRIES_DATE_5(rs.getString("L_TRIES_DATE_5"));
                    tr.setL_TRIES_DATE_6(rs.getString("L_TRIES_DATE_6"));
                    tr.setL_TRIES_DATE_7(rs.getString("L_TRIES_DATE_7"));
                    tr.setL_TRIES_DATE_8(rs.getString("L_TRIES_DATE_8"));
                    tr.setL_TRIES_KM_1(rs.getString("L_TRIES_KM_1"));
                    tr.setL_TRIES_KM_2(rs.getString("L_TRIES_KM_2"));
                    tr.setL_TRIES_KM_3(rs.getString("L_TRIES_KM_3"));
                    tr.setL_TRIES_KM_4(rs.getString("L_TRIES_KM_4"));
                    tr.setL_TRIES_KM_5(rs.getString("L_TRIES_KM_5"));
                    tr.setL_TRIES_KM_6(rs.getString("L_TRIES_KM_6"));
                    tr.setL_TRIES_KM_7(rs.getString("L_TRIES_KM_7"));
                    tr.setL_TRIES_KM_8(rs.getString("L_TRIES_KM_8"));
                    tr.setR_TRIES_1(rs.getString("R_TRIES_1"));
                    tr.setR_TRIES_2(rs.getString("R_TRIES_2"));
                    tr.setR_TRIES_3(rs.getString("R_TRIES_3"));
                    tr.setR_TRIES_4(rs.getString("R_TRIES_4"));
                    tr.setR_TRIES_5(rs.getString("R_TRIES_5"));
                    tr.setR_TRIES_6(rs.getString("R_TRIES_6"));
                    tr.setR_TRIES_7(rs.getString("R_TRIES_7"));
                    tr.setR_TRIES_8(rs.getString("R_TRIES_8"));
                    tr.setR_TRIES_DATE_1(rs.getString("R_TRIES_DATE_1"));
                    tr.setR_TRIES_DATE_2(rs.getString("R_TRIES_DATE_2"));
                    tr.setR_TRIES_DATE_3(rs.getString("R_TRIES_DATE_3"));
                    tr.setR_TRIES_DATE_4(rs.getString("R_TRIES_DATE_4"));
                    tr.setR_TRIES_DATE_5(rs.getString("R_TRIES_DATE_5"));
                    tr.setR_TRIES_DATE_6(rs.getString("R_TRIES_DATE_6"));
                    tr.setR_TRIES_DATE_7(rs.getString("R_TRIES_DATE_7"));
                    tr.setR_TRIES_DATE_8(rs.getString("R_TRIES_DATE_8"));
                    tr.setR_TRIES_KM_1  (rs.getString("R_TRIES_KM_1"));
                    tr.setR_TRIES_KM_2  (rs.getString("R_TRIES_KM_2"));
                    tr.setR_TRIES_KM_3  (rs.getString("R_TRIES_KM_3"));
                    tr.setR_TRIES_KM_4  (rs.getString("R_TRIES_KM_4"));
                    tr.setR_TRIES_KM_5  (rs.getString("R_TRIES_KM_5"));
                    tr.setR_TRIES_KM_6  (rs.getString("R_TRIES_KM_6"));
                    tr.setR_TRIES_KM_7  (rs.getString("R_TRIES_KM_7"));
                    tr.setR_TRIES_KM_8  (rs.getString("R_TRIES_KM_8"));
                    tr.setF_STATUS(rs.getString("F_STATUS"));
                    tr.setF_KM1 (rs.getString("F_KM1"));
                    tr.setF_KM2 (rs.getString("F_KM2"));
                    tr.setF_KM3 (rs.getString("F_KM3"));
                    tr.setF_KM4 (rs.getString("F_KM4"));
                    tr.setF_KM5 (rs.getString("F_KM5"));
                    tr.setF_KM6 (rs.getString("F_KM6"));
                    tr.setF_KM7 (rs.getString("F_KM7"));
                    tr.setF_KM8 (rs.getString("F_KM8"));
                    tr.setF_KM9 (rs.getString("F_KM9"));
                    tr.setF_KM10 (rs.getString("F_KM10"));
                    tr.setF_KM11 (rs.getString("F_KM11"));
                    tr.setF_KM12 (rs.getString("F_KM12"));
                    tr.setF_KM13 (rs.getString("F_KM13"));
                    tr.setF_KM14 (rs.getString("F_KM14"));
                    tr.setF_KM15 (rs.getString("F_KM15"));
                    tr.setF_KM16 (rs.getString("F_KM16"));
                    tr.setF_KM_LL1 (rs.getString("F_KM_LL1"));
                    tr.setF_KM_LL2 (rs.getString("F_KM_LL2"));
                    tr.setF_KM_LL3 (rs.getString("F_KM_LL3"));
                    tr.setF_KM_LL4 (rs.getString("F_KM_LL4"));
                    tr.setF_KM_LL5 (rs.getString("F_KM_LL5"));
                    tr.setF_KM_LL6 (rs.getString("F_KM_LL6"));
                    tr.setF_KM_LL7 (rs.getString("F_KM_LL7"));
                    tr.setF_KM_LL8 (rs.getString("F_KM_LL8"));
                    tr.setF_KM_LL9 (rs.getString("F_KM_LL9"));
                    tr.setF_KM_LL10 (rs.getString("F_KM_LL10"));
                    tr.setF_KM_LL11(rs.getString("F_KM_LL11"));
                    tr.setF_KM_LL12 (rs.getString("F_KM_LL12"));
                    tr.setF_KM_LL13 (rs.getString("F_KM_LL13"));
                    tr.setF_KM_LL14 (rs.getString("F_KM_LL14"));
                    tr.setF_KM_LL15 (rs.getString("F_KM_LL15"));
                    tr.setF_KM_LL16(rs.getString("F_KM_LL16"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<VicicleFooter> ListVicicleFooterByID(VicicleFooterReq vicicleFooterReq) {
        try {
            String sql = "select * from V_ALL_FOOTER_TRUCH WHERE KEY_ID= '"+vicicleFooterReq.getKey_id()+"'";
            return EBankJdbcTemplate.query(sql, new RowMapper<VicicleFooter>() {
                @Override
                public VicicleFooter mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleFooter tr =new VicicleFooter();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setF_BRANCH(rs.getString("F_BRANCH"));
                    tr.setF_YEAR(rs.getString("F_YEAR"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setF_DATEEXPRIED(rs.getString("F_DATEEXPRIED"));
                    tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                    tr.setF_LEKKUNZEE(rs.getString("F_LEKKUNZEE"));
                    tr.setF_PAO(rs.getString("F_PAO"));
                    tr.setF_KORKC (rs.getString("F_KORKC"));
                    tr.setF_TOLOCKTU(rs.getString("F_TOLOCKTU"));
                    tr.setF_SO(rs.getString("F_SO"));
                    tr.setF_PABUD (rs.getString("F_PABUD"));
                    tr.setF_FAIKHANG(rs.getString("F_FAIKHANG"));
                    tr.setF_FAITHAIY(rs.getString("F_FAITHAIY"));
                    tr.setF_BGTHOM(rs.getString("F_BGTHOM"));
                    tr.setF_GALATY_NO  (rs.getString("F_GALATY_NO"));
                    tr.setF_GALATY_DEP (rs.getString("F_GALATY_DEP"));
                    tr.setL_TRIES_1(rs.getString("L_TRIES_1"));
                    tr.setL_TRIES_2(rs.getString("L_TRIES_2"));
                    tr.setL_TRIES_3(rs.getString("L_TRIES_3"));
                    tr.setL_TRIES_4(rs.getString("L_TRIES_4"));
                    tr.setL_TRIES_5(rs.getString("L_TRIES_5"));
                    tr.setL_TRIES_6(rs.getString("L_TRIES_6"));
                    tr.setL_TRIES_7(rs.getString("L_TRIES_7"));
                    tr.setL_TRIES_8(rs.getString("L_TRIES_8"));
                    tr.setL_TRIES_DATE_1(rs.getString("L_TRIES_DATE_1"));
                    tr.setL_TRIES_DATE_2(rs.getString("L_TRIES_DATE_2"));
                    tr.setL_TRIES_DATE_3(rs.getString("L_TRIES_DATE_3"));
                    tr.setL_TRIES_DATE_4(rs.getString("L_TRIES_DATE_4"));
                    tr.setL_TRIES_DATE_5(rs.getString("L_TRIES_DATE_5"));
                    tr.setL_TRIES_DATE_6(rs.getString("L_TRIES_DATE_6"));
                    tr.setL_TRIES_DATE_7(rs.getString("L_TRIES_DATE_7"));
                    tr.setL_TRIES_DATE_8(rs.getString("L_TRIES_DATE_8"));
                    tr.setL_TRIES_KM_1(rs.getString("L_TRIES_KM_1"));
                    tr.setL_TRIES_KM_2(rs.getString("L_TRIES_KM_2"));
                    tr.setL_TRIES_KM_3(rs.getString("L_TRIES_KM_3"));
                    tr.setL_TRIES_KM_4(rs.getString("L_TRIES_KM_4"));
                    tr.setL_TRIES_KM_5(rs.getString("L_TRIES_KM_5"));
                    tr.setL_TRIES_KM_6(rs.getString("L_TRIES_KM_6"));
                    tr.setL_TRIES_KM_7(rs.getString("L_TRIES_KM_7"));
                    tr.setL_TRIES_KM_8(rs.getString("L_TRIES_KM_8"));
                    tr.setR_TRIES_1(rs.getString("R_TRIES_1"));
                    tr.setR_TRIES_2(rs.getString("R_TRIES_2"));
                    tr.setR_TRIES_3(rs.getString("R_TRIES_3"));
                    tr.setR_TRIES_4(rs.getString("R_TRIES_4"));
                    tr.setR_TRIES_5(rs.getString("R_TRIES_5"));
                    tr.setR_TRIES_6(rs.getString("R_TRIES_6"));
                    tr.setR_TRIES_7(rs.getString("R_TRIES_7"));
                    tr.setR_TRIES_8(rs.getString("R_TRIES_8"));
                    tr.setR_TRIES_DATE_1(rs.getString("R_TRIES_DATE_1"));
                    tr.setR_TRIES_DATE_2(rs.getString("R_TRIES_DATE_2"));
                    tr.setR_TRIES_DATE_3(rs.getString("R_TRIES_DATE_3"));
                    tr.setR_TRIES_DATE_4(rs.getString("R_TRIES_DATE_4"));
                    tr.setR_TRIES_DATE_5(rs.getString("R_TRIES_DATE_5"));
                    tr.setR_TRIES_DATE_6(rs.getString("R_TRIES_DATE_6"));
                    tr.setR_TRIES_DATE_7(rs.getString("R_TRIES_DATE_7"));
                    tr.setR_TRIES_DATE_8(rs.getString("R_TRIES_DATE_8"));
                    tr.setR_TRIES_KM_1  (rs.getString("R_TRIES_KM_1"));
                    tr.setR_TRIES_KM_2  (rs.getString("R_TRIES_KM_2"));
                    tr.setR_TRIES_KM_3  (rs.getString("R_TRIES_KM_3"));
                    tr.setR_TRIES_KM_4  (rs.getString("R_TRIES_KM_4"));
                    tr.setR_TRIES_KM_5  (rs.getString("R_TRIES_KM_5"));
                    tr.setR_TRIES_KM_6  (rs.getString("R_TRIES_KM_6"));
                    tr.setR_TRIES_KM_7  (rs.getString("R_TRIES_KM_7"));
                    tr.setR_TRIES_KM_8  (rs.getString("R_TRIES_KM_8"));
                    tr.setF_STATUS(rs.getString("F_STATUS"));
                    tr.setF_KM1 (rs.getString("F_KM1"));
                    tr.setF_KM2 (rs.getString("F_KM2"));
                    tr.setF_KM3 (rs.getString("F_KM3"));
                    tr.setF_KM4 (rs.getString("F_KM4"));
                    tr.setF_KM5 (rs.getString("F_KM5"));
                    tr.setF_KM6 (rs.getString("F_KM6"));
                    tr.setF_KM7 (rs.getString("F_KM7"));
                    tr.setF_KM8 (rs.getString("F_KM8"));
                    tr.setF_KM9 (rs.getString("F_KM9"));
                    tr.setF_KM10 (rs.getString("F_KM10"));
                    tr.setF_KM11 (rs.getString("F_KM11"));
                    tr.setF_KM12 (rs.getString("F_KM12"));
                    tr.setF_KM13 (rs.getString("F_KM13"));
                    tr.setF_KM14 (rs.getString("F_KM14"));
                    tr.setF_KM15 (rs.getString("F_KM15"));
                    tr.setF_KM16 (rs.getString("F_KM16"));
                    tr.setF_KM_LL1 (rs.getString("F_KM_LL1"));
                    tr.setF_KM_LL2 (rs.getString("F_KM_LL2"));
                    tr.setF_KM_LL3 (rs.getString("F_KM_LL3"));
                    tr.setF_KM_LL4 (rs.getString("F_KM_LL4"));
                    tr.setF_KM_LL5 (rs.getString("F_KM_LL5"));
                    tr.setF_KM_LL6 (rs.getString("F_KM_LL6"));
                    tr.setF_KM_LL7 (rs.getString("F_KM_LL7"));
                    tr.setF_KM_LL8 (rs.getString("F_KM_LL8"));
                    tr.setF_KM_LL9 (rs.getString("F_KM_LL9"));
                    tr.setF_KM_LL10 (rs.getString("F_KM_LL10"));
                    tr.setF_KM_LL11(rs.getString("F_KM_LL11"));
                    tr.setF_KM_LL12 (rs.getString("F_KM_LL12"));
                    tr.setF_KM_LL13 (rs.getString("F_KM_LL13"));
                    tr.setF_KM_LL14 (rs.getString("F_KM_LL14"));
                    tr.setF_KM_LL15 (rs.getString("F_KM_LL15"));
                    tr.setF_KM_LL16(rs.getString("F_KM_LL16"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int saveVicicleFooter(VicicleFooterReq vicicleFooterReq) {
        log.info("111:"+vicicleFooterReq.getF_KM_LL11());
        log.info("112:"+vicicleFooterReq.getF_KM_LL12());
        try {
            String sql ="insert into TB_FOOTER_TRUCH(F_BRANCH,F_YEAR,F_CAR_TYPE,F_DATEEXPRIED,F_CARD_NO,F_LEKKUNZEE,F_PAO,F_KORKC,F_TOLOCKTU,F_SO,F_PABUD,F_FAIKHANG,F_FAITHAIY,F_BGTHOM\n" +
                    ",F_GALATY_NO,F_GALATY_DEP,L_TRIES_1,L_TRIES_2,L_TRIES_3,L_TRIES_4,L_TRIES_5,L_TRIES_6,L_TRIES_7,L_TRIES_8,L_TRIES_DATE_1,L_TRIES_DATE_2\n" +
                    ",L_TRIES_DATE_3,L_TRIES_DATE_4,L_TRIES_DATE_5,L_TRIES_DATE_6,L_TRIES_DATE_7,L_TRIES_DATE_8,L_TRIES_KM_1,L_TRIES_KM_2,L_TRIES_KM_3,L_TRIES_KM_4\n" +
                    ",L_TRIES_KM_5,L_TRIES_KM_6,L_TRIES_KM_7,L_TRIES_KM_8,R_TRIES_1,R_TRIES_2,R_TRIES_3,R_TRIES_4,R_TRIES_5,R_TRIES_6,R_TRIES_7,R_TRIES_8\n" +
                    ",R_TRIES_DATE_1,R_TRIES_DATE_2,R_TRIES_DATE_3,R_TRIES_DATE_4,R_TRIES_DATE_5,R_TRIES_DATE_6,R_TRIES_DATE_7,R_TRIES_DATE_8,R_TRIES_KM_1,R_TRIES_KM_2\n" +
                    ",R_TRIES_KM_3,R_TRIES_KM_4,R_TRIES_KM_5,R_TRIES_KM_6,R_TRIES_KM_7,R_TRIES_KM_8,F_STATUS,F_KM1 ,\n" +
                    "F_KM2 ,\n" +
                    "F_KM3 ,\n" +
                    "F_KM4 ,\n" +
                    "F_KM5 ,\n" +
                    "F_KM6 ,\n" +
                    "F_KM7 ,\n" +
                    "F_KM8 ,\n" +
                    "F_KM9 ,\n" +
                    "F_KM10 ,\n" +
                    "F_KM11 ,\n" +
                    "F_KM12 ,\n" +
                    "F_KM13 ,\n" +
                    "F_KM14 ,\n" +
                    "F_KM15 ,\n" +
                    "F_KM16 ,\n" +
                    "F_KM_LL1 ,\n" +
                    "F_KM_LL2 ,\n" +
                    "F_KM_LL3 ,\n" +
                    "F_KM_LL4 ,\n" +
                    "F_KM_LL9 ,\n" +
                    "F_KM_LL10 ,\n" +
                    "F_KM_LL11 ,\n" +
                    "F_KM_LL12 ,\n" +
                    "F_KM_LL5 ,\n" +
                    "F_KM_LL6 ,\n" +
                    "F_KM_LL7 ,\n" +
                    "F_KM_LL8 ,\n" +
                    "F_KM_LL13 ,\n" +
                    "F_KM_LL14 ,\n" +
                    "F_KM_LL15 ,\n" +
                    "F_KM_LL16) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
           log.info("sql:"+sql);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleFooterReq.getF_BRANCH());
            paramList.add(vicicleFooterReq.getF_YEAR  ());
            paramList.add(vicicleFooterReq.getF_CAR_TYPE());
            paramList.add(vicicleFooterReq.getF_DATEEXPRIED());
            paramList.add(vicicleFooterReq.getF_CARD_NO ());
            paramList.add(vicicleFooterReq.getF_LEKKUNZEE  ());
            paramList.add(vicicleFooterReq.getF_PAO());
            paramList.add(vicicleFooterReq.getF_KORKC());
            paramList.add(vicicleFooterReq.getF_TOLOCKTU());
            paramList.add(vicicleFooterReq.getF_SO());
            paramList.add(vicicleFooterReq.getF_PABUD());
            paramList.add(vicicleFooterReq.getF_FAIKHANG());
            paramList.add(vicicleFooterReq.getF_FAITHAIY());
            paramList.add(vicicleFooterReq.getF_BGTHOM());
            paramList.add(vicicleFooterReq.getF_GALATY_NO  ());
            paramList.add(vicicleFooterReq.getF_GALATY_DEP ());
            paramList.add(vicicleFooterReq.getL_TRIES_1());
            paramList.add(vicicleFooterReq.getL_TRIES_2());
            paramList.add(vicicleFooterReq.getL_TRIES_3());
            paramList.add(vicicleFooterReq.getL_TRIES_4());
            paramList.add(vicicleFooterReq.getL_TRIES_5());
            paramList.add(vicicleFooterReq.getL_TRIES_6());
            paramList.add(vicicleFooterReq.getL_TRIES_7());
            paramList.add(vicicleFooterReq.getL_TRIES_8());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_1());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_2());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_3());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_4());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_5());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_6());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_7());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_8());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_1  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_2  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_3  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_4  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_5  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_6  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_7  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_8  ());
            paramList.add(vicicleFooterReq.getR_TRIES_1());
            paramList.add(vicicleFooterReq.getR_TRIES_2());
            paramList.add(vicicleFooterReq.getR_TRIES_3());
            paramList.add(vicicleFooterReq.getR_TRIES_4());
            paramList.add(vicicleFooterReq.getR_TRIES_5());
            paramList.add(vicicleFooterReq.getR_TRIES_6());
            paramList.add(vicicleFooterReq.getR_TRIES_7());
            paramList.add(vicicleFooterReq.getR_TRIES_8());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_1());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_2());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_3());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_4());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_5());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_6());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_7());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_8());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_1  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_2  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_3  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_4  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_5  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_6  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_7  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_8  ());

            paramList.add(vicicleFooterReq.getF_KM1());
            paramList.add(vicicleFooterReq.getF_KM2());
            paramList.add(vicicleFooterReq.getF_KM3());
            paramList.add(vicicleFooterReq.getF_KM4());
            paramList.add(vicicleFooterReq.getF_KM5());
            paramList.add(vicicleFooterReq.getF_KM6());
            paramList.add(vicicleFooterReq.getF_KM7());
            paramList.add(vicicleFooterReq.getF_KM8());
            paramList.add(vicicleFooterReq.getF_KM9());
            paramList.add(vicicleFooterReq.getF_KM10());
            paramList.add(vicicleFooterReq.getF_KM11());
            paramList.add(vicicleFooterReq.getF_KM12());
            paramList.add(vicicleFooterReq.getF_KM13());
            paramList.add(vicicleFooterReq.getF_KM14());
            paramList.add(vicicleFooterReq.getF_KM15());
            paramList.add(vicicleFooterReq.getF_KM16());
            paramList.add(vicicleFooterReq.getF_KM_LL1());
            paramList.add(vicicleFooterReq.getF_KM_LL2());
            paramList.add(vicicleFooterReq.getF_KM_LL3());
            paramList.add(vicicleFooterReq.getF_KM_LL4());
            paramList.add(vicicleFooterReq.getF_KM_LL9());
            paramList.add(vicicleFooterReq.getF_KM_LL10());
            paramList.add(vicicleFooterReq.getF_KM_LL11());
            paramList.add(vicicleFooterReq.getF_KM_LL12());
            paramList.add(vicicleFooterReq.getF_KM_LL5());
            paramList.add(vicicleFooterReq.getF_KM_LL6());
            paramList.add(vicicleFooterReq.getF_KM_LL7());
            paramList.add(vicicleFooterReq.getF_KM_LL8());
            paramList.add(vicicleFooterReq.getF_KM_LL13());
            paramList.add(vicicleFooterReq.getF_KM_LL14());
            paramList.add(vicicleFooterReq.getF_KM_LL15());
            paramList.add(vicicleFooterReq.getF_KM_LL16());
            return EBankJdbcTemplate.update(sql, paramList.toArray());

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int delVicicleFooter(VicicleFooterReq vicicleFooterReq) {
        int i=0;
        try{
            String SQL="delete from TB_FOOTER_TRUCH where key_id='"+vicicleFooterReq.getKey_id()+"'";
            EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    @Override
    public int updateVicicleFooter(VicicleFooterReq vicicleFooterReq) {
        log.info("data:"+vicicleFooterReq);
        log.info("req:"+vicicleFooterReq.getKey_id());
        log.info("req:"+vicicleFooterReq.getF_KM_LL1());
        log.info("req:"+vicicleFooterReq.getF_KM_LL2());
        log.info("req:"+vicicleFooterReq.getF_KM_LL3());
        log.info("req:"+vicicleFooterReq.getF_KM_LL4());
        log.info("req:"+vicicleFooterReq.getF_KM_LL5());
        log.info("req:"+vicicleFooterReq.getF_KM_LL6());
        log.info("req:"+vicicleFooterReq.getF_KM_LL7());
        log.info("req:"+vicicleFooterReq.getF_KM_LL8());
        log.info("req:"+vicicleFooterReq.getF_KM_LL9());

        try{
            String sql="update  TB_FOOTER_TRUCH set F_BRANCH=?,F_YEAR=?,F_CAR_TYPE=?,F_DATEEXPRIED=?,F_CARD_NO=?,F_LEKKUNZEE=?,F_PAO=?,F_KORKC=?,F_TOLOCKTU=?,F_SO=?,F_PABUD=?,F_FAIKHANG=?,F_FAITHAIY=?,F_BGTHOM\n" +
                    "=?,F_GALATY_NO=?,F_GALATY_DEP=?,L_TRIES_1=?,L_TRIES_2=?,L_TRIES_3=?,L_TRIES_4=?,L_TRIES_5=?,L_TRIES_6=?,L_TRIES_7=?,L_TRIES_8=?,L_TRIES_DATE_1=?,L_TRIES_DATE_2\n" +
                    "=?,L_TRIES_DATE_3=?,L_TRIES_DATE_4=?,L_TRIES_DATE_5=?,L_TRIES_DATE_6=?,L_TRIES_DATE_7=?,L_TRIES_DATE_8=?,L_TRIES_KM_1=?,L_TRIES_KM_2=?,L_TRIES_KM_3=?,L_TRIES_KM_4\n" +
                    "=?,L_TRIES_KM_5=?,L_TRIES_KM_6=?,L_TRIES_KM_7=?,L_TRIES_KM_8=?,R_TRIES_1=?,R_TRIES_2=?,R_TRIES_3=?,R_TRIES_4=?,R_TRIES_5=?,R_TRIES_6=?,R_TRIES_7=?,R_TRIES_8\n" +
                    "=?,R_TRIES_DATE_1=?,R_TRIES_DATE_2=?,R_TRIES_DATE_3=?,R_TRIES_DATE_4=?,R_TRIES_DATE_5=?,R_TRIES_DATE_6=?,R_TRIES_DATE_7=?,R_TRIES_DATE_8=?,R_TRIES_KM_1=?,R_TRIES_KM_2\n" +
                    "=?,R_TRIES_KM_3=?,R_TRIES_KM_4=?,R_TRIES_KM_5=?,R_TRIES_KM_6=?,R_TRIES_KM_7=?,R_TRIES_KM_8=?,F_STATUS=?," +
                    "F_KM1=?,\n" +
                    "F_KM2=?,\n" +
                    "F_KM3=?,\n" +
                    "F_KM4=?,\n" +
                    "F_KM5=?,\n" +
                    "F_KM6=?,\n" +
                    "F_KM7=?,\n" +
                    "F_KM8=?,\n" +
                    "F_KM9=?,\n" +
                    "F_KM10=?,\n" +
                    "F_KM11=?,\n" +
                    "F_KM12=?,\n" +
                    "F_KM13=?,\n" +
                    "F_KM14=?,\n" +
                    "F_KM15=?,\n" +
                    "F_KM16=?,\n" +
                    "F_KM_LL1=?,\n" +
                    "F_KM_LL2=?,\n" +
                    "F_KM_LL3=?,\n" +
                    "F_KM_LL4=?,\n" +
                    "F_KM_LL5=?,\n" +
                    "F_KM_LL6=?,\n" +
                    "F_KM_LL7=?,\n" +
                    "F_KM_LL8=?,\n" +
                    "F_KM_LL9=?,\n" +
                    "F_KM_LL10=?,\n" +
                    "F_KM_LL11=?,\n" +
                    "F_KM_LL12=?,\n" +
                    "F_KM_LL13=?,\n" +
                    "F_KM_LL14=?,\n" +
                    "F_KM_LL15=?,\n" +
                    "F_KM_LL16=? where key_id='"+vicicleFooterReq.getKey_id()+"'";
            log.info("sql"+sql);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleFooterReq.getF_BRANCH());
            paramList.add(vicicleFooterReq.getF_YEAR  ());
            paramList.add(vicicleFooterReq.getF_CAR_TYPE());
            paramList.add(vicicleFooterReq.getF_DATEEXPRIED());
            paramList.add(vicicleFooterReq.getF_CARD_NO ());
            paramList.add(vicicleFooterReq.getF_LEKKUNZEE  ());
            paramList.add(vicicleFooterReq.getF_PAO   ());
            paramList.add(vicicleFooterReq.getF_KORKC ());
            paramList.add(vicicleFooterReq.getF_TOLOCKTU());
            paramList.add(vicicleFooterReq.getF_SO    ());
            paramList.add(vicicleFooterReq.getF_PABUD ());
            paramList.add(vicicleFooterReq.getF_FAIKHANG());
            paramList.add(vicicleFooterReq.getF_FAITHAIY());
            paramList.add(vicicleFooterReq.getF_BGTHOM());
            paramList.add(vicicleFooterReq.getF_GALATY_NO  ());
            paramList.add(vicicleFooterReq.getF_GALATY_DEP ());
            paramList.add(vicicleFooterReq.getL_TRIES_1());
            paramList.add(vicicleFooterReq.getL_TRIES_2());
            paramList.add(vicicleFooterReq.getL_TRIES_3());
            paramList.add(vicicleFooterReq.getL_TRIES_4());
            paramList.add(vicicleFooterReq.getL_TRIES_5());
            paramList.add(vicicleFooterReq.getL_TRIES_6());
            paramList.add(vicicleFooterReq.getL_TRIES_7());
            paramList.add(vicicleFooterReq.getL_TRIES_8());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_1());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_2());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_3());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_4());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_5());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_6());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_7());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_8());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_1  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_2  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_3  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_4  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_5  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_6  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_7  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_8  ());
            paramList.add(vicicleFooterReq.getR_TRIES_1());
            paramList.add(vicicleFooterReq.getR_TRIES_2());
            paramList.add(vicicleFooterReq.getR_TRIES_3());
            paramList.add(vicicleFooterReq.getR_TRIES_4());
            paramList.add(vicicleFooterReq.getR_TRIES_5());
            paramList.add(vicicleFooterReq.getR_TRIES_6());
            paramList.add(vicicleFooterReq.getR_TRIES_7());
            paramList.add(vicicleFooterReq.getR_TRIES_8());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_1());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_2());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_3());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_4());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_5());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_6());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_7());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_8());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_1  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_2  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_3  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_4  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_5  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_6  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_7  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_8  ());
            paramList.add(vicicleFooterReq.getF_STATUS());
            paramList.add(vicicleFooterReq.getF_KM1());
            paramList.add(vicicleFooterReq.getF_KM2());
            paramList.add(vicicleFooterReq.getF_KM3());
            paramList.add(vicicleFooterReq.getF_KM4());
            paramList.add(vicicleFooterReq.getF_KM5());
            paramList.add(vicicleFooterReq.getF_KM6());
            paramList.add(vicicleFooterReq.getF_KM7());
            paramList.add(vicicleFooterReq.getF_KM8());
            paramList.add(vicicleFooterReq.getF_KM9());
            paramList.add(vicicleFooterReq.getF_KM10());
            paramList.add(vicicleFooterReq.getF_KM11());
            paramList.add(vicicleFooterReq.getF_KM12());
            paramList.add(vicicleFooterReq.getF_KM13());
            paramList.add(vicicleFooterReq.getF_KM14());
            paramList.add(vicicleFooterReq.getF_KM15());
            paramList.add(vicicleFooterReq.getF_KM16());
            paramList.add(vicicleFooterReq.getF_KM_LL1());
            paramList.add(vicicleFooterReq.getF_KM_LL2());
            paramList.add(vicicleFooterReq.getF_KM_LL3());
            paramList.add(vicicleFooterReq.getF_KM_LL4());
            paramList.add(vicicleFooterReq.getF_KM_LL5());
            paramList.add(vicicleFooterReq.getF_KM_LL6());
            paramList.add(vicicleFooterReq.getF_KM_LL7());
            paramList.add(vicicleFooterReq.getF_KM_LL8());
            paramList.add(vicicleFooterReq.getF_KM_LL9());
            paramList.add(vicicleFooterReq.getF_KM_LL10());
            paramList.add(vicicleFooterReq.getF_KM_LL11());
            paramList.add(vicicleFooterReq.getF_KM_LL12());
            paramList.add(vicicleFooterReq.getF_KM_LL13());
            paramList.add(vicicleFooterReq.getF_KM_LL14());
            paramList.add(vicicleFooterReq.getF_KM_LL15());
            paramList.add(vicicleFooterReq.getF_KM_LL16());

            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<VicicleFooter> ReportFooter(ReportAllReq reportAllReq) {

        log.info("start:"+reportAllReq.getStartDate());
        log.info("end:"+reportAllReq.getEndDate());
        try {
            String sql = "select * from V_RE_FOOTER_HIS where HIS_DATE1 BETWEEN '"+reportAllReq.getStartDate()+"' AND '"+reportAllReq.getEndDate()+"'";
           log.info("sql:"+sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<VicicleFooter>() {
                @Override
                public VicicleFooter mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleFooter tr =new VicicleFooter();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setF_BRANCH(rs.getString("F_BRANCH"));
                    tr.setF_YEAR(rs.getString("F_YEAR"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setF_DATEEXPRIED(rs.getString("F_DATEEXPRIED"));
                    tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                    tr.setF_LEKKUNZEE(rs.getString("F_LEKKUNZEE"));
                    tr.setF_PAO(rs.getString("F_PAO"));
                    tr.setF_KORKC (rs.getString("F_KORKC"));
                    tr.setF_TOLOCKTU(rs.getString("F_TOLOCKTU"));
                    tr.setF_SO(rs.getString("F_SO"));
                    tr.setF_PABUD (rs.getString("F_PABUD"));
                    tr.setF_FAIKHANG(rs.getString("F_FAIKHANG"));
                    tr.setF_FAITHAIY(rs.getString("F_FAITHAIY"));
                    tr.setF_BGTHOM(rs.getString("F_BGTHOM"));
                    tr.setF_GALATY_NO  (rs.getString("F_GALATY_NO"));
                    tr.setF_GALATY_DEP (rs.getString("F_GALATY_DEP"));
                    tr.setL_TRIES_1(rs.getString("L_TRIES_1"));
                    tr.setL_TRIES_2(rs.getString("L_TRIES_2"));
                    tr.setL_TRIES_3(rs.getString("L_TRIES_3"));
                    tr.setL_TRIES_4(rs.getString("L_TRIES_4"));
                    tr.setL_TRIES_5(rs.getString("L_TRIES_5"));
                    tr.setL_TRIES_6(rs.getString("L_TRIES_6"));
                    tr.setL_TRIES_7(rs.getString("L_TRIES_7"));
                    tr.setL_TRIES_8(rs.getString("L_TRIES_8"));
                    tr.setL_TRIES_DATE_1(rs.getString("L_TRIES_DATE_1"));
                    tr.setL_TRIES_DATE_2(rs.getString("L_TRIES_DATE_2"));
                    tr.setL_TRIES_DATE_3(rs.getString("L_TRIES_DATE_3"));
                    tr.setL_TRIES_DATE_4(rs.getString("L_TRIES_DATE_4"));
                    tr.setL_TRIES_DATE_5(rs.getString("L_TRIES_DATE_5"));
                    tr.setL_TRIES_DATE_6(rs.getString("L_TRIES_DATE_6"));
                    tr.setL_TRIES_DATE_7(rs.getString("L_TRIES_DATE_7"));
                    tr.setL_TRIES_DATE_8(rs.getString("L_TRIES_DATE_8"));
                    tr.setL_TRIES_KM_1(rs.getString("L_TRIES_KM_1"));
                    tr.setL_TRIES_KM_2(rs.getString("L_TRIES_KM_2"));
                    tr.setL_TRIES_KM_3(rs.getString("L_TRIES_KM_3"));
                    tr.setL_TRIES_KM_4(rs.getString("L_TRIES_KM_4"));
                    tr.setL_TRIES_KM_5(rs.getString("L_TRIES_KM_5"));
                    tr.setL_TRIES_KM_6(rs.getString("L_TRIES_KM_6"));
                    tr.setL_TRIES_KM_7(rs.getString("L_TRIES_KM_7"));
                    tr.setL_TRIES_KM_8(rs.getString("L_TRIES_KM_8"));
                    tr.setR_TRIES_1(rs.getString("R_TRIES_1"));
                    tr.setR_TRIES_2(rs.getString("R_TRIES_2"));
                    tr.setR_TRIES_3(rs.getString("R_TRIES_3"));
                    tr.setR_TRIES_4(rs.getString("R_TRIES_4"));
                    tr.setR_TRIES_5(rs.getString("R_TRIES_5"));
                    tr.setR_TRIES_6(rs.getString("R_TRIES_6"));
                    tr.setR_TRIES_7(rs.getString("R_TRIES_7"));
                    tr.setR_TRIES_8(rs.getString("R_TRIES_8"));
                    tr.setR_TRIES_DATE_1(rs.getString("R_TRIES_DATE_1"));
                    tr.setR_TRIES_DATE_2(rs.getString("R_TRIES_DATE_2"));
                    tr.setR_TRIES_DATE_3(rs.getString("R_TRIES_DATE_3"));
                    tr.setR_TRIES_DATE_4(rs.getString("R_TRIES_DATE_4"));
                    tr.setR_TRIES_DATE_5(rs.getString("R_TRIES_DATE_5"));
                    tr.setR_TRIES_DATE_6(rs.getString("R_TRIES_DATE_6"));
                    tr.setR_TRIES_DATE_7(rs.getString("R_TRIES_DATE_7"));
                    tr.setR_TRIES_DATE_8(rs.getString("R_TRIES_DATE_8"));
                    tr.setR_TRIES_KM_1  (rs.getString("R_TRIES_KM_1"));
                    tr.setR_TRIES_KM_2  (rs.getString("R_TRIES_KM_2"));
                    tr.setR_TRIES_KM_3  (rs.getString("R_TRIES_KM_3"));
                    tr.setR_TRIES_KM_4  (rs.getString("R_TRIES_KM_4"));
                    tr.setR_TRIES_KM_5  (rs.getString("R_TRIES_KM_5"));
                    tr.setR_TRIES_KM_6  (rs.getString("R_TRIES_KM_6"));
                    tr.setR_TRIES_KM_7  (rs.getString("R_TRIES_KM_7"));
                    tr.setR_TRIES_KM_8  (rs.getString("R_TRIES_KM_8"));
                    tr.setF_STATUS(rs.getString("F_STATUS"));
                    tr.setHIS_DATE(rs.getString("HIS_DATE"));
                    tr.setHis_REASON(rs.getString("his_reson"));
                    tr.setF_KM1 (rs.getString("F_KM1"));
                    tr.setF_KM2 (rs.getString("F_KM2"));
                    tr.setF_KM3 (rs.getString("F_KM3"));
                    tr.setF_KM4 (rs.getString("F_KM4"));
                    tr.setF_KM5 (rs.getString("F_KM5"));
                    tr.setF_KM6 (rs.getString("F_KM6"));
                    tr.setF_KM7 (rs.getString("F_KM7"));
                    tr.setF_KM8 (rs.getString("F_KM8"));
                    tr.setF_KM9 (rs.getString("F_KM9"));
                    tr.setF_KM10 (rs.getString("F_KM10"));
                    tr.setF_KM11 (rs.getString("F_KM11"));
                    tr.setF_KM12 (rs.getString("F_KM12"));
                    tr.setF_KM13 (rs.getString("F_KM13"));
                    tr.setF_KM14 (rs.getString("F_KM14"));
                    tr.setF_KM15 (rs.getString("F_KM15"));
                    tr.setF_KM16 (rs.getString("F_KM16"));
                    tr.setF_KM_LL1 (rs.getString("F_KM_LL1"));
                    tr.setF_KM_LL2 (rs.getString("F_KM_LL2"));
                    tr.setF_KM_LL3 (rs.getString("F_KM_LL3"));
                    tr.setF_KM_LL4 (rs.getString("F_KM_LL4"));
                    tr.setF_KM_LL5 (rs.getString("F_KM_LL5"));
                    tr.setF_KM_LL6 (rs.getString("F_KM_LL6"));
                    tr.setF_KM_LL7 (rs.getString("F_KM_LL7"));
                    tr.setF_KM_LL8 (rs.getString("F_KM_LL8"));
                    tr.setF_KM_LL9 (rs.getString("F_KM_LL9"));
                    tr.setF_KM_LL10 (rs.getString("F_KM_LL10"));
                    tr.setF_KM_LL11(rs.getString("F_KM_LL11"));
                    tr.setF_KM_LL12 (rs.getString("F_KM_LL12"));
                    tr.setF_KM_LL13 (rs.getString("F_KM_LL13"));
                    tr.setF_KM_LL14 (rs.getString("F_KM_LL14"));
                    tr.setF_KM_LL15 (rs.getString("F_KM_LL15"));
                    tr.setF_KM_LL16(rs.getString("F_KM_LL16"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VicicleFooter> ListVicicleFooterCombo1() {
        try {
            String sql = "select * from V_ALL_FOOTER_TRUCH WHERE F_STATUS='Y' ";
            return EBankJdbcTemplate.query(sql, new RowMapper<VicicleFooter>() {
                @Override
                public VicicleFooter mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleFooter tr =new VicicleFooter();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setF_BRANCH(rs.getString("F_BRANCH"));
                    tr.setF_YEAR(rs.getString("F_YEAR"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setF_DATEEXPRIED(rs.getString("F_DATEEXPRIED"));
                    tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                    tr.setF_LEKKUNZEE(rs.getString("F_LEKKUNZEE"));
                    tr.setF_PAO(rs.getString("F_PAO"));
                    tr.setF_KORKC (rs.getString("F_KORKC"));
                    tr.setF_TOLOCKTU(rs.getString("F_TOLOCKTU"));
                    tr.setF_SO(rs.getString("F_SO"));
                    tr.setF_PABUD (rs.getString("F_PABUD"));
                    tr.setF_FAIKHANG(rs.getString("F_FAIKHANG"));
                    tr.setF_FAITHAIY(rs.getString("F_FAITHAIY"));
                    tr.setF_BGTHOM(rs.getString("F_BGTHOM"));
                    tr.setF_GALATY_NO  (rs.getString("F_GALATY_NO"));
                    tr.setF_GALATY_DEP (rs.getString("F_GALATY_DEP"));
                    tr.setL_TRIES_1(rs.getString("L_TRIES_1"));
                    tr.setL_TRIES_2(rs.getString("L_TRIES_2"));
                    tr.setL_TRIES_3(rs.getString("L_TRIES_3"));
                    tr.setL_TRIES_4(rs.getString("L_TRIES_4"));
                    tr.setL_TRIES_5(rs.getString("L_TRIES_5"));
                    tr.setL_TRIES_6(rs.getString("L_TRIES_6"));
                    tr.setL_TRIES_7(rs.getString("L_TRIES_7"));
                    tr.setL_TRIES_8(rs.getString("L_TRIES_8"));
                    tr.setL_TRIES_DATE_1(rs.getString("L_TRIES_DATE_1"));
                    tr.setL_TRIES_DATE_2(rs.getString("L_TRIES_DATE_2"));
                    tr.setL_TRIES_DATE_3(rs.getString("L_TRIES_DATE_3"));
                    tr.setL_TRIES_DATE_4(rs.getString("L_TRIES_DATE_4"));
                    tr.setL_TRIES_DATE_5(rs.getString("L_TRIES_DATE_5"));
                    tr.setL_TRIES_DATE_6(rs.getString("L_TRIES_DATE_6"));
                    tr.setL_TRIES_DATE_7(rs.getString("L_TRIES_DATE_7"));
                    tr.setL_TRIES_DATE_8(rs.getString("L_TRIES_DATE_8"));
                    tr.setL_TRIES_KM_1(rs.getString("L_TRIES_KM_1"));
                    tr.setL_TRIES_KM_2(rs.getString("L_TRIES_KM_2"));
                    tr.setL_TRIES_KM_3(rs.getString("L_TRIES_KM_3"));
                    tr.setL_TRIES_KM_4(rs.getString("L_TRIES_KM_4"));
                    tr.setL_TRIES_KM_5(rs.getString("L_TRIES_KM_5"));
                    tr.setL_TRIES_KM_6(rs.getString("L_TRIES_KM_6"));
                    tr.setL_TRIES_KM_7(rs.getString("L_TRIES_KM_7"));
                    tr.setL_TRIES_KM_8(rs.getString("L_TRIES_KM_8"));
                    tr.setR_TRIES_1(rs.getString("R_TRIES_1"));
                    tr.setR_TRIES_2(rs.getString("R_TRIES_2"));
                    tr.setR_TRIES_3(rs.getString("R_TRIES_3"));
                    tr.setR_TRIES_4(rs.getString("R_TRIES_4"));
                    tr.setR_TRIES_5(rs.getString("R_TRIES_5"));
                    tr.setR_TRIES_6(rs.getString("R_TRIES_6"));
                    tr.setR_TRIES_7(rs.getString("R_TRIES_7"));
                    tr.setR_TRIES_8(rs.getString("R_TRIES_8"));
                    tr.setR_TRIES_DATE_1(rs.getString("R_TRIES_DATE_1"));
                    tr.setR_TRIES_DATE_2(rs.getString("R_TRIES_DATE_2"));
                    tr.setR_TRIES_DATE_3(rs.getString("R_TRIES_DATE_3"));
                    tr.setR_TRIES_DATE_4(rs.getString("R_TRIES_DATE_4"));
                    tr.setR_TRIES_DATE_5(rs.getString("R_TRIES_DATE_5"));
                    tr.setR_TRIES_DATE_6(rs.getString("R_TRIES_DATE_6"));
                    tr.setR_TRIES_DATE_7(rs.getString("R_TRIES_DATE_7"));
                    tr.setR_TRIES_DATE_8(rs.getString("R_TRIES_DATE_8"));
                    tr.setR_TRIES_KM_1  (rs.getString("R_TRIES_KM_1"));
                    tr.setR_TRIES_KM_2  (rs.getString("R_TRIES_KM_2"));
                    tr.setR_TRIES_KM_3  (rs.getString("R_TRIES_KM_3"));
                    tr.setR_TRIES_KM_4  (rs.getString("R_TRIES_KM_4"));
                    tr.setR_TRIES_KM_5  (rs.getString("R_TRIES_KM_5"));
                    tr.setR_TRIES_KM_6  (rs.getString("R_TRIES_KM_6"));
                    tr.setR_TRIES_KM_7  (rs.getString("R_TRIES_KM_7"));
                    tr.setR_TRIES_KM_8  (rs.getString("R_TRIES_KM_8"));
                    tr.setF_STATUS(rs.getString("F_STATUS"));
                    tr.setHis_REASON(rs.getString("HIS_RESON"));
                    tr.setF_KM1 (rs.getString("F_KM1"));
                    tr.setF_KM2 (rs.getString("F_KM2"));
                    tr.setF_KM3 (rs.getString("F_KM3"));
                    tr.setF_KM4 (rs.getString("F_KM4"));
                    tr.setF_KM5 (rs.getString("F_KM5"));
                    tr.setF_KM6 (rs.getString("F_KM6"));
                    tr.setF_KM7 (rs.getString("F_KM7"));
                    tr.setF_KM8 (rs.getString("F_KM8"));
                    tr.setF_KM9 (rs.getString("F_KM9"));
                    tr.setF_KM10 (rs.getString("F_KM10"));
                    tr.setF_KM11 (rs.getString("F_KM11"));
                    tr.setF_KM12 (rs.getString("F_KM12"));
                    tr.setF_KM13 (rs.getString("F_KM13"));
                    tr.setF_KM14 (rs.getString("F_KM14"));
                    tr.setF_KM15 (rs.getString("F_KM15"));
                    tr.setF_KM16 (rs.getString("F_KM16"));
                    tr.setF_KM_LL1 (rs.getString("F_KM_LL1"));
                    tr.setF_KM_LL2 (rs.getString("F_KM_LL2"));
                    tr.setF_KM_LL3 (rs.getString("F_KM_LL3"));
                    tr.setF_KM_LL4 (rs.getString("F_KM_LL4"));
                    tr.setF_KM_LL5 (rs.getString("F_KM_LL5"));
                    tr.setF_KM_LL6 (rs.getString("F_KM_LL6"));
                    tr.setF_KM_LL7 (rs.getString("F_KM_LL7"));
                    tr.setF_KM_LL8 (rs.getString("F_KM_LL8"));
                    tr.setF_KM_LL9 (rs.getString("F_KM_LL9"));
                    tr.setF_KM_LL10 (rs.getString("F_KM_LL10"));
                    tr.setF_KM_LL11(rs.getString("F_KM_LL11"));
                    tr.setF_KM_LL12 (rs.getString("F_KM_LL12"));
                    tr.setF_KM_LL13 (rs.getString("F_KM_LL13"));
                    tr.setF_KM_LL14 (rs.getString("F_KM_LL14"));
                    tr.setF_KM_LL15 (rs.getString("F_KM_LL15"));
                    tr.setF_KM_LL16(rs.getString("F_KM_LL16"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveVicicleFooterHistory(VicicleFooterReq vicicleFooterReq) {

        log.info("req1:"+vicicleFooterReq.getF_KM_LL1());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL2());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL3());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL4());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL5());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL6());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL7());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL8());
        log.info("req1:"+vicicleFooterReq.getF_KM_LL9());
        try {
            String sql ="insert into TB_FOOTER_TRUCH_HISTORY(F_BRANCH,F_YEAR,F_CAR_TYPE,F_DATEEXPRIED,F_CARD_NO,F_LEKKUNZEE,F_PAO,F_KORKC,F_TOLOCKTU,F_SO,F_PABUD,F_FAIKHANG,F_FAITHAIY,F_BGTHOM\n" +
                    ",F_GALATY_NO,F_GALATY_DEP,L_TRIES_1,L_TRIES_2,L_TRIES_3,L_TRIES_4,L_TRIES_5,L_TRIES_6,L_TRIES_7,L_TRIES_8,L_TRIES_DATE_1,L_TRIES_DATE_2\n" +
                    ",L_TRIES_DATE_3,L_TRIES_DATE_4,L_TRIES_DATE_5,L_TRIES_DATE_6,L_TRIES_DATE_7,L_TRIES_DATE_8,L_TRIES_KM_1,L_TRIES_KM_2,L_TRIES_KM_3,L_TRIES_KM_4\n" +
                    ",L_TRIES_KM_5,L_TRIES_KM_6,L_TRIES_KM_7,L_TRIES_KM_8,R_TRIES_1,R_TRIES_2,R_TRIES_3,R_TRIES_4,R_TRIES_5,R_TRIES_6,R_TRIES_7,R_TRIES_8\n" +
                    ",R_TRIES_DATE_1,R_TRIES_DATE_2,R_TRIES_DATE_3,R_TRIES_DATE_4,R_TRIES_DATE_5,R_TRIES_DATE_6,R_TRIES_DATE_7,R_TRIES_DATE_8,R_TRIES_KM_1,R_TRIES_KM_2\n" +
                    ",R_TRIES_KM_3,R_TRIES_KM_4,R_TRIES_KM_5,R_TRIES_KM_6,R_TRIES_KM_7,R_TRIES_KM_8,F_STATUS,HIS_DATE,HIS_RESON,F_KM1,\n" +
                    "F_KM2,\n" +
                    "F_KM3,\n" +
                    "F_KM4,\n" +
                    "F_KM5,\n" +
                    "F_KM6,\n" +
                    "F_KM7,\n" +
                    "F_KM8,\n" +
                    "F_KM9,\n" +
                    "F_KM10,\n" +
                    "F_KM11,\n" +
                    "F_KM12,\n" +
                    "F_KM13,\n" +
                    "F_KM14,\n" +
                    "F_KM15,\n" +
                    "F_KM16,\n" +
                    "F_KM_LL1,\n" +
                    "F_KM_LL2,\n" +
                    "F_KM_LL3,\n" +
                    "F_KM_LL4,\n" +
                    "F_KM_LL5,\n" +
                    "F_KM_LL6,\n" +
                    "F_KM_LL7,\n" +
                    "F_KM_LL8,\n" +
                    "F_KM_LL9,\n" +
                    "F_KM_LL10,\n" +
                    "F_KM_LL11,\n" +
                    "F_KM_LL12,\n" +
                    "F_KM_LL13,\n" +
                    "F_KM_LL14,\n" +
                    "F_KM_LL15,\n" +
                    "F_KM_LL16) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'N',now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
           // log.info("sql"+sql);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleFooterReq.getF_BRANCH());
            paramList.add(vicicleFooterReq.getF_YEAR  ());
            paramList.add(vicicleFooterReq.getF_CAR_TYPE());
            paramList.add(vicicleFooterReq.getF_DATEEXPRIED());
            paramList.add(vicicleFooterReq.getF_CARD_NO());
            paramList.add(vicicleFooterReq.getF_LEKKUNZEE());
            paramList.add(vicicleFooterReq.getF_PAO());
            paramList.add(vicicleFooterReq.getF_KORKC());
            paramList.add(vicicleFooterReq.getF_TOLOCKTU());
            paramList.add(vicicleFooterReq.getF_SO());
            paramList.add(vicicleFooterReq.getF_PABUD());
            paramList.add(vicicleFooterReq.getF_FAIKHANG());
            paramList.add(vicicleFooterReq.getF_FAITHAIY());
            paramList.add(vicicleFooterReq.getF_BGTHOM());
            paramList.add(vicicleFooterReq.getF_GALATY_NO());
            paramList.add(vicicleFooterReq.getF_GALATY_DEP());
            paramList.add(vicicleFooterReq.getL_TRIES_1());
            paramList.add(vicicleFooterReq.getL_TRIES_2());
            paramList.add(vicicleFooterReq.getL_TRIES_3());
            paramList.add(vicicleFooterReq.getL_TRIES_4());
            paramList.add(vicicleFooterReq.getL_TRIES_5());
            paramList.add(vicicleFooterReq.getL_TRIES_6());
            paramList.add(vicicleFooterReq.getL_TRIES_7());
            paramList.add(vicicleFooterReq.getL_TRIES_8());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_1());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_2());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_3());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_4());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_5());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_6());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_7());
            paramList.add(vicicleFooterReq.getL_TRIES_DATE_8());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_1  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_2  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_3  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_4  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_5  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_6  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_7  ());
            paramList.add(vicicleFooterReq.getL_TRIES_KM_8  ());
            paramList.add(vicicleFooterReq.getR_TRIES_1());
            paramList.add(vicicleFooterReq.getR_TRIES_2());
            paramList.add(vicicleFooterReq.getR_TRIES_3());
            paramList.add(vicicleFooterReq.getR_TRIES_4());
            paramList.add(vicicleFooterReq.getR_TRIES_5());
            paramList.add(vicicleFooterReq.getR_TRIES_6());
            paramList.add(vicicleFooterReq.getR_TRIES_7());
            paramList.add(vicicleFooterReq.getR_TRIES_8());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_1());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_2());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_3());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_4());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_5());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_6());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_7());
            paramList.add(vicicleFooterReq.getR_TRIES_DATE_8());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_1  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_2  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_3  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_4  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_5  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_6  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_7  ());
            paramList.add(vicicleFooterReq.getR_TRIES_KM_8  ());
            paramList.add(vicicleFooterReq.getHis_REASON());
            paramList.add(vicicleFooterReq.getF_KM1());
            paramList.add(vicicleFooterReq.getF_KM2());
            paramList.add(vicicleFooterReq.getF_KM3());
            paramList.add(vicicleFooterReq.getF_KM4());
            paramList.add(vicicleFooterReq.getF_KM5());
            paramList.add(vicicleFooterReq.getF_KM6());
            paramList.add(vicicleFooterReq.getF_KM7());
            paramList.add(vicicleFooterReq.getF_KM8());
            paramList.add(vicicleFooterReq.getF_KM9());
            paramList.add(vicicleFooterReq.getF_KM10());
            paramList.add(vicicleFooterReq.getF_KM11());
            paramList.add(vicicleFooterReq.getF_KM12());
            paramList.add(vicicleFooterReq.getF_KM13());
            paramList.add(vicicleFooterReq.getF_KM14());
            paramList.add(vicicleFooterReq.getF_KM15());
            paramList.add(vicicleFooterReq.getF_KM16());
            paramList.add(vicicleFooterReq.getF_KM_LL1());
            paramList.add(vicicleFooterReq.getF_KM_LL2());
            paramList.add(vicicleFooterReq.getF_KM_LL3());
            paramList.add(vicicleFooterReq.getF_KM_LL4());
            paramList.add(vicicleFooterReq.getF_KM_LL5());
            paramList.add(vicicleFooterReq.getF_KM_LL6());
            paramList.add(vicicleFooterReq.getF_KM_LL7());
            paramList.add(vicicleFooterReq.getF_KM_LL8());
            paramList.add(vicicleFooterReq.getF_KM_LL9());
            paramList.add(vicicleFooterReq.getF_KM_LL10());
            paramList.add(vicicleFooterReq.getF_KM_LL11());
            paramList.add(vicicleFooterReq.getF_KM_LL12());
            paramList.add(vicicleFooterReq.getF_KM_LL13());
            paramList.add(vicicleFooterReq.getF_KM_LL14());
            paramList.add(vicicleFooterReq.getF_KM_LL15());
            paramList.add(vicicleFooterReq.getF_KM_LL16());
            return EBankJdbcTemplate.update(sql, paramList.toArray());

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
