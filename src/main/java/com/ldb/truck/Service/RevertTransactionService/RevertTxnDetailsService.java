package com.ldb.truck.Service.RevertTransactionService;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.Revert.RevertDao;
import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Details.DetailsRes;
import com.ldb.truck.Model.Login.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.Details.DetailsReq;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevertTxnDetailsService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(RevertTxnDetailsService.class);
    @Autowired
    RevertDao revertDao;
    //show all txn
    public  DetailsRes showOutCarRevert(){
        DetailsRes result = new DetailsRes();
        List<Details> resData = new ArrayList<>();
        try{
            resData = revertDao.showOutCarRevert();
            result.setStatus("00");
            result.setMessage("success");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
      return result;
    }

    //---show txn by No
    public  DetailsRes showOutCarRevertbyNo(DetailsReq detailsReq){
        DetailsRes result = new DetailsRes();
        List<Details> resData = new ArrayList<>();
        try{
            resData = revertDao.showOutCarRevertbyNo(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return result;
    }
    //update all txn by lahudpoylod
    public DetailsRes UpdateRevertTxn(DetailsReq detailsReq){
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
        DetailsRes result = new DetailsRes();
        int i = 0;
        try{
            if (detailsReq.getHeaderNew()!="") {
                revertDao.updateHeader01(detailsReq);
                revertDao.updateHeaderNew(detailsReq);
                revertDao.updateHeader(detailsReq);
                revertDao.UpdateRevertTxn(detailsReq);
            }
            if(detailsReq.getFooterNew()!=""){
                revertDao.updateFooter(detailsReq);
                revertDao.updateFooterNew(detailsReq);
                revertDao.updateFooter01(detailsReq);
                revertDao.UpdateRevertTxn(detailsReq);
            }
            if(detailsReq.getStaffNew01()!=""){
                revertDao.updateStaff01(detailsReq);
                revertDao.updateStaff01New(detailsReq);
                revertDao.updateStaff011(detailsReq);
                revertDao.UpdateRevertTxn(detailsReq);
            }
            if(detailsReq.getStaffNew02()!=""){
                revertDao.updateStaff02(detailsReq);
                revertDao.updateStaff02New(detailsReq);
                revertDao.updateStaff012(detailsReq);
                revertDao.UpdateRevertTxn(detailsReq);
            }else {
                revertDao.UpdateRevertTxn(detailsReq);
            }
            result.setStatus("00");
            result.setMessage("success");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
    //---revert by update status
    public DetailsRes UpdateRevertOutCar(DetailsReq detailsReq){
        DetailsRes result = new DetailsRes();
        int i = 0;
        try{
            i = revertDao.UpdateRevertOutCar(detailsReq);
            if(i==0){
                result.setStatus("01");
                result.setMessage("data not found");
                return result;
            }
            result.setStatus("00");
            result.setMessage("success");
            return result;
        }catch (Exception e){
           e.printStackTrace();
        }
        return  result;
    }
}
