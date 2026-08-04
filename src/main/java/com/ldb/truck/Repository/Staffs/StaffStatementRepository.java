package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.StaffStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffStatementRepository extends JpaRepository<StaffStatement, Integer> {
}
