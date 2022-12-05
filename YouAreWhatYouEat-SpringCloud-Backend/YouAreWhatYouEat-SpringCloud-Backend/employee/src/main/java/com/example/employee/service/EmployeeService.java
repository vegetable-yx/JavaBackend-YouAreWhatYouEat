package com.example.employee.service;

import com.example.employee.dto.AllEmployeeInfoOutDto;
import com.example.employee.dto.EmployeeSimpleInfoOutDto;
import com.example.employee.dto.OneEmployeeInDto;
import com.example.employee.dto.OneEmployeeOutDto;

import java.math.BigInteger;
import java.util.List;

public interface EmployeeService {
    public List<AllEmployeeInfoOutDto> getAllEmployeeInfo();
    public boolean addEmployee(OneEmployeeInDto employee);
    public boolean updateEmployee(OneEmployeeInDto employee);
    public boolean deleteEmployee(String id);
    public OneEmployeeOutDto getOneEmployeeInfo(String id);
    public List<EmployeeSimpleInfoOutDto> getAllEmployeeSimpleInfo();
}
