package com.example.employee.service;

import com.example.employee.dto.AllEmployeeInfoDto;
import com.example.employee.dto.OneEmployeeDto;
import com.example.employee.entity.EmployeeEntity;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    public List<AllEmployeeInfoDto> getAllEmployeeInfo();
    public boolean addEmployee(OneEmployeeDto employee);
    public boolean updateEmployee(OneEmployeeDto employee);
    public boolean deleteEmployee(String id);
}
