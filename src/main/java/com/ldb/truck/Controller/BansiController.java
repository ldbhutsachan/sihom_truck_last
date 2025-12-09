package com.ldb.truck.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Bansi.*;
import com.ldb.truck.Model.Bansi.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Service.Bansi.BansiService;
import lombok.extern.slf4j.Slf4j;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
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
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("b_id") Long bId
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
            dto.setB_id(bId);

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
            @RequestParam(value = "bill_status", required = false) String bill_status,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("b_id") Long bId
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
            dto.setBill_status(bill_status);
            dto.setFile(file);
            dto.setB_id(bId);

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

    //approve billNo
    @CrossOrigin(origins = "*")
    @PostMapping("/approveBill.service")
    public ResponseEntity<DataResponse> approveBill(
            @RequestBody Map<String, Object> requestBody
    ) {
        DataResponse response = new DataResponse();

        try {
            String token = (String) requestBody.get("toKen");
            String billStatus = (String) requestBody.get("billStatus");

            // billNo เป็น List
            List<String> billNos = (List<String>) requestBody.get("billNo");

            if (billNos == null || billNos.isEmpty()) {
                throw new Exception("billNo list is empty");
            }

            List<Map<String, Object>> resultList = new ArrayList<>();

            for (String billNo : billNos) {
                PaymentRequestEntity result = bansiService.approveBillNo(billNo, token, billStatus);

                resultList.add(new HashMap<String, Object>() {{
                    put("billNo", billNo);
                    put("new_bill_status", result.getBillStatus());
                    put("approve_by",
                            result.getFinalApproveBy() != null ? result.getFinalApproveBy()
                                    : result.getAccountApproveBy() != null ? result.getAccountApproveBy()
                                    : result.getBansiApproveBy()
                    );
                }});
            }

            response.setStatus("OK");
            response.setMessage("Approve success: " + billNos.size() + " item(s)");
            response.setDataResponse(resultList);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return ResponseEntity.ok(response);
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

    //saveInterviewee
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/saveInterviewee.service", consumes = "multipart/form-data")
    public DataResponse saveInterviewee(
            @RequestParam("interviewee") String interviewee,
            @RequestParam("position") String position,
            @RequestParam("experience") String experience,
            @RequestParam("age") Integer age,
            @RequestParam("tel") String tel,
            @RequestParam(value = "tel1", required = false) String tel1,
            @RequestParam("toKen") String toKen,
            @RequestParam(value = "interview_date", required = false) String interviewDateStr,
            @RequestParam(value = "interview_time", required = false) String interviewTimeStr,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "interviewer1", required = false) String interviewer1,
            @RequestParam(value = "interviewer2", required = false) String interviewer2,
            @RequestParam(value = "interviewer3", required = false) String interviewer3,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @RequestPart(value = "profile", required = false) MultipartFile profileFile,
            @RequestParam(value = "salary", required = false) String salary,
            @RequestParam(value = "currency", required = false) String currency
    ) {

        // ✅ เขียน log ตรงนี้เช็คค่าที่ส่งมา
        log.info("✅ interviewDateStr: {}", interviewDateStr);
        log.info("✅ interviewTimeStr: {}", interviewTimeStr);

        IntervieweeEntity entity = new IntervieweeEntity();
        entity.setInterviewee(interviewee);
        entity.setPosition(position);
        entity.setExperience(experience);
        entity.setAge(age);
        entity.setTel(tel);
        entity.setTel1(tel1);
        entity.setToKen(toKen);
        entity.setInterviewDate(interviewDateStr);
        entity.setInterviewTime(interviewTimeStr);

        entity.setStatus(status);
        entity.setInterviewer1(interviewer1);
        entity.setInterviewer2(interviewer2);
        entity.setInterviewer3(interviewer3);
        entity.setSalary(salary);
        entity.setCurrency(currency);

        return bansiService.saveInterviewee(entity, imageFile, profileFile);
    }


    //
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateInterviewee.service", consumes = "multipart/form-data")
    public DataResponse updateInterviewee(
            @RequestParam("keyId") Integer keyId,
            @RequestParam("interviewee") String interviewee,
            @RequestParam("position") String position,
            @RequestParam("experience") String experience,
            @RequestParam("age") Integer age,
            @RequestParam("tel") String tel,
            @RequestParam(value = "tel1", required = false) String tel1,
            @RequestParam("toKen") String toKen,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "interview_date", required = false) String interviewDateStr,
            @RequestParam(value = "interview_time", required = false) String interviewTimeStr,
            @RequestParam(value = "interviewer1", required = false) String interviewer1,
            @RequestParam(value = "interviewer2", required = false) String interviewer2,
            @RequestParam(value = "interviewer3", required = false) String interviewer3,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @RequestPart(value = "profile", required = false) MultipartFile profileFile,
            @RequestParam(value = "salary", required = false) String salary,
            @RequestParam(value = "currency", required = false) String currency
    ) {
        return bansiService.updateInterviewee(
                keyId, interviewee, position, experience, age, tel, tel1, toKen,
                interviewDateStr, interviewTimeStr,status, interviewer1, interviewer2, interviewer3, imageFile, profileFile, salary, currency
        );
    }

    //show interviewee controller
    @CrossOrigin(origins = "*")
    @PostMapping("/getInterviewee.service")
    public IntervieweeRes getInterviewee(@RequestBody IntervieweeReq req){
        return bansiService.getInterviewee(req);
    }

    //show reportAccounting controller
    @CrossOrigin(origins = "*")
    @PostMapping("/reportAccounting.service")
    public ReportAccountingRes reportAccounting(@RequestBody AccountingReportReq req){
        return bansiService.reportAccounting(req);
    }

    //bank Account controller
    @CrossOrigin(origins = "*")
    @PostMapping("/saveBankAccount.service")
    public ResponseEntity<DataResponse> saveBankAccount(@RequestBody BankEntity bankEntity){
        DataResponse response = bansiService.saveBankAccount(bankEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // bank Account Controller
    @CrossOrigin(origins = "*")
    @PostMapping("/updateBankAccount.service")
    public ResponseEntity<DataResponse> updateBankAccount(@RequestBody BankEntity bankEntity) {
        DataResponse response = bansiService.updateBankAccount(bankEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //show bankAccount controller
    @CrossOrigin(origins = "*")
    @PostMapping("/getBankAccounts.service")
    public ResponseEntity<DataResponse> getAllBankAccounts(@RequestBody BankEntity bankEntity) {
        DataResponse response = bansiService.getAllBankAccounts(bankEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
