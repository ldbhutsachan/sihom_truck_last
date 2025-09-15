package com.ldb.truck.Dao.MachineDao;

import com.ldb.truck.Model.Machine.*;

import java.util.List;

public interface MachineInterface {
  public List<Machine> getMachineByMerchantNo(MachineReq machineRPReq);
  public List<Machine> getMachine(MachineRPReq machineRPReq,String role, String borNo);
  public List<MachineDetails> getReportMachineDetails(MachineRPReq machineRPReq ,String role ,String borNo);
  public List<MachineReport> getReportMachine(MachineRPReq machineRPReq,String role,String borNo);

  public int saveMachine(MachineReq machineReq);
  public int updateMachine(MachineReq machineReq);
  public int enableMachine(MachineReq machineReq);
}
