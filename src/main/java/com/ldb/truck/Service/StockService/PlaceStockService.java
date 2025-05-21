package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.PlaceStock.PlaceStockEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.PlaceStockEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class PlaceStockService {
    @Autowired
    PlaceStockEntityRepository repository;

    public DataResponse getPlaceStockHouse(PlaceStockEntity placeStockEntity){
        DataResponse response = new DataResponse();
        try {
            if(placeStockEntity.getKhId() != null){
                response.setDataResponse(repository.findById(placeStockEntity.getKhId()));
            }else {
                response.setDataResponse(repository.findAllStockHouses());
            }
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not Found !!!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!!");
        }
        return response;
    }    public DataResponse storePlaceStockHouse(PlaceStockEntity placeStockEntity){
        DataResponse response = new DataResponse();
        try {
                response.setDataResponse(repository.save(placeStockEntity));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save Data !!!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!!");
        }
        return response;
    }

    public DataResponse updatePlaceStock(PlaceStockEntity placeStockEntity){
        DataResponse response = new DataResponse();
        try {
            String khNo = placeStockEntity.getKhNo();
            String khName = placeStockEntity.getKhName();
            Integer sole = placeStockEntity.getSole();
            String soleStep= placeStockEntity.getSoleStep();
            String blockNo= placeStockEntity.getBlockNo();
            String status= placeStockEntity.getStatus();
            Long khId = placeStockEntity.getKhId();
                response.setDataResponse(repository.updatePlaceStock(khNo,khName,sole,soleStep,blockNo,status,khId));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save Data !!!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!!");
        }
        return response;
    } public DataResponse delPlaceStock(PlaceStockEntity placeStockEntity){
        DataResponse response = new DataResponse();
        try {
            Long khId = placeStockEntity.getKhId();
                response.setDataResponse(repository.delPlaceStock(khId));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't delete Data !!!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!!");
        }
        return response;
    }
}
