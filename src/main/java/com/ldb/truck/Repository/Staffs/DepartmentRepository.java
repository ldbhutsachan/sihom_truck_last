package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAllByStatus(String status);
    boolean existsByDeptName(String deptName);
}
