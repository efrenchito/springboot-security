package com.learningspring.springbootsecurity.service;

import com.learningspring.springbootsecurity.model.dto.EmployeeDto;
import com.learningspring.springbootsecurity.model.entity.Employee;
import com.learningspring.springbootsecurity.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Employee with ID '%d' NOT FOUND", id)));
    }

    @Override
    public Employee updateEmployee(long id, EmployeeDto employeeDto) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        return employeeRepository.save(employee);
    }

    @Override
    public String deleteEmployee(long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        return String.format("Employee with ID '%d' has been deleted successfully", id);
    }
}
