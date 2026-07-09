package com.ldb.truck.Dao;

import com.ldb.truck.Model.StaffStatement.StaffStatementRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StaffStatementDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertStaffStatement(Integer staffId, String title, String files, String saveBy) {
        String sql = "INSERT INTO staff_statements (staff_id, title, files, save_by, create_date) VALUES (?, ?, ?, ?, NOW())";
        return jdbcTemplate.update(sql, staffId, title, files, saveBy);
    }

    public List<StaffStatementRes> searchStaffStatements(Integer staffId, Integer borId, Long deptId, String startDate, String endDate) {
        StringBuilder sql = new StringBuilder(
            "SELECT s.*, c.username, c.lao_name, c.staff_code, c.dept_id, c.pos_id, c.bor_id, " +
            "d.dept_name AS deptName, p.pos_name AS posName, b.b_name " +
            "FROM staff_statements s " +
            "LEFT JOIN company_staffs c ON s.staff_id = c.id " +
            "LEFT JOIN company_departments d ON c.dept_id = d.id " +
            "LEFT JOIN company_positions p ON c.pos_id = p.id " +
            "LEFT JOIN tb_bors b ON c.bor_id = b.key_id " +
            "WHERE 1=1 "
        );

        List<Object> params = new java.util.ArrayList<>();
        
        if (staffId != null) {
            sql.append("AND s.staff_id = ? ");
            params.add(staffId);
        }
        if (borId != null) {
            sql.append("AND c.bor_id = ? ");
            params.add(borId);
        }
        if (deptId != null) {
            sql.append("AND c.dept_id = ? ");
            params.add(deptId);
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            sql.append("AND s.create_date >= ? ");
            params.add(startDate.trim() + " 00:00:00");
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            sql.append("AND s.create_date <= ? ");
            params.add(endDate.trim() + " 23:59:59");
        }
        
        sql.append("ORDER BY s.id DESC");
        
        return jdbcTemplate.query(sql.toString(), params.toArray(), new StaffStatementRowMapper());
    }

    public int updateStaffStatement(Long id, String title, String files, String saveBy) {
        String sql = "UPDATE staff_statements SET title = ?, files = ?, save_by = ? WHERE id = ?";
        return jdbcTemplate.update(sql, title, files, saveBy, id);
    }

    public int deleteStaffStatement(Long id) {
        String sql = "DELETE FROM staff_statements WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private static class StaffStatementRowMapper implements RowMapper<StaffStatementRes> {
        @Override
        public StaffStatementRes mapRow(ResultSet rs, int rowNum) throws SQLException {
            StaffStatementRes res = new StaffStatementRes();
            res.setId(rs.getLong("id"));
            res.setStaffId(rs.getInt("staff_id"));
            res.setTitle(rs.getString("title"));
            res.setFiles(rs.getString("files"));
            res.setSaveBy(rs.getString("save_by"));
            res.setCreateDate(rs.getString("create_date"));
            
            // From join
            res.setUsername(rs.getString("username"));
            res.setLaoName(rs.getString("lao_name"));
            res.setStaffCode(rs.getString("staff_code"));
            res.setDeptId(rs.getLong("dept_id"));
            if (rs.wasNull()) res.setDeptId(null);
            res.setPosId(rs.getLong("pos_id"));
            if (rs.wasNull()) res.setPosId(null);
            
            res.setBorId(rs.getInt("bor_id"));
            if (rs.wasNull()) res.setBorId(null);
            res.setDeptName(rs.getString("deptName"));
            res.setPosName(rs.getString("posName"));
            res.setBName(rs.getString("b_name"));
            
            return res;
        }
    }
}
