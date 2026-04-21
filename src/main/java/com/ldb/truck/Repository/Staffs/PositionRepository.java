package com.ldb.truck.Repository.Staffs;


import com.ldb.truck.Entity.Staff.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findAllByStatus(String status);
    List<Position> findAllByDeptIdAndStatus(Long deptId, String status);
    boolean existsByPosNameAndDeptId(String posName, Long deptId);
}