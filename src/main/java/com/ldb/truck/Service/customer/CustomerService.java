package com.ldb.truck.Service.customer;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
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
}
