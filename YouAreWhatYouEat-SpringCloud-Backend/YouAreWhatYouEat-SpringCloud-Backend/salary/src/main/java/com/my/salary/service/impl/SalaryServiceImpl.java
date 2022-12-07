package com.my.salary.service.impl;

import com.my.salary.dto.Salary.PostSalaryInfo;
import com.my.salary.dto.Salary.SalaryInfo;
import com.my.salary.dto.SalaryRecord.PostSalaryRecord;
import com.my.salary.dto.SalaryRecord.SalaryRecord;
import com.my.salary.entity.EmployeeEntity;
import com.my.salary.entity.PayrollEntity;
import com.my.salary.entity.SalaryEntity;
import com.my.salary.repository.EmployeeRepository;
import com.my.salary.repository.PayRollRepository;
import com.my.salary.repository.SalaryRepository;
import com.my.salary.service.SalaryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SalaryServiceImpl implements SalaryService {
    @Resource
    private SalaryRepository salaryRepository;

    @Resource
    private PayRollRepository payRollRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public List<SalaryInfo> getSalaryInfo() {
        log.info("hahaha");
        List<SalaryEntity> salaries = salaryRepository.findAll();
        List<SalaryInfo> salaryInfos = new ArrayList<>();
        for (SalaryEntity salary : salaries) {
            SalaryInfo salaryInfo = new SalaryInfo();
            salaryInfo.setOccupation(salary.getOccupation());
            salaryInfo.setAmount(salary.getAmount());
            salaryInfo.setCount(BigDecimal.valueOf(salary.getEmployees().size()));
            salaryInfos.add(salaryInfo);
        }
        return salaryInfos;
    }

    @Override
    public List<SalaryRecord> getSalaryRecord(String occupation, String id, String timeStart, String timeEnd) {
        List<PayrollEntity> records = payRollRepository.findAll();
        try {
            List<SalaryRecord> salaryRecords = new ArrayList<>();
            for (PayrollEntity record : records) {
                if (id != null && !record.getEmployeeId().toString().equals(id)) {
                    continue;
                }
                Timestamp start = timeStart == null ? Timestamp.valueOf("0001-01-01 00:00:00") : Timestamp.valueOf(timeStart);
                Timestamp end   = timeEnd   == null ? Timestamp.valueOf("9999-12-31 23:59:59") : Timestamp.valueOf(timeEnd);
                if (record.getPayDatetime().before(start) || record.getPayDatetime().after(end)) {
                    continue;
                }
                Optional<EmployeeEntity> employee = employeeRepository.findById(record.getEmployeeId());
                if (employee.isEmpty()) {
                    continue;
                }
                if (occupation != null && !employee.get().getOccupation().equals(occupation)) {
                    continue;
                }
                Optional<SalaryEntity> salary = salaryRepository.findFirstByOccupation(occupation);
                if (salary.isEmpty()) {
                    continue;
                }
                SalaryRecord salaryRecord = new SalaryRecord();
                salaryRecord.setId(record.getEmployeeId().toString());
                salaryRecord.setName(employee.get().getName());
                salaryRecord.setOccupation(employee.get().getOccupation());
                salaryRecord.setTime(record.getPayDatetime().toString());
                salaryRecord.setAmount(salary.get().getAmount());
                salaryRecords.add(salaryRecord);
            }
            return salaryRecords;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Override
    public HttpStatus postOneSalaryInfo(PostSalaryInfo p) {
        if (p.getOccupation() == null || p.getAmount() == null) {
            return HttpStatus.BAD_REQUEST;
        }
        try {
            SalaryEntity salary = new SalaryEntity();
            salary.setOccupation(p.getOccupation());
            salary.setAmount(BigDecimal.valueOf(Long.parseLong(p.getAmount())));
            salaryRepository.saveAndFlush(salary);
            return HttpStatus.CREATED;
        }
        catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Transactional
    @Override
    public HttpStatus postOneSalaryRecordInfo(PostSalaryRecord p) {
        if (p.getId() == null || p.getTime() == null) {
            return HttpStatus.BAD_REQUEST;
        }
        try {
            PayrollEntity payroll = new PayrollEntity();
            payroll.setEmployeeId(BigInteger.valueOf(Long.parseLong(p.getId())));
            payroll.setPayDatetime(Timestamp.valueOf(p.getTime()));
            payRollRepository.saveAndFlush(payroll);
            return HttpStatus.CREATED;
        }
        catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    @Modifying
    @Transactional
    @Override
    public HttpStatus deleteSalaryInfo(String occupation) {
        if (occupation == null) {
            return HttpStatus.BAD_REQUEST;
        }
        Optional<SalaryEntity> salary = salaryRepository.findFirstByOccupation(occupation);
        if (salary.isEmpty()) {
            return HttpStatus.NO_CONTENT;
        }
        try {
            salaryRepository.flush();
            salaryRepository.deleteByOccupation(occupation);
            salaryRepository.flush();
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
