package com.ldb.truck.Service.Product;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Model.Login.product.ProductOut;
import com.ldb.truck.Model.Login.product.ProductReq;
import com.ldb.truck.Model.Login.product.ProductRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ImpCustomerDao impCustomerDao;

    public ProductRes getAllProcut(){

        ProductRes restult = new ProductRes();
        List<ProductOut> data = new ArrayList<>();

        try {

            data = impCustomerDao.getAllProduct();

            if(data.size() < 1){
                restult.setStatus("01");
                restult.setMessage("data not found");
                return  restult;
            }

            restult.setStatus("00");
            restult.setMessage("success");
            restult.setData(data);
            return restult;

        }catch (Exception e){
            e.printStackTrace();
            restult.setStatus("01");
            restult.setMessage("data not found");
            return  restult;
        }

    }

    public ProductRes getProcutById( ProductReq productReq){

        ProductRes restult = new ProductRes();
        List<ProductOut> data = new ArrayList<>();

        try {

            data = impCustomerDao.getProductById(productReq.getId());

            if(data.size() < 1){
                restult.setStatus("01");
                restult.setMessage("data not found");
                return  restult;
            }

            restult.setStatus("00");
            restult.setMessage("success");
            restult.setData(data);
            return restult;

        }catch (Exception e){
            e.printStackTrace();
            restult.setStatus("01");
            restult.setMessage("data not found");
            return  restult;
        }

    }

    public ProductRes StoreProcut(ProductReq productReq){

        ProductRes restult = new ProductRes();

        int i = 0;

        try {

            i = impCustomerDao.StoreProduct(productReq);

            if(i== 0) {
                restult.setStatus("01");
                restult.setMessage(" can not store product ");
                return restult;
            }

            restult.setStatus("00");
            restult.setMessage("success");

            return restult;

        }catch (Exception e){
            e.printStackTrace();
            restult.setStatus("01");
            restult.setMessage("can not store product ");
            return  restult;
        }

    }

    public ProductRes UpdaeProcut(ProductReq productReq){

        ProductRes restult = new ProductRes();

        int i = 0;

        try {

            i = impCustomerDao.UpdateProduct(productReq);

           if(i== 0) {
               restult.setStatus("01");
               restult.setMessage(" can not update product ");
               return restult;
           }

            restult.setStatus("00");
            restult.setMessage("success");

            return restult;

        }catch (Exception e){
            e.printStackTrace();
            restult.setStatus("01");
            restult.setMessage("can not update product ");
            return  restult;
        }

    }

    public ProductRes DeleteProcut(ProductReq productReq){

        ProductRes restult = new ProductRes();

        int i = 0;

        try {

            i = impCustomerDao.deleteProduct(productReq.getId());

            if(i== 0) {
                restult.setStatus("01");
                restult.setMessage(" can not update product ");
                return restult;
            }

            restult.setStatus("00");
            restult.setMessage("success");

            return restult;

        }catch (Exception e){
            e.printStackTrace();
            restult.setStatus("01");
            restult.setMessage("can not update product ");
            return  restult;
        }

    }
}
