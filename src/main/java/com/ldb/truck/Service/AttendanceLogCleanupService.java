package com.ldb.truck.Service;

import com.ldb.truck.Repository.Staffs.AttendanceLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AttendanceLogCleanupService {

    @Autowired
    private AttendanceLogRepository attendanceLogRepository;

    @Autowired
    private com.ldb.truck.Repository.Staffs.AttendanceLogBackupRepository attendanceLogBackupRepository;

    // Run automatically at 00:00:00 on the 1st day of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void cleanupOldAttendanceLogs() {
        log.info("Starting scheduled cleanup of old attendance logs...");
        try {
            // Keep only the latest 2 months of data based on the createdAt field
            LocalDateTime cutoffDate = LocalDateTime.now().minusMonths(2);

            // First, backup any records that are about to be deleted (in case they weren't
            // backed up by the listener)
            attendanceLogRepository.backupLogsOlderThan(cutoffDate);

            // Then delete them from the main table
            attendanceLogRepository.deleteLogsOlderThan(cutoffDate);
            log.info("Successfully deleted attendance logs older than: {}", cutoffDate);

            // Keep only the latest 4 months of data in the backup table based on the
            // createdAt field
            LocalDateTime backupCutoffDate = LocalDateTime.now().minusMonths(3);
            attendanceLogBackupRepository.deleteLogsOlderThan(backupCutoffDate);
            log.info("Successfully deleted attendance log backups older than: {}", backupCutoffDate);
        } catch (Exception e) {
            log.error("Error occurred while deleting old attendance logs: ", e);
        }
    }
}
