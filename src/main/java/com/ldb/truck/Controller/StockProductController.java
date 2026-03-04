package com.ldb.truck.Controller;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Bor.BorEntityReq;
import com.ldb.truck.Entity.Bor.BorEntityReqSave;
import com.ldb.truck.Entity.ItemPayment.*;
import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Entity.OrderItem.OrderItemReportEntity;
import com.ldb.truck.Entity.OrderItem.OrderRequest;
import com.ldb.truck.Entity.RequestItem.RequestItemDetailsRes;
import com.ldb.truck.Entity.RequestItem.RequestItemEbtity;
import com.ldb.truck.Entity.RequestItem.RequestItems;
import com.ldb.truck.Entity.Stock.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Entity.RequestItem.requestData;
import com.ldb.truck.Model.ReportItemInOutModel.details_item_req;
import com.ldb.truck.Service.StockService.StockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${base_url}")
public class StockProductController {
    @Autowired
    StockServiceImpl stockService;
    @Autowired
    private MediaUploadService mediaUploadService;
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
    @PostMapping("/getAlertStock.service")
    public ResponseEntity<?> getAlertStock (@RequestBody AlertReq alertReq){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(alertReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
//        String branchNo = userProfiles.get(0).getBranchNo();
        String borNo = userProfiles.get(0).getBorNo();
        String role = userProfiles.get(0).getRole();
        try {
            AlertReq req = new AlertReq();
            req.setBorNo(borNo);
            req.setRole(role);
            response = stockService.getAlertStock(req);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//======start order product
    @CrossOrigin(origins = "*")
    @PostMapping("/getOrderItemDetails.service")
    public ResponseEntity<?> getOrderItemDetails(@RequestBody details_item_req stockItemDetailsEntity){
        V_OrderItemDetailsRes response  = new V_OrderItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        String role = userProfiles.get(0).getRole();
        String userMission = userProfiles.get(0).getStaff_id();
        String branchNo = userProfiles.get(0).getBranchNo();
        String status = stockItemDetailsEntity.getStatus();
        String conditionReq = stockItemDetailsEntity.getShowByCondition();

        String startDate = stockItemDetailsEntity.getStartDate();
        String endDate =  stockItemDetailsEntity.getEndDate();
        String borNo = userProfiles.get(0).getBorNo();
        String borNoFone = stockItemDetailsEntity.getBorNo();
        String type_of_order = stockItemDetailsEntity.getTypeOfPay();

        try {
                response = stockService.getOrderItemReport(conditionReq,branchNo,userId, role,status,startDate,endDate,borNo,borNoFone,userMission, type_of_order);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/saveOrderItemDetails.service")
    public ResponseEntity<?> saveOrderItemDetails(@RequestBody OrderRequest stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
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
    public ResponseEntity<?> updateItemInOrder (@RequestBody OrderItemReportEntity stockItemDetailsEntity){
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
        String userId = userProfiles.get(0).getUserId();
        String role = userProfiles.get(0).getRole();
        StockItemDetailsReq data = new StockItemDetailsReq();
        data.setUserId(userId);
        data.setBillNo(stockItemDetailsReq.getBillNo());
        data.setRole(role);
        data.setToKen(stockItemDetailsReq.getToKen());
        data.setDetailId(stockItemDetailsReq.getDetailId());
        data.setPathApi(stockItemDetailsReq.getPathApi());
        try {
             response = stockService.approveStockItemDetailsOrderProd(data);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error Controller !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/approveOrderItemAuthbyAdmin.service")
    public ResponseEntity<?> approveOrderItemAuthbyAdmin(@RequestBody StockItemAuthReq stockItemDetailsReq){
        log.info("===start ====approveOrderItemAuth");
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String role = userProfiles.get(0).getRole();
        StockItemAuthReq data = new StockItemAuthReq();
        data.setUserId(userId);
        data.setBillNo(stockItemDetailsReq.getBillNo());
        data.setRole(role);
        data.setToKen(stockItemDetailsReq.getToKen());
        data.setDetailId(stockItemDetailsReq.getDetailId());
        data.setStatus(stockItemDetailsReq.getStatus());
        try {
             response = stockService.authByAdmin(data,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error Controller !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @CrossOrigin(origins = "*")
//    @PostMapping("/approveOrderItemAuth.service")
//    public ResponseEntity<?> approveOrderItemAuth(
//            @RequestBody StockItemAuthReq stockItemDetailsReq) {
//
//        log.info("=== start approveOrderItemAuth ===");
//
//        DataResponse response = new DataResponse();
//
//        List<Profile> userProfiles =
//                profileDao.getProfileInfoByToken(stockItemDetailsReq.getToKen());
//
//        if (userProfiles.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        Profile profile = userProfiles.get(0);
//
//        StockItemAuthReq data = new StockItemAuthReq();
//        data.setUserId(profile.getUserId());
//        data.setBillNo(stockItemDetailsReq.getBillNo());
//        data.setRole(profile.getRole());
//        data.setStatus(stockItemDetailsReq.getStatus());
//        data.setOrderStatus(stockItemDetailsReq.getOrderStatus());
//        data.setRemark(stockItemDetailsReq.getRemark());
//        data.setToKen(stockItemDetailsReq.getToKen());
//        data.setDetailId(stockItemDetailsReq.getDetailId());
//
//        // new fields
//        data.setPlaceBuy(stockItemDetailsReq.getPlaceBuy());
//        data.setShopeId(stockItemDetailsReq.getShopeId());
//        data.setTypeOfPay(stockItemDetailsReq.getTypeOfPay());
//        data.setDatePay(stockItemDetailsReq.getDatePay());
//        data.setPayStatus(stockItemDetailsReq.getPayStatus());
//        data.setItemArriveDate(stockItemDetailsReq.getItemArriveDate());
//
//
//        try {
//            response = stockService.auth(
//                    data,
//                    profile.getUserId(),
//                    profile.getUserName()
//            );
//        } catch (Exception e) {
//            log.error("Controller error", e);
//            response.setStatus("EE");
//            response.setMessage("Data Error Controller !!");
//        }
//
//        return ResponseEntity.ok(response);
//    }
@CrossOrigin(origins = "*")
@PostMapping("/approveOrderItemAuth.service")
public ResponseEntity<?> approveOrderItemAuth(@RequestBody StockItemAuthReq stockItemDetailsReq) {

    log.info("=== start approveOrderItemAuth ===");

    DataResponse response = new DataResponse();

    // 1️⃣ ดึงข้อมูล user จาก token
    List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsReq.getToKen());
    if (userProfiles.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Profile profile = userProfiles.get(0);

    // 2️⃣ เตรียม DTO สำหรับส่งเข้า Service
    StockItemAuthReq data = new StockItemAuthReq();
    data.setUserId(profile.getUserId());
    data.setBillNo(stockItemDetailsReq.getBillNo());
    data.setRole(profile.getRole());
    data.setStatus(stockItemDetailsReq.getStatus());
    data.setOrderStatus(stockItemDetailsReq.getOrderStatus());
    data.setRemark(stockItemDetailsReq.getRemark());
    data.setToKen(stockItemDetailsReq.getToKen());
    data.setDetailId(stockItemDetailsReq.getDetailId());

    // new fields
    data.setPlaceBuy(stockItemDetailsReq.getPlaceBuy());
    data.setShopeId(stockItemDetailsReq.getShopeId());
    data.setTypeOfPay(stockItemDetailsReq.getTypeOfPay());
    data.setDatePay(stockItemDetailsReq.getDatePay());
    data.setPayStatus(stockItemDetailsReq.getPayStatus());
    data.setItemArriveDate(stockItemDetailsReq.getItemArriveDate());

    // 3️⃣ Upload images จาก Base64 (หลายไฟล์)
    List<String> uploadedUrls = new ArrayList<>();
    if (stockItemDetailsReq.getImageList() != null && !stockItemDetailsReq.getImageList().isEmpty()) {

        for (String base64 : stockItemDetailsReq.getImageList()) {
            if (base64 == null || base64.isBlank()) continue;

            // Skip ถ้าเป็น URL
            if (base64.startsWith("http")) {
                log.warn("Skip URL image: {}", base64);
                uploadedUrls.add(base64);
                continue;
            }

            // ตัด prefix data:image/...;base64, ถ้ามี
            if (base64.startsWith("data:image")) {
                base64 = base64.substring(base64.indexOf(",") + 1);
            }

            try {
                byte[] fileBytes = Base64.getDecoder().decode(base64);

                // สร้าง MultipartFile แบบ override
                final String fileName = System.currentTimeMillis() + "_image.jpg";
                MultipartFile multipartFile = new MultipartFile() {
                    @Override public String getName() { return "file"; }
                    @Override public String getOriginalFilename() { return fileName; }
                    @Override public String getContentType() { return "image/jpeg"; }
                    @Override public boolean isEmpty() { return fileBytes.length == 0; }
                    @Override public long getSize() { return fileBytes.length; }
                    @Override public byte[] getBytes() { return fileBytes; }
                    @Override public InputStream getInputStream() { return new ByteArrayInputStream(fileBytes); }
                    @Override public void transferTo(File dest) throws IOException {
                        try (FileOutputStream fos = new FileOutputStream(dest)) {
                            fos.write(fileBytes);
                        }
                    }
                };

                // upload
                String uploadedFileName = mediaUploadService.uploadMedia(multipartFile);
                String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
                uploadedUrls.add(fileUrl);
                log.info("✅ Uploaded image: {}", fileUrl);

            } catch (IllegalArgumentException e) {
                log.error("❌ Invalid base64 image, skip", e);
                uploadedUrls.add(null);
            } catch (Exception e) {
                log.error("❌ Error uploading image", e);
                uploadedUrls.add(null);
            }
        }
    }

    // 4️⃣ copy uploadedUrls เข้า data
    data.setImageList(uploadedUrls);
    log.info("✅ imageList before checkStatusAuth: {}", data.getImageList());

    // 5️⃣ เรียก Service
    try {
        response = stockService.auth(data, profile.getUserId(), profile.getUserName());
    } catch (Exception e) {
        log.error("Controller error", e);
        response.setStatus("EE");
        response.setMessage("Data Error Controller !!");
    }

    return ResponseEntity.ok(response);
}





    @CrossOrigin(origins = "*")
    @PostMapping("/authItemToStock.service")
    public ResponseEntity<?> authItemToStock(@RequestBody StockItemDetailsReq stockItemDetailsReq){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String role = userProfiles.get(0).getRole();
        StockItemDetailsReq data = new StockItemDetailsReq();
        data.setUserId(userId);
        data.setBillNo(stockItemDetailsReq.getBillNo());
        data.setRole(role);
        data.setToKen(stockItemDetailsReq.getToKen());
        data.setDetailId(stockItemDetailsReq.getDetailId());
        try {
             response = stockService.approveItemToStock(data);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error Controller !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getOrderItemAuth.service")
    public ResponseEntity<?> getOrderItemAuth (@RequestBody StockTxnEntity stockItemDetailsEntity){
        log.info("req body:"+stockItemDetailsEntity.toString());
        OrderAuthResponse response  = new OrderAuthResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String role = userProfiles.get(0).getRole();
        String billNo = stockItemDetailsEntity.getBillNo();
        String branchNo =userProfiles.get(0).getBranchNo();
        String borNo =userProfiles.get(0).getBranchNo();
        try {
            response = stockService.getOrderItemAuth(billNo,role,userId,branchNo,borNo, stockItemDetailsEntity.getStatus());
        }catch (Exception e){
            response.setStatus("00");
            response.setMessage("Data Error Controller!!");
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
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();

        try {
            response = stockService.getReportOrderItem(stockRequest,userId,role,borNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/saveRequestItem.service")
    public ResponseEntity<?> saveRequestItem(@RequestBody RequestItems stockItemDetailsEntity){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsEntity.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String borNo = userProfiles.get(0).getBorNo();
        try {
            response = stockService.saveRequestItem(stockItemDetailsEntity,userId,borNo);
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
    public ResponseEntity<?> approveRequestItem(@RequestBody RequestItemDetailsReq stockItemDetailsReq){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(stockItemDetailsReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserName();
        RequestItemDetailsReq data = new RequestItemDetailsReq();
        data.setBillNo(stockItemDetailsReq.getBillNo());
        data.setStatus(stockItemDetailsReq.getStatus());
        data.setRemark(stockItemDetailsReq.getRemark());
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
        String userMission = userProfiles.get(0).getStaff_id();
        String billNo = stockItemDetailsEntity.getBillNo();
        String status = stockItemDetailsEntity.getStatus();
        String branchNo =userProfiles.get(0).getBranchNo();
        String borNo =userProfiles.get(0).getBorNo();
        String startDate = stockItemDetailsEntity.getStartDate();
        String endDate = stockItemDetailsEntity.getEndDate();


        try {
            log.info("branchNo:"+branchNo);
            response = stockService.getRequestItem(billNo,role,userId,status,branchNo,borNo, userMission, startDate, endDate);
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
        String borId = userProfiles.get(0).getBranchNo();
        try {
            response = stockService.getRequestItemReport(stockRequest,userId,role,borId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getRequestKey.service")
    public ResponseEntity<?> getRequestKey (){
        DataResponse response  = new DataResponse();
        try {
            response = stockService.getRequestKey();
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getRequestItemType.service")
    public ResponseEntity<?> getRequestItemType (){
        DataResponse response  = new DataResponse();
        try {
            response = stockService.getRequestItemType();
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getRequestItemByItemType.service")
    public ResponseEntity<?> getRequestItemByItemType (@RequestBody requestData requestData){
        DataResponse response  = new DataResponse();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(requestData.getToKen());
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String borId = userProfiles.get(0).getBranchNo();
            String boNo = userProfiles.get(0).getBorNo();
            String role = userProfiles.get(0).getRole();
            response = stockService.getRequestItemByItemType(requestData,borId,boNo,role);
            log.info("response :"+response.getDataResponse());

        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/checkKeyOrder.service")
    public ResponseEntity<?> checkKeyOrder (){
        DataResponse response  = new DataResponse();
        try {
            response = stockService.checkKeyOrder();
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getBorAll.service")
    public ResponseEntity<?> getBorAll (@RequestBody BorEntityReq borEntityReq){
        DataResponse response  = new DataResponse();

        try {
            response = stockService.getBorAll(borEntityReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/saveBoEntity.service")
    public ResponseEntity<?> saveBoEntity (@RequestBody BorEntityReqSave borEntityReq){
        DataResponse response  = new DataResponse();
     List<Profile> userProfiles = profileDao.getProfileInfoByToken(borEntityReq.getToKen());
     if (userProfiles.isEmpty()) {
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
     }
     String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.saveBoEntity(borEntityReq,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateBoEntity.service")
    public ResponseEntity<?> updateBoEntity (@RequestBody BorEntityReqSave borEntityReq){
        DataResponse response  = new DataResponse();
     List<Profile> userProfiles = profileDao.getProfileInfoByToken(borEntityReq.getToKen());
     if (userProfiles.isEmpty()) {
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
     }
     String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.updateBoEntity(borEntityReq,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 @CrossOrigin(origins = "*")
    @PostMapping("/disbleBorEntity.service")
    public ResponseEntity<?> disbleBorEntity (@RequestBody BorEntityReqSave borEntityReq){
        DataResponse response  = new DataResponse();
     List<Profile> userProfiles = profileDao.getProfileInfoByToken(borEntityReq.getToKen());
     if (userProfiles.isEmpty()) {
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
     }
     String userId = userProfiles.get(0).getUserName();
        try {
            response = stockService.disbleBorEntity(borEntityReq,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //*********tb bor ********


    @CrossOrigin(origins = "*")
    @PostMapping("/getPaymentItem.service")
    public ResponseEntity<?> getPaymentItem (@RequestBody ItemPaymentReq itemPaymentReq){
        PaymentItemDetailsRes response  = new PaymentItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(itemPaymentReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String role = userProfiles.get(0).getRole();
        String branchNo = userProfiles.get(0).getBranchNo();
        try {
            response = stockService.getPaymentItem(itemPaymentReq,userId,role,branchNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getReportPaymentItem.service")
    public ResponseEntity<?> getReportPaymentItem (@RequestBody ItemPaymentReq itemPaymentReq){
        PaymentItemDetailsRes response  = new PaymentItemDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(itemPaymentReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String role = userProfiles.get(0).getRole();
        String branchNo = userProfiles.get(0).getBranchNo();
        try {
            response = stockService.getReportPaymentItem(itemPaymentReq,userId,role,branchNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data not found !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //*paymentItem
    @CrossOrigin(origins = "*")
    @PostMapping("/PaymentItem.service")
    public ResponseEntity<?> PaymentItem (@RequestBody PaymentDetailsEntityReq itemPaymentReq){
        DataResponse response  = new DataResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(itemPaymentReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        try {
            response = stockService.paymentItem(itemPaymentReq,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data not found !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getPaymentItemDetail.service")
    public ResponseEntity<?> getPaymentItemDetail (@RequestBody ItemPaymentReq itemPaymentReq){
        PaymentDetailsRes response  = new PaymentDetailsRes();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(itemPaymentReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String role = userProfiles.get(0).getRole();
        String branchNo = userProfiles.get(0).getBranchNo();
        try {
            response = stockService.getPaymentItemDetail(itemPaymentReq,userId,role,branchNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
