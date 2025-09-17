package com.ldb.truck.Repository;

import com.ldb.truck.Entity.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface employeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findByUsername(String username);   // ✅ ใช้ Username
    boolean existsByEmail(String email);              // ✅ แก้ exist → exists
}

