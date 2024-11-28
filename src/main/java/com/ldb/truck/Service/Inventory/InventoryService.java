package com.ldb.truck.Service.Inventory;

import com.ldb.truck.Dao.Inventory.InventoryDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilModel;
import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilReq;
import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilRes;
import com.ldb.truck.Model.Login.CarOffice.FillOil.sumFooterGroupHisOil;
import com.ldb.truck.Model.Login.Inventory.CUR.ReportOfferPaperModel;
import com.ldb.truck.Model.Login.Inventory.CUR.ReportOfferPaperModelLAK;
import com.ldb.truck.Model.Login.Inventory.CUR.ReportOfferPaperModelTHB;
import com.ldb.truck.Model.Login.Inventory.Fix.*;
import com.ldb.truck.Model.Login.Inventory.Fix.FixReqListProve.ReqListOfFixModel;
import com.ldb.truck.Model.Login.Inventory.Fix.FixReqListProve.ShowFixRequest;
import com.ldb.truck.Model.Login.Inventory.Items.*;
import com.ldb.truck.Model.Login.Inventory.OfferPaper.*;
import com.ldb.truck.Model.Login.Inventory.Report_Stock.*;
import com.ldb.truck.Model.Login.Inventory.Shops.*;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(InventoryService.class);
    @Autowired private InventoryDao inventoryDao;
    //=====================================Shops======================================
    //---Show shop
    public ShopRes ListShops (@RequestBody ShopReq shopReq){
        log.info("toKen=======================:"+shopReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(shopReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        shopReq.setUserId(userId);
        shopReq.setBranch(userBranchNo);
        //====================================================================
        List<Shops> data = new ArrayList<>();
        ShopRes result = new ShopRes();
        try {
            data = inventoryDao.ListShops(shopReq);
            if(data.size() < 1 ){
                result.setMessage("have no data");
                result.setStatus("01");
                return result;
            }else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(data);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
//    list of history oill fill
public FillOilRes ListOilFillService (@RequestBody FillOilReq fillOilReq){

    List<FillOilModel> data = new ArrayList<>();
    FillOilRes result = new FillOilRes();
    try {
        data = inventoryDao.ListHisFillOillDao(fillOilReq);

        DecimalFormat numfm = new DecimalFormat("###,###.###");
        sumFooterGroupHisOil restFooter = new sumFooterGroupHisOil();
        double priceAll =  data.stream().map(FillOilModel::getPrice).collect(Collectors.summingDouble(Double::doubleValue));
        restFooter.setTotalPricePaidOil(numfm.format(priceAll));

        if(data.size() < 1 ){
            result.setMessage("have no data");
            result.setStatus("01");
            return result;
        }else {
            result.setSumFooter(restFooter);
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(data);
            return result;
        }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
    // Report list shop must pay
    public ShopsMustPayRes ListShopsMustPayService (@RequestBody ShopReq shopReq){
        log.info("toKen=======================:"+shopReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(shopReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        shopReq.setUserId(userId);
        shopReq.setBranch(userBranchNo);
        //====================================================================
        List<ReportShops> data = new ArrayList<>();
        ShopsMustPayRes result = new ShopsMustPayRes();
        try {
            data = inventoryDao.ListShopsMustPayDao(shopReq);
            if(data.size() < 1 ){
                result.setMessage("have no data");
                result.setStatus("01");
                return result;
            }else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(data);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("Error data not found");
            result.setStatus("01");
            return result;
        }
    }
// insert shops
public ShopRes InsertShops(ShopReq shopReq ){

    log.info("toKen=======================:"+shopReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(shopReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    shopReq.setUserId(userId);
    shopReq.setBranch(userBranchNo);
    //====================================================================
    ShopRes result = new ShopRes();
    int i = 0 ;
    try {
        i = inventoryDao.InsertShops(shopReq);
        if(i == 0 ){
            result.setMessage("can't Insert Shops'");
            result.setStatus("01");
            return result;
        }
        result.setMessage("Success");
        result.setStatus("00");
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("exeption");
        result.setStatus("01");
        return result;
    }
}
//fill oil insert serviece
public FillOilRes InsertOil (FillOilReq fillOilReq){
    FillOilRes result = new FillOilRes();
    int i = 0 ;
    try {
        i = inventoryDao.InsertFilloilDaos(fillOilReq);
        if(i == 0 ){
            result.setMessage("can't Insert oil'");
            result.setStatus("01");
            return result;
        }
        result.setMessage("Insert oil Success");
        result.setStatus("00");
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("exeption");
        result.setStatus("01");
        return result;
    }
}
// save offer paper
public OfferpaperRes SaveOfferPaper(OfferPaperReq offerPaperReq ){

    log.info("toKen=======================:"+offerPaperReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    offerPaperReq.setUserId(userId);
    offerPaperReq.setBranch(userBranchNo);
    //====================================================================
    OfferpaperRes result = new OfferpaperRes();
    try {
        inventoryDao.saveOfferPaperDao(offerPaperReq);
        result.setMessage("Successful");
        result.setStatus("00");
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("exeption");
        result.setStatus("01");
        return result;
    }
}
//fix service
public FixRes FixService(FixReq fixReq){

    log.info("toKen=======================:"+fixReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(fixReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    fixReq.setUserId(userId);
    fixReq.setBranch(userBranchNo);
    //====================================================================
    FixRes result = new FixRes();
    try {
            if (fixReq.getHeader_id().isEmpty() && fixReq.getFooter_id().isEmpty() && fixReq.getUserId().equals("78")) {
                inventoryDao.FixDaoIftruckNull(fixReq);
                result.setMessage("Successful");
                result.setStatus("00");
                return result;
            } else if (fixReq.getHeader_id().isEmpty() && fixReq.getFooter_id().isEmpty() && fixReq.getUserId().equals("142")) {
                inventoryDao.FixDaoIftruckNullXiengKhouang(fixReq);
                result.setMessage("Successful");
                result.setStatus("00");
                return result;
            } else {
                inventoryDao.FixDao(fixReq);
                result.setMessage("Successful");
                result.setStatus("00");
                return result;
            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("exeption");
        result.setStatus("01");
        return result;
    }
}
//approve fix service
public FixRes approveFixService(FixReq fixReq){

    log.info("toKen=======================:"+fixReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(fixReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    fixReq.setUserId(userId);
    fixReq.setBranch(userBranchNo);
    //====================================================================
    FixRes result = new FixRes();
    try {
            inventoryDao.ApproveFixDao(fixReq);
            result.setMessage("Successful");
            result.setStatus("00");
            return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("exeption");
        result.setStatus("01");
        return result;
    }
}
//prove fix req
public FixRes proofFixReqService(FixReq fixReq){
    FixRes result = new FixRes();
    try {
        inventoryDao.proofFixReqDao(fixReq);
        result.setMessage("List proved success");
        result.setStatus("00");
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("exeption");
        result.setStatus("01");
        return result;
    }
}
    // Move item to stock
    public MoveToStockRes MoveItemToStockService (MoveToStockReq moveToStockReq ){

        log.info("toKen=======================:"+moveToStockReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(moveToStockReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        moveToStockReq.setUserId(userId);
        moveToStockReq.setBranch(userBranchNo);
        //====================================================================
        MoveToStockRes result = new MoveToStockRes();
        try {
            inventoryDao.MoveItemToStockDao(moveToStockReq);
            inventoryDao.MoveItemToStockDaoAndSaveItemHistory(moveToStockReq);
            result.setMessage("Successful");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
    // pay to shop service
    // Move item to stock
    public PayToShopRes PayToShopService (List<PayToShopReq> payToShopReq){
        PayToShopRes result = new PayToShopRes();
        try {
            inventoryDao.PayToShopDao(payToShopReq);
            result.setMessage("Successful");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
    //report stock
    public ReportStockRes ReportStockService (MoveToStockReq moveToStockReq ){

        log.info("toKen=======================:"+moveToStockReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(moveToStockReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        moveToStockReq.setUserId(userId);
        moveToStockReq.setBranch(userBranchNo);
        //====================================================================
        ReportStockRes result = new ReportStockRes();
        List<ReportStockModel> data = new ArrayList<>();

        DecimalFormat numfm = new DecimalFormat("###,###.###");
        try {
            data = inventoryDao.ListStock(moveToStockReq);

            sumFooterGroupTotalValue restFooter = new sumFooterGroupTotalValue();
            double TotalValue =  data.stream().map(ReportStockModel::getTotalValue).collect(Collectors.summingDouble(Double::doubleValue));
            restFooter.setTotalValue(numfm.format(TotalValue));

            result.setSumFooter(restFooter);
            result.setMessage("Successful");
            result.setStatus("00");
            result.setData(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
    // item his
    public ItemHisRes ItemHisResService (ItemHisReq itemHisReq){

        log.info("toKen=======================:"+itemHisReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(itemHisReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        itemHisReq.setUserId(userId);
        itemHisReq.setBranch(userBranchNo);
        //====================================================================
        ItemHisRes result = new ItemHisRes();
        List<ItemHis> data = new ArrayList<>();
        try {
            data = inventoryDao.ItemHisDao(itemHisReq);

            sumFooterItemHis restFooter = new sumFooterItemHis();
            double sum =  data.stream().map(ItemHis::getItem_qty).collect(Collectors.summingDouble(Double::doubleValue));
            restFooter.setSumQTY(sum);

            result.setSumFooter(restFooter);
            result.setMessage("Successful");
            result.setStatus("00");
            result.setData(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
    //report stock detail
    public ReportStockRes ReportStockServiceDetail (ReportStockDetailReq reportStockDetailReq ){

        log.info("toKen=======================:"+reportStockDetailReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(reportStockDetailReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        reportStockDetailReq.setUserId(userId);
        reportStockDetailReq.setBranch(userBranchNo);
        //====================================================================
        ReportStockRes result = new ReportStockRes();
        List<ReportStockModel> data = new ArrayList<>();
        try {
            data = inventoryDao.ListStockDetail(reportStockDetailReq);
            result.setMessage("Successful");
            result.setStatus("00");
            result.setData(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
    // save Purchase Order
    public PurchaseOrderRes SavePurchaseOrder (PurchaseOrderReq purchaseOrderReq ){

        log.info("toKen=======================:"+purchaseOrderReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(purchaseOrderReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        purchaseOrderReq.setUserId(userId);
        purchaseOrderReq.setBranch(userBranchNo);
        //====================================================================
        PurchaseOrderRes result = new PurchaseOrderRes();
        try {
            inventoryDao.savePurchaseorderDao(purchaseOrderReq);
            result.setMessage("Successful");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
// show offer paper after saved
public ShowOfferPaper ShowOfferPaperSaved(@RequestBody OfferPaperReq offerPaperReq) {
    log.info("toKen=======================:" + offerPaperReq.getToKen());

    // Get User info
    List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
    logUserInformation(userIn);

    // Set user data
    offerPaperReq.setUserId(userIn.get(0).getUserId());
    offerPaperReq.setBranch(userIn.get(0).getBranchNo());

    // Get offer paper data
    List<OfferPaperModelFaso> listData = inventoryDao.ShowofferpaperDAOs(offerPaperReq);

    // Calculate sum based on status
    double realTotalMoneySum = listData.stream()
            .filter(data -> "Y".equals(data.getStatus())).mapToDouble(OfferPaperModelFaso::getReal_totalMoney).sum();

    ShowOfferPaper result = new ShowOfferPaper();
    DecimalFormat numfm = new DecimalFormat("###,###.###");

    sumFooterGroupOfferPaper restFooter = new sumFooterGroupOfferPaper();
    restFooter.setTotalMoney(numfm.format(realTotalMoneySum));

    result.setSumFooter(restFooter);
    result.setMessage("Success");
    result.setStatus("00");
    result.setData(listData);

    return result;
}

    private void logUserInformation(List<Profile> userIn) {
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
    }
//public ShowOfferPaper ShowOfferPaperSaved (@RequestBody OfferPaperReq offerPaperReq ){
//    log.info("toKen=======================:"+offerPaperReq.getToKen());
//    //============================get User info=======================
//    List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
//    log.info("show=================UserNo:"+userIn.get(0).getUserId());
//    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//    log.info("show=================Role:"+userIn.get(0).getRole());
//    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
//    //================================================================
//    String userId = userIn.get(0).getUserId();
//    String userBranchNo = userIn.get(0).getBranchNo();
//    //===================set data to userId===============================
//    offerPaperReq.setUserId(userId);
//    offerPaperReq.setBranch(userBranchNo);
//    //====================================================================
//    List<OfferPaperModelFaso> listData = new ArrayList<>();
////    List<OfferPaperModelFaso> listDataToshow = new ArrayList<>();
//    List<OfferPaperModelFaso> listData2 = new ArrayList<>();
//    ShowOfferPaper result = new ShowOfferPaper();
//    DecimalFormat numfm = new DecimalFormat("###,###.###");
//    try {
//
////        listDataToshow = inventoryDao.ToShowofferpaperDAOs(offerPaperReq);
//        listData = inventoryDao.ShowofferpaperDAOs(offerPaperReq);
//        sumFooterGroupOfferPaper restFooter = new sumFooterGroupOfferPaper();
//        double Real_TotalMoney =  listData.stream().map(OfferPaperModelFaso::getReal_totalMoney).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooter.setTotalMoney(numfm.format(Real_TotalMoney));
//
//        result.setSumFooter(restFooter);
//        result.setMessage("Success");
//        result.setStatus("00");
//        result.setData(listData);     //original
////        result.setDataShow(listDataToshow);
////=============================================================================================
////        listData2 = inventoryDao.ShowofferpaperDAOspayCredit(offerPaperReq);
////        sumFooterGroupOfferPaper_Paid_Credit restFooter2 = new sumFooterGroupOfferPaper_Paid_Credit();
////        double Real_TotalMoney2 =  listData2.stream().map(OfferPaperModelFaso::getReal_totalMoneyCredit).collect(Collectors.summingDouble(Double::doubleValue));
////        restFooter2.setTotalMoney_credit(numfm.format(Real_TotalMoney2));
////
////        result.setSumFooter_Credit(restFooter2);
////        result.setMessage("Success");
////        result.setStatus("00");
////        result.setData(listData2);
////=============================================================================================
//
//
//            return result;
//    }catch (Exception e){
//        e.printStackTrace();
//        result.setMessage("data not found");
//        result.setStatus("01");
//        return result;
//    }
//}
//report offer paper
public ShowOfferPaper ReportShowOfferPaperSaved (@RequestBody OfferPaperReq offerPaperReq ){
    log.info("toKen=======================:"+offerPaperReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    offerPaperReq.setUserId(userId);
    offerPaperReq.setBranch(userBranchNo);
    //====================================================================
    List<OfferPaperModelFaso> listData = new ArrayList<>();
    List<OfferPaperModelFaso> listData2 = new ArrayList<>();
    ShowOfferPaper result = new ShowOfferPaper();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {

        listData = inventoryDao.ReportShowofferpaperDAOs(offerPaperReq);
        sumFooterGroupOfferPaper restFooter = new sumFooterGroupOfferPaper();
        double Real_TotalMoney =  listData.stream().map(OfferPaperModelFaso::getReal_totalMoney).collect(Collectors.summingDouble(Double::doubleValue));
        restFooter.setTotalMoney(numfm.format(Real_TotalMoney));

        result.setSumFooter(restFooter);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listData);
//=============================================================================================
        listData2 = inventoryDao.ShowofferpaperDAOspayCredit(offerPaperReq);
        sumFooterGroupOfferPaper_Paid_Credit restFooter2 = new sumFooterGroupOfferPaper_Paid_Credit();
        double Real_TotalMoney2 =  listData2.stream().map(OfferPaperModelFaso::getReal_totalMoneyCredit).collect(Collectors.summingDouble(Double::doubleValue));
        restFooter2.setTotalMoney_credit(numfm.format(Real_TotalMoney2));

        result.setSumFooter_Credit(restFooter2);
        result.setMessage("Success");
        result.setStatus("00");
//        result.setData(listData2);
//=============================================================================================


        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//service report Showofferpaper Currency
public ReportShowOfferPaper reportShowofferpaperCurrencyServiceUSD (@RequestBody OfferPaperReq offerPaperReq ){
    log.info("toKen=======================:"+offerPaperReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    offerPaperReq.setUserId(userId);
    offerPaperReq.setBranch(userBranchNo);
    //====================================================================
//    List<ReportOfferPaperModelTHB> listDataTHB = new ArrayList<>();
    List<ReportOfferPaperModel> listDataUSD = new ArrayList<>();
//    List<ReportOfferPaperModelLAK> listDataLAK = new ArrayList<>();
    ReportShowOfferPaper result = new ReportShowOfferPaper();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
//        listDataTHB = inventoryDao.ShowReportSumofferpaperTHB(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterTHB = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyTHB =  listDataTHB.stream().map(ReportOfferPaperModelTHB::getSumMoneyCurrencyTHB).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterTHB.setSumMoneyCurrencyTHB(numfm.format(sumMoneyTHB));
//        result.setSumFooter_Currency(restFooterTHB);
//=============================================================================================
//
        listDataUSD = inventoryDao.ShowReportSumofferpaperUSD(offerPaperReq);
        sumFooterGroupOfferReportCurrency restFooterUSD = new sumFooterGroupOfferReportCurrency();

        double sumMoneyUSD =  listDataUSD.stream().map(ReportOfferPaperModel::getSumMoneycurrencyUSD).collect(Collectors.summingDouble(Double::doubleValue));
        double curUSD =  listDataUSD.stream().map(ReportOfferPaperModel::getTotalPriceCur).collect(Collectors.summingDouble(Double::doubleValue));
        restFooterUSD.setSumMoneycurrencyUSD(numfm.format(sumMoneyUSD));
        restFooterUSD.setUsd(numfm.format(curUSD));
        result.setSumFooter_Currency(restFooterUSD);
////=============================================================================================
//
//        listDataLAK = inventoryDao.ShowReportSumofferpaperLAK(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterLAK = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyLAK =  listDataLAK.stream().map(ReportOfferPaperModelLAK::getSumMoneycurrencyLAK).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterLAK.setSumMoneycurrencyLAK(numfm.format(sumMoneyLAK));

        result.setMessage("Success");
        result.setStatus("00");
//        result.setData(listDataTHB);
//        result.setData(listDataUSD);
//        result.setData(listDataLAK);
        result.setSumFooter_Currency(restFooterUSD);
//        result.setSumFooter_Currency(restFooterTHB);
//        result.setSumFooter_Currency(restFooterLAK);
//=============================================================================================


        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
    public ReportShowOfferPaper CurrencyUSDinKip (@RequestBody OfferPaperReq offerPaperReq ){
        log.info("toKen=======================:"+offerPaperReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        offerPaperReq.setUserId(userId);
        offerPaperReq.setBranch(userBranchNo);
        //====================================================================
//    List<ReportOfferPaperModelTHB> listDataTHB = new ArrayList<>();
        List<ReportOfferPaperModel> listDataUSD = new ArrayList<>();
//    List<ReportOfferPaperModelLAK> listDataLAK = new ArrayList<>();
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        try {
//        listDataTHB = inventoryDao.ShowReportSumofferpaperTHB(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterTHB = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyTHB =  listDataTHB.stream().map(ReportOfferPaperModelTHB::getSumMoneyCurrencyTHB).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterTHB.setSumMoneyCurrencyTHB(numfm.format(sumMoneyTHB));
//        result.setSumFooter_Currency(restFooterTHB);
//=============================================================================================
//
            listDataUSD = inventoryDao.CurrencyUSDinKip(offerPaperReq);
            sumFooterGroupOfferReportCurrency restFooterUSD = new sumFooterGroupOfferReportCurrency();
            double sumMoneyUSD =  listDataUSD.stream().map(ReportOfferPaperModel::getSumMoneycurrencyUSD).collect(Collectors.summingDouble(Double::doubleValue));
            restFooterUSD.setSumMoneycurrencyUSD(numfm.format(sumMoneyUSD));
            result.setSumFooter_Currency(restFooterUSD);
////=============================================================================================
//
//        listDataLAK = inventoryDao.ShowReportSumofferpaperLAK(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterLAK = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyLAK =  listDataLAK.stream().map(ReportOfferPaperModelLAK::getSumMoneycurrencyLAK).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterLAK.setSumMoneycurrencyLAK(numfm.format(sumMoneyLAK));

            result.setMessage("Success");
            result.setStatus("00");
//        result.setData(listDataTHB);
//        result.setData(listDataUSD);
//        result.setData(listDataLAK);
            result.setSumFooter_Currency(restFooterUSD);
//        result.setSumFooter_Currency(restFooterTHB);
//        result.setSumFooter_Currency(restFooterLAK);
//=============================================================================================


            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    public ReportShowOfferPaper CurrencyTHBinKip (@RequestBody OfferPaperReq offerPaperReq ){
        log.info("toKen=======================:"+offerPaperReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        offerPaperReq.setUserId(userId);
        offerPaperReq.setBranch(userBranchNo);
        //====================================================================
    List<ReportOfferPaperModelTHB> listDataTHBinKip = new ArrayList<>();
//        List<ReportOfferPaperModel> listDataUSD = new ArrayList<>();
//    List<ReportOfferPaperModelLAK> listDataLAK = new ArrayList<>();
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        try {
        listDataTHBinKip = inventoryDao.CurrencyTHBinKip(offerPaperReq);
        sumFooterGroupOfferReportCurrency restFooterTHB = new sumFooterGroupOfferReportCurrency();
        double sumMoneyTHB =  listDataTHBinKip.stream().map(ReportOfferPaperModelTHB::getSumMoneyCurrencyTHB).collect(Collectors.summingDouble(Double::doubleValue));
        restFooterTHB.setSumMoneyCurrencyTHB(numfm.format(sumMoneyTHB));
        result.setSumFooter_Currency(restFooterTHB);
//=============================================================================================
//
//            listDataUSD = inventoryDao.CurrencyUSDinKip(offerPaperReq);
//            sumFooterGroupOfferReportCurrency restFooterUSD = new sumFooterGroupOfferReportCurrency();
//            double sumMoneyUSD =  listDataUSD.stream().map(ReportOfferPaperModel::getSumMoneycurrencyUSD).collect(Collectors.summingDouble(Double::doubleValue));
//            restFooterUSD.setSumMoneycurrencyUSD(numfm.format(sumMoneyUSD));
//            result.setSumFooter_Currency(restFooterUSD);
////=============================================================================================
//
//        listDataLAK = inventoryDao.ShowReportSumofferpaperLAK(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterLAK = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyLAK =  listDataLAK.stream().map(ReportOfferPaperModelLAK::getSumMoneycurrencyLAK).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterLAK.setSumMoneycurrencyLAK(numfm.format(sumMoneyLAK));

            result.setMessage("Success");
            result.setStatus("00");
//        result.setData(listDataTHB);
//        result.setData(listDataUSD);
//        result.setData(listDataLAK);
//            result.setSumFooter_Currency(restFooterUSD);
        result.setSumFooter_Currency(restFooterTHB);
//        result.setSumFooter_Currency(restFooterLAK);
//=============================================================================================


            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    public ReportShowOfferPaper CurrencyLAKinKip (@RequestBody OfferPaperReq offerPaperReq ){
        log.info("toKen=======================:"+offerPaperReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        offerPaperReq.setUserId(userId);
        offerPaperReq.setBranch(userBranchNo);
        //====================================================================
//        List<ReportOfferPaperModelTHB> listDataTHBinKip = new ArrayList<>();
//        List<ReportOfferPaperModel> listDataUSD = new ArrayList<>();
    List<ReportOfferPaperModelLAK> listDataLAK = new ArrayList<>();
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        try {
//            listDataTHBinKip = inventoryDao.CurrencyTHBinKip(offerPaperReq);
//            sumFooterGroupOfferReportCurrency restFooterTHB = new sumFooterGroupOfferReportCurrency();
//            double sumMoneyTHB =  listDataTHBinKip.stream().map(ReportOfferPaperModelTHB::getSumMoneyCurrencyTHB).collect(Collectors.summingDouble(Double::doubleValue));
//            restFooterTHB.setSumMoneyCurrencyTHB(numfm.format(sumMoneyTHB));
//            result.setSumFooter_Currency(restFooterTHB);
//=============================================================================================
//
//            listDataUSD = inventoryDao.CurrencyUSDinKip(offerPaperReq);
//            sumFooterGroupOfferReportCurrency restFooterUSD = new sumFooterGroupOfferReportCurrency();
//            double sumMoneyUSD =  listDataUSD.stream().map(ReportOfferPaperModel::getSumMoneycurrencyUSD).collect(Collectors.summingDouble(Double::doubleValue));
//            restFooterUSD.setSumMoneycurrencyUSD(numfm.format(sumMoneyUSD));
//            result.setSumFooter_Currency(restFooterUSD);
////=============================================================================================

        listDataLAK = inventoryDao.CurrencyLAKinKip(offerPaperReq);
        sumFooterGroupOfferReportCurrency restFooterLAK = new sumFooterGroupOfferReportCurrency();
        double sumMoneyLAK =  listDataLAK.stream().map(ReportOfferPaperModelLAK::getSumMoneycurrencyLAK).collect(Collectors.summingDouble(Double::doubleValue));
        restFooterLAK.setSumMoneycurrencyLAK(numfm.format(sumMoneyLAK));

            result.setMessage("Success");
            result.setStatus("00");
//        result.setData(listDataTHB);
//        result.setData(listDataUSD);
//        result.setData(listDataLAK);
//            result.setSumFooter_Currency(restFooterUSD);
//            result.setSumFooter_Currency(restFooterTHB);
        result.setSumFooter_Currency(restFooterLAK);
//=============================================================================================


            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
//THB
public ReportShowOfferPaper reportShowofferpaperCurrencyServiceTHB (@RequestBody OfferPaperReq offerPaperReq ){
    log.info("toKen=======================:"+offerPaperReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    offerPaperReq.setUserId(userId);
    offerPaperReq.setBranch(userBranchNo);
    //====================================================================
    List<ReportOfferPaperModelTHB> listDataTHB = new ArrayList<>();
//    List<ReportOfferPaperModel> listDataUSD = new ArrayList<>();
//    List<ReportOfferPaperModelLAK> listDataLAK = new ArrayList<>();
    ReportShowOfferPaper result = new ReportShowOfferPaper();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
        listDataTHB = inventoryDao.ShowReportSumofferpaperTHB(offerPaperReq);
        sumFooterGroupOfferReportCurrency restFooterTHB = new sumFooterGroupOfferReportCurrency();
        double sumMoneyTHB =  listDataTHB.stream().map(ReportOfferPaperModelTHB::getSumMoneyCurrencyTHB).collect(Collectors.summingDouble(Double::doubleValue));

        double curTHB =  listDataTHB.stream().map(ReportOfferPaperModelTHB::getTotalPriceCur).collect(Collectors.summingDouble(Double::doubleValue));

        restFooterTHB.setThb(numfm.format(curTHB));
        restFooterTHB.setSumMoneyCurrencyTHB(numfm.format(sumMoneyTHB));
        result.setSumFooter_Currency(restFooterTHB);
//=============================================================================================
//
//        listDataUSD = inventoryDao.ShowReportSumofferpaperUSD(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterUSD = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyUSD =  listDataUSD.stream().map(ReportOfferPaperModel::getSumMoneycurrencyUSD).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterUSD.setSumMoneycurrencyUSD(numfm.format(sumMoneyUSD));
//        result.setSumFooter_Currency(restFooterUSD);
//=============================================================================================
//
//        listDataLAK = inventoryDao.ShowReportSumofferpaperLAK(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterLAK = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyLAK =  listDataLAK.stream().map(ReportOfferPaperModelLAK::getSumMoneycurrencyLAK).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterLAK.setSumMoneycurrencyLAK(numfm.format(sumMoneyLAK));

        result.setMessage("Success");
        result.setStatus("00");
//        result.setData(listDataTHB);
//        result.setData(listDataUSD);
//        result.setData(listDataLAK);
//        result.setSumFooter_Currency(restFooterUSD);
        result.setSumFooter_Currency(restFooterTHB);
//        result.setSumFooter_Currency(restFooterLAK);
//=============================================================================================


        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//LAK
public ReportShowOfferPaper reportShowofferpaperCurrencyServiceLAK (@RequestBody OfferPaperReq offerPaperReq ){
    log.info("toKen=======================:"+offerPaperReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(offerPaperReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    offerPaperReq.setUserId(userId);
    offerPaperReq.setBranch(userBranchNo);
    //====================================================================
//    List<ReportOfferPaperModelTHB> listDataTHB = new ArrayList<>();
//    List<ReportOfferPaperModel> listDataUSD = new ArrayList<>();
    List<ReportOfferPaperModelLAK> listDataLAK = new ArrayList<>();
    ReportShowOfferPaper result = new ReportShowOfferPaper();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
//        listDataTHB = inventoryDao.ShowReportSumofferpaperTHB(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterTHB = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyTHB =  listDataTHB.stream().map(ReportOfferPaperModelTHB::getSumMoneyCurrencyTHB).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterTHB.setSumMoneyCurrencyTHB(numfm.format(sumMoneyTHB));
//        result.setSumFooter_Currency(restFooterTHB);
////=============================================================================================
//
//        listDataUSD = inventoryDao.ShowReportSumofferpaperUSD(offerPaperReq);
//        sumFooterGroupOfferReportCurrency restFooterUSD = new sumFooterGroupOfferReportCurrency();
//        double sumMoneyUSD =  listDataUSD.stream().map(ReportOfferPaperModel::getSumMoneycurrencyUSD).collect(Collectors.summingDouble(Double::doubleValue));
//        restFooterUSD.setSumMoneycurrencyUSD(numfm.format(sumMoneyUSD));
//        result.setSumFooter_Currency(restFooterUSD);
//=============================================================================================

        listDataLAK = inventoryDao.ShowReportSumofferpaperLAK(offerPaperReq);
        sumFooterGroupOfferReportCurrency restFooterLAK = new sumFooterGroupOfferReportCurrency();
        double sumMoneyLAK =  listDataLAK.stream().map(ReportOfferPaperModelLAK::getSumMoneycurrencyLAK).collect(Collectors.summingDouble(Double::doubleValue));
        restFooterLAK.setSumMoneycurrencyLAK(numfm.format(sumMoneyLAK));

        result.setMessage("Success");
        result.setStatus("00");
//        result.setData(listDataTHB);
//        result.setData(listDataUSD);
//        result.setData(listDataLAK);
//        result.setSumFooter_Currency(restFooterUSD);
//        result.setSumFooter_Currency(restFooterTHB);
        result.setSumFooter_Currency(restFooterLAK);
//=============================================================================================


        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//report stock day week
public ReportstockRes reportStockDayWeekService (@RequestBody ReportstockReq reportstockReq ){
    log.info("toKen=======================:"+reportstockReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(reportstockReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    reportstockReq.setUserId(userId);
    reportstockReq.setBranch(userBranchNo);
    //====================================================================
    List<ReportstockModel> listData = new ArrayList<>();
    List<ReportstockModel2> listData2 = new ArrayList<>();
    ReportstockRes result = new ReportstockRes();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {

            listData2 = inventoryDao.inventoryalaireportStockDayWeekDaos(reportstockReq);
            sumFooterGroup2 restFooter2 = new sumFooterGroup2();
            double total_qty_stock2 =  listData2.stream().map(ReportstockModel2::getQty_stock2).collect(Collectors.summingDouble(Double::doubleValue));
            double total_yodyokma2 =  listData2.stream().map(ReportstockModel2::getYodyokma2).collect(Collectors.summingDouble(Double::doubleValue));
            restFooter2.setTotal_qty_stock2(numfm.format(total_qty_stock2));
            restFooter2.setTotal_yodyokma2(numfm.format(total_yodyokma2));

//==========================================================================================
            listData = inventoryDao.reportStockDayWeekDaos(reportstockReq);
            sumFooterGroup restFooter = new sumFooterGroup();
            double total_qty_stock = listData.stream().map(ReportstockModel::getQty_stock).collect(Collectors.summingDouble(Double::doubleValue));
            double total_qty_in = listData.stream().map(ReportstockModel::getQty_in).collect(Collectors.summingDouble(Double::doubleValue));
            double total_qty_out = listData.stream().map(ReportstockModel::getQty_out).collect(Collectors.summingDouble(Double::doubleValue));
            double total_yodyokma = listData.stream().map(ReportstockModel::getYordyokma).collect(Collectors.summingDouble(Double::doubleValue));
            restFooter.setTotal_qty_stock(numfm.format(total_qty_stock));
            restFooter.setTotal_qty_in(numfm.format(total_qty_in));
            restFooter.setTotal_qty_out(numfm.format(total_qty_out));
            restFooter.setTotal_yodyokma(numfm.format(total_yodyokma));
//            =========================================================== for sang a lai
            restFooter2.setTotal_yodyokma2(numfm.format(total_yodyokma2));
            restFooter2.setTotal_qty_stock2(numfm.format(total_qty_stock2));
            restFooter2.setTotal_qty_in(numfm.format(total_qty_in));
            restFooter2.setTotal_qty_out(numfm.format(total_qty_out));

            if (total_qty_stock==0 && total_yodyokma ==0){
                result.setSumFooter2(restFooter2);
                result.setMessage("Success");
                result.setStatus("00");
                result.setData(listData);
                return result;
            }else {
                result.setSumFooter(restFooter);
                result.setMessage("Success");
                result.setStatus("00");
                result.setData(listData);
                return result;
            }
//            result.setSumFooter(restFooter);
//            result.setMessage("Success");
//            result.setStatus("00");
//            result.setData(listData);
//            return result;

    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
// show all fix list
public ShowFix  ShowFixList (@RequestBody FixReq fixReq){
    log.info("toKen=======================:"+fixReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(fixReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    fixReq.setUserId(userId);
    fixReq.setBranch(userBranchNo);
    //====================================================================
    List<ShowFixModel> listData = new ArrayList<>();
    ShowFix result = new ShowFix();
    try {
        listData = inventoryDao.ShowFixListDAOs(fixReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listData);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//=================================================================================original
//show fix req list
public ShowFixRequest  showListofFixReqService (@RequestBody FixReq fixReq){
    log.info("toKen=======================:"+fixReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(fixReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    fixReq.setUserId(userId);
    fixReq.setBranch(userBranchNo);
    //====================================================================
    List<ReqListOfFixModel> Data = new ArrayList<>();
    ShowFixRequest result = new ShowFixRequest();
    try {
        Data = inventoryDao.ShowProveFixListDAOs(fixReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//    bard===============================================================================================

// show fix detail
public ShowFix  ShowFixDetail (@RequestBody FixReq fixReq){
    log.info("toKen=======================:"+fixReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(fixReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    fixReq.setUserId(userId);
    fixReq.setBranch(userBranchNo);
    //====================================================================
    List<ShowFixModel> listData = new ArrayList<>();
    ShowFix result = new ShowFix();
    try {
        listData = inventoryDao.ShowFixListDAOsDetail(fixReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listData);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
// update fix cost
public ShowFix  UpdateFixCostService (@RequestBody FixReq fixReq){
    List<ShowFixModel> listData = new ArrayList<>();
    ShowFix result = new ShowFix();
    try {
        listData = inventoryDao.UpdateFixCostDao(fixReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listData);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
// fix report service
public FixReportRes FixReportService (@RequestBody FixReq fixReq ){
    log.info("toKen=======================:"+fixReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(fixReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    fixReq.setUserId(userId);
    fixReq.setBranch(userBranchNo);
    //====================================================================
    List<FixModelFaso> listData = new ArrayList<>();
    FixReportRes result = new FixReportRes();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
        listData = inventoryDao.FixReportDAOs(fixReq);
        sumFooterGroupFix restFooter = new sumFooterGroupFix();
        double TotalFixCost =  listData.stream().map(FixModelFaso::getTotalFixCost).collect(Collectors.summingDouble(Double::doubleValue));
        restFooter.setTotalFixCost(numfm.format(TotalFixCost));

        result.setSumFooter(restFooter);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listData);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
// show offer paper detail
public ShowOfferPaperDetail ShowOfferPaperDetail (@RequestBody OfferPaperReq offerPaperReq ){
    List<OfferPaperModelFaso> listData = new ArrayList<>();
    ShowOfferPaperDetail result = new ShowOfferPaperDetail();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
        listData = inventoryDao.ShowofferpaperDetails(offerPaperReq);
        sumFooterGroupOfferPaper restFooter = new sumFooterGroupOfferPaper();
        double TotalMoney =  listData.stream().map(OfferPaperModelFaso::getTotalMoney).collect(Collectors.summingDouble(Double::doubleValue));
        restFooter.setTotalMoney(numfm.format(TotalMoney));

        result.setSumFooter(restFooter);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listData);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
// paid to shop detail bill service
public PaidToShopDetail PaidToShopDetailService (@RequestBody PaidToShopDetailReq paidToShopDetailReq ){
    log.info("toKen=======================:"+paidToShopDetailReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(paidToShopDetailReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    paidToShopDetailReq.setUserId(userId);
    paidToShopDetailReq.setBranch(userBranchNo);
    //====================================================================
    List<PaidToShopDetailModel> listData = new ArrayList<>();
    PaidToShopDetail result = new PaidToShopDetail();
    try {
        listData = inventoryDao.PaidToShopDetailsDaos(paidToShopDetailReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listData);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
// gen offer code
public GenCodeOfferPaper GenOfferPaperService (){
    List<OfferCodeModel> listdata = new ArrayList<>();
    GenCodeOfferPaper result = new GenCodeOfferPaper();
    try {
        listdata = inventoryDao.showOFFER_CODE();
        result.setStatus("00");
        result.setMessage("success");
        result.setData(listdata);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("data not found");
        result.setData(listdata);
    }
    return  result;
}
    // gen PO code
    public GenCodePO GenCodePoService (){
        List<PoCodeModel> listdata = new ArrayList<>();
        GenCodePO result = new GenCodePO();
        try {
            listdata = inventoryDao.showPO_CODE();
            result.setStatus("00");
            result.setMessage("success");
            result.setData(listdata);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
            result.setData(listdata);
        }
        return  result;
    }
// delete shop
    public ShopRes DelShops (ShopReq shopReq){
        ShopRes result = new ShopRes();
        try {
            inventoryDao.delShops(shopReq);
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
//    delete fill oill
public FillOilRes DelFillOilHis (FillOilReq fillOilReq){
    FillOilRes result = new FillOilRes();
    try {
        inventoryDao.delfillOill(fillOilReq);
        result.setMessage("Success");
        result.setStatus("00");
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
    // update shops
    public ShopRes UpdateShops(ShopReq shopReq ){

        log.info("toKen=======================:"+shopReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(shopReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        shopReq.setUserId(userId);
        shopReq.setBranch(userBranchNo);
        //====================================================================
        ShopRes result = new ShopRes();
        int i = 0 ;
        try {
            i = inventoryDao.UpdateShops(shopReq);
            if(i == 0 ){
                result.setMessage("can't Insert Shops'");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
    //=====================================Shops======================================

    //---Show
    public ItemRes ListItems (@RequestBody ItemReq itemReq){
        log.info("toKen=======================:"+itemReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(itemReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        itemReq.setUserId(userId);
        itemReq.setBranch(userBranchNo);
        //====================================================================
        List<Items> data = new ArrayList<>();
        ItemRes result = new ItemRes();
        try {
            data = inventoryDao.ListItems(itemReq);
            if(data.size() < 1 ){
                result.setMessage("have no data");
                result.setStatus("01");
                return result;
            }else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(data);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
//    detail item
public ItemRes ItemsDetailbyId (@RequestBody ItemReq itemReq){
    List<Items> data = new ArrayList<>();
    ItemRes result = new ItemRes();
    try {
        data = inventoryDao.ItemsDtailItemDAOs(itemReq);
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(data);
            return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
    //--delete item
    public ItemRes DelItem (ItemReq itemReq){
        ItemRes result = new ItemRes();
        try {
            inventoryDao.delItem(itemReq);
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
//--insert Item
    public Messages saveItems(ItemReq itemReq){
        log.info("toKen=======================:"+itemReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(itemReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        itemReq.setUserId(userId);
        itemReq.setBranch(userBranchNo);
        //====================================================================

        Messages message = new Messages();
        int i = 0;
        try {
            i = inventoryDao.InsertItemDao(itemReq);
            if(i == 0){
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ບັນທຶກສຳເລັດ");

        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return message;
        }
        return message;
    }
//Update
    public Messages  UpdateItems(ItemReq itemReq){
        log.info("toKen=======================:"+itemReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(itemReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        itemReq.setUserId(userId);
        itemReq.setBranch(userBranchNo);
        //====================================================================
        Messages message = new Messages();
        int i = 0;
        try {
            if(itemReq.getImg().equals("1") || itemReq.getImg() == null){
                i =inventoryDao.UpdateItemDaoHaveData(itemReq);
            }else {
                i =inventoryDao.UpdateItem(itemReq);
            }
            if(i == 0){
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດເເກ້ໄຂໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ເເກ້ໄຂສຳເລັດ");
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດເເກ້ໄຂໄດ້");
            return message;
        }
        return message;
    }
}
