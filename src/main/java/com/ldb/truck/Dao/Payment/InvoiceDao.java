package com.ldb.truck.Dao.Payment;
import com.ldb.truck.Model.Login.Payment.*;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.TogenTheCodeReq;
import com.ldb.truck.RowMapper.Payment.GenerateIDMapper;
import com.ldb.truck.RowMapper.Payment.InvoiceMapper;
import com.ldb.truck.RowMapper.Payment.PrintInvoiceByNoRowMapper;
import com.ldb.truck.RowMapper.Payment.PrintInvoiceOnlyByNoRowMapper;
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
public class InvoiceDao  implements  InvoiceInDao{
    private static final Logger log = LogManager.getLogger(InvoiceInDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL="";


    private String getSqlForBranch5() {
        return "select INVOICE_ID5,printDate from AUTO_INVOICENO";
    }

    private String getSqlForBranch2() {
        return "select INVOICE_ID2,printDate from AUTO_INVOICENO";
    }

    private String getSqlForBranch3() {
        return "select INVOICE_ID3,printDate from AUTO_INVOICENO";
    }

    private String getSqlForBranch4() {
        return "select INVOICE_ID4,printDate from AUTO_INVOICENO";
    }

    @Override
    ///----generate  ID
    public List<GenerateInvoiceID> gernerateID(TogenTheCodeReq togenTheCodeReq) {
        List<GenerateInvoiceID> result = new ArrayList<>();
        String sql;
        try {
//            SQL = "select  * from  AUTO_INVOICENO order by INVOICE_ID asc";
//            System.out.println("sql:"+SQL);
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
            return EBankJdbcTemplate.query(sql, new RowMapper<GenerateInvoiceID>() {
                @Override
                public GenerateInvoiceID  mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GenerateInvoiceID data = new GenerateInvoiceID();
                    data.setINVOICE_ID(rs.getString("INVOICE_ID" + branch));
                    data.setPrintDate(rs.getString("printDate"));
                    return data;
                }
            });
//            result = EBankJdbcTemplate.query(sql , new GenerateIDMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
//        return result;
    }
    @Override
    public List<Invoice> ListInvoiceAll() {
        List<Invoice> result = new ArrayList<>();
        try {
            SQL = "select * from  V_INVOICE_ALL order by INVOICE_ID asc";
            System.out.println("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL , new InvoiceMapper());
        }catch (Exception e){
        e.printStackTrace();
        return result;
        }
        return result;
    }
    @Override
    public List<Invoice> listInvoiceDetails(ResFromDateReq resFromDateReq) {
        List<Invoice> result = new ArrayList<>();
        log.info("resALL:"+resFromDateReq.getStartDate());
        try {
            if(resFromDateReq.getStartDate()==null && resFromDateReq.getEndDate()==null){
                log.info("res:"+resFromDateReq.getStartDate());
//                SQL = "select * from V_PRINTINVOICEBLACK  order by INVOICE_ID asc";
                SQL = "select * from V_PRINTINVOICEBLACK a inner join LOGIN b ON a.userId=b.KEY_ID where b.BRANCH='"+resFromDateReq.getBranch()+"'  order by a.INVOICE_ID asc";
            }else {
                SQL = "select * from V_PRINTINVOICEBLACK a inner join LOGIN b a.userId=b.KEY_ID where b.BRANCH='"+resFromDateReq.getBranch()+"' AND a.INVOICE_DATE between '"+resFromDateReq.getStartDate()+"' and '"+resFromDateReq.getEndDate()+"' order by INVOICE_ID asc";
            }
            System.out.println("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL , new InvoiceMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public int saveInvoice(InvoiceReq invoiceReq) {
        try {
            SQL = "insert into  INVOICE (INVOICE_ID,INVOICE_DATE)" +
                    " values (?,now())";
            List<Object> paramList = new ArrayList<>();
            paramList.add(invoiceReq.getInVoiceID());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e ){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<PrintInvoiceByNo> pintBillByNo(PrintInvoiceByNoReq printInvoiceByNoReq) {
        List<PrintInvoiceByNo> result =new ArrayList<>();
        try {
            //PayDateOwe
                SQL= "select * from V_PRINTINVOICE where INVOICE_ID ='"+printInvoiceByNoReq.getBillNo()+"'";
//                SQL= "select * from V_PRINTINVOICE a inner join LOGIN b ON a.userId=b.KEY_ID where a.INVOICE_ID ='"+printInvoiceByNoReq.getBillNo()+"' and b.BRANCH='"+printInvoiceByNoReq.getBranch()+"'";
                result = EBankJdbcTemplate.query(SQL,new PrintInvoiceOnlyByNoRowMapper());

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //---
    @Override
    public List<PrintInvoiceByNo> viewPintBillByNo(PrintInvoiceByNoReq printInvoiceByNoReq) {
        List<PrintInvoiceByNo> result =new ArrayList<>();
        try {
            SQL= "select * from V_PRINTINVOICE where INVOICE_ID ='"+printInvoiceByNoReq.getBillNo()+"' ";
            result = EBankJdbcTemplate.query(SQL,new PrintInvoiceOnlyByNoRowMapper());

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
//payback
@Override
public List<PrintInvoiceByNo> viewPintBillBackByNo(PrintInvoiceByNoReq printInvoiceByNoReq) {
    List<PrintInvoiceByNo> result =new ArrayList<>();
    try {
        SQL= "select * from V_PAYMANT_OWE_BLACK  where INVOICE_ID ='"+printInvoiceByNoReq.getBillNo()+"' OR BILLNO ='"+printInvoiceByNoReq.getBillNo()+"' ";
        result = EBankJdbcTemplate.query(SQL,new PrintInvoiceByNoRowMapper());

    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
    @Override
    public int CreateMoreInvoice(List<InvoiceDetailReq> invoiceDetailReq) {
        log.info("key:"+invoiceDetailReq);
        try {
            SQL ="insert into  INVOICE_DETAILS (CUSTOMER_ID,CUSTOMER_NAME,PERFORMANCEBILLNO,PRO_TYPE,PRODUCT_AMOUNT,PRICE,TOTAL_PRICE,INVOICE_ID,STATUS,userId)" +
                    "values (?,?,?,?,?,?,?,?,?,?)";
            log.info("sqL:"+SQL);
            List<Object[]> paramList = new ArrayList<Object[]>();
            String status ="N";
            for(InvoiceDetailReq resList : invoiceDetailReq){
                log.info("key:"+resList.getKey_id());
                log.info("key:"+resList.getPerID());
                log.info("userId:"+resList.getUserId());
                Object[] objectArray = {
                        resList.getCusID(),
                        resList.getCusName() ,
                        resList.getKey_id(),
                        resList.getProType(),
                        resList.getProAmount(),
                        resList.getPriCE(),
                        resList.getTotalPrice(),
                        resList.getInVoiceID(),
                        status,
                        resList.getUserId()
                };
                paramList.add(objectArray);
            }
            EBankJdbcTemplate.batchUpdate(SQL,paramList);
            //--insert invoice frist
            for(InvoiceDetailReq resList1 : invoiceDetailReq){
                String SQL1 = "insert into  INVOICE (INVOICE_ID,INVOICE_DATE,Status,userId) values  (?,now(),'N',?)";
                List<Object> paramList1 = new ArrayList<>();
                paramList1.add(resList1.getInVoiceID());
                paramList1.add(resList1.getUserId());
                return EBankJdbcTemplate.update(SQL1, paramList1.toArray());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    //--  update TB_PERFORMANCE set status='Y' where PERFORMANCEBILLNO='10008'
    @Override
    public int updatePerformance(List<InvoiceDetailReq> invoiceDetailReq) {
        log.info("sa:"+invoiceDetailReq);
        try{
            SQL="update TB_PERFORMANCE set status=? where PERFORMANCEBILLNO= ? ";
            List<Object[]> paramList = new ArrayList<Object[]>();
            String status ="Y";
            for(InvoiceDetailReq resList : invoiceDetailReq){
                log.info("list1:"+resList.getPerID());
                log.info("list2:"+status);
                Object[] objectArray = {
                        status,
                        resList.getPerID(),
                };
                paramList.add(objectArray);
            }
            log.info("sql:"+SQL);
            EBankJdbcTemplate.batchUpdate(SQL,paramList);
        }catch (Exception e){
        e.printStackTrace();
        }
        return 0;
    }
    //--
    @Override
    public List<Invoice> List_v_popupPerInVoice(InvoiceDetailReq invoiceDetailReq) {
        List<Invoice> result = new ArrayList<>();
        try {
//            SQL = "select * from V_PRINTINVOICEBຫຳສLACK where INVOICE_STATUS in ('N')  order by INVOICE_ID asc";
            SQL = "select * from V_PRINTINVOICEBLACK a join LOGIN b on a.userId =b.KEY_ID  where a.INVOICE_STATUS ='N' and b.BRANCH ='"+invoiceDetailReq.getBranch()+"' ";
            System.out.println("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL , new InvoiceMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
