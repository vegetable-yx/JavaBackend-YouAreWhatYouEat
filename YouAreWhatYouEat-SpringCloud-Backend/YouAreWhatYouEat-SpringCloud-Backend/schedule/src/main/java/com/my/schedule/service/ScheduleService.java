package com.my.schedule.service;

import com.my.schedule.dto.EmployeeInfo;
import com.my.schedule.dto.ScheduleInfo;
import com.my.schedule.dto.ScheduleInfo2;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ScheduleService {
    List<ScheduleInfo> getScheduleInfo(
            String start,
            String end,
            String id,
            String place,
            String occupation
    );

    List<EmployeeInfo> getFreeEmployee(
            String start,
            String end,
            String place,
            String occupation
    );

    HttpStatus postScheduleInfo(ScheduleInfo2 p);

    HttpStatus deleteScheduleInfo(String id);
}
