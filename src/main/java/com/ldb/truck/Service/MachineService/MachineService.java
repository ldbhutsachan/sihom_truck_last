package com.ldb.truck.Service.MachineService;

import com.ldb.truck.Dao.MachineDao.MachineInterface;
import com.ldb.truck.Model.Machine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {
    @Autowired
    MachineInterface machineInterface;
    public MachineResponse getMachine(MachineRPReq machineRPReq) {
        MachineResponse response = new MachineResponse();
        try {
            List<Machine> data = machineInterface.getMachine(machineRPReq);
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
    public MachineDetailsResponse getReportMachineDetail(MachineRPReq machineRPReq) {
        MachineDetailsResponse response = new MachineDetailsResponse();

        try {
            List<MachineDetails> data = machineInterface.getReportMachineDetails(machineRPReq);
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

    public MachineReportResposne getReportMachineSum(MachineRPReq machineRPReq) {
        MachineReportResposne response = new MachineReportResposne();

        try {
            List<MachineReport> data = machineInterface.getReportMachine(machineRPReq);
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

        try {
            int result = machineInterface.saveMachine(machineReq);

            if (result > 0) {
                response.setStatus("00");
                response.setMessage("Data saved successfully");
                response.setData(null); // You can return saved ID or object if needed
            } else {
                response.setStatus("01");
                response.setMessage("Failed to save data");
                response.setData(null);
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
