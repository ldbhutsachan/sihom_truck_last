package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.Supplier.SupplierEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.SupplierEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl {
    @Autowired
    SupplierEntityRepository repository;
    public DataResponse getSupplier(SupplierEntity supplierEntity){
        DataResponse response = new DataResponse();
    try {
           if(supplierEntity.getSupplierId() != null) {
               response.setDataResponse(repository.getSupplierBySupplierId(supplierEntity.getSupplierId()));
           }else {
               response.setDataResponse(repository.getSupplierBySupplierAll());
           }
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not Found");
            }
    }catch (Exception e){
        response.setStatus("EE");
        response.setMessage("Error Data");
    }
    return response;
    }

    public DataResponse saveSupplier(SupplierEntity supplierEntity){
        DataResponse response = new DataResponse();
        try {
                response.setDataResponse(repository.save(supplierEntity));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not Found");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data");
        }
        return response;
    }
    public DataResponse updateSupplier(SupplierEntity supplierEntity){
        DataResponse response = new DataResponse();
        try {

            String supplierName= supplierEntity.getSupplierName();
            String mobile = supplierEntity.getMobile();
            String location = supplierEntity.getLocation();
            String email = supplierEntity.getEmail();
            String supplier_type = supplierEntity.getSupplierType();
            int supplierId = supplierEntity.getSupplierId();
            response.setDataResponse(repository.updateSupplier(supplierName,mobile,location,email,supplier_type,supplierId));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save Data");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data");
        }
        return response;
    } public DataResponse delSupplier(SupplierEntity supplierEntity){
        DataResponse response = new DataResponse();
        try {
            int supplierId = supplierEntity.getSupplierId();
            response.setDataResponse(repository.delSupplier(supplierId));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't del Data");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data");
        }
        return response;
    }
}
