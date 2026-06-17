package com.ldb.truck.Repository.Staffs;


import com.ldb.truck.Entity.Staff.StaffShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StaffShiftRepository
        extends JpaRepository<StaffShift, Long> {

    //  หากะปัจจุบันของ staff ณ วันที่กำหนด
    @Query("SELECT s FROM StaffShift s WHERE " +
            "s.staff.id = :staffId AND " +
            "s.effectiveDate <= :date AND " +
            "(s.endDate IS NULL OR s.endDate >= :date) " +
            "ORDER BY s.effectiveDate DESC")
    List<StaffShift> findCurrentShifts(
            @Param("staffId") Long staffId,
            @Param("date") LocalDate date);


    //add
    @Query("SELECT s FROM StaffShift s " +
            "JOIN FETCH s.shift " +
            "WHERE s.staff.id IN :staffIds " +
            "AND s.effectiveDate <= :date " +
            "AND (s.endDate IS NULL OR s.endDate >= :date) " +
            "ORDER BY s.effectiveDate DESC")
    List<StaffShift> findCurrentShiftsByStaffIds(
            @Param("staffIds") List<Long> staffIds,
            @Param("date") LocalDate date
    );
}
