package com.ldb.truck.Service.ReportAllService;

import com.ldb.truck.Dao.ReportAllDao.ReportAllServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportAllRes;
import com.ldb.truck.Model.Login.Report.ReportAll;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportAllService {
    @Autowired
    ReportAllServiceDao reportStaffServiceDao;
    public ReportAllRes ListReportAll_Product(ReportAllReq reportAllReq){
        List<ReportAll> listData = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = reportStaffServiceDao.ListAllReportCustomer(reportAllReq);
            result.setData(listData);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e ){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    public ReportAllRes ListReportAll_Customer(ReportAllReq reportAllReq){
        List<ReportAll> listData = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = reportStaffServiceDao.ListAllReportCustomer(reportAllReq);
            result.setData(listData);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e ){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //---product
    public ReportAllRes ListReportAllProduct(ReportAllReq reportAllReq){
        List<ReportAll> listData = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = reportStaffServiceDao.ListAllReportProduct(reportAllReq);

            result.setData(listData);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e ){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }

}
