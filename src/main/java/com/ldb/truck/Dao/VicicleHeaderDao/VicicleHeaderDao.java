package com.ldb.truck.Dao.VicicleHeaderDao;

import com.ldb.truck.Model.Login.CarOffice.*;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.ldb.truck.Model.Login.Report.ReportHeader;

import java.text.ParseException;
import java.util.List;
public interface VicicleHeaderDao {
    public List<VicicleHeader> listVicicleHeader (VicicleHeaderReq vicicleHeaderReq);
    public List<VicicleHeader> listVicicleHeaderByID (VicicleHeaderReq vicicleHeaderReq);
    public List<CarOfficeModel> listCarOfficeDAOs (CarOfficeReq carOfficeReq, String role, String branch, String bor_no );
    public List<CarBorModel> listCarBorDao(CarBorReq CarBorReq, String role, String  borNoClient,String borNoProfile);
    public List<CarPaidModel> listCarDaoPaid (CarOfficeReq carOfficeReq);
    public List<CarOfficeModel> listLodDaoOfficeDAOs (CarOfficeReq carOfficeReq);
    public List<CarOfficeModel> listCarOfficeDAOsDetailById (CarOfficeReq carOfficeReq);
    public int saveVicicleHeader (VicicleHeaderReq vicicleHeaderReq) throws ParseException;
    public int updateVicicleHeader (VicicleHeaderReq vicicleHeaderReq) throws ParseException;
    public int updateVicicleHeaderUppicHaveData (VicicleHeaderReq vicicleHeaderReq) throws ParseException;
    public int delVicicleHeader (VicicleHeaderReq vicicleHeaderReq);
    public int delCarOfficeDAOs (CarOfficeReq carOfficeReq);

    public int InsertCarOfficeDAOs (CarOfficeReq carOfficeReq) throws ParseException;
    public int PayCarDao (PaidCarDaoReq paidCarDaoReq) throws ParseException;
    public int UpdateCarOfficeDAOs (CarOfficeReq carOfficeReq) throws ParseException;
    public int updateCarOfficeUppicHaveData (CarOfficeReq carOfficeReq) throws ParseException;

    public int saveHeaderHistroty(VicicleHeaderReq vicicleHeaderReq);
    List<VicicleHeader> ReportHistoryHeader(ReportAllReq vicicleHeaderReq);
    List <VicicleHeader> listVicicleHeaderCombox1(VicicleHeaderReq vicicleHeaderReq);
    List<ReportHeader> listReportHeader(ReportHeaderReq reportHeaderReq);
    public int UpdateCarOfficenoticeStatusDAOs (CarOfficeReq carOfficeReq);

}
