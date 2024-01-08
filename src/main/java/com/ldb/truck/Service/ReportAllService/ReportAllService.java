package com.ldb.truck.Service.ReportAllService;

import com.ldb.truck.Dao.ReportAllDao.ReportAllServiceDao;
import com.ldb.truck.Model.Login.Pay.PrintBillPayment;
import com.ldb.truck.Model.Login.Report.sumFooterGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportAllRes;
import com.ldb.truck.Model.Login.Report.ReportAll;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        double totalBiaLieng =0.0;
        double totalNummun =0.0;
        double todtalLaiyJaiyFrist =0.0;
        double todtalLaiyJaiySecond =0.0;
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        List<ReportAll> listData = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = reportStaffServiceDao.ListAllReportProduct(reportAllReq);

            double sumNummun =  listData.stream().map(ReportAll::getTotalNummun).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalBiaLieng=  listData.stream().map(ReportAll::getTotalBiaLieng).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtodtalLaiyJaiyFrist =  listData.stream().map(ReportAll::getTodtalLaiyJaiyFrist).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtodtodtalLaiyJaiySecond =  listData.stream().map(ReportAll::getTodtalLaiyJaiySecond).collect(Collectors.summingDouble(Double::doubleValue));
            sumFooterGroup restFooter = new sumFooterGroup();

            restFooter.setTotalNummun(numfm.format(sumNummun));
            restFooter.setTotalBiaLieng(numfm.format(sumtotalBiaLieng));
            restFooter.setTodtalLaiyJaiyFrist(numfm.format(sumtodtalLaiyJaiyFrist));
            restFooter.setTodtalLaiyJaiySecond(numfm.format(sumtodtodtalLaiyJaiySecond));

            result.setSumFooter(restFooter);
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
