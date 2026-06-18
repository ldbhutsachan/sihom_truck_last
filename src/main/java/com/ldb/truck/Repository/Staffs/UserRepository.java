package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<StaffEntity, Long> {

    // ─── มีอยู่แล้ว ───────────────────────────────────────────

    Optional<StaffEntity> findByUsername(String username);

    Optional<StaffEntity> findByToken(String token);

    Optional<StaffEntity> findByStaffCode(String staffCode);

    boolean existsByUsername(String username);

    boolean existsByStaffCode(String staffCode);

    List<StaffEntity> findAllByStatus(String status);
    // NEW — ดึงเฉพาะ staffCode (ไม่โหลด entity ทั้งหมด ประหยัด memory)
    @Query("SELECT s.staffCode FROM StaffEntity s WHERE s.status = :status")
    List<String> findAllStaffCodesByStatus(@Param("status") String status);

    // ───  NEW เพิ่มใหม่ ──────────────────────────────────────

    //  NEW — ดึง staff ตาม borId และ status
    List<StaffEntity> findAllByBorIdAndStatus(Integer borId, String status);

    //  NEW — ดึง staff ตาม borId ทั้งหมด
    List<StaffEntity> findAllByBorId(Integer borId);
    // NEW — ดึง staff ตาม borId และ workSchedule
    List<StaffEntity> findAllByBorIdAndStatusAndWorkSchedule(
            Integer borId, String status, String workSchedule);

    // NEW — ดึง staff ตาม workSchedule
    List<StaffEntity> findAllByStatusAndWorkSchedule(
            String status, String workSchedule);

    // NEW — filter deptId
    @Query("SELECT s FROM StaffEntity s WHERE s.dept_id = :deptId")
    List<StaffEntity> findAllByDeptId(@Param("deptId") Long deptId);

    @Query("SELECT s FROM StaffEntity s WHERE s.borId = :borId AND s.dept_id = :deptId")
    List<StaffEntity> findAllByBorIdAndDeptId(
            @Param("borId")  Integer borId,
            @Param("deptId") Long deptId);

}