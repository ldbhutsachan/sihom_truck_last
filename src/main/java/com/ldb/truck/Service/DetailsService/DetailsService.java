package com.ldb.truck.Service.DetailsService;
import com.ldb.truck.Dao.Details.DetailsServiceDao;
import com.ldb.truck.Dao.Performance.PerformanceDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Details.DetailsRes;
import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFormatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DetailsService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(DetailsService.class);
    @Autowired
    private DetailsServiceDao detailsServiceDao;
    @Autowired
    PerformanceDao performanceDao;
    public DetailsRes ListDataALL(){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            listdata = detailsServiceDao.ListDetails();
            result.setData(listdata);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
     public DetailsRes ListDataALLbyID(DetailsReq detailsReq){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            listdata = detailsServiceDao.ListDetailsById(detailsReq);
            result.setData(listdata);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    public DetailsRes deldata(DetailsReq detailsReq){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            listdata = detailsServiceDao.delData(detailsReq);
            result.setData(listdata);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    public DetailsRes upDateData(DetailsReq detailsReq){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            detailsServiceDao.updateData(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    public DetailsRes saveData(DetailsReq detailsReq){
        log.info("toKen=======================:"+detailsReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(detailsReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        detailsReq.setUserId(userId);
        detailsReq.setBranch(userBranchNo);
        //====================================================================
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
           detailsServiceDao.saveData(detailsReq);
           performanceDao.updateDetailsHeaderKM(detailsReq);
           performanceDao.updateDetailsFooterKM(detailsReq);
//           detailsServiceDao.UpdateHeader(detailsReq);
//           detailsServiceDao.UpdateFooter(detailsReq);
//           detailsServiceDao.updateStaff01(detailsReq);
//           detailsServiceDao.updateStaff02(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //show get invoice No
    public getInvoiceNoRes ListInvoicedetails (TogenTheCodeReq togenTheCodeReq){
        log.info("toKen=======================:"+togenTheCodeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(togenTheCodeReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        togenTheCodeReq.setUserId(userId);
        togenTheCodeReq.setBranch(userBranchNo);
        //====================================================================
        List<getInvoiceNo> listdata = new ArrayList<>();
        getInvoiceNoRes result = new getInvoiceNoRes();
        try {
            listdata = detailsServiceDao.showMaxLahudPoyLod(togenTheCodeReq);
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
//    KKT-service
public QuotationRes genQuotationCodeService (){
//    log.info("toKen=======================:"+togenTheCodeReq.getToKen());
//    //============================get User info=======================
//    List<Profile> userIn = profileDao.getProfileInfoByToken(togenTheCodeReq.getToKen());
//    log.info("show=================UserNo:"+userIn.get(0).getUserId());
//    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//    log.info("show=================Role:"+userIn.get(0).getRole());
//    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
//    //================================================================
//    String userId = userIn.get(0).getUserId();
//    String userBranchNo = userIn.get(0).getBranchNo();
//    //===================set data to userId===============================
//    togenTheCodeReq.setUserId(userId);
//    togenTheCodeReq.setBranch(userBranchNo);
//    //====================================================================
    List<getQuotationCode> listdata = new ArrayList<>();
    QuotationRes result = new QuotationRes();
    try {
        listdata = detailsServiceDao.showKKTcode();
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
//gen invoice dept service
public GenInvoiceDeptRes genInvoiceDeptCodeService (){
    List<getInvoiceDeptCode> listdata = new ArrayList<>();
    GenInvoiceDeptRes result = new GenInvoiceDeptRes();
    try {
        listdata = detailsServiceDao.showINVcode();
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

}
