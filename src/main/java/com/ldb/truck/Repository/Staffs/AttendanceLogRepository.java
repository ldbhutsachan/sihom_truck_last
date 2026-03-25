package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {

    Optional<AttendanceLog> findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeDesc(
            Long staffId, String checkType, LocalDateTime start, LocalDateTime end);

    // ✅ ดึง log ทั้งหมดของ staff ตามช่วงวันที่
    List<AttendanceLog> findByStaff_IdAndCheckTimeBetweenOrderByCheckTimeAsc(
            Long staffId, LocalDateTime start, LocalDateTime end);

    // ✅ ดึง log ทั้งหมดทุกคนตามช่วงวันที่ (ADMIN)
    List<AttendanceLog> findAllByCheckTimeBetweenOrderByCheckTimeAsc(
            LocalDateTime start, LocalDateTime end);
}
