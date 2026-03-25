package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<StaffEntity, Long> {

    Optional<StaffEntity> findByUsername(String username);

    Optional<StaffEntity> findByToken(String token);

    Optional<StaffEntity> findByStaffCode(String staffCode);  // ✅ เพิ่ม

    boolean existsByUsername(String username);

    boolean existsByStaffCode(String staffCode);               // ✅ เพิ่ม

    List<StaffEntity> findAllByStatus(String status);
}