package com.ldb.truck.Service.customer;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.FuelStation.FuelStationOut;
import com.ldb.truck.Model.Login.FuelStation.FuelStationReq;
import com.ldb.truck.Model.Login.FuelStation.FuelStationRes;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.customer.CustomerOut;
import com.ldb.truck.Model.Login.customer.CustomerReq;
import com.ldb.truck.Model.Login.customer.CustomerRes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(CustomerService.class);

    @Autowired
    ImpCustomerDao impCustomerDao;

    public CustomerRes getAllCustomer (CustomerReq customerReq){
        log.info("toKen=======================:"+customerReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(customerReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        customerReq.setUserId(userId);
        customerReq.setBranch(userBranchNo);
        //====================================================================
        List<CustomerOut> listData = new ArrayList<>();
        CustomerRes result = new CustomerRes();
        try {
            listData = impCustomerDao.getAllCustomer(customerReq);
            System.out.println(listData);
            if(listData.size() > 0 ){
                result.setData(listData);
                result.setMessage("success");
                result.setStatus("00");
                return result;
            }
            result.setMessage("data not found");
            result.setStatus("01");
            return result;

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage(" exeption");
            result.setStatus("01");
            return result;
        }
    }
    //=====start===fuel station service========================================
    public FuelStationRes getAllFuelStation (FuelStationReq fuelStationReq){
        log.info("toKen=======================:"+fuelStationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(fuelStationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        fuelStationReq.setUserId(userId);
        fuelStationReq.setBranch(userBranchNo);
        //====================================================================
        List<FuelStationOut> listData = new ArrayList<>();
        FuelStationRes result = new FuelStationRes();
        try {
            listData = impCustomerDao.getAllFuelStation(fuelStationReq);
            System.out.println(listData);
            if(listData.size() > 0 ){
                result.setData(listData);
                result.setMessage("success");
                result.setStatus("00");
                return result;
            }
            result.setMessage("data not found");
            result.setStatus("01");
            return result;

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage(" exeption");
            result.setStatus("01");
            return result;
        }
    }
    //=====start===fuel station service========================================

    public CustomerRes getCustomerById (CustomerReq customerReq){
        log.info("toKen=======================:"+customerReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(customerReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        customerReq.setUserId(userId);
        customerReq.setBranch(userBranchNo);
        //====================================================================

        List<CustomerOut> listData = new ArrayList<>();
        CustomerRes result = new CustomerRes();
        try {

//            listData = impCustomerDao.getCustomerById(String.valueOf(customerReq));
            listData = impCustomerDao.getCustomerById(customerReq);

            System.out.println(listData);

            if(listData.size() > 0 ){
                result.setData(listData);
                result.setMessage("success");
                result.setStatus("00");
                return result;
            }

            result.setMessage("data not found");
            result.setStatus("01");
            return result;

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage(" exeption");
            result.setStatus("01");
            return result;
        }
    }
    public CustomerRes StoreCustomer(CustomerReq customerReq){

        log.info("toKen=======================:"+customerReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(customerReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        customerReq.setUserId(userId);
        customerReq.setBranch(userBranchNo);
        //====================================================================
        CustomerRes result = new CustomerRes();
        int i = 0 ;
        try {
            i = impCustomerDao.StoreCustomer(customerReq);
            if(i == 0 ){
                result.setMessage("can not store customer");
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
    //============store fuel station=====================================
    public FuelStationRes StoreFuelStation(FuelStationReq fuelStationReq){

        log.info("toKen=======================:"+fuelStationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(fuelStationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        fuelStationReq.setUserId(userId);
        fuelStationReq.setBranch(userBranchNo);
        //====================================================================
        FuelStationRes result = new FuelStationRes();
        int i = 0 ;
        try {
            i = impCustomerDao.StoreFuelStation(fuelStationReq);
            if(i == 0 ){
                result.setMessage("can not store fuel station");
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
    //============store fuel station=====================================
    public FuelStationRes UpdateStatusFuelStationService(FuelStationReq fuelStationReq){

        log.info("toKen=======================:"+fuelStationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(fuelStationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        fuelStationReq.setUserId(userId);
        fuelStationReq.setBranch(userBranchNo);
        //====================================================================
        FuelStationRes result = new FuelStationRes();
        int i = 0 ;
        try {
//            i = impCustomerDao.UpdateStatusFuelStation(fuelStationReq);
            i = impCustomerDao.UpdateStatusFuelStationMulti(fuelStationReq);
            i = impCustomerDao.insertTotalprice(fuelStationReq);
            if(i == 0 ){
                result.setMessage("can not pay");
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
    //============Update fuel station=====================================
    public FuelStationRes UpdateFuelStation (FuelStationReq fuelStationReq){

        log.info("toKen=======================:"+fuelStationReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(fuelStationReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        fuelStationReq.setUserId(userId);
        fuelStationReq.setBranch(userBranchNo);
        //====================================================================

        FuelStationRes result = new FuelStationRes();
        int i = 0 ;
        try {
            i = impCustomerDao.UpdateFuelStation(fuelStationReq);
            if(i == 0 ){
                result.setMessage("can not update fuel station");
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
    //============Update fuel station=====================================
    public CustomerRes UpdateCustomer (CustomerReq customerReq){

        log.info("toKen=======================:"+customerReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(customerReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        customerReq.setUserId(userId);
        customerReq.setBranch(userBranchNo);
        //====================================================================

        CustomerRes result = new CustomerRes();
        int i = 0 ;
        try {
            i = impCustomerDao.UpdateCustomer(customerReq);
            if(i == 0 ){
                result.setMessage("can not update customer");
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
    public CustomerRes deleteCustomer (String id){
        CustomerRes result = new CustomerRes();
        int i = 0 ;

        try {

            i = impCustomerDao.deleteCustomer(id);

            if(i == 0 ){
                result.setMessage("can not delete customer");
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
    //===============delete fuel sstation========================service
    public FuelStationRes deleteFuelStation (FuelStationReq fuelStationReq){
        FuelStationRes result = new FuelStationRes();
        int i = 0 ;

        try {

            i = impCustomerDao.deleteFuelStation(fuelStationReq);

            if(i == 0 ){
                result.setMessage("can not delete fuel station");
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
    //===============delete fuel sstation========================service
}
