package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.StaffDayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StaffDayOffRepository extends JpaRepository<StaffDayOff, Long> {

    // ดึงวันหยุด pattern ของ staff
    List<StaffDayOff> findByStaffId(Long staffId);

    // ลบวันหยุดเดิมของ staff (ตอน update)
    void deleteByStaffId(Long staffId);
}
