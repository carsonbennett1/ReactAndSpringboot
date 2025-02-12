package com.example.login.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.login.DTO.EmployeeDTO;
import com.example.login.Entity.Employee;
import com.example.login.Repo.EmployeeRepo;
import com.example.login.Service.EmployeeService;

public class EmployeeIMPL implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        
        Employee employee = new Employee(
            employeeDTO.getEmployee_id(),
            employeeDTO.getEmployee_name(),
            employeeDTO.getEmail(),

            this.passwordEncoder.encode(employeeDTO.getPassword())

        );

        employeeRepo.save(employee);


        return employee.getEmployee_name();

    }
    
}
