package com.ldb.truck.Service.Bansi;

import com.ldb.truck.Dao.Bansi.PaymentDetailDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.Bansi.*;
import com.ldb.truck.Model.Bansi.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Repository.Bansi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BansiService {

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
                bansiEntity.setBansi("1");
            } else if ("SUPERACCOUNT".equalsIgnoreCase(role)) {
                bansiEntity.setBansi("2");
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

//        if (!"SUPERBANSI".equalsIgnoreCase(role)) {
//            throw new Exception("No right to insert (role: " + role + ")");
//        }
        if (!"SUPERBANSI".equalsIgnoreCase(role)
                && !"SUPERACCOUNT".equalsIgnoreCase(role)
                && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
            throw new Exception("No right to update (role: " + role + ")");
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

        MultipartFile file = req.getFile();
        if (file != null && !file.isEmpty()) {
            // ✅ ใช้ service เดิมเพื่อให้ path เหมือนกับ saveMachine()
            String uploadedFileName = mediaUploadService.uploadMedia(file);

            // ✅ สร้าง URL สำหรับบันทึกใน DB
            String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;

            // ✅ เซตค่าให้ entity
            entity.setFile(fileUrl);

            // ✅ log ตรวจสอบได้
            log.info("Uploaded file successfully: {}", fileUrl);
        } else {
            // ✅ กรณีไม่มีไฟล์ (optional)
            log.warn("No file uploaded for payment detail.");
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

//        if (!"SUPERBANSI".equalsIgnoreCase(role)) {
//            throw new Exception("No right to update (role: " + role + ")");
//        }
        if (!"SUPERBANSI".equalsIgnoreCase(role)
                && !"SUPERACCOUNT".equalsIgnoreCase(role)
                && !"FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role)) {
            throw new Exception("No right to update (role: " + role + ")");
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

        if (req.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(req.getDate());
            entity.setDate(sdf.format(parsedDate));
        }
        //  Upload file (ใช้ service เดิม)
        MultipartFile file = req.getFile();
        if (file != null && !file.isEmpty()) {
            String uploadedFileName = mediaUploadService.uploadMedia(file);
            String fileUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
            entity.setFile(fileUrl);
            log.info("✅ Updated file uploaded: {}", fileUrl);
        } else {
            log.info("ℹ️ No new file uploaded. Keep old file: {}", entity.getFile());
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
                        "FOR_DOCUMENT_ADMIN".equalsIgnoreCase(role);

        if (!isAllowed) {
            result.setStatus("02");
            result.setMessage("No permission");
            result.setData(new ArrayList<>());
            return result;
        }


        // เรียก DAO พร้อม role + userId
        List<PaymentDetailModel> data = paymentDetailDao.findPaymentDetails(
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
            result.setSumUsd(0);
            result.setSumLak(0);
            result.setSumThb(0);
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
            result.setSumUsd(0);
            result.setSumLak(0);
            result.setSumThb(0);
            return result;
        }

        List<AccountingReportModel> data = paymentDetailDao.reportAccounting(
                req.getBig_project_id(),
                req.getSmall_project_id(),
                req.getPay_type_id(),
                req.getType_of_pay(),
                req.getStartDate(),
                req.getEndDate(),
                role
        );

        // คำนวณ sum
        double sumUsd = 0, sumLak = 0, sumThb = 0;
        for (AccountingReportModel m : data) {
            if (m.getCurrency() == null) continue;
            switch (m.getCurrency().toUpperCase()) {
                case "USD": sumUsd += m.getPrice(); break;
                case "LAK": sumLak += m.getPrice(); break;
                case "THB": sumThb += m.getPrice(); break;
            }
        }

        result.setStatus("00");
        result.setMessage("Success");
        result.setData(data);
        result.setSumUsd(sumUsd);
        result.setSumLak(sumLak);
        result.setSumThb(sumThb);

        return result;
    }


}
