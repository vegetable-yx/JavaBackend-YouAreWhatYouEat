package com.example.employee.service;

import com.example.employee.dto.EmployeePrizeDto;
import com.example.employee.entity.PrizeEntity;

import java.util.Collection;

public interface EmployeeService {
    public Collection<EmployeePrizeDto> getPrizesByEmployeeId(int id);
}
