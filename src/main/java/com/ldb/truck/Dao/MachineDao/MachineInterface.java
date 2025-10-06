package com.ldb.truck.Dao.MachineDao;

import com.ldb.truck.Model.Machine.*;

import java.util.List;

public interface MachineInterface {
//  public List<MachineStockDetails> getSumReportMachine(MachineRPReq machineRPReq,String role,String borNo);
  public int acceptItem(AceptItemReq req,String userName);

  public List<MachineStockDetails> getRequestItemList(MachineStockDetailsReq req,String borNo);
  public List<MachineHis> getMachineHis(MachineHisReq machineHisReq,String borNo);
  public int saveMachinedaily(MachineHisReq machineHisReq,String userId);
  public int updateMachinedaily(MachineHisReq machineHisReq);
  public int enableMachinedaily(MachineHisReq machineHisReq);
  public List<Machine> getMachineByMerchantNo(MachineReq machineRPReq);
  public List<Machine> getMachine(MachineRPReq machineRPReq,String role, String borNo);
  public List<MachineDetails> getReportMachineDetails(MachineRPReq machineRPReq ,String role ,String borNo);
  public List<MachineReport> getReportMachine(MachineRPReq machineRPReq,String role,String borNo);

  public int saveMachine(MachineReq machineReq);
  public int updateMachine(MachineReq machineReq);


}
