package com.ldb.truck.Dao.documentStorageDAOs;

import com.ldb.truck.Model.Login.Dept_Must_Receive.*;
import com.ldb.truck.Model.Login.DocumentStorage.*;

import java.text.ParseException;
import java.util.List;

public interface DocumentInterface {
    public List<ListNameModel> ListNameDeptDAOs (DeptMustReceivedReq deptMustReceivedReq);
    public int DeptMustRecievedInsertArray(List<DeptMustReceivedReq> deptMustReceivedReq);
    public List<CustomerHisPayModel> SearchDeptMustReceivedHistoryCustormerDAOs (DeptMustReceivedReq deptMustReceivedReq);
    public int InvoiceDeptInsertDAOs (InvoiceDeptReq invoiceDeptReq) throws ParseException;
    public int AcceptupdateStatusDAOs(DeptMustReceivedReq deptMustReceivedReq);
    public List<CustomerHisPayModel> DeptMustReceivedHistoryCustormerDAOs (DeptMustReceivedReq deptMustReceivedReq);
    public int delDEptMustReceivedDAOs (DeptMustReceivedReq deptMustReceivedReq);
    public List<DeptMustReceivedModel> listDeptMustReceiveddetailDAOs (DeptMustReceivedReq deptMustReceivedReq);
    public int DeptMustReceivedUpdateDAOs (DeptMustReceivedReq deptMustReceivedReq) throws ParseException;
    public List<DeptMustReceivedModel> listDeptMustReceivedDAOs (DeptMustReceivedReq deptMustReceivedReq);
    public int DeptMustReceivedInsertDAOs (DeptMustReceivedReq deptMustReceivedReq) throws ParseException;
    public int InsertDocumentDAOs (DocumentStorageReq documentStorageReq) throws ParseException;
    public List<DocumentStorageModel> listDocDAOs (DocumentStorageReq documentStorageReq);
    public int delDocumentDAOs (DocumentStorageReq documentStorageReq);
    public List<DocumentStorageModel> ShowDocumentDetailDAOs (DocumentStorageReq documentStorageReq);
//    public List<DocumentStorageModel> listDocumentAdmin (DocumentStorageReq documentStorageReq);

    public List<DataHoleModel> AlllistOfHoleDAOs (DataHoleReq dataHoleReq);
    public List<DataHoleModel> AlllistOfHoleByKeyIdDAOs (DataHoleReq dataHoleReq);
    public int delHoledataDAOs (DataHoleReq dataHoleReq);
    public int InsertResultOfSurveyDAOs (DataHoleReq dataHoleReq) throws ParseException;
    public List<ResultOfSurveyModel> AllResultOfSurveyDAOs (DataHoleReq dataHoleReq);
    public List<ResultOfSurveyModel> AllResultOfSurveyByIdDAOs (DataHoleReq dataHoleReq);
    public int delResultOfSurveyDAOs (DataHoleReq dataHoleReq);
    public int InsertBouangDAOs(BouangReq bouangReq);
    public List<BouangModel> ListBouangAllDAOs(BouangReq bouangReq);
    public int updateBouangDAOs(BouangReq bouangReq);
    public int delBouangDAOs (BouangReq bouangReq);
    public int UpdateDocumentDAOs (DocumentStorageReq documentStorageReq) throws ParseException;
    public List<BouangModel> ListBouangAllDAOSpecial (BouangReq bouangReq);
}

