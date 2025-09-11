package com.ldb.truck.Dao.MachineDao;

import com.ldb.truck.Model.Machine.*;

import java.util.List;

public interface MachineInterface {
  public List<Machine> getMachine(MachineRPReq machineRPReq);
  public List<MachineDetails> getReportMachineDetails(MachineRPReq machineRPReq);
  public List<MachineReport> getReportMachine(MachineRPReq machineRPReq);

  public int saveMachine(MachineReq machineReq);
  public int updateMachine(MachineReq machineReq);
  public int enableMachine(MachineReq machineReq);
}
