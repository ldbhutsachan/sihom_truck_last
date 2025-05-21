package com.ldb.truck.Controller;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Entity.Brand.BrandReq;
import com.ldb.truck.Entity.Stock.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Service.StockService.StockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("${base_url}")
public class StockProductController {
    @Autowired
    StockServiceImpl stockService;
    @Autowired
    ProfileDao profileDao;
    @CrossOrigin(origins = "*")
    @PostMapping("/saveStockItemDetails.service")
    public ResponseEntity<?> SaveStockItemDetails (@RequestBody StockItemDetailsEntity stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.saveStockIn(stockItemDetailsEntity,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 @CrossOrigin(origins = "*")
    @PostMapping("/updateStockItemDetails.service")
    public ResponseEntity<?> updateStockItemDetails (@RequestBody StockItemDetailsEntity stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.updateStockIn(stockItemDetailsEntity,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/approveStockItemDetails.service")
    public ResponseEntity<?> approveStockItemDetails (@RequestBody StockItemDetailsReq stockItemDetailsReq){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        StockItemDetailsReq data = new StockItemDetailsReq();
        data.setUserId(userId);
        data.setToKen(stockItemDetailsReq.getToKen());
        data.setDetailId(stockItemDetailsReq.getDetailId());
        try {
            response = stockService.approveStockItemDetails(data);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getStockItemDetails.service")
    public ResponseEntity<?> getStockItemDetails2 (@RequestBody StockTxnEntity stockItemDetailsEntity){
        StockItemDetailsRes response  = new StockItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        String role = userProfiles.get(0).getRole();
        Integer deId = stockItemDetailsEntity.getDetailId();
        String billNo = stockItemDetailsEntity.getBillNo();
        try {
            response = stockService.getVStockV2(billNo,role,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getStockItemAuth.service")
    public ResponseEntity<?> getStockItemAuth (@RequestBody StockTxnEntity stockItemDetailsEntity){
        StockItemDetailsRes response  = new StockItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        String role = userProfiles.get(0).getRole();
        Integer deId = stockItemDetailsEntity.getDetailId();
        String billNo = stockItemDetailsEntity.getBillNo();
        try {
            response = stockService.getVStockAuth(billNo,role,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getReportStockItem.service")
    public ResponseEntity<?> getReportStockItem (@RequestBody StockRequest stockRequest ){
        StockItemDetailsRes response  = new StockItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockRequest.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();

        try {
            response = stockService.getVStockReport(stockRequest,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getAlertStock.service")
    public ResponseEntity<?> getAlertStock (){
        DataResponse response  = new DataResponse();
        try {
            response = stockService.getAlertStock();
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
