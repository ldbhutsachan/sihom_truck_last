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
