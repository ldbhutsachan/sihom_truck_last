package com.ldb.truck.Dao.documentStorageDAOs;

import com.ldb.truck.Controller.VicicleHeaderController;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeModel;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeReq;
import com.ldb.truck.Model.Login.Dept_Must_Receive.*;
import com.ldb.truck.Model.Login.DocumentStorage.*;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenTypeReq;
import com.ldb.truck.Model.Login.Payment.InvoiceDetailReq;
import com.ldb.truck.Model.Login.Task.TaskModel;
import com.ldb.truck.Model.Login.Task.TaskReq;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
//implements DocumentInterface
public class DocumentStorageDaos implements DocumentInterface{
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

//    inert or store the document
    private static final Logger log = LogManager.getLogger(VicicleHeaderController.class);
    public int InsertDocumentDAOs (DocumentStorageReq documentStorageReq) throws ParseException {
        String path="http://khounkham.com/images/car/";
        String fileName = documentStorageReq.getPdf();
        log.info("path:"+path+fileName);
        List<DocumentStorageModel> data = new ArrayList<>();
        try{
            String SQL = "insert into DOCUMENT_STORAGE (PDF,BRANCH_USER,DATECREATE,DOC_TYPE,BRANCH_OFFICE,TOKEN,INBOUNDNUMBER,OUTBOUNDNUMBER,DateExpDoc,CLASSOFDOCS,BOUND,CONTENT_DOC,WHOCARRYDOC,ETC,LEKTEE,DateTakeIn,saveById,company,bouang,inside) value(?,?,now(),'"+documentStorageReq.getDocType()+"','"+documentStorageReq.getBranch()+"','"+documentStorageReq.getToKen()+"','"+documentStorageReq.getInboundnumber()+"','"+documentStorageReq.getOutboundnumber()+"','"+documentStorageReq.getDateExpDoc()+"','"+documentStorageReq.getClassofdocs()+"','"+documentStorageReq.getBound()+"','"+documentStorageReq.getContent_doc()+"','"+documentStorageReq.getWhocarrydoc()+"','"+documentStorageReq.getEtc()+"','"+documentStorageReq.getLektee()+"','"+documentStorageReq.getDatetakein()+"','"+documentStorageReq.getUserId()+"','"+documentStorageReq.getCompany()+"','"+documentStorageReq.getBouang()+"','"+documentStorageReq.getInside()+"')";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(path + fileName);
            paramList.add(documentStorageReq.getBranchUser());
            paramList.add(documentStorageReq.getDateCreate());
            paramList.add(documentStorageReq.getDocType());
            paramList.add(documentStorageReq.getToKen());
            paramList.add(documentStorageReq.getInboundnumber());
            paramList.add(documentStorageReq.getOutboundnumber());
            paramList.add(documentStorageReq.getDateExpDoc());
            paramList.add(documentStorageReq.getClassofdocs());
            paramList.add(documentStorageReq.getBound());
            paramList.add(documentStorageReq.getContent_doc());
            paramList.add(documentStorageReq.getWhocarrydoc());
            paramList.add(documentStorageReq.getEtc());
            paramList.add(documentStorageReq.getLektee());
            paramList.add(documentStorageReq.getDatetakein());
            paramList.add(documentStorageReq.getCompany());
            paramList.add(documentStorageReq.getBouang());
            paramList.add(documentStorageReq.getInside());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
//    insert pic of borhin
//public int InsertMultiPic (DocumentStorageReq documentStorageReq) throws ParseException {
//    String path="http://khounkham.com/images/car/";
//    String fileName = documentStorageReq.getPdf();
//    log.info("path:"+path+fileName);
//    List<DocumentStorageModel> data = new ArrayList<>();
//    try{
//        String SQL = "insert into TB_PICTURE (pic,userId,branch) value(?,'"+documentStorageReq.getBranch()+"','"+documentStorageReq.getBranch()+"')";
//        log.info("SQL:"+SQL);
//        List<Object> paramList = new ArrayList<Object>();
//        paramList.add(path + fileName);
//        paramList.add(documentStorageReq.getUserId());
//        paramList.add(documentStorageReq.getBranch());
//        return EBankJdbcTemplate.update(SQL, paramList.toArray());
//    }catch (Exception e){
//        e.printStackTrace();
//        return -1;
//    }
//}
public int InsertMultiPic(DocumentStorageReq[] documentStorageReqs) throws ParseException {
    int totalInserted = 0; // Track total inserted records

    String path = "http://khounkham.com/images/car/";

    try {
        String SQL = "insert into TB_PICTURE (pic,userId,branch,folderName,dateCreate,branch_id) values(?, ?, ?, ?, ?, ?)";

        for (DocumentStorageReq documentStorageReq : documentStorageReqs) {
            String fileName = documentStorageReq.getPdf();
            log.info("Processing request for: " + fileName);

            List<Object> paramList = new ArrayList<>();
            paramList.add(path + fileName);
            paramList.add(documentStorageReq.getUserId());
            paramList.add(documentStorageReq.getBranch());
            paramList.add(documentStorageReq.getFolderName());
            paramList.add(documentStorageReq.getDateCreate());
            paramList.add(documentStorageReq.getBranch_id());

            totalInserted += EBankJdbcTemplate.update(SQL, paramList.toArray());
        }

        return totalInserted;

    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
}
//task dao
public int InsertTaskDaos(TaskReq[] taskReq1) throws ParseException {
    int totalInserted = 0; // Track total inserted records
    try {
        String SQL = "insert into TB_TASKS (TOPIC_TASK,SUB_TASK,START_DATE,END_DATE,toKen,BRANCH_ID,PROGRESS) values(?, ?, ?, ?, ?, ?, ?)";
        log.info("Script" + SQL);

        for (TaskReq taskReq:taskReq1) {
            List<Object> paramList = new ArrayList<>();
            paramList.add(taskReq.getTopic_task());
            paramList.add(taskReq.getParent());
            paramList.add(taskReq.getStartDate());
            paramList.add(taskReq.getEndDate());
            paramList.add(taskReq.getToKen());
            paramList.add(taskReq.getBranch());
            paramList.add(taskReq.getProgress());
            totalInserted += EBankJdbcTemplate.update(SQL, paramList.toArray());
        }
        return totalInserted;
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
}
//update task DAOs
public int UpdateTaskDaos (TaskReq[] taskReq1) throws ParseException {
    int totalInserted = 0; // Track total inserted records
    try {
        String SQL = "update TB_TASKS set TOPIC_TASK=?,SUB_TASK=?, PROGRESS=? where KEY_ID =? ";
        log.info("Script" + SQL);

        for (TaskReq taskReq:taskReq1) {
            List<Object> paramList = new ArrayList<>();
            paramList.add(taskReq.getTopic_task());
            paramList.add(taskReq.getParent());
//            paramList.add(taskReq.getStartDate());
            paramList.add(taskReq.getProgress());
            paramList.add(taskReq.getKey_id());
            totalInserted += EBankJdbcTemplate.update(SQL, paramList.toArray());
        }
        return totalInserted;
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
}
//    dept must received DAOs
public int DeptMustReceivedInsertDAOs (DeptMustReceivedReq deptMustReceivedReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = deptMustReceivedReq.getDocument_1();
//    String fileName2 = deptMustReceivedReq.getDocument_2();
    String d1 =path + fileName;
//    String d2 =path + fileName2;
    log.info("path:"+path+fileName);
    String sql ="";
    String sqq ="";
//    log.info("path2:"+path+fileName2);
    List<DocumentStorageModel> data = new ArrayList<>();
    try{
         sql = "insert into DEBT_MUST_RECEIVED (CUSTOMER_ID,TOPIC,TYPE_ID,CURRENCY,DATE,DUE_DATE,DATE_CREATE,STATUS_WAIT_APPROVE,REFERENCE_NUMBER,LEK_BAI_SUNG,AMOUNT_MONEY,QUOTATION,DOCUMENT_1,DESCRIPTION,NOTE,NUM,UNIT,TOTALMONEY,QUOTATION_CODE,userId,INVOICE_STATUS,ACCOUNT_NUMBER,ACCOUNT_NAME) value(?,?,?,?,?,?,now(),'N','"+deptMustReceivedReq.getReference_number()+"','"+deptMustReceivedReq.getLek_bai_sung()+"','"+deptMustReceivedReq.getAmount_money()+"','"+deptMustReceivedReq.getQuotation()+"','"+d1+"','"+deptMustReceivedReq.getDescription()+"','"+deptMustReceivedReq.getNote()+"','"+deptMustReceivedReq.getNum()+"','"+deptMustReceivedReq.getUnit()+"','"+deptMustReceivedReq.getTotalMooney()+"','"+deptMustReceivedReq.getQuotation_code()+"','"+deptMustReceivedReq.getUserId()+"','N','ACCOUNT_NUMBER','ACCOUNT_NAME') ";
        log.info("SQL:"+sql);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(deptMustReceivedReq.getCustomer_id());
        paramList.add(deptMustReceivedReq.getTopic());
        paramList.add(deptMustReceivedReq.getType_id());
        paramList.add(deptMustReceivedReq.getCurrency());
        paramList.add(deptMustReceivedReq.getDate());
        paramList.add(deptMustReceivedReq.getDue_date());
        paramList.add(deptMustReceivedReq.getDate_create());
        paramList.add(deptMustReceivedReq.getStatus_wait_approve());
        paramList.add(deptMustReceivedReq.getReference_number());
        paramList.add(deptMustReceivedReq.getLek_bai_sung());
        paramList.add(deptMustReceivedReq.getAmount_money());
        paramList.add(deptMustReceivedReq.getQuotation());
        paramList.add(d1);
//        paramList.add(d2);
        paramList.add(deptMustReceivedReq.getDescription());
        paramList.add(deptMustReceivedReq.getNote());
        paramList.add(deptMustReceivedReq.getNum());
        paramList.add(deptMustReceivedReq.getUnit());
        paramList.add(deptMustReceivedReq.getTotalMooney());
        paramList.add(deptMustReceivedReq.getQuotation_code());
        paramList.add(deptMustReceivedReq.getUserId());
        paramList.add(deptMustReceivedReq.getInvoice_status());
        paramList.add(deptMustReceivedReq.getAccount_number());
        paramList.add(deptMustReceivedReq.getAccount_name());
        return EBankJdbcTemplate.update(sql, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//insert no pic
public int DeptMustReceivedInsertDAOsNoDoc (DeptMustReceivedReq deptMustReceivedReq) throws ParseException {
    String sql ="";
    List<DocumentStorageModel> data = new ArrayList<>();
    try{
        sql = "insert into DEBT_MUST_RECEIVED (CUSTOMER_ID,TOPIC,TYPE_ID,CURRENCY,DATE,DUE_DATE,DATE_CREATE,STATUS_WAIT_APPROVE,REFERENCE_NUMBER,LEK_BAI_SUNG,AMOUNT_MONEY,QUOTATION,DESCRIPTION,NOTE,NUM,UNIT,TOTALMONEY,QUOTATION_CODE,userId,INVOICE_STATUS,ACCOUNT_NUMBER,ACCOUNT_NAME) value(?,?,?,?,?,?,now(),'N','"+deptMustReceivedReq.getReference_number()+"','"+deptMustReceivedReq.getLek_bai_sung()+"','"+deptMustReceivedReq.getAmount_money()+"','"+deptMustReceivedReq.getQuotation()+"','"+deptMustReceivedReq.getDescription()+"','"+deptMustReceivedReq.getNote()+"','"+deptMustReceivedReq.getNum()+"','"+deptMustReceivedReq.getUnit()+"','"+deptMustReceivedReq.getTotalMooney()+"','"+deptMustReceivedReq.getQuotation_code()+"','"+deptMustReceivedReq.getUserId()+"','N','ACCOUNT_NUMBER','ACCOUNT_NAME') ";
        log.info("SQL:"+sql);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(deptMustReceivedReq.getCustomer_id());
        paramList.add(deptMustReceivedReq.getTopic());
        paramList.add(deptMustReceivedReq.getType_id());
        paramList.add(deptMustReceivedReq.getCurrency());
        paramList.add(deptMustReceivedReq.getDate());
        paramList.add(deptMustReceivedReq.getDue_date());
        paramList.add(deptMustReceivedReq.getDate_create());
        paramList.add(deptMustReceivedReq.getStatus_wait_approve());
        paramList.add(deptMustReceivedReq.getReference_number());
        paramList.add(deptMustReceivedReq.getLek_bai_sung());
        paramList.add(deptMustReceivedReq.getAmount_money());
        paramList.add(deptMustReceivedReq.getQuotation());
        paramList.add(deptMustReceivedReq.getDescription());
        paramList.add(deptMustReceivedReq.getNote());
        paramList.add(deptMustReceivedReq.getNum());
        paramList.add(deptMustReceivedReq.getUnit());
        paramList.add(deptMustReceivedReq.getTotalMooney());
        paramList.add(deptMustReceivedReq.getQuotation_code());
        paramList.add(deptMustReceivedReq.getUserId());
        paramList.add(deptMustReceivedReq.getInvoice_status());
        paramList.add(deptMustReceivedReq.getAccount_number());
        paramList.add(deptMustReceivedReq.getAccount_name());
        return EBankJdbcTemplate.update(sql, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//service array insert
@Override
public int DeptMustRecievedInsertArray(List<DeptMustReceivedReq> deptMustReceivedRe) {
    try {
    String SQL ="insert into  TB_LIST_DMRC (listName,quotation_code,amount,price,totalPrice)" +
                "values (?,?,?,?,?)";
        log.info("sqL:"+SQL);
        List<Object[]> paramList = new ArrayList<Object[]>();
        for(DeptMustReceivedReq resList : deptMustReceivedRe){
            Object[] objectArray = {
                    resList.getListName() ,
                    resList.getQuotation_code() ,
                    resList.getNum(),
                    resList.getAmount_money(),
                    resList.getTotalMooney()
            };
            paramList.add(objectArray);
        }
        EBankJdbcTemplate.batchUpdate(SQL,paramList);
    }catch (Exception e){
        e.printStackTrace();
    }
    return -1;
}
    public int InvoiceDeptInsertDAOsNoPic (InvoiceDeptReq invoiceDeptReq) throws ParseException {
//        String path="http://khounkham.com/images/car/";
//        String fileName = invoiceDeptReq.getPdfandpic();
//        String d =path + fileName;
//        log.info("path:"+path+fileName);
        List<DocumentStorageModel> data = new ArrayList<>();
        try{
            String SQL = "insert into INVOICE_DEPT_OUT (date_invoice,detail,GETMONEY_STATUS,AMOUNT_OF_MONEY,userId,quotation_code,INVOICE_CODE) value('"+invoiceDeptReq.getDate_invoice()+"','"+invoiceDeptReq.getDetail()+"','N','"+invoiceDeptReq.getAmount_of_money()+"','"+invoiceDeptReq.getUserId()+"','"+invoiceDeptReq.getQuotation_code()+"','"+invoiceDeptReq.getInvoice_code()+"')";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(invoiceDeptReq.getDate_invoice());
            paramList.add(invoiceDeptReq.getDetail());
            paramList.add(invoiceDeptReq.getAmount_of_money());
            paramList.add(invoiceDeptReq.getUserId());
            paramList.add(invoiceDeptReq.getQuotation_code());
            paramList.add(invoiceDeptReq.getInvoice_code());
            EBankJdbcTemplate.update(SQL, paramList.toArray());

//        inserrt his payment
            String invoiceHis = "insert into INVOICE_DEPT_HIS (date_invoice,AMOUNT_OF_MONEY,userId,quotation_code,INVOICE_CODE) value('"+invoiceDeptReq.getDate_invoice()+"','"+invoiceDeptReq.getAmount_of_money()+"','"+invoiceDeptReq.getUserId()+"','"+invoiceDeptReq.getQuotation_code()+"','"+invoiceDeptReq.getInvoice_code()+"')";
            log.info("SQL:"+invoiceHis);
            paramList.add(invoiceDeptReq.getDate_invoice());
            paramList.add(invoiceDeptReq.getAmount_of_money());
            paramList.add(invoiceDeptReq.getUserId());
            paramList.add(invoiceDeptReq.getQuotation_code());
            paramList.add(invoiceDeptReq.getInvoice_code());
            EBankJdbcTemplate.update(invoiceHis, paramList.toArray());

//         update amount - amout
            String UpdateAmout = "update DEBT_MUST_RECEIVED set TOTALMONEY=TOTALMONEY-'"+invoiceDeptReq.getAmount_of_money()+"' where QUOTATION_CODE ='"+invoiceDeptReq.getQuotation_code()+"'";
            log.info("SQL_update_DEBT_MUST_RECEIVED:"+UpdateAmout);
//        paramList.add(invoiceDeptReq.getAmount_of_money());
            paramList.add(invoiceDeptReq.getQuotation_code());
            EBankJdbcTemplate.update(UpdateAmout, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
//insert invoice dept DAOs
public int InvoiceDeptInsertDAOs (InvoiceDeptReq invoiceDeptReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = invoiceDeptReq.getPdfandpic();
    String d =path + fileName;
    log.info("path:"+path+fileName);
    List<DocumentStorageModel> data = new ArrayList<>();
    try{
        String SQL = "insert into INVOICE_DEPT_OUT (date_invoice,detail,file,GETMONEY_STATUS,AMOUNT_OF_MONEY,userId,quotation_code,INVOICE_CODE) value('"+invoiceDeptReq.getDate_invoice()+"','"+invoiceDeptReq.getDetail()+"','"+d+"','N','"+invoiceDeptReq.getAmount_of_money()+"','"+invoiceDeptReq.getUserId()+"','"+invoiceDeptReq.getQuotation_code()+"','"+invoiceDeptReq.getInvoice_code()+"')";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(invoiceDeptReq.getDate_invoice());
        paramList.add(invoiceDeptReq.getDetail());
        paramList.add(d);
        paramList.add(invoiceDeptReq.getAmount_of_money());
        paramList.add(invoiceDeptReq.getUserId());
        paramList.add(invoiceDeptReq.getQuotation_code());
        paramList.add(invoiceDeptReq.getInvoice_code());
         EBankJdbcTemplate.update(SQL, paramList.toArray());

//        inserrt his payment
        String invoiceHis = "insert into INVOICE_DEPT_HIS (date_invoice,AMOUNT_OF_MONEY,userId,quotation_code,INVOICE_CODE) value('"+invoiceDeptReq.getDate_invoice()+"','"+invoiceDeptReq.getAmount_of_money()+"','"+invoiceDeptReq.getUserId()+"','"+invoiceDeptReq.getQuotation_code()+"','"+invoiceDeptReq.getInvoice_code()+"')";
        log.info("SQL:"+invoiceHis);
        paramList.add(invoiceDeptReq.getDate_invoice());
        paramList.add(invoiceDeptReq.getAmount_of_money());
        paramList.add(invoiceDeptReq.getUserId());
        paramList.add(invoiceDeptReq.getQuotation_code());
        paramList.add(invoiceDeptReq.getInvoice_code());
         EBankJdbcTemplate.update(invoiceHis, paramList.toArray());

//         update amount - amout
        String UpdateAmout = "update DEBT_MUST_RECEIVED set TOTALMONEY=TOTALMONEY-'"+invoiceDeptReq.getAmount_of_money()+"' where QUOTATION_CODE ='"+invoiceDeptReq.getQuotation_code()+"'";
        log.info("SQL_update_DEBT_MUST_RECEIVED:"+UpdateAmout);
//        paramList.add(invoiceDeptReq.getAmount_of_money());
        paramList.add(invoiceDeptReq.getQuotation_code());
        EBankJdbcTemplate.update(UpdateAmout, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
    return 0;
}
//update dept must received
public int DeptMustReceivedUpdateDAOs (DeptMustReceivedReq deptMustReceivedReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = deptMustReceivedReq.getDocument_1();
    String fileName2 = deptMustReceivedReq.getDocument_2();
    String d1 =path + fileName;
//    String d2 =path + fileName2;
    log.info("path:"+path+fileName);
//    log.info("path2:"+path+fileName2);
    List<DocumentStorageModel> data = new ArrayList<>();
    try{
        String SQL ="UPDATE DEBT_MUST_RECEIVED SET CUSTOMER_ID=?,TOPIC=?,TYPE_ID=?,CURRENCY=?,DATE=?,DUE_DATE=?,DATE_CREATE=now(),REFERENCE_NUMBER='"+deptMustReceivedReq.getReference_number()+"',LEK_BAI_SUNG='"+deptMustReceivedReq.getLek_bai_sung()+"',AMOUNT_MONEY='"+deptMustReceivedReq.getAmount_money()+"',QUOTATION='"+deptMustReceivedReq.getQuotation()+"',DOCUMENT_1='"+d1+"',DESCRIPTION='"+deptMustReceivedReq.getDescription()+"',NOTE='"+deptMustReceivedReq.getNote()+"',NUM='"+deptMustReceivedReq.getNum()+"',UNIT='"+deptMustReceivedReq.getUnit()+"',TOTALMONEY='"+deptMustReceivedReq.getTotalMooney()+"' WHERE KEY_ID='"+deptMustReceivedReq.getKey_id()+"'";
            log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(deptMustReceivedReq.getCustomer_id());
        paramList.add(deptMustReceivedReq.getTopic());
        paramList.add(deptMustReceivedReq.getType_id());
        paramList.add(deptMustReceivedReq.getCurrency());
        paramList.add(deptMustReceivedReq.getDate());
        paramList.add(deptMustReceivedReq.getDue_date());
        paramList.add(deptMustReceivedReq.getDate_create());
        paramList.add(deptMustReceivedReq.getReference_number());
        paramList.add(deptMustReceivedReq.getLek_bai_sung());
        paramList.add(deptMustReceivedReq.getAmount_money());
        paramList.add(deptMustReceivedReq.getQuotation());
        paramList.add(d1);
//        paramList.add(d2);
        paramList.add(deptMustReceivedReq.getDescription());
        paramList.add(deptMustReceivedReq.getNote());
        paramList.add(deptMustReceivedReq.getNum());
        paramList.add(deptMustReceivedReq.getUnit());
        paramList.add(deptMustReceivedReq.getTotalMooney());
        paramList.add(deptMustReceivedReq.getKey_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//invoice dept update
public int InvoiceDeptUpdateDAOs (InvoiceDeptReq invoiceDeptReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = invoiceDeptReq.getPdfandpic();
    String d =path + fileName;
    log.info("path:"+path+fileName);
    List<DocumentStorageModel> data = new ArrayList<>();
    try{
        String SQL ="UPDATE INVOICE_DEPT_OUT SET date_invoice=?,detail=?,file=?,AMOUNT_OF_MONEY=?,userId=? WHERE KEY_ID='"+invoiceDeptReq.getKey_id()+"' AND INVOICE_CODE='"+invoiceDeptReq.getInvoice_code()+"'";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(invoiceDeptReq.getDate_invoice());
        paramList.add(invoiceDeptReq.getDetail());
        paramList.add(d);
        paramList.add(invoiceDeptReq.getAmount_of_money());
        paramList.add(invoiceDeptReq.getUserId());
        paramList.add(invoiceDeptReq.getKey_id());
        paramList.add(invoiceDeptReq.getInvoice_code());
        EBankJdbcTemplate.update(SQL, paramList.toArray());

        //        inserrt his payment
        String invoiceHis = "insert into INVOICE_DEPT_HIS (date_invoice,AMOUNT_OF_MONEY,userId,quotation_code,INVOICE_CODE) value('"+invoiceDeptReq.getDate_invoice()+"','"+invoiceDeptReq.getAmount_of_money()+"','"+invoiceDeptReq.getUserId()+"','"+invoiceDeptReq.getQuotation_code()+"','"+invoiceDeptReq.getInvoice_code()+"')";
        log.info("SQL:"+invoiceHis);
        paramList.add(invoiceDeptReq.getDate_invoice());
        paramList.add(invoiceDeptReq.getAmount_of_money());
        paramList.add(invoiceDeptReq.getUserId());
        paramList.add(invoiceDeptReq.getQuotation_code());
        paramList.add(invoiceDeptReq.getInvoice_code());
        EBankJdbcTemplate.update(invoiceHis, paramList.toArray());

//         update amount - amout
        String UpdateAmout = "update DEBT_MUST_RECEIVED set AMOUNT_MONEY=AMOUNT_MONEY-'"+invoiceDeptReq.getAmount_of_money()+"' where QUOTATION_CODE ='"+invoiceDeptReq.getQuotation_code()+"'";
        log.info("SQL:"+UpdateAmout);
        paramList.add(invoiceDeptReq.getAmount_of_money());
        paramList.add(invoiceDeptReq.getQuotation_code());
        EBankJdbcTemplate.update(UpdateAmout, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
    return 0;
}
//    update Document
public int UpdateDocumentDAOs (DocumentStorageReq documentStorageReq) throws ParseException {
//    String path="http://khounkham.com/images/car/";
//    String fileName = documentStorageReq.getPdf();
//    log.info("path:"+path+fileName);
    List<DocumentStorageModel> data = new ArrayList<>();
    try{
        String SQL = "update DOCUMENT_STORAGE set DOC_TYPE=?,INBOUNDNUMBER=?,OUTBOUNDNUMBER=?,DateExpDoc=?,CLASSOFDOCS=?,BOUND=?,CONTENT_DOC=?,WHOCARRYDOC=?,ETC=?,LEKTEE=?,DateTakeIn=?,company=?,bouang=?,inside=? where KEY_ID='"+documentStorageReq.getKey_id()+"'";
        log.info("SQL:"+documentStorageReq);
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
//        paramList.add(path + fileName);
//        paramList.add(documentStorageReq.getBranchUser());
//        paramList.add(documentStorageReq.getDateCreate());
        paramList.add(documentStorageReq.getDocType());
//        paramList.add(documentStorageReq.getToKen());
        paramList.add(documentStorageReq.getInboundnumber());
        paramList.add(documentStorageReq.getOutboundnumber());
        paramList.add(documentStorageReq.getDateExpDoc());
        paramList.add(documentStorageReq.getClassofdocs());
        paramList.add(documentStorageReq.getBound());
        paramList.add(documentStorageReq.getContent_doc());
        paramList.add(documentStorageReq.getWhocarrydoc());
        paramList.add(documentStorageReq.getEtc());
        paramList.add(documentStorageReq.getLektee());
        paramList.add(documentStorageReq.getDatetakein());
        paramList.add(documentStorageReq.getCompany());
        paramList.add(documentStorageReq.getBouang());
        paramList.add(documentStorageReq.getInside());
        paramList.add(documentStorageReq.getKey_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}

//    insert hole data ข้อมูนรูเจาะ
public int InsertDataHoleDAOs (DataHoleReq dataHoleReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = dataHoleReq.getPic();
    log.info("path:"+path+fileName);
    List<DataHoleModel> data = new ArrayList<>();
    try{
        String SQL = "insert into DATA_HOLE (pic,hole_number,data_Coller,userId,full_Name_Hole_number,branch_id) value(?,?,?,'"+dataHoleReq.getUserId()+"','"+dataHoleReq.getFull_Name_Hole_number()+"','"+dataHoleReq.getBranch_id()+"')";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(path + fileName);
        paramList.add(dataHoleReq.getHoleNumber());
        paramList.add(dataHoleReq.getDataColler());
        paramList.add(dataHoleReq.getFull_Name_Hole_number());
        paramList.add(dataHoleReq.getBranch_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//bouang insert
    @Override
    public int InsertBouangDAOs(BouangReq bouangReq) {
        try{
            String SQL="insert into TB_BOUANG (NAME,TOKEN) values(?,?)";
            List<Object> paraList = new ArrayList<>();
            paraList.add(bouangReq.getNameOfBouang());
            paraList.add(bouangReq.getToKen());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<BouangModel> ListBouangAllDAOSpecial (BouangReq bouangReq) {
        List<BouangModel> result = new ArrayList<>();
        try{
            String SQL = "select * from TB_BOUANG";
                log.info("sql:" + SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<BouangModel>() {
                @Override
                public BouangModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BouangModel tr = new BouangModel();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setNameOfBouang(rs.getString("NAME"));
                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
//    show bouang DAOs
@Override
public List<BouangModel> ListBouangAllDAOs(BouangReq bouangReq) {
    List<BouangModel> result = new ArrayList<>();
    try{
        String SQL = "select * from TB_BOUANG where TOKEN='" + bouangReq.getToKen() + "'";
            log.info("sql:" + SQL);
        return EBankJdbcTemplate.query(SQL, new RowMapper<BouangModel>() {
            @Override
            public BouangModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                BouangModel tr = new BouangModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setNameOfBouang(rs.getString("NAME"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
//bouang update
@Override
public int updateBouangDAOs(BouangReq bouangReq) {
    List<BouangModel> result = new ArrayList<>();
    try{
       String SQL ="update TB_BOUANG set NAME=? where KEY_ID ='"+bouangReq.getKey_id()+"'";
        log.info("sql"+SQL);
        List<Object> paraList = new ArrayList<>();
        paraList.add(bouangReq.getNameOfBouang());
        return EBankJdbcTemplate.update(SQL,paraList.toArray());
    }catch (Exception e){
        e.printStackTrace();
    }
    return 0;

}
//accept request
@Override
public int AcceptupdateStatusDAOs(DeptMustReceivedReq deptMustReceivedReq) {
    List<BouangModel> result = new ArrayList<>();
    try{
        String SQL ="update DEBT_MUST_RECEIVED set STATUS_WAIT_APPROVE = 'Y' where KEY_ID ='"+deptMustReceivedReq.getKey_id()+"'";
        log.info("sql"+SQL);
        List<Object> paraList = new ArrayList<>();
        paraList.add(deptMustReceivedReq.getStatus_wait_approve());
        return EBankJdbcTemplate.update(SQL,paraList.toArray());
    }catch (Exception e){
        e.printStackTrace();
    }
    return 0;

}
//result of survey
public int InsertResultOfSurveyDAOs (DataHoleReq dataHoleReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = dataHoleReq.getFile();
    log.info("path:"+path+fileName);
    List<ResultOfSurveyModel> data = new ArrayList<>();
    try{
        String SQL = "insert into RESULT_SURVEY (files,type,branch_of_bor,name,dateInsert,nameDetail,branch_id) value(?,?,'"+dataHoleReq.getBranch()+"',?,?,?,?)";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(path + fileName);
        paramList.add(dataHoleReq.getType());
//        paramList.add(dataHoleReq.getBranch_Of_Bor());
        paramList.add(dataHoleReq.getName());
        paramList.add(dataHoleReq.getDateInsert());
        paramList.add(dataHoleReq.getNameDetail());
        paramList.add(dataHoleReq.getBranch_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//    show list of document
    public List<DocumentStorageModel> listDocDAOs (DocumentStorageReq documentStorageReq) {
        String sql;
        try{
            if (documentStorageReq.getToKen().equals("UnCuQ8Dql7bSVS9LcDfMWmA8asAtQLMF")){
                if (documentStorageReq.getBound().equals("in"))
                {
                    sql = "select * from V_DOC WHERE BOUND='in'";
                    log.info("SQL in bound:" + sql);
                }
                else if (documentStorageReq.getBound().equals("out"))
                {
                    sql = "select * from V_DOC WHERE BOUND='"+documentStorageReq.getBound()+"'";
                    log.info("SQL out bound:" + sql);
                }
                else if (documentStorageReq.getBound().equals("inside"))
                {
                    sql = "select * from V_DOC WHERE inside='inside'";
                    log.info("SQL out bound:" + sql);
                }
                else if(documentStorageReq.getUserIdoffinanceial().isEmpty())
                {
                    sql = "select * from V_DOC";

                    log.info("SQL:" + sql);
                }else {
                    sql = "select * from V_DOC where saveById='"+documentStorageReq.getUserIdoffinanceial()+"'";
                    log.info("SQL:" + sql);
                }
            }
            else {
                if (documentStorageReq.getBound().equals("in"))
                {
                    sql = "select * from V_DOC WHERE TOKEN='"+documentStorageReq.getToKen()+"'and BOUND='"+documentStorageReq.getBound()+"'";
                    log.info("SQL in bound:" + sql);
                }
                else if (documentStorageReq.getBound().equals("out"))
                {
                    sql = "select * from V_DOC WHERE TOKEN='" + documentStorageReq.getToKen() + "' and BOUND='"+documentStorageReq.getBound()+"'";
                    log.info("SQL out bound:" + sql);
                }
                else if (documentStorageReq.getBound().equals("inside"))
                {
                    sql = "select * from V_DOC WHERE TOKEN='" + documentStorageReq.getToKen() + "' and inside='inside'";
                    log.info("SQL out bound:" + sql);
                }
                else {
                    sql = "select * from V_DOC WHERE TOKEN='" + documentStorageReq.getToKen() + "'";
                    log.info("SQL:" + sql);
                }
            }
            return EBankJdbcTemplate.query(sql, new RowMapper<DocumentStorageModel>() {
                @Override
                public DocumentStorageModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DocumentStorageModel tr = new DocumentStorageModel();
                    tr.setPdf(rs.getString("PDF"));
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setBranchUser(rs.getString("BRANCH_USER"));
                    tr.setDateCreate(rs.getString("DATECREATE"));
                    tr.setDocType(rs.getString("DOC_TYPE"));
                    tr.setInboundnumber(rs.getString("INBOUNDNUMBER"));
                    tr.setOutboundnumber(rs.getString("OUTBOUNDNUMBER"));
                    tr.setDateExpDoc(rs.getString("DateExpDoc"));
                    tr.setClassofdocs(rs.getString("CLASSOFDOCS"));
                    tr.setBound(rs.getString("BOUND"));
                    tr.setContent_doc(rs.getString("CONTENT_DOC"));
                    tr.setWhocarrydoc(rs.getString("WHOCARRYDOC"));
                    tr.setEtc(rs.getString("ETC"));
                    tr.setLektee(rs.getString("LEKTEE"));
                    tr.setDatetakein(rs.getString("DateTakeIn"));
                    tr.setCompany(rs.getString("company"));
                    tr.setBouang(rs.getString("bouang"));
                    tr.setStatus(rs.getString("STATUS"));
                    tr.setInside(rs.getString("inside"));
                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    dept must received DAOs
public List<DeptMustReceivedModel> listDeptMustReceivedDAOs (DeptMustReceivedReq deptMustReceivedReq) {
    String sql;
    try{
        if (deptMustReceivedReq.getToKen().equals("UnCuQ8Dql7bSVS9LcDfMWmA8asAtQLMF")){
            {
                sql = "select * from V_DEPT_MUST_RECEIVED ";
                log.info("SQL:" + sql);
            }
        }
        else {
                sql = "select * from V_DEPT_MUST_RECEIVED WHERE userId='"+deptMustReceivedReq.getUserId()+"'";
                log.info("SQL:" + sql);
        }
        return EBankJdbcTemplate.query(sql, new RowMapper<DeptMustReceivedModel>() {
            @Override
            public DeptMustReceivedModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                DeptMustReceivedModel tr = new DeptMustReceivedModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setCustomerName(rs.getString("CUSTOMER_NAME"));
                tr.setCustomer_id(rs.getString("CUSTOMER_ID"));
                tr.setTopic(rs.getString("TOPIC"));
                tr.setTypeName(rs.getString("NAME_TYPE"));
                tr.setType_id(rs.getString("TYPE_ID"));
                tr.setCurrency(rs.getString("CURRENCY"));
                tr.setDate(rs.getString("DATE"));
                tr.setDue_date(rs.getString("DUE_DATE"));
                tr.setDate_create(rs.getString("DATE_CREATE"));
                tr.setStatus_wait_approve(rs.getString("STATUS_WAIT_APPROVE"));
                tr.setReference_number(rs.getString("REFERENCE_NUMBER"));
                tr.setLek_bai_sung(rs.getString("LEK_BAI_SUNG"));
                tr.setAmount_money(rs.getString("AMOUNT_MONEY"));
                tr.setQuotation(rs.getString("QUOTATION"));
                tr.setDocument_1(rs.getString("DOCUMENT_1"));
//                tr.setDocument_2(rs.getString("DOCUMENT_2"));
                tr.setDescription(rs.getString("DESCRIPTION"));
                tr.setNote(rs.getString("NOTE"));
                tr.setNum(rs.getString("NUM"));
                tr.setUnit(rs.getString("UNIT"));
                tr.setTotalMoney(rs.getString("TOTALMONEY"));
                tr.setQuotation_code(rs.getString("QUOTATION_CODE"));
                tr.setUserId(rs.getString("userId"));
                tr.setInvoice_status(rs.getString("INVOICE_STATUS"));
                tr.setAccount_number(rs.getString("ACCOUNT_NUMBER"));
                tr.setAccount_name(rs.getString("ACCOUNT_NAME"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//invoice dept daos
public List<InvoiceDeptModel> InvoiceDeptDAOs (DeptMustReceivedReq deptMustReceivedReq) {
    String sql;
//    deptMustReceivedReq.getKey_id() == null || deptMustReceivedReq.getKey_id().isEmpty()
    try{
        if (deptMustReceivedReq.getToKen().equals("UnCuQ8Dql7bSVS9LcDfMWmA8asAtQLMF")){
            {
                sql = "select * from V_INVOICE_DEPT_MUST_RECIEVED ";
                log.info("SQL:" + sql);
            }
        }
        else {
            if (deptMustReceivedReq.getKey_id()!=null){
                sql = "select * from V_INVOICE_DEPT_MUST_RECIEVED WHERE KEY_ID='"+deptMustReceivedReq.getKey_id()+"'";
                log.info("SQL:" + sql);
            }else {
                sql = "select * from V_INVOICE_DEPT_MUST_RECIEVED WHERE userId='" + deptMustReceivedReq.getUserId() + "'";
                log.info("SQL:" + sql);
            }
        }

//        original
//        if (deptMustReceivedReq.getQuotation_code()!=null){
//            sql = "select * from V_INVOICE_DEPT_MUST_RECIEVED WHERE QUOTATION_CODE_tba='"+deptMustReceivedReq.getQuotation_code()+"'";
//            log.info("SQL:" + sql);
//        }else {
//            sql = "select * from V_INVOICE_DEPT_MUST_RECIEVED WHERE userId='" + deptMustReceivedReq.getUserId() + "'";
//            log.info("SQL:" + sql);
//        }
//    }
        return EBankJdbcTemplate.query(sql, new RowMapper<InvoiceDeptModel>() {
            @Override
            public InvoiceDeptModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvoiceDeptModel tr = new InvoiceDeptModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setDate_invoice(rs.getString("date_invoice"));
                tr.setPdfandpic(rs.getString("file"));
                tr.setGetmoney_status(rs.getString("new_GETMONEY_STATUS"));
                tr.setAmount_of_money(rs.getString("AMOUNT_OF_MONEY"));
//                tr.setUserId(rs.getString("userId"));
                tr.setDetail(rs.getString("detail"));
                tr.setQuotation_code(rs.getString("quotation_code_tbb"));
                tr.setInvoice_code(rs.getString("INVOICE_CODE"));
                tr.setDept_total(rs.getString("AMOUNT_MONEY"));
                tr.setUnit(rs.getString("UNIT"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//list name daos
public List<ListNameModel> ListNameDeptDAOs (DeptMustReceivedReq deptMustReceivedReq) {
    try{
        String sql="SELECT d.UNIT,l.KEY_ID,l.listName,l.quotation_code,l.amount,price,l.totalPrice,d.TOTALMONEY FROM TB_LIST_DMRC l join DEBT_MUST_RECEIVED d on l.quotation_code =d.QUOTATION_CODE where l.quotation_code = '"+deptMustReceivedReq.getQuotation_code()+"'";
        return EBankJdbcTemplate.query(sql, new RowMapper<ListNameModel>() {
            @Override
            public ListNameModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ListNameModel tr = new ListNameModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setListName(rs.getString("listName"));
                tr.setQuotation_code(rs.getString("quotation_code"));
                tr.setAmount(rs.getString("amount"));
                tr.setPrice(rs.getString("price"));
                tr.setTotalPrice(rs.getDouble("totalPrice"));
                tr.setTotalPrice1(rs.getDouble("TOTALMONEY"));
                tr.setUnit(rs.getString("UNIT"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//dept must received detail
public List<DeptMustReceivedModel> listDeptMustReceiveddetailDAOs (DeptMustReceivedReq deptMustReceivedReq) {
    String sql;
    try{
            sql = "select * from V_DEPT_MUST_RECEIVED WHERE KEY_ID='"+deptMustReceivedReq.getKey_id()+"'";
            log.info("SQL:" + sql);
        return EBankJdbcTemplate.query(sql, new RowMapper<DeptMustReceivedModel>() {
            @Override
            public DeptMustReceivedModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                DeptMustReceivedModel tr = new DeptMustReceivedModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setCustomerName(rs.getString("CUSTOMER_NAME"));
                tr.setCustomer_id(rs.getString("CUSTOMER_ID"));
                tr.setTopic(rs.getString("TOPIC"));
                tr.setTypeName(rs.getString("NAME_TYPE"));
                tr.setType_id(rs.getString("TYPE_ID"));
                tr.setCurrency(rs.getString("CURRENCY"));
                tr.setDate(rs.getString("DATE"));
                tr.setDue_date(rs.getString("DUE_DATE"));
                tr.setDate_create(rs.getString("DATE_CREATE"));
                tr.setStatus_wait_approve(rs.getString("STATUS_WAIT_APPROVE"));
                tr.setReference_number(rs.getString("REFERENCE_NUMBER"));
                tr.setLek_bai_sung(rs.getString("LEK_BAI_SUNG"));
                tr.setAmount_money(rs.getString("AMOUNT_MONEY"));
                tr.setQuotation(rs.getString("QUOTATION"));
                tr.setDocument_1(rs.getString("DOCUMENT_1"));
//                tr.setDocument_2(rs.getString("DOCUMENT_2"));
                tr.setDescription(rs.getString("DESCRIPTION"));
                tr.setNote(rs.getString("NOTE"));
                tr.setNum(rs.getString("NUM"));
                tr.setUnit(rs.getString("UNIT"));
                tr.setTotalMoney(rs.getString("TOTALMONEY"));
                tr.setQuotation_code(rs.getString("QUOTATION_CODE"));
                tr.setUserId(rs.getString("userId"));
                tr.setInvoice_status(rs.getString("INVOICE_STATUS"));
                tr.setAccount_number(rs.getString("ACCOUNT_NUMBER"));
                tr.setAccount_name(rs.getString("ACCOUNT_NAME"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//his customer pay
public List<CustomerHisPayModel> DeptMustReceivedHistoryCustormerDAOs (DeptMustReceivedReq deptMustReceivedReq) {
    String sql;
    try{
        sql = "select * from V_CUSTOMER_HIS_PAY WHERE userId='"+deptMustReceivedReq.getUserId()+"' and CUSTOMER_ID='"+deptMustReceivedReq.getCustomer_id()+"'";
        log.info("SQL:" + sql);
        return EBankJdbcTemplate.query(sql, new RowMapper<CustomerHisPayModel>() {
            @Override
            public CustomerHisPayModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomerHisPayModel tr = new CustomerHisPayModel();
                tr.setCustomerName(rs.getString("CUSTOMER_NAME"));
                tr.setDate(rs.getString("date_invoice"));
                tr.setMoney_T_jaiy(rs.getDouble("money_T_jaiy"));
                tr.setMoney_y_luea(rs.getDouble("money_y_luea"));
                tr.setCurrency(rs.getString("CURRENCY"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//search dept must recieved DAOs
public List<CustomerHisPayModel> SearchDeptMustReceivedHistoryCustormerDAOs (DeptMustReceivedReq deptMustReceivedReq) {
    String sql;
    try{
        if (deptMustReceivedReq.getStartDate()== null && deptMustReceivedReq.getEndDate() == null && deptMustReceivedReq.getCustomer_id()!=null)
        {
            sql = "select * from V_CUSTOMER_HIS_PAY where CUSTOMER_ID='"+deptMustReceivedReq.getCustomer_id()+"'";
            log.info("SQL1:" + sql);
        }
        else if(deptMustReceivedReq.getStartDate()!= null && deptMustReceivedReq.getEndDate() != null)
        {
            sql = "select * from V_CUSTOMER_HIS_PAY where date_invoice between '"+deptMustReceivedReq.getStartDate()+"' and '"+deptMustReceivedReq.getEndDate()+"'";
            log.info("SQL2:" + sql);
        }
        else if(deptMustReceivedReq.getCurrency()!= null)
        {
            sql = "select * from V_CUSTOMER_HIS_PAY where CURRENCY='"+deptMustReceivedReq.getCurrency()+"'";
            log.info("SQL3:" + sql);
        }else {
            sql = "select * from V_CUSTOMER_HIS_PAY where CURRENCY='"+deptMustReceivedReq.getCurrency()+"' and date_invoice between '"+deptMustReceivedReq.getStartDate()+"' and '"+deptMustReceivedReq.getEndDate()+"' and CUSTOMER_ID='"+deptMustReceivedReq.getCustomer_id()+"'";
            log.info("SQL4:" + sql);
        }
        return EBankJdbcTemplate.query(sql, new RowMapper<CustomerHisPayModel>() {
            @Override
            public CustomerHisPayModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomerHisPayModel tr = new CustomerHisPayModel();
                tr.setCustomerName(rs.getString("CUSTOMER_NAME"));
                tr.setDate(rs.getString("date_invoice"));
                tr.setMoney_T_jaiy(rs.getDouble("money_T_jaiy"));
                tr.setMoney_y_luea(rs.getDouble("money_y_luea"));
                tr.setCurrency(rs.getString("CURRENCY"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//    search Doc DAOs
public List<DocumentStorageModel> SearchlistDocDAOs (DocumentStorageReq documentStorageReq) {
    String sql;
    try{
        if (documentStorageReq.getToKen().equals("UnCuQ8Dql7bSVS9LcDfMWmA8asAtQLMF")){
            if (documentStorageReq.getBouang()!=null && documentStorageReq.getCompany()==null && documentStorageReq.getType()==null)
            {
                sql = "select * from V_DOC WHERE bouang='"+documentStorageReq.getBouang()+"'";
                log.info("SQL in bound:" + sql);
            }
            else if (documentStorageReq.getBouang()==null && documentStorageReq.getCompany()!=null && documentStorageReq.getType()==null)
            {
                sql = "select * from V_DOC WHERE company='"+documentStorageReq.getCompany()+"'";
                log.info("SQL out bound:" + sql);
            }
            else if (documentStorageReq.getBouang()==null && documentStorageReq.getCompany()==null && documentStorageReq.getType()!=null)
            {
                sql = "select * from V_DOC WHERE DOC_TYPE='"+documentStorageReq.getType()+"'";
                log.info("SQL out bound:" + sql);
            }
            else if(documentStorageReq.getUserIdoffinanceial().isEmpty())
            {
                sql = "select * from V_DOC";

                log.info("SQL:" + sql);
            }
            else {
                sql = "select * from V_DOC where saveById='"+documentStorageReq.getUserIdoffinanceial()+"'";
                log.info("SQL:" + sql);
            }
        }
        else {
            if (documentStorageReq.getBouang()!=null && documentStorageReq.getCompany()==null && documentStorageReq.getType()==null)
            {
                sql = "select * from V_DOC WHERE bouang='"+documentStorageReq.getBouang()+"' and TOKEN='"+documentStorageReq.getToKen()+"'";
                log.info("SQL in bound:" + sql);
            }
            else if (documentStorageReq.getBouang()==null && documentStorageReq.getCompany()!=null && documentStorageReq.getType()==null)
            {
                sql = "select * from V_DOC WHERE company='"+documentStorageReq.getCompany()+"'and TOKEN='"+documentStorageReq.getToKen()+"'";
                log.info("SQL out bound:" + sql);
            }
            else if (documentStorageReq.getBouang()==null && documentStorageReq.getCompany()==null && documentStorageReq.getType()!=null)
            {
                sql = "select * from V_DOC WHERE DOC_TYPE='"+documentStorageReq.getType()+"'and TOKEN='"+documentStorageReq.getToKen()+"'";
                log.info("SQL out bound:" + sql);
            }
            else {
                sql = "select * from V_DOC WHERE TOKEN='"+documentStorageReq.getToKen()+"'";
                log.info("normal SQL:" + sql);
            }
        }


        return EBankJdbcTemplate.query(sql, new RowMapper<DocumentStorageModel>() {
            @Override
            public DocumentStorageModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                DocumentStorageModel tr = new DocumentStorageModel();
                tr.setPdf(rs.getString("PDF"));
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setBranchUser(rs.getString("BRANCH_USER"));
                tr.setDateCreate(rs.getString("DATECREATE"));
                tr.setDocType(rs.getString("DOC_TYPE"));
                tr.setInboundnumber(rs.getString("INBOUNDNUMBER"));
                tr.setOutboundnumber(rs.getString("OUTBOUNDNUMBER"));
                tr.setDateExpDoc(rs.getString("DateExpDoc"));
                tr.setClassofdocs(rs.getString("CLASSOFDOCS"));
                tr.setBound(rs.getString("BOUND"));
                tr.setContent_doc(rs.getString("CONTENT_DOC"));
                tr.setWhocarrydoc(rs.getString("WHOCARRYDOC"));
                tr.setEtc(rs.getString("ETC"));
                tr.setLektee(rs.getString("LEKTEE"));
                tr.setDatetakein(rs.getString("DateTakeIn"));
                tr.setCompany(rs.getString("company"));
                tr.setBouang(rs.getString("bouang"));
                tr.setStatus(rs.getString("STATUS"));
                tr.setInside(rs.getString("inside"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//    all List of hole DAOs
public List<DataHoleModel> AlllistOfHoleDAOs (DataHoleReq dataHoleReq) {
    String sql;
    try{
        sql = "select * from DATA_HOLE where branch_id= '"+dataHoleReq.getBranch_id()+"'";
        return EBankJdbcTemplate.query(sql, new RowMapper<DataHoleModel>() {
            @Override
            public DataHoleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                DataHoleModel tr = new DataHoleModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setPic(rs.getString("pic"));
                tr.setHoeNumber(rs.getString("hole_number"));
                tr.setDataColler(rs.getString("data_Coller"));
                tr.setFull_Name_Hole_number(rs.getString("full_Name_Hole_number"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//show result of survey DAOs
public List<ResultOfSurveyModel> AllResultOfSurveyDAOs (DataHoleReq dataHoleReq) {
    String sql;
    try{
        sql = "select * from RESULT_SURVEY where branch_id= '"+dataHoleReq.getBranch_id()+"'";
        return EBankJdbcTemplate.query(sql, new RowMapper<ResultOfSurveyModel>() {
            @Override
            public ResultOfSurveyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResultOfSurveyModel tr = new ResultOfSurveyModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setFile(rs.getString("files"));
                tr.setType(rs.getString("type"));
                tr.setName(rs.getString("name"));
                tr.setDateInsert(rs.getString("dateInsert"));
                tr.setNameDetail(rs.getString("nameDetail"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//show task DAOs
public List<TaskModel> AllTaskDAOs (TaskReq taskReq ) {
    String sql;
    try{
        sql = "select * from V_TASKS where toKen= '"+taskReq.getToKen()+"'";
        return EBankJdbcTemplate.query(sql, new RowMapper<TaskModel>() {
            @Override
            public TaskModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                TaskModel tr = new TaskModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setTopic_task(rs.getString("TOPIC_TASK"));
                tr.setParent(rs.getString("SUB_TASK"));
                tr.setStartDate(rs.getString("START_DATE"));
                tr.setEndDate(rs.getString("END_DATE"));
                tr.setDuration(rs.getString("DURATION"));
                tr.setBranch_name(rs.getString("B_NAME"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//show pic of bor
public List<PicOfBorModel> PicOfSurveyDAOs (DataHoleReq dataHoleReq) {
    String sql;
    try{
        //serch by folder name
        if (dataHoleReq.getFolderName()==null && dataHoleReq.getStartDate()==null && dataHoleReq.getEndDate()==null )
        {
            sql = "select * from TB_PICTURE where branch_id='"+dataHoleReq.getBranch_id()+"'";
        }
        else if (dataHoleReq.getFolderName()==null && dataHoleReq.getStartDate()!=null && dataHoleReq.getEndDate()!=null )
        {
            sql = "select * from TB_PICTURE where branch_id='"+dataHoleReq.getBranch_id()+"' AND dateCreate between '"+dataHoleReq.getStartDate()+"' AND '"+dataHoleReq.getEndDate()+"' ";
        }
        else {
            sql = "select * from TB_PICTURE where branch_id='"+dataHoleReq.getBranch_id()+"' AND folderName = '"+dataHoleReq.getFolderName()+"' AND dateCreate between '"+dataHoleReq.getStartDate()+"' AND '"+dataHoleReq.getEndDate()+"' ";
        }
        log.info("SQL select : "+sql);
//        sql = "select * from TB_PICTURE where branch='"+dataHoleReq.getBranch()+"'";
        return EBankJdbcTemplate.query(sql, new RowMapper<PicOfBorModel>() {
            @Override
            public PicOfBorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                PicOfBorModel tr = new PicOfBorModel();
//                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setPic(rs.getString("pic"));
//                tr.setUserId(rs.getString("userId"));
//                tr.setBranch(rs.getString("branch"));
                tr.setFolderName(rs.getString("folderName"));
                tr.setDateCreate(rs.getString("dateCreate"));

                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//show result of survey by id
public List<ResultOfSurveyModel> AllResultOfSurveyByIdDAOs (DataHoleReq dataHoleReq) {
    String sql;
    try{
        sql = "select * from RESULT_SURVEY where KEY_ID= '"+dataHoleReq.getKey_id()+"'";
        return EBankJdbcTemplate.query(sql, new RowMapper<ResultOfSurveyModel>() {
            @Override
            public ResultOfSurveyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResultOfSurveyModel tr = new ResultOfSurveyModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setFile(rs.getString("files"));
                tr.setType(rs.getString("type"));
                tr.setName(rs.getString("name"));
                tr.setDateInsert(rs.getString("dateInsert"));
                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//show hole data by Key_id
public List<DataHoleModel> AlllistOfHoleByKeyIdDAOs (DataHoleReq dataHoleReq) {
    String sql;
    try{
        sql = "select * from DATA_HOLE where KEY_ID= '"+dataHoleReq.getKey_id()+"'";
        return EBankJdbcTemplate.query(sql, new RowMapper<DataHoleModel>() {
            @Override
            public DataHoleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                DataHoleModel tr = new DataHoleModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setPic(rs.getString("pic"));
                tr.setHoeNumber(rs.getString("hole_number"));
                tr.setDataColler(rs.getString("data_Coller"));
                tr.setFull_Name_Hole_number(rs.getString("full_Name_Hole_number"));

                return tr ;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//search doc

//    full accese permission admin bigbos sis nok
//public List<DocumentStorageModel> listDocumentAdmin (DocumentStorageReq documentStorageReq) {
//    try{
//           String  SQL ="select * from DOCUMENT_STORAGE";
//            log.info("SQL:"+SQL);
//        return EBankJdbcTemplate.query(SQL, new RowMapper<DocumentStorageModel>() {
//            @Override
//            public DocumentStorageModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//                DocumentStorageModel tr = new DocumentStorageModel();
//                tr.setPdf(rs.getString("PDF"));
//                tr.setKey_id(rs.getString("KEY_ID"));
//                tr.setBranchUser(rs.getString("BRANCH_USER"));
//                tr.setDateCreate(rs.getString("DATECREATE"));
//                tr.setDocType(rs.getString("DOC_TYPE"));
//                return tr ;
//            }
//        });
//    }catch (Exception e){
//        e.printStackTrace();
//    }
//    return null;
//}
//    show document detail
    public List<DocumentStorageModel> ShowDocumentDetailDAOs (DocumentStorageReq documentStorageReq) {

        try{
            String SQL ="select * from V_DOC WHERE KEY_ID='"+documentStorageReq.getKey_id()+"' ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<DocumentStorageModel>() {
                @Override
                public DocumentStorageModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    DocumentStorageModel tr = new DocumentStorageModel();
                    tr.setPdf(rs.getString("PDF"));
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setBranchUser(rs.getString("BRANCH_USER"));
                    tr.setDateCreate(rs.getString("DATECREATE"));
                    tr.setDocType(rs.getString("DOC_TYPE"));
                    tr.setInboundnumber(rs.getString("INBOUNDNUMBER"));
                    tr.setOutboundnumber(rs.getString("OUTBOUNDNUMBER"));
                    tr.setDateExpDoc(rs.getString("DateExpDoc"));
                    tr.setClassofdocs(rs.getString("CLASSOFDOCS"));
                    tr.setContent_doc(rs.getString("CONTENT_DOC"));
                    tr.setWhocarrydoc(rs.getString("WHOCARRYDOC"));
                    tr.setEtc(rs.getString("ETC"));
                    tr.setLektee(rs.getString("LEKTEE"));
                    tr.setDatetakein(rs.getString("DateTakeIn"));
                    tr.setCompany(rs.getString("company"));
                    tr.setBouang(rs.getString("bouang"));
                    tr.setStatus(rs.getString("STATUS"));
                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    delete Document
public int delDocumentDAOs (DocumentStorageReq documentStorageReq) {
    String keyId = documentStorageReq.getKey_id();
    int i =0;
    try {
        String SQL = "delete from DOCUMENT_STORAGE where KEY_ID = '" + keyId +"'";
        log.info("SQL:"+SQL);
        i= EBankJdbcTemplate.update(SQL);
    }catch (Exception e){
        e.printStackTrace();
        return i;
    }
    return i;
}
// del dept must recieve DAOs
public int delDEptMustReceivedDAOs (DeptMustReceivedReq deptMustReceivedReq) {
    String keyId = deptMustReceivedReq.getKey_id();
    int i =0;
    String SQL="";
    try {
        if (deptMustReceivedReq.getToKen().equals("UnCuQ8Dql7bSVS9LcDfMWmA8asAtQLMF"))
        {
            SQL = "delete from DEBT_MUST_RECEIVED where KEY_ID ='" + keyId + "'";
        } else {
         SQL = "delete from DEBT_MUST_RECEIVED where KEY_ID = '" + keyId + "' AND STATUS_WAIT_APPROVE ='N'";
        log.info("SQL:" + SQL);
    }
        i= EBankJdbcTemplate.update(SQL);
    }catch (Exception e){
        e.printStackTrace();
        return i;
    }
    return i;
}
//delete hole DAOs
public int delHoledataDAOs (DataHoleReq dataHoleReq) {
    String keyId = dataHoleReq.getKey_id();
    int i =0;
    try {
        String SQL = "delete from DATA_HOLE where KEY_ID = '" + keyId +"'";
        log.info("SQL:"+SQL);
        i= EBankJdbcTemplate.update(SQL);
    }catch (Exception e){
        e.printStackTrace();
        return i;
    }
    return i;
}
//delete result of survey
public int delResultOfSurveyDAOs (DataHoleReq dataHoleReq) {
    String keyId = dataHoleReq.getKey_id();
    int i =0;
    try {
        String SQL = "delete from RESULT_SURVEY where KEY_ID = '" + keyId +"'";
        log.info("SQL:"+SQL);
        i= EBankJdbcTemplate.update(SQL);
    }catch (Exception e){
        e.printStackTrace();
        return i;
    }
    return i;
}
//delete bouang
public int delBouangDAOs (BouangReq bouangReq) {
    int i =0;
    try {
        String SQL = "delete from TB_BOUANG where KEY_ID = '" +bouangReq.getKey_id()+"'";
        log.info("SQL:"+SQL);
        i= EBankJdbcTemplate.update(SQL);
    }catch (Exception e){
        e.printStackTrace();
        return i;
    }
    return i;
}
}
