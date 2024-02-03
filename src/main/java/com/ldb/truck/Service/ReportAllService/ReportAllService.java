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
        double totalPriceFuel =0.0;
//me do new
        double totalstaff02_payAll =0.0;
        double totalstaff02_beforepay =0.0;
        double allLaiyJaiy=0.0;

        DecimalFormat numfm = new DecimalFormat("###,###.###");
        List<ReportAll> listData = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = reportStaffServiceDao.ListAllReportProduct(reportAllReq);

            double sumNummun =  listData.stream().map(ReportAll::getTotalNummun).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalBiaLieng=  listData.stream().map(ReportAll::getTotalBiaLieng).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtodtalLaiyJaiyFrist =  listData.stream().map(ReportAll::getTodtalLaiyJaiyFrist).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtodtodtalLaiyJaiySecond =  listData.stream().map(ReportAll::getTodtalLaiyJaiySecond).collect(Collectors.summingDouble(Double::doubleValue));

// me de new
            double sumtotalstaff02_payAll=  listData.stream().map(ReportAll::getTotalstaff02_payAll).collect(Collectors.summingDouble(Double::doubleValue));
            double sumtotalstaff02_beforepay =  listData.stream().map(ReportAll::getTotalstaff02_payAll).collect(Collectors.summingDouble(Double::doubleValue));
//            double sumtotalstaff02_notpay =  listData.stream().map(ReportAll::getStaff02_notpay).collect(Collectors.summingDouble(Double::doubleValue));
// Total price fuel
            double sumtotalPriceFuel =  listData.stream().map(ReportAll::getTotalPriceFuel).collect(Collectors.summingDouble(Double::doubleValue));
// Total price nammun
            double sumtotalPriceNummun =  listData.stream().map(ReportAll::getTotalPriceNummun).collect(Collectors.summingDouble(Double::doubleValue));
// all laiy jaiy bialieng+price nummun
            double sumallLaiyJaiy =  listData.stream().map(ReportAll::getAllLaiyJaiy).collect(Collectors.summingDouble(Double::doubleValue));
            double getAllTotalLaijai =  listData.stream().map(ReportAll::getAllLaiyJaiyOut).collect(Collectors.summingDouble(Double::doubleValue));
            double getAllTotalLaijaiFrist =  listData.stream().map(ReportAll::getAllLaiyJaiyFrist).collect(Collectors.summingDouble(Double::doubleValue));
//#######################____sum fee waste____#########################
//#######################____sum fee waste____#########################
            sumFooterGroup restFooter = new sumFooterGroup();

            restFooter.setTotalNummun(numfm.format(sumNummun));
            restFooter.setTotalBiaLieng(numfm.format(sumtotalBiaLieng));
            restFooter.setTodtalLaiyJaiyFrist(numfm.format(sumtodtalLaiyJaiyFrist));
            restFooter.setTodtalLaiyJaiySecond(numfm.format(sumtodtodtalLaiyJaiySecond));
// all laiy jaiy bialieng+price nummun
            restFooter.setAllLaiyJaiy(numfm.format(sumallLaiyJaiy));
//  me do new
            restFooter.setTotalstaff02_payAll(numfm.format(sumtotalstaff02_payAll));
            restFooter.setTotalstaff02_beforepay(numfm.format(sumtotalstaff02_beforepay));
//            restFooter.setStaff02_notpay(sumtotalstaff02_notpay);

// Total price nammun
//            double cal = sumtotalPriceNummun*sumNummun;
// Total price fuel
            restFooter.setTotalPriceFuel(numfm.format(sumtotalPriceFuel));
            restFooter.setTotalPriceNammun(numfm.format(sumtotalPriceNummun));
            //=================================cal lai jaiy=================
            restFooter.setLaiJaiyOutFrist(numfm.format(getAllTotalLaijaiFrist));
            restFooter.setLaiJaiyOutTotal(numfm.format(getAllTotalLaijai));
            //=================================cal lai jaiy=================

            result.setSumFooter(restFooter);
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
