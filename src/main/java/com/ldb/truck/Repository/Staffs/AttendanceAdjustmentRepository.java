package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.AttendanceAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceAdjustmentRepository
        extends JpaRepository<AttendanceAdjustment, Long> {

    // ดูรายการของ staff คนนั้น
    List<AttendanceAdjustment> findByStaff_IdOrderByCreatedAtDesc(Long staffId);

    // ดูตาม status
    List<AttendanceAdjustment> findByStatusOrderByCreatedAtDesc(String status);

    // ดูตาม bor_id
    List<AttendanceAdjustment> findByStaff_BorIdOrderByCreatedAtDesc(Integer borId);

    // ดูทั้งหมด
    List<AttendanceAdjustment> findAllByOrderByCreatedAtDesc();
}
