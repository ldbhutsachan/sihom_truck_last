package com.ldb.truck.Service.NotiService;
import com.ldb.truck.Dao.NotiDao.NotiDao;
import com.ldb.truck.Model.Login.Noti.*;
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
    public NotiRes listNotiTap3(){
        NotiRes result = new NotiRes();
        List<NotiDetails> resDetails = new ArrayList<>();
        List<NotiInvoice> resInvoice = new ArrayList<>();
        List<NotiPerFormace> resPer = new ArrayList<>();
        List<OweNoti> resOweNoti = new ArrayList<>();
        List<notiPay> resNotiPay = new ArrayList<>();
        double total = 0.0;
        double total1 = 0.0;
        double total2 = 0.0;
        double total3 = 0.0;
        double total4 = 0.0;
        double total5 =0.0;
        DecimalFormat numfm = new DecimalFormat("###,###");
        try{
            resDetails = notiDao.notiDetal();
            resInvoice = notiDao.Invoice();
            resPer = notiDao.noPer();
            resOweNoti = notiDao.oweNoti();
            resNotiPay = notiDao.notiPayList();
            total1 =resDetails.get(0).getDetailsStatus();
            total2 =resInvoice.get(0).getInvoiceStatus();
            total3 =resPer.get(0).getPerStatus();
            total4 =resOweNoti.get(0).getTotal_owe();
            total5 =resNotiPay.get(0).getTotal_pay();
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
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("Data Not found");
            result.setStatus("01");
            result.setNotiDetails(numfm.format(total1));
            result.setNotiInvoice(numfm.format(total2));
            result.setNotiPerForMance(numfm.format(total3));
            result.setTotalOwe(numfm.format(total4));
            result.setTotalRow(numfm.format(total));
        }
        return result;
    }
}
