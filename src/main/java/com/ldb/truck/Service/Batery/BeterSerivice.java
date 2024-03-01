package com.ldb.truck.Service.Batery;

import com.ldb.truck.Dao.BateryDao.ImplBateryDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Batery.Batery;
import com.ldb.truck.Model.Login.Batery.BateryReq;
import com.ldb.truck.Model.Login.Batery.BateryRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.staft.stafReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeterSerivice {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(BeterSerivice.class);
@Autowired
    ImplBateryDao implBateryDao;
public BateryRes getBateryAll(BateryReq  bateryReq){

    log.info("toKen=======================:"+bateryReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(bateryReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    bateryReq.setUserId(userId);
    bateryReq.setBranch(userBranchNo);
    //====================================================================
    BateryRes result =new BateryRes();
    List<Batery> listData = new ArrayList<>();
    try {
        listData = implBateryDao.getBateryAll(bateryReq);

        if(listData.size()> 1){
            result.setStatus("00");
            result.setMessage("suscess ");
            result.setData(listData);
        }else {
            result.setStatus("01");
            result.setMessage("Data not Found");
            result.setData(listData);
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
//==========================Save batery
public Messages saveBatery(BateryReq bateryReq){
    log.info("toKen=======================:"+bateryReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(bateryReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    bateryReq.setUserId(userId);
    bateryReq.setBranch(userBranchNo);
    //====================================================================
    Messages message = new Messages();
    int i = 0;
    try {
        i = implBateryDao.saveBatery(bateryReq);
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
    //==========================UPDATE batery
    public Messages updateBatery(BateryReq bateryReq){

        log.info("toKen=======================:"+bateryReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(bateryReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        bateryReq.setUserId(userId);
        bateryReq.setBranch(userBranchNo);
        //====================================================================
        Messages message = new Messages();
        int i = 0;
        try {
            if(bateryReq.getImageBatery().equals("1")){
                i = implBateryDao.UpdateBateryNoUpdateimage(bateryReq);
            }else {
                i = implBateryDao.UpdateBatery(bateryReq);
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
    //delete data
    public Messages DelBatery(BateryReq bateryReq){

        Messages message = new Messages();
        int i = 0;
        try {
            i = implBateryDao.DelBatery(bateryReq);
            if(i == 0){
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດລຶບຂໍ້ມູນໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ລຶບຂໍ້ມູນສຳເລັດ");

        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດລຶບຂໍ້ມູນໄດ້");
            return message;
        }
        return message;
    }
}
