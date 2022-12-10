package com.example.employee.service.impl;

import com.example.employee.dto.*;
import com.example.employee.entity.*;
import com.example.employee.repository.*;
import com.example.employee.service.EmployeeService;
import jakarta.annotation.Resource;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Value("${myConfiguration.url}")
    private String baseUrl;

    @Value("${myConfiguration.path}")
    private String localPath;
    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private AttendRepository attendRepository;

    @Resource
    private WorkPlanRepository workPlanRepository;

    @Resource
    private AwardRepository awardRepository;

    @Resource
    private SalaryRepository salaryRepository;

    @Override
    public List<AllEmployeeInfoOutDto> getAllEmployeeInfo() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<AllEmployeeInfoOutDto> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        try {
            for (EmployeeEntity employeeEntity : employeeEntities) {
                AllEmployeeInfoOutDto allEmployeeInfoDto = modelMapper.map(employeeEntity, AllEmployeeInfoOutDto.class);
                allEmployeeInfoDto.setAvatar(baseUrl + "employees/employee_" + allEmployeeInfoDto.getId() + ".png");

                // get attendance rate
                // List<AttendEntity> attends = attendRepository.findAllByEmployeeId(employeeEntity.getId());
                Collection<AttendEntity> attends = employeeEntity.getAttends();
                if (attends.size() == 0) {
                    allEmployeeInfoDto.setAttendance_rate(0.0);
                } else {
                    Double count = 0.0;
                    for (AttendEntity attendEntity : attends) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public boolean addEmployee(OneEmployeeInDto employeeDto) {
        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity employee = modelMapper.map(employeeDto, EmployeeEntity.class);
        employee.setAttends(null);
        employee.setParolls(null);
        employee.setPrizes(null);

        try {
            employeeRepository.saveAndFlush(employee);

            // upload pictures
            Base64.Decoder decoder = Base64.getDecoder();
            if (employeeDto.getAvatar() != null && !employeeDto.getAvatar().isEmpty()) {
                // Base64
                byte[] b = decoder.decode(employeeDto.getAvatar());
                String imgFilePath = localPath + "employee/employee_" + employeeDto.getId() + ".png";
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
            }

            if (employeeDto.getCover() != null && !employeeDto.getCover().isEmpty()) {
                // Base64
                byte[] b = decoder.decode(employeeDto.getCover());
                String imgFilePath = localPath + "cover/cover_" + employeeDto.getId() + ".png";
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    @Override
    public boolean updateEmployee(OneEmployeeInDto employeeDto) {
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

            // upload pictures
            Base64.Decoder decoder = Base64.getDecoder();
            if (employeeDto.getAvatar() != null && !employeeDto.getAvatar().isEmpty()) {
                // Base64
                byte[] b = decoder.decode(employeeDto.getAvatar());
                String imgFilePath = localPath + "employee/employee_" + employeeDto.getId() + ".png";
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
            }

            if (employeeDto.getCover() != null && !employeeDto.getCover().isEmpty()) {
                // Base64
                byte[] b = decoder.decode(employeeDto.getCover());
                String imgFilePath = localPath + "cover/cover_" + employeeDto.getId() + ".png";
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
            }
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

    @Override
    public OneEmployeeOutDto getOneEmployeeInfo(String id) {
        Long long_id = Long.valueOf(id);
        BigInteger int_id = BigInteger.valueOf(long_id);
        Optional<EmployeeEntity> employee = employeeRepository.findById(int_id);
        OneEmployeeOutDto oneEmployeeOutDto = null;

        if (employee.isEmpty()) return null;
        else {
            EmployeeEntity employeeEntity = employee.get();
            ModelMapper modelMapper = new ModelMapper();
            oneEmployeeOutDto = modelMapper.map(employeeEntity, OneEmployeeOutDto.class);
            oneEmployeeOutDto.setAvatar(baseUrl + "employees/employee_" + oneEmployeeOutDto.getId() + ".png");
            oneEmployeeOutDto.setCover(baseUrl + "covers/cover_" + oneEmployeeOutDto.getId() + ".png");
            oneEmployeeOutDto.setAttends(new HashSet<>());
            oneEmployeeOutDto.setPayrolls(new HashSet<>());
            oneEmployeeOutDto.setPrizes(new HashSet<>());

            for (AttendEntity attend : employeeEntity.getAttends()) {
                AttendInfo info = new AttendInfo();
                info.setPlan_id(attend.getPlanId().toString());
                info.setAttendance(attend.getAttendance() == BigInteger.ONE);

                BigInteger planId = attend.getPlanId();
                WorkPlanEntity workPlan = workPlanRepository.findFirstById(planId);
                info.setPlace(workPlan.getPlace());
                info.setTime_start(workPlan.getTimeStart().toString());
                info.setTime_end(workPlan.getTimeEnd().toString());
                oneEmployeeOutDto.getAttends().add(info);
            }

            for (PayrollEntity payroll : employeeEntity.getParolls()) {
                PayrollInfo info = new PayrollInfo();
                info.setPay_datetime(payroll.getPayDatetime().toString());

                String occupation = employeeEntity.getOccupation();
                info.setAmount(salaryRepository.findFirstByOccupation(occupation).getAmount());
                oneEmployeeOutDto.getPayrolls().add(info);
            }

            for (PrizeEntity prize : employeeEntity.getPrizes()) {
                PrizeInfo info = new PrizeInfo();
                info.setPrize_datetime(prize.getPrizeDatetime().toString());
                info.setLevel(prize.getLv());
                info.setAmount(Double.valueOf(awardRepository.findFirstByLv(prize.getLv()).getAmount()));
                oneEmployeeOutDto.getPrizes().add(info);
            }
        }
        return oneEmployeeOutDto;
    }

    @Override
    public List<EmployeeSimpleInfoOutDto> getAllEmployeeSimpleInfo() {
        List<EmployeeSimpleInfoOutDto> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (EmployeeEntity employee : employeeRepository.findAll()) {
            EmployeeSimpleInfoOutDto info = modelMapper.map(employee, EmployeeSimpleInfoOutDto.class);
            result.add(info);
        }
        return result;
    }
}
