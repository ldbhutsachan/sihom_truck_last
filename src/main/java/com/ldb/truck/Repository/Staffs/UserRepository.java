package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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

    // ───  NEW เพิ่มใหม่ ──────────────────────────────────────

    //  NEW — ดึง staff ตาม borId และ status
    List<StaffEntity> findAllByBorIdAndStatus(Integer borId, String status);

    //  NEW — ดึง staff ตาม borId ทั้งหมด
    List<StaffEntity> findAllByBorId(Integer borId);
}