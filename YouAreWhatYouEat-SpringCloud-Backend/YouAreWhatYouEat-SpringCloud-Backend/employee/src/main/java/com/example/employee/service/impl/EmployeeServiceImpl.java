package com.example.employee.service.impl;

import com.example.employee.dto.AllEmployeeInfoDto;
import com.example.employee.dto.OneEmployeeDto;
import com.example.employee.entity.AttendEntity;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.entity.PrizeEntity;
import com.example.employee.repository.AttendRepository;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private AttendRepository attendRepository;

    @Override
    public List<AllEmployeeInfoDto> getAllEmployeeInfo() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<AllEmployeeInfoDto> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(EmployeeEntity employeeEntity : employeeEntities) {
            AllEmployeeInfoDto allEmployeeInfoDto = modelMapper.map(employeeEntity, AllEmployeeInfoDto.class);
            allEmployeeInfoDto.setAvatar("testUrl" + allEmployeeInfoDto.getId());

            // get attendance rate
            // List<AttendEntity> attends = attendRepository.findAllByEmployeeId(employeeEntity.getId());
            Collection<AttendEntity> attends = employeeEntity.getAttends();
            if (attends.size() == 0) {
                allEmployeeInfoDto.setAttendance_rate(0.0);
            } else {
                Double count = 0.0;
                for(AttendEntity attendEntity : attends) {
                    if (attendEntity.getAttendance() == BigInteger.ONE) {
                        count++;
                    }
                }
                allEmployeeInfoDto.setAttendance_rate(count / Double.valueOf(attends.size()));
            }

            // get prize times
            allEmployeeInfoDto.setAward_times(Double.valueOf(employeeEntity.getPrizes().size()));
            result.add(allEmployeeInfoDto);
        }
        return result;
    }

    @Override
    public boolean addEmployee(OneEmployeeDto employeeDto) {
        long id = Long.valueOf(employeeDto.getId());
        Optional<EmployeeEntity> tem = employeeRepository.findById(BigInteger.valueOf(id));
        if (!tem.isEmpty()) return false;

        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity employee = modelMapper.map(employeeDto, EmployeeEntity.class);
        employee.setAttends(null);
        employee.setParolls(null);
        employee.setPrizes(null);

        try {
            employeeRepository.saveAndFlush(employee);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateEmployee(OneEmployeeDto employeeDto) {
        long id = Long.valueOf(employeeDto.getId());
        Optional<EmployeeEntity> tem = employeeRepository.findById(BigInteger.valueOf(id));
        if (tem.isEmpty()) return false;

        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity employee = modelMapper.map(employeeDto, EmployeeEntity.class);
        employee.setAttends(null);
        employee.setParolls(null);
        employee.setPrizes(null);

        try {
            employeeRepository.saveAndFlush(employee);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteEmployee(String id) {
        try {
            Long long_id = Long.valueOf(id);
            BigInteger int_id = BigInteger.valueOf(long_id);
            employeeRepository.deleteById(int_id);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
