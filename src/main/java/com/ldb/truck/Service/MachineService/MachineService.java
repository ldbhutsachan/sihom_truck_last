package com.ldb.truck.Service.MachineService;

import com.ldb.truck.Dao.MachineDao.MachineInterface;
import com.ldb.truck.Model.Borcar.BorCarModel;
import com.ldb.truck.Model.Borcar.BorCarResponse;
import com.ldb.truck.Model.Borcar.Borcar;
import com.ldb.truck.Model.Borcar.BorcarReq;
import com.ldb.truck.Model.Login.Pay.PrintBillPayment;
import com.ldb.truck.Model.Login.Payment.GenerateInvoiceID;
import com.ldb.truck.Model.Login.Payment.PrintInvoiceByNo;
import com.ldb.truck.Model.Login.Performance.v_performance;
import com.ldb.truck.Model.Machine.*;
import com.ldb.truck.Repository.MachineHis.MerchinHisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.Timestamp;


@Service
@Slf4j
@RequiredArgsConstructor
public class MachineService {
    private final MachineInterface machineInterface;
    private final MerchinHisRepository MERCHIN_HIS_REPOSITORY;
    private final JdbcTemplate jdbcTemplate;



    public MachineResponse saveMachineHis(MachineHisReq machineHisReq,String userId){
        MachineResponse response = new MachineResponse();
        try {
            int check  = machineInterface.saveMachinedaily(machineHisReq,userId);
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
                response.setStatus("01");
                response.setMessage("out of update step!!!!");
                response.setData(null);
                return response;
            }
            response.setStatus("02");
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
public MachineResponse enableMachineHis(MachineHisReq machineHisReq, String userName) {
    MachineResponse response = new MachineResponse();

    try {
        int check;
        String type = machineHisReq.getType();
        String mchNo = machineHisReq.getMchNo();

        if ("1".equals(type)) {
            check = MERCHIN_HIS_REPOSITORY.updateMachineStatusToClosed(mchNo, userName);
        } else if ("2".equals(type)) {
            check = MERCHIN_HIS_REPOSITORY.updateMachineStatusToClosedTye2(mchNo, userName);
        } else {
            check = MERCHIN_HIS_REPOSITORY.updateMachineStatusToClosedTyeAll(mchNo, userName);
        }

        log.info("check: {}", check);

//        switch (check) {
//            case 1:
//                response.setStatus("00");
//                response.setMessage("Update completed successfully.");
//                break;
//            case 2:
//                response.setStatus("01");
//                response.setMessage("Machine already closed or no data to update.");
//                break;
//            default:
//                response.setStatus("00");
//                response.setMessage("Update data success!");
//        }
        if (check > 0) {
            response.setStatus("00");
            response.setMessage("Update completed successfully. Rows updated: " + check);
        } else {
            response.setStatus("01");
            response.setMessage("No data found to update.");
        }

    } catch (Exception e) {
        log.error("Error updating machine history", e);
        response.setStatus("99");
        response.setMessage("Error: Can't update data!");
    }

    response.setData(null);
    return response;
}

    public MachineHisResponse getMachineHis(MachineHisReq machineHisReq,String borNo){
        MachineHisResponse response = new MachineHisResponse();
        try {
            List<MachineHis> rspList = machineInterface.getMachineHis(machineHisReq, borNo);
            if (rspList != null && !rspList.isEmpty()) {
                response.setData(rspList);
                response.setMessage("OK");
                response.setStatus("00");
            } else {
                response.setData(null);
                response.setMessage("Do not data not found !!!!!");
                response.setStatus("00");
            }
        } catch (Exception e) {
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
                 machine.setPrice(resp.getPrice());
                 machine.setCurrency(resp.getCurrency());
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

                 machine.setAll_Used_Hours(resp.getAll_Used_Hours());
                 machine.setLast_engine_Hours(resp.getLast_engine_Hours());
                 machine.setLast_hydraulic_Hours(resp.getLast_hydraulic_Hours());
                 machine.setTotalFixMo(resp.getTotalFixMo());
                 machine.setTotalFixMoOil(resp.getTotalFixMoOil());
                 machine.setImage(resp.getImage());
                 machine.setDate_in(resp.getDate_in());

                 // ✅ เพิ่มตรงนี้เพื่อ map tools ด้วย
                 machine.setTools(resp.getTools() != null ? resp.getTools() : new ArrayList<>());

                 //ກຳນົດ limit monitor

                 //for checking
                 int timeOFix=resp.getTime_fix();
                 int timeHFix=resp.getTime_oil_fix();

                 int time1 = resp.getTime_fix_monitor();
                 int time_mo = resp.getTime_oil_fix_mo();
                 // ຄໍານວນ ຍໍ້າມັນ
                 int time2 = resp.getTotalFixMo();
                 // ຄໍານວນ ນໍ້າມັນ ໄຮໂດລິກ
                 int time3 = resp.getTotalFixMoOil();

                 String mesTime1 = "OK";
                 String mesTime2 = "LOW";
                 String mesTime3 = "EP";

                 if(timeOFix <= 0 && timeHFix <= 0){
                     totalMsg = mesTime1;
                     totalMs2 = mesTime1;
                 }else{
                     //ກວດສະຖານະນໍ້າມັຫນ
                     if(time2 > time1 ){
                         totalMsg = mesTime1;
                     }else if(time2 <=0  ){
                         totalMsg = mesTime3;
                     }else  {
                         totalMsg = mesTime2;
                     }
                     //ກວດສະຖານະນ ໄຮໂດລິກ
                     if(time3 > time_mo ){
                         totalMs2 = mesTime1;
                     }else if(time3 <= 0  ){
                         totalMs2 = mesTime3;
                     }else  {
                         totalMs2 = mesTime2;
                     }
                 }

                 machine.setStatus_mo(totalMsg);
                 machine.setStatus_oil_mo(totalMs2);

                 dataRsp.add(machine);
             }
            if (dataRsp != null && !dataRsp.isEmpty()) {
                response.setStatus("00");
                response.setMessage("successfully");
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

    public MachineReportSumResposne getSumReportMachine(MachineRPReq machineRPReq,String role,String borNo) {
        MachineReportSumResposne response = new MachineReportSumResposne();
        GroupHeaderReport groupHeader = new GroupHeaderReport();
        List<MachineSumRptModel> dataList = new ArrayList<>();
        double sumTotalThb = 0.0;
        double sumTotalUsd = 0.0;
        double sumTotalLak =0.0;
        try {
            List<MachineStockDetails> data = machineInterface.getSumReportMachine(machineRPReq, role, borNo);
//            if(data.size()> 1){
            if (data != null && data.size() > 0) {


                sumTotalLak = Optional.ofNullable(data)
                        .orElse(Collections.emptyList())
                        .stream()
                        .filter(p -> "LAK".equals(p.getCcy()))
                        .mapToDouble(MachineStockDetails::getTotal)
                        .sum();

                sumTotalThb = Optional.ofNullable(data)
                        .orElse(Collections.emptyList())
                        .stream()
                        .filter(p -> "THB".equals(p.getCcy()))
                        .mapToDouble(MachineStockDetails::getTotal)
                        .sum();

                sumTotalUsd = Optional.ofNullable(data)
                        .orElse(Collections.emptyList())
                        .stream()
                        .filter(p -> "USD".equals(p.getCcy()))
                        .mapToDouble(MachineStockDetails::getTotal)
                        .sum();




                List<String> merCodeList = data.stream()
                        .map(MachineStockDetails::getMchNo)
                        .distinct()
                        .collect(Collectors.toList());

                for (String merCo : merCodeList) {
                    List<MachineStockDetails> filtered = data.stream()
                            .filter(p -> merCo.equals(p.getMchNo()))
                            .collect(Collectors.toList());

                    if (filtered.isEmpty()) continue;

                    MachineSumRptModel rePData = new MachineSumRptModel();
                    MachineStockDetails first = filtered.get(0); // assuming consistent mchId/mchName per mchNo
                    rePData.setMchId(first.getKeyId());
                    rePData.setMchNo(first.getMchNo());
                    rePData.setMchName(first.getMchName());

                    List<MachineSumRptModel.GroupItemList> groupItemList = filtered.stream()
                            .map(p -> {
                                MachineSumRptModel.GroupItemList item = new MachineSumRptModel.GroupItemList();
                                item.setBillNo(p.getBillNo());
                                item.setSaveBy(p.getSaveBy());
                                item.setSaveDate(p.getSaveDate());
                                item.setApproveBy(p.getApproveBy());
                                item.setApproveDate(p.getApproveDate());

                                item.setBorNo(p.getBorNo());
                                item.setBorName(p.getBorName());
                                item.setItemId(p.getItemId());
                                item.setItemName(p.getItemName());
                                item.setCcy(p.getCcy());
                                item.setQty(p.getQty());
                                item.setUnit(p.getUnit());
                                item.setPrice(p.getPrice());
                                item.setTotal(p.getTotal());
                                return item;
                            })
                            .collect(Collectors.toList());

                    rePData.setGroupItemList(groupItemList);
                    dataList.add(rePData);
                }

                groupHeader.setSumLak(sumTotalLak);
                groupHeader.setSumthb(sumTotalThb);
                groupHeader.setSumUsd(sumTotalUsd);

                response.setStatus("00");
                response.setMessage("OK");
                response.setGroupHeader(groupHeader);
                response.setData(dataList);
            }
        } catch (Exception e){
            response.setStatus("05");
            response.setMessage(e.getMessage());
            response.setGroupHeader(null);
            response.setData(null);
        }
        return response;
    }

    // getReportBorCarService
    public BorCarResponse getReportBorCar(BorcarReq borcarReq, String role) {
        BorCarResponse response = new BorCarResponse();
        List<BorCarModel> dataList = new ArrayList<>();

        try {
            List<Borcar> data = machineInterface.getReportBorCar(borcarReq, role);

            if (!data.isEmpty()) {
                // ✅ ใช้ Map เพื่อรวมข้อมูลตาม car_id
                Map<Integer, BorCarModel> groupedData = new LinkedHashMap<>();

                for (Borcar b : data) {
                    int carId = Integer.parseInt(b.getCar_id());
                    BorCarModel model = groupedData.get(carId);

                    // ถ้ายังไม่มีข้อมูลรถคันนี้ -> สร้างใหม่
                    if (model == null) {
                        model = new BorCarModel();
                        model.setCar_id(carId);
                        model.setCar_number(b.getCar_number());
                        model.setBor_no(b.getBor_no());
                        model.setBor_name(b.getBor_name());
                        model.setGroupList(new ArrayList<>());
                        groupedData.put(carId, model);
                    }

                    //  เพิ่มข้อมูลใน groupList
                    BorCarModel.GroupList group = new BorCarModel.GroupList();
//                    group.setLicense_plate_end(b.getLicense_plate_end());
//                    group.setLicense_plate_start(b.getLicense_plate_start());
                    group.setSaveby_name(b.getSaveby_name());
                    group.setSavedate(b.getSavedate());
                    group.setBill_no(b.getBill_no());
                    group.setItem_id(b.getItem_id());
                    group.setItem_name(b.getItem_name());
                    group.setUnit(b.getUnit());
                    group.setPrice(b.getPrice());
                    group.setCurrency(b.getCurrency());
                    group.setQty(b.getQty());
                    group.setTotal(b.getTotal());
//                    group.setBor_no(b.getBor_no());
//                    group.setBor_name(b.getBor_name());
                    group.setApproveby(b.getApproveby());
                    group.setApprovedate(b.getApprovedate());
                    model.getGroupList().add(group);
                }

                //  แปลง Map → List
                dataList.addAll(groupedData.values());
                //  คำนวณยอดรวมตามสกุลเงิน
                for (BorCarModel car : dataList) {
                    double sumLak = 0.0;
                    double sumUsd = 0.0;
                    double sumThb = 0.0;

                    for (BorCarModel.GroupList g : car.getGroupList()) {
                        double total = 0.0;
                        try {
                            total = Double.parseDouble(g.getTotal());
                        } catch (NumberFormatException e) {
                            total = 0.0;
                        }

                        if ("LAK".equalsIgnoreCase(g.getCurrency())) {
                            sumLak += total;
                        } else if ("USD".equalsIgnoreCase(g.getCurrency())) {
                            sumUsd += total;
                        } else if ("THB".equalsIgnoreCase(g.getCurrency())) {
                            sumThb += total;
                        }
                    }

                    car.setSumlak(sumLak);
                    car.setSumusd(sumUsd);
                    car.setSumthb(sumThb);
                }
                response.setStatus("00");
                response.setMessage("fetch success");
                response.setData(dataList);
            } else {
                response.setStatus("04");
                response.setMessage("no data found");
                response.setData(new ArrayList<>());
            }
        } catch (Exception e) {
            log.error("❌ Error in getReportBorCar", e);
            response.setStatus("05");
            response.setMessage("internal error: " + e.getMessage());
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

    @Transactional
    public MachineReportResposne saveMachineWithTools(MachineReq machineReq) {
        MachineReportResposne response = new MachineReportResposne();
        try {
            // ตรวจสอบเครื่องจักรซ้ำ
            List<Machine> getMer = machineInterface.getMachineByMerchantNo(machineReq);
            if (getMer != null && !getMer.isEmpty()) {
                response.setStatus("EE");
                response.setMessage("This machine already exist in DB");
                response.setData(null);
                return response;
            }
            // 1. Insert เครื่องจักร
            int result = machineInterface.saveMachine(machineReq);

            // 2. Insert tools
            if (result > 0 && machineReq.getTools() != null) {
                for (MachineReq.ToolReq tool : machineReq.getTools()) {
                    String sqlTool = "INSERT INTO tb_machine_tool (mch_no, tool_name, qty, status, update_date, updated_by,unit) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    jdbcTemplate.update(sqlTool,
                            machineReq.getMchNo(),
                            tool.getToolName(),
                            tool.getQty(),
                            "ok",
                            new java.sql.Timestamp(System.currentTimeMillis()),
                            machineReq.getCreateBy(),
                            tool.getUnit() != null ? tool.getUnit() : ""
                    );

                }
            }

            response.setStatus("00");
            response.setMessage("Data saved successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage("Error while saving data");
        }
        return response;
    }

    @Transactional
    public MachineReportResposne updateMachine(MachineReq machineReq) {

        MachineReportResposne response = new MachineReportResposne();

        try {
            // 1. Update tb_machine
            int result = machineInterface.updateMachine(machineReq);

            if (result <= 0) {
                response.setStatus("01");
                response.setMessage("Failed to update machine");
                return response;
            }

            // 2. Update/Insert tools
            if (machineReq.getTools() != null) {

                List<MachineReq.ToolReq> incomingTools = machineReq.getTools();

                List<Long> incomingIds = new ArrayList<>();

                String insertSql = "INSERT INTO tb_machine_tool " +
                        "(mch_no, tool_name, qty, status, update_date, updated_by, unit) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

                String updateSql = "UPDATE tb_machine_tool " +
                        "SET tool_name = ?, qty = ?, unit = ?, status = 'ok', " +
                        "update_date = ?, updated_by = ? " +
                        "WHERE id = ? AND mch_no = ?";

                for (MachineReq.ToolReq tool : incomingTools) {

                    // =========================
                    // INSERT NEW TOOL
                    // =========================
                    if (tool.getId() == null) {

                        KeyHolder keyHolder = new GeneratedKeyHolder();

                        jdbcTemplate.update(connection -> {
                            PreparedStatement ps = connection.prepareStatement(
                                    insertSql,
                                    Statement.RETURN_GENERATED_KEYS
                            );

                            ps.setString(1, machineReq.getMchNo());
                            ps.setString(2, tool.getToolName());
                            ps.setInt(3, tool.getQty());
                            ps.setString(4, "ok"); // ✅ always ok
                            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                            ps.setString(6, machineReq.getCreateBy());
                            ps.setString(7, tool.getUnit() != null ? tool.getUnit() : "");

                            return ps;

                        }, keyHolder);

                        Long newId = keyHolder.getKey().longValue();
                        incomingIds.add(newId);

                    }

                    // =========================
                    // UPDATE EXISTING TOOL
                    // =========================
                    else {

                        jdbcTemplate.update(updateSql,
                                tool.getToolName(),
                                tool.getQty(),
                                tool.getUnit() != null ? tool.getUnit() : "",
                                new Timestamp(System.currentTimeMillis()),
                                machineReq.getCreateBy(),
                                tool.getId(),
                                machineReq.getMchNo()
                        );

                        incomingIds.add(tool.getId()); // ✅ important
                    }
                }

                // =========================
                // MARK NOT-USE (SAFE VERSION)
                // =========================
                if (!incomingIds.isEmpty()) {

                    String placeholders = incomingIds.stream()
                            .map(id -> "?")
                            .collect(Collectors.joining(","));

                    String updateStatusSql =
                            "UPDATE tb_machine_tool SET status = 'NOT-USE' " +
                                    "WHERE mch_no = ? AND id NOT IN (" + placeholders + ")";

                    List<Object> params = new ArrayList<>();
                    params.add(machineReq.getMchNo());
                    params.addAll(incomingIds);

                    jdbcTemplate.update(updateStatusSql, params.toArray());
                }
            }

            // =========================
            // RESPONSE
            // =========================
            response.setStatus("00");
            response.setMessage("Data updated successfully");
            response.setData(null);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("05");
            response.setMessage("An error occurred while updating data");
            response.setData(null);
        }

        return response;
    }
}
