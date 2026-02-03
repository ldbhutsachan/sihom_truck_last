package com.ldb.truck.Dao.ReportAllDao;

import com.ldb.truck.Entity.Stock.StockRequest;
import com.ldb.truck.Model.Login.ForShowTotalOil.ForShowTotalOilPaid;
import com.ldb.truck.Model.Login.Report.ReportAll;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportFuel;
import com.ldb.truck.Model.ReportAllStock.ReportAllStock;

import com.ldb.truck.Model.ReportAllStock.ReportAllStockInOut;
import com.ldb.truck.Model.ReportItemInOutModel.ReportItemInOutModelReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
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
                sql ="select * from V_REPORT_PRODUCT a INNER JOIN LOGIN b ON a.userId=b.KEY_ID WHERE b.BRANCH='"+reportAllReq.getBranch()+"'";
            }else {
                sql = "select * from V_REPORT_PRODUCT a INNER JOIN LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
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
                sql ="select '1' as type,   a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal ,\n" +
                        "0 as total  \n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"'  \n" ;
                log.info("sql:"+sql);
            }
            else if(reportAllReq.getStartDate() == null && !(reportAllReq.getStatus().equals("A"))){
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.d_status ='"+reportAllReq.getStatus()+"'\n" ;
                log.info("sql:"+sql);
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (reportAllReq.getStatus().equals("A")))
            {
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.D_STATUS IN ('N', 'Y') AND a.OUT_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" ;
            }
            else if((reportAllReq.getStartDate() == null) && (reportAllReq.getEndDate() == null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.D_STATUS = '"+reportAllReq.getStatus()+"' " ;
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.D_STATUS = '"+reportAllReq.getStatus()+"' and a.OUT_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" ;
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
    //start  Report Fuel
    public List<ReportFuel> ReportFuealDao(@RequestBody  ReportAllReq reportAllReq) {
        try {
            if(reportAllReq.getStartDate() == null  && reportAllReq.getStatus_fuel() == null && reportAllReq.getFuelStationId() == null){
                sql ="select * from V_RE_ALL a inner join LOGIN b ON (a.userId=b.KEY_ID) JOIN FUEL_STATION f ON (a.FUEL_STATION_ID = f.FUEL_STATION_ID) where b.BRANCH='"+reportAllReq.getBranch()+"'  \n" ;
                log.info("SQL.1:"+sql);
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getStatus_fuel() != null) && reportAllReq.getFuelStationId() == null) {
                log.info("SQL.2:"+sql);
                sql ="select * from V_RE_ALL a inner join LOGIN b ON (a.userId=b.KEY_ID) JOIN FUEL_STATION f ON (a.FUEL_STATION_ID = f.FUEL_STATION_ID) where b.BRANCH='"+reportAllReq.getBranch()+"'  AND a.FUEL_STATUS = '"+reportAllReq.getStatus_fuel()+"' and a.OUT_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" ;
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (reportAllReq.getStatus_fuel() == null)) {
                log.info("SQL.3:"+sql);
                sql ="select * from V_RE_ALL a inner join LOGIN b ON (a.userId=b.KEY_ID) JOIN FUEL_STATION f ON (a.FUEL_STATION_ID = f.FUEL_STATION_ID) where b.BRANCH='"+reportAllReq.getBranch()+"'  AND a.FUEL_STATUS in ('P','UP') and a.OUT_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" ;
            }
            else if((reportAllReq.getStartDate() == null) && (reportAllReq.getEndDate() == null) && (reportAllReq.getStatus_fuel() != null)) {
                log.info("SQL.5:"+sql);
                sql ="select * from V_RE_ALL a inner join LOGIN b ON (a.userId=b.KEY_ID) JOIN FUEL_STATION f ON (a.FUEL_STATION_ID = f.FUEL_STATION_ID) where b.BRANCH='"+reportAllReq.getBranch()+"'  AND a.FUEL_STATUS = '"+reportAllReq.getStatus_fuel()+"'" ;
            }
            else if((reportAllReq.getFuelStationId() != null) && (reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (reportAllReq.getStatus_fuel() != null))
            {
                log.info("SQL.6:"+sql);
                sql ="select * from V_RE_ALL a join LOGIN b ON (a.userId=b.KEY_ID) JOIN FUEL_STATION f ON (a.FUEL_STATION_ID = f.FUEL_STATION_ID) where b.BRANCH='"+reportAllReq.getBranch()+"'  AND f.FUEL_STATION_ID='"+reportAllReq.getFuelStationId()+"' AND a.FUEL_STATUS = '"+reportAllReq.getStatus_fuel()+"'  and a.OUT_DATE BETWEEN '" +reportAllReq.getStartDate()+"' and '" +reportAllReq.getEndDate() +"'" ;
            }
            else if(reportAllReq.getFuelStationId() != null && reportAllReq.getStartDate() == null && reportAllReq.getEndDate() == null && reportAllReq.getStatus_fuel() == null)
            {
                log.info("SQL.7:"+sql);
                sql ="select * from V_RE_ALL a join LOGIN b ON (a.userId=b.KEY_ID) JOIN FUEL_STATION f ON (a.FUEL_STATION_ID = f.FUEL_STATION_ID) where b.BRANCH='"+reportAllReq.getBranch()+"'  AND f.FUEL_STATION_ID='"+reportAllReq.getFuelStationId()+"' " ;
            }
            return EBankJdbcTemplate.query(sql, new RowMapper<ReportFuel>() {
                @Override
                public ReportFuel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportFuel tr =new ReportFuel();

                    tr.setKeyIds(rs.getString("KEY_ID2"));
                    tr.setDel(rs.getString("LAHUD_POYLOD"));
                    tr.setPlateTruckHead(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setDateFillFuel(rs.getString("OUT_DATE"));
                    tr.setLidFuel(rs.getDouble("SAINUMMUN"));
                    tr.setPumpName(rs.getString("FUEL_STATION_NAME"));
                    tr.setVillage(rs.getString("VILLAGE"));
                    tr.setDistrict(rs.getString("DISTRICT"));
                    tr.setProvince(rs.getString("PROVICNE"));
                    tr.setPumpName(rs.getString("FUEL_STATION_NAME"));
                    tr.setVillage(rs.getString("VILLAGE"));
                    tr.setDistrict(rs.getString("DISTRICT"));
                    tr.setProvince(rs.getString("PROVICNE"));
                    tr.setPrizPerLid(rs.getDouble("PRIECENUMNUN"));
                    tr.setStatus_fuel(rs.getString("FUEL_STATUS"));
                    String lidFuel = rs.getString("SAINUMMUN").replaceAll(",","");

                    double conVertnumMun = Double.parseDouble(lidFuel);
                    tr.setTotalLidFuel(conVertnumMun);
                    //----------------------total price nammun
                    String numtotalPriceNammun = rs.getString("PRIECENUMNUN").replaceAll(",","");
                    double conVerttotalPriceNammun  = Double.parseDouble(numtotalPriceNammun);
                    tr.setTotalPrizeFuel(conVerttotalPriceNammun);



                    double countTotalNummun = conVerttotalPriceNammun*conVertnumMun;
                    tr.setTotalPrizeFuelAll(countTotalNummun);
                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    show total price oil
public List<ForShowTotalOilPaid> ShowOilPaid(@RequestBody  ReportAllReq reportAllReq) {
    try {
        if (reportAllReq.getStartDate() == null) {
            sql = "select * from SPEND_OILS a inner join LOGIN b ON (a.userId=b.KEY_ID) where b.BRANCH='" + reportAllReq.getBranch() + "'";
        }
        else {
            sql = "select * from SPEND_OILS a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.DATECREATE BETWEEN '" + reportAllReq.getStartDate() +"' AND '" + reportAllReq.getEndDate()+"'";
        }
        return EBankJdbcTemplate.query(sql, new RowMapper<ForShowTotalOilPaid>() {
            @Override
            public ForShowTotalOilPaid mapRow(ResultSet rs, int rowNum) throws SQLException {
                ForShowTotalOilPaid tr =new ForShowTotalOilPaid();
                tr.setTotalOilPaid(rs.getDouble("TOTAL_PRICE"));
                return tr;
            }
        });

    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
    //end  Report Fuel

    public List<ReportAll> ListAllReportProductType02(@RequestBody  ReportAllReq reportAllReq) {
        try {
            if(reportAllReq.getStartDate() == null  && reportAllReq.getStatus().equals("A")){
                sql ="select '1' as type,   a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal ,\n" +
                        "                                                0 as total  \n" +
                        "                                                from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"'  \n" +
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
                        "                                                sum (TOTAL) as total from V_EXPENSES where STATUS in('PAY') AND BRANCH='"+reportAllReq.getBranch()+"' ";
            }
            else if(reportAllReq.getStartDate() == null && !(reportAllReq.getStatus().equals("A"))){
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.d_status ='"+reportAllReq.getStatus()+"'\n" +
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
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in('PAY') AND BRANCH='"+reportAllReq.getBranch()+"'";
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (reportAllReq.getStatus().equals("A")))
            {
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+reportAllReq.getBranch()+"' AND a.D_STATUS IN ('N', 'Y') AND a.OUT_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" +
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
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in ('PAY') AND BRANCH='"+reportAllReq.getBranch()+"' and EXPDATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'";

                //sql ="SELECT * FROM V_RE_ALL WHERE D_STATUS IN ('N', 'Y') AND DETAILS_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
            else if((reportAllReq.getStartDate() == null) && (reportAllReq.getEndDate() == null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where a.D_STATUS = '"+reportAllReq.getStatus()+"' AND b.BRANCH='"+reportAllReq.getBranch()+"'" +
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
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in ('PAY') AND BRANCH='"+reportAllReq.getBranch()+"' ";
               // sql = "select * from V_RE_ALL where D_STATUS = '"+reportAllReq.getStatus()+"' and  DETAILS_DATE between '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "' ";
            }
            else if((reportAllReq.getStartDate() != null) && (reportAllReq.getEndDate() != null) && (!reportAllReq.getStatus().equals("A"))) {
                sql ="select '1' as type, a.PRICE,a.TOTAL_PRICE,a.PRIECENUMNUN,a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,a.STAFT_ID1,a.STAFT_NAME1,a.STAFT_SURNAME1,\n" +
                        " a.H_VICIVLE_NUMBER,a.H_VICIVLE_BRANCHTYPE,a.F_BRANCH,a.F_CARD_NO,a.F_CAR_TYPE,a.PROVINCE,a.DETAIL,a.PROVINCE1,a.DETAIL1,a.CUSTOMER_ID,a.CUSTOMER_NAME,a.PRODUCT_ID,a.PRO_NAME,a.PRO_TYPE,a.PRODUCT_AMOUNT,a.PRODUCT_SIZE,a.PRODUCT_DETAILS,a.PRODUCT_FROM,a.PRODUCT_TO,a.PLACE_PD_FROM,a.PLACE_PD_TO,a.STAFF_ID_NUM1,a.STAFF_ID_NUM2,a.STAFF_BIALIENG,a.STAFF_BIALIENG_FRIST,\n" +
                        " a.STAFF_BIALINEG_KANGJAIY,a.STAFF_BIALINEG_KANGsecond,a.staff02_payAll,a.staff02_beforepay,a.staff02_notpay,a.HEADER_ID,a.FOOTER_ID,a.OUT_DATE,a.IN_DATE,a.LAIYATHANG,a.SAINUMMUN,a.NUMNUKLOD,a.KONGNARLOD,a.KHG_MUE_TIDLOD,a.KIM_KILO,a.LAHUD_POYLOD,a.H_LEK_NUMMUNKHG,a.DETAILS_DATE,a.D_STATUS,a.CURRENCY,a.STAFF_BIALIENG_CUR,a.totalDay,\n" +
                        "(a.feeOvertime1 + a.feeJumPo2 + a.feeTaxung4 + a.feeTiew5 + a.feeLakSao + a.feePassport + a.feePolish3 + a.feevacin + a.feesing + a.feesaphan + a.feeyoktu  + a.feecontrainer + a.feepayang + a.add_feeOvertime1 + a.add_feeJumPo2 + a.add_feePolish3 + a.add_feeTaxung4 + a.add_feeTiew5 + a.add_feesing + a.add_feesaphan + a.add_feeyoktu + a.add_feecontrainer + a.add_feepayang)  AS RunningTotal ,\n" +
                        "0 as total\n" +
                        "from V_RE_ALL a inner join LOGIN b ON a.userId=b.KEY_ID where BRANCH='"+reportAllReq.getBranch()+"' AND a.D_STATUS = '"+reportAllReq.getStatus()+"' and a.OUT_DATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'" +
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
                        "sum (TOTAL) as total from V_EXPENSES where STATUS in ('PAY') AND BRANCH='"+reportAllReq.getBranch()+"'  and EXPDATE BETWEEN '" + reportAllReq.getStartDate() + "' and '" + reportAllReq.getEndDate() + "'";
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

    //*****report stock
    public List<ReportAllStockInOut> getReportDetailDailyStock(ReportItemInOutModelReq stockRequest,
                                                               String role,String borNos,String borNoss){
        String startDate = stockRequest.getStartDate();
        String endDate = stockRequest.getEndDate();
        String itemId= stockRequest.getItemId();
        String houseNo =stockRequest.getHouseNo();
        String borNo= stockRequest.getBorNo();
        String conItem = "";
        String conItemBoNo = "";
        if (!"all".equals(houseNo)) {
            conItem += "\n and houseNo = '" + houseNo + "'";
        }
        if(!"all".equals(itemId)){
            conItem= "\n and item_id ='"+itemId+"'";
        }else {
            conItem= "";
        }
        if("PADMIN".equals(role)){
            if(!"all".equals(borNo)){
                conItemBoNo ="\n and borNo='"+borNo+"'";
            }else {
                conItemBoNo =" ";
            }
            if (!"all".equals(houseNo)) {
                conItem += "\n and houseNo = '" + houseNo + "'";
            }
        }else {
            conItemBoNo ="\n and borkey='"+borNoss+"'";
            if (!"all".equals(houseNo)) {
                conItem += "\n and houseNo = '" + houseNo + "'";
            }
        }

//        String startDateCon = "\n and dateIn >= '"+startDate+"' and dateIn <= '"+endDate+"' or dateOut >= '"+startDate+"' and dateOut <= '"+endDate+"' " ;
        String startDateCon = "\n and ((dateIn >= '"+startDate+"' and dateIn <= '"+endDate+"') " +
                "or (dateOut >= '"+startDate+"' and dateOut <= '"+endDate+"'))";

        try {
            StringBuilder sb = new StringBuilder();
//            sb.append("SELECT * FROM v_sum_order_item_sum WHERE stockOut > 0 AND stockOut IS NOT NULL AND 1=1 \n");
            sb.append("SELECT * FROM v_sum_order_item_sum WHERE 1=1\n");
            sb.append(startDateCon);
            sb.append(conItem);
            sb.append(conItemBoNo);
            sb.append(" ORDER BY houseNo DESC");

            String query = sb.toString();
            log.info("sql:"+query);
            return EBankJdbcTemplate.query(query, new RowMapper<ReportAllStockInOut>() {
                @Override
                public ReportAllStockInOut mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportAllStockInOut tr = new ReportAllStockInOut();
                    tr.setItemId(rs.getString("item_id"));
                    tr.setImage(rs.getString("image"));
                    tr.setItemName(rs.getString("item_name"));
                    tr.setUnit(rs.getString("unit"));
                    tr.setSize(rs.getString("size"));
                    tr.setRaisedAmt(rs.getInt("closingBalance"));
                    tr.setInAmt(rs.getInt("stockIn"));
                    tr.setOutAmt(rs.getInt("stockOut"));
                    tr.setClosingAmt(rs.getInt("opening_balance"));
                    tr.setDateIn(rs.getString("dateIn"));
                    tr.setDateOut(rs.getString("dateOut"));
                    tr.setBorkey(rs.getString("borkey"));
                    tr.setBorname(rs.getString("b_name"));
                    tr.setHouseNo(rs.getString("houseNo"));
                    tr.setHouseName(rs.getString("houseName"));
                    tr.setPrice(rs.getDouble("price"));
                    tr.setCurrency(rs.getString("currency"));

                   return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ReportAllStock> getTxnStock(ReportItemInOutModelReq stockRequest){
        String startDate = stockRequest.getStartDate();
        String endDate = stockRequest.getEndDate();
        String itemId = stockRequest.getItemId();
        log.info("start itemId:"+itemId);
        String group = "\n group by a.item_id,a.image,item_name,\n" +
                "d.req_id,d.req_name,f.key_id,f.b_name,f.location,to_char(b.savedate,'yyyy-mm-dd'),to_char(c.savedate,'yyyy-mm-dd') ";
        String startDateCon = "\nand to_char(c.savedate,'yyyy-mm-dd') >= '"+startDate+"'";
        String endDateCon = "\nand to_char(c.savedate,'yyyy-mm-dd') <= '"+endDate+"'";
        String tableCon = "\n from  item_inventory a left join sotck_item_details b on a.item_id=b.item_id\n" +
                "left join request_item_details c on a.item_id=c.item_id \n" +
                "left join tb_bors f on f.type=c.type\n" +
                "left join request_item_type d on f.type= d.req_id ";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select  \n" +
                    "a.item_id,\n" +
                    "a.item_name,\n" +
                    "a.image,\n" +
                    "d.req_id type,\n" +
                    "d.req_name type_name,\n" +
                    "f.key_id bor_no,\n" +
                    "f.b_name bor_name,\n" +
                    "f.location blocation,\n" +
                    "to_char(b.savedate,'yyyy-mm-dd') txndateIn,\n" +
                    "to_char(c.savedate,'yyyy-mm-dd') txndateOut,\n" +
                    "   SUM(a.unit) AS amt,\n" +
                    "    FORMAT(SUM(a.price), '###,###,###') AS price,\n" +
                    "    FORMAT(SUM(a.qty * a.price), '###,###,###') AS total,\n" +
                    "    \n" +
                    "    SUM(b.unit) AS amt_in,\n" +
                    "    FORMAT(SUM(b.price), '###,###,###') AS price_in,\n" +
                    "    FORMAT(SUM(b.qty * b.price), '###,###,###') AS total_in,\n" +
                    "    \n" +
                    "    SUM(c.unit) AS amt_out,\n" +
                    "    FORMAT(SUM(c.price), '###,###,###') AS price_out,\n" +
                    "    FORMAT(SUM(c.qty * c.price), '###,###,###') AS total_out  ");
            sb.append(tableCon);
            sb.append(startDateCon);
            sb.append(endDateCon);
            sb.append(group);
            String query = sb.toString();
            log.info("query sql:"+query);
            return EBankJdbcTemplate.query(query, new RowMapper<ReportAllStock>() {
                @Override
                public ReportAllStock mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportAllStock tr = new ReportAllStock();
                    tr.setImage(rs.getString("image"));
                    tr.setItemName(rs.getString("item_name"));
                    tr.setItemId(rs.getString("item_id"));
                    tr.setType(rs.getString("type"));
                    tr.setTypeName(rs.getString("type_name"));
                    tr.setBorNo(rs.getString("bor_no"));
                    tr.setBorName(rs.getString("bor_name"));
                    tr.setBlocation(rs.getString("blocation"));
                    tr.setTxnDateIn(rs.getString("txndateIn"));
                    tr.setTotalOut(rs.getString("txndateOut"));
                    tr.setAmt(rs.getInt("amt"));
                    tr.setPrice(rs.getDouble("price"));
                    tr.setTotal(rs.getInt("total"));
                    tr.setAmtIn(rs.getInt("amt_in") != 0 ? rs.getInt("amt_in") : 0);
                    tr.setPriceIn(rs.getDouble("price_in"));
                    tr.setTotalIn(rs.getString("total_in") != null ? rs.getString("total_in") : "0");
                    tr.setAmtOut(rs.getInt("amt_out") != 0 ? rs.getInt("amt_out") : 0);
                    tr.setPriceOut(rs.getDouble("price_out"));
                    tr.setTotalOut(rs.getString("total_out") != null ? rs.getString("total_out") : "0");
                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
