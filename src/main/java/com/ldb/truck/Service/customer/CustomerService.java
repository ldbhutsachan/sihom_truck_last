package com.ldb.truck.Service.customer;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Model.Login.customer.CustomerOut;
import com.ldb.truck.Model.Login.customer.CustomerReq;
import com.ldb.truck.Model.Login.customer.CustomerRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
@Service
public class CustomerService {

    @Autowired
    ImpCustomerDao impCustomerDao;

    public CustomerRes getAllCustomer (){
        List<CustomerOut> listData = new ArrayList<>();
        CustomerRes result = new CustomerRes();
        try {
            listData = impCustomerDao.getAllCustomer();
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

        List<CustomerOut> listData = new ArrayList<>();
        CustomerRes result = new CustomerRes();
        try {

            listData = impCustomerDao.getCustomerById(String.valueOf(customerReq.getId()));

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
