package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkShiftRepository
        extends JpaRepository<WorkShift, Long> {
    List<WorkShift> findAllByStatus(String status);
    Optional<WorkShift> findByShiftCode(String shiftCode);
}
