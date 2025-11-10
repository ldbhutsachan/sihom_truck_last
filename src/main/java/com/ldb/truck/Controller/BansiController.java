package com.ldb.truck.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Bansi.BansiEntity;
import com.ldb.truck.Entity.Bansi.PayTypeEntity;
import com.ldb.truck.Entity.Bansi.PaymentRequestEntity;
import com.ldb.truck.Entity.Bansi.SignatureEntity;
import com.ldb.truck.Model.Bansi.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Service.Bansi.BansiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${base_url}")
public class BansiController {

    @Autowired
    private BansiService bansiService;
    @Autowired
    private MediaUploadService mediaUploadService;

    //save project
    @CrossOrigin(origins = "*")
    @PostMapping("/saveProjectPaymen.service")
    public ResponseEntity<DataResponse> saveReq(@RequestBody BansiEntity bansiEntity) {
        DataResponse response = bansiService.saveProjectPaymen(bansiEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update project
    @CrossOrigin(origins = "*")
    @PostMapping("/updateProjectPaymen.service")
    public ResponseEntity<DataResponse> updateReq(@RequestBody BansiEntity bansiEntity) {
        DataResponse response = bansiService.updateProjectPaymen(bansiEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //show project
    @CrossOrigin(origins = "*")
    @PostMapping("/showProjectPaymen.service")
    public ResponseEntity<DataResponse> showProject(@RequestBody ProjectShowReq request) {
        DataResponse response = bansiService.showProjectPayment(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // save payment_type
    @CrossOrigin(origins = "*")
    @PostMapping("/savePayType.service")
    public ResponseEntity<DataResponse> saveReq(@RequestBody PayTypeEntity payTypeEntity) {
        DataResponse response = bansiService.savePayType(payTypeEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update payment_tpye
    @CrossOrigin(origins = "*")
    @PostMapping("/updatePayType.service")
    public ResponseEntity<Map<String, String>> updatePayType(@RequestBody PayTypeEntity payTypeEntity) {
        Map<String, String> response = bansiService.updatePayType(payTypeEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //show payment_type
    @CrossOrigin(origins = "*")
    @PostMapping("/showPayType.service")
    public ResponseEntity<DataResponse> showPayType(@RequestBody PayTypeReq request) {
        DataResponse response = bansiService.showPayType(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //generate bill_no api
    @CrossOrigin(origins = "*")
    @GetMapping("/generateBillNo.service")
    public ResponseEntity<Map<String, String>> generateBillNoApi() {
        Map<String, String> response = new HashMap<>();
        try {
            String newBillNo = bansiService.generateBillNo();
            response.put("billNo", newBillNo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Cannot generate billNo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    //insert paymentDetail
    @CrossOrigin(origins = "*")
    @PostMapping("/insertPaymentDetail")
    public ResponseEntity<?> insertPaymentRequest(
            @RequestParam("toKen") String token,
            @RequestParam("pay_typeid") Long payTypeId,
            @RequestParam("supplierid") Long supplierId,
            @RequestParam("billNo") String billNo,
            @RequestParam("title") String title,
            @RequestParam("currency") String currency,
            @RequestParam("exchange_rate") String exchangeRate,
            @RequestParam("date") String date,
            @RequestParam(value = "reference_number", required = false) String referenceNumber,
            @RequestParam(value = "reference", required = false) String reference,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam(value = "internal_remark", required = false) String internalRemark,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "tools", required = false) String toolsJson,
            @RequestParam(value = "datermine_date", required = false) String datermine_date,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PaymentRequestDto dto = new PaymentRequestDto();

            dto.setToKen(token);
            dto.setPay_typeid(payTypeId);
            dto.setSupplierid(supplierId);
            dto.setBillNo(billNo);
            dto.setTitle(title);
            dto.setCurrency(currency);
            dto.setExchange_rate(exchangeRate);
            dto.setDate(date);
            dto.setReference_number(referenceNumber);
            dto.setReference(reference);
            dto.setRemark(remark);
            dto.setInternal_remark(internalRemark);
            dto.setTag(tag);
            dto.setDatermine_date(datermine_date);
            dto.setFile(file);

            if (toolsJson != null && !toolsJson.isEmpty()) {
                dto.setTools(mapper.readValue(
                        toolsJson,
                        mapper.getTypeFactory().constructCollectionType(java.util.List.class, PaymentRequestDto.ToolDto.class)
                ));
            }

            PaymentRequestEntity result = bansiService.insertPaymentDetail(dto);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error inserting payment detail: " + e.getMessage());
        }
    }

    //updated paymentDetail
    @CrossOrigin(origins = "*")
    @PostMapping("/updatePaymentDetail")
    public ResponseEntity<?> updatePaymentRequest(
            @RequestParam("billNo") String billNo,  // เปลี่ยนจาก keyId
            @RequestParam("toKen") String token,
            @RequestParam(value = "pay_typeid", required = false) Long payTypeId,
            @RequestParam(value = "supplierid", required = false) Long supplierId,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "currency", required = false) String currency,
            @RequestParam(value = "exchange_rate", required = false) String exchangeRate,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "reference_number", required = false) String referenceNumber,
            @RequestParam(value = "reference", required = false) String reference,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam(value = "internal_remark", required = false) String internalRemark,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "tools", required = false) String toolsJson,
            @RequestParam(value = "datermine_date", required = false) String datermine_date,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PaymentRequestDto dto = new PaymentRequestDto();

            dto.setToKen(token);
            dto.setPay_typeid(payTypeId);
            dto.setSupplierid(supplierId);
            dto.setTitle(title);
            dto.setCurrency(currency);
            dto.setExchange_rate(exchangeRate);
            dto.setDate(date);
            dto.setReference_number(referenceNumber);
            dto.setReference(reference);
            dto.setRemark(remark);
            dto.setInternal_remark(internalRemark);
            dto.setTag(tag);
            dto.setDatermine_date(datermine_date);
            dto.setFile(file);

            if (toolsJson != null && !toolsJson.isEmpty()) {
                dto.setTools(mapper.readValue(
                        toolsJson,
                        mapper.getTypeFactory().constructCollectionType(java.util.List.class, PaymentRequestDto.ToolDto.class)
                ));
            }

            // เรียก service แบบใช้ billNo
            PaymentRequestEntity result = bansiService.updatePaymentDetailByBillNo(billNo, dto);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error updating payment detail: " + e.getMessage());
        }
    }

    //show PaymentDetail
    @CrossOrigin(origins = "*")
    @PostMapping("/listPaymentDetail")
    public PaymentDetailRes listPaymentDetail(@RequestBody PaymentDetailReq req) {
        return bansiService.getPaymentDetails(req);
    }

    //insert signature
    @CrossOrigin(origins = "*")
    @PostMapping("/saveSignature.service")
    public ResponseEntity<DataResponse> saveSignature(
            @RequestParam("userName") String userName,
            @RequestParam("file") MultipartFile file) {

        DataResponse response = new DataResponse();
        try {
            String fileName = mediaUploadService.uploadMedia(file);
            String fileUrl = "http://khounkham.com/images/batery/" + fileName;

            SignatureEntity signatureEntity = new SignatureEntity();
            signatureEntity.setUserName(userName);
            signatureEntity.setSignature(fileUrl);

            response = bansiService.saveSignature(signatureEntity);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Store Data is Error !!");
        }
        return ResponseEntity.ok(response);
    }

    //update signature
    @CrossOrigin(origins = "*")
    @PostMapping("/updateSignature.service")
    public ResponseEntity<DataResponse> updateSignature(
            @RequestParam("sId") Long sId,
            @RequestParam("userName") String userName,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        DataResponse response = new DataResponse();
        try {
            // เตรียม entity สำหรับส่งไป Service
            SignatureEntity signatureEntity = new SignatureEntity();
            signatureEntity.setSid(sId);
            signatureEntity.setUserName(userName);

            // ถ้ามีไฟล์ใหม่ ส่งไป Service เพื่ออัปเดต
            if (file != null && !file.isEmpty()) {
                String fileName = mediaUploadService.uploadMedia(file);
                String fileUrl = "http://khounkham.com/images/batery/" + fileName;
                signatureEntity.setSignature(fileUrl);
            }

            response = bansiService.updateSignature(signatureEntity);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Update Data is Error !!");
        }
        return ResponseEntity.ok(response);
    }
//show
    @CrossOrigin(origins = "*")
    @GetMapping("/getAllSignatures.service")
    public ResponseEntity<DataResponse> getAllSignatures() {
    DataResponse response = new DataResponse();
    try {
        response = bansiService.getAllSignatures();
    } catch (Exception e) {
        e.printStackTrace();
        response.setStatus("EE");
        response.setMessage("Fetch Data Error !!");
    }
    return ResponseEntity.ok(response);
    }






}
