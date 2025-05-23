package com.ldb.truck.Controller;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Entity.RequestItem.RequestItemDetailsRes;
import com.ldb.truck.Entity.RequestItem.RequestItemEbtity;
import com.ldb.truck.Entity.Stock.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Service.StockService.StockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//======start order product
    @CrossOrigin(origins = "*")
    @PostMapping("/getOrderItemDetails.service")
    public ResponseEntity<?> getOrderItemDetails(@RequestBody OrderItemEntity stockItemDetailsEntity){
        OrderItemDetailsRes response  = new OrderItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        String billNo = stockItemDetailsEntity.getBillNo();
        try {
            response = stockService.getOrderItem(billNo,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/saveOrderItemDetails.service")
    public ResponseEntity<?> saveOrderItemDetails(@RequestBody OrderItemEntity stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.saveItemIn(stockItemDetailsEntity,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateOrderItem.service")
    public ResponseEntity<?> updateItemInOrder (@RequestBody OrderItemEntity stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        try {
              response = stockService.updateOrderItemIn(stockItemDetailsEntity,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/approveOrderItemDetails.service")
    public ResponseEntity<?> approveOrderItemDetails(@RequestBody StockItemDetailsReq stockItemDetailsReq){
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

             response = stockService.approveStockItemDetailsOrderProd(data);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/approveItemToStock.service")
    public ResponseEntity<?> approveItemToStock(@RequestBody StockItemDetailsReq stockItemDetailsReq){
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
              response = stockService.approveItemToStock(data);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //approveStockItemDetailsOrderProd
    @CrossOrigin(origins = "*")
    @PostMapping("/getOrderItemAuth.service")
    public ResponseEntity<?> getOrderItemAuth (@RequestBody StockTxnEntity stockItemDetailsEntity){
        OrderItemDetailsRes response  = new OrderItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        String role = userProfiles.get(0).getRole();
        Integer deId = stockItemDetailsEntity.getDetailId();
        String billNo = stockItemDetailsEntity.getBillNo();
        try {
            response = stockService.getOrderItemAuth(billNo,role,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getReportOrderItem.service")
    public ResponseEntity<?> getReportOrderItem (@RequestBody StockRequest stockRequest ){
        OrderItemDetailsRes response  = new OrderItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockRequest.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();

        try {
            response = stockService.getReportOrderItem(stockRequest,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/saveRequestItem.service")
    public ResponseEntity<?> saveRequestItem(@RequestBody RequestItemEbtity stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.saveRequestItem(stockItemDetailsEntity,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateRequestItem.service")
    public ResponseEntity<?> updateRequestItem (@RequestBody RequestItemEbtity stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.updateRequestItem(stockItemDetailsEntity,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/approveRequestItem.service")
    public ResponseEntity<?> approveRequestItem(@RequestBody StockItemDetailsReq stockItemDetailsReq){
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

            response = stockService.approveRequestItem(data);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getRequestItem.service")
    public ResponseEntity<?> getRequestItem (@RequestBody RequestItemEbtity stockItemDetailsEntity){
        RequestItemDetailsRes response  = new RequestItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        String role = userProfiles.get(0).getRole();
        Integer deId = stockItemDetailsEntity.getDetailId();
        String billNo = stockItemDetailsEntity.getBillNo();
        String status = stockItemDetailsEntity.getStatus();
        try {
            response = stockService.getRequestItem(billNo,role,userId,status);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getRequestItemReport.service")
    public ResponseEntity<?> getRequestItemReport (@RequestBody StockRequest stockRequest ){
        RequestItemDetailsRes response  = new RequestItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockRequest.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        String role = userProfiles.get(0).getRole();
        try {
            response = stockService.getRequestItemReport(stockRequest,userId,role);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
