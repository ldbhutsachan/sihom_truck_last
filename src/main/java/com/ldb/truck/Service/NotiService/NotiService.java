package com.ldb.truck.Service.NotiService;
import com.ldb.truck.Dao.NotiDao.NotiDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Dept_Must_Receive.NotificationDeptList;
import com.ldb.truck.Model.Login.Dept_Must_Receive.NotificationDeptListY;
import com.ldb.truck.Model.Login.Noti.*;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Service.ExpensesBookService.ExpensesBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class NotiService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(NotiService.class);
    @Autowired
    NotiDao notiDao;

//    public NotiRes listNoti(){
//        NotiRes result = new NotiRes();
//        List<NotiDetails> resDetails = new ArrayList<>();
//        List<NotiInvoice> resInvoice = new ArrayList<>();
//        List<NotiPerFormace> resPer = new ArrayList<>();
//        try{
//            resDetails = notiDao.notiDetails();
//            resInvoice = notiDao.notiInvoice();
//            resPer = notiDao.noTiPer();
//            result.setMessage("success");
//            result.setStatus("00");
//            result.setNotiDetails(resDetails);
//            result.setNotiInvoice(resInvoice);
//            result.setNotiPerForMance(resPer);
//        }catch (Exception e){
//        e.printStackTrace();
//            result.setMessage("Data Not found");
//            result.setStatus("01");
//            result.setNotiDetails(resDetails);
//            result.setNotiInvoice(resInvoice);
//            result.setNotiPerForMance(resPer);
//        }
//        return result;
//    }
    //get noti for tap 3 ========================>****<=================================
    public NotiRes listNotiTap3(NoticeReq noticeReq){

        log.info("toKen=======================:"+noticeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(noticeReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        String userBranchName = userIn.get(0).getBranchName();
        //===================set data to userId===============================
        noticeReq.setUserId(userId);
        noticeReq.setBranch(userBranchNo);
        noticeReq.setBranchName(userBranchName);
        //====================================================================
        NotiRes result = new NotiRes();
        List<NotiDetails> resDetails = new ArrayList<>();
        List<NotiInvoice> resInvoice = new ArrayList<>();
        List<NotiPerFormace> resPer = new ArrayList<>();
        List<OweNoti> resOweNoti = new ArrayList<>();
        List<notiPay> resNotiPay = new ArrayList<>();
        List<NotiFuel> resFuelP = new ArrayList<>();
        List<NotiFuel> resFuelUP = new ArrayList<>();
        List<NotiOffer> resOFFER = new ArrayList<>();
        List<NotiPO> resPO = new ArrayList<>();
        List<NoticeBialieng> resBL = new ArrayList<>();
        List<NoticePayOil> resPayOil = new ArrayList<>();
        List<NoticeStatusWaitToMoveToWhereHouse> resWait = new ArrayList<>();
        List<NotificationDeptList> resDeptStatusN = new ArrayList<>();
        List<NotificationDeptListY> resDeptStatusY = new ArrayList<>();
        double total = 0.0;
        double total1 = 0.0;
        double total2 = 0.0;
        double total3 = 0.0;
        double total4 = 0.0;
        double total5 =0.0;
        double total_P =0.0;
        double total_UP =0.0;
        double total_OFF =0.0;
        double total_PO =0.0;
        double total_BL =0.0;
        double total_PayOil =0.0;
        double total_Wait =0.0;
        double total_DeptStatusN =0.0;
        double total_DeptStatusY =0.0;
        DecimalFormat numfm = new DecimalFormat("###,###");
        try{
            resDetails = notiDao.notiDetal(noticeReq);
            resInvoice = notiDao.Invoice(noticeReq);
            resPer = notiDao.noPer(noticeReq);
            resOweNoti = notiDao.oweNoti(noticeReq);
            resNotiPay = notiDao.notiPayList(noticeReq);
            resFuelP = notiDao.NotiFuelP(noticeReq);
            resFuelUP = notiDao.NotiFuelUP(noticeReq);
            resOFFER = notiDao.NotiOffer(noticeReq);
            resPO = notiDao.NotiPurchaseOrder(noticeReq);
            resBL = notiDao.NotiBialieng(noticeReq);
            resPayOil = notiDao.NoticePayOil(noticeReq);
            resWait = notiDao.NoticeWait(noticeReq);
            resDeptStatusN = notiDao.DeptStatusNDAOs(noticeReq);
            resDeptStatusY = notiDao.DeptStatusYDAOs(noticeReq);

            total1 =resDetails.get(0).getDetailsStatus();
            total2 =resInvoice.get(0).getInvoiceStatus();
            total3 =resPer.get(0).getPerStatus();
            total4 =resOweNoti.get(0).getTotal_owe();
            total5 =resNotiPay.get(0).getTotal_pay();

            total_P =resFuelP.get(0).getTotal_FuelPaid();
            total_UP =resFuelUP.get(0).getTotal_FuelUnpaid();
            total_OFF =resOFFER.get(0).getTotal_Offer_List();
            total_PO =resPO.get(0).getTotal_PO();
            total_BL =resBL.get(0).getTotal_Bialieng();
            total_PayOil =resPayOil.get(0).getTotal_PayOil();
            total_Wait =resWait.get(0).getTotalWait();
            total_DeptStatusN =resDeptStatusN.get(0).getTotaldeptStatusN();
            total_DeptStatusY =resDeptStatusY.get(0).getTotaldeptStatusY();

            log.info("show:"+resNotiPay.get(0).getTotal_pay());
            log.info("show2:"+total4);
            total = resDetails.get(0).getDetailsStatus()+resInvoice.get(0).getInvoiceStatus()+resPer.get(0).getPerStatus()+
                    resOweNoti.get(0).getTotal_owe()+resNotiPay.get(0).getTotal_pay();
            result.setMessage("success");
            result.setStatus("00");
            result.setNotiDetails(numfm.format(total1));
            result.setNotiInvoice(numfm.format(total2));
            result.setNotiPerForMance(numfm.format(total3));
            result.setTotalOwe(numfm.format(total4));
            result.setPayStatus(numfm.format(total5));
            result.setTotalRow(numfm.format(total));
            result.setTotal_FuelPaid(numfm.format(total_P));
            result.setTotal_FuelUnpaid(numfm.format(total_UP));
            result.setTotal_Offer_List(numfm.format(total_OFF));
            result.setTotal_PO(numfm.format(total_PO));
            result.setTotal_BL(numfm.format(total_BL));
            result.setTotal_PayOil(numfm.format(total_PayOil));
            result.setTotal_Wait(numfm.format(total_Wait));
            result.setBranchName(userBranchName);
            result.setTotal_status_N_deptMustReceived(numfm.format(total_DeptStatusN));
            result.setTotal_status_Y_deptMustReceived(numfm.format(total_DeptStatusY));
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("Data Not found");
            result.setStatus("01");
            result.setNotiDetails(numfm.format(total1));
            result.setNotiInvoice(numfm.format(total2));
            result.setNotiPerForMance(numfm.format(total3));
            result.setTotalOwe(numfm.format(total4));
            result.setTotalRow(numfm.format(total));
            result.setTotal_FuelPaid(numfm.format(total_P));
            result.setTotal_FuelUnpaid(numfm.format(total_UP));
            result.setTotal_Offer_List(numfm.format(total_OFF));
            result.setTotal_PO(numfm.format(total_PO));
            result.setTotal_BL(numfm.format(total_BL));
            result.setTotal_PayOil(numfm.format(total_PayOil));
            result.setTotal_Wait(numfm.format(total_Wait));
            result.setBranchName(userBranchName);
            result.setTotal_status_N_deptMustReceived(numfm.format(total_DeptStatusN));
            result.setTotal_status_Y_deptMustReceived(numfm.format(total_DeptStatusY));
        }
        return result;
    }
}
