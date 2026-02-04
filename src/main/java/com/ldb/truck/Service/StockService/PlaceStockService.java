package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.PlaceStock.PlaceStockEntity;
import com.ldb.truck.Entity.PlaceStock.PlaceStockEntityReq;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.BranchEntityRepository;
import com.ldb.truck.Repository.PlaceStockEntityRepository;
import com.ldb.truck.Repository.PlaceStockViewEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PlaceStockService {
    @Autowired
    PlaceStockEntityRepository repository;
    @Autowired
    PlaceStockViewEntityRepository placeStockViewEntityRepository;

    @Autowired
    BranchEntityRepository branchEntityRepository;

    public DataResponse getBranch(){
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(branchEntityRepository.getBranchModel());
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
    }


//    public DataResponse getPlaceStockHouse(PlaceStockEntityReq placeStockEntity){
////        USER01	123	USER
////        USER02	123	AUTH
////        USER03	123	BUYER
////        USER04	123	ACCOUNTING
////        USER05	123	PADMIN
//        String role = placeStockEntity.getRole();
//        String branchNo = placeStockEntity.getBrandNo();
//        String userId = placeStockEntity.getUserId();
//        String borNo = placeStockEntity.getBorNo();
//        log.info("userId : "+userId);
//        log.info("role : "+role);
//        log.info("branchNo : "+branchNo);
//        log.info("khNo : "+placeStockEntity.getKhNo());
//        DataResponse response = new DataResponse();
//        try {
//            if("PADMIN".equals(role)){
//                response.setDataResponse(placeStockViewEntityRepository.findAllStockHousesAdminFilterBor());
//            }
//            else if("AUTH".equals(role) || "USERSTOCK".equals(role)){
//                response.setDataResponse(placeStockViewEntityRepository.findAllStockHousesBranchNo(branchNo,borNo));
//            }
//            else if("USER".equals(role)){
//                response.setDataResponse(placeStockViewEntityRepository.findAllStockHousesUserId(userId));
//            }else {
//                response.setDataResponse(null);
//            }
//
//            if(response.getDataResponse() != null){
//                response.setStatus("00");
//                response.setMessage("Success");
//            }else {
//                response.setStatus("05");
//                response.setMessage("Data not Found !!!");
//            }
//        }catch (Exception e){
//            response.setStatus("EE");
//            response.setMessage("Error Data !!!");
//        }
//        return response;
//    }

    public DataResponse getPlaceStockHouse(PlaceStockEntityReq placeStockEntity, String borNoForAdmin, String umission){

        String role = placeStockEntity.getRole();
        String branchNo = placeStockEntity.getBrandNo();
        String userId = placeStockEntity.getUserId();
        String borNo = placeStockEntity.getBorNo();

        DataResponse response = new DataResponse();

        try {
            if ("PADMIN".equals(role)) {
                response.setDataResponse(
                        placeStockViewEntityRepository
                                .findAllStockHousesAdminFilterBor(borNoForAdmin, umission)
                );
            }
            else if ("AUTH".equals(role) || "USERSTOCK".equals(role)) {
                response.setDataResponse(
                        placeStockViewEntityRepository
                                .findAllStockHousesBranchNo(branchNo, borNo)
                );
            }
            else if ("USER".equals(role)) {
                response.setDataResponse(
                        placeStockViewEntityRepository
                                .findAllStockHousesUserId(userId)
                );
            }
            else {
                response.setDataResponse(null);
            }

            if (response.getDataResponse() != null
                    && response.getDataResponse() instanceof List
                    && !((List<?>) response.getDataResponse()).isEmpty()) {

                response.setStatus("00");
                response.setMessage("Success");
            } else {
                response.setStatus("05");
                response.setMessage("Data not Found !!!");
            }


        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error Data !!!");
        }

        return response;
    }
    public DataResponse getPlaceStockHouseByKey(PlaceStockEntityReq placeStockEntity){
        DataResponse response = new DataResponse();
        try {
                response.setDataResponse(placeStockViewEntityRepository.findById(placeStockEntity.getKhId()));
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
    }

    public DataResponse storePlaceStockHouse(PlaceStockEntityReq placeStockEntity, String userId){
        DataResponse response = new DataResponse();
        try {
            PlaceStockEntity entity = mapEntity(placeStockEntity,userId) ;
                response.setDataResponse(repository.save(entity));
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
        public static PlaceStockEntity mapEntity(PlaceStockEntityReq placeStockEntity,String userId) {
            if (placeStockEntity == null) {
                return null;
            }
            PlaceStockEntity entity = new PlaceStockEntity();
            entity.setKhNo(placeStockEntity.getKhNo());
            entity.setKhName(placeStockEntity.getKhName());
            entity.setSole(placeStockEntity.getSole());
            entity.setSoleStep(placeStockEntity.getSoleStep());
            entity.setBlockNo(placeStockEntity.getBlockNo());
            entity.setStatus(placeStockEntity.getStatus());
            entity.setBorNo(placeStockEntity.getBorNo());
            entity.setCreateDate(new Date());
            entity.setUserId(userId);
            return entity;
        }

    public DataResponse updatePlaceStock(PlaceStockEntityReq placeStockEntity,String userId){
        DataResponse response = new DataResponse();
        try {
            String khNo = placeStockEntity.getKhNo();
            String khName = placeStockEntity.getKhName();
            String sole = placeStockEntity.getSole();
            String soleStep= placeStockEntity.getSoleStep();
            String blockNo= placeStockEntity.getBlockNo();
            String status= placeStockEntity.getStatus();
            String borNo= placeStockEntity.getBorNo();
            Long khId = placeStockEntity.getKhId();
                response.setDataResponse(repository.updatePlaceStock(khNo,khName,sole,soleStep,blockNo,status,borNo,userId,khId));
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
