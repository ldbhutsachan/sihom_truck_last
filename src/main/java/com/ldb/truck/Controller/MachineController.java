package com.ldb.truck.Controller;

import com.ldb.truck.Model.Machine.*;
import com.ldb.truck.Service.MachineService.MachineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class MachineController {
private final  MachineService MACHINE_SERVICE;

    public MachineController(MachineService machineService) {
        MACHINE_SERVICE = machineService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getMachine.service")
    public MachineResponse getMachine(@RequestBody MachineRPReq machineRPReq){
        MachineResponse result = new MachineResponse();
        try {
            result = MACHINE_SERVICE.getMachine(machineRPReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Error !!");
            return result;
        }
        return result;
    } @CrossOrigin(origins = "*")
    @PostMapping("/getReportMachineDetail.service")
    public MachineDetailsResponse getReportMachineDetail(@RequestBody MachineRPReq machineRPReq){
        MachineDetailsResponse result = new MachineDetailsResponse();
        try {
            result = MACHINE_SERVICE.getReportMachineDetail(machineRPReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Error !!");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getReportMachineSum.service")
    public MachineReportResposne getReportMachineSum(@RequestBody MachineRPReq machineRPReq){
        MachineReportResposne result = new MachineReportResposne();
        try {
            result = MACHINE_SERVICE.getReportMachineSum(machineRPReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Error !!");
            return result;
        }
        return result;
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
