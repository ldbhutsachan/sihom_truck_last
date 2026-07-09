package com.ldb.truck.Dao;

import com.ldb.truck.Model.ImageOfCar.ImageOfCar;
import com.ldb.truck.Model.ImageOfCar.ImageOfCarReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class ImageOfCarDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertCarImage(ImageOfCarReq req, String userName, String filePath) {
        String sql = "INSERT INTO tb_imageofcar (car_id, image_type, file, create_by, create_date) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, req.getCarId(), req.getImageType(), filePath, userName, new Date());
    }

    public int updateCarImage(ImageOfCarReq req, String userName, String filePath) {
        if (filePath != null) {
            String sql = "UPDATE tb_imageofcar SET image_type = ?, file = ?, create_by = ?, create_date = ? WHERE id = ?";
            return jdbcTemplate.update(sql, req.getImageType(), filePath, userName, new Date(), req.getId());
        } else {
            String sql = "UPDATE tb_imageofcar SET image_type = ?, create_by = ?, create_date = ? WHERE id = ?";
            return jdbcTemplate.update(sql, req.getImageType(), userName, new Date(), req.getId());
        }
    }

    public List<ImageOfCar> listCarImages(ImageOfCarReq req) {
        StringBuilder sql = new StringBuilder("SELECT * FROM tb_imageofcar WHERE 1=1 ");
        
        if (req.getCarId() != null) {
            sql.append(" AND car_id = ").append(req.getCarId());
        }
        if (req.getImageType() != null && !req.getImageType().isEmpty()) {
            sql.append(" AND image_type = '").append(req.getImageType()).append("'");
        }
        
        return jdbcTemplate.query(sql.toString(), new RowMapper<ImageOfCar>() {
            @Override
            public ImageOfCar mapRow(ResultSet rs, int rowNum) throws SQLException {
                ImageOfCar img = new ImageOfCar();
                img.setId(rs.getLong("id"));
                img.setCarId(rs.getInt("car_id"));
                img.setImageType(rs.getString("image_type"));
                img.setFile(rs.getString("file"));
                img.setCreateBy(rs.getString("create_by"));
                img.setCreateDate(rs.getString("create_date"));
                return img;
            }
        });
    }
}
