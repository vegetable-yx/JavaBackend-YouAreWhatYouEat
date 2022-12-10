package com.my.schedule.service.impl;

import com.my.schedule.dto.EmployeeInfo;
import com.my.schedule.dto.PeopleInfo;
import com.my.schedule.dto.ScheduleInfo;
import com.my.schedule.dto.ScheduleInfo2;
import com.my.schedule.entity.AttendEntity;
import com.my.schedule.entity.EmployeeEntity;
import com.my.schedule.entity.WorkPlanEntity;
import com.my.schedule.repository.AttendRepository;
import com.my.schedule.repository.EmployeeRepository;
import com.my.schedule.repository.WorkPlanRepository;
import com.my.schedule.service.ScheduleService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    WorkPlanRepository workPlanRepository;

    @Resource
    EmployeeRepository employeeRepository;

    @Resource
    AttendRepository attendRepository;

    @Value("${myConfiguration.url}")
    private String imageUrl;

    @Override
    public List<ScheduleInfo> getScheduleInfo(String start, String end, String id, String place, String occupation) {
        System.out.println(start + end + id + place + occupation);
        List<WorkPlanEntity> scheduleInfos = workPlanRepository.findAll();
        Timestamp startTime = null, endTime = null;
        if (start != null && !start.isEmpty()) {
            startTime = Timestamp.valueOf(start);
        }
        if (end != null && !end.isEmpty()) {
            endTime = Timestamp.valueOf(end);
        }
        List<ScheduleInfo> infos = new ArrayList<>();
        for (WorkPlanEntity schedule : scheduleInfos)
        {
            if ((startTime == null || startTime.before(schedule.getTimeStart()))
                    && (endTime == null || endTime.after(schedule.getTimeEnd()))
                    && (place == null || place.isEmpty() || schedule.getPlace().equals(place))
                    && (occupation == null || occupation.isEmpty() || schedule.getOccupation().equals(occupation)))
            {
                boolean tag = true;
                if (id != null)
                {
                    tag = false;
                    for (AttendEntity attend : schedule.getAttends())
                    {
                        if (attend.getEmployeeId().toString().equals(id))
                        {
                            tag = true;
                            break;
                        }
                    }
                }
                if (!tag) continue;

                ScheduleInfo info = new ScheduleInfo();
                info.setPlanId(schedule.getId().toString());
                info.setTimeStart(schedule.getTimeStart().toString());
                info.setTimeEnd(schedule.getTimeEnd().toString());
                info.setPlace(schedule.getPlace());
                info.setOccupation(schedule.getOccupation());

                List<PeopleInfo> peoples = new ArrayList<>();
                for (AttendEntity attend : schedule.getAttends())
                {
                    PeopleInfo peop = new PeopleInfo();
                    peop.setId(attend.getEmployeeId().toString());
                    Optional<EmployeeEntity> employee = employeeRepository.findById(attend.getEmployeeId());
                    peop.setName(employee.isEmpty() ? null : employee.get().getName());
                    peop.setGender(employee.isEmpty() ? null : employee.get().getGender());
                    peop.setAttendance(attend.getAttendance());
                    peoples.add(peop);
                }
                info.setPeople(peoples);
                infos.add(info);
            }
        }

        if (infos.size() == 0) return null;
        return infos;
    }

    @Override
    public List<EmployeeInfo> getFreeEmployee(String start, String end, String place, String occupation) {
        Timestamp startDate, endDate;
        if (start != null) startDate = Timestamp.valueOf(start);
        else startDate = Timestamp.valueOf("0001-01-01 00:00:00");
        if (end != null) endDate = Timestamp.valueOf(end);
        else endDate = Timestamp.valueOf("9999-12-31 23:59:59");

        List<WorkPlanEntity> workInfo = workPlanRepository.findAllByTimeStartAfterAndTimeEndBefore(startDate, endDate);
        List<BigInteger> busyIds = new ArrayList<>();
        for (WorkPlanEntity work : workInfo)
        {
            for (AttendEntity attend : work.getAttends())
            {
                if (busyIds.stream().noneMatch(b -> b.equals(attend.getEmployeeId()))) {
                    busyIds.add(attend.getEmployeeId());
                }
            }
        }

        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<EmployeeInfo> info = new ArrayList<>();

        for (EmployeeEntity employee : employees)
        {
            if ((occupation == null || employee.getOccupation().equals(occupation))
                    && busyIds.stream().noneMatch(b -> b.equals(employee.getId())))
            {
                EmployeeInfo emp = new EmployeeInfo();
                emp.setId(employee.getId().toString());
                emp.setName(employee.getName());
                emp.setGender(employee.getGender());
                emp.setAvatar(this.imageUrl + "employees/" + "employee_" + emp.getId() + ".png");
                info.add(emp);
            }
        }

        if (info.size() == 0) return null;
        return info;
    }

    @Transactional
    @Override
    public HttpStatus postScheduleInfo(ScheduleInfo2 p) {
        try {
            Timestamp start = Timestamp.valueOf(p.getTimeStart());
            Timestamp end = Timestamp.valueOf(p.getTimeEnd());
            String occupation = p.getOccupation();
            String place = p.getPlace();

            List<WorkPlanEntity> workPlanEntities = workPlanRepository.findAll();
            BigInteger id = BigInteger.ONE;
            Optional<WorkPlanEntity> maxId = workPlanEntities.stream().max((w1, w2) -> w1.getId().compareTo(w2.getId()));
            if (maxId.isPresent()) {
                id = maxId.get().getId();
            }
            WorkPlanEntity workPlan = new WorkPlanEntity();
            workPlan.setTimeStart(start);
            workPlan.setTimeEnd(end);
            workPlan.setOccupation(occupation);
            workPlan.setPlace(place);
            workPlan.setId(id);
            workPlan.setNo(BigInteger.valueOf(p.getEmployee_ids().size()));

            workPlanRepository.save(workPlan);
            for (int i = 0; i < p.getEmployee_ids().size(); ++i) {
                AttendEntity attend = new AttendEntity();
                attend.setAttendance(false);
                attend.setPlanId(id);
                attend.setEmployeeId(BigInteger.valueOf(Long.parseLong(p.getEmployee_ids().get(i))));
                attendRepository.save(attend);
            }
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Transactional
    @Override
    public HttpStatus deleteScheduleInfo(String id) {
        BigInteger wid;
        try {
            wid = BigInteger.valueOf(Long.parseLong(id));
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        if (!workPlanRepository.existsById(wid)) {
            return HttpStatus.NO_CONTENT;
        }
        try {
            workPlanRepository.deleteById(wid);
            return HttpStatus.OK;
        }
        catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
