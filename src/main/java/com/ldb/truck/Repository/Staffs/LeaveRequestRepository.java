package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // ดูรายการขอวันลาของ staff คนนั้น
    List<LeaveRequest> findByStaff_IdOrderByCreatedAtDesc(Long staffId);

    // ดูรายการขอวันลาตาม status
    List<LeaveRequest> findByStatusOrderByCreatedAtDesc(String status);

    // ดูรายการขอวันลาของ staff ตาม status
    List<LeaveRequest> findByStaff_IdAndStatusOrderByCreatedAtDesc(Long staffId, String status);

    // เช็คว่าช่วงวันที่ซ้อนทับกับที่ขอไปแล้วไหม
    List<LeaveRequest> findByStaff_IdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long staffId, String status, LocalDate endDate, LocalDate startDate);

    // นับวันลาที่ใช้ไปแล้วในปีนั้น
    List<LeaveRequest> findByStaff_IdAndLeaveTypeAndStatusAndStartDateBetween(
            Long staffId, String leaveType, String status,
            LocalDate startDate, LocalDate endDate);
}
