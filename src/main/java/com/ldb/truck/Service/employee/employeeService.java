package com.ldb.truck.Service.employee;

import com.ldb.truck.Entity.employee.EmployeeEntity;
import com.ldb.truck.Repository.employeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class employeeService {
    private final employeeRepository employeeRepository;


    public employeeService(employeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // เพิ่มพนักงานใหม่
    public EmployeeEntity addEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }
    // ดึงข้อมูลพนักงานทั้งหมด
    public List<EmployeeEntity>getAllEmployees(){
        return employeeRepository.findAll();
    }
}
