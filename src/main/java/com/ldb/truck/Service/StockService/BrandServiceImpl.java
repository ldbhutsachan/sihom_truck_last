package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.Brand.BrandEntity;
import com.ldb.truck.Entity.Brand.BrandReq;
import com.ldb.truck.Entity.Brand.BrandResponse;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl {

    @Autowired
    BrandRepository brandRepository;
    public DataResponse getBrand (BrandReq brandReq){
        DataResponse response = new DataResponse();
        try {
            if(!"".equals(brandReq.getBrandId())){
                response.setDataResponse(brandRepository.findByBrandId(brandReq.getBrandId()));
            }else {
                response.setDataResponse(brandRepository.findByAll());
            }
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data Not Found");
            }
        }catch (Exception e){
            response.setStatus("05");
            response.setMessage("Data Not Found");
        }
        return response;
    }
    public DataResponse saveBand(BrandEntity brandReq){
        DataResponse response = new DataResponse();
        try {
                response.setDataResponse(brandRepository.save(brandReq));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Save Data Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save Data !!");
            }
        }catch (Exception e){
            response.setStatus("05");
            response.setMessage("Save Data Error !!");
        }
        return response;
    }
    public DataResponse updateBand(BrandEntity brandReq){
        DataResponse response = new DataResponse();
        try {
            String brand_Name  = brandReq.getBrandName();
            String  company_Product  = brandReq.getCompanyProduct();
            String status = brandReq.getStatus();
            String brandid = brandReq.getBrandId();
                response.setDataResponse(brandRepository.update(brand_Name,company_Product,status,brandid));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Save update Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't update Data !!");
            }
        }catch (Exception e){
            response.setStatus("05");
            response.setMessage("update Data Error !!");
        }
        return response;
    }
    public DataResponse updateStatus(BrandEntity brandReq){
        DataResponse response = new DataResponse();
        try {
            String brandid = brandReq.getBrandId();
                response.setDataResponse(brandRepository.updateStatus(brandid));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("update Data Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't update Data !!");
            }
        }catch (Exception e){
            response.setStatus("05");
            response.setMessage("update Data Error !!");
        }
        return response;
    }
}
