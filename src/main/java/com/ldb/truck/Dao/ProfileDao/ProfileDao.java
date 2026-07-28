package com.ldb.truck.Dao.ProfileDao;

import com.ldb.truck.Dao.Performance.PerformanceDao;
import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.Profile.ProfileReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Repository
public class ProfileDao {
    private static final Logger log = LogManager.getLogger(ProfileDao.class);
    // get bill No ----T-10001 like
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    public List<Profile> getProfileInfo(BrachReq profileReq) {
        log.info("get data info:" + profileReq.getToKen());
        try {
            String SQL = "select b.KEY_ID as userId ,b.USER_LOGIN as userName ,b.ROLE,b.BRANCH as branchNo ,a.B_NAME as banchName \n"
                    +
                    "from LOGIN b inner join TB_BRANCH a on a.KEY_ID  =b.BRANCH  where token='" + profileReq.getToKen()
                    + "'";
            log.info("SQL:" + SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<Profile>() {
                @Override
                public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Profile tr = new Profile();
                    tr.setUserId(rs.getString("userId"));
                    tr.setUserName(rs.getString("userName"));
                    tr.setRole(rs.getString("ROLE"));
                    tr.setBranchNo(rs.getString("branchNo"));
                    tr.setBranchName(rs.getString("banchName"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Profile> getProfileInfoByToken(String token) {
        try {
            // 🔧 FIX: ใช้ Parameterized Query แทนการเชื่อม String ตรงๆ (ป้องกัน SQL
            // Injection)
            String SQL = "SELECT b.STAFT_ID, b.KEY_ID AS userId, b.USER_LOGIN AS userName, " +
                    "b.ROLE, b.BRANCH AS branchNo, a.B_NAME AS branchName, b.bor_no, b.role2 " +
                    "FROM LOGIN b " +
                    "LEFT JOIN TB_BRANCH a ON a.KEY_ID = b.BRANCH " +
                    "WHERE b.token = ?";

            log.info("SQL: getProfileInfoByToken"); // 🔧 FIX: ไม่ log token หรือ SQL ที่มีข้อมูล sensitive

            return EBankJdbcTemplate.query(SQL, new Object[] { token }, new RowMapper<Profile>() {
                @Override
                public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Profile tr = new Profile();
                    tr.setUserId(rs.getString("userId"));
                    tr.setStaff_id(rs.getString("STAFT_ID"));
                    tr.setUserName(rs.getString("userName"));
                    tr.setRole(rs.getString("ROLE"));
                    tr.setBranchNo(rs.getString("branchNo"));
                    tr.setBranchName(rs.getString("branchName"));
                    tr.setBorNo(rs.getString("bor_no"));
                    tr.setRole2(rs.getString("role2"));
                    return tr;
                }
            });

        } catch (Exception e) {
            log.error("Error in getProfileInfoByToken: {}", e.getMessage(), e); // 🔧 FIX: ใช้ log.error
        }

        return new ArrayList<>(); // 🔧 FIX: return ArrayList() แทน null เพื่อให้สอดคล้องกับ getStaffInfoByToken
    }

    public List<Profile> getStaffInfoByToken(String token) {
        try {
            String SQL = "SELECT * FROM company_staffs WHERE token = ?";

            log.info("SQL: getStaffInfoByToken"); // 🔧 FIX: ไม่ log token

            return EBankJdbcTemplate.query(SQL, new Object[] { token }, new RowMapper<Profile>() {
                @Override
                public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Profile tr = new Profile();
                    tr.setUserId(rs.getString("id"));
                    tr.setStaff_id(rs.getString("staff_code"));
                    tr.setUserName(rs.getString("username"));
                    tr.setRole(rs.getString("role"));
                    return tr;
                }
            });

        } catch (Exception e) {
            log.error("Error in getStaffInfoByToken: {}", e.getMessage(), e); // 🔧 FIX: ใช้ log.error
        }

        return new ArrayList<>();
    }

    public Map<Long, String> getUserNameMapByIds(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty())
            return new HashMap<>();

        try {
            // สร้าง IN clause -> ?,?,?
            String placeholders = userIds.stream()
                    .map(id -> "?")
                    .collect(Collectors.joining(","));

            String SQL = "SELECT b.KEY_ID as userId, b.USER_LOGIN as staffId " +
                    "FROM LOGIN b " +
                    "WHERE b.KEY_ID IN (" + placeholders + ")";

            log.info(">>> SQL getUserNameMapByIds: " + SQL);
            log.info(">>> userIds to query: " + userIds);

            List<Map<String, Object>> rows = EBankJdbcTemplate.queryForList(SQL, userIds.toArray());

            log.info(">>> rows found: " + rows.size());
            rows.forEach(row -> log.info(">>> row: " + row));

            Map<Long, String> result = new HashMap<>();
            for (Map<String, Object> row : rows) {
                Long userId = Long.valueOf(row.get("userId").toString()); // ← KEY_ID
                String name = row.get("staffId") != null ? row.get("staffId").toString() : "-";
                result.put(userId, name);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
