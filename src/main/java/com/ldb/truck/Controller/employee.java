package com.ldb.truck.Controller;

import com.ldb.truck.Entity.employee.EmployeeEntity;
import com.ldb.truck.Service.employee.employeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class employee {
    private final employeeService employeeService;

    public employee(employeeService employeeService){
        this.employeeService=employeeService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/insertEmployee.serivce")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee){
        return employeeService.addEmployee(employee);
    }
    @GetMapping("/getEmployee.service")
    public List<EmployeeEntity>getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
