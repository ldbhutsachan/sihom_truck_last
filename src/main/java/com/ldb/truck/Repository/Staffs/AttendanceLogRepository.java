package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {

    Optional<AttendanceLog> findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeDesc(
            Long staffId, String checkType, LocalDateTime start, LocalDateTime end);

    //  ดึง log ทั้งหมดของ staff ตามช่วงวันที่
    @Query("SELECT a FROM AttendanceLog a " +
            "JOIN FETCH a.staff s " +
            "WHERE s.id = :staffId " +
            "AND a.checkTime BETWEEN :startDate AND :endDate " +
            "ORDER BY a.checkTime ASC")
    List<AttendanceLog> findByStaff_IdAndCheckTimeBetweenOrderByCheckTimeAsc(
            @Param("staffId") Long staffId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
     //    List<AttendanceLog> findByStaff_IdAndCheckTimeBetweenOrderByCheckTimeAsc(
     //            Long staffId, LocalDateTime start, LocalDateTime end);


    //  ดึง log ทั้งหมดทุกคนตามช่วงวันที่ (ADMIN)
    @Query("SELECT a FROM AttendanceLog a " +
            "JOIN FETCH a.staff s " +
            "WHERE a.checkTime BETWEEN :startDate AND :endDate " +
            "ORDER BY a.checkTime ASC")
    List<AttendanceLog> findAllByCheckTimeBetweenOrderByCheckTimeAsc(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
    //    List<AttendanceLog> findAllByCheckTimeBetweenOrderByCheckTimeAsc(
    //            LocalDateTime start, LocalDateTime end);

    //  NEW — ดึง CHECK_IN ครั้งแรกของวันที่กำหนด (OrderByCheckTimeAsc)
    Optional<AttendanceLog> findTopByStaff_IdAndCheckTypeAndCheckTimeBetweenOrderByCheckTimeAsc(
            Long staffId, String checkType, LocalDateTime start, LocalDateTime end);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query("DELETE FROM AttendanceLog a WHERE a.createdAt < :cutoffDate")
    void deleteLogsOlderThan(@Param("cutoffDate") LocalDateTime cutoffDate);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "INSERT INTO attendance_logs_backup (original_log_id, user_id, check_type, check_time, remark, created_at, ip_address, mac_address, backed_up_at) " +
            "SELECT id, user_id, check_type, check_time, remark, created_at, ip_address, mac_address, NOW() " +
            "FROM attendance_logs " +
            "WHERE created_at < :cutoffDate AND id NOT IN (SELECT original_log_id FROM attendance_logs_backup WHERE original_log_id IS NOT NULL)", nativeQuery = true)
    void backupLogsOlderThan(@Param("cutoffDate") LocalDateTime cutoffDate);
}
