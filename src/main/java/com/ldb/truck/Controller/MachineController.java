package com.ldb.truck.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Entity.MerchineHis.MachineMaintenanceHistory;
import com.ldb.truck.Entity.MerchineHis.MachineToolHis;
import com.ldb.truck.Model.Borcar.BorCarResponse;
import com.ldb.truck.Model.Borcar.BorcarReq;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Machine.*;
import com.ldb.truck.Repository.MachineHis.MerchinHisRepository;
import com.ldb.truck.Service.MachineService.MachineService;
import com.ldb.truck.Service.MediaUploadServiceImpl;
import com.ldb.truck.enums.MaintenanceType;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("${base_url}")
public class MachineController {
    private final ProfileDao profileDao;
    private final MachineService MACHINE_SERVICE;
    @Autowired
    private  MerchinHisRepository MERCHIN_HIS_REPOSITORY;
    @Autowired
    private MediaUploadService mediaUploadService;

    public MachineController(ProfileDao profileDao, MachineService machineService) {
        this.profileDao = profileDao;
        MACHINE_SERVICE = machineService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/acceptMachineHis.service")
    public ResponseEntity<?> acceptMachineHis(@RequestBody AceptItemReq machineRPReq) {
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userName = userProfiles.get(0).getUserName();
        log.info("show role" + userName);
        try {
            response = MACHINE_SERVICE.aceptMachineHis(machineRPReq, userName);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/saveMachineHis.service")
    public ResponseEntity<?> saveMachineHis(@RequestBody MachineHisReq machineRPReq) {
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        log.info("show role" + userId);
        try {
            response = MACHINE_SERVICE.saveMachineHis(machineRPReq, userId);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateMachineHis.service")
    public ResponseEntity<?> updateMachineHis(@RequestBody MachineHisReq machineRPReq) {
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userName = userProfiles.get(0).getUserName();
        log.info("show role" + userName);
        try {
            response = MACHINE_SERVICE.updateMachineHis(machineRPReq, userName);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/enableMachineHis.service")
    public ResponseEntity<?> enableMachineHis(@RequestBody MachineHisReq machineRPReq) {
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userName = userProfiles.get(0).getUserName();
        try {
            response = MACHINE_SERVICE.enableMachineHis(machineRPReq, userName);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getMachineHis.service")
    public ResponseEntity<?> getMachineHis(@RequestBody MachineHisReq machineRPReq) {
        MachineHisResponse response = new MachineHisResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        log.info("show role" + role);
        try {
            response = MACHINE_SERVICE.getMachineHis(machineRPReq, borNo);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getRequestItemList.service")
    public ResponseEntity<?> getRequestItemList(@RequestBody MachineStockDetailsReq machineRPReq) {
        MachineStockDetailsResponse response = new MachineStockDetailsResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        log.info("show role" + role);
        try {
            response = MACHINE_SERVICE.getRequestItemList(machineRPReq, borNo);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getMachine.service")
    public ResponseEntity<?> getMachine(@RequestBody MachineRPReq machineRPReq) {
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        log.info("show role" + role);
        try {
            response = MACHINE_SERVICE.getMachine(machineRPReq, role, borNo);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getReportMachineDetail.service")
    public ResponseEntity<?> getReportMachineDetail(@RequestBody MachineRPReq machineRPReq) {
        MachineDetailsResponse response = new MachineDetailsResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        try {
            response = MACHINE_SERVICE.getReportMachineDetail(machineRPReq, role, borNo);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getSumReportMachine.service")
    public ResponseEntity<?> getSumReportMachine(@RequestBody MachineRPReq machineRPReq) {
        MachineReportSumResposne response = new MachineReportSumResposne();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        try {
            response = MACHINE_SERVICE.getSumReportMachine(machineRPReq, role, borNo);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getReportBorCar.service")
    public ResponseEntity<?> getReportBorCar(@RequestBody BorcarReq borcarReq) {
        BorCarResponse response = new BorCarResponse();

        // ตรวจสอบ token
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(borcarReq.getToken());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String role = userProfiles.get(0).getRole();
//        String borNo = userProfiles.get(0).getBorNo();

        try {
            response = MACHINE_SERVICE.getReportBorCar(borcarReq, role);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/getReportMachineSum.service")
    public ResponseEntity<?> getReportMachineSum(@RequestBody MachineRPReq machineRPReq) {
        MachineReportResposne response = new MachineReportResposne();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        try {
            response = MACHINE_SERVICE.getReportMachineSum(machineRPReq, role, borNo);
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/saveMachine.service")
    public ResponseEntity<MachineReportResposne> saveMachine(
            @RequestParam("mchNo") String mchNo,
            @RequestParam("mchName") String mchName,
            @RequestParam("price") String price,
            @RequestParam("currency") String currency,
            @RequestParam("createBy") String createBy,
            @RequestParam("status") String status,
            @RequestParam(value = "borNo", required = false) String borNo,
            @RequestParam(value = "time_fix", required = false) Integer timeFix,
            @RequestParam(value = "time_fix_monitor", required = false) Integer timeFixMonitor,
            @RequestParam(value = "time_oil_fix", required = false) Integer timeOilFix,
            @RequestParam(value = "time_oil_fix_mo", required = false) Integer timeOilFixMo,
            @RequestParam(value = "tools", required = false) String toolsJson,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "mchBranchName", required = false) String mchBranchName,
            @RequestParam(value = "mchModel", required = false) String mchModel,
            @RequestParam(value = "mchProductYear", required = false) String mchProductYear,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_in,  // for insert date_in in local Datetime
            @RequestParam(value = "machine_mileage_now", required = false) BigDecimal machine_mileage_now,
            @RequestParam(value = "machine_mileage_next", required = false) BigDecimal machine_mileage_next,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateChangeLeean,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateChangeLeeanNext,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateleanGia,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateleanGiaNextday,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateleanFuengThaiy,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate_kongnam,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate_kongnam,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hydraulic_date,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hydraulic_nextdate

    ) {
        MachineReportResposne result = new MachineReportResposne();
        try {
            MachineReq machineReq = new MachineReq();
            machineReq.setMchNo(mchNo);
            machineReq.setMchName(mchName);
            machineReq.setPrice(price);
            machineReq.setCurrency(currency);
            machineReq.setCreateBy(createBy);
            machineReq.setStatus(status);
            machineReq.setBorNo(borNo);
            machineReq.setTime_fix(timeFix);
            machineReq.setTime_fix_monitor(timeFixMonitor);
            machineReq.setTime_oil_fix(timeOilFix);
            machineReq.setTime_oil_fix_mo(timeOilFixMo);
            machineReq.setMchBranchName(mchBranchName);
            machineReq.setMchModel(mchModel);
            machineReq.setMchProductYear(mchProductYear);
            machineReq.setDate_in(date_in);
            machineReq.setMachine_mileage_now(machine_mileage_now);
            machineReq.setMachine_mileage_next(machine_mileage_next);
            machineReq.setDateChangeLeean(dateChangeLeean);
            machineReq.setDateChangeLeeanNext(dateChangeLeeanNext);
            machineReq.setDateleanGia(dateleanGia);
            machineReq.setDateleanGiaNextday(dateleanGiaNextday);
            machineReq.setDateleanFuengThaiy(dateleanFuengThaiy);
            machineReq.setStartdate_kongnam(startdate_kongnam);
            machineReq.setEnddate_kongnam(enddate_kongnam);
            machineReq.setHydraulic_date(hydraulic_date);
            machineReq.setHydraulic_nextdate(hydraulic_nextdate);

            // แปลง JSON string ของ tools เป็น List<ToolReq>
            if (toolsJson != null && !toolsJson.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                List<MachineReq.ToolReq> tools = mapper.readValue(
                        toolsJson,
                        new TypeReference<List<MachineReq.ToolReq>>() {
                        }
                );
                machineReq.setTools(tools);
            }
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            String pathAdd = "http://khounkham.com/images/batery/";
            if (imageFile == null) {
                log.warn("************* file name is null ****************");
                machineReq.setImage("http://khounkham.com/images/image.jpg");
            } else {
                Arrays.asList(imageFile).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMedia(file));
                });
                log.info("Uploaded the files successfully: " + fileNames);
                fileName = StringUtils.join(fileNames, ',');
                machineReq.setImage(pathAdd + fileName);
            }
            result = MACHINE_SERVICE.saveMachineWithTools(machineReq);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Error !!");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateMachine.service")
    public ResponseEntity<MachineReportResposne> updateMachine(
            @RequestParam("keyId") Integer keyId,
            @RequestParam("mchNo") String mchNo,
            @RequestParam("mchName") String mchName,
            @RequestParam("price") String price,
            @RequestParam("currency") String currency,
            @RequestParam("createBy") String createBy,
            @RequestParam("status") String status,
            @RequestParam(value = "borNo", required = false) String borNo,
            @RequestParam(value = "time_fix", required = false) Integer timeFix,
            @RequestParam(value = "time_fix_monitor", required = false) Integer timeFixMonitor,
            @RequestParam(value = "time_oil_fix", required = false) Integer timeOilFix,
            @RequestParam(value = "time_oil_fix_mo", required = false) Integer timeOilFixMo,
            @RequestParam(value = "mchBranchName", required = false) String mchBranchName,
            @RequestParam(value = "mchModel", required = false) String mchModel,
            @RequestParam(value = "mchProductYear", required = false) String mchProductYear,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam(value = "tools", required = false) String toolsJson,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "date_in", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_in,
            @RequestParam(value = "machine_mileage_now", required = false) BigDecimal machine_mileage_now,
            @RequestParam(value = "machine_mileage_next", required = false) BigDecimal machine_mileage_next,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateChangeLeean,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateChangeLeeanNext,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateleanGia,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateleanGiaNextday,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateleanFuengThaiy,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate_kongnam,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate_kongnam,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hydraulic_date,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hydraulic_nextdate


    ) throws Exception {

        MachineReq machineReq = new MachineReq();
        machineReq.setKeyId(keyId);
        machineReq.setMchNo(mchNo);
        machineReq.setMchName(mchName);
        machineReq.setPrice(price);
        machineReq.setCurrency(currency);
        machineReq.setCreateBy(createBy);
        machineReq.setStatus(status);
        machineReq.setBorNo(borNo);
        machineReq.setTime_fix(timeFix);
        machineReq.setTime_fix_monitor(timeFixMonitor);
        machineReq.setTime_oil_fix(timeOilFix);
        machineReq.setTime_oil_fix_mo(timeOilFixMo);
        machineReq.setMchBranchName(mchBranchName);
        machineReq.setMchModel(mchModel);
        machineReq.setMchProductYear(mchProductYear);
        machineReq.setRemark(remark);
        machineReq.setDate_in(date_in);
        machineReq.setMachine_mileage_now(machine_mileage_now);
        machineReq.setMachine_mileage_next(machine_mileage_next);
        machineReq.setDateChangeLeean(dateChangeLeean);
        machineReq.setDateChangeLeeanNext(dateChangeLeeanNext);
        machineReq.setDateleanGia(dateleanGia);
        machineReq.setDateleanGiaNextday(dateleanGiaNextday);
        machineReq.setDateleanFuengThaiy(dateleanFuengThaiy);
        machineReq.setStartdate_kongnam(startdate_kongnam);
        machineReq.setEnddate_kongnam(enddate_kongnam);
        machineReq.setHydraulic_date(hydraulic_date);
        machineReq.setHydraulic_nextdate(hydraulic_nextdate);

        if (toolsJson != null && !toolsJson.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            List<MachineReq.ToolReq> tools = mapper.readValue(
                    toolsJson, new TypeReference<List<MachineReq.ToolReq>>() {
                    }
            );
            machineReq.setTools(tools);
        }
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        String pathAdd = "http://khounkham.com/images/batery/";
        if (imageFile != null && !imageFile.isEmpty()) {
            Arrays.asList(imageFile).forEach(file -> {
                fileNames.add(mediaUploadService.uploadMedia(file));
            });
            log.info("Uploaded the files successfully: " + fileNames);
            fileName = StringUtils.join(fileNames, ',');
            machineReq.setImage(pathAdd + fileName);
        } else {
            log.warn("No image uploaded — keeping the old image");
            machineReq.setImage(null);
        }

        MachineReportResposne result = MACHINE_SERVICE.updateMachine(machineReq);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //insert Machinetoolhis
    @CrossOrigin(origins = "*")
    @PostMapping("/insertMachineToolHis")
    public ResponseEntity<?> insert(@RequestBody MachineToolHisRequest request) {

        // Check token
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(request.getToken());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Get username from token
        String userName = userProfiles.get(0).getUserName();

        // Map to entity
        MachineToolHis entity = new MachineToolHis();
        entity.setToolId(request.getToolId().longValue()); // cast เป็น Long
        entity.setQty(request.getQty());
        entity.setCreateBy(userName);

        return ResponseEntity.status(HttpStatus.CREATED).body(MACHINE_SERVICE.insert(entity));
    }

    //getmachineToolHis
    @CrossOrigin(origins = "*")
    @PostMapping("/getMachineToolHis")
    public ResponseEntity<?> findByToolId(@RequestBody Map<String, Long> body) { // เปลี่ยนตรงนี้
        Long toolId = body.get("id");
        return ResponseEntity.ok(MACHINE_SERVICE.findByToolId(toolId));
    }

    // บันทึก history
    @CrossOrigin(origins = "*")
    @PostMapping("/saveMaintenance")
    public ResponseEntity<?> saveHistory(
            @RequestParam("toKen") String toKen,
            @RequestParam("machineKeyId") Integer machineKeyId,
            @RequestParam("mchNo") String mchNo,
            @RequestParam("machineMileage") BigDecimal machineMileage,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam(value = "dateChangeLeean", required = false) String dateChangeLeean,
            @RequestParam(value = "dateChangeLeeanNext", required = false) String dateChangeLeeanNext,
            @RequestParam(value = "dateleanGia", required = false) String dateleanGia,
            @RequestParam(value = "dateleanGiaNextday", required = false) String dateleanGiaNextday,
            @RequestParam(value = "dateleanFuengThaiy", required = false) String dateleanFuengThaiy,
            @RequestParam(value = "startdateKongnam", required = false) String startdateKongnam,
            @RequestParam(value = "enddateKongnam", required = false) String enddateKongnam,
            @RequestParam(value = "hydraulicDate", required = false) String hydraulicDate,
            @RequestParam(value = "hydraulicNextdate", required = false) String hydraulicNextdate,
            @RequestPart(value = "files", required = false) MultipartFile[] imageFile) {

        MaintenanceMachineRes response = new MaintenanceMachineRes();

        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String userName = userProfiles.get(0).getUserName();

            LocalDate leean     = parseDate(dateChangeLeean);
            LocalDate leeanNext = parseDate(dateChangeLeeanNext);
            LocalDate gia       = parseDate(dateleanGia);
            LocalDate giaNext   = parseDate(dateleanGiaNextday);
            LocalDate fueng     = parseDate(dateleanFuengThaiy);
            LocalDate kongStart = parseDate(startdateKongnam);
            LocalDate kongEnd   = parseDate(enddateKongnam);
            LocalDate hydDate   = parseDate(hydraulicDate);
            LocalDate hydNext   = parseDate(hydraulicNextdate);

            String filePath = null;
            String pathAdd = "http://khounkham.com/images/batery/";
            if (imageFile != null && imageFile.length > 0) {
                List<String> fileNames = new ArrayList<>();
                Arrays.asList(imageFile).forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMedia(file));
                });
                log.info("Uploaded files successfully: " + fileNames);
                filePath = pathAdd + StringUtils.join(fileNames, ',');
            } else {
                log.warn("No files uploaded");
            }

            if (leean != null) {
                MACHINE_SERVICE.saveHistoryAndUpdateMachine(machineKeyId, mchNo, MaintenanceType.LEEAN,
                        machineMileage, leean, leeanNext, filePath, userName, remark);
                //change ນ້ຳມັນເຄື່ອງ
                MERCHIN_HIS_REPOSITORY.updateMachineStatusToClosed(mchNo, userName);
            }
            if (gia != null) {
                MACHINE_SERVICE.saveHistoryAndUpdateMachine(machineKeyId, mchNo, MaintenanceType.LEEAN_GIA,
                        machineMileage, gia, giaNext, filePath, userName, remark);
            }
            if (fueng != null) {
                MACHINE_SERVICE.saveHistoryAndUpdateMachine(machineKeyId, mchNo, MaintenanceType.LEEAN_FUENG_THAI,
                        machineMileage, fueng, null, filePath, userName, remark);
            }
            if (kongStart != null) {
                MACHINE_SERVICE.saveHistoryAndUpdateMachine(machineKeyId, mchNo, MaintenanceType.KONG_NAM,
                        machineMileage, kongStart, kongEnd, filePath, userName, remark);
            }
            if (hydDate != null) {
                MACHINE_SERVICE.saveHistoryAndUpdateMachine(machineKeyId, mchNo, MaintenanceType.HYDRAULIC,
                        machineMileage, hydDate, hydNext, filePath, userName, remark);
                //change ນ້ຳມັນhydraulic
                MERCHIN_HIS_REPOSITORY.updateMachineStatusToClosedTye2(mchNo, userName);
            }

            response.setStatus("00");
            response.setMessage("Saved maintenance successfully");
            response.setData(null);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("05");
            response.setMessage("An error occurred while saving data");
            response.setData(null);
        }

        return ResponseEntity.ok(response);
    }

    // --- Helper แปลง String เป็น LocalDate ---
    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        // ตัด " ออกกรณี Postman ส่งมาพร้อม "
        dateStr = dateStr.replace("\"", "").trim();
        return LocalDate.parse(dateStr);
    }

    //update
    @CrossOrigin(origins = "*")
    @PostMapping("/updateMaintenance")
    public ResponseEntity<?> updateHistory(
            @RequestParam("toKen") String toKen,
            @RequestParam("id") Integer id,
            @RequestParam(value = "dateChange", required = false) String dateChange,
            @RequestParam(value = "dateNext", required = false) String dateNext,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestPart(value = "files", required = false) MultipartFile[] imageFile) {

        MaintenanceMachineRes response = new MaintenanceMachineRes();

        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // จัดการไฟล์
            String filePath = null;
            String pathAdd = "http://khounkham.com/images/batery/";
            if (imageFile != null && imageFile.length > 0) {
                List<String> fileNames = new ArrayList<>();
                Arrays.asList(imageFile).forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMedia(file));
                });
                log.info("Uploaded files successfully: " + fileNames);
                filePath = pathAdd + StringUtils.join(fileNames, ',');
            }

            MACHINE_SERVICE.updateMaintenanceHistory(
                    id,
                    parseDate(dateChange),
                    parseDate(dateNext),
                    filePath,
                    remark
            );

            response.setStatus("00");
            response.setMessage("Updated maintenance successfully");
            response.setData(null);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("05");
            response.setMessage("An error occurred while updating data");
            response.setData(null);
        }

        return ResponseEntity.ok(response);
    }

    // ดึง history ทั้งหมดของเครื่องจักร
    @CrossOrigin(origins = "*")
    @PostMapping("/getMaintenance-by-machine")
    public ResponseEntity<?> getHistory(
            @RequestBody Map<String, Object> body) {

        MaintenanceMachineRes response = new MaintenanceMachineRes();

        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(
                    (String) body.get("toKen")
            );
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Integer machineKeyId = (Integer) body.get("machineKeyId");
            List<MachineMaintenanceHistoryResponse> data =
                    MACHINE_SERVICE.getHistoryByMachine(machineKeyId);

            response.setStatus("00");
            response.setMessage("Data retrieved successfully");
            response.setData(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("05");
            response.setMessage("An error occurred while retrieving data");
            response.setData(null);
        }

        return ResponseEntity.ok(response);
    }

    // ดึง history ตามประเภท
    @CrossOrigin(origins = "*")
    @PostMapping("/getMaintenance-by-type")
    public ResponseEntity<?> getHistoryByType(
            @RequestBody Map<String, Object> body) {

        MaintenanceMachineRes response = new MaintenanceMachineRes();

        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(
                    (String) body.get("toKen")
            );
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Integer machineKeyId = (Integer) body.get("machineKeyId");
            MaintenanceType type = MaintenanceType.valueOf(
                    (String) body.get("maintenanceType")
            );
            List<MachineMaintenanceHistoryResponse> data =
                    MACHINE_SERVICE.getHistoryByType(machineKeyId, type);

            response.setStatus("00");
            response.setMessage("Get Maintenance Data successfully");
            response.setData(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("05");
            response.setMessage("An error occurred while retrieving data");
            response.setData(null);
        }
        return ResponseEntity.ok(response);
    }
}
