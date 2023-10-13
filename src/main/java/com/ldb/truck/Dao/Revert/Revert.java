package com.ldb.truck.Dao.Revert;

import com.ldb.truck.Dao.OweDao.OweDao;
import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Details.DetailsRes;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Payment.Invoice;
import com.ldb.truck.Model.Login.Payment.InvoiceDetail;
import com.ldb.truck.Model.Login.Payment.InvoiceDetailReq;
import com.ldb.truck.Model.Login.Payment.InvoiceReq;
import com.ldb.truck.Model.Login.Performance.*;
import com.ldb.truck.Model.Login.RevertModel.PerformanceModelRes;
import com.ldb.truck.RowMapper.Payment.InvoiceMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.ldb.truck.RowMapper.DetailsMapper.DetailsMapper;
import com.ldb.truck.RowMapper.Revert.ReverPerFormanceMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class Revert implements RevertDao{
    private static final Logger log = LogManager.getLogger(Revert.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String sql="";

    @Override
    public int updateHeader01(DetailsReq detailsReq) {
      sql="update TB_DETAILS set HEADER_ID='"+detailsReq.getHeaderNew()+"' where LAHUD_POYLOD='"+detailsReq.getLaHud_poyLod()+"'";
      return EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateFooter01(DetailsReq detailsReq) {
        sql="update TB_DETAILS set FOOTER_ID='"+detailsReq.getFooterNew()+"' where LAHUD_POYLOD='"+detailsReq.getLaHud_poyLod()+"'";
        return EBankJdbcTemplate.update(sql);
    }
    @Override
    public int updateStaff011(DetailsReq detailsReq) {
        sql="update TB_DETAILS set STAFF_ID_NUM1='"+detailsReq.getStaffNew01()+"' where LAHUD_POYLOD='"+detailsReq.getLaHud_poyLod()+"'";
        return EBankJdbcTemplate.update(sql);
    }
    @Override
    public int updateStaff012(DetailsReq detailsReq) {
        sql="update TB_DETAILS set STAFF_ID_NUM2='"+detailsReq.getStaffNew02()+"' where LAHUD_POYLOD='"+detailsReq.getLaHud_poyLod()+"'";
        return EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateHeader(DetailsReq detailsReq) {
        sql = "update TB_HEADER_TRUCK set H_STATUS='Y' where key_id='"+detailsReq.getHEADER_ID()+"'";
        return  EBankJdbcTemplate.update(sql);
    }
    @Override
    public int updateFooter(DetailsReq detailsReq) {
        sql = "update TB_FOOTER_TRUCH set F_STATUS='Y' where key_id='"+detailsReq.getFOOTER_ID()+"'";
        return  EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateStaff01(DetailsReq detailsReq) {
        sql = "update STAFF set OUT_STATUS='N' where key_id='"+detailsReq.getSTAFF_ID_NUM1()+"'";
        return  EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateStaff02(DetailsReq detailsReq) {
        sql = "update STAFF set OUT_STATUS='N' where key_id='"+detailsReq.getSTAFF_ID_NUM2()+"'";
        return  EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateHeaderNew(DetailsReq detailsReq) {
        sql = "update TB_HEADER_TRUCK set H_STATUS='N' where key_id='"+detailsReq.getHeaderNew()+"'";
        return  EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateFooterNew(DetailsReq detailsReq) {
        sql = "update TB_FOOTER_TRUCH set F_STATUS='N' where key_id='"+detailsReq.getFooterNew()+"'";
        return  EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateStaff01New(DetailsReq detailsReq) {
        sql = "update STAFF set OUT_STATUS='Y' where key_id='"+detailsReq.getStaffNew01()+"'";
        return  EBankJdbcTemplate.update(sql);
    }

    @Override
    public int updateStaff02New(DetailsReq detailsReq) {
        sql = "update STAFF set OUT_STATUS='Y' where key_id='"+detailsReq.getStaffNew02()+"'";
        return  EBankJdbcTemplate.update(sql);
    }

    //show list for revert
    @Override
    public List<Details> showOutCarRevert() {
        List<Details> result = new ArrayList<>();
        try{
            sql="select * from V_TB_DETAILS where  order by KEY_ID asc";
            result =  EBankJdbcTemplate.query(sql, new DetailsMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
//***show details by showOutCarRevertbyNo
    @Override
    public List<Details> showOutCarRevertbyNo(DetailsReq detailsReq) {
        List<Details> result = new ArrayList<>();
        try{
            sql="select * from V_TB_DETAILS where LAHUD_POYLOD='"+detailsReq.getLaHud_poyLod()+"' order by KEY_ID asc";
            result = EBankJdbcTemplate.query(sql, new DetailsMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //update details
    @Override
    public int UpdateRevertTxn(DetailsReq detailsReq) {
        Details data = new Details();
        try
        {
                sql ="update TB_DETAILS set CUSTOMER_ID=?,  \n" +
                        "PRODUCT_ID=?,  \n" +
                        "PRODUCT_AMOUNT=?,  \n" +
                        "PRODUCT_SIZE=?,  \n" +
                        "PRODUCT_DETAILS=?, \n" +
                        "PRODUCT_FROM=?,  \n" +
                        "PRODUCT_TO=?,  \n" +
                        "PLACE_PD_FROM=?,  \n" +
                        "PLACE_PD_TO=?,  \n" +
//                        "STAFF_ID_NUM1=?,  \n" +
//                        "STAFF_ID_NUM2=?,  \n" +
                        "STAFF_BIALIENG=?,  \n" +
                        "STAFF_BIALIENG_FRIST=?, \n" +
                        "STAFF_BIALINEG_KANGJAIY=?, \n" +
//                        "HEADER_ID=?,  \n" +
//                        "FOOTER_ID=?,  \n" +
                        "OUT_DATE=?,  \n" +
                        "IN_DATE=?,  \n" +
                        "LAIYATHANG=?,  \n" +
                        "SAINUMMUN=?,  \n" +
                        "NUMNUKLOD=?,  \n" +
                        "KONGNARLOD=?,  \n" +
                        "KHG_MUE_TIDLOD=?,PRICE=?,TOTAL_PRICE=?,PRIECENUMNUN=?,KIM_KILO=?,CURRENCY=?,STAFF_BIALIENG_CUR=? where LAHUD_POYLOD=?";
                log.info("sql:"+sql);
                List<Object> paramList = new ArrayList<Object>();
                paramList.add(detailsReq.getCUSTOMER_ID());
                paramList.add(detailsReq.getPRODUCT_ID());
                paramList.add(detailsReq.getPRODUCT_AMOUNT());
                paramList.add(detailsReq.getPRODUCT_SIZE());
                paramList.add(detailsReq.getPRODUCT_DETAILS());
                paramList.add(detailsReq.getPRODUCT_FROM());
                paramList.add(detailsReq.getPRODUCT_TO());
                paramList.add(detailsReq.getPLACE_PD_FROM());
                paramList.add(detailsReq.getPLACE_PD_TO());
                paramList.add(detailsReq.getSTAFF_BIALIENG());
                paramList.add(detailsReq.getSTAFF_BIALIENG_FRIST());
                paramList.add(detailsReq.getSTAFF_BIALINEG_KANGJAIY());
                paramList.add(detailsReq.getOUT_DATE());
                paramList.add(detailsReq.getIN_DATE());
                paramList.add(detailsReq.getLAIYATHANG());
                paramList.add(detailsReq.getSAINUMMUN());
                paramList.add(detailsReq.getNUMNUKLOD());
                paramList.add(detailsReq.getKONGNARLOD());
                paramList.add(detailsReq.getKHG_MUE_TIDLOD());
                paramList.add(detailsReq.getPrice());
                paramList.add(detailsReq.getTotalPrice());
                paramList.add(detailsReq.getPriceNamMun());
                paramList.add(detailsReq.getKim_KILO());
                paramList.add(detailsReq.getCurrency());
                paramList.add(detailsReq.getStaff_Curr());
                paramList.add(detailsReq.getLaHud_poyLod());
                return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //---revert out car by lahudpoylod no:
    @Override
    public int UpdateRevertOutCar(DetailsReq detailsReq) {
        try
        {
            sql ="update TB_DETAILS set D_STATUS='Reversal' where lahud_poylod='"+detailsReq.getLaHud_poyLod()+"'";
            log.info("sql:"+sql);
            return EBankJdbcTemplate.update(sql);
        }catch (Exception e){
           e.printStackTrace();
        }
        return 0;
    }
    //action Revert performance
    @Override
    public List<Performance> showPerformance() {
        List<Performance> result = new ArrayList<>();
        try{
            sql="select * from V_PRINT_BILL_PROFORMANCE order by key_id asc";
            result = EBankJdbcTemplate.query(sql,new ReverPerFormanceMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Performance> showPerformanceByNo(PerformanceReq vPerformanceReq) {
        List<Performance> result = new ArrayList<>();
        try{
            sql="select * from V_PRINT_BILL_PROFORMANCE where LAHUD_POYLOD like '"+vPerformanceReq.getPerformanceBillNo()+"' OR KEY_ID like '"+vPerformanceReq.getPerformanceBillNo()+"' order by key_id asc";
            result = EBankJdbcTemplate.query(sql,new ReverPerFormanceMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int updatePerformanceStatusByNo(PerformanceReq vPerformanceReq) {
        try{
            sql="update TB_PERFORMANCE set STATUS='Reversal' where KEY_ID='"+vPerformanceReq.getKey_id()+"'";
            return EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
        ///
    }
    @Override
    public int updatePerformanceAllTxn(PerformanceReq performanceReq) {
        log.info("key:"+performanceReq.getKey_id());
        v_performance data = new v_performance();
        try{
            sql="update TB_PERFORMANCE set performanceReDate=?,performanceTotal=?,\n" +
                    "performanceOvertime=?,performanceJumPho=?,performanceFeePolish=?,performanceFeeTaxung=?,\n" +
                    "performanceFeeTiew=?,performanceOverVN=?,performanceBoderLak20=?,performancePassport=?,\n" +
                    "performanceVaccine=?,performanceFeeSing=?,performanceFeeSaPhan=?,performanceFeeYoktu=?,\n" +
                    "performanceFeeOutContainer=?,feeUnit=?,feeTotal=?,PERFORMANCEFE_PAYANG=?,PRODUCT_SIZE=?,PRODUCT_AMOUNT=?,PRODUCT_TOTALAMOUNT=?  where key_id='"+performanceReq.getKey_id()+"' OR  performanceBillNo='"+performanceReq.getPerformanceBillNo()+"'";
            log.info("sql:"+sql);
            List<String> paramList = new ArrayList<>();
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
            paramList.add(performanceReq.getProSize());
            paramList.add(performanceReq.getProAmount());
            paramList.add(performanceReq.getTotal_PRICE());
            return EBankJdbcTemplate.update(sql,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int updatePerformanceAllTxnHis(PerformanceReq performanceReq) {
        log.info("key:"+performanceReq.getPer_PA());
        v_performance data = new v_performance();
        try{
            sql="update TB_PERFORMANCE_HIS set performanceReDate=?,performanceTotal=?,\n" +
                    "performanceOvertime=?,performanceJumPho=?,performanceFeePolish=?,performanceFeeTaxung=?,\n" +
                    "performanceFeeTiew=?,performanceOverVN=?,performanceBoderLak20=?,performancePassport=?,\n" +
                    "performanceVaccine=?,performanceFeeSing=?,performanceFeeSaPhan=?,performanceFeeYoktu=?,\n" +
                    "performanceFeeOutContainer=?,feeUnit=?,feeTotal=?,PERFORMANCEFE_PAYANG=? ,PRODUCT_SIZE=?,PRODUCT_AMOUNT=?,PRODUCT_TOTALAMOUNT=?,CURRENCY=? where key_id='"+performanceReq.getKey_id()+"' OR  performanceBillNo='"+performanceReq.getPerformanceBillNo()+"'";
            log.info("sql:"+sql);
            List<String> paramList = new ArrayList<>();

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
            paramList.add(performanceReq.getProSize());
            paramList.add(performanceReq.getTotal_PRICE());
            paramList.add(performanceReq.getTotal_PRICE());
            paramList.add(performanceReq.getCurrency());

            return EBankJdbcTemplate.update(sql,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int updateAmountDetails(PerformanceReq performanceReq) {
        try
        {
            sql="update  TB_DETAILS set PRODUCT_SIZE='"+performanceReq.getProSize()+"',PRICE='"+performanceReq.getTotal_PRICE()+"',TOTAL_PRICE='"+performanceReq.getTotal_PRICE()+"' where key_id  in (select a.LAHUD_POYLOD from tb_details a inner " +
                    "join TB_PERFORMANCE b on a.LAHUD_POYLOD=b.PERFORMANCEBILLNO  where LAHUD_POYLOD='"+performanceReq.getPerformanceBillNo()+"') ";
            return EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    ////Revert invoice
    @Override
    public List<InvoiceDetail> ShowInvoiceDetailsAll() {
        List<InvoiceDetail> result = new ArrayList<>();
        try {
            sql = "select * from V_PRINTINVOICEBLACK  order by INVOICE_ID asc";
            System.out.println("sql:"+sql);
            result = EBankJdbcTemplate.query(sql , new InvoiceMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
//--show pup up invoice
@Override
public List<InvoiceDetail> ShowInvoiceDetailsAllForPupupInvoice() {
    List<InvoiceDetail> result = new ArrayList<>();
    try {
        sql = "select * from V_PRINTINVOICEBLACK where INVOICE_STATUS in ('N') order by INVOICE_ID asc";
        System.out.println("sql:"+sql);
        result = EBankJdbcTemplate.query(sql , new InvoiceMapper());
    }catch (Exception e){
        e.printStackTrace();
        return result;
    }
    return result;
}
    @Override
    public List<InvoiceDetail> ShowInvoiceDetailsByNo(InvoiceDetailReq invoiceDetailReq) {
        List<InvoiceDetail> result = new ArrayList<>();
        try {
            sql = "select * from V_PRINTINVOICEBLACK where INVOICE_ID='"+invoiceDetailReq.getInVoiceID()+"'  order by INVOICE_ID asc";
            System.out.println("sql:"+sql);
            result = EBankJdbcTemplate.query(sql , new InvoiceMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
///INVOICE_DETAILS           PERFORMANCEBILLNO
    @Override
    public int updateInvoiceAllTxn(InvoiceDetailReq invoiceDetailReq) {
        try {
            sql = "update INVOICE_DETAILS set PERFORMANCEBILLNO=?,\n" +
                    "CUSTOMER_ID=?,CUSTOMER_NAME=?,PRO_TYPE=?,PRODUCT_AMOUNT=?,PRICE=?,TOTAL_PRICE=?,STATUS=?  where INVOICE_ID ='"+invoiceDetailReq.getInVoiceID()+"' ";
            System.out.println("sql:"+sql);
            List<String> paramList = new ArrayList<>();
            paramList.add(invoiceDetailReq.getKey_id());
            paramList.add(invoiceDetailReq.getCusName());
            paramList.add(invoiceDetailReq.getCusName());
            paramList.add(invoiceDetailReq.getProType());
            paramList.add(invoiceDetailReq.getProAmount());
            paramList.add(invoiceDetailReq.getPriCE());
            paramList.add(invoiceDetailReq.getTotalPrice());
            paramList.add(invoiceDetailReq.getStatus());
//            paramList.add(data.getInVoiceID());
            return EBankJdbcTemplate.update(sql,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //---Revert invoice
    //--INVOICE
    @Override
    public int reVertInvoiceByNo(InvoiceDetailReq invoiceDetailReq) {
        try {
            sql = "update INVOICE set STATUS='Revesal'  where INVOICE_ID ='"+invoiceDetailReq.getInVoiceID()+"' ";
            System.out.println("sql:"+sql);
          return  EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
@Override
public int reVertInvoiceHisByNo(InvoiceDetailReq invoiceDetailReq) {
    try {
        sql = "update INVOICE_DETAILS set STATUS='Revesal'  where INVOICE_ID ='"+invoiceDetailReq.getInVoiceID()+"' ";
        System.out.println("sql:"+sql);
        return  EBankJdbcTemplate.update(sql);
    }catch (Exception e){
        e.printStackTrace();
    }
    return 0;
}
    @Override
    public int updateInvoiceStatusFromTxn(InvoiceDetailReq invoiceDetailReq) {
       InvoiceDetail data = new InvoiceDetail();
        try{
            sql="update INVOICE set STATUS=? where INVOICE_ID=?";
                    System.out.println("sql:"+sql);
                    List<String> paramList = new ArrayList<>();
                    paramList.add(data.getStatus());
                    paramList.add(data.getKeyId());
                    return EBankJdbcTemplate.update(sql,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //--
    @Override
    public int reVertPerByNo(InvoiceDetailReq invoiceDetailReq){
        try {
            //select b.KEY_ID from INVOICE_DETAILS a inner join tb_performance b on b.key_id=a.PERFORMANCEBILLNO where INVOICE_ID='VOICE-10009'
            //TB_PERFORMANCE_HIS
            sql = "update TB_PERFORMANCE set STATUS='N'  where KEY_ID in (select b.KEY_ID from INVOICE_DETAILS a inner join TB_PERFORMANCE b on b.key_id=a.PERFORMANCEBILLNO where INVOICE_ID='"+invoiceDetailReq.getInVoiceID()+"') ";
            System.out.println("sql:"+sql);
            return  EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int reVertPerHisByNo(InvoiceDetailReq invoiceDetailReq){
        try {
            //TB_PERFORMANCE_HIS
            sql = "update TB_PERFORMANCE_HIS set STATUS='N'  where KEY_ID in (select b.KEY_ID from INVOICE_DETAILS a inner join TB_PERFORMANCE b on b.key_id=a.PERFORMANCEBILLNO where INVOICE_ID='"+invoiceDetailReq.getInVoiceID()+"') ";
            System.out.println("sql:"+sql);
            return  EBankJdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


}
