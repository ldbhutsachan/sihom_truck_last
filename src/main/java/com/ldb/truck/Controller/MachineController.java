package com.ldb.truck.Controller;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Machine.*;
import com.ldb.truck.Service.MachineService.MachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("${base_url}")
public class MachineController {
private final ProfileDao profileDao;
private final  MachineService MACHINE_SERVICE;

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
        log.info("show role"+ userName);
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
//@CrossOrigin(origins = "*")
//    @PostMapping("/getSumReportMachine.segetSumReportMachinervice")
//    public ResponseEntity<?> (@RequestBody MachineRPReq machineRPReq){
//    MachineReportSumResposne response = new MachineReportSumResposne();
//        List<Profile> userProfiles = profileDao.getProfileInfoByToken(machineRPReq.getToKen());
//        if (userProfiles.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        String role = userProfiles.get(0).getRole();
//        String borNo = userProfiles.get(0).getBorNo();
//        try {
//            response = MACHINE_SERVICE.getSumReportMachine(machineRPReq,role,borNo);
//        }catch (Exception e){
//            response.setStatus("EE");
//            response.setMessage("Data Error !!");
//        }
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @CrossOrigin(origins = "*")
    @PostMapping("/saveMachine.service")
    public MachineReportResposne saveMachine(@RequestBody MachineReq machineRPReq){
        MachineReportResposne result = new MachineReportResposne();
        try {
            result = MACHINE_SERVICE.saveMachine(machineRPReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Error !!");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateMachine.service")
    public MachineReportResposne updateMachine(@RequestBody MachineReq machineRPReq){
        MachineReportResposne result = new MachineReportResposne();
        try {
            result = MACHINE_SERVICE.updateMachine(machineRPReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Error !!");
            return result;
        }
        return result;
    }
}
