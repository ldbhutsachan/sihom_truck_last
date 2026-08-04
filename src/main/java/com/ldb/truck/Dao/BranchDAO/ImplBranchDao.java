package com.ldb.truck.Dao.BranchDAO;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Branch.Branch;
import com.ldb.truck.Model.Login.Task.LinkReq;
import com.ldb.truck.Model.Login.Task.TaskReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImplBranchDao implements BranchDao {
    private static final Logger log = LogManager.getLogger(ImplBranchDao.class);

    // ==================connnection==================
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    // ================================================
    String query = "";

    @Override
    public List<Branch> getBranch(BrachReq brachReq) {
        log.info("show get info:======999999:" + brachReq.getUserId());
        log.info("show get info:======branch:" + brachReq.getBranchNo());
        try {
            query = "select a.KEY_ID ,a.B_NAME ,a.B_TEL ,a.B_LOCATION ,a.EMAIL ,a.userId ,b.USER_LOGIN ,a.createDate, a.B_STATUS, "
                    +
                    "(select count(*) from DATA_HOLE h where h.branch_id = a.KEY_ID) as HOLE_COUNT, " +
                    "(select max(create_date) from DATA_HOLE h where h.branch_id = a.KEY_ID) as LAST_UPDATE\n" +
                    "from LOGIN b inner join TB_BRANCH a on a.userId  =b.KEY_ID where a.key_id ='"
                    + brachReq.getBranchNo() + "' AND a.userId='" + brachReq.getUserId() + "'";

            log.info("show SQL:" + query);
            return EBankJdbcTemplate.query(query, new RowMapper<Branch>() {
                @Override
                public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Branch tr = new Branch();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setB_name(rs.getString("B_NAME"));
                    tr.setB_tel(rs.getString("B_TEL"));
                    tr.setLocation(rs.getString("B_LOCATION"));
                    tr.setEmail(rs.getString("EMAIL"));
                    tr.setUserName(rs.getString("userId"));
                    tr.setCreateDate(rs.getString("createDate"));
                    tr.setHoleCount(rs.getInt("HOLE_COUNT"));
                    tr.setLastUpdate(rs.getString("LAST_UPDATE"));
                    tr.setStatus(rs.getString("B_STATUS"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // new show branch
    @Override
    public List<Branch> getBranchNew(BrachReq brachReq) {
        log.info("show get info:======999999:" + brachReq.getUserId());
        log.info("show get info:======branch:" + brachReq.getBranchNo());
        try {
            try {
                // Alter table to add create_date if it does not exist
                EBankJdbcTemplate
                        .execute("ALTER TABLE DATA_HOLE ADD COLUMN create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP");
                log.info("Successfully executed DDL to add create_date to DATA_HOLE if missing");
            } catch (Exception e) {
                // Expected if column already exists
            }

            try {
                // Alter table to add B_STATUS if it does not exist
                EBankJdbcTemplate.execute("ALTER TABLE TB_BRANCH ADD COLUMN B_STATUS VARCHAR(255) DEFAULT 'ດຳເນີນງານ'");
                log.info("Successfully executed DDL to add B_STATUS to TB_BRANCH if missing");
            } catch (Exception e) {
                // Expected if column already exists
            }

            try {
                // Check if test boreholes are already inserted to avoid duplication
                Integer testCount = EBankJdbcTemplate.queryForObject(
                        "select count(*) from DATA_HOLE where hole_number like 'BH-TEST-%'", Integer.class);
                if (testCount == null || testCount == 0) {
                    // Get current date string and format test timestamps relative to current date
                    // (e.g. 10 minutes ago, 2 hours ago, 4 hours ago)
                    // Using exact timestamps matching user OS local time or near current time
                    EBankJdbcTemplate.execute(
                            "insert into DATA_HOLE (pic, hole_number, data_Coller, userId, full_Name_Hole_number, branch_id, create_date) "
                                    +
                                    "values ('pic_test1.png', 'BH-TEST-001', 'TESTER', '166', 'Borehole Test XiengKhouang', '3', DATE_SUB(NOW(), INTERVAL 15 MINUTE))");
                    EBankJdbcTemplate.execute(
                            "insert into DATA_HOLE (pic, hole_number, data_Coller, userId, full_Name_Hole_number, branch_id, create_date) "
                                    +
                                    "values ('pic_test2.png', 'BH-TEST-002', 'TESTER', '166', 'Borehole Test Savannakhet', '4', DATE_SUB(NOW(), INTERVAL 2 HOUR))");
                    EBankJdbcTemplate.execute(
                            "insert into DATA_HOLE (pic, hole_number, data_Coller, userId, full_Name_Hole_number, branch_id, create_date) "
                                    +
                                    "values ('pic_test3.png', 'BH-TEST-003', 'TESTER', '166', 'Borehole Test HO Office', '5', DATE_SUB(NOW(), INTERVAL 5 HOUR))");
                    log.info("Successfully inserted test borehole records");
                }
            } catch (Exception e) {
                log.error("Failed to insert test records", e);
            }

            query = "select *, " +
                    "(select count(*) from DATA_HOLE where branch_id = TB_BRANCH.KEY_ID) as HOLE_COUNT, " +
                    "(select max(create_date) from DATA_HOLE where branch_id = TB_BRANCH.KEY_ID) as LAST_UPDATE " +
                    "from TB_BRANCH where userId='" + brachReq.getUserId() + "' " +
                    "or ('" + brachReq.getUserId()
                    + "' in (select KEY_ID from LOGIN where ROLE in ('FOR_DOCUMENT_ADMIN', 'FOR_DOCUMENT')) " +
                    "and userId in ('166', '141'))";
            log.info("show SQL:" + query);
            return EBankJdbcTemplate.query(query, new RowMapper<Branch>() {
                @Override
                public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Branch tr = new Branch();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setB_name(rs.getString("B_NAME"));
                    tr.setB_tel(rs.getString("B_TEL"));
                    tr.setLocation(rs.getString("B_LOCATION"));
                    tr.setEmail(rs.getString("EMAIL"));
                    tr.setUserName(rs.getString("userId"));
                    tr.setCreateDate(rs.getString("createDate"));
                    tr.setHoleCount(rs.getInt("HOLE_COUNT"));
                    tr.setLastUpdate(rs.getString("LAST_UPDATE"));
                    tr.setStatus(rs.getString("B_STATUS"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveDataBranch(BrachReq brachReq) {
        try {
            query = "insert into TB_BRANCH (B_NAME,B_TEL,B_LOCATION,EMAIL,userId,createDate,B_STATUS) VALUES (?,?,?,?,?,now(),?)";
            List<String> paraList = new ArrayList<>();
            paraList.add(brachReq.getB_name());
            paraList.add(brachReq.getB_tel());
            paraList.add(brachReq.getLocation());
            paraList.add(brachReq.getEmail());
            paraList.add(brachReq.getUserId());
            paraList.add(brachReq.getStatus() != null ? brachReq.getStatus() : "ດຳເນີນງານ");
            return EBankJdbcTemplate.update(query, paraList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateDataBranch(BrachReq brachReq) {
        try {
            // 1. Get old B_NAME before updating
            String oldBName = null;
            try {
                oldBName = EBankJdbcTemplate.queryForObject(
                        "SELECT B_NAME FROM TB_BRANCH WHERE KEY_ID=?",
                        String.class,
                        brachReq.getKey_id());
            } catch (Exception e) {
                log.warn("Could not find old B_NAME for KEY_ID: " + brachReq.getKey_id());
            }

            // 2. Update TB_BRANCH
            query = "update TB_BRANCH set B_NAME=?,B_TEL=?,B_LOCATION=?,EMAIL=?,userId=?,B_STATUS=?,createDate=now() WHERE KEY_ID=?";
            List<Object> paraList = new ArrayList<>();
            paraList.add(brachReq.getB_name());
            paraList.add(brachReq.getB_tel());
            paraList.add(brachReq.getLocation());
            paraList.add(brachReq.getEmail());
            paraList.add(brachReq.getUserId());
            paraList.add(brachReq.getStatus());
            paraList.add(brachReq.getKey_id());

            int result = EBankJdbcTemplate.update(query, paraList.toArray());

            // 3. Update DOCUMENT_STORAGE bouang column if B_NAME changed
            if (result > 0 && oldBName != null && !oldBName.trim().isEmpty()
                    && brachReq.getB_name() != null && !oldBName.equals(brachReq.getB_name())) {
                try {
                    String updateDocSql = "UPDATE DOCUMENT_STORAGE SET bouang=? WHERE bouang=?";
                    int docUpdated = EBankJdbcTemplate.update(updateDocSql, brachReq.getB_name(), oldBName);
                    log.info("Updated " + docUpdated + " documents in DOCUMENT_STORAGE from bouang '" + oldBName
                            + "' to '" + brachReq.getB_name() + "'");
                } catch (Exception e) {
                    log.error("Error updating bouang in DOCUMENT_STORAGE", e);
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delDataBranch(BrachReq brachReq) {
        try {
            query = "delete  FROM  TB_BRANCH  WHERE KEY_ID=?";
            List<String> paraList = new ArrayList<>();
            paraList.add(brachReq.getKey_id());
            return EBankJdbcTemplate.update(query, paraList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // =================================================
    // Delete Task
    @Override
    public int delDataTasks(TaskReq taskReq) {
        try {
            query = "delete  FROM  TB_TASKS  WHERE KEY_ID=?";
            List<String> paraList = new ArrayList<>();
            paraList.add(taskReq.getKey_id());
            return EBankJdbcTemplate.update(query, paraList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delDatalink(LinkReq linkReq) {
        try {
            query = "delete  FROM  TASK_LINK  WHERE id=?";
            List<String> paraList = new ArrayList<>();
            paraList.add(linkReq.getId());
            return EBankJdbcTemplate.update(query, paraList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
