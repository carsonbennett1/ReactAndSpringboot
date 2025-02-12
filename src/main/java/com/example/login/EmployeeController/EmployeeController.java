package com.example.login.EmployeeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.login.DTO.EmployeeDTO;
import com.example.login.DTO.LoginDTO;
import com.example.login.Service.EmployeeService;
import com.example.login.response.LoginResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/employee")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    
    @PostMapping("/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        String id = employeeService.addEmployee(employeeDTO);
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee (@RequestBody LoginDTO loginDTO){
        LoginResponse loginMessage = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

}
