package com.ldb.truck.Service.location;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Dao.Login.ImploginDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.location.LocationOut;
import com.ldb.truck.Model.Login.location.LocationReq;
import com.ldb.truck.Model.Login.location.LocationRes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(LocationService.class);
    @Autowired
    ImpCustomerDao impCustomerDao;

    public LocationRes getAllLocation (LocationReq locationReq){
        log.info("toKen=======================:"+locationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(locationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        locationReq.setUserId(userId);
        locationReq.setBranch(userBranchNo);
        //====================================================================
        LocationRes result = new LocationRes();
        List<LocationOut> listData = new ArrayList<>();

        try {

            listData = impCustomerDao.getAllLocatino(locationReq);

            if(listData.size() < 1){

                result.setMessage("01");
                result.setStatus("data not found ");
                return result;
            }

            result.setStatus("00");
            result.setMessage("success");
            result.setData(listData);
            return  result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus("data not found ");
            return result;

        }

    }

    public LocationRes getLocationById (LocationReq locationReq){
        log.info("toKen=======================:"+locationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(locationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        locationReq.setUserId(userId);
        locationReq.setBranch(userBranchNo);
        //====================================================================

        LocationRes result = new LocationRes();
        List<LocationOut> listData = new ArrayList<>();

        try {

            listData = impCustomerDao.getLocationById(locationReq);

            if(listData.size() < 1){

                result.setMessage("01");
                result.setStatus("data not found ");
                return result;
            }

            result.setStatus("00");
            result.setMessage("success");
            result.setData(listData);
            return  result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus("data not found ");
            return result;

        }

    }

    public LocationRes StoreLocation (LocationReq locationReq){
        log.info("toKen=======================:"+locationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(locationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        locationReq.setUserId(userId);
        locationReq.setBranch(userBranchNo);
        //====================================================================

        LocationRes result = new LocationRes();
        int i = 0;

        try {

            i = impCustomerDao.StoreLocation(locationReq);

            if(i == 0){

                result.setMessage("01");
                result.setStatus(" can not Store Location ");
                return result;
            }

            result.setMessage("00");
            result.setStatus("success");
            return result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus(" can not Store Location ");
            return result;
        }
    }

    public LocationRes UpdateLocation (LocationReq locationReq){
        log.info("toKen=======================:"+locationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(locationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        locationReq.setUserId(userId);
        locationReq.setBranch(userBranchNo);
        //====================================================================

        LocationRes result = new LocationRes();
        int i = 0;

        try {

            i = impCustomerDao.UpdateLocation(locationReq);

            if(i == 0){

                result.setMessage("01");
                result.setStatus(" can not Update Location ");
                return result;
            }

            result.setMessage("00");
            result.setStatus("success");
            return result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus(" can not Update Location ");
            return result;
        }
    }

    public LocationRes DeleteLocation (LocationReq locationReq){

        LocationRes result = new LocationRes();
        int i = 0;

        try {

            i = impCustomerDao.DeleteLocation(locationReq);

            if(i == 0){

                result.setMessage("01");
                result.setStatus(" can not Delete Location ");
                return result;
            }

            result.setMessage("00");
            result.setStatus("success");
            return result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus(" can not Delete Location ");
            return result;
        }
    }

}
