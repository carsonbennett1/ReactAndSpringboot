package com.example.login.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.login.DTO.EmployeeDTO;
import com.example.login.DTO.LoginDTO;
import com.example.login.Entity.Employee;
import com.example.login.Repo.EmployeeRepo;
import com.example.login.Service.EmployeeService;
import com.example.login.response.LoginResponse;

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

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
        if(employee1 != null){
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);
            if(isPasswordRight){
                Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
                if(employee.isPresent()){
                    return new LoginResponse("Login Success", true);
                }else {
                    return new LoginResponse("Login Failed", false);
                }

            }else{
                return new LoginResponse("Password Not Match", false);
            }
        }else{
            return new LoginResponse("Email not valid", false);
        }
    }
    
}
