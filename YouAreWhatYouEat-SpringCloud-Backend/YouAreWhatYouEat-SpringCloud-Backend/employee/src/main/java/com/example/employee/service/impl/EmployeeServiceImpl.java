package com.example.employee.service.impl;

import com.example.employee.dto.EmployeePrizeDto;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.entity.PrizeEntity;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public Collection<EmployeePrizeDto> getPrizesByEmployeeId(int id) {
        EmployeeEntity employee = employeeRepository.findFirstById(id);
        Collection<PrizeEntity> prizes = employee.getPrizes();

        List<EmployeePrizeDto> result = new ArrayList<>();
        for (PrizeEntity prize : prizes) {
            EmployeePrizeDto employeePrizeDto = new EmployeePrizeDto();
            employeePrizeDto.setPrizeDatetime(prize.getPrizeDatetime());
            employeePrizeDto.setLv(prize.getLv());
            result.add(employeePrizeDto);
        }
        return result;
    }
}
