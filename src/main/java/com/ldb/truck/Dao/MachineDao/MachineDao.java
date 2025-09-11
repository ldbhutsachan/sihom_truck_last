package com.ldb.truck.Dao.MachineDao;

import com.ldb.truck.Model.Machine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
@Component
public class MachineDao implements MachineInterface {
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate JdbcTemplate;


    @Override
    public List<Machine> getMachine(MachineRPReq machineRPReq) {
        StringBuilder  sb = new StringBuilder();
        String mch_no = machineRPReq.getMch_no();
     //   String borNo = machineRPReq.getBorNo();
//        String startDate = machineRPReq.getStartDate();
//        String endDate = machineRPReq.getEndDate();
//        String status = machineRPReq.getStatus();
        try {
            String conMchNo = null;
            String conBorNo = null;
            String conStartDate = null;
            String conStatus = null;
//            if(mch_no != null || mch_no.isEmpty() || mch_no.equals("")){
//                conMchNo = "\n AND mch_no ='"+mch_no+"'  ";
//            }
//            if(borNo != null){
//                conBorNo  ="\n AND borNo='"+borNo+"' ";
//            }
//            if(startDate != null || startDate.isEmpty()){
//                conStartDate = "\n AND DATE_FORMAT(a.create_date, '%Y-%m-%d') >= '"+startDate+"'  AND DATE_FORMAT(a.create_date, '%Y-%m-%d')  <= '"+endDate+"'";
//            }
//            if(status != null){
//                conStatus = "\n AND a.status='"+status+"'";
//            }
            sb.append("select \n" +
                    "a.key_id,a.mch_no,a.mch_name,a.mch_branch_name,a.mch_model,a.mch_product_year,\n" +
                    "a.create_date,a.create_by,c.USER_LOGIN,c.`ROLE`,a.status\n" +
                    ",a.borNo,b.b_name borname,b.location borlocationnaem\n" +
                    "from tb_bors b inner join tb_machine a on b.key_id=a.borNo\n" +
                    "LEFT join LOGIN c on  a.create_by =c.KEY_ID  where 1=1  ");

         //   sb.append(conMchNo);
           // sb.append(conBorNo);
           // sb.append(conStartDate);
          //  sb.append(conStatus);
            String sql = sb.toString();
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
                    return tr;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MachineDetails> getReportMachineDetails(MachineRPReq machineRPReq) {
        StringBuilder  sb = new StringBuilder();
        String mch_no = machineRPReq.getMch_no();
        String borNo = machineRPReq.getBorNo();
        String startDate = machineRPReq.getStartDate();
        String endDate = machineRPReq.getEndDate();
        String status = machineRPReq.getStatus();
        try {
            String conMchNo = null;
            String conBorNo = null;
            String conStartDate = null;
            String conStatus = null;
            if(mch_no != null){
                conMchNo = "\n AND mch_no ='"+mch_no+"'  ";
            }
            if(borNo != null){
                conBorNo  ="\n AND borNo='"+borNo+"' ";
            }
            if(startDate != null){
                conStartDate = "\nAND DATE_FORMAT(request_Date, '%Y-%m-%d') >= '"+startDate+"'  AND DATE_FORMAT(request_Date, '%Y-%m-%d')  <= '"+endDate+"'";
            }
            if(status != null){
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
                    return tr;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MachineReport> getReportMachine(MachineRPReq machineRPReq) {
        StringBuilder  sb = new StringBuilder();
        String mch_no = machineRPReq.getMch_no();
        String borNo = machineRPReq.getBorNo();
        String startDate = machineRPReq.getStartDate();
        String endDate = machineRPReq.getEndDate();
        String status = machineRPReq.getStatus();
        try {
            String conMchNo = null;
            String conBorNo = null;
            String conStartDate = null;
            String conStatus = null;
            if(mch_no != null){
                conMchNo = "\n AND mch_no ='"+mch_no+"'  ";
            }
            if(borNo != null){
                conBorNo  ="\n AND borNo='"+borNo+"' ";
            }
            if(startDate != null){
                conStartDate = "\n AND DATE_FORMAT(request_Date, '%Y-%m-%d') >= '"+startDate+"'  AND DATE_FORMAT(request_Date, '%Y-%m-%d')  <= '"+endDate+"'";
            }
            if(status != null){
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
            String sql = "INSERT INTO tb_machine (" +
                    "mch_no, mch_name, mch_branch_name, mch_model, mch_product_year, " +
                    "create_date, create_by, status, borNo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            return JdbcTemplate.update(sql,
                   // machineReq.getKeyId(),
                    machineReq.getMchNo(),
                    machineReq.getMchName(),
                    machineReq.getMchBranchName(),
                    machineReq.getMchModel(),
                    machineReq.getMchProductYear(),
                    new Date(),
                    machineReq.getCreateBy(),
                    machineReq.getStatus(),
                    machineReq.getBorNo()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateMachine(MachineReq machineReq) {
        try {
            String sql = "UPDATE tb_machine SET " +
                    "mch_no = ?, " +
                    "mch_name = ?, " +
                    "mch_branch_name = ?, " +
                    "mch_model = ?, " +
                    "mch_product_year = ?, " +
                    "update_date = ?, " +
                    "update_by = ?, " +
                    "status = ?, " +
                    "borNo = ? " +
                    "WHERE key_id = ?";

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
                    machineReq.getKeyId()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int enableMachine(MachineReq machineReq) {
        return 0;
    }

    //MachineReq



}
