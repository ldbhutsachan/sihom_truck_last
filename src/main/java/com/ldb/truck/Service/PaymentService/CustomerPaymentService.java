package com.ldb.truck.Service.PaymentService;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.Payment.Customer_Payment;
import com.ldb.truck.Model.Login.Payment.Customer_PaymentRes;
import com.ldb.truck.Model.Login.Payment.Customer_PaymentReq;
import com.ldb.truck.Dao.Payment.CustomerPaymentDao;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerPaymentService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(CustomerPaymentService.class);
    @Autowired
    CustomerPaymentDao customerPaymentDao;
    public Customer_PaymentRes listGetPayment(Customer_PaymentReq customerPaymentReq){
        log.info("toKen=======================:"+customerPaymentReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(customerPaymentReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        customerPaymentReq.setUserId(userId);
        customerPaymentReq.setBranch(userBranchNo);
        //====================================================================
        List<Customer_Payment> resData = new ArrayList<>();
        Customer_PaymentRes result = new Customer_PaymentRes();
        try {
            resData = customerPaymentDao.listGetCustomer(customerPaymentReq);
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
        return  result;
    }
    public Customer_PaymentRes listGetCustomerByInfo(Customer_PaymentReq customerPaymentReq){
        List<Customer_Payment> resData = new ArrayList<>();
        Customer_PaymentRes result = new Customer_PaymentRes();
        try {
            resData = customerPaymentDao.listGetCustomerByInfo(customerPaymentReq);
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
        return  result;
    }

}
