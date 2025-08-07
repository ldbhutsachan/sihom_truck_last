package com.ldb.truck.Service.ReportAllService;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.ReportAllDao.ReportAllServiceDao;
import com.ldb.truck.Entity.Stock.StockRequest;
import com.ldb.truck.Model.Login.ForShowTotalOil.ForShowTotalOilPaid;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.Report.*;
import com.ldb.truck.Model.Login.Report.Bialieng.sumfooterOilPaid;
import com.ldb.truck.Model.ReportAllStock.ReportAllStock;
import com.ldb.truck.Model.ReportAllStock.ReportAllStockInOut;
import com.ldb.truck.Model.ReportAllStock.ReportAllStockInOutRes;
import com.ldb.truck.Model.ReportAllStock.ReportAllStockRes;
import com.ldb.truck.Model.ReportInoutItem.ReportInoutItemGroup;
import com.ldb.truck.Model.ReportItemInOutModel.ReportItemInOutModelReq;
import com.ldb.truck.Model.ReportItemInOutModel.ReportItemInOutModelResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportAllService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(ReportAllService.class);

    @Autowired
    ReportAllServiceDao reportStaffServiceDao;
    public ReportAllRes ListReportAll_Product(ReportAllReq reportAllReq){
        log.info("toKen=======================:"+reportAllReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(reportAllReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        reportAllReq.setUserId(userId);
        reportAllReq.setBranch(userBranchNo);
        //====================================================================

        List<ReportAll> listData = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = reportStaffServiceDao.ListAllReportCustomer(reportAllReq);
//  new          listData = reportStaffServiceDao.ListAllReportProduct(reportAllReq);
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
        log.info("toKen=======================:"+reportAllReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(reportAllReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        reportAllReq.setUserId(userId);
        reportAllReq.setBranch(userBranchNo);
        //====================================================================

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
        log.info("toKen=======================:"+reportAllReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(reportAllReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        reportAllReq.setUserId(userId);
        reportAllReq.setBranch(userBranchNo);
        //====================================================================
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

            restFooter.setTotalBiaLiengAndlaiJaiyOutFrist(numfm.format(sumtotalBiaLieng+getAllTotalLaijaiFrist));
            restFooter.setBiaOutWasted(numfm.format(sumtotalBiaLieng+getAllTotalLaijaiFrist+sumRunningTotal));

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
// service report fuel
public ReportFuelRes ReportFuealStation(ReportAllReq reportAllReq){
    log.info("toKen=======================:"+reportAllReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(reportAllReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    reportAllReq.setUserId(userId);
    reportAllReq.setBranch(userBranchNo);
    //====================================================================

    double totalNummun =0.0;
    double totalPriceFuel =0.0;

    List<ReportFuel> groupListData = new ArrayList<>();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    List<ReportFuel> listData = new ArrayList<>();
    ReportFuelRes result = new ReportFuelRes();
    try {
        listData = reportStaffServiceDao.ReportFuealDao(reportAllReq);
        //================================sum footer=================================

        double sumNummun =  listData.stream().map(ReportFuel::getTotalLidFuel).collect(Collectors.summingDouble(Double::doubleValue));
        double sumtotalPriceFuel =  listData.stream().map(ReportFuel::getTotalPrizeFuelAll).collect(Collectors.summingDouble(Double::doubleValue));

        sumFooterGroupFuel restFooter = new sumFooterGroupFuel();
        restFooter.setTotalLidFuel(numfm.format(sumNummun));
        restFooter.setTotalPriceFuel(numfm.format(sumtotalPriceFuel));

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
// service report fuel

//    show total oils paid
public ShowOilPaidRes ShowTotalOilPaidServiece (ReportAllReq reportAllReq){
    log.info("toKen=======================:"+reportAllReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(reportAllReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    reportAllReq.setUserId(userId);
    reportAllReq.setBranch(userBranchNo);
    //====================================================================

//    double totalNummun =0.0;
//    double totalPriceFuel =0.0;

//    List<ReportFuel> groupListData = new ArrayList<>();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    List<ForShowTotalOilPaid> data = new ArrayList<>();
    ShowOilPaidRes result = new ShowOilPaidRes();
    try {
        data = reportStaffServiceDao.ShowOilPaid(reportAllReq);
        //================================sum footer=================================

        double sumtotalOilPaid =  data.stream().map(ForShowTotalOilPaid::getTotalOilPaid).collect(Collectors.summingDouble(Double::doubleValue));
//        double sumtotalPriceFuel =  listData.stream().map(ReportFuel::getTotalPrizeFuelAll).collect(Collectors.summingDouble(Double::doubleValue));
//
        sumfooterOilPaid restFooter = new sumfooterOilPaid();
        restFooter.setSumtotalOilPaid(numfm.format(sumtotalOilPaid));
//
        result.setSumFooter(restFooter);
        //================================sum footer=================================
        result.setData(data);
        result.setStatus("00");
        result.setMessage("success");
    }catch (Exception e ){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("data not found ");
    }
    return result;
}
    public ReportAllStockInOutRes getReportDetailDailyStock(ReportItemInOutModelReq stockRequest,String role,String borNo) {

        ReportAllStockInOutRes response = new ReportAllStockInOutRes();
        try {
            ReportInoutItemGroup groupFooter = new ReportInoutItemGroup();
            int totalRaisedAmt = 0;
            int totalInAmt = 0;
            int totalCloseAmt = 0;
            List<ReportAllStockInOut> resultData = new ArrayList<>();
            ReportAllStockInOut summary = new ReportAllStockInOut();

            int totalOutAmt = 0;

            int totalAmt = 0;
            List<ReportAllStockInOut> rsListData = reportStaffServiceDao.getReportDetailDailyStock(stockRequest, role, borNo);
            Map<String, Map<String, List<ReportAllStockInOut>>> grouped =
                    rsListData.stream().collect(Collectors.groupingBy(
                            ReportAllStockInOut::getItemId,
                            Collectors.groupingBy(ReportAllStockInOut::getDateOut)
                    ));

           for(ReportAllStockInOut base : rsListData){
               summary = new ReportAllStockInOut();

                  int raisedAmt = base.getRaisedAmt() ;
                  int inAmt = base.getInAmt();
                  int closeInAmt = base.getClosingAmt();

                  int raisedOutAmt = base.getRaisedOutAmt();
                  int outAmt =base.getOutAmt();
                  int closeOutAmt = base.getClosingOutAmt();
                  summary.setItemId(base.getItemId());
                  summary.setDateIn(base.getDateIn());
                  summary.setItemName(base.getItemName());
                  summary.setImage(base.getImage());
                  summary.setDateOut(base.getDateOut());
                  summary.setInByUser(base.getInByUser());
                  summary.setOutByUser(base.getOutByUser());
                  summary.setBorkey(base.getBorkey());
                  summary.setBorname(base.getBorname());
                  summary.setType(base.getUsingType());
                  summary.setUsingWith(base.getUsingWith());
                  summary.setUsingType(base.getUsingType());
                  summary.setUnit(base.getUnit());
                  summary.setDetailsId(base.getDetailsId());

                  summary.setRaisedAmt(raisedAmt);
                  summary.setInAmt(inAmt);
                  summary.setClosingAmt(closeInAmt);

                  summary.setRaisedOutAmt(raisedOutAmt);
                  summary.setOutAmt(outAmt);
                  summary.setClosingOutAmt(closeOutAmt);

                  summary.setCalTotal(totalAmt);


               resultData.add(summary);

           }

            groupFooter.setRaiseAmt(totalRaisedAmt);
            groupFooter.setInAmt(totalInAmt);
            groupFooter.setOutAmt(totalOutAmt);
            groupFooter.setCloseAmt(totalCloseAmt);

            if (!resultData.isEmpty()) {
                response.setStatus("00");
                response.setMessage("Success");
                response.setData(resultData);
                response.setGroupFooter(groupFooter);
            } else {
                response.setStatus("00");
                response.setMessage("Data Not Found !!");
            }

        } catch (Exception e) {
            log.error("Error in getReportDetailDailyStock", e);
            response.setStatus("EE");
            response.setMessage("Error Data !!!");
        }

        return response;
    }

//    public ReportAllStockInOutRes getReportDetailDailyStock(ReportItemInOutModelReq stockRequest,String role,String borNo) {
//
//        ReportAllStockInOutRes response = new ReportAllStockInOutRes();
//        try {
//          ReportInoutItemGroup groupFooter = new ReportInoutItemGroup();
//          int totalRaisedAmt = 0;
//          int totalInAmt = 0;
//          int totalCloseAmt = 0;
//
//
//            int totalOutAmt = 0;
//
//            int totalAmt = 0;
//          List<ReportAllStockInOut> rsListData = reportStaffServiceDao.getReportDetailDailyStock(stockRequest, role, borNo);
//          Map<String, Map<String, List<ReportAllStockInOut>>> grouped =
//                  rsListData.stream().collect(Collectors.groupingBy(
//                          ReportAllStockInOut::getItemId,
//                          Collectors.groupingBy(ReportAllStockInOut::getDateOut)
//                  ));
//          log.info("grouped:"+grouped);
//
//          List<ReportAllStockInOut> resultData = new ArrayList<>();
//
//          for (Map.Entry<String, Map<String, List<ReportAllStockInOut>>> itemEntry : grouped.entrySet()) {
//              String itemId = itemEntry.getKey();
//              Map<String, List<ReportAllStockInOut>> dateMap = itemEntry.getValue();
//
//              for (Map.Entry<String, List<ReportAllStockInOut>> dateEntry : dateMap.entrySet()) {
////                  String dateIn = dateEntry.getKey();
//                  List<ReportAllStockInOut> records = dateEntry.getValue();
//
//                  log.info("records:"+records);
//                  ReportAllStockInOut base = records.get(0);
//
//                  int raisedAmt = records.stream().mapToInt(ReportAllStockInOut::getRaisedAmt).sum();
//                  int inAmt = records.stream().mapToInt(ReportAllStockInOut::getInAmt).sum();
//                  int closeInAmt = records.stream().mapToInt(ReportAllStockInOut::getClosingAmt).sum();
//
//                  int raisedOutAmt = records.stream().mapToInt(ReportAllStockInOut::getRaisedOutAmt).sum();
//                  int outAmt = records.stream().mapToInt(ReportAllStockInOut::getOutAmt).sum();
//                  int closeOutAmt = records.stream().mapToInt(ReportAllStockInOut::getClosingOutAmt).sum();
//
//                  ReportAllStockInOut summary = new ReportAllStockInOut();
//                  summary.setItemId(base.getItemId());
//                  summary.setDateIn(base.getDateIn());
//                  summary.setItemName(base.getItemName());
//                  summary.setImage(base.getImage());
//                  summary.setDateOut(base.getDateOut());
//                  summary.setInByUser(base.getInByUser());
//                  summary.setOutByUser(base.getOutByUser());
//                  summary.setBorkey(base.getBorkey());
//                  summary.setBorname(base.getBorname());
//                  summary.setType(base.getUsingType());
//                  summary.setUsingWith(base.getUsingWith());
//                  summary.setUsingType(base.getUsingType());
//                  summary.setUnit(base.getUnit());
//                  summary.setDetailsId(base.getDetailsId());
//
//                  summary.setRaisedAmt(raisedAmt);
//                  summary.setInAmt(inAmt);
//                  summary.setClosingAmt(closeInAmt);
//
//                  summary.setRaisedOutAmt(raisedOutAmt);
//                  summary.setOutAmt(outAmt);
//                  summary.setClosingOutAmt(closeOutAmt);
//
//                  summary.setCalTotal(totalAmt);
//                  resultData.add(summary);
//              }
//          }
//
//
//          groupFooter.setRaiseAmt(totalRaisedAmt);
//          groupFooter.setInAmt(totalInAmt);
//          groupFooter.setOutAmt(totalOutAmt);
//          groupFooter.setCloseAmt(totalCloseAmt);
//
//            if (!resultData.isEmpty()) {
//                response.setStatus("00");
//                response.setMessage("Success");
//                response.setData(resultData);
//                response.setGroupFooter(groupFooter);
//            } else {
//                response.setStatus("00");
//                response.setMessage("Data Not Found !!");
//            }
//
//        } catch (Exception e) {
//            log.error("Error in getReportDetailDailyStock", e);
//            response.setStatus("EE");
//            response.setMessage("Error Data !!!");
//        }
//
//        return response;
//    }
    public ReportItemInOutModelResponse getTxnStock(ReportItemInOutModelReq stockRequest){
//update
        ReportItemInOutModelResponse resposne = new ReportItemInOutModelResponse();
        List<ReportAllStock> listData = new ArrayList<>();
        try {
            listData = reportStaffServiceDao.getTxnStock(stockRequest);
            if(listData.size() > 0){
                double sumRaisedAmt =  listData.stream().map(ReportAllStock::getTotal).collect(Collectors.summingInt(Integer::intValue));
                double sumInAmt =  listData.stream().map(ReportAllStock::getAmtIn).collect(Collectors.summingInt(Integer::intValue));
                double sumOutAmt =  listData.stream().map(ReportAllStock::getAmtOut).collect(Collectors.summingInt(Integer::intValue));
                double sumAmt =  listData.stream().map(ReportAllStock::getAmt).collect(Collectors.summingInt(Integer::intValue));
                double sumAmount =  listData.stream().map(ReportAllStock::getPrice).collect(Collectors.summingDouble(Double::doubleValue));
                double sumAmountIn =  listData.stream().map(ReportAllStock::getPriceIn).collect(Collectors.summingDouble(Double::doubleValue));
                double sumAmountOut =  listData.stream().map(ReportAllStock::getPriceOut).collect(Collectors.summingDouble(Double::doubleValue));
                resposne.setDataResponse(listData);
                resposne.setSumRaisedAmt(sumRaisedAmt);
                resposne.setSumInAmt(sumInAmt);
                resposne.setSumOutAmt(sumOutAmt);
                resposne.setSumAmt(sumAmt);
                resposne.setSumPrice(sumAmount);
                resposne.setSumPriceIn(sumAmountIn);
                resposne.setSumPriceOut(sumAmountOut);
                return resposne;
            }
            resposne.setStatus("00");
            resposne.setMessage("Data Not Found !!");
        }catch (Exception e){
            resposne.setStatus("EE");
            resposne.setMessage("Error Data !!!");
        }
        return resposne;
}


}
