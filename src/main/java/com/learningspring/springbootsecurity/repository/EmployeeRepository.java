package com.learningspring.springbootsecurity.repository;

import com.learningspring.springbootsecurity.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
