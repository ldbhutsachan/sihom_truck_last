package com.ldb.truck.Service.ReportAllService;

import com.ldb.truck.Dao.ReportAllDao.ReportAllServiceDao;
import com.ldb.truck.Model.Login.Pay.PrintBillPayment;
import com.ldb.truck.Model.Login.Report.sumFooterGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger(ReportAllService.class);

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
        double totalPriceFuel =0.0;
        double totalstaff02_payAll =0.0;
        double totalstaff02_beforepay =0.0;
        double allLaiyJaiy=0.0;
        double runningTotal=0.0;
        List<ReportAll> groupListData = new ArrayList<>();
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        List<ReportAll> listData = new ArrayList<>();
        List<ReportAll> listData02 = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = reportStaffServiceDao.ListAllReportProduct(reportAllReq);
            listData02 = reportStaffServiceDao.ListAllReportProductType02(reportAllReq);
            //================================sum footer=================================
            double sumNummun =  listData02.stream().map(ReportAll::getTotalNummun).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalBiaLieng=  listData02.stream().map(ReportAll::getTotalBiaLieng).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtodtalLaiyJaiyFrist =  listData02.stream().map(ReportAll::getTodtalLaiyJaiyFrist).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtodtodtalLaiyJaiySecond =  listData02.stream().map(ReportAll::getTodtalLaiyJaiySecond).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalstaff02_payAll=  listData02.stream().map(ReportAll::getTotalstaff02_payAll).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalstaff02_beforepay =  listData02.stream().map(ReportAll::getTotalstaff02_payAll).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalPriceFuel =  listData02.stream().map(ReportAll::getTotalPriceFuel).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalPriceNummun =  listData02.stream().map(ReportAll::getTotalPriceNummun).collect(Collectors.summingDouble(Double::doubleValue));
            double sumallLaiyJaiy =  listData02.stream().map(ReportAll::getAllLaiyJaiy).collect(Collectors.summingDouble(Double::doubleValue));
            double getAllTotalLaijai =  listData02.stream().map(ReportAll::getAllLaiyJaiyOut).collect(Collectors.summingDouble(Double::doubleValue));
            double getAllTotalLaijaiFrist =  listData02.stream().map(ReportAll::getAllLaiyJaiyFrist).collect(Collectors.summingDouble(Double::doubleValue));
            double sumRunningTotal =  listData02.stream().map(ReportAll::getRunningTotal).collect(Collectors.summingDouble(Double::doubleValue));
            sumFooterGroup restFooter = new sumFooterGroup();
            restFooter.setRunningTotal(numfm.format(sumRunningTotal));
            restFooter.setTotalNummun(numfm.format(sumNummun));
            restFooter.setTotalBiaLieng(numfm.format(sumtotalBiaLieng));
            restFooter.setTodtalLaiyJaiyFrist(numfm.format(sumtodtalLaiyJaiyFrist));
            restFooter.setTodtalLaiyJaiySecond(numfm.format(sumtodtodtalLaiyJaiySecond));
            restFooter.setAllLaiyJaiy(numfm.format(sumallLaiyJaiy));
            restFooter.setTotalstaff02_payAll(numfm.format(sumtotalstaff02_payAll));
            restFooter.setTotalstaff02_beforepay(numfm.format(sumtotalstaff02_beforepay));
            restFooter.setTotalPriceFuel(numfm.format(sumtotalPriceFuel));
            restFooter.setTotalPriceNammun(numfm.format(sumtotalPriceNummun));
            restFooter.setLaiJaiyOutFrist(numfm.format(getAllTotalLaijaiFrist));
            restFooter.setLaiJaiyOutTotal(numfm.format(getAllTotalLaijai));
            result.setSumFooter(restFooter);
            //================================sum footer=================================
            result.setData(listData);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e ){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found ");
        }
        return result;
    }

}
