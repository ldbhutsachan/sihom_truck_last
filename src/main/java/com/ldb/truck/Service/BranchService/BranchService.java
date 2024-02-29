package com.ldb.truck.Service.BranchService;

import com.ldb.truck.Dao.BranchDAO.ImplBranchDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Branch.Branch;
import com.ldb.truck.Model.Login.Branch.BranchRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class BranchService {
    private static final Logger log = LogManager.getLogger(BranchService.class);

    @Autowired
    ImplBranchDao implBranchDao;
    @Autowired
    ProfileDao profileDao;
    //================================================================================
    public BranchRes getShowBranch(BrachReq branchReq){
        log.info("toKen=======================:"+branchReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfo(branchReq);
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
       // log.info("show==========where:"+branchReq.getBranchNo(userBranchNo));
        branchReq.setUserId(userId);
        branchReq.setBranchNo(userBranchNo);

        //====================================================================
        Messages messages = new Messages();
        BranchRes result = new BranchRes();
        try{

            List<Branch> listData = new ArrayList<>();
            listData = implBranchDao.getBranch(branchReq);
            if(listData.size() > 1){
                result.setStatus("00");
                result.setMessage("Done");
                result.setData(listData);
            }else {
                result.setStatus("01");
                result.setMessage("No Data");
                result.setData(listData);
            }
        }catch (Exception e){
            e.printStackTrace();
            //result.setData(listData);
        }
        return result;
    }
    //==========================================insert  ======================================
    public BranchRes saveBranch(BrachReq brachReq){
        //============================get User info=======================
        log.info("toKen=======================:"+brachReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfo(brachReq);
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        brachReq.setUserId(userId);
        //====================================================================
        Messages messages = new Messages();
        BranchRes result = new BranchRes();
        List<Branch> listData = new ArrayList<>();
        int i =0;
        try{
            i = implBranchDao.saveDataBranch(brachReq);
            if(i  == 0){
                result.setStatus("01");
                result.setMessage("No Save Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("Save Data Done");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //================================================================================
    //================================================================================
}
