package com.ldb.truck.Service.Bansi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.truck.Dao.Bansi.PaymentDetailDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Bansi.*;
import com.ldb.truck.Model.Bansi.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Repository.Bansi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BansiService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BansiRepository bansiRepository;  // ✅ ใช้ entity type จริง
    @Autowired
    private PayTypeRepository payTypeRepository;
    @Autowired
    private PaymentRequestRepository paymentRequestRepository;
    @Autowired
    private IntervieweeRepository intervieweeRepository;
    @Autowired
    private PaymentRequestListRepository paymentRequestListRepository;

    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private PaymentDetailDao paymentDetailDao;;
    @Autowired
    private MediaUploadService mediaUploadService;
    @Autowired
    private SignatureRepository signatureRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private FinanceListRepository financeListRepository;
    @Autowired
    private FinanceRepository financeRepository;
    @Autowired
    private FinanceManageListRepository financeManageListRepository;
    @Autowired
    private FinanceViewRepository financeViewRepository;
    @Autowired
    private FinanceHisRepository financeHisRepository;
    @Autowired
    private FinancePayHisRepo financePayHisRepo;
    @Autowired
    private SupplierNotPayRepo supplierNotPayRepo;



    public DataResponse saveProjectPaymen(BansiEntity bansiEntity) {
        DataResponse response = new DataResponse();
        try {
            // ตรวจ token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(bansiEntity.getToKen());
            if(userProfiles.isEmpty()){
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }
            String role = userProfiles.get(0).getRole();

            // ตัวอย่าง: ถ้า role SUPERBANSI ให้ bansi = "1"
            if("SUPERBANSI".equalsIgnoreCase(role)){
                bansiEntity.setBansi("bansi");
            } else if ("SUPERACCOUNT".equalsIgnoreCase(role)) {
                bansiEntity.setBansi("accounting");
            } else {
                bansiEntity.setBansi("");
            }

            response.setDataResponse(bansiRepository.save(bansiEntity));
            response.setStatus("00");
            response.setMessage("Success");
        } catch(Exception e){
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Store Data is Error !!");
        }
        return response;
    }

    // update
    public DataResponse updateProjectPaymen(BansiEntity bansiEntity) {
        DataResponse response = new DataResponse();
        try {
            if (bansiEntity.getReqId() == null) {
                response.setStatus("01");
                response.setMessage("reqId is required for update");
                return response;
            }
            // ตรวจ token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(bansiEntity.getToKen());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            // หา entity เดิมจาก DB
            BansiEntity existing = bansiRepository.findById(bansiEntity.getReqId()).orElse(null);
            if (existing == null) {
                response.setStatus("02");
                response.setMessage("Record not found");
                return response;
            }

            // update fields
            existing.setReqName(bansiEntity.getReqName());
            existing.setItem_typeid(bansiEntity.getItem_typeid());

            String role = userProfiles.get(0).getRole();
            if ("SUPERBANSI".equalsIgnoreCase(role)) {
                existing.setBansi("1");
            } else if ("SUPERACCOUNT".equalsIgnoreCase(role)) {
                existing.setBansi("2");
            } else {
                existing.setBansi(bansiEntity.getBansi());
            }

            response.setDataResponse(bansiRepository.save(existing));
            response.setStatus("00");
            response.setMessage("Update Success");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Update Data is Error !!");
        }
        return response;
    }

    //show
    public DataResponse showProjectPayment(ProjectShowReq request) {
        DataResponse response = new DataResponse();
        try {
            List<Profile> profiles = profileDao.getProfileInfoByToken(request.getToKen());
            if (profiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            String role = profiles.get(0).getRole();

            if (!"SUPERBANSI".equalsIgnoreCase(role)
                    && !"SUPERACCOUNT".equalsIgnoreCase(role)
                    && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
                response.setStatus("01");
                response.setMessage("Access Denied");
                return response;
            }

            List<Object[]> rawList = bansiRepository.findByItemTypeIdOrBansi(request.getItemTypeId());
            List<ProjectPaymentModel> result = new ArrayList<>();

            for (Object[] row : rawList) {
                ProjectPaymentModel model = new ProjectPaymentModel();
                model.setReq_id(((Number) row[0]).longValue());
                model.setPayment_type((String) row[1]);
                model.setBansi((String) row[2]);
                model.setProjectID(((Number) row[3]).longValue());
                model.setProject_name((String) row[4]);
                result.add(model);
            }

            response.setStatus("00");
            response.setMessage("Success");
            response.setDataResponse(result);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error fetching data");
        }
        return response;
    }


    // insertPayType
    public DataResponse savePayType(PayTypeEntity payTypeEntity) {
        DataResponse response = new DataResponse();
        try {
            // ตรวจ token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(payTypeEntity.getToKen());
            if(userProfiles.isEmpty()){
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            String role = userProfiles.get(0).getRole();

            // ถ้า role ไม่ใช่ SUPERBANSI ให้ return 01
            if (!"SUPERBANSI".equalsIgnoreCase(role)
                    && !"SUPERACCOUNT".equalsIgnoreCase(role)
                    && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
                response.setStatus("01");
                response.setMessage("No right to save");
                return response;
            }


            // กำหนด date_create อัตโนมัติเป็น LocalDateTime.now()
            payTypeEntity.setDateCreate(LocalDateTime.now());

            // save data
            response.setDataResponse(payTypeRepository.save(payTypeEntity));
            response.setStatus("00");
            response.setMessage("Success");

        } catch(Exception e){
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Store Data is Error !!");
        }
        return response;
    }

    // update paytype
    public Map<String, String> updatePayType(PayTypeEntity payTypeEntity) {
        Map<String, String> result = new HashMap<>();
        try {
            // ตรวจ token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(payTypeEntity.getToKen());
            if (userProfiles.isEmpty()) {
                result.put("status", "05");
                result.put("message", "Unauthorized");
                return result;
            }

            String role = userProfiles.get(0).getRole();
            if (!"SUPERBANSI".equalsIgnoreCase(role)
                    && !"SUPERACCOUNT".equalsIgnoreCase(role)
                    && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
                result.put("status", "06");
                result.put("message", "No right to update");
                return result;
            }

            // หา entity ที่ต้องการ update
            Optional<PayTypeEntity> existingOpt = payTypeRepository.findById(payTypeEntity.getPid());
            if (existingOpt.isEmpty()) {
                result.put("status", "04");
                result.put("message", "PayType not found");
                return result;
            }

            PayTypeEntity existing = existingOpt.get();
            existing.setTypeName(payTypeEntity.getTypeName());
            existing.setReqId(payTypeEntity.getReqId());
            existing.setDateCreate(LocalDateTime.now());

            payTypeRepository.save(existing);

            result.put("status", "00");
            result.put("message", "update Success");
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "EE");
            result.put("message", "Update Error !!");
            return result;
        }
    }

    //show payment_type
    public DataResponse showPayType(PayTypeReq request) {
        DataResponse response = new DataResponse();
        try {
            // ตรวจสอบสิทธิ์
            List<Profile> profiles = profileDao.getProfileInfoByToken(request.getToKen());
            if (profiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            String role = profiles.get(0).getRole();

            // ตรวจ role
//            if (!"SUPERBANSI".equalsIgnoreCase(role)) {
//                response.setStatus("01");
//                response.setMessage("Access Denied");
//                return response;
//            }
            if (!"SUPERBANSI".equalsIgnoreCase(role)
                    && !"SUPERACCOUNT".equalsIgnoreCase(role)
                    && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
                response.setStatus("01");
                response.setMessage("Access Denied");
                return response;
            }

            // ดึงข้อมูลจาก DB
            List<Object[]> rawList = payTypeRepository.findPayTypeByReqId(request.getReq_id());
            List<PaymentModel> result = new ArrayList<>();

            for (Object[] row : rawList) {
                PaymentModel model = new PaymentModel();
                model.setPid(((Number) row[0]).longValue());
                model.setPay_type((String) row[1]);
                model.setDate_create((row[2] != null) ? ((java.sql.Timestamp) row[2]).toLocalDateTime() : null);
                model.setSmall_project((String) row[3]);
                model.setBig_project((String) row[4]);
                model.setSmall_project_id(((Number) row[5]).longValue());
                model.setType_of_pay((String) row[6]);
                result.add(model);
            }

            response.setStatus("00");
            response.setMessage("Success");
            response.setDataResponse(result);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error fetching data");
        }
        return response;
    }

    // generate bill_no
    public String generateBillNo() {
        String lastBillNo = paymentRequestRepository.findLastBillNo();

        String prefix = "PAY-";
        int number = 1; // ค่าเริ่มต้น

        if (lastBillNo != null && !lastBillNo.isEmpty()) {
            try {
                String[] parts = lastBillNo.split("-");
                number = Integer.parseInt(parts[1]) + 1; // เพิ่มจากล่าสุด
            } catch (Exception e) {
                number = 1; // กรณี format ผิด
            }
        }

        // format ให้เป็น zero-padded 4 หลัก (PAY-0001, PAY-0010, ...)
        // สำหรับเลข > 9999 ก็ยังสามารถเพิ่มได้ (PAY-10001)
        String formattedNumber = String.format("%04d", number);

        return prefix + formattedNumber;
    }

    // generate nextBillno ຖ້າລະຫັດບຶນມາຮອດຊ້ຳກັນ
    @Transactional
    public synchronized String generateNextBillNo() {
        String lastBillNo = paymentRequestRepository.findLastBillNo();
        String prefix = "PAY-";
        int number = 1;

        if (lastBillNo != null && !lastBillNo.isEmpty()) {
            try {
                String[] parts = lastBillNo.split("-");
                number = Integer.parseInt(parts[1]) + 1;
            } catch (Exception e) {
                number = 1;
            }
        }

        return prefix + String.format("%04d", number);
    }




    // insert payment Detail
    public PaymentRequestEntity insertPaymentDetail(PaymentRequestDto req) throws Exception {
        PaymentRequestEntity entity = new PaymentRequestEntity();

        // check token
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToKen());
        if (userProfiles.isEmpty()) {
            throw new Exception("❌ Unauthorized: Invalid token");
        }
        Profile user = userProfiles.get(0);
        String role = user.getRole();

        if (!"SUPERBANSI".equalsIgnoreCase(role)
                && !"SUPERACCOUNT".equalsIgnoreCase(role)
                && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
            throw new Exception("No right to update (role: " + role + ")");
        }
        if ("SUPERBANSI".equalsIgnoreCase(role)) {
            entity.setDataType("bansi");
        } else if ("SUPERACCOUNT".equalsIgnoreCase(role)) {
            entity.setDataType("accounting");
        } else {
            entity.setDataType("admin");
        }
        // set user_id from token
        entity.setUserId(Long.valueOf(user.getUserId()));
        entity.setPayTypeId(req.getPay_typeid());
        entity.setSupplierId(req.getSupplierid());
        entity.setTitle(req.getTitle());
        entity.setCurrency(req.getCurrency());
        entity.setExchangeRate(req.getExchange_rate());

        // Generate billNo auto
        entity.setBillNo(generateNextBillNo());

        // adjust Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = sdf.parse(req.getDate());
        entity.setDate(sdf.format(parsedDate));

        entity.setReferenceNumber(req.getReference_number());
        entity.setReference(req.getReference());
        entity.setRemark(req.getRemark());
        entity.setInternalRemark(req.getInternal_remark());
        entity.setTag(req.getTag());
        entity.setDatertimeDate(req.getDatermine_date());
        entity.setDateCreate(LocalDate.now());
        entity.setBillStatus("wait");
        entity.setBId(req.getB_id());

//        MultipartFile[] files = req.getFiles(); // รับหลายไฟล์จาก @RequestParam("files") MultipartFile[]
//        List<String> fileUrls = new ArrayList<>();
//
//        if (files != null && files.length > 0) {
//            for (MultipartFile file : files) {
//                if (!file.isEmpty()) {
//                    String uploadedFileName = mediaUploadService.uploadMedia(file);
//                    String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
//                    fileUrls.add(fileUrl);
//                }
//            }
//            // แปลง List เป็น JSON string
//            ObjectMapper mapper = new ObjectMapper();
//            String filesJson = mapper.writeValueAsString(fileUrls);
//            entity.setFile(filesJson);
//        } else {
//            entity.setFile("[]"); // ไม่มีไฟล์
//        }
        MultipartFile[] files = req.getFile();
        if (files != null && files.length > 0) {
            List<String> fileUrls = new ArrayList<>();
            for (MultipartFile f : files) {
                if (!f.isEmpty()) {
                    String uploadedFileName = mediaUploadService.uploadMedia(f);
                    String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
                    fileUrls.add(fileUrl);
                    log.info("Uploaded file successfully: {}", fileUrl);
                }
            }
            // join เป็น string เดียวเก็บใน entity (ถ้าต้องการ)
            entity.setFile(String.join(",", fileUrls));
        } else {
            log.warn("No files uploaded for payment detail.");
            entity.setFile("http://khounkham.com/images/image.jpg");
        }


        // save main data
        PaymentRequestEntity saved = paymentRequestRepository.save(entity);

        // save list data
        if (req.getTools() != null) {
            for (PaymentRequestDto.ToolDto t : req.getTools()) {
                PaymentDetailListEntity list = new PaymentDetailListEntity();
                list.setKeyId(saved.getKeyId());
                list.setBillNo(saved.getBillNo());
                list.setListName(t.getList_name());
                list.setQty(t.getQty());
                list.setUnit(t.getUnit());
                list.setPrice(t.getPrice());
                list.setReduce(t.getReduce());
                list.setReduceStatus(t.getReduce_status());
                list.setTax(t.getTax());
                list.setTaxStatus(t.getTax_status());
                list.setUsdPrice(t.getUsdPrice());
                paymentRequestListRepository.save(list);
            }
        }
        return saved;
    }

    //update
    @Transactional
    public PaymentRequestEntity updatePaymentDetailByBillNo(String billNo, PaymentRequestDto req) throws Exception {
        // หา entity จาก billNo
        PaymentRequestEntity entity = paymentRequestRepository.findByBillNo(billNo);
        if (entity == null) {
            throw new Exception("❌ PaymentRequest not found with billNo: " + billNo);
        }
        // check token
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToKen());
        if (userProfiles.isEmpty()) {
            throw new Exception("❌ Unauthorized: Invalid token");
        }
        Profile user = userProfiles.get(0);
        String role = user.getRole();
        if (!"SUPERBANSI".equalsIgnoreCase(role)
                && !"SUPERACCOUNT".equalsIgnoreCase(role)
                && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
            throw new Exception("No right to update (role: " + role + ")");
        }
        if ("SUPERBANSI".equalsIgnoreCase(role)) {
            entity.setDataType("bansi");
        } else if ("SUPERACCOUNT".equalsIgnoreCase(role)) {
            entity.setDataType("accounting");
        } else {
            entity.setDataType("admin");
        }
        // **billNo ไม่เปลี่ยน**
        // entity.setBillNo(...) //

        // update fields อื่น ๆ
        entity.setPayTypeId(req.getPay_typeid() != null ? req.getPay_typeid() : entity.getPayTypeId());
        entity.setSupplierId(req.getSupplierid() != null ? req.getSupplierid() : entity.getSupplierId());
        entity.setTitle(req.getTitle() != null ? req.getTitle() : entity.getTitle());
        entity.setCurrency(req.getCurrency() != null ? req.getCurrency() : entity.getCurrency());
        entity.setExchangeRate(req.getExchange_rate() != null ? req.getExchange_rate() : entity.getExchangeRate());
        entity.setReferenceNumber(req.getReference_number() != null ? req.getReference_number() : entity.getReferenceNumber());
        entity.setReference(req.getReference() != null ? req.getReference() : entity.getReference());
        entity.setRemark(req.getRemark() != null ? req.getRemark() : entity.getRemark());
        entity.setInternalRemark(req.getInternal_remark() != null ? req.getInternal_remark() : entity.getInternalRemark());
        entity.setTag(req.getTag() != null ? req.getTag() : entity.getTag());
        entity.setDatertimeDate(req.getDatermine_date() != null ? req.getDatermine_date() : entity.getDatertimeDate());
        entity.setBillStatus(req.getBill_status() !=null ? req.getBill_status() : entity.getBillStatus());
        entity.setBId(req.getB_id() !=null ? req.getB_id() : entity.getBId());

        if (req.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(req.getDate());
            entity.setDate(sdf.format(parsedDate));
        }
        //  Upload file (ใช้ service เดิม)
//        MultipartFile file = req.getFile();
//        if (file != null && !file.isEmpty()) {
//            String uploadedFileName = mediaUploadService.uploadMedia(file);
//            String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
//            entity.setFile(fileUrl);
//            log.info("✅ Updated file uploaded: {}", fileUrl);
//        } else {
//            log.info("ℹ️ No new file uploaded. Keep old file: {}", entity.getFile());
//        }
        // Upload files (ใช้ service เดิม แต่รองรับหลายไฟล์)
        MultipartFile[] files = req.getFile(); // DTO ต้องมี getFiles()
        if (files != null && files.length > 0) {
            List<String> fileUrls = new ArrayList<>();

            // ถ้า entity มีไฟล์เก่าอยู่แล้ว ให้เก็บไว้ก่อน
            if (entity.getFile() != null && !entity.getFile().isEmpty()) {
                // แยก string เดิมเป็น list
                String[] existingFiles = entity.getFile().split(",");
                fileUrls.addAll(Arrays.asList(existingFiles));
            }

            // upload ไฟล์ใหม่ทั้งหมด
            for (MultipartFile f : files) {
                if (!f.isEmpty()) {
                    String uploadedFileName = mediaUploadService.uploadMedia(f);
                    String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
                    fileUrls.add(fileUrl);
                    log.info("✅ Uploaded new file: {}", fileUrl);
                }
            }

            // เซตค่าไฟล์รวมทั้งหมดกลับไปที่ entity
            entity.setFile(String.join(",", fileUrls));

        } else {
            log.info("ℹ️ No new files uploaded. Keep old files: {}", entity.getFile());
        }


        // save main data
        PaymentRequestEntity saved = paymentRequestRepository.save(entity);

        // update tools list
        if (req.getTools() != null) {
            // delete old items
            paymentRequestListRepository.deleteAll(
                    paymentRequestListRepository.findAll()
                            .stream()
                            .filter(item -> billNo.equals(item.getBillNo()))
                            .collect(Collectors.toList())
            );

            // save new items
            for (PaymentRequestDto.ToolDto t : req.getTools()) {
                PaymentDetailListEntity list = new PaymentDetailListEntity();
                list.setKeyId(saved.getKeyId());
                list.setBillNo(saved.getBillNo());
                list.setListName(t.getList_name());
                list.setQty(t.getQty());
                list.setUnit(t.getUnit());
                list.setPrice(t.getPrice());
                list.setReduce(t.getReduce());
                list.setReduceStatus(t.getReduce_status());
                list.setTax(t.getTax());
                list.setTaxStatus(t.getTax_status());
                list.setUsdPrice(t.getUsdPrice());
                paymentRequestListRepository.save(list);
            }
        }

        return saved;
    }
    // showing paymentDetail
    public PaymentDetailRes getPaymentDetails(PaymentDetailReq req) {
        PaymentDetailRes result = new PaymentDetailRes();

        // ตรวจสอบ token
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToKen());
        if (userProfiles.isEmpty()) {
            result.setStatus("01");
            result.setMessage("Token invalid");
            result.setData(new ArrayList<>());
            return result;
        }

        Profile profile = userProfiles.get(0);
        String role = profile.getRole();
        if (role != null) {
            role = role.trim(); // ลบช่องว่าง
        }
        log.info("User role: '{}'", role);

        boolean isAllowed =
                "SUPERBANSI".equalsIgnoreCase(role) ||
                        "SUPERACCOUNT".equalsIgnoreCase(role) ||
                        "FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)||
                        "BANSIAPPROVE".equalsIgnoreCase(role);
        if (!isAllowed) {
            result.setStatus("02");
            result.setMessage("No permission");
            result.setData(new ArrayList<>());
            return result;
        }
        // เรียก DAO พร้อม role + userId
        List<PaymentDetailModel> data = paymentDetailDao.findPaymentDetails(
                req.getStartDate(),
                req.getEndDate(),
                req.getItemTypeid(),
                req.getReq_id(),
                req.getPid(),
                role
        );
        result.setStatus("00");
        result.setMessage("Success");
        result.setData(data);
        return result;
    }

    // approve billNo of Bansi
    @Transactional
    public PaymentRequestEntity approveBillNo(String billNo, String token, String billStatus) throws Exception {
        // หา entity จาก billNo
        PaymentRequestEntity entity = paymentRequestRepository.findByBillNo(billNo);
        if (entity == null) {
            throw new Exception("❌ PaymentRequest not found with billNo: " + billNo);
        }

        List<Profile> userProfiles = profileDao.getProfileInfoByToken(token);
        if (userProfiles == null || userProfiles.isEmpty()) {
            throw new Exception("❌ Token invalid");
        }
        Profile user = userProfiles.get(0);
        String role = user.getRole();
        String approveBy = user.getUserName();
        String status = entity.getBillStatus();
        LocalDateTime now = LocalDateTime.now();

        // กรณี client ส่ง "return"
        if ("return".equalsIgnoreCase(billStatus)) {
            entity.setBillStatus("wait");
            entity.setReturnBy(approveBy);
            entity.setReturnDate(now);
            return paymentRequestRepository.save(entity);
        }

        if (!"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
            if ("wait".equals(status) && !"bansiapprove".equalsIgnoreCase(role)) {
                throw new Exception("this user can't approve this bill: 'wait'");
            }
//            else if ("wait-finance".equals(status) && !"superaccount".equalsIgnoreCase(role)) {
//                throw new Exception("this user can't approve this bill: 'wait-finance'");
//            }
        }

        // อนุมัติขั้นต่อไป
        if ("wait".equals(status) || "".equals(status)) {
            entity.setBasiApproveDate(now);
            entity.setBansiApproveBy(approveBy);
            entity.setBillStatus("ok");
//            entity.setBillStatus("wait-finance");
        }
//        else if ("wait-finance".equals(status)) {
//            entity.setAccountApproveDate(now);
//            entity.setAccountApproveBy(approveBy);
//            entity.setBillStatus("ok");
//        }
        else if ("ok".equals(status)) {
            throw new Exception("this bill has done approving (status = ok).");
        } else {
            throw new Exception("the bill_status is incorrect : " + status);
        }

        return paymentRequestRepository.save(entity);
    }




    //save signature
    public DataResponse saveSignature(SignatureEntity signatureEntity) {
        DataResponse response = new DataResponse();
        try {
            // บันทึกลง DB
            SignatureEntity saved = signatureRepository.save(signatureEntity);

            // set response
            response.setDataResponse(saved); // ข้อมูลที่บันทึกแล้ว
            response.setStatus("00");
            response.setMessage("Insert signature successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Store Data is Error !!");
        }
        return response;
    }
    //update
    public DataResponse updateSignature(SignatureEntity signatureEntity) {
        DataResponse response = new DataResponse();
        try {
            // ตรวจสอบว่ามีข้อมูลอยู่ใน DB
            SignatureEntity existing = signatureRepository.findById(signatureEntity.getSid()).orElse(null);

            if (existing == null) {
                response.setStatus("EE");
                response.setMessage("Signature not found with id: " + signatureEntity.getSid());
                return response;
            }

            // อัปเดต userName เสมอ
            existing.setUserName(signatureEntity.getUserName());

            // อัปเดต signature **เฉพาะถ้ามีค่าใหม่ส่งมาจาก client**
            if (signatureEntity.getSignature() != null) {
                existing.setSignature(signatureEntity.getSignature());
            }

            // บันทึกลง DB
            SignatureEntity updated = signatureRepository.save(existing);

            response.setDataResponse(updated);
            response.setStatus("00");
            response.setMessage("Update signature successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Update Data is Error !!");
        }
        return response;
    }

    //show signature
    public DataResponse getAllSignatures() {
        DataResponse response = new DataResponse();
        try {
            List<SignatureEntity> list = signatureRepository.findAll();
            response.setDataResponse(list);
            response.setStatus("00");
            response.setMessage("Fetch all signatures successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Fetch Data Error !!");
        }
        return response;
    }

    // insert interviewee
    public DataResponse saveInterviewee(IntervieweeEntity intervieweeEntity,
                                        MultipartFile imageFile,
                                        MultipartFile profileFile) {

        DataResponse response = new DataResponse();

        try {
            // ===== 1. ตรวจสอบ token =====
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(intervieweeEntity.getToKen());
            if (userProfiles.isEmpty()) {
                response.setStatus("EE");
                response.setMessage("Token invalid");
                return response;
            }

            Profile user = userProfiles.get(0);
            String role = user.getRole();
            if(!"HR".equalsIgnoreCase(role)) {
                throw new Exception("No right to save (role: " + role + ")");
            }

            intervieweeEntity.setUserId(Integer.valueOf(user.getUserId()));
            // ===== 2. Set default date/time =====
            if (intervieweeEntity.getDateCreate() == null)
                intervieweeEntity.setDateCreate(LocalDate.now());


            // ===== 3. Save image file =====
            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadedFileName = mediaUploadService.uploadMedia(imageFile);
                String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
                intervieweeEntity.setImage(fileUrl);
                log.info(" Image uploaded: {}", fileUrl);
            } else {
                log.info("ℹ No new image uploaded. Keep old image: {}", intervieweeEntity.getImage());
            }

            // ===== 4. Save profile file =====
            if (profileFile != null && !profileFile.isEmpty()) {
                String uploadedFileName = mediaUploadService.uploadMedia(profileFile);
                String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
                intervieweeEntity.setProfile(fileUrl);
                log.info(" Profile uploaded: {}", fileUrl);
            } else {
                log.info("ℹ No new profile uploaded. Keep old profile: {}", intervieweeEntity.getProfile());
            }

            // ===== 5. Save data to DB =====
            IntervieweeEntity saved = intervieweeRepository.save(intervieweeEntity);

            response.setStatus("OK");
            response.setMessage("Store data success");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error saving data");
        }
        return response;
    }

    //update interviewee
    public DataResponse updateInterviewee(Integer keyId, String interviewee, String position, String experience,
                                          Integer age, String tel, String tel1, String toKen,
                                          String interviewDateStr, String interviewTimeStr, String status,
                                          String interviewer1, String interviewer2, String interviewer3,
                                          MultipartFile imageFile, MultipartFile profileFile, String salary, String currency) {
        DataResponse response = new DataResponse();
        try {
            // ===== ตรวจสอบ token =====
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
            if (userProfiles.isEmpty()) {
                response.setStatus("EE");
                response.setMessage("Token invalid");
                return response;
            }

            // ===== โหลด entity จาก DB =====
            Optional<IntervieweeEntity> optionalEntity = intervieweeRepository.findById(keyId);
            if (optionalEntity.isEmpty()) {
                response.setStatus("EE");
                response.setMessage("Interviewee not found");
                return response;
            }

            IntervieweeEntity entity = optionalEntity.get();

            // ===== อัปเดต fields =====
            entity.setInterviewee(interviewee);
            entity.setPosition(position);
            entity.setExperience(experience);
            entity.setAge(age);
            entity.setTel(tel);
            entity.setTel1(tel1);
            entity.setInterviewTime(interviewTimeStr);
            entity.setInterviewTime(interviewTimeStr);
            entity.setStatus(status);
            entity.setInterviewer1(interviewer1);
            entity.setInterviewer2(interviewer2);
            entity.setInterviewer3(interviewer3);
            entity.setSalary(salary);
            entity.setCurrency(currency);

            // ===== อัปเดต image ถ้ามี =====
            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadedFileName = mediaUploadService.uploadMedia(imageFile);
                String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
                entity.setImage(fileUrl);
                log.info(" Image updated: {}", fileUrl);
            }

            // ===== อัปเดต profile ถ้ามี =====
            if (profileFile != null && !profileFile.isEmpty()) {
                String uploadedFileName = mediaUploadService.uploadMedia(profileFile);
                String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
                entity.setProfile(fileUrl);
                log.info(" Profile updated: {}", fileUrl);
            }

            // ===== save =====
            intervieweeRepository.save(entity);

            response.setStatus("OK");
            response.setMessage("Update success");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error updating data");
        }

        return response;
    }

    //showing interviewee service
    public IntervieweeRes getInterviewee(IntervieweeReq req){
        IntervieweeRes result = new IntervieweeRes();

        // 🔹 check token
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToKen());
        if(userProfiles.isEmpty()){
            result.setStatus("01");
            result.setMessage("User does not exist");
            return result;
        }

        //  check role
        boolean isHR = "HR".equalsIgnoreCase(userProfiles.get(0).getRole());
        if(!isHR){
            result.setStatus("02");
            result.setMessage("This User has no permission");
            return result;
        }

        // get data
        List<IntervieweeModel> data = paymentDetailDao.findInterviewees(
                req.getStatus(),
                req.getStartDate(),
                req.getEndDate()
        );
        result.setStatus("00");
        result.setMessage("Success fetching Interviewee Data");
        result.setData(data);

        return result;
    }

    //show ReportAccounting service
    public ReportAccountingRes reportAccounting(AccountingReportReq req){
        ReportAccountingRes result = new ReportAccountingRes();

        // ตรวจสอบ token
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToKen());
        if (userProfiles.isEmpty()) {
            result.setStatus("01");
            result.setMessage("Token invalid");
            result.setData(new ArrayList<>());
            return result;
        }
        Profile profile = userProfiles.get(0);
        String role = profile.getRole();
        if (role != null) role = role.trim();

        boolean isAllowed =
                "SUPERBANSI".equalsIgnoreCase(role) ||
                        "SUPERACCOUNT".equalsIgnoreCase(role) ||
                        "FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role);

        if (!isAllowed) {
            result.setStatus("02");
            result.setMessage("No permission");
            result.setData(new ArrayList<>());
            return result;
        }
        // ดึงข้อมูลจาก DAO
        List<AccountingReportModel> data = paymentDetailDao.reportAccounting(
                req.getBig_project_id(),
                req.getSmall_project_id(),
                req.getPay_type_id(),
                req.getType_of_pay(),
                req.getStartDate(),
                req.getEndDate(),
                role
        );
        // --------------------------------------------------------------------
        // SUM RECEIVE
        // --------------------------------------------------------------------
        double sumReceiveUsd = 0, sumReceiveLak = 0, sumReceiveThb = 0;

        // --------------------------------------------------------------------
        // SUM PAY
        // --------------------------------------------------------------------
        double sumPayUsd = 0, sumPayLak = 0, sumPayThb = 0;

        // New variables for USD equivalent
        double sumReceiveUsdEquivalent = 0;
        double sumPayUsdEquivalent = 0;

        for (AccountingReportModel m : data) {

            if (m.getCurrency() == null) continue;

            String currency = m.getCurrency().toUpperCase();
            Double price = m.getPrice();
            if (price == null) price = 0.0;  // ถ้า null ให้เป็น 0
            Double defaulUSD = m.getUsd_price();
            if (defaulUSD == null) defaulUSD = 0.0;  // ถ้า null ให้เป็น 0
            String type = (m.getTypeOf() == null) ? "" : m.getTypeOf().toUpperCase();
            // ---- SUM RECEIVE ----
            if (type.equals("RECEIVE")) {
                switch (currency) {
                    case "USD": sumReceiveUsd += price; break;
                    case "LAK": sumReceiveLak += price; break;
                    case "THB": sumReceiveThb += price; break;
                }
                sumReceiveUsdEquivalent += defaulUSD; // Sum USD equivalent for RECEIVE

            }

            // ---- SUM PAY ----
            else if (type.equals("PAY")) {
                switch (currency) {
                    case "USD": sumPayUsd += price; break;
                    case "LAK": sumPayLak += price; break;
                    case "THB": sumPayThb += price; break;
                }
                sumPayUsdEquivalent += defaulUSD; // Sum USD equivalent for PAY
            }
        }

        result.setStatus("00");
        result.setMessage("Success");
        result.setData(data);
        // SUM RECEIVE
        result.setSumReceiveUsd(sumReceiveUsd);
        result.setSumReceiveLak(sumReceiveLak);
        result.setSumReceiveThb(sumReceiveThb);
        result.setSumReceiveUsdDefual(sumReceiveUsdEquivalent);
        // SUM PAY
        result.setSumPayUsd(sumPayUsd);
        result.setSumPayLak(sumPayLak);
        result.setSumPayThb(sumPayThb);
        result.setSumPayUsdDefual(sumPayUsdEquivalent);
        return result;
    }

    //insertBank
    public DataResponse saveBankAccount(BankEntity bankEntity){
        DataResponse response = new DataResponse();

        try {
            // check token
            if (bankEntity.getToKen() == null || bankEntity.getToKen().isEmpty()) {
                response.setStatus("05");
                response.setMessage("Token missing");
                return response;
            }

            List<Profile> userProfiles = profileDao.getProfileInfoByToken(bankEntity.getToKen());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            Profile user = userProfiles.get(0);
            String role = user.getRole();
            String userId = user.getUserId();

            // allowed role list
            List<String> allowed = Arrays.asList("SUPERBANSI", "SUPERACCOUNT", "FOR_DOCUMENT_ADMIN");
            if (!allowed.contains(role.toUpperCase())) {
                response.setStatus("01");
                response.setMessage("No right to save");
                return response;
            }

            // auto-set fields
            bankEntity.setDateCreate(LocalDateTime.now());
            bankEntity.setUserId(userId);

            BankEntity saved = bankRepository.save(bankEntity);

            response.setStatus("00");
            response.setMessage("Success Save bank-account");
            response.setDataResponse(saved);
            return response;

        } catch (Exception e) {
            log.error("Store bank account error: ", e);
            response.setStatus("EE");
            response.setMessage("Store bank account is Error !!");
            return response;
        }
    }
    //update bankAccount
    public DataResponse updateBankAccount(BankEntity bankEntity) {
        DataResponse response = new DataResponse();

        try {
            // 1) check token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(bankEntity.getToKen());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            Profile user = userProfiles.get(0);
            String role = user.getRole();
            String userId = user.getUserId();

            // 2) check role
            List<String> allowed = Arrays.asList("SUPERBANSI", "SUPERACCOUNT", "FOR_DOCUMENT_ADMIN");
            if (!allowed.contains(role.toUpperCase())) {
                response.setStatus("01");
                response.setMessage("No right to update");
                return response;
            }

            // 3) check b_id
            if (bankEntity.getBId() == null) {
                response.setStatus("06");
                response.setMessage("Missing b_id");
                return response;
            }

            // 4) find record
            BankEntity existing = bankRepository.findById(bankEntity.getBId())
                    .orElse(null);

            if (existing == null) {
                response.setStatus("04");
                response.setMessage("Bank account not found");
                return response;
            }

            // 5) update fields
            existing.setAccountName(bankEntity.getAccountName());
            existing.setAccountNo(bankEntity.getAccountNo());
            existing.setBankName(bankEntity.getBankName());
            existing.setBankNameLao(bankEntity.getBankNameLao());
            existing.setDateCreate(LocalDateTime.now());
            existing.setUserId(userId);

            // 6) save
            BankEntity updated = bankRepository.save(existing);

            response.setStatus("00");
            response.setMessage("Success Update bank-account");
            response.setDataResponse(updated);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Update bank account is Error !!");
        }

        return response;
    }

    //show bankAccount Service
    public DataResponse getAllBankAccounts(BankEntity bankEntity) {
        DataResponse response = new DataResponse();

        try {
            // 1) check token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(bankEntity.getToKen());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            Profile user = userProfiles.get(0);
            String role = user.getRole();

            // 2) check role
            List<String> allowed = Arrays.asList("SUPERBANSI", "SUPERACCOUNT", "FOR_DOCUMENT_ADMIN");
            if (!allowed.contains(role.toUpperCase())) {
                response.setStatus("01");
                response.setMessage("No right");
                return response;
            }

            List<BankEntity> list = bankRepository.findAll();

            response.setStatus("00");
            response.setMessage("Success showing Data");
            response.setDataResponse(list);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error retrieving bank accounts");
        }

        return response;
    }


    //show financeList service
    public DataResponse getListForFinance(FinanceListEntity financeListEntity){
        DataResponse response = new DataResponse();
        try{
            // 1) check token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(financeListEntity.getToKen());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            Profile user = userProfiles.get(0);
            String role = user.getRole();

            // 2) check role
            List<String> allowed = Arrays.asList("SUPERACCOUNT", "FOR_DOCUMENT_ADMIN","SUPERBANSI");
            if (!allowed.contains(role.toUpperCase())) {
                response.setStatus("01");
                response.setMessage("No right to fetch data");
                return response;
            }

            // 3) Prepare filter values
            String supplierId = financeListEntity.getSupplierId() == null
                    ? null
                    : String.valueOf(financeListEntity.getSupplierId());

            String payTypeId = financeListEntity.getPayTypeId();
            String typeOf = financeListEntity.getTypeOf();
            String currency = financeListEntity.getCurrency();

            String startDate = financeListEntity.getStartDate();
            String endDate = financeListEntity.getEndDate();

            List<FinanceListEntity> list =
                    financeListRepository.searchFinance(
                            supplierId,
                            payTypeId,
                            typeOf,
                            currency,
                            startDate,
                            endDate
                    );

            response.setStatus("00");
            response.setMessage("Success showing Finance Data");
            response.setDataResponse(list);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error retrieving Finance Data");
        }

        return response;
    }

    // getFinanceBill service
    public String generateFinaceBill() {
        String lastBillNo = financeRepository.findLastFinanceBillNo();

        String prefix = "F-BILL-";
        int number = 1;

        if (lastBillNo != null && !lastBillNo.isEmpty()) {
            try {
                String[] parts = lastBillNo.split("-");
                number = Integer.parseInt(parts[2]) + 1; // ← ต้องเป็น index 2
            } catch (Exception e) {
                number = 1;
            }
        }

        return prefix + String.format("%04d", number);
    }

   //INSERT FINANCEBILL
@Transactional
public DataResponse insertFinance(FinanceRequestDto req) {
    DataResponse response = new DataResponse();
    // 1. Validate token
    List<Profile> profileList = profileDao.getProfileInfoByToken(req.getToKen());
    if (profileList.isEmpty()) {
        response.setStatus("05");
        response.setMessage("Unauthorized");
        return response;
    }
    Profile user = profileList.get(0);

    // 2. Check role
    List<String> allowedRoles = Arrays.asList("SUPERACCOUNT", "FOR_DOCUMENT_ADMIN","SUPERBANSI");
    if (!allowedRoles.contains(user.getRole().toUpperCase())) {
        response.setStatus("01");
        response.setMessage("No permission to insert finance");
        return response;
    }

    try {
        // 3. Generate financeBill (ensure unique)
        String financeBill = generateNextFinanceBill();
        while (existsFinanceBill(financeBill)) {
            financeBill = generateNextFinanceBill();
        }

        // ===============================
        // 4. Parse & validate amounts
        // ===============================
        BigDecimal amountMustPay;
        BigDecimal pay;

        try {
            amountMustPay = (req.getAmountMustPay() != null && !req.getAmountMustPay().trim().isEmpty())
                    ? new BigDecimal(req.getAmountMustPay().trim())
                    : BigDecimal.ZERO;

            pay = (req.getPay() != null && !req.getPay().trim().isEmpty())
                    ? new BigDecimal(req.getPay().trim())
                    : BigDecimal.ZERO;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format (amountMustPay / pay)");
        }

        // ===============================
        // 5. Insert tb_finance (MASTER)
        // ===============================
        FinanceEntity master = new FinanceEntity();
        master.setFinanceBill(financeBill);
        master.setSupplierId(req.getSupplierId());
        master.setTypeOf(req.getType_of());
        master.setAmountMustPay(amountMustPay);
        master.setCurrency(req.getCurrency());
        master.setPay1(pay);

        // pay status
        master.setPayStatus(
                amountMustPay.subtract(pay).compareTo(BigDecimal.ZERO) <= 0
                        ? "DONE"
                        : "IN-PROGRESS"
        );


        // nextDatePay
        if (req.getNextDatePay() != null && !req.getNextDatePay().isEmpty()) {
            master.setNextDatePay(req.getNextDatePay());
        }
        if (req.getFirstDatePay() != null && !req.getFirstDatePay().isEmpty()) {
                String input = req.getFirstDatePay();
                if (input.length() == 10) { // yyyy-MM-dd
                    input += " 00:00:00"; // เติมเวลา
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime firstDatePay = LocalDateTime.parse(input, formatter);
                master.setFirstDatePay(firstDatePay);
            }

        master.setCreateDate(LocalDateTime.now());
        master.setCreateby(user.getUserName());

        FinanceEntity savedFinance = financeRepository.save(master);

        // ===============================
        // 6. Insert tb_finance_managelist
        // ===============================
        if (req.getBillList() != null && !req.getBillList().isEmpty()) {

            List<FinanceManageListEntity> manageList = new ArrayList<>();

            for (String billNo : req.getBillList()) {

                FinanceManageListEntity d = new FinanceManageListEntity();
                d.setFinanceBill(financeBill);
                d.setBillNo(billNo);
                manageList.add(d);

                int updated = paymentRequestRepository
                        .updatePayStatusByBillNo(billNo, "DONE-PAY");

                if (updated == 0) {
                    throw new RuntimeException("BillNo not found: " + billNo);
                }
            }

            financeManageListRepository.saveAll(manageList);
        }

        // ===============================
        // 7. Insert tb_finance_pay (HISTORY)
        // ===============================
        if (!"SUPERBANSI".equalsIgnoreCase(user.getRole())) {

            if (req.getBillList() != null && !req.getBillList().isEmpty()) {

                List<FinanceHisEntity> payHisList = new ArrayList<>();

                FinanceHisEntity h = new FinanceHisEntity();
                h.setFinanceBill(financeBill);
                h.setPayAmount(pay);
                h.setDatePay(req.getFirstDatePay());
                h.setCreateDate(LocalDateTime.now());

                payHisList.add(h);
                financeHisRepository.saveAll(payHisList);
            }
        }

        response.setStatus("00");
        response.setMessage("Finance saved successfully");
        response.setDataResponse(savedFinance);
        return response;

    } catch (Exception e) {
        // ❗ สำคัญ: rollback ทั้ง transaction
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        e.printStackTrace();
        response.setStatus("EE");
        response.setMessage("Error saving finance: " + e.getMessage());
        return response;
    }
}


    // helper: generate next financeBill
    @Transactional
    public synchronized String generateNextFinanceBill() {
        String lastBillNo = financeRepository.findLastFinanceBillNo();
        String prefix = "F-BILL-";
        int number = 1;

        if (lastBillNo != null && !lastBillNo.isEmpty()) {
            try {
                String[] parts = lastBillNo.split("-");
                number = Integer.parseInt(parts[2]) + 1; // ปรับ index เป็น 2 เพราะรูปแบบ F-BILL-0001
            } catch (Exception e) {
                number = 1;
            }
        }

        return prefix + String.format("%04d", number);
    }

    // helper: check if financeBill exists
    private boolean existsFinanceBill(String billNo) {
        return financeRepository.countBill(billNo) > 0;
    }

     // PAY BACK
    @Transactional
    public DataResponse updateFinancePay(FinanceUpdateDto req) {
        DataResponse response = new DataResponse();

        try {
            // ===============================
            // 1. Validate token
            // ===============================
            List<Profile> profileList = profileDao.getProfileInfoByToken(req.getToKen());
            if (profileList.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }
            Profile user = profileList.get(0);

            // ===============================
            // 2. Check role
            // ===============================
            List<String> allowedRoles = Arrays.asList("SUPERACCOUNT", "FOR_DOCUMENT_ADMIN");
            if (!allowedRoles.contains(user.getRole().toUpperCase())) {
                response.setStatus("01");
                response.setMessage("No permission to update finance");
                return response;
            }

            // ===============================
            // 3. Find Finance by financeBill
            // ===============================
            FinanceEntity finance = financeRepository.findByFinanceBill(req.getFinanceBill());
            if (finance == null) {
                response.setStatus("04");
                response.setMessage("Finance bill not found");
                return response;
            }

            // ===============================
            // 4. Parse pay
            // ===============================
            BigDecimal pay;
            try {
                pay = (req.getPay() != null && !req.getPay().trim().isEmpty())
                        ? new BigDecimal(req.getPay().trim())
                        : BigDecimal.ZERO;
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid pay format");
            }

            if (pay.compareTo(BigDecimal.ZERO) <= 0) {
                response.setStatus("06");
                response.setMessage("Pay must be greater than zero");
                return response;
            }

             // 5. Update pay1 by summing with client pay
            // ===============================
            BigDecimal currentPay = finance.getPay1() != null ? finance.getPay1() : BigDecimal.ZERO;
            BigDecimal newPay = currentPay.add(pay); // pay from client
            finance.setPay1(newPay);


            // ===============================
            // 6. Update nextDatePay if provided
            // ===============================
            if (req.getNextDatePay() != null && !req.getNextDatePay().isEmpty()) {
                finance.setNextDatePay(req.getNextDatePay());
            }

            // ===============================
            // 7. Update currency if provided
            // ===============================
            if (req.getCurrency() != null && !req.getCurrency().isEmpty()) {
                finance.setCurrency(req.getCurrency());
            }


            // ===============================
            // 9. Update payStatus
            // ===============================
            BigDecimal amountMustPay = finance.getAmountMustPay() != null
                    ? finance.getAmountMustPay()
                    : BigDecimal.ZERO;

            if (finance.getPay1().compareTo(amountMustPay) >= 0) {
                finance.setPayStatus("DONE");
            } else {
                finance.setPayStatus("IN-PROGRESS");
            }
            finance.setApproveby(user.getUserName());
            finance.setApproveDate(LocalDateTime.now());

            FinanceEntity updatedFinance = financeRepository.save(finance);

            // 11. Insert tb_finance_pay (history)
            // ===============================
            if (req.getFinanceBill() != null && !req.getFinanceBill().isEmpty()) {
                List<FinanceHisEntity> payHisList = new ArrayList<>();

                FinanceHisEntity h = new FinanceHisEntity();
                h.setFinanceBill(req.getFinanceBill());
                h.setPayAmount(pay);
                h.setCreateDate(LocalDateTime.now());
                if (req.getPayDate() != null && !req.getPayDate().isEmpty()) {
                    // Normalize to yyyy-MM-dd HH:mm:ss
                    String dateStr = req.getPayDate().trim();
                    if (dateStr.length() == 10) { // yyyy-MM-dd
                        dateStr += " 00:00:00";
                    }
                    h.setDatePay(dateStr);
                } else {
                    // default now
                    h.setDatePay(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }

                payHisList.add(h);
                financeHisRepository.saveAll(payHisList);
            }
            response.setStatus("00");
            response.setMessage("Payment updated successfully");
            response.setDataResponse(updatedFinance);
            return response;

        } catch (Exception e) {
            // Rollback transaction
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error updating finance: " + e.getMessage());
            return response;
        }
    }


    //show FinanceViewService
    public DataResponse getFinanceViewGrouped(FinanceViewDto financeViewDto) {
        DataResponse response = new DataResponse();

        try {
            // 1) ตรวจสอบ token และ role
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(financeViewDto.getToken());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            Profile user = userProfiles.get(0);
            List<String> allowed = Arrays.asList("SUPERACCOUNT", "FOR_DOCUMENT_ADMIN","SUPERBANSI");
            if (!allowed.contains(user.getRole().toUpperCase())) {
                response.setStatus("01");
                response.setMessage("No right to fetch data");
                return response;
            }

            // 2) ดึงข้อมูลจาก Repository
            List<FinanceViewDto> financeList = financeViewRepository.findFinanceByFilter(
                    financeViewDto.getTypeOf(),
                    financeViewDto.getPayStatus(),
                    financeViewDto.getStartDate(),
                    financeViewDto.getEndDate()
            );


            // 3) Group by financeBill
            Map<String, List<FinanceViewDto>> grouped = financeList.stream()
                    .collect(Collectors.groupingBy(FinanceViewDto::getFinanceBill));

            // 4) สร้าง map สำหรับ response
            List<Map<String, Object>> result = new ArrayList<>();
            for (Map.Entry<String, List<FinanceViewDto>> entry : grouped.entrySet()) {
                List<FinanceViewDto> list = entry.getValue();
                if (list.isEmpty()) continue;

                FinanceViewDto first = list.get(0);

                Map<String, Object> map = new HashMap<>();
                map.put("keyId", first.getKeyId());
                map.put("supplierId", first.getSupplierId());
                map.put("supplierName", first.getSupplierName());
                map.put("financeBill", first.getFinanceBill());
                map.put("amountMustPay", first.getAmountMustPay());
                map.put("pay1", first.getPay1());
                map.put("nextDatePay", first.getNextDatePay());
                map.put("typeOf",first.getTypeOf());
                map.put("payStatus", first.getPayStatus());
                map.put("currency", first.getCurrency());
                map.put("createBy", first.getCreateBy());
                map.put(("create_date"),first.getCreateDate());
                map.put("token", first.getToken());

                // รวม billNo เป็น list
                List<String> billNos = list.stream()
                        .map(FinanceViewDto::getBillNo)
                        .collect(Collectors.toList());
                map.put("billNos", billNos);

                // คำนวณ paidTotal และ amountNotPayYet
                double paidTotal = first.getPay1() ;
                double amountNotPayYet = first.getAmountMustPay() - paidTotal;
                map.put("paidTotal", paidTotal);
                map.put("amountNotPayYet", amountNotPayYet);

                // ------------------ CALCULATE notify_status ------------------
                String notifyStatus = "UNKNOWN";
                if ("DONE".equalsIgnoreCase(first.getPayStatus())) {
                    notifyStatus = "DONE";
                } else if (first.getNextDatePay() != null && !first.getNextDatePay().isEmpty()) {
                    try {
                        String dateStr = first.getNextDatePay();
                        // ถ้า format มีเวลา ให้ตัดเป็น yyyy-MM-dd
                        if (dateStr.length() > 10) {
                            dateStr = dateStr.substring(0, 10);
                        }
                        LocalDate nextDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate today = LocalDate.now();

                        if (nextDate.isAfter(today)) {
                            notifyStatus = "COMING";
                        } else if (nextDate.isEqual(today)) {
                            notifyStatus = "EXPIRED";
                        } else {
                            notifyStatus = "OVERDUE";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        notifyStatus = "ERROR";
                    }
                }
                map.put("notify_status", notifyStatus);

                result.add(map);
            }

            response.setStatus("00");
            response.setMessage("Success showing Finance Data");
            response.setDataResponse(result);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error retrieving Finance Data");
        }

        return response;
    }
    //show Finace Pay detail
    public DataResponse getFinancePayByBill(FinanceHistDto financeHistDto) {
        DataResponse response = new DataResponse();

        try {
            // เรียก repository โดยส่ง financeBill
            List<FinanceHistDto> financeHist = financePayHisRepo.findFinancePayHisFilter(
                    financeHistDto.getFinanceBill()
            );

            // set result ลง response
            response.setStatus("00");  // 00 = success
            response.setMessage("Success");
            response.setDataResponse(financeHist);

        } catch (Exception e) {
            // Rollback transaction
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error FinanceHis by bill: " + e.getMessage());
        }

        return response;  // return response ทั้งกรณี success และ error
    }

    public DataResponse searchSupplierNotPay(SupplierNotPayReq req) {

        DataResponse response = new DataResponse();

        try {
            // =========================
            // 1) Validate token
            // =========================
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToken());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            Profile user = userProfiles.get(0);

            // =========================
            // 2) Validate role
            // =========================
            String role = user.getRole() == null ? "" : user.getRole().toUpperCase();
            List<String> allowedRoles = Arrays.asList(
                    "SUPERACCOUNT",
                    "FOR_DOCUMENT_ADMIN",
                    "SUPERBANSI"
            );

            if (!allowedRoles.contains(role)) {
                response.setStatus("01");
                response.setMessage("No right to fetch data");
                return response;
            }

            // =========================
            // 3) Get finance bills (flat)
            // =========================
            List<SupplierNotPayDto> flatList =
                    supplierNotPayRepo.findSupplierNotPay(
                            req.getStartDate(),
                            req.getEndDate(),
                            req.getTypeOf(),
                            req.getSupplierId()
                    );

            Map<Long, SupplierNotPayNestedDto> supplierMap = new LinkedHashMap<>();

            // =========================
            // 4) Build supplier + financeBills
            // =========================
            for (SupplierNotPayDto row : flatList) {

                Long supplierId = row.getSupplierId();
                SupplierNotPayNestedDto supplier = supplierMap.get(supplierId);

                if (supplier == null) {
                    supplier = new SupplierNotPayNestedDto();
                    supplier.setKeyId(row.getKeyId());
                    supplier.setSupplierId(supplierId);
                    supplier.setSupplierName(row.getSupplierName());
                    supplierMap.put(supplierId, supplier);
                }

                String financeBillStr = row.getFinanceBill();
                String billNoStr = row.getBillNo();

                Optional<FinanceBillDto> fbOpt = supplier.getFinanceBills()
                        .stream()
                        .filter(fb -> fb.getFinanceBill().equals(financeBillStr))
                        .findFirst();

                FinanceBillDto financeBillDto;

                if (fbOpt.isPresent()) {
                    financeBillDto = fbOpt.get();
                } else {
                    financeBillDto = new FinanceBillDto();
                    financeBillDto.setFinanceBill(financeBillStr);
                    financeBillDto.setAmountMustPay(row.getAmountMustPay());
                    financeBillDto.setPaid(row.getPaid());
                    financeBillDto.setDateCreate(row.getCreateDate());
                    financeBillDto.setNextDatePay(row.getNextDatePay());
                    financeBillDto.setPayStatus(row.getPayStatus());
                    financeBillDto.setCurrency(row.getCurrency());
                    financeBillDto.setTypeOf(row.getTypeOf());

                    supplier.getFinanceBills().add(financeBillDto);
                }

                financeBillDto.getBillNos().add(billNoStr);
            }

            // =========================
            // 5) Get SUMMARY from SQL
            // =========================
            List<SupplierSummaryRowDto> summaryList =
                    supplierNotPayRepo.findSupplierSummary(
                            req.getStartDate(),
                            req.getEndDate(),
                            req.getTypeOf(),
                            req.getSupplierId()
                    );

            // =========================
            // 6) Merge SUMMARY → PAY / RECEIVE
            // =========================
            for (SupplierSummaryRowDto row : summaryList) {

                SupplierNotPayNestedDto supplier = supplierMap.get(row.getSupplierId());
                if (supplier == null) {
                    continue; // safety
                }

                CurrencySummaryDto summary =
                        "PAY".equalsIgnoreCase(row.getTypeOf())
                                ? supplier.getPAY()
                                : supplier.getRECEIVE();

                BigDecimal mustPay = row.getSumMustPay() == null
                        ? BigDecimal.ZERO
                        : row.getSumMustPay();

                BigDecimal pay = row.getSumPay() == null
                        ? BigDecimal.ZERO
                        : row.getSumPay();

                if ("LAK".equalsIgnoreCase(row.getCurrency())) {
                    summary.setSumMustPayLAK(mustPay);
                    summary.setSumPayLAK(pay);
                } else if ("THB".equalsIgnoreCase(row.getCurrency())) {
                    summary.setSumMustPayTHB(mustPay);
                    summary.setSumPayTHB(pay);
                } else if ("USD".equalsIgnoreCase(row.getCurrency())) {
                    summary.setSumMustPayUSD(mustPay);
                    summary.setSumPayUSD(pay);
                }
            }

            // =========================
            // 7) Calculate next pay
            // =========================
            for (SupplierNotPayNestedDto s : supplierMap.values()) {
                s.getPAY().calculateNextPay();
                s.getRECEIVE().calculateNextPay();
            }

            // =========================
            // 8) Response
            // =========================
            response.setStatus("00");
            response.setMessage("Success showing Supplier Not Pay Data");
            response.setDataResponse(new ArrayList<>(supplierMap.values()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error retrieving Supplier Not Pay Data");
        }

        return response;
    }

    public DataResponse getAccountingWaitCount(FinanceViewDto financeViewDto) {
        DataResponse response = new DataResponse();
        AccountingWaitCountRes result = new AccountingWaitCountRes();

        try {
            // 1) ตรวจสอบ token และ role
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(financeViewDto.getToken());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            Profile user = userProfiles.get(0);
            List<String> allowed = Arrays.asList("SUPERACCOUNT", "FOR_DOCUMENT_ADMIN", "SUPERBANSI");
            if (!allowed.contains(user.getRole().toUpperCase())) {
                response.setStatus("01");
                response.setMessage("No right to fetch data");
                return response;
            }

            // --- Query 1 : v_accounting_report ---
            String sql1 =
                    "SELECT " +
                            " SUM(CASE WHEN type_of = 'PAY' AND bill_status = 'wait' THEN 1 ELSE 0 END) AS pay_wait_count, " +
                            " SUM(CASE WHEN type_of = 'RECEIVE' AND bill_status = 'wait' THEN 1 ELSE 0 END) AS receive_wait_count " +
                            "FROM v_accounting_report";

            List<AccountingWaitCountRes> list1 = jdbcTemplate.query(sql1, (rs, rowNum) -> {
                AccountingWaitCountRes res = new AccountingWaitCountRes();
                res.setAccountBillPayWait(rs.getInt("pay_wait_count"));
                res.setAccountbillReceiveWait(rs.getInt("receive_wait_count"));
                return res;
            });

            if (!list1.isEmpty()) {
                result.setAccountBillPayWait(list1.get(0).getAccountBillPayWait());
                result.setAccountbillReceiveWait(list1.get(0).getAccountbillReceiveWait());
            }

            // --- Query 2 : tb_finance ---
            String sql2 =
                    "SELECT " +
                            " SUM(CASE WHEN TRIM(UPPER(pay_status)) = 'IN-PROGRESS' " +
                            "  AND TRIM(UPPER(type_of)) = 'RECEIVE' THEN 1 ELSE 0 END) AS in_progress_receive, " +
                            " SUM(CASE WHEN TRIM(UPPER(pay_status)) = 'IN-PROGRESS' " +
                            "  AND TRIM(UPPER(type_of)) = 'PAY' THEN 1 ELSE 0 END) AS in_progress_pay " +
                            "FROM tb_finance";

            AccountingWaitCountRes financeCount =
                    jdbcTemplate.queryForObject(sql2, (rs, rowNum) -> {
                        AccountingWaitCountRes r = new AccountingWaitCountRes();
                        r.setFinanceBillInProgressReceive(rs.getInt("in_progress_receive"));
                        r.setFinanceBillInProgressPay(rs.getInt("in_progress_pay"));
                        return r;
                    });

            if (financeCount != null) {
                result.setFinanceBillInProgressReceive(financeCount.getFinanceBillInProgressReceive());
                result.setFinanceBillInProgressPay(financeCount.getFinanceBillInProgressPay());
            }


            log.info(sql1);
            log.info(sql2);
            // --- set result ลง response ---
            response.setStatus("00");
            response.setMessage("Success");
            response.setDataResponse(result);

        } catch (Exception e) {
            log.error("Error getAccountingWaitCount", e);
            response.setStatus("99");
            response.setMessage("Exception: " + e.getMessage());
        }

        return response;
    }












}
