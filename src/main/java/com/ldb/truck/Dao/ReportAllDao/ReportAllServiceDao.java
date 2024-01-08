package com.ldb.truck.Dao.ReportAllDao;

import com.ldb.truck.Dao.VicicleHeaderDao.VicicleHeaderServiceDao;
import com.ldb.truck.Model.Login.Report.ReportAll;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
@Repository
public class ReportAllServiceDao implements ReportAllDao{
    private static final Logger log = LogManager.getLogger(ReportAllServiceDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String sql="";
    @Override
    public List<ReportAll> ListAllReport(@RequestBody  ReportAllReq reportAllReq) {
        try {
           if(reportAllReq.getStartDate() == null){
                sql ="select * from V_RE_ALL";
            }else {
                sql = "select * from V_RE_ALL where  DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
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
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
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
                    tr.setCurrency(rs.getString("currency"));
                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<ReportAll> ListAllReport_product(@RequestBody  ReportAllReq reportAllReq) {
        try {
            if(reportAllReq.getStartDate() == null){
                sql ="select * from V_REPORT_CUSTOMER";
            }else {
                sql = "select * from V_REPORT_CUSTOMER where DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
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
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
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
                    tr.setCurrency(rs.getString("currency"));
                    tr.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //---report customer
    @Override
    public List<ReportAll> ListAllReportCustomer(@RequestBody  ReportAllReq reportAllReq) {
        try {
            if(reportAllReq.getStartDate() == null){
                sql ="select * from V_REPORT_PRODUCT";
            }else {
                sql = "select * from V_REPORT_PRODUCT where  DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
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
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
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
                    tr.setCurrency(rs.getString("currency"));
                    tr.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //-
    //--product
    @Override
    public List<ReportAll> ListAllReportProduct(@RequestBody  ReportAllReq reportAllReq) {
        try {
            if(reportAllReq.getStartDate() == null){
                sql ="select * from V_RE_ALL";
            }else {
                sql = "select * from V_RE_ALL where DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
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
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
                    tr.setSTAFF_BIALINEG_KANGSecond(rs.getString("STAFF_BIALINEG_KANGsecond"));
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
                    tr.setCurrency(rs.getString("currency"));
                    tr.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));


                    String numMun = rs.getString("SAINUMMUN").replaceAll(",","");
                    double conVertnumMun = Double.parseDouble(numMun);
                    log.info("show: "+conVertnumMun);
                    tr.setTotalNummun(conVertnumMun);
                    //----------------------total staff bialieng all
                    String numtotalStaffbialieng = rs.getString("STAFF_BIALIENG").replaceAll(",","");
                    double conVertStaffbialieng  = Double.parseDouble(numtotalStaffbialieng);
                    tr.setTotalBiaLieng(conVertStaffbialieng);
                    //---------------------kang jai staff 1 ----------
                    String numtotalStaffbialieng01 = rs.getString("STAFF_BIALINEG_KANGJAIY").replaceAll(",","");
                    double conVertStaffbialieng01  = Double.parseDouble(numtotalStaffbialieng01);
                    tr.setTodtalLaiyJaiyFrist(conVertStaffbialieng01);
                    //---------------------kang jai staff 2 ----------
                    String numtotalStaffbialieng02 = rs.getString("STAFF_BIALINEG_KANGsecond").replaceAll(",","");
                    double conVertStaffbialieng02  = Double.parseDouble(numtotalStaffbialieng02);
                    tr.setTodtalLaiyJaiySecond(conVertStaffbialieng02);

                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
