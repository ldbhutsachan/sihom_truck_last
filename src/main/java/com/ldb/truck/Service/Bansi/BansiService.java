package com.ldb.truck.Service.Bansi;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Entity.Bansi.BansiEntity;
import com.ldb.truck.Entity.Bansi.PayTypeEntity;
import com.ldb.truck.Model.Bansi.PayTypeReq;
import com.ldb.truck.Model.Bansi.ProjectPaymentModel;
import com.ldb.truck.Model.Bansi.ProjectShowReq;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Repository.Bansi.BansiRepository;
import com.ldb.truck.Repository.Bansi.PayTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BansiService {

    @Autowired
    private BansiRepository bansiRepository;  // ✅ ใช้ entity type จริง
    @Autowired
    private PayTypeRepository payTypeRepository;


    @Autowired
    private ProfileDao profileDao;

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

            if (!"SUPERBANSI".equalsIgnoreCase(role)) {
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
            if(!"SUPERBANSI".equalsIgnoreCase(role)){
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
            if (!"SUPERBANSI".equals(role)) {
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
    public DataResponse showOnePayType(PayTypeReq payTypeReq) {
        DataResponse response = new DataResponse();

        try {
            // ✅ ตรวจสอบ token
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(payTypeReq.getToKen());
            if (userProfiles.isEmpty()) {
                response.setStatus("05");
                response.setMessage("Unauthorized");
                return response;
            }

            String role = userProfiles.get(0).getRole();

            // ✅ ถ้าไม่ใช่ SUPERBANSI
            if (!"SUPERBANSI".equalsIgnoreCase(role)) {
                response.setStatus("99");
                response.setMessage("You don't have permission to fetch data");
                return response;
            }

            // ✅ ถ้าเป็น SUPERBANSI
            List<PayTypeEntity> data;
            if (payTypeReq.getReq_id() != null) {
                // แสดงเฉพาะ req_id ที่ส่งมา
                data = payTypeRepository.findByReqId(payTypeReq.getReq_id());
            } else {
                // แสดงทั้งหมด
                data = payTypeRepository.findAll();
            }
            response.setStatus("00");
            response.setMessage("Success");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error while fetching data");
        }

        return response;
    }



}
