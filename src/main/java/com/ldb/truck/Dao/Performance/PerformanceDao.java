package com.ldb.truck.Dao.Performance;

import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Performance.*;
import com.ldb.truck.Model.Login.Report.ReportAll;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;
import com.ldb.truck.RowMapper.Performance.*;
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
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class PerformanceDao implements PerformanceInDao {
    private static final Logger log = LogManager.getLogger(PerformanceDao.class);
    //get bill No  ----T-10001 like
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    @Override
    public List<getBillNo> getBillNo(getBillNoReg getBillNoReg) {
        List<getBillNo> result = new ArrayList<>();
        try {
            String SQL = "select * from V_GET_BILLNO  where LAHUD_POYLOD like '%"+getBillNoReg.getLahud_poylod()+"%' AND D_STATUS='N'";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new GetBillNoMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    //---set amount when performance
    @Override
    public int updateDetailsAmount(PerformanceReq performanceReq){
        log.info("1:"+performanceReq.getPerformanceTotal());
        log.info("getProAmount:"+performanceReq.getProAmount());
        log.info("getProTotalAmount:"+performanceReq.getProTotalAmount());
        log.info("2:");
        List<Performance> data = new ArrayList<>();
        try
        {
            String sql = "update TB_DETAILS set PRODUCT_SIZE=?,TOTAL_PRICE=?,PRICE=?,CURRENCY=? where LAHUD_POYLOD=?";
            log.info("SQL UP Details:"+sql);
            List<String> paraList = new ArrayList<>();
            paraList.add(performanceReq.getProSize());
            paraList.add(performanceReq.getProTotalAmount());
            paraList.add(performanceReq.getProAmount());
            paraList.add(performanceReq.getCurrency());
            //paraList.add(performanceReq.getStaff_Curr());
            paraList.add(performanceReq.getPerformanceBillNo());
            return EBankJdbcTemplate.update(sql,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int savePerformance(PerformanceReq performanceReq) {
        List<Performance> data = new ArrayList<>();
        try {
        String sql ="insert into TB_PERFORMANCE (key_id,performanceBillNo,performanceReDate,performanceDate,performanceTotal," +
                "performanceOvertime,performanceJumPho,performanceFeePolish,performanceFeeTaxung," +
                "performanceFeeTiew,performanceOverVN,performanceBoderLak20,performancePassport," +
                "performanceVaccine,performanceFeeSing,performanceFeeSaPhan,performanceFeeYoktu," +
                "performanceFeeOutContainer,feeUnit,feeTotal,status,PERFORMANCEFE_PAYANG,Last_LEKMAI," +
                "PRODUCT_AMOUNT,PRODUCT_QTY,PRODUCT_TOTALAMOUNT,PRODUCT_SIZE) values (?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'N',?,?,?,?,?,?)";
            log.info("sql insert 01:"+sql);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(performanceReq.getKey_id());
            paramList.add(performanceReq.getPerformanceBillNo());
            paramList.add(performanceReq.getPerformanceReDate());
            paramList.add(performanceReq.getPerformanceTotal());
            paramList.add(performanceReq.getPerformanceOvertime());
            paramList.add(performanceReq.getPerformanceJumPho());
            paramList.add(performanceReq.getPerformanceFeePolish());
            paramList.add(performanceReq.getPerformanceFeeTaxung());
            paramList.add(performanceReq.getPerformanceFeeTiew());
            paramList.add(performanceReq.getPerformanceOverVN());
            paramList.add(performanceReq.getPerformanceBoderLak20());
            paramList.add(performanceReq.getPerformancePassport());
            paramList.add(performanceReq.getPerformanceVaccine());
            paramList.add(performanceReq.getPerformanceFeeSing());
            paramList.add(performanceReq.getPerformanceFeeSaPhan());
            paramList.add(performanceReq.getPerformanceFeeYoktu());
            paramList.add(performanceReq.getPerformanceFeeOutContainer());
            paramList.add(performanceReq.getFeeUnit());
            paramList.add(performanceReq.getFeeTotal());
            paramList.add(performanceReq.getPer_PA());
            paramList.add(performanceReq.getLast_LEKMAI());
            paramList.add(performanceReq.getProAmount());
            paramList.add(performanceReq.getProQty());
            paramList.add(performanceReq.getProTotalAmount());
            paramList.add(performanceReq.getProSize());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public int updateDetails(PerformanceReq performanceReq) {
        System.out.println("show billNo:"+performanceReq.getPerformanceBillNo());
        try
        {
            String sql ="update TB_DETAILS set dpay_money='"+performanceReq.getDpay_Money()+"' , D_STATUS='Y',CURRENCY='"+performanceReq.getCurrency()+"' where LAHUD_POYLOD='"+performanceReq.getPerformanceBillNo()+"'";
            return EBankJdbcTemplate.update(sql);

        }catch (Exception e){
            e.printStackTrace();}
        return 0;
    }
    @Override
    public List<ReportAll> viewPopup() {
        try {
               String sql ="select * from V_RE_ALL where D_STATUS='N'";
            return EBankJdbcTemplate.query(sql, new RowMapper<ReportAll>() {
                @Override
                public ReportAll mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportAll tr =new ReportAll();
                    tr.setSTAFT_ID(rs.getString("STAFT_ID"));
                    tr.setSTAFT_NAME(rs.getString("STAFT_NAME"));
                    tr.setSTAFT_SURNAME(rs.getString("STAFT_SURNAME"));
                    tr.setSTAFT_ID1(rs.getString("STAFT_ID1"));
                    tr.setSTAFT_NAME1(rs.getString("STAFT_NAME1"));
                    tr.setSTAFT_SURNAME1(rs.getString("STAFT_SURNAME1"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setF_BRANCH(rs.getString("F_BRANCH"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setPROVINCE(rs.getString("PROVINCE"));
                    tr.setDETAIL(rs.getString("DETAIL"));
                    tr.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
                    tr.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
                    tr.setPRO_NAME(rs.getString("PRO_NAME"));
                    tr.setPRO_TYPE(rs.getString("PRO_TYPE"));
                    tr.setPRODUCT_AMOUNT(rs.getString("PRODUCT_AMOUNT"));
                    tr.setPRODUCT_SIZE(rs.getString("PRODUCT_SIZE"));
                    tr.setPRODUCT_DETAILS(rs.getString("PRODUCT_DETAILS"));
                    tr.setPRODUCT_FROM(rs.getString("PRODUCT_FROM"));
                    tr.setPRODUCT_TO(rs.getString("PRODUCT_TO"));
                    tr.setPLACE_PD_FROM(rs.getString("PLACE_PD_FROM"));
                    tr.setPLACE_PD_TO(rs.getString("PLACE_PD_TO"));
                    tr.setSTAFF_ID_NUM1(rs.getString("STAFF_ID_NUM1"));
                    tr.setSTAFF_ID_NUM2(rs.getString("STAFF_ID_NUM2"));
                    tr.setSTAFF_BIALIENG(rs.getDouble("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getDouble("STAFF_BIALINEG_KANGJAIY"));
                    tr.setHEADER_ID(rs.getString("HEADER_ID"));
                    tr.setFOOTER_ID(rs.getString("FOOTER_ID"));
                    tr.setOUT_DATE(rs.getString("OUT_DATE"));
                    tr.setIN_DATE(rs.getString("IN_DATE"));
                    tr.setLAIYATHANG(rs.getString("LAIYATHANG"));
                    tr.setSAINUMMUN(rs.getString("SAINUMMUN"));
                    tr.setNUMNUKLOD(rs.getString("NUMNUKLOD"));
                    tr.setKONGNARLOD(rs.getString("KONGNARLOD"));
                    tr.setKHG_MUE_TIDLOD(rs.getString("KHG_MUE_TIDLOD"));
                    tr.setKIM_KILO(rs.getString("KIM_KILO"));
                    tr.setLAHUD_POYLOD(rs.getString("LAHUD_POYLOD"));
                    tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                    tr.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
                    tr.setDETAIL1(rs.getString("DETAIL1"));
                    tr.setPROVINCE1(rs.getString("PROVINCE1"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setPrice(rs.getString("PRICE"));
                    tr.setTotal_price(rs.getString("TOTAL_PRICE"));
                    tr.setPriceNamMun(rs.getDouble("PRIECENUMNUN"));
                    tr.setTotalDay(rs.getString("totalDay"));
                    tr.setStatus(rs.getString("D_STATUS"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
                   // CURRENCY
                   // STAFF_BIALIENG_CUR

                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int storePerformanceHis(PerformanceReq performanceReq) {
        List<Performance> data = new ArrayList<>();
        try {
            String sql ="insert into TB_PERFORMANCE_HIS (key_id,performanceBillNo,performanceReDate,performanceDate,performanceTotal," +
                    "performanceOvertime,performanceJumPho,performanceFeePolish,performanceFeeTaxung," +
                    "performanceFeeTiew,performanceOverVN,performanceBoderLak20,performancePassport," +
                    "performanceVaccine,performanceFeeSing,performanceFeeSaPhan,performanceFeeYoktu," +
                    "performanceFeeOutContainer,feeUnit,feeTotal,status,PERFORMANCEFE_PAYANG,Last_LEKMAI,PRODUCT_AMOUNT,PRODUCT_QTY,PRODUCT_TOTALAMOUNT,PRODUCT_SIZE,CURRENCY,STAFF_BIALIENG_CUR) values (?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'N',?,?,?,?,?,?,?,?)";
            log.info("sql his:"+sql);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(performanceReq.getKey_id());
            paramList.add(performanceReq.getPerformanceBillNo());
            paramList.add(performanceReq.getPerformanceReDate());
            paramList.add(performanceReq.getPerformanceTotal());
            paramList.add(performanceReq.getPerformanceOvertime());
            paramList.add(performanceReq.getPerformanceJumPho());
            paramList.add(performanceReq.getPerformanceFeePolish());
            paramList.add(performanceReq.getPerformanceFeeTaxung());
            paramList.add(performanceReq.getPerformanceFeeTiew());
            paramList.add(performanceReq.getPerformanceOverVN());
            paramList.add(performanceReq.getPerformanceBoderLak20());
            paramList.add(performanceReq.getPerformancePassport());
            paramList.add(performanceReq.getPerformanceVaccine());
            paramList.add(performanceReq.getPerformanceFeeSing());
            paramList.add(performanceReq.getPerformanceFeeSaPhan());
            paramList.add(performanceReq.getPerformanceFeeYoktu());
            paramList.add(performanceReq.getPerformanceFeeOutContainer());
            paramList.add(performanceReq.getFeeUnit());
            paramList.add(performanceReq.getFeeTotal());
            paramList.add(performanceReq.getPer_PA());
            paramList.add(performanceReq.getLast_LEKMAI());
            paramList.add(performanceReq.getProAmount());
            paramList.add(performanceReq.getProQty());
            paramList.add(performanceReq.getProTotalAmount());
            paramList.add(performanceReq.getProSize());
            paramList.add(performanceReq.getCurrency());
            paramList.add(performanceReq.getStaff_Curr());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public List<generateKeyID> genKeyID() {
        List<generateKeyID> result = new ArrayList<>();
        try{
        String sql ="select  * from AUTO_PERFORMANCENO ";
            result = EBankJdbcTemplate.query(sql, new RowMapper<generateKeyID>() {
                @Override
                public generateKeyID mapRow(ResultSet rs, int rowNum) throws SQLException {
                    generateKeyID tr = new generateKeyID();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    return tr;
                }
            });
        }catch (Exception e){e.printStackTrace();}
        return result;
    }
    //---update header status then car black
    @Override
    public int updateDetailsFooter(PerformanceReq performanceReq) {
            List<Performance> data = new ArrayList<>();
            try {
             String sql ="update TB_FOOTER_TRUCH set F_STATUS='Y'" +
//                        ",L_TRIES_KM_1=TO_NUMBER(L_TRIES_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+" " +
//                        ",L_TRIES_KM_2=TO_NUMBER(L_TRIES_KM_2, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",L_TRIES_KM_3=TO_NUMBER(L_TRIES_KM_3, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",L_TRIES_KM_4=TO_NUMBER(L_TRIES_KM_4, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",L_TRIES_KM_5=TO_NUMBER(L_TRIES_KM_5, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",L_TRIES_KM_6=TO_NUMBER(L_TRIES_KM_6, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",L_TRIES_KM_7=TO_NUMBER(L_TRIES_KM_7, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",L_TRIES_KM_8=TO_NUMBER(L_TRIES_KM_8, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_1=TO_NUMBER(R_TRIES_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_2=TO_NUMBER(R_TRIES_KM_2, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_3=TO_NUMBER(R_TRIES_KM_3, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_4=TO_NUMBER(R_TRIES_KM_4, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_5=TO_NUMBER(R_TRIES_KM_5, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_6=TO_NUMBER(R_TRIES_KM_6, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_7=TO_NUMBER(R_TRIES_KM_7, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                        ",R_TRIES_KM_8=TO_NUMBER(R_TRIES_KM_8, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
                        "where key_id  in (select a.key_id from  TB_FOOTER_TRUCH a INNER JOIN TB_DETAILS b ON a.KEY_ID = b.FOOTER_ID " +
                        "where LAHUD_POYLOD='"+performanceReq.getPerformanceBillNo()+"') ";
             return  EBankJdbcTemplate.update(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
    }
    //---update header status then car black
    @Override
    public int updateDetailsHeader(PerformanceReq performanceReq) {
        ///
        List<Performance> data = new ArrayList<>();
        try {
            String sql ="update TB_HEADER_TRUCK set h_status='Y'" +
//                    ",LL_TIRE_KM_1=TO_NUMBER(LL_TIRE_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+" " +
//                    ",LL_TIRE_KM_2=TO_NUMBER(LL_TIRE_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",LL_TIRE_KM_3=TO_NUMBER(LL_TIRE_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",LL_TIRE_KM_4=TO_NUMBER(LL_TIRE_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",LL_TIRE_KM_5=TO_NUMBER(LL_TIRE_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",LL_TIRE_KM_6=TO_NUMBER(LL_TIRE_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",R_TIRE_KM_1=TO_NUMBER(R_TIRE_KM_1, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",R_TIRE_KM_2=TO_NUMBER(R_TIRE_KM_2, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",R_TIRE_KM_3=TO_NUMBER(R_TIRE_KM_3, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",R_TIRE_KM_4=TO_NUMBER(R_TIRE_KM_4, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",R_TIRE_KM_5=TO_NUMBER(R_TIRE_KM_5, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
//                    ",R_TIRE_KM_6=TO_NUMBER(R_TIRE_KM_6, '99999999999999')-"+performanceReq.getCheck_KM()+"  "+
                    "where key_id  in (select a.key_id from  TB_HEADER_TRUCK a INNER JOIN TB_DETAILS b ON a.KEY_ID = b.HEADER_ID " +
                    "where LAHUD_POYLOD='"+performanceReq.getPerformanceBillNo()+"') ";
            log.info("update KM header:"+sql);
            return  EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();}
        return 0;
    }
    //update km =============================
    @Override
    public int updateDetailsHeaderKM(DetailsReq detailsReq) {
        log.info("===============================================<update km<=======================================");
        log.info("getHEADER_ID:"+detailsReq.getHEADER_ID());
        log.info("getLAIYATHANG:"+detailsReq.getLAIYATHANG());
        String laiyathangString = detailsReq.getLAIYATHANG();
        laiyathangString = laiyathangString.replace(",", "");
        int laiyathang = Integer.parseInt(laiyathangString);
        log.info("laiyathang:"+laiyathang);
        List<Performance> data = new ArrayList<>();
        try {
            String sql ="update TB_HEADER_TRUCK set h_status='Y' " +
                    ",KIM_KM= KIM_KM + "+laiyathang+" " +
                    ",LL_TIRE_KM_1= LL_TIRE_KM_1 - "+laiyathang+" " +
                    ",LL_TIRE_KM_2= LL_TIRE_KM_2 - "+laiyathang+" " +
                    ",LL_TIRE_KM_3= LL_TIRE_KM_3 - "+laiyathang+" " +
                    ",LL_TIRE_KM_4= LL_TIRE_KM_4 - "+laiyathang+" " +
                    ",LL_TIRE_KM_5= LL_TIRE_KM_5 - "+laiyathang+" " +
                    ",LL_TIRE_KM_6= LL_TIRE_KM_6 - "+laiyathang+" " +
                    ",LL_TIRE_KM_7= LL_TIRE_KM_7 - "+laiyathang+" " +
                    ",R_TIRE_KM_1=R_TIRE_KM_1-"+laiyathang+""+
                    ",R_TIRE_KM_2=R_TIRE_KM_2-"+laiyathang+""+
                    ",R_TIRE_KM_3=R_TIRE_KM_3-"+laiyathang+""+
                    ",R_TIRE_KM_4=R_TIRE_KM_4-"+laiyathang+""+
                    ",R_TIRE_KM_5=R_TIRE_KM_5-"+laiyathang+""+
                    ",R_TIRE_KM_6=R_TIRE_KM_6-"+laiyathang+""+
                    ",R_TIRE_KM_7=R_TIRE_KM_7-"+laiyathang+""+
                    "\n where key_id='"+detailsReq.getHEADER_ID()+"'";
            return  EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();}
        return 0;
    }


    //L_TRIES_KM_1    ---R_TRIES_KM_1
    @Override
    public int updateDetailsFooterKM(DetailsReq detailsReq) {
        log.info("===============================================<update footer<=======================================");
        log.info("getFOOTER_ID:"+detailsReq.getFOOTER_ID());
        log.info("getLAIYATHANG:"+detailsReq.getLAIYATHANG());
        String laiyathangString = detailsReq.getLAIYATHANG();
        laiyathangString = laiyathangString.replace(",", "");
        int laiyathang = Integer.parseInt(laiyathangString);
        log.info("laiyathang:"+laiyathang);
        List<VicicleFooter> data = new ArrayList<>();
        try {
            String sql ="update TB_FOOTER_TRUCH set F_STATUS='Y' " +
                    ",L_TRIES_KM_1= L_TRIES_KM_1 - "+laiyathang+" " +
                    ",L_TRIES_KM_2= L_TRIES_KM_2 - "+laiyathang+" " +
                    ",L_TRIES_KM_3= L_TRIES_KM_3 - "+laiyathang+" " +
                    ",L_TRIES_KM_4= L_TRIES_KM_4 - "+laiyathang+" " +
                    ",L_TRIES_KM_5= L_TRIES_KM_5 - "+laiyathang+" " +
                    ",L_TRIES_KM_6= L_TRIES_KM_6 - "+laiyathang+" " +
                    ",L_TRIES_KM_7= L_TRIES_KM_7 - "+laiyathang+" " +
                    ",R_TRIES_KM_1=R_TRIES_KM_1-"+laiyathang+""+
                    ",R_TRIES_KM_2=R_TRIES_KM_2-"+laiyathang+""+
                    ",R_TRIES_KM_3=R_TRIES_KM_3-"+laiyathang+""+
                    ",R_TRIES_KM_4=R_TRIES_KM_4-"+laiyathang+""+
                    ",R_TRIES_KM_5=R_TRIES_KM_5-"+laiyathang+""+
                    ",R_TRIES_KM_6=R_TRIES_KM_6-"+laiyathang+""+
                    ",R_TRIES_KM_7=R_TRIES_KM_7-"+laiyathang+""+
                    "\n where key_id='"+detailsReq.getFOOTER_ID()+"'";
            return  EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();}
        return 0;
    }
    @Override
    public List<performanceheaderGruop> gruopperformance(PerformanceReq performanceReq) {
        List<performanceheaderGruop> result = new ArrayList<>();
        try {
            String SQL = "select H_VICIVLE_NUMBER,STAFT_ID,STAFFNAME, STAFT_ID1, STAFFNAME1  from V_PRINT_BILL_PROFORMANCE where key_id = '"+performanceReq.getPerformanceBillNo()+"' ";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new PerformanceGroupHeaderMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<performaceGroupDetails> gruoperDetails(PerformanceReq performanceReq) {
        List<performaceGroupDetails> result = new ArrayList<>();
        try {
            String SQL = "select PERFORMANCEREDATE,\n" +
                    "LAHUD_POYLOD,\n" +
                    "PERFORMANCEDATE,\n" +
                    "IN_DATE,\n" +
                    "CUSTOMER_ID,\n" +
                    "CUSTOMER_NAME,\n" +
                    "PRO_NAME,\n" +
                    "PRO_TYPE,\n" +
                    "TO_CUSTOMER,\n" +
                    "PERFORMANCENO,\n" +
                    "TOTAL_PRICE,CURRENCY,STAFF_BIALIENG_CUR  from V_PRINT_BILL_PROFORMANCE where key_id = '"+performanceReq.getPerformanceBillNo()+"' ";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new PerformanceGroupDetailsMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    //--PerformanceGroupFeeMapper
    @Override
    public List<performanceGroupFee> gruoperFee(PerformanceReq performanceReq) {
        List<performanceGroupFee> result = new ArrayList<>();
        try {
            String SQL = "select * from V_PRINT_BILL_PROFORMANCE where key_id = '"+performanceReq.getPerformanceBillNo()+"' ";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new PerformanceGroupFeeMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<performance_FeePower> groupFeePower(PerformanceReq performanceReq) {
        List<performance_FeePower> result = new ArrayList<>();
        try {
            String SQL = "select * from V_PRINT_BILL_PROFORMANCE where key_id = '"+performanceReq.getPerformanceBillNo()+"' ";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new PerformanceFeePowerMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<performance_SmallHeaderGruop> groupSmallGroup(PerformanceReq performanceReq) {
        List<performance_SmallHeaderGruop> result = new ArrayList<>();
        try {
            String SQL = "select * from V_PRINT_BILL_PROFORMANCE where key_id = '"+performanceReq.getPerformanceBillNo()+"' ";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new PerformanceSmallMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<v_performance> ListV_performance() {
        List<v_performance> result = new ArrayList<>();
        try {
            String SQL = "select * from V_PERFORMANCE order by PERFORMANCEDATE desc";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new v_performanceMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    //---view popup details

    @Override
    public List<v_performance> v_popupPerformance() {
        List<v_performance> result = new ArrayList<>();
        try {
            String SQL = "select * from V_PERFORMANCE where STATUS ='N' order by PERFORMANCEDATE desc";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new v_performanceMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<v_performance> ListV_performancebyBillNo(PerformanceReq performanceReq) {
        List<v_performance> result = new ArrayList<>();
        try {
            String SQL = "select * from V_PERFORMANCE where  PERFORMANCEBILLNO like '%"+performanceReq.getPerformanceBillNo()+"%' or customer_NAME like '%"+performanceReq.getPerformanceBillNo()+"%' " +
                    " or PERFORMANCEDATE between '"+performanceReq.getPerformanceReDate()+"' and '"+performanceReq.getPerformanceDate()+"' order by PERFORMANCEDATE desc";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new v_performanceMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    //--update staus performance stafff
    @Override
    public int updateStaffNum01(PerformanceReq performanceReq) {
        List<Performance> data = new ArrayList<>();
        try
        {
            String sql ="update STAFF set OUT_STATUS='N' " +
                    "where key_id  in (select a.key_id from  STAFF a INNER JOIN TB_DETAILS b ON a.KEY_ID = b.STAFF_ID_NUM1 where LAHUD_POYLOD='"+performanceReq.getPerformanceBillNo()+"')";
            log.info("Update Header:"+sql);
            List<Object> paramList = new ArrayList<Object>();
            //paramList.add(performanceReq.get());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();}
        return 0;
    }
    @Override
    public int updateStaffNum02(PerformanceReq performanceReq) {
        List<Performance> data = new ArrayList<>();
        try
        {
            String sql ="update STAFF set OUT_STATUS='N' " +
                    "where key_id  in (select a.key_id from  STAFF a INNER JOIN TB_DETAILS b ON a.KEY_ID = b.STAFF_ID_NUM2 where LAHUD_POYLOD='"+performanceReq.getPerformanceBillNo()+"')";
            log.info("Update Header:"+sql);
            List<Object> paramList = new ArrayList<Object>();
            //paramList.add(performanceReq.get());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();}
        return 0;
    }
}
