package com.ldb.truck.Dao.MachineDao;

import com.ldb.truck.Entity.MerchineHis.MerchineHisEntity;
import com.ldb.truck.Entity.RequestItem.RequestItemEbtity;
import com.ldb.truck.Model.Machine.*;
import com.ldb.truck.Repository.MachineHis.MerchinHisRepository;
import com.ldb.truck.Repository.RequestItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MachineDao implements MachineInterface {
    private final MerchinHisRepository MERCHIN_HIS_REPOSITORY;
    private final RequestItemRepository repository;

    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate JdbcTemplate;

    public MachineDao(MerchinHisRepository merchinHisRepository, RequestItemRepository repository) {
        MERCHIN_HIS_REPOSITORY = merchinHisRepository;
        this.repository = repository;
    }

    @Override
    public List<MachineStockDetails> getSumReportMachine(MachineRPReq req, String role, String borNo) {
        StringBuilder sb = new StringBuilder();
        List<String> conditions = new ArrayList<>();
        String orderData = " ORDER BY d.savedate DESC ";

        try {
            // ✅ Date filter
            if (req.getStartDate() != null && !req.getStartDate().isEmpty()
                    && req.getEndDate() != null && !req.getEndDate().isEmpty()) {
                conditions.add("DATE(d.using_date) >= '" + req.getStartDate() + "'");
                conditions.add("DATE(d.using_date) <= '" + req.getEndDate() + "'");
            }

            // ✅ Status filter
            if (req.getStatus() != null && !req.getStatus().isEmpty()) {
                // ปรับให้ case-insensitive
                conditions.add("LOWER(d.using_status) = '" + req.getStatus().toLowerCase() + "'");
            }

            // ✅ BorNo filter
            if (borNo != null && !borNo.isEmpty()) {
                conditions.add("d.bor_no = '" + borNo + "'");
            }

            // ✅ MchNo filter
            if (req.getMchNo() != null && !req.getMchNo().isEmpty()) {
                conditions.add("b.mch_no = '" + req.getMchNo() + "'");
            }
            // ✅ Base SQL
            sb.append("SELECT \n")
                    .append(" d.saveby_name,d.savedate,\n" +
                            "d.approveby,d.approvedate,d.detail_id AS key_id, \n")
                    .append(" b.mch_no, d.using_date AS create_date, d.saveby_name AS create_by, \n")
                    .append(" 0 AS time_total, d.using_date AS txn_date, 0 AS status, \n")
                    .append(" b.mch_name, b.mch_branch_name, b.mch_model, b.mch_product_year, \n")
                    .append(" d.bor_no, d.bor_name, \n")
                    .append(" d.bill_no, d.item_id, d.item_name, d.unit, \n")
                    .append(" d.currency, d.qty, d.price, d.price*d.qty AS total, \n")
                    .append(" CASE WHEN d.using_status IS NULL THEN 'wait' ELSE d.using_status END AS using_status \n")
                    .append("FROM tb_machine b \n")
                    .append("INNER JOIN tb_bors c ON b.borNo = c.key_id \n")
                    .append("LEFT JOIN v_request_item_fix d ON d.bor_no = b.borNo AND d.jukNo = b.mch_no \n")
                    .append("WHERE 1=1 ");

            // ✅ Append all conditions
            for (String cond : conditions) {
                sb.append(" AND ").append(cond);
            }

            sb.append(orderData);

            String sql = sb.toString();
            log.info("SQL: " + sql);

            // Execute query
            return JdbcTemplate.query(sql, (rs, rowNum) -> {
                MachineStockDetails tr = new MachineStockDetails();
                tr.setKeyId(rs.getInt("key_id"));
                tr.setMchNo(rs.getString("mch_no"));
                tr.setMchName(rs.getString("mch_name"));

                tr.setSaveBy(rs.getString("saveby_name"));
                tr.setSaveDate(rs.getString("savedate"));
                tr.setApproveBy(rs.getString("approveby"));
                tr.setApproveDate(rs.getString("approvedate"));

                tr.setBorNo(rs.getString("bor_no"));
                tr.setBorName(rs.getString("bor_name"));
                tr.setItemId(rs.getString("item_id"));
                tr.setItemName(rs.getString("item_name"));
                tr.setCcy(rs.getString("currency"));
                tr.setQty(rs.getInt("qty"));
                tr.setPrice(rs.getDouble("price"));
                tr.setTotal(rs.getDouble("total"));
                return tr;
            });

        } catch (Exception e) {
            log.error("Error in getSumReportMachine", e);
            return new ArrayList<>(); // return empty list แทน null
        }
    }




    @Override
    public int acceptItem(AceptItemReq req,String userName) {
        List<Integer> itemList = req.getItemList();

        // Assuming the repository method exists to find items by a list of IDs
        List<RequestItemEbtity> entities = repository.findByKeyIdRequest(req.getBillNo(), itemList);

        if (!entities.isEmpty()) {
            for (RequestItemEbtity entity : entities) {
                entity.setUsing_status("ok");
                entity.setUsing_date(new Date());
                entity.setUsingBy(userName);
            }

            try {
                repository.saveAll(entities); // Save all updated entities
                return 1; // Return 1 for success
            } catch (Exception e) {
                e.printStackTrace();
                return -1; // Return -1 for failure
            }
        } else {
            return 2; // Return 2 if no entities were found
        }
    }

//    @Override
//    public List<MachineStockDetails> getRequestItemList(MachineStockDetailsReq req,String borNo) {
//        StringBuilder sb = new StringBuilder();
//        String itemId= req.getItemId();
//        String bill = req.getBillNo();
//        String status = req.getStatus();
//        String conItem = "";
//        String conBill = "";
//        String conBorNo = "";
//        String conDate = "";
//        String conStatus= "";
//        String orderData  = "\n order by d.savedate desc ";
//
//        try {
//            if (req.getStartDate() != null && !req.getStartDate().isEmpty()) {
//                conDate = "\nAND DATE_FORMAT(using_date, '%Y-%m-%d') >= '"+req.getStartDate()+"'  AND DATE_FORMAT(using_date, '%Y-%m-%d')  <= '"+req.getStartDate()+"'";
//            } else {
//                conDate = "\n ";
//            }
//            if (status != null && !status.isEmpty()) {
//                conStatus = "\n AND using_status = '" + status + "' ";
//            } else {
//                conStatus = "\n AND using_status is null ";
//            }
//            if (itemId != null && !itemId.isEmpty()) {
//                conItem = "\n AND d.item_id = '" + itemId + "' ";
//            } else {
//                conItem = "";
//            }
//            if(bill != null && !bill.isEmpty()){
//                conBill = "\n AND d.bill_no ='"+bill+"' ";
//            }else {
//                conBill = "";
//            }
//            if(!borNo.isEmpty() || !borNo.equals(null)){
//                conBorNo = "\n AND d.bor_no = '"+borNo+"'";
//            }else {
//                conBorNo = "";
//            }
//            //a.key_id,a.mch_no,a.create_date,a.create_by,a.time_total,a.txn_date,a.status,
//            sb.append("select \n" +
//                    "d.detail_id key_id,b.mch_no,using_date create_date,d.saveby_name create_by,0 time_total,d.using_date txn_date,0 status,\n" +
//                    "b.mch_name,b.mch_branch_name,b.mch_model,b.mch_product_year,\n" +
//                    "d.bor_no,d.bor_name,\n" +
//                    "bill_no,d.item_id,d.item_name,d.unit,\n" +
//                    "d.currency,d.qty,d.price,d.price*d.qty total,\n" +
//                    "case when using_status is null then 'wait' else 'ok'  end using_status\n" +
//                    "from  tb_machine b \n" +
//                    "inner join  tb_bors c on b.borNo=c. key_id\n" +
//                    "inner join v_request_item_fix d on d.bor_no=b.borNo\n" +
//                    "where using_status  is  null  and  1=1  ");
//            sb.append(conItem);
//            sb.append(conBill);
//            sb.append(conBorNo);
//            sb.append(conStatus);
//            sb.append(conDate);
//            sb.append(orderData);
//            String sql =  sb.toString();
//            log.info("info sql:"+sql);
//            return JdbcTemplate.query(sql, new RowMapper<MachineStockDetails>() {
//                @Override
//                public MachineStockDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    MachineStockDetails tr = new MachineStockDetails();
//                    tr.setKeyId(rs.getInt("key_id"));
//                    tr.setMchNo(rs.getString("mch_no"));
//                    tr.setCreateDate(rs.getTimestamp("create_date"));
//                    tr.setCreateBy(rs.getString("create_by"));
//                    tr.setTimeTotal(rs.getInt("time_total"));
//                    tr.setTxnDate(rs.getDate("txn_date"));
//                    tr.setStatus(rs.getInt("status"));
//                    tr.setMchName(rs.getString("mch_name"));
//                    tr.setMchBranchName(rs.getString("mch_branch_name"));
//                    tr.setMchModel(rs.getString("mch_model"));
//                    tr.setMchProductYear(rs.getInt("mch_product_year"));
//                    tr.setBorNo(rs.getString("bor_no"));
//                    tr.setBorName(rs.getString("bor_name"));
//                    tr.setBillNo(rs.getString("bill_no"));
//                    tr.setItemId(rs.getString("item_id"));
//                    tr.setItemName(rs.getString("item_name"));
//                    tr.setUnit(rs.getString("unit"));
//                    tr.setCurrency(rs.getString("currency"));
//                    tr.setQty(rs.getInt("qty"));
//                    tr.setPrice(rs.getDouble("price"));
//                    tr.setTotal(rs.getDouble("total"));
//                    tr.setUsingStatus(rs.getString("using_status"));
//                    return tr;
//                }
//            });
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
@Override
public List<MachineStockDetails> getRequestItemList(MachineStockDetailsReq req, String borNo) {
    StringBuilder sb = new StringBuilder();

    try {
        // Base SQL
        sb.append("SELECT \n" +
                "    MIN(d.detail_id) AS key_id,\n" +
                "    MIN(d.using_date) AS create_date,\n" +
                "    MIN(d.saveby_name) AS create_by,\n" +
                "    0 AS time_total,\n" +
                "    MIN(d.using_date) AS txn_date,\n" +
                "    0 AS status,\n" +
                "    d.bill_no,\n" +
                "    d.item_id,\n" +
                "    d.item_name,\n" +
                "    d.unit,\n" +
                "    d.currency,\n" +
                "    SUM(d.qty) AS qty,\n" +
                "    AVG(d.price) AS price,\n" +
                "    SUM(d.price * d.qty) AS total,\n" +
                "    CASE WHEN d.using_status IS NULL THEN 'wait' ELSE 'ok' END AS using_status,\n" +
                "    MIN(b.mch_no) AS mch_no,\n" +
                "    MIN(b.mch_name) AS mch_name,\n" +
                "    MIN(b.mch_branch_name) AS mch_branch_name,\n" +
                "    MIN(b.mch_model) AS mch_model,\n" +
                "    MIN(b.mch_product_year) AS mch_product_year,\n" +
                "    MIN(d.bor_no) AS bor_no,\n" +
                "    MIN(d.bor_name) AS bor_name\n" +
                "FROM tb_machine b\n" +
                "INNER JOIN tb_bors c ON b.borNo = c.key_id\n" +
                "INNER JOIN v_request_item_fix d ON d.jukNo = b.mch_no\n" +
                "WHERE 1=1  and d.status ='ok'");

        // Dynamic conditions
        if (req.getItemId() != null && !req.getItemId().isEmpty()) {
            sb.append(" AND d.item_id = '" + req.getItemId() + "' ");
        }

        if (req.getBillNo() != null && !req.getBillNo().isEmpty()) {
            sb.append(" AND d.bill_no = '" + req.getBillNo() + "' ");
        }

        if (borNo != null && !borNo.isEmpty()) {
            sb.append(" AND d.bor_no = '" + borNo + "' ");
        }

        if (req.getStatus() != null && !req.getStatus().isEmpty()) {
            sb.append(" AND d.using_status = '" + req.getStatus() + "' ");
        } else {
            sb.append(" AND d.using_status IS NULL ");
        }

        if (req.getStartDate() != null && !req.getStartDate().isEmpty()) {
            sb.append(" AND DATE_FORMAT(d.using_date, '%Y-%m-%d') >= '" + req.getStartDate() + "' ");
        }

        if (req.getEndDate() != null && !req.getEndDate().isEmpty()) {
            sb.append(" AND DATE_FORMAT(d.using_date, '%Y-%m-%d') <= '" + req.getEndDate() + "' ");
        }

        // Group by for aggregation
        sb.append(" GROUP BY d.bill_no, d.item_id, d.item_name, d.unit, d.currency, d.using_status ");

        // Order by (ใช้ MIN ของ create_date แทน savedate)
        sb.append(" ORDER BY MIN(d.using_date) DESC ");

        String sql = sb.toString();
        log.info("Generated SQL: " + sql);

        // Execute query
        return JdbcTemplate.query(sql, new RowMapper<MachineStockDetails>() {
            @Override
            public MachineStockDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                MachineStockDetails tr = new MachineStockDetails();
                tr.setKeyId(rs.getInt("key_id"));
                tr.setCreateDate(rs.getTimestamp("create_date"));
                tr.setCreateBy(rs.getString("create_by"));
                tr.setTimeTotal(rs.getInt("time_total"));
                tr.setTxnDate(rs.getDate("txn_date"));
                tr.setStatus(rs.getInt("status"));

                tr.setBillNo(rs.getString("bill_no"));
                tr.setItemId(rs.getString("item_id"));
                tr.setItemName(rs.getString("item_name"));
                tr.setUnit(rs.getString("unit"));
                tr.setCurrency(rs.getString("currency"));
                tr.setQty(rs.getInt("qty"));
                tr.setPrice(rs.getDouble("price"));
                tr.setTotal(rs.getDouble("total"));
                tr.setUsingStatus(rs.getString("using_status"));

                tr.setMchNo(rs.getString("mch_no"));
                tr.setMchName(rs.getString("mch_name"));
                tr.setMchBranchName(rs.getString("mch_branch_name"));
                tr.setMchModel(rs.getString("mch_model"));
                tr.setMchProductYear(rs.getInt("mch_product_year"));

                tr.setBorNo(rs.getString("bor_no"));
                tr.setBorName(rs.getString("bor_name"));

                return tr;
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}


//    @Override
//    public List<MachineHis> getMachineHis(MachineHisReq machineHisReq,String borNo) {
//        StringBuilder sb = new StringBuilder();
//        Integer keyId = machineHisReq.getKeyId();
//        Integer status = machineHisReq.getStatus();
//
//        String conOrder = "\n order by a.key_id desc";
//        String conBoNo = "";
//        String conkeyId = "";
//        String conStatus = "";
//        if (status != null) {
//            conStatus ="\n AND a.status='"+status+"'";
//        }else {
//            conStatus = "";
//        }
//        if (keyId != null) {
//            conkeyId = "\n AND a.key_id = '" + keyId + "' ";
//        } else {
//            conkeyId = "";
//        }
////        if(!borNo.isEmpty() || !borNo.equals(null)){
////            conBoNo = "\n b.borNo='"+borNo+"' ";
////        }else {
////            conBoNo = "";
////        }
//        try {
////            sb.append("select \n" +
////                    "a.key_id,a.mch_no,a.create_date,a.create_by,D.USER_LOGIN,a.time_total,a.txn_date,a.status,\n" +
////                    "b.mch_name,b.mch_branch_name,b.mch_model,b.mch_product_year,\n" +
////                    "c.key_id borNo,c.b_name borName\n" +
////                    "from tb_machine_his a inner join tb_machine b on a.mch_no =b.mch_no\n" +
////                    "inner join  tb_bors c on b.borNo=c.key_id inner join LOGIN d on a.create_by=d.key_id where 1=1  ");
//
//
//            sb.append(conBoNo);
//            sb.append(conkeyId);
//            sb.append(conStatus);
//            sb.append(conOrder);
//            String sql = sb.toString();
//            return  JdbcTemplate.query(sql, new RowMapper<MachineHis>() {
//                @Override
//                public MachineHis mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    MachineHis tr = new MachineHis();
//                    tr.setKeyId(rs.getInt("key_id"));
//                    tr.setMchNo(rs.getString("mch_no"));
//                    tr.setCreateDate(rs.getTimestamp("create_date"));
//                    tr.setCreateBy(rs.getString("USER_LOGIN"));
//                    tr.setTimeTotal(rs.getString("time_total"));
//                    tr.setTxnDate(rs.getDate("txn_date"));
//                    tr.setStatus(rs.getInt("status"));
//                    tr.setBorNo(rs.getString("borNo"));
//                    tr.setBorName(rs.getString("borName"));
//                    return tr;
//                }
//            });
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            log.error("Error in getMachineHis: ", e);
//        }
//        return null;
//    }
@Override
public List<MachineHis> getMachineHis(MachineHisReq machineHisReq, String borNo) {
    StringBuilder sb = new StringBuilder();

    Integer keyId = machineHisReq.getKeyId();
//    Integer status = machineHisReq.getStatus();
    String mchNo = machineHisReq.getMchNo();

    String conOrder = "\n ORDER BY a.key_id DESC";
    String conBorNo = "";
    String conKeyId = "";
//    String conStatus = "";
    String conMchNo = "";

    // ✅ เงื่อนไข status
//    if (status != null) {
//        conStatus = "\n AND a.status = " + status;
//    }
//    if (status != null && status != 0) { // สมมติ 0 หมายถึง "all"
//        conStatus = "\n AND a.status = " + status;
//    }


    // ✅ เงื่อนไข keyId
    if (keyId != null) {
        conKeyId = "\n AND a.key_id = " + keyId;
    }

    // ✅ เงื่อนไข borNo (จาก parameter borNo)
    if (borNo != null && !borNo.trim().isEmpty()) {
        conBorNo = "\n AND b.borNo = '" + borNo + "'";
    }

    // ✅ เงื่อนไข mchNo (จาก body request)
    if (mchNo != null && !mchNo.trim().isEmpty()) {
        conMchNo = "\n AND a.mch_no = '" + mchNo + "'";
    }

    // ✅ สร้าง SQL หลัก
    sb.append("SELECT \n")
            .append("a.key_id, a.mch_no,a.status, a.status2, a.create_date, a.create_by, d.USER_LOGIN AS USER_LOGIN, a.time_total, a.txn_date, a.status,\n")
            .append("b.mch_name, b.mch_branch_name, b.mch_model, b.mch_product_year,\n")
            .append("c.key_id borNo, c.b_name borName\n")
            .append("FROM tb_machine_his a\n")
            .append("INNER JOIN tb_machine b ON a.mch_no = b.mch_no\n")
            .append("INNER JOIN tb_bors c ON b.borNo = c.key_id\n")
            .append("INNER JOIN LOGIN d ON a.create_by = d.key_id\n")
            .append("WHERE 1=1");

    // ✅ append เงื่อนไขตามค่าที่มี
    sb.append(conBorNo);
    sb.append(conMchNo);
    sb.append(conKeyId);
//    sb.append(conStatus);
    sb.append(conOrder);

    // Log SQL
    String sql = sb.toString();
    log.info("SQL for getMachineHis:\n" + sql);

    try {
        return JdbcTemplate.query(sql, new RowMapper<MachineHis>() {
            @Override
            public MachineHis mapRow(ResultSet rs, int rowNum) throws SQLException {
                MachineHis tr = new MachineHis();
                tr.setKeyId(rs.getInt("key_id"));
                tr.setMchNo(rs.getString("mch_no"));
                tr.setMch_name(rs.getString("mch_name"));
                tr.setCreateDate(rs.getTimestamp("create_date"));
                tr.setCreateBy(rs.getString("USER_LOGIN"));
                tr.setTimeTotal(rs.getString("time_total"));
                tr.setTxnDate(rs.getDate("txn_date"));
                tr.setStatus(rs.getInt("status"));
                tr.setBorNo(rs.getString("borNo"));
                tr.setBorName(rs.getString("borName"));
                tr.setStatus2(rs.getInt("status2"));
                return tr;
            }
        });
    } catch (Exception e) {
        log.error("Error in getMachineHis: ", e);
    }

    return null;
}



    @Override
    public int saveMachinedaily(MachineHisReq machineHisReq,String userId) {
        MerchineHisEntity entity = new MerchineHisEntity();
        entity.setMchNo(machineHisReq.getMchNo());
        entity.setCreateDate(new Date());
        entity.setCreateBy(userId);
        log.info("👉 Saving MachineHis with createBy = " + userId);
        entity.setTime_total(machineHisReq.getTimeClose());
        entity.setTxnDate(machineHisReq.getTxnDate());
        entity.setStatus(1); // Assuming '1' means active or a specific status
        entity.setStatus2(1);

        try {
            MERCHIN_HIS_REPOSITORY.save(entity);
            return 1; // Return 0 for success
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 for failure
        }
    }

    @Override
    public int updateMachinedaily(MachineHisReq machineHisReq) {
        // Find the existing entity by its machine number or ID (adjust as necessary)
        Optional<MerchineHisEntity> optionalEntity = MERCHIN_HIS_REPOSITORY.findByKeyId(machineHisReq.getKeyId());

        if (optionalEntity.isPresent()) {
            MerchineHisEntity entity = optionalEntity.get();
            entity.setMchNo(machineHisReq.getMchNo());
            entity.setTime_total(machineHisReq.getTimeClose());
            entity.setTxnDate(machineHisReq.getTxnDate());
            entity.setStatus(machineHisReq.getStatus()); // Assuming status is part of MachineHisReq
            entity.setStatus2(machineHisReq.getStatus2());
            try {
                MERCHIN_HIS_REPOSITORY.save(entity); // Save the updated entity
                return 1; // Return 0 for success
            } catch (Exception e) {
                e.printStackTrace();
                return -1; // Return -1 for failure
            }
        } else {
            return 2; // Return 1 if the entity does not exist
        }
    }



    @Override
    public List<Machine> getMachineByMerchantNo(MachineReq machineRPReq) {
        StringBuilder  sb = new StringBuilder();
        String merNo = machineRPReq.getMchNo();
        try {
            String conMchNo =  "\n AND a.mch_no ='"+merNo+"'  ";
            sb.append("SELECT\n" +
                    "    a.key_id,\n" +
                    "    a.mch_no,\n" +
                    "    a.mch_name,\n" +
                    "    a.mch_branch_name,\n" +
                    "    a.mch_model,\n" +
                    "    a.mch_product_year,\n" +
                    "    a.create_date,\n" +
                    "    a.create_by,\n" +
                    "    c.USER_LOGIN,\n" +
                    "    c.ROLE,\n" +
                    "    a.status,\n" +
                    "    a.borNo,\n" +
                    "    b.b_name AS borname,\n" +
                    "    b.location borlocationnaem,\n" +
                    "    a.time_fix,\n" +
                    "    a.time_fix_monitor,\n" +
                    "    a.time_oil_fix,\n" +
                    "    a.time_oil_fix_mo,\n" +
                    "    \n" +
                    "    -- Use COALESCE to return 0 if the sum is null\n" +
                    "    (a.time_fix - \n" +
                    "     COALESCE((SELECT SUM(ss.time_total) \n" +
                    "               FROM tb_machine_his ss \n" +
                    "               WHERE ss.mch_no = a.mch_no), 0)) AS timeTotal_Monitor,\n" +
                    "\n" +
                    "    (a.time_oil_fix - \n" +
                    "     COALESCE((SELECT SUM(ss.time_total) \n" +
                    "               FROM tb_machine_his ss \n" +
                    "               WHERE ss.mch_no = a.mch_no), 0)) AS timeTotal_Oil_Monitor\n" +
                    "\n" +
                    "FROM \n" +
                    "    tb_bors b \n" +
                    "INNER JOIN \n" +
                    "    tb_machine a ON b.key_id = a.borNo\n" +
                    "LEFT JOIN \n" +
                    "    LOGIN c ON a.create_by = c.KEY_ID  \n" +
                    "WHERE \n" +
                    "    1 = 1 ");

            sb.append(conMchNo);
            String sql = sb.toString();
            log.info("sql:"+sql);
            return JdbcTemplate.query(sql, new RowMapper<Machine>() {
                @Override
                public Machine mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Machine tr = new Machine();
                    tr.setKeyId(rs.getInt("key_id"));
                    tr.setMchNo(rs.getString("mch_no"));
                    tr.setMchName(rs.getString("mch_name"));
                    tr.setMchBranchName(rs.getString("mch_branch_name"));
                    tr.setMchModel(rs.getString("mch_model"));
                    tr.setMchProductYear(rs.getString("mch_product_year"));
                    tr.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                    tr.setCreateBy(rs.getString("create_by"));
                    tr.setUserLogin(rs.getString("USER_LOGIN"));
                    tr.setRole(rs.getString("ROLE"));
                    tr.setStatus(rs.getString("status"));
                    tr.setBorNo(rs.getString("borNo"));
                    tr.setBorName(rs.getString("borname"));
                    tr.setBorLocationName(rs.getString("borlocationnaem"));


                    tr.setTime_fix(rs.getInt("time_fix"));
                    tr.setTime_fix_monitor(rs.getInt("time_fix_monitor"));

                    tr.setTime_oil_fix(rs.getInt("time_oil_fix"));
                    tr.setTime_oil_fix_mo(rs.getInt("time_oil_fix_mo"));

                    tr.setTotalFixMo(rs.getInt("timeTotal_Monitor"));
                    tr.setTotalFixMoOil(rs.getInt("timeTotal_Oil_Monitor"));

                    return tr;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public List<Machine> getMachine(MachineRPReq machineRPReq,String role, String borNo) {
//        StringBuilder  sb = new StringBuilder();
//        String merNo = machineRPReq.getMerNo();
//        try {
//            String conMchNo = null;
//            String conAdmin = null;
//            String conOrder = "\n  order by a.key_id desc";
//
//            if (merNo == null || merNo.isEmpty()) {
//                conMchNo = "";
//            } else {
//                conMchNo = "\n AND a.mch_no ='"+merNo+"'  ";
//            }
//            //******ກວດສິດການ query  serch by borNo
////            if(!role.equals("PADMIN")){
////                conAdmin = "\n AND a.borNo='"+borNo+"' ";
////            }else {
////                conAdmin= "";
////            }
//            //******ກວດສິດການ query  serch by borNo
//            String reqBorNo = machineRPReq.getBorNo();
//
//            if (reqBorNo != null && !reqBorNo.isEmpty()) {
//                // ถ้ามีค่า borNo จาก client ให้ใช้ค่านั้นกรองเสมอ
//                conAdmin = "\n AND a.borNo='" + reqBorNo + "' ";
//            } else if (!role.equals("PADMIN")) {
//                // ถ้าไม่มีค่า borNo จาก client และไม่ใช่ admin ให้กรองตาม borNo ของ user
//                conAdmin = "\n AND a.borNo='" + borNo + "' ";
//            } else {
//                // ถ้าเป็น admin และไม่มีค่า borNo จาก client ให้ดึงทั้งหมด
//                conAdmin = "";
//            }
//
//            sb.append("SELECT a.drillrod_pq3,a.drillrod_hq3,a.core_barrelhq3_1_5m,a.backReamer,a.caphq,a.drillbit_hq3,a.water_pump,a.pipewrench24,a.pipewrench36,a.pipewrench48,a.monkey_wrench_hq3,a.rodpuller,a.adapter3in1_hq,a.lifting_plug_hq,a.circuit_breaker,a.led_light,a.fuel,\n" +
//                    "    a.key_id,\n" +
//                    "    a.mch_no,\n" +
//                    "    a.mch_name,\n" +
//                    "    a.mch_branch_name,\n" +
//                    "    a.mch_model,\n" +
//                    "    a.mch_product_year,\n" +
//                    "    a.create_date,\n" +
//                    "    a.create_by,\n" +
//                    "    c.USER_LOGIN,\n" +
//                    "    c.ROLE,\n" +
//                    "    a.status,\n" +
//                    "    a.borNo,\n" +
//                    "    b.b_name AS borname,\n" +
//                    "    b.location borlocationnaem,\n" +
//                    "    a.time_fix,\n" +
//                    "    a.time_fix_monitor,\n" +
//                    "    a.time_oil_fix,\n" +
//                    "    a.time_oil_fix_mo,\n" +
//                    "    \n" +
//                    "    -- Use COALESCE to return 0 if the sum is null\n" +
//                    "    (a.time_fix - \n" +
//                    "     COALESCE((SELECT SUM(ss.time_total) \n" +
//                    "               FROM tb_machine_his ss \n" +
//                    "               WHERE ss.status=1 and ss.mch_no = a.mch_no), 0)) AS timeTotal_Monitor,\n" +
//                    "\n" +
//                    "    (a.time_oil_fix - \n" +
//                    "     COALESCE((SELECT SUM(ss.time_total) \n" +
//                    "               FROM tb_machine_his ss \n" +
//                    "               WHERE ss.status=1 and ss.mch_no = a.mch_no), 0)) AS timeTotal_Oil_Monitor\n" +
//                    "\n" +
//                    "FROM \n" +
//                    "    tb_bors b \n" +
//                    "INNER JOIN \n" +
//                    "    tb_machine a ON b.key_id = a.borNo\n" +
//                    "LEFT JOIN \n" +
//                    "    LOGIN c ON a.create_by = c.KEY_ID  \n" +
//                    "WHERE \n" +
//                    "    1 = 1 ");
//
//            sb.append(conMchNo);
//            sb.append(conAdmin);
//            sb.append(conOrder);
//
//            String sql = sb.toString();
//            log.info("sql:"+sql);
//            return JdbcTemplate.query(sql, new RowMapper<Machine>() {
//                @Override
//                public Machine mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    Machine tr = new Machine();
//                    tr.setKeyId(rs.getInt("key_id"));
//                    tr.setMchNo(rs.getString("mch_no"));
//                    tr.setMchName(rs.getString("mch_name"));
//                    tr.setMchBranchName(rs.getString("mch_branch_name"));
//                    tr.setMchModel(rs.getString("mch_model"));
//                    tr.setMchProductYear(rs.getString("mch_product_year"));
//                    tr.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
//                    tr.setCreateBy(rs.getString("create_by"));
//                    tr.setUserLogin(rs.getString("USER_LOGIN"));
//                    tr.setRole(rs.getString("ROLE"));
//                    tr.setStatus(rs.getString("status"));
//                    tr.setBorNo(rs.getString("borNo"));
//                    tr.setBorName(rs.getString("borname"));
//                    tr.setBorLocationName(rs.getString("borlocationnaem"));
//
//
//                    tr.setTime_fix(rs.getInt("time_fix"));
//                    tr.setTime_fix_monitor(rs.getInt("time_fix_monitor"));
//
//                    tr.setTime_oil_fix(rs.getInt("time_oil_fix"));
//                    tr.setTime_oil_fix_mo(rs.getInt("time_oil_fix_mo"));
//
//                    tr.setTotalFixMo(rs.getInt("timeTotal_Monitor"));
//                    tr.setTotalFixMoOil(rs.getInt("timeTotal_Oil_Monitor"));
//
//
//                    tr.setDrillrod_pq3(rs.getString("drillrod_hq3"));
//                    tr.setCore_barrelhq3_1_5m(rs.getString("core_barrelhq3_1_5m"));
//                    tr.setBackReamer(rs.getString("backReamer"));
//                    tr.setCaphq(rs.getString("caphq"));
//                    tr.setDrillbit_hq3(rs.getString("drillbit_hq3"));
//                    tr.setWater_pump(rs.getString("water_pump"));
//                    tr.setPipewrench24(rs.getString("pipewrench24"));
//                    tr.setPipewrench36(rs.getString("pipewrench36"));
//                    tr.setPipewrench48(rs.getString("pipewrench48"));
//                    tr.setMonkey_wrench_hq3(rs.getString("monkey_wrench_hq3"));
//                    tr.setRodpuller(rs.getString("rodpuller"));
//                    tr.setAdapter3in1_hq(rs.getString("adapter3in1_hq"));
//                    tr.setLifting_plug_hq(rs.getString("lifting_plug_hq"));
//                    tr.setCircuit_breaker(rs.getString("circuit_breaker"));
//                    tr.setLed_light(rs.getString("led_light"));
//                    tr.setFuel(rs.getString("fuel"));
//                    return tr;
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

@Override
public List<Machine> getMachine(MachineRPReq machineRPReq, String role, String borNo) {
    StringBuilder sb = new StringBuilder();
    String merNo = machineRPReq.getMerNo();

    try {
        String conMchNo;
        String conAdmin;
        String conOrder = "\nORDER BY a.key_id DESC";

        // ✅ เงื่อนไขตาม merNo
        if (merNo == null || merNo.isEmpty()) {
            conMchNo = "";
        } else {
            conMchNo = "\nAND a.mch_no = '" + merNo + "' ";
        }

        // ✅ เงื่อนไขตาม borNo หรือ role
        String reqBorNo = machineRPReq.getBorNo();
        if (reqBorNo != null && !reqBorNo.isEmpty()) {
            conAdmin = "\nAND a.borNo='" + reqBorNo + "' ";
        } else if (!"PADMIN".equals(role)) {
            conAdmin = "\nAND a.borNo='" + borNo + "' ";
        } else {
            conAdmin = "";
        }

        // ✅ SQL หลัก
        sb.append("SELECT a.key_id,\n")
                .append("    a.mch_no,\n")
                .append("    a.mch_name, a.price,a.currency,\n")
                .append("    a.mch_branch_name,\n")
                .append("    a.mch_model,\n")
                .append("    a.mch_product_year,\n")
                .append("    a.create_date,\n")
                .append("    a.create_by,\n")
                .append("    c.USER_LOGIN,\n")
                .append("    c.ROLE,\n")
                .append("    a.status,\n")
                .append("    a.borNo,\n")
                .append("    b.b_name AS borname,\n")
                .append("    b.location AS borlocationnaem,\n")
                .append("    a.time_fix,\n")
                .append("    a.time_fix_monitor,\n")
                .append("    a.time_oil_fix,\n")
                .append("    a.time_oil_fix_mo,\n")
                .append("    a.image,\n")
                .append("(a.time_fix - COALESCE((SELECT SUM(ss.time_total) FROM tb_machine_his ss WHERE ss.status=1 AND ss.mch_no = a.mch_no), 0)) AS timeTotal_Monitor,\n")
                .append("(a.time_oil_fix - COALESCE((SELECT SUM(ss.time_total) FROM tb_machine_his ss WHERE ss.status2=1 AND ss.mch_no = a.mch_no), 0)) AS timeTotal_Oil_Monitor\n")

                .append("FROM tb_bors b\n")
                .append("INNER JOIN tb_machine a ON b.key_id = a.borNo\n")
                .append("LEFT JOIN LOGIN c ON a.create_by = c.KEY_ID\n")
                .append("WHERE 1 = 1 ");

        sb.append(conMchNo);
        sb.append(conAdmin);
        sb.append(conOrder);

        String sql = sb.toString();
        log.info("SQL Query => {}", sql);

        // ✅ ดึงข้อมูลจาก tb_machine
        List<Machine> machines = JdbcTemplate.query(sql, new RowMapper<Machine>() {
            @Override
            public Machine mapRow(ResultSet rs, int rowNum) throws SQLException {
                Machine tr = new Machine();
                tr.setKeyId(rs.getInt("key_id"));
                tr.setMchNo(rs.getString("mch_no"));
                tr.setMchName(rs.getString("mch_name"));
                tr.setPrice(rs.getString("price"));
                tr.setCurrency(rs.getString("currency"));
                tr.setMchBranchName(rs.getString("mch_branch_name"));
                tr.setMchModel(rs.getString("mch_model"));
                tr.setMchProductYear(rs.getString("mch_product_year"));
                tr.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                tr.setCreateBy(rs.getString("create_by"));
                tr.setUserLogin(rs.getString("USER_LOGIN"));
                tr.setRole(rs.getString("ROLE"));
                tr.setStatus(rs.getString("status"));
                tr.setBorNo(rs.getString("borNo"));
                tr.setBorName(rs.getString("borname"));
                tr.setBorLocationName(rs.getString("borlocationnaem"));
                tr.setTime_fix(rs.getInt("time_fix"));
                tr.setTime_fix_monitor(rs.getInt("time_fix_monitor"));
                tr.setTime_oil_fix(rs.getInt("time_oil_fix"));
                tr.setTime_oil_fix_mo(rs.getInt("time_oil_fix_mo"));
                tr.setTotalFixMo(rs.getInt("timeTotal_Monitor"));
                tr.setTotalFixMoOil(rs.getInt("timeTotal_Oil_Monitor"));
                tr.setImage(rs.getString("image"));
                return tr;
            }
        });


        // ✅ ดึง tools ทั้งหมด
        String sqlTools = "SELECT id, mch_no, tool_name, qty FROM tb_machine_tool";
        List<Map<String, Object>> tools = JdbcTemplate.queryForList(sqlTools);

        // ✅ จับคู่ tools เข้ากับ machine แต่ละตัว
        for (Machine machine : machines) {
            String mchNo = machine.getMchNo();

            List<Machine.Tool> machineTools = tools.stream()
                    .filter(t -> mchNo != null && mchNo.equals(String.valueOf(t.get("mch_no")).trim()))
                    .map(t -> new Machine.Tool(
                            String.valueOf(t.get("tool_name")),
                            Integer.parseInt(String.valueOf(t.get("qty"))),
                            Integer.parseInt(String.valueOf(t.get("id"))),
                            String.valueOf(t.get("mch_no")),
                            String.valueOf(t.get("unit"))
                    ))
                    .collect(Collectors.toList());

            machine.setTools(machineTools != null ? machineTools : new ArrayList<>());
            log.info("Machine {} has {} tools", mchNo, machineTools.size());
        }

        return machines;

    } catch (Exception e) {
        log.error("❌ Error in getMachine(): {}", e.getMessage(), e);
        return Collections.emptyList();
    }
}



    @Override
    public List<MachineDetails> getReportMachineDetails(MachineRPReq machineRPReq,String role ,String borNo) {
        StringBuilder  sb = new StringBuilder();
        String mch_no = machineRPReq.getMerNo();
        String startDate = machineRPReq.getStartDate();
        String endDate = machineRPReq.getEndDate();
        String status = machineRPReq.getStatus();
        try {
            String conMchNo = null;
            String conBorNo = null;
            String conStartDate = null;
            String conStatus = null;
            if(mch_no == null || mch_no.isEmpty()){
                conMchNo = "";
            }else {
                conMchNo = "\n AND mch_no ='"+mch_no+"'  ";
            }
            if(borNo.isEmpty() || borNo == null){
                conBorNo = "";
            }else {
                conBorNo  ="\n AND borNo='"+borNo+"' ";
            }
            if(startDate == null || startDate.isEmpty()){
                conStartDate = "";
            }else {
                conStartDate = "\nAND DATE_FORMAT(request_Date, '%Y-%m-%d') >= '"+startDate+"'  AND DATE_FORMAT(request_Date, '%Y-%m-%d')  <= '"+endDate+"'";
            }
            if(status.isEmpty() || status == null){
                conStatus = "";
            }else {
                conStatus = "\n AND status='"+status+"'";
            }
            sb.append("SELECT * FROM rp_machine_detail where 1=1 ");
            sb.append(conMchNo);
            sb.append(conBorNo);
            sb.append(conStartDate);
            sb.append(conStatus);
            String sql = sb.toString();


            return JdbcTemplate.query(sql, new RowMapper<MachineDetails>() {
                @Override
                public MachineDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MachineDetails tr = new MachineDetails();
                    tr.setDetail_id(rs.getInt("detail_id"));
                    tr.setMch_id(rs.getInt("mch_id"));
                    tr.setMch_no(rs.getString("mch_no"));
                    tr.setMch_name(rs.getString("mch_name"));
                    tr.setMch_branch_name(rs.getString("mch_branch_name"));
                    tr.setMch_model(rs.getString("mch_model"));
                    tr.setMch_product_year(rs.getString("mch_product_year"));
                    tr.setROLE(rs.getString("ROLE"));
                    tr.setBorNo(rs.getString("borNo"));
                    tr.setBorNo(rs.getString("borname"));
                    tr.setBoreLocateName(rs.getString("borlocationnaem"));
                    tr.setItem_id(rs.getInt("item_id"));
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setUnit(rs.getString("unit"));
                    tr.setCurrency(rs.getString("currency"));
                    tr.setPrice(rs.getDouble("price"));
                    tr.setQty(rs.getInt("qty"));
                    tr.setRequest_by(rs.getString("request_by"));
                    tr.setRequest_Date(rs.getTimestamp("request_Date").toLocalDateTime());
                    tr.setStatus(rs.getString("status"));

                    tr.setDrillrod_pq3(rs.getString("drillrod_hq3"));
                    tr.setCore_barrelhq3_1_5m(rs.getString("core_barrelhq3_1_5m"));
                    tr.setBackReamer(rs.getString("backReamer"));
                    tr.setCaphq(rs.getString("caphq"));
                    tr.setDrillbit_hq3(rs.getString("drillbit_hq3"));
                    tr.setWater_pump(rs.getString("water_pump"));
                    tr.setPipewrench24(rs.getString("pipewrench24"));
                    tr.setPipewrench36(rs.getString("pipewrench36"));
                    tr.setPipewrench48(rs.getString("pipewrench48"));
                    tr.setMonkey_wrench_hq3(rs.getString("monkey_wrench_hq3"));
                    tr.setRodpuller(rs.getString("rodpuller"));
                    tr.setAdapter3in1_hq(rs.getString("adapter3in1_hq"));
                    tr.setLifting_plug_hq(rs.getString("lifting_plug_hq"));
                    tr.setCircuit_breaker(rs.getString("circuit_breaker"));
                    tr.setLed_light(rs.getString("led_light"));
                    tr.setFuel(rs.getString("fuel"));

                    return tr;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<MachineReport> getReportMachine(MachineRPReq machineRPReq,String role,String borNo) {
        StringBuilder  sb = new StringBuilder();
        String mchNo = machineRPReq.getMerNo();
        String startDate = machineRPReq.getStartDate();
        String endDate = machineRPReq.getEndDate();
        String status = machineRPReq.getStatus();
        try {
            String conMchNo = null;
            String conBorNo = null;
            String conStartDate = null;
            String conStatus = null;
            if (mchNo == null || mchNo.isEmpty()) {
                conMchNo = "";
            }else {
                conMchNo = "\n AND mch_no ='"+mchNo+"'  ";
            }
            if (borNo == null || borNo.isEmpty()) {
                conBorNo = "";
            }else {
                conBorNo  ="\n AND borNo='"+borNo+"' ";
            }
            if (startDate == null || startDate.isEmpty()) {
                conStartDate = "";
            }else {
                conStartDate = "\n AND DATE_FORMAT(request_Date, '%Y-%m-%d') >= '"+startDate+"'  AND DATE_FORMAT(request_Date, '%Y-%m-%d')  <= '"+endDate+"'";
            }

            if (status == null || status.isEmpty()) {
                conStatus = "";
            }else {
                conStatus = "\n AND status='"+status+"'";
            }
            sb.append("SELECT \n" +
                    "mch_id, mch_no, mch_name, mch_branch_name, mch_model, mch_product_year,\n" +
                    "DATE_FORMAT(request_Date, '%Y-%m-%d') AS request_Date,\n" +
                    "borNo, borname, item_id, item_name, unit, currency,\n" +
                    "SUM(qty) AS qty,\n" +
                    "price,\n" +
                    "SUM(qty) * price AS total\n" +
                    "FROM rp_machine_detail  where 1=1 ");
            sb.append(conMchNo);
            sb.append(conBorNo);
            sb.append(conStartDate);
            sb.append(conStatus);
            String sql = sb.toString();
            log.info("reposrt :"+ sql);
            return JdbcTemplate.query(sql, new RowMapper<MachineReport>() {
                @Override
                public MachineReport mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MachineReport tr = new MachineReport();
                    tr.setMchId(rs.getInt("mch_id"));
                    tr.setMchNo(rs.getString("mch_no"));
                    tr.setMchName(rs.getString("mch_name"));
                    tr.setMchBranchName(rs.getString("mch_branch_name"));
                    tr.setMchModel(rs.getString("mch_model"));
                    tr.setMchProductYear(rs.getString("mch_product_year"));
                    tr.setRequestDate(rs.getString("request_Date")); // already formatted as 'YYYY-MM-DD'
                    tr.setBorNo(rs.getString("borNo"));
                    tr.setBorName(rs.getString("borname"));
                    tr.setItemId(rs.getInt("item_id"));
                    tr.setItemName(rs.getString("item_name"));
                    tr.setUnit(rs.getString("unit"));
                    tr.setCurrency(rs.getString("currency"));
                    tr.setQty(rs.getInt("qty"));
                    tr.setPrice(rs.getBigDecimal("price"));
                    tr.setTotal(rs.getBigDecimal("total"));
                    return tr;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveMachine(MachineReq machineReq) {
        try {
            String imageUrl = machineReq.getImage(); // ใช้ URL แทน byte[]

            String sql = "INSERT INTO tb_machine (" +
                    "mch_no, mch_name, mch_branch_name, mch_model, mch_product_year, " +
                    "create_date, create_by, status, borNo, time_fix, time_fix_monitor, time_oil_fix, time_oil_fix_mo, image" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            return JdbcTemplate.update(sql,
                    machineReq.getMchNo(),
                    machineReq.getMchName(),
                    machineReq.getMchBranchName(),
                    machineReq.getMchModel(),
                    machineReq.getMchProductYear(),
                    new Date(),
                    machineReq.getCreateBy(),
                    machineReq.getStatus(),
                    machineReq.getBorNo(),
                    machineReq.getTime_fix(),
                    machineReq.getTime_fix_monitor(),
                    machineReq.getTime_oil_fix(),
                    machineReq.getTime_oil_fix_mo(),
                    imageUrl // <-- URL image2
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

@Override
public int updateMachine(MachineReq machineReq) {
    try {
        // สร้าง SQL พื้นฐาน
        StringBuilder sql = new StringBuilder("UPDATE tb_machine SET " +
                "mch_no = ?, " +
                "mch_name = ?, " +
                "mch_branch_name = ?, " +
                "mch_model = ?, " +
                "mch_product_year = ?, " +
                "update_date = ?, " +
                "update_by = ?, " +
                "status = ?, " +
                "borNo = ?, " +
                "time_fix = ?, " +
                "time_fix_monitor = ?, " +
                "time_oil_fix = ?, " +
                "image = ?, " +
                "time_oil_fix_mo = ? " +
                "WHERE key_id = ?"
        );

        List<Object> params = new ArrayList<>();
        params.add(machineReq.getMchNo());
        params.add(machineReq.getMchName());
        params.add(machineReq.getMchBranchName());
        params.add(machineReq.getMchModel());
        params.add(machineReq.getMchProductYear());
        params.add(new Date()); // update_date
        params.add(machineReq.getCreateBy());
        params.add(machineReq.getStatus());
        params.add(machineReq.getBorNo());
        params.add(machineReq.getTime_fix());
        params.add(machineReq.getTime_fix_monitor());
        params.add(machineReq.getTime_oil_fix());
        params.add(machineReq.getImage());   // image
        params.add(machineReq.getTime_oil_fix_mo());
        params.add(machineReq.getKeyId());    // key_id

        return JdbcTemplate.update(sql.toString(), params.toArray());

    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}










}
