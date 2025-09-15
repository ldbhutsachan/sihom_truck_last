package com.ldb.truck.Service.MachineService;

import com.ldb.truck.Dao.MachineDao.MachineInterface;
import com.ldb.truck.Model.Machine.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MachineService {
    private final MachineInterface machineInterface;

    public MachineService(MachineInterface machineInterface) {
        this.machineInterface = machineInterface;
    }

    public MachineResponse saveMachineHis(MachineHisReq machineHisReq,String userName){
        MachineResponse response = new MachineResponse();
        try {
            int check  = machineInterface.saveMachinedaily(machineHisReq,userName);
            if(check >= 1 ){
                response.setStatus("00");
                response.setMessage("OK");
                response.setData(null);
                return response;
            }
            response.setStatus("00");
            response.setMessage("Can't Save Data !!!!");
            response.setData(null);
            return response;

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("00");
            response.setMessage("Error Can't Save Data !!!!");
            response.setData(null);
            //return response;
        }

        return response;
    }
    public MachineResponse aceptMachineHis(AceptItemReq machineHisReq,String userName){
        MachineResponse response = new MachineResponse();
        try {
            int check  = machineInterface.acceptItem(machineHisReq,userName);
            if(check >= 1 ){
                response.setStatus("00");
                response.setMessage("OK");
                response.setData(null);
                return response;
            }
            response.setStatus("00");
            response.setMessage("Can't Accept Data !!!!");
            response.setData(null);
            return response;

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("00");
            response.setMessage("Error Can't Accept Data !!!!");
            response.setData(null);
            //return response;
        }

        return response;
    }
    public MachineResponse updateMachineHis(MachineHisReq machineHisReq,String userName){
        MachineResponse response = new MachineResponse();
        try {
            int check  = machineInterface.updateMachinedaily(machineHisReq);
            if(check == 1  ){
                response.setStatus("00");
                response.setMessage("OK");
                response.setData(null);
                return response;
            }
            else if(check == 2){
                response.setStatus("00");
                response.setMessage("You're data is Error!!!!");
                response.setData(null);
                return response;
            }
            response.setStatus("00");
            response.setMessage("Can't update Data !!!!");
            response.setData(null);
            return response;

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("00");
            response.setMessage("Error Can't update Data !!!!");
            response.setData(null);
        }

        return response;
    }

    public MachineResponse enableMachineHis(MachineHisReq machineHisReq,String userName){
        MachineResponse response = new MachineResponse();
        try {
            int check  = machineInterface.enableMachinedaily(machineHisReq);
            if(check == 1  ){
                response.setStatus("00");
                response.setMessage("OK");
                response.setData(null);
                return response;
            }
            else if(check == 2){
                response.setStatus("00");
                response.setMessage("You're data is Error!!!!");
                response.setData(null);
                return response;
            }
            response.setStatus("00");
            response.setMessage("Can't update Data !!!!");
            response.setData(null);
            return response;

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("00");
            response.setMessage("Error Can't update Data !!!!");
            response.setData(null);
            //return response;
        }

        return response;
    }
    public MachineHisResponse getMachineHis(MachineHisReq machineHisReq,String borNo){
        MachineHisResponse response = new MachineHisResponse();

        try {
            List<MachineHis> rspList = machineInterface.getMachineHis(machineHisReq,borNo);

            if (rspList != null && !rspList.isEmpty()){
                response.setData(rspList);
                response.setMessage("OK");
                response.setStatus("00");
            }
            response.setData(null);
            response.setMessage("Do not data not found !!!!!");
            response.setStatus("00");

        }catch (Exception e){
            response.setData(null);
            response.setMessage("Error !!!!!");
            response.setStatus("05");
            e.printStackTrace();
        }
        return  response;

    }
    public MachineStockDetailsResponse getRequestItemList(MachineStockDetailsReq machineHisReq,String borNo){
        MachineStockDetailsResponse response = new MachineStockDetailsResponse();

        try {
            List<MachineStockDetails> rspList = machineInterface.getRequestItemList(machineHisReq,borNo);

            response.setData(rspList);
            response.setMessage("OK");
            response.setStatus("00");

        }catch (Exception e){
            response.setData(null);
            response.setMessage("Error !!!!!");
            response.setStatus("05");
            e.printStackTrace();
        }
        return  response;

    }

    public MachineResponse getMachine(MachineRPReq machineRPReq,String role,String borNo) {
        String mesTime1 = "OK";
        String mesTime2 = "LOW";
        String mesTime3 = "EP";
        String totalMsg = "";
        String totalMs2 = "";
        MachineResponse response = new MachineResponse();
        List<Machine> data = new ArrayList<>();
        List<Machine> dataRsp = new ArrayList<>();
        try {
             data = machineInterface.getMachine(machineRPReq,role,borNo);
             for (Machine resp : data){
                 Machine machine = new Machine();
                 machine.setKeyId(resp.getKeyId());
                 machine.setMchNo(resp.getMchNo());
                 machine.setMchName(resp.getMchName());
                 machine.setMchBranchName(resp.getMchBranchName());
                 machine.setMchModel(resp.getMchModel());
                 machine.setMchProductYear(resp.getMchProductYear());
                 machine.setCreateDate(resp.getCreateDate());
                 machine.setCreateBy(resp.getCreateBy());
                 machine.setUserLogin(resp.getUserLogin());
                 machine.setRole(resp.getRole());
                 machine.setStatus(resp.getStatus());
                 machine.setBorNo(resp.getBorNo());
                 machine.setBorName(resp.getBorName());
                 machine.setBorLocationName(resp.getBorLocationName());
                 machine.setTime_fix(resp.getTime_fix());
                 machine.setTime_fix_monitor(resp.getTime_fix_monitor());
                 machine.setTime_oil_fix(resp.getTime_oil_fix());
                 machine.setTime_oil_fix_mo(resp.getTime_oil_fix_mo());

                 machine.setTotalFixMo(resp.getTotalFixMo());
                 machine.setTotalFixMoOil(resp.getTotalFixMoOil());

                 //ກຳນົດ limit monitor
                 int time1 = resp.getTime_fix_monitor();
                 // ຄໍານວນ ຍໍ້າມັນ
                 int time2 = resp.getTotalFixMo();
                 // ຄໍານວນ ນໍ້າມັນ ໄຮໂດລິກ
                 int time3 = resp.getTotalFixMoOil();

                 //ກວດສະຖານະນໍ້າມັຫນ
                 if(time2 > time1 ){
                     totalMsg = mesTime1;
                 }else if(time2 ==0  ){
                     totalMsg = mesTime3;
                 }else  {
                     totalMsg = mesTime2;
                 }
                 //ກວດສະຖານະນ ໄຮໂດລິກ
                 if(time3 > time1 ){
                     totalMs2 = mesTime1;
                 }else if(time3 ==0  ){
                     totalMs2 = mesTime3;
                 }else  {
                     totalMs2 = mesTime2;
                 }

                 machine.setStatus_mo(totalMsg);
                 machine.setStatus_oil_mo(totalMs2);

                 dataRsp.add(machine);
             }
            if (dataRsp != null && !dataRsp.isEmpty()) {
                response.setStatus("00");
                response.setMessage("OK");
                response.setData(dataRsp);
            } else {
                response.setStatus("01");
                response.setMessage("Data not found  !!!");
                response.setData(null);
            }

        } catch (Exception e) {
            response.setStatus("05");
            response.setMessage("Error occurred while retrieving data");
            response.setData(null);
        }

        return response;
    }
    public MachineDetailsResponse getReportMachineDetail(MachineRPReq machineRPReq,String role ,String borNo) {
        MachineDetailsResponse response = new MachineDetailsResponse();
        try {
            List<MachineDetails> data = machineInterface.getReportMachineDetails(machineRPReq,role,borNo);
            if (data != null && !data.isEmpty()) {
                response.setStatus("00");
                response.setMessage("OK");
                response.setData(data);
            } else {
                response.setStatus("01");
                response.setMessage("Data not found");
                response.setData(null);
            }

        } catch (Exception e) {
            response.setStatus("05");
            response.setMessage("Error occurred while retrieving data");
            response.setData(null);
        }

        return response;
    }

    public MachineReportResposne getReportMachineSum(MachineRPReq machineRPReq,String role,String borNo) {
        MachineReportResposne response = new MachineReportResposne();

        try {
            List<MachineReport> data = machineInterface.getReportMachine(machineRPReq,role,borNo);

            if (data != null && !data.isEmpty()) {
                response.setStatus("00");
                response.setMessage("OK");
                response.setData(data);
            } else {
                response.setStatus("01");
                response.setMessage("Data not found");
                response.setData(null);
            }

        } catch (Exception e) {
            response.setStatus("05");
            response.setMessage("Error occurred while retrieving data");
            response.setData(null);
        }

        return response;
    }

    public MachineReportResposne saveMachine(MachineReq machineReq) {
        MachineReportResposne response = new MachineReportResposne();
        int result = 0;
        try {
            ///ກວດສອບ merCode
            List<Machine> getMer = machineInterface.getMachineByMerchantNo(machineReq);
            if (getMer != null && !getMer.isEmpty()) {
                response.setStatus("EE");
                response.setMessage("ຂໍ້ມູນເຄື່ອງຈັກຂອງມີເເລ້ວ ມັນຊໍາກັນ !!!!");
                response.setData(null); // You can return saved ID or object if needed
            }else {
                result = machineInterface.saveMachine(machineReq);
                if (result > 0) {
                    response.setStatus("00");
                    response.setMessage("Data saved successfully");
                    response.setData(null); // You can return saved ID or object if needed
                } else {
                    response.setStatus("01");
                    response.setMessage("Failed to save data");
                    response.setData(null);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Consider logging this instead
            response.setStatus("05");
            response.setMessage("An error occurred while saving data");
            response.setData(null);
        }

        return response;
    }

    public MachineReportResposne updateMachine(MachineReq machineReq) {
        MachineReportResposne response = new MachineReportResposne();
        try {
            int result = machineInterface.updateMachine(machineReq);
            if (result > 0) {
                response.setStatus("00");
                response.setMessage("Data Update successfully");
                response.setData(null); // You can return saved ID or object if needed
            } else {
                response.setStatus("01");
                response.setMessage("Failed to Update data");
                response.setData(null);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Consider logging this instead
            response.setStatus("05");
            response.setMessage("An error occurred while Updating data");
            response.setData(null);
        }

        return response;
    }
}
