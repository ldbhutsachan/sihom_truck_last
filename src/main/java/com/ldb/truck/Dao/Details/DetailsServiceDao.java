package com.ldb.truck.Dao.Details;

import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Inventory.OfferPaper.PoCodeModel;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.*;
import com.ldb.truck.RowMapper.DetailsMapper.DetailsMapper;
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
public class DetailsServiceDao implements  DetailsDao {
    private static final Logger log = LogManager.getLogger(DetailsServiceDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    @Override
    public List<Details> ListDetailsPopup() {
        try{
            String sql ="select * from TB_DETAILS order by OUT_DATE desc ";
            return  EBankJdbcTemplate.query(sql, new RowMapper<Details>() {
                @Override
                public Details mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Details tr =new Details();
                    tr.setKEY_ID(rs.getString("KEY_ID"));
                    tr.setLaHud_poyLod(rs.getString("LAHUD_POYLOD"));
                    tr.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
                    tr.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
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
                    tr.setKim_KILO(rs.getString("KIM_KILO"));
                    tr.setPRICE(rs.getString("PRICE"));
                    tr.setTOTAL_PRICE(rs.getString("TOTAL_PRICE"));
                    tr.setD_STATUS(rs.getString("d_status"));
                    tr.setPriceNamMun(rs.getString("PRIECENUMNUN"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //show invoice No
    @Override
    public List<getInvoiceNo> showMaxLahudPoyLod(TogenTheCodeReq togenTheCodeReq) {
        String sql;
        try {
            int branch = Integer.parseInt(togenTheCodeReq.getBranch());
            switch (branch) {
                case 5:
                    sql = getSqlForBranch5();
                    break;
                case 2:
                    sql = getSqlForBranch2();
                    break;
                case 3:
                    sql = getSqlForBranch3();
                    break;
                case 4:
                    sql = getSqlForBranch4();
                    break;
                default:
                    // Handle invalid branch case (e.g., throw exception)
                    throw new IllegalArgumentException("Invalid branch code: " + branch);
            }

            log.info("SQL:" + sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<getInvoiceNo>() {
                @Override
                public getInvoiceNo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    getInvoiceNo tr = new getInvoiceNo();
                    tr.setLAHUD_POYLOD(rs.getString("LAHUD_POYLOD" + branch)); // Assuming suffix based on branch
                    return tr;
                }
            });
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log error and throw a specific exception)
            throw new RuntimeException("Error fetching invoice number", e);
        }
    }
    //show Offer paper code No
    @Override
    public List<GetOfferCode> showMaxOfferCode (TogenTheCodeReq togenTheCodeReq) {
        String sql;
        try {
            int branch = Integer.parseInt(togenTheCodeReq.getBranch());
            switch (branch) {
                case 5:
                    sql = getSqlForOffBranch5();
                    break;
                case 2:
                    sql = getSqlForOffBranch2();
                    break;
                case 3:
                    sql = getSqlForOffBranch3();
                    break;
                case 4:
                    sql = getSqlForOffBranch4();
                    break;
                case 9:
                    sql = getSqlForOffBranch9();
                    break;
                default:
                    // Handle invalid branch case (e.g., throw exception)
                    throw new IllegalArgumentException("Invalid branch code: " + branch);
            }

            log.info("SQL:" + sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<GetOfferCode>() {
                @Override
                public GetOfferCode mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GetOfferCode tr = new GetOfferCode();
                    tr.setOFFER_CODE(rs.getString("OFFER_CODE" + branch)); // Assuming suffix based on branch
                    return tr;
                }
            });
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log error and throw a specific exception)
            throw new RuntimeException("Error fetching invoice number", e);
        }
    }

//    new gen bor id
@Override
public List<GetOfferCode> GenOfferCodeBorhin (TogenTheCodeReq togenTheCodeReq) {

    try {
        String sql = "SELECT OFFER_CODE_bor FROM GEN_OFFER_NO";
        log.info("SQL:" + sql);
        return EBankJdbcTemplate.query(sql, new RowMapper<GetOfferCode>() {
            @Override
            public GetOfferCode mapRow(ResultSet rs, int rowNum) throws SQLException {
                GetOfferCode tr = new GetOfferCode();
                tr.setOFFER_CODE(rs.getString("OFFER_CODE_bor")); // Assuming suffix based on branch
                return tr;
            }
        });
    } catch (Exception e) {
        // Handle exception appropriately (e.g., log error and throw a specific exception)
        throw new RuntimeException("Error fetching invoice number", e);
    }
}
    private String getSqlForOffBranch5() {
        return "select OFFER_CODE5 from GEN_OFFER_NO";
    }

    private String getSqlForOffBranch2() {
        return "select OFFER_CODE2 from GEN_OFFER_NO";
    }

    private String getSqlForOffBranch3() {
        return "select OFFER_CODE3 from GEN_OFFER_NO";
    }
    private String getSqlForOffBranch4() {return "select OFFER_CODE4 from GEN_OFFER_NO"; }
    private String getSqlForOffBranch9() {return "select OFFER_CODE9 from GEN_OFFER_NO"; }

//    get PO code new
    @Override
    public List<PoCodeModel> showMaxPOCodeNew (TogenTheCodeReq togenTheCodeReq) {
        String sql;
        try {
            int branch = Integer.parseInt(togenTheCodeReq.getBranch());
            switch (branch) {
                case 5:
                    sql = getSqlForPOBranch5();
                    break;
                case 2:
                    sql = getSqlForPOBranch2();
                    break;
                case 3:
                    sql = getSqlForPOBranch3();
                    break;
                case 4:
                    sql = getSqlForPOBranch4();
                    break;
                case 9:
                    sql = getSqlForPOBranch9();
                    break;
                default:
                    // Handle invalid branch case (e.g., throw exception)
                    throw new IllegalArgumentException("Invalid branch code: " + branch);
            }

            log.info("SQL:" + sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<PoCodeModel>() {
                @Override
                public PoCodeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PoCodeModel tr = new PoCodeModel();
                    tr.setPO_CODE(rs.getString("PO_CODE" + branch)); // Assuming suffix based on branch
                    return tr;
                }
            });
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log error and throw a specific exception)
            throw new RuntimeException("Error fetching invoice number", e);
        }
    }
    // gen po bor hin
    public List<PoCodeModel> showMaxPOCodeNewBorHin (TogenTheCodeReq togenTheCodeReq) {
        try {
            String sql = "select PO_CODE_bor from GEN_PO";
            log.info("SQL:" + sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<PoCodeModel>() {
                @Override
                public PoCodeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PoCodeModel tr = new PoCodeModel();
                    tr.setPO_CODE(rs.getString("PO_CODE_bor")); // Assuming suffix based on branch
                    return tr;
                }
            });
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log error and throw a specific exception)
            throw new RuntimeException("Error fetching invoice number", e);
        }
    }
    private String getSqlForPOBranch5() {
        return "select PO_CODE5 from GEN_PO";
    }

    private String getSqlForPOBranch2() {
        return "select PO_CODE2 from GEN_PO";
    }

    private String getSqlForPOBranch3() {
        return "select PO_CODE3 from GEN_PO";
    }

    private String getSqlForPOBranch4() {
        return "select PO_CODE4 from GEN_PO";
    }
    private String getSqlForPOBranch9() {
        return "select PO_CODE9 from GEN_PO";
    }

//    get KKT- code quotation
public List<getQuotationCode> showKKTcode () {
    String sql;
    try {
        sql="select * from GEN_QUOTATION_CODE";
        return EBankJdbcTemplate.query(sql, new RowMapper<getQuotationCode>() {
            @Override
            public getQuotationCode mapRow(ResultSet rs, int rowNum) throws SQLException {
                getQuotationCode tr = new getQuotationCode();
                tr.setKkt_code(rs.getString("QUOTATION_CODE"));
                return tr;
            }
        });
    } catch (Exception e) {
        // Handle exception appropriately (e.g., log error and throw a specific exception)
        throw new RuntimeException("Error fetching invoice number", e);
    }
}
//get invoice dept code DAOs
public List<getInvoiceDeptCode> showINVcode () {
    String sql;
    try {
        sql="select * from GEN_INVOICE_OUT";
        return EBankJdbcTemplate.query(sql, new RowMapper<getInvoiceDeptCode>() {
            @Override
            public getInvoiceDeptCode mapRow(ResultSet rs, int rowNum) throws SQLException {
                getInvoiceDeptCode tr = new getInvoiceDeptCode();
                tr.setInvoice_code_out(rs.getString("INVOICE_CODE"));
                return tr;
            }
        });
    } catch (Exception e) {
        // Handle exception appropriately (e.g., log error and throw a specific exception)
        throw new RuntimeException("Error fetching invoice number", e);
    }
}
    private String getSqlForBranch5() {
        return "select LAHUD_POYLOD5 from AUTO_DETAILNO";
    }

    private String getSqlForBranch2() {
        return "select LAHUD_POYLOD2 from AUTO_DETAILNO";
    }

    private String getSqlForBranch3() {
        return "select LAHUD_POYLOD3 from AUTO_DETAILNO";
    }

    private String getSqlForBranch4() {
        return "select LAHUD_POYLOD4 from AUTO_DETAILNO";
    }

    //PRICE,TOTAL_PRICE
    @Override
    public List<Details> ListDetails() {
        try{
            String sql ="select * from V_TB_DETAILS order by OUT_DATE,totalday desc ";
            return  EBankJdbcTemplate.query(sql, new RowMapper<Details>() {
                @Override
                public Details mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Details tr =new Details();
                    tr.setKEY_ID(rs.getString("KEY_ID"));
                    tr.setLaHud_poyLod(rs.getString("LAHUD_POYLOD"));
                    tr.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
                    tr.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
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
                    tr.setKim_KILO(rs.getString("KIM_KILO"));
                    tr.setPRICE(rs.getString("PRICE"));
                    tr.setTOTAL_PRICE(rs.getString("TOTAL_PRICE"));
                    tr.setD_STATUS(rs.getString("d_status"));
                    tr.setPriceNamMun(rs.getString("PRIECENUMNUN"));
                    tr.setTotalDay(rs.getString("totalDay"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Details> ListDetailsById(DetailsReq detailsReq) {
        try{
            String sql ="select * from V_TB_DETAILS where KEY_ID ='"+detailsReq.getKEY_ID()+"' order by OUT_DATE,totalday desc ";
            return EBankJdbcTemplate.query(sql, new DetailsMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int updateData(DetailsReq detailsReq) {
        try {
            String sql ="update TB_DETAILS set  " +
                    "LAHUD_POYLOD=?," +
                    "CUSTOMER_ID=?,  \n" +
                    "PRODUCT_ID=?,  \n" +
                    "PRODUCT_AMOUNT=?,  \n" +
                    "PRODUCT_SIZE=?,  \n" +
                    "PRODUCT_DETAILS=?, \n" +
                    "PRODUCT_FROM=?,  \n" +
                    "PRODUCT_TO=?,  \n" +
                    "PLACE_PD_FROM=?,  \n" +
                    "PLACE_PD_TO=?,  \n" +
                    "STAFF_ID_NUM1=?,  \n" +
                    "STAFF_ID_NUM2=?,  \n" +
                    "STAFF_BIALIENG=?,  \n" +
                    "STAFF_BIALIENG_FRIST=?, \n" +
                    "STAFF_BIALINEG_KANGJAIY=?, \n" +
                    "HEADER_ID=?,  \n" +
                    "FOOTER_ID=?,  \n" +
                    "OUT_DATE=?,  \n" +
                    "IN_DATE=?,  \n" +
                    "LAIYATHANG=?,  \n" +
                    "SAINUMMUN=?,  \n" +
                    "NUMNUKLOD=?,  \n" +
                    "KONGNARLOD=?,  \n" +
                    "KHG_MUE_TIDLOD=?,PRICE=?,TOTAL_PRICE=?,PRIECENUMNUN=?  \n" +
                    "KIM_KILO=?,staff02_payAll=?,staff02_beforepay=?,staff02_notpay=?,FeeOvertime1=?,\n" +
                    "FeeJumPo2=?,\n" +
                    "FeePolish3=?,\n" +
                    "FeeTaxung4=?,\n" +
                    "FeeTiew5=?,\n" +
                    "FeeLakSao=?,\n" +
                    "FeePassport=?,\n" +
                    "Feevacin=?,\n" +
                    "Feesing=?,\n" +
                    "Feesaphan=?,\n" +
                    "Feeyoktu=?,\n" +
                    "Feecontrainer=?,\n" +
                    "Feepayang=? where key_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(detailsReq.getLaHud_poyLod());
            paramList.add(detailsReq.getCUSTOMER_ID());
            paramList.add(detailsReq.getPRODUCT_ID());
            paramList.add(detailsReq.getPRODUCT_AMOUNT());
            paramList.add(detailsReq.getPRODUCT_SIZE());
            paramList.add(detailsReq.getPRODUCT_DETAILS());
            paramList.add(detailsReq.getPRODUCT_FROM());
            paramList.add(detailsReq.getPRODUCT_TO());
            paramList.add(detailsReq.getPLACE_PD_FROM());
            paramList.add(detailsReq.getPLACE_PD_TO());
            paramList.add(detailsReq.getSTAFF_ID_NUM1());
            paramList.add(detailsReq.getSTAFF_ID_NUM2());
            paramList.add(detailsReq.getSTAFF_BIALIENG());
            paramList.add(detailsReq.getSTAFF_BIALIENG_FRIST());
            paramList.add(detailsReq.getSTAFF_BIALINEG_KANGJAIY());
            paramList.add(detailsReq.getHEADER_ID());
            paramList.add(detailsReq.getFOOTER_ID());
            paramList.add(detailsReq.getOUT_DATE());
            paramList.add(detailsReq.getIN_DATE());
            paramList.add(detailsReq.getLAIYATHANG());
            paramList.add(detailsReq.getSAINUMMUN());
            paramList.add(detailsReq.getNUMNUKLOD());
            paramList.add(detailsReq.getKONGNARLOD());
            paramList.add(detailsReq.getKHG_MUE_TIDLOD());
            paramList.add(detailsReq.getKim_KILO());
            paramList.add(detailsReq.getPrice());
            paramList.add(detailsReq.getTotalPrice());
            paramList.add(detailsReq.getPriceNamMun());
            paramList.add(detailsReq.getStaff02_PayAll());
            paramList.add(detailsReq.getStaff02_Beforepay());
            paramList.add(detailsReq.getStaff02_Notpay());

            paramList.add(detailsReq.getFeeOvertime1());
            paramList.add(detailsReq.getFeeJumPo2());
            paramList.add(detailsReq.getFeePolish3());
            paramList.add(detailsReq.getFeeTaxung4());
            paramList.add(detailsReq.getFeeTiew5());
            paramList.add(detailsReq.getFeeLakSao());
            paramList.add(detailsReq.getFeePassport());
            paramList.add(detailsReq.getFeevacin());
            paramList.add(detailsReq.getFeesing());
            paramList.add(detailsReq.getFeesaphan());
            paramList.add(detailsReq.getFeeyoktu());
            paramList.add(detailsReq.getFeecontrainer());
            paramList.add(detailsReq.getFeepayang());

            paramList.add(detailsReq.getKEY_ID());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e ){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Details> delData(DetailsReq detailsReq) {
        try {
        String sql ="delete from TB_DETAILS where key_id='"+detailsReq.getKEY_ID()+"' ";
                EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int saveData(DetailsReq detailsReq) {
        String PRIECENUMNUN = detailsReq.getPriceNamMun().replace(",","");
        String STAFF_BIALIENG = detailsReq.getSTAFF_BIALIENG().replace(",","");
        String price = detailsReq.getPrice().replace(",","");
        String totalPrice = detailsReq.getTotalPrice().replace(",","");

        String staff02_payAll = detailsReq.getStaff02_PayAll().replace(",","");
        String staff02_beforepay = detailsReq.getStaff02_Beforepay().replace(",","");
        String staff02_notpay = detailsReq.getStaff02_Notpay().replace(",","");


        try {
             String sql ="insert into TB_DETAILS (LAHUD_POYLOD,CUSTOMER_ID,  \n" +
                     "PRODUCT_ID,  \n" +
                     "PRODUCT_AMOUNT,  \n" +
                     "PRODUCT_SIZE,  \n" +
                     "PRODUCT_DETAILS, \n" +
                     "PRODUCT_FROM,  \n" +
                     "PRODUCT_TO,  \n" +
                     "PLACE_PD_FROM,  \n" +
                     "PLACE_PD_TO,  \n" +
                     "STAFF_ID_NUM1,  \n" +
                     "STAFF_ID_NUM2,  \n" +
                     "STAFF_BIALIENG,  \n" +
                     "STAFF_BIALIENG_FRIST, \n" +
                     "STAFF_BIALINEG_KANGJAIY, \n" +
                     "HEADER_ID,  \n" +
                     "FOOTER_ID,  \n" +
                     "OUT_DATE,  \n" +
                     "IN_DATE,  \n" +
                     "LAIYATHANG,  \n" +
                     "SAINUMMUN,  \n" +
                     "NUMNUKLOD,  \n" +
                     "KONGNARLOD,  \n" +
                     "KHG_MUE_TIDLOD,  \n" +
                     "KIM_KILO,DETAILS_DATE," +
                     "PRICE,TOTAL_PRICE,d_status,PRIECENUMNUN,CURRENCY,STAFF_BIALIENG_CUR,CREATE_DATE,CREATE_BY,staff_01_status,staff_02_status,staff02_payAll,staff02_beforepay,staff02_notpay,feeOvertime1,\n" +
                     "feeJumPo2,\n" +
                     "feePolish3,\n" +
                     "feeTaxung4,\n" +
                     "feeTiew5,\n" +
                     "feeLakSao,\n" +
                     "feePassport,\n" +
                     "feevacin,\n" +
                     "feesing,\n" +
                     "feesaphan,\n" +
                     "feeyoktu,\n" +
                     "feecontrainer,\n" +
                     "feepayang,userId,add_feeOvertime1,add_feeJumPo2,add_feePolish3,add_feeTaxung4,add_feeTiew5,add_feesing,add_feesaphan,add_feeyoktu,add_feecontrainer,add_feepayang,FUEL_STATUS,FUEL_STATION_ID,LAIYATHANG_SUM) " +
                     "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,'N',?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,KIM_KILO+LAIYATHANG) ";
             log.info("SQL:"+sql);
            List<Object> paramList = new ArrayList<Object>();
          //  paramList.add(detailsReq.getKEY_ID());
            paramList.add(detailsReq.getLaHud_poyLod());
            paramList.add(detailsReq.getCUSTOMER_ID());
            paramList.add(detailsReq.getPRODUCT_ID());
            paramList.add(detailsReq.getPRODUCT_AMOUNT());
            paramList.add(detailsReq.getPRODUCT_SIZE());
            paramList.add(detailsReq.getPRODUCT_DETAILS());
            paramList.add(detailsReq.getPRODUCT_FROM());
            paramList.add(detailsReq.getPRODUCT_TO());
            paramList.add(detailsReq.getPLACE_PD_FROM());
            paramList.add(detailsReq.getPLACE_PD_TO());
            paramList.add(detailsReq.getSTAFF_ID_NUM1());
            paramList.add(detailsReq.getSTAFF_ID_NUM2());
            paramList.add(STAFF_BIALIENG);
            paramList.add(detailsReq.getSTAFF_BIALIENG_FRIST());
            paramList.add(detailsReq.getSTAFF_BIALINEG_KANGJAIY());
            paramList.add(detailsReq.getHEADER_ID());
            paramList.add(detailsReq.getFOOTER_ID());
            paramList.add(detailsReq.getOUT_DATE());
            paramList.add(detailsReq.getIN_DATE());
            paramList.add(detailsReq.getLAIYATHANG());
            paramList.add(detailsReq.getSAINUMMUN());
            paramList.add(detailsReq.getNUMNUKLOD());
            paramList.add(detailsReq.getKONGNARLOD());
            paramList.add(detailsReq.getKHG_MUE_TIDLOD());
            paramList.add(detailsReq.getKim_KILO());
            paramList.add(price);
            paramList.add(totalPrice);
            paramList.add(PRIECENUMNUN);
            paramList.add(detailsReq.getCurrency());
            paramList.add(detailsReq.getStaff_Curr());
            paramList.add(detailsReq.getCreate_By());
            paramList.add(detailsReq.getStaff_Status01());
            paramList.add(detailsReq.getStaff_Status02());
            paramList.add(staff02_payAll);
            paramList.add(staff02_beforepay);
            paramList.add(staff02_notpay);

            paramList.add(detailsReq.getFeeOvertime1());
            paramList.add(detailsReq.getFeeJumPo2());
            paramList.add(detailsReq.getFeePolish3());
            paramList.add(detailsReq.getFeeTaxung4());
            paramList.add(detailsReq.getFeeTiew5());
            paramList.add(detailsReq.getFeeLakSao());
            paramList.add(detailsReq.getFeePassport());
            paramList.add(detailsReq.getFeevacin());
            paramList.add(detailsReq.getFeesing());
            paramList.add(detailsReq.getFeesaphan());
            paramList.add(detailsReq.getFeeyoktu());
            paramList.add(detailsReq.getFeecontrainer());
            paramList.add(detailsReq.getFeepayang());
            paramList.add(detailsReq.getUserId());

            paramList.add(detailsReq.getAdd_feeOvertime1());
            paramList.add(detailsReq.getAdd_feeJumPo2());
            paramList.add(detailsReq.getAdd_feePolish3());
            paramList.add(detailsReq.getAdd_feeTaxung4());
            paramList.add(detailsReq.getAdd_feeTiew5());
            paramList.add(detailsReq.getAdd_feesing());
            paramList.add(detailsReq.getAdd_feesaphan());
            paramList.add(detailsReq.getAdd_feeyoktu());
            paramList.add(detailsReq.getAdd_feecontrainer());
            paramList.add(detailsReq.getAdd_feepayang());
            paramList.add(detailsReq.getFuel_status());
            paramList.add(detailsReq.getFuelStationId());
            paramList.add(detailsReq.getLAIYATHANG_SUM());
          //  paramList.add(detailsReq.getDETAILS_DATE());
            return EBankJdbcTemplate.update(sql, paramList.toArray());

        }catch (Exception e ){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public int UpdateHeader(DetailsReq detailsReq) {
        System.out.println("hh:"+detailsReq.getHEADER_ID());
        try{
            String sql = "update TB_HEADER_TRUCK set h_status='N' where key_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(detailsReq.getHEADER_ID());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int UpdateFooter(DetailsReq detailsReq) {
        System.out.println("hh:"+detailsReq.getFOOTER_ID());
        try{
            String sql = "update TB_FOOTER_TRUCH set f_status='N' where key_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(detailsReq.getFOOTER_ID());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //--
            //paramList.add(detailsReq.getSTAFF_ID_NUM1());
           // paramList.add(detailsReq.getSTAFF_ID_NUM2());
    @Override
    public int updateStaff01(DetailsReq detailsReq) {
        try{
            String sql = "update STAFF set OUT_STATUS='Y' where key_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(detailsReq.getSTAFF_ID_NUM1());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int updateStaff02(DetailsReq detailsReq) {
        try{
            String sql = "update STAFF set OUT_STATUS='Y' where key_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(detailsReq.getSTAFF_ID_NUM2());
            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
