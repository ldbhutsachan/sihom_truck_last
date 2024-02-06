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

import java.sql.PreparedStatement;
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
            if(reportAllReq.getStartDate() == null  && reportAllReq.getStatus().equals(0)){
                sql ="select * from V_RE_ALL";
            }
            if(reportAllReq.getStartDate() == null && !reportAllReq.getStatus().equals(0)){
                sql ="select * from V_RE_ALL where D_STATUS ='"+reportAllReq.getStatus()+"' ";
            }
            else {
                sql = "select * from V_RE_ALL where D_STATUS ='"+reportAllReq.getStatus()+"' and  DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
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
            if(reportAllReq.getStartDate() == null  && reportAllReq.getStatus().equals("A")){
                sql ="select '1' as type,  \n" +
                        "                                                PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,  \n" +
                        "                                                H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,  \n" +
                        "                                                CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,  \n" +
                        "                                                PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,  \n" +
                        "                                                STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,  \n" +
                        "                                                FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,  \n" +
                        "                                                H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "                                                0 as total  \n" +
                        "                                                from V_RE_ALL  \n" ;
            }
            else if(reportAllReq.getStartDate() == null && !(reportAllReq.getStatus().equals("A"))){
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where d_status ='"+reportAllReq.getStatus()+"'\n" ; }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (reportAllReq.getStatus().equals("A")))
            {
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where D_STATUS IN ('N', 'Y') AND DETAILS_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" ;
            }
            else if((reportAllReq.getStartDate() == null) && (reportAllReq.getEndDate() == null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where D_STATUS = '"+reportAllReq.getStatus()+"' " ;
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where D_STATUS = '"+reportAllReq.getStatus()+"' and DETAILS_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" ;
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
                    tr.setStaff02_payAll(rs.getString("staff02_payAll"));
                    tr.setStaff02_beforepay(rs.getString("staff02_beforepay"));
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


                    tr.setType(rs.getString("type"));
//########################Fee Waste#########################################
//                    tr.setFeeOvertime1(rs.getString("feeOvertime1"));
//                    tr.setFeeJumPo2(rs.getString("feeJumPo2"));
//                    tr.setFeeTaxung4(rs.getString("feeTaxung4"));
//                    tr.setFeeTiew5(rs.getString("feeTiew5"));
//                    tr.setFeeLakSao(rs.getString("feeLakSao"));
//                    tr.setFeePassport(rs.getString("feePassport"));
//                    tr.setFeevacin(rs.getString("feevacin"));
//                    tr.setFeesing(rs.getString("feesing"));
//                    tr.setFeesaphan(rs.getString("feesaphan"));
//                    tr.setFeeyoktu(rs.getString("feeyoktu"));
//                    tr.setFeecontrainer(rs.getString("feecontrainer"));
                    tr.setRunningTotal(rs.getDouble("RunningTotal"));

                    //----------------------convert ,Fee Waste
                    String numRunningTotal = rs.getString("RunningTotal").replaceAll(",","");
                    double conVertRunningTotal  = Double.parseDouble(numRunningTotal);
                    tr.setRunningTotal(conVertRunningTotal);
                    // sum all real laiy jaiy t dai ma jark kha sin puerng + nam mun and bia lieng


                    // sum all real laiy jaiy t dai ma jark kha sin puerng + nam mun and bia lieng
//########################Fee Waste#########################################

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

                    //---------------------total price fuel ----------
//                    String numtotalPriceFuel = rs.getString("totalPriceFuel").replaceAll(",","");
//                    double conVerttotalPriceFuel  = Double.parseDouble(numtotalPriceFuel);
//                    tr.setTotalPriceFuel(conVerttotalPriceFuel);

                    //----------------------total price nammun
                    String numtotalPriceNammun = rs.getString("PRIECENUMNUN").replaceAll(",","");
                    double conVerttotalPriceNammun  = Double.parseDouble(numtotalPriceNammun);
                    tr.setTotalPriceNummun(conVerttotalPriceNammun);

//---------------------person 2 jaiy mod----------
                    String numStaff02_payAll = rs.getString("staff02_payAll").replaceAll(",","");
                    double conVertStaff02_payAll  = Double.parseDouble(numStaff02_payAll);
                    tr.setTotalstaff02_payAll(conVertStaff02_payAll);

//---------------------person 2 jaiy krn----------
                    String numStaff02_beforepay = rs.getString("staff02_payAll").replaceAll(",","");
                    double conVertStaff02_beforepay  = Double.parseDouble(numStaff02_beforepay);
                    tr.setTotalstaff02_beforepay(conVertStaff02_beforepay);

//                    calculate nummun price
//                    totalPriceFuel
                    double countTotalNummun = conVerttotalPriceNammun*conVertnumMun;
                    tr.setTotalPriceFuel(countTotalNummun);

                    //laiy jaiy all bialieng+numnun
                    double allLaiyJaiy = countTotalNummun + conVertStaff02_beforepay + conVertStaffbialieng02 + conVertStaffbialieng01 + conVertStaffbialieng + conVertRunningTotal;
                    tr.setAllLaiyJaiy(allLaiyJaiy);


                    //=================================cal layjai in and out====================
                    tr.setAllLaiyJaiyFrist(rs.getDouble("total"));
                    Double laijaiyOut = rs.getDouble("total");
                    double countTotalLaijaiy = laijaiyOut+allLaiyJaiy;
                    tr.setAllLaiyJaiyOut(countTotalLaijaiy);
                    //=================================cal layjai in and out====================

                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ReportAll> ListAllReportProductType02(@RequestBody  ReportAllReq reportAllReq) {
        try {
            if(reportAllReq.getStartDate() == null  && reportAllReq.getStatus().equals("A")){
                sql ="select '1' as type,  \n" +
                        "                                                PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,  \n" +
                        "                                                H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,  \n" +
                        "                                                CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,  \n" +
                        "                                                PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,  \n" +
                        "                                                STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,  \n" +
                        "                                                FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,  \n" +
                        "                                                H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "                                                0 as total  \n" +
                        "                                                from V_RE_ALL  \n" +
                        "                                                union  \n" +
                        "select '2' as type,  \n" +
                        "                                                0 as PRICE,0 as TOTAL_PRICE,0 as PRIECENUMNUN,0 as STAFT_ID,0 as STAFT_NAME,0 as STAFT_SURNAME,0 as STAFT_ID1,0 as STAFT_NAME1,  \n" +
                        "                                                0 as STAFT_SURNAME1,0 as H_VICIVLE_NUMBER,0 as H_VICIVLE_BRANCHTYPE,0 as F_BRANCH,0 as F_CARD_NO,0 as F_CAR_TYPE,  \n" +
                        "                                                0 as PROVINCE,0 as DETAIL,0 as PROVINCE1,0 as DETAIL1,0 as CUSTOMER_ID,0 as CUSTOMER_NAME,0 as PRODUCT_ID,0 as PRO_NAME,  \n" +
                        "                                                0 as PRO_TYPE,0 as PRODUCT_AMOUNT,0 as PRODUCT_SIZE,0 as PRODUCT_DETAILS,0 as PRODUCT_FROM,0 as PRODUCT_TO,  \n" +
                        "                                                0 as PLACE_PD_FROM,0 as PLACE_PD_TO,0 as STAFF_ID_NUM1,0 as STAFF_ID_NUM2,0 as STAFF_BIALIENG,0 as STAFF_BIALIENG_FRIST,  \n" +
                        "                                                0 as STAFF_BIALINEG_KANGJAIY,0 as STAFF_BIALINEG_KANGsecond,0 as staff02_payAll,0 as staff02_beforepay,0 as staff02_notpay,  \n" +
                        "                                                0 as HEADER_ID,0 as FOOTER_ID,0 as OUT_DATE,0 as IN_DATE,0 as LAIYATHANG,0 as SAINUMMUN,0 as NUMNUKLOD,0 as KONGNARLOD,  \n" +
                        "                                                0 as KHG_MUE_TIDLOD,0 as KIM_KILO,0 as LAHUD_POYLOD,0 as H_LEK_NUMMUNKHG,EXPDATE as DETAILS_DATE,0 as D_STATUS,0 as CURRENCY,  \n" +
                        "                                                0 as STAFF_BIALIENG_CUR,0 as totalDay, 0 as RunningTotal, \n" +
                        "                                                sum (TOTAL) as total from V_EXPENSES where STATUS in('PAY')  ";
            }
            else if(reportAllReq.getStartDate() == null && !(reportAllReq.getStatus().equals("A"))){
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where d_status ='"+reportAllReq.getStatus()+"'\n" +
                        "union\n" +
                        "select '2' as type,\n" +
                        "0 PRICE,0 as TOTAL_PRICE,0 as PRIECENUMNUN,0 as STAFT_ID,0 as STAFT_NAME,0 as STAFT_SURNAME,0 as STAFT_ID1,0 as STAFT_NAME1,\n" +
                        "0 as STAFT_SURNAME1,0 as H_VICIVLE_NUMBER,0 as H_VICIVLE_BRANCHTYPE,0 as F_BRANCH,0 as F_CARD_NO,0 as F_CAR_TYPE,\n" +
                        "0 as PROVINCE,0 as DETAIL,0 as PROVINCE1,0 as DETAIL1,0 as CUSTOMER_ID,0 as CUSTOMER_NAME,0 as PRODUCT_ID,0 as PRO_NAME,\n" +
                        "0 as PRO_TYPE,0 as PRODUCT_AMOUNT,0 as PRODUCT_SIZE,0 as PRODUCT_DETAILS,0 as PRODUCT_FROM,0 as PRODUCT_TO,\n" +
                        "0 as PLACE_PD_FROM,0 as PLACE_PD_TO,0 as STAFF_ID_NUM1,0 as STAFF_ID_NUM2,0 as STAFF_BIALIENG,0 as STAFF_BIALIENG_FRIST,\n" +
                        "0 as STAFF_BIALINEG_KANGJAIY,0 as STAFF_BIALINEG_KANGsecond,0 as staff02_payAll,0 as staff02_beforepay,0 as staff02_notpay,\n" +
                        "0 as HEADER_ID,0 as FOOTER_ID,0 as OUT_DATE,0 as IN_DATE,0 as LAIYATHANG,0 as SAINUMMUN,0 as NUMNUKLOD,0 as KONGNARLOD,\n" +
                        "0 as KHG_MUE_TIDLOD,0 as KIM_KILO,0 as LAHUD_POYLOD,0 as H_LEK_NUMMUNKHG,EXPDATE as DETAILS_DATE,0 as D_STATUS,0 as CURRENCY,\n" +
                        "0 as STAFF_BIALIENG_CUR,0 as totalDay,0 as RunningTotal,\n" +
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in('PAY')";
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (reportAllReq.getStatus().equals("A")))
            {
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where D_STATUS IN ('N', 'Y') AND DETAILS_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" +
                        "union\n" +
                        "select '2' as type,\n" +
                        "0 PRICE,0 as TOTAL_PRICE,0 as PRIECENUMNUN,0 as STAFT_ID,0 as STAFT_NAME,0 as STAFT_SURNAME,0 as STAFT_ID1,0 as STAFT_NAME1,\n" +
                        "0 as STAFT_SURNAME1,0 as H_VICIVLE_NUMBER,0 as H_VICIVLE_BRANCHTYPE,0 as F_BRANCH,0 as F_CARD_NO,0 as F_CAR_TYPE,\n" +
                        "0 as PROVINCE,0 as DETAIL,0 as PROVINCE1,0 as DETAIL1,0 as CUSTOMER_ID,0 as CUSTOMER_NAME,0 as PRODUCT_ID,0 as PRO_NAME,\n" +
                        "0 as PRO_TYPE,0 as PRODUCT_AMOUNT,0 as PRODUCT_SIZE,0 as PRODUCT_DETAILS,0 as PRODUCT_FROM,0 as PRODUCT_TO,\n" +
                        "0 as PLACE_PD_FROM,0 as PLACE_PD_TO,0 as STAFF_ID_NUM1,0 as STAFF_ID_NUM2,0 as STAFF_BIALIENG,0 as STAFF_BIALIENG_FRIST,\n" +
                        "0 as STAFF_BIALINEG_KANGJAIY,0 as STAFF_BIALINEG_KANGsecond,0 as staff02_payAll,0 as staff02_beforepay,0 as staff02_notpay,\n" +
                        "0 as HEADER_ID,0 as FOOTER_ID,0 as OUT_DATE,0 as IN_DATE,0 as LAIYATHANG,0 as SAINUMMUN,0 as NUMNUKLOD,0 as KONGNARLOD,\n" +
                        "0 as KHG_MUE_TIDLOD,0 as KIM_KILO,0 as LAHUD_POYLOD,0 as H_LEK_NUMMUNKHG,EXPDATE as DETAILS_DATE,0 as D_STATUS,0 as CURRENCY,\n" +
                        "0 as STAFF_BIALIENG_CUR,0 as totalDay,0 as RunningTotal,\n" +
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in ('PAY')";

                //sql ="SELECT * FROM V_RE_ALL WHERE D_STATUS IN ('N', 'Y') AND DETAILS_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
            else if((reportAllReq.getStartDate() == null) && (reportAllReq.getEndDate() == null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where D_STATUS = '"+reportAllReq.getStatus()+"' " +
                        "union\n" +
                        "select '2' as type,\n" +
                        "0 PRICE,0 as TOTAL_PRICE,0 as PRIECENUMNUN,0 as STAFT_ID,0 as STAFT_NAME,0 as STAFT_SURNAME,0 as STAFT_ID1,0 as STAFT_NAME1,\n" +
                        "0 as STAFT_SURNAME1,0 as H_VICIVLE_NUMBER,0 as H_VICIVLE_BRANCHTYPE,0 as F_BRANCH,0 as F_CARD_NO,0 as F_CAR_TYPE,\n" +
                        "0 as PROVINCE,0 as DETAIL,0 as PROVINCE1,0 as DETAIL1,0 as CUSTOMER_ID,0 as CUSTOMER_NAME,0 as PRODUCT_ID,0 as PRO_NAME,\n" +
                        "0 as PRO_TYPE,0 as PRODUCT_AMOUNT,0 as PRODUCT_SIZE,0 as PRODUCT_DETAILS,0 as PRODUCT_FROM,0 as PRODUCT_TO,\n" +
                        "0 as PLACE_PD_FROM,0 as PLACE_PD_TO,0 as STAFF_ID_NUM1,0 as STAFF_ID_NUM2,0 as STAFF_BIALIENG,0 as STAFF_BIALIENG_FRIST,\n" +
                        "0 as STAFF_BIALINEG_KANGJAIY,0 as STAFF_BIALINEG_KANGsecond,0 as staff02_payAll,0 as staff02_beforepay,0 as staff02_notpay,\n" +
                        "0 as HEADER_ID,0 as FOOTER_ID,0 as OUT_DATE,0 as IN_DATE,0 as LAIYATHANG,0 as SAINUMMUN,0 as NUMNUKLOD,0 as KONGNARLOD,\n" +
                        "0 as KHG_MUE_TIDLOD,0 as KIM_KILO,0 as LAHUD_POYLOD,0 as H_LEK_NUMMUNKHG,EXPDATE as DETAILS_DATE,0 as D_STATUS,0 as CURRENCY,\n" +
                        "0 as STAFF_BIALIENG_CUR,0 as totalDay,0 as RunningTotal,\n" +
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in ('PAY')";
               // sql = "select * from V_RE_ALL where D_STATUS = '"+reportAllReq.getStatus()+"' and  DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type,\n" +
                        "PRICE,TOTAL_PRICE,PRIECENUMNUN,STAFT_ID,STAFT_NAME,STAFT_SURNAME,STAFT_ID1,STAFT_NAME1,STAFT_SURNAME1,\n" +
                        "H_VICIVLE_NUMBER,H_VICIVLE_BRANCHTYPE,F_BRANCH,F_CARD_NO,F_CAR_TYPE,PROVINCE,DETAIL,PROVINCE1,DETAIL1,\n" +
                        "CUSTOMER_ID,CUSTOMER_NAME,PRODUCT_ID,PRO_NAME,PRO_TYPE,PRODUCT_AMOUNT,PRODUCT_SIZE,PRODUCT_DETAILS,\n" +
                        "PRODUCT_FROM,PRODUCT_TO,PLACE_PD_FROM,PLACE_PD_TO,STAFF_ID_NUM1,STAFF_ID_NUM2,STAFF_BIALIENG,STAFF_BIALIENG_FRIST,\n" +
                        "STAFF_BIALINEG_KANGJAIY,STAFF_BIALINEG_KANGsecond,staff02_payAll,staff02_beforepay,staff02_notpay,HEADER_ID,\n" +
                        "FOOTER_ID,OUT_DATE,IN_DATE,LAIYATHANG,SAINUMMUN,NUMNUKLOD,KONGNARLOD,KHG_MUE_TIDLOD,KIM_KILO,LAHUD_POYLOD,\n" +
                        "H_LEK_NUMMUNKHG,DETAILS_DATE,D_STATUS,CURRENCY,STAFF_BIALIENG_CUR,totalDay,( feeOvertime1 +  \n" +
                        "                                                                        feeJumPo2 + feeTaxung4 + feeTiew5 + feeLakSao + feePassport + feevacin + feesing + feesaphan + feeyoktu  + \n" +
                        "                                                                        feecontrainer + feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL where D_STATUS = '"+reportAllReq.getStatus()+"' and DETAILS_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" +
                        "union\n" +
                        "select '2' as type,\n" +
                        "0 PRICE,0 as TOTAL_PRICE,0 as PRIECENUMNUN,0 as STAFT_ID,0 as STAFT_NAME,0 as STAFT_SURNAME,0 as STAFT_ID1,0 as STAFT_NAME1,\n" +
                        "0 as STAFT_SURNAME1,0 as H_VICIVLE_NUMBER,0 as H_VICIVLE_BRANCHTYPE,0 as F_BRANCH,0 as F_CARD_NO,0 as F_CAR_TYPE,\n" +
                        "0 as PROVINCE,0 as DETAIL,0 as PROVINCE1,0 as DETAIL1,0 as CUSTOMER_ID,0 as CUSTOMER_NAME,0 as PRODUCT_ID,0 as PRO_NAME,\n" +
                        "0 as PRO_TYPE,0 as PRODUCT_AMOUNT,0 as PRODUCT_SIZE,0 as PRODUCT_DETAILS,0 as PRODUCT_FROM,0 as PRODUCT_TO,\n" +
                        "0 as PLACE_PD_FROM,0 as PLACE_PD_TO,0 as STAFF_ID_NUM1,0 as STAFF_ID_NUM2,0 as STAFF_BIALIENG,0 as STAFF_BIALIENG_FRIST,\n" +
                        "0 as STAFF_BIALINEG_KANGJAIY,0 as STAFF_BIALINEG_KANGsecond,0 as staff02_payAll,0 as staff02_beforepay,0 as staff02_notpay,\n" +
                        "0 as HEADER_ID,0 as FOOTER_ID,0 as OUT_DATE,0 as IN_DATE,0 as LAIYATHANG,0 as SAINUMMUN,0 as NUMNUKLOD,0 as KONGNARLOD,\n" +
                        "0 as KHG_MUE_TIDLOD,0 as KIM_KILO,0 as LAHUD_POYLOD,0 as H_LEK_NUMMUNKHG,EXPDATE as DETAILS_DATE,0 as D_STATUS,0 as CURRENCY,\n" +
                        "0 as STAFF_BIALIENG_CUR,0 as totalDay,0 as RunningTotal,\n" +
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in ('PAY')";
                // sql = "select * from V_RE_ALL where D_STATUS = '"+reportAllReq.getStatus()+"' and  DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
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
                    tr.setStaff02_payAll(rs.getString("staff02_payAll"));
                    tr.setStaff02_beforepay(rs.getString("staff02_beforepay"));
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


                    tr.setType(rs.getString("type"));
//########################Fee Waste#########################################
                    tr.setRunningTotal(rs.getDouble("RunningTotal"));

                    String numRunningTotal = rs.getString("RunningTotal").replaceAll(",","");
                    double conVertRunningTotal  = Double.parseDouble(numRunningTotal);
                    tr.setRunningTotal(conVertRunningTotal);
//########################Fee Waste#########################################

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

                    //---------------------total price fuel ----------
//                    String numtotalPriceFuel = rs.getString("totalPriceFuel").replaceAll(",","");
//                    double conVerttotalPriceFuel  = Double.parseDouble(numtotalPriceFuel);
//                    tr.setTotalPriceFuel(conVerttotalPriceFuel);

                    //----------------------total price nammun
                    String numtotalPriceNammun = rs.getString("PRIECENUMNUN").replaceAll(",","");
                    double conVerttotalPriceNammun  = Double.parseDouble(numtotalPriceNammun);
                    tr.setTotalPriceNummun(conVerttotalPriceNammun);

//---------------------person 2 jaiy mod----------
                    String numStaff02_payAll = rs.getString("staff02_payAll").replaceAll(",","");
                    double conVertStaff02_payAll  = Double.parseDouble(numStaff02_payAll);
                    tr.setTotalstaff02_payAll(conVertStaff02_payAll);

//---------------------person 2 jaiy krn----------
                    String numStaff02_beforepay = rs.getString("staff02_payAll").replaceAll(",","");
                    double conVertStaff02_beforepay  = Double.parseDouble(numStaff02_beforepay);
                    tr.setTotalstaff02_beforepay(conVertStaff02_beforepay);

//                    calculate nummun price
//                    totalPriceFuel
                    double countTotalNummun = conVerttotalPriceNammun*conVertnumMun;
                    tr.setTotalPriceFuel(countTotalNummun);

            //laiy jaiy all bialieng+numnun
                    double allLaiyJaiy = countTotalNummun + conVertStaff02_beforepay + conVertStaffbialieng02 + conVertStaffbialieng01 + conVertStaffbialieng + conVertRunningTotal;
                    tr.setAllLaiyJaiy(allLaiyJaiy);


                    //=================================cal layjai in and out====================
                    tr.setAllLaiyJaiyFrist(rs.getDouble("total"));
                    Double laijaiyOut = rs.getDouble("total");
                    double countTotalLaijaiy = laijaiyOut+allLaiyJaiy;
                    tr.setAllLaiyJaiyOut(countTotalLaijaiy);
                    //=================================cal layjai in and out====================

                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
