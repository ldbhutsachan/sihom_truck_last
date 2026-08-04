package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.AttendanceLogBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface AttendanceLogBackupRepository extends JpaRepository<AttendanceLogBackup, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AttendanceLogBackup a WHERE a.createdAt < :cutoffDate")
    void deleteLogsOlderThan(@Param("cutoffDate") LocalDateTime cutoffDate);
}
