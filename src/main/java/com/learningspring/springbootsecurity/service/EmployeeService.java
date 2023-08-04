package com.learningspring.springbootsecurity.service;

import com.learningspring.springbootsecurity.model.dto.EmployeeDto;
import com.learningspring.springbootsecurity.model.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee createEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);

    Employee updateEmployee(long id, EmployeeDto employeeDto);

    String deleteEmployee(long id);
}
