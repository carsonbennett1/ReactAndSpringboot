package com.example.login.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.login.Entity.Employee;

@EnableJpaRepositories
@Repository

public interface EmployeeRepo extends JpaRepository <Employee, Integer>{
    
}
