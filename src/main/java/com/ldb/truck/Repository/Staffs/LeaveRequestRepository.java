package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //  เพิ่มใหม่ทั้งหมด ──────────────────────────────

    // ✅ NEW — Query รวมทุก filter
    @Query("SELECT l FROM LeaveRequest l WHERE " +
            "(:staffId IS NULL OR l.staff.id = :staffId) AND " +
            "(:borId IS NULL OR l.staff.borId = :borId) AND " +
            "(:status IS NULL OR l.status = :status) AND " +
            "(:startDate IS NULL OR l.startDate >= :startDate) AND " +
            "(:endDate IS NULL OR l.endDate <= :endDate) " +
            "ORDER BY l.createdAt DESC")
    List<LeaveRequest> findByFilters(
            @Param("staffId")   Long staffId,
            @Param("borId")     Integer borId,
            @Param("status")    String status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate")   LocalDate endDate
    );
}
