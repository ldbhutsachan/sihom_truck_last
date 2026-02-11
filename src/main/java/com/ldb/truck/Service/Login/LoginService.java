package com.ldb.truck.Service.Login;

import com.ldb.truck.Dao.Login.ImploginDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.User.UserImplDao;
import com.ldb.truck.Entity.User.UserHisEntity;
import com.ldb.truck.Entity.User.VUserHisEntity;
import com.ldb.truck.Model.Login.Login.*;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.User.UserLogin;
import com.ldb.truck.Model.Login.User.UserReq;
import com.ldb.truck.Model.Login.User.UserRes;
import com.ldb.truck.Repository.UserHisRepository;
import com.ldb.truck.Service.BranchService.BranchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    private static final Logger log = LogManager.getLogger(LoginService.class);
    @Autowired
    ProfileDao profileDao;
    @Autowired
    ImploginDao imploginDao;
    @Autowired
    UserImplDao userLogin;
    @Autowired
    UserHisRepository userHisRepository;

    public UserHisResponse getUserHistory(String userId){
        UserHisResponse resposne = new UserHisResponse();
        try {
            List<UserHisEntity> rslUser = userHisRepository.getDetailsUserHis(userId);
            if(rslUser.size() >= 1 ){
                resposne.setStatus("00");
                resposne.setMessage("susccess");
                resposne.setData(rslUser);
            }
            resposne.setStatus("00");
            resposne.setMessage("Data Do not Found !!");
            resposne.setData(null);

        }catch (Exception e){
            resposne.setStatus("00");
            resposne.setMessage("Data Do not Found !!");
            resposne.setData(null);
        }
        return resposne;
    }

    public UserHisViewResponse getUserHistoryView(String userId,UserHisRequest userHisRequest){
        UserHisViewResponse resposne = new UserHisViewResponse();
        try {
            List<VUserHisEntity> rslUser = userHisRepository.getDetailViewIEWUserHis(userHisRequest.getDetailId());
            if(rslUser.size() >= 1 ){
                resposne.setStatus("00");
                resposne.setMessage("susccess");
                resposne.setData(rslUser);
            }
            resposne.setStatus("00");
            resposne.setMessage("Data Do not Found !!");
            resposne.setData(null);

        }catch (Exception e){
            resposne.setStatus("00");
            resposne.setMessage("Data Do not Found !!");
            resposne.setData(null);
        }
        return resposne;
    }


    public GetUserLoginRes Userlogin(LoginReq loginReq){
        GetUserLoginRes result = new GetUserLoginRes();
        List<GetUserLoginOut> listData = new ArrayList<>();
        GetUserLoginOut data = new GetUserLoginOut();
        try {
            listData = imploginDao.Login(loginReq);
            if(listData.size() < 1){
                result.setMessage("user or password incorrect");
                result.setStatus("01");
                return result;
            }
            data.setStaftName(listData.get(0).getStaftName());
            data.setStaftId(listData.get(0).getStaftId());
            data.setRole(listData.get(0).getRole());
            data.setStatus(listData.get(0).getStatus());
            data.setToKen(listData.get(0).getToKen());
            data.setDepartment(listData.get(0).getDepartment());
            data.setDepartment1(listData.get(0).getDepartment1());
            data.setSprit_role(listData.get(0).getSprit_role());
            data.setBranchNo(listData.get(0).getBranchNo());
            data.setBranchName(listData.get(0).getBranchName());
            data.setBorNo(listData.get(0).getBorNo());
            data.setBorName(listData.get(0).getBorName());

            result.setMessage("success");
            result.setStatus("00");
            result.setData(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("user or password incorrect");
            result.setStatus("01");
            return result;
        }

    }
    public UserRes ListUserLogin(UserReq userReq){
        log.info("toKen=======================:"+userReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(userReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        userReq.setUserId(userId);
        userReq.setBranch(userBranchNo);
        //====================================================================
        UserRes result = new UserRes();
        List<UserLogin> listData = new PersistentList();
        try{
            listData = userLogin.listUser(userReq);
            if(listData.size() == 0){
                result.setStatus("01");
                result.setMessage("No Data");
                result.setData(listData);
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("success");
                result.setData(listData);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public UserRes storeUserLogin(UserReq userReq){
        UserRes result = new UserRes();
        log.info("toKen=======================:"+userReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(userReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        // log.info("show==========where:"+branchReq.getBranchNo(userBranchNo));
        userReq.setSaveById(userId);
        int checkData = 0;
        try{
            checkData = userLogin.storeUser(userReq);
            if(checkData  == 0){
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
    public UserRes updateUserLogin(UserReq userReq){
        UserRes result = new UserRes();
        log.info("toKen=======================:"+userReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(userReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        int checkData = 0;
        try{
            checkData = userLogin.editUser(userReq);
            if(checkData  == 0){
                result.setStatus("01");
                result.setMessage("No edit User Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("edit User Data Done");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public UserRes delUserLogin(UserReq userReq){
        UserRes result = new UserRes();
        int checkData = 0;
        try{
            checkData = userLogin.delUser(userReq);
            if(checkData  == 0){
                result.setStatus("01");
                result.setMessage("No del User Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("del User Data Done");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
