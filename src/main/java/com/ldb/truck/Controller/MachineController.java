package com.ldb.truck.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Borcar.BorCarResponse;
import com.ldb.truck.Model.Borcar.BorcarReq;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Machine.*;
import com.ldb.truck.Service.MachineService.MachineService;
import com.ldb.truck.Service.MediaUploadServiceImpl;
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


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("${base_url}")
public class MachineController {
private final ProfileDao profileDao;
private final  MachineService MACHINE_SERVICE;
@Autowired
 private MediaUploadService mediaUploadService;

    public MachineController(ProfileDao profileDao, MachineService machineService) {
        this.profileDao = profileDao;
        MACHINE_SERVICE = machineService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/acceptMachineHis.service")
    public ResponseEntity<?>  acceptMachineHis(@RequestBody AceptItemReq machineRPReq){
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userName = userProfiles.get(0).getUserName();
        log.info("show role"+ userName);
        try {
            response = MACHINE_SERVICE.aceptMachineHis(machineRPReq,userName);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/saveMachineHis.service")
    public ResponseEntity<?>  saveMachineHis(@RequestBody MachineHisReq machineRPReq){
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        log.info("show role"+ userId);
        try {
            response = MACHINE_SERVICE.saveMachineHis(machineRPReq,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateMachineHis.service")
    public ResponseEntity<?>  updateMachineHis(@RequestBody MachineHisReq machineRPReq){
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userName = userProfiles.get(0).getUserName();
        log.info("show role"+ userName);
        try {
            response = MACHINE_SERVICE.updateMachineHis(machineRPReq,userName);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

 @CrossOrigin(origins = "*")
    @PostMapping("/enableMachineHis.service")
    public ResponseEntity<?>  enableMachineHis(@RequestBody MachineHisReq machineRPReq){
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userName = userProfiles.get(0).getUserName();
        try {
            response = MACHINE_SERVICE.enableMachineHis(machineRPReq,userName);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getMachineHis.service")
    public ResponseEntity<?>  getMachineHis(@RequestBody MachineHisReq machineRPReq){
        MachineHisResponse response = new MachineHisResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        log.info("show role"+ role);
        try {
            response = MACHINE_SERVICE.getMachineHis(machineRPReq,borNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getRequestItemList.service")
    public ResponseEntity<?>  getRequestItemList(@RequestBody MachineStockDetailsReq machineRPReq){
        MachineStockDetailsResponse response = new MachineStockDetailsResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        log.info("show role"+ role);
        try {
            response = MACHINE_SERVICE.getRequestItemList(machineRPReq,borNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getMachine.service")
    public ResponseEntity<?>  getMachine(@RequestBody MachineRPReq machineRPReq){
        MachineResponse response = new MachineResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        log.info("show role"+ role);
        try {
            response = MACHINE_SERVICE.getMachine(machineRPReq,role,borNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getReportMachineDetail.service")
    public ResponseEntity<?>  getReportMachineDetail(@RequestBody MachineRPReq machineRPReq){
        MachineDetailsResponse response = new MachineDetailsResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        try {
            response = MACHINE_SERVICE.getReportMachineDetail(machineRPReq,role,borNo);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getSumReportMachine.service")
    public ResponseEntity<?> getSumReportMachine(@RequestBody MachineRPReq machineRPReq){
        MachineReportSumResposne response = new MachineReportSumResposne();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        try {
            response = MACHINE_SERVICE.getSumReportMachine(machineRPReq,role,borNo);
        }catch (Exception e){
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
    public ResponseEntity<?> getReportMachineSum(@RequestBody MachineRPReq machineRPReq){
        MachineReportResposne response = new MachineReportResposne();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String role = userProfiles.get(0).getRole();
        String borNo = userProfiles.get(0).getBorNo();
        try {
            response = MACHINE_SERVICE.getReportMachineSum(machineRPReq,role,borNo);
        }catch (Exception e){
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
//        @RequestParam(value = "date_in", required = false) String date_in // for insert like text
         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date_in  // for insert date_in in local Datetime
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

        // แปลง JSON string ของ tools เป็น List<ToolReq>
        if (toolsJson != null && !toolsJson.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            List<MachineReq.ToolReq> tools = mapper.readValue(
                    toolsJson,
                    new TypeReference<List<MachineReq.ToolReq>>() {}
            );
            machineReq.setTools(tools);
        }
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        String pathAdd="http://khounkham.com/images/batery/";
        if(imageFile == null){
            log.warn("************* file name is null ****************");
            machineReq.setImage("http://khounkham.com/images/image.jpg");
        }else {
            Arrays.asList(imageFile).stream().forEach(file -> {
                fileNames.add(mediaUploadService.uploadMedia(file));
            });
            log.info("Uploaded the files successfully: "+ fileNames );
            fileName = StringUtils.join(fileNames, ',');
            machineReq.setImage(pathAdd+fileName);
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
        @RequestParam(value = "tools", required = false) String toolsJson,
        @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,

//        @RequestParam(value = "date_in", required = false) String date_in // for insert date_in like text
        @RequestParam(value = "date_in", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                LocalDate date_in


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
    machineReq.setDate_in(date_in);

    if (toolsJson != null && !toolsJson.isEmpty()) {
        ObjectMapper mapper = new ObjectMapper();
        List<MachineReq.ToolReq> tools = mapper.readValue(
                toolsJson, new TypeReference<List<MachineReq.ToolReq>>() {}
        );
        machineReq.setTools(tools);
    }
    String fileName = "";
    List<String> fileNames = new ArrayList<>();
    String pathAdd="http://khounkham.com/images/batery/";
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
}
