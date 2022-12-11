package com.my.schedule.controller;

import com.my.schedule.dto.EmployeeInfo;
import com.my.schedule.dto.ScheduleInfo;
import com.my.schedule.dto.ScheduleInfo2;
import com.my.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/schedules")
@CrossOrigin
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("")
    public ResponseEntity<List<ScheduleInfo>> getScheduleInfo(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String place,
            @RequestParam(required = false) String occupation
    ) {
        System.out.println("start " + start);
        System.out.println("end " + end);
        List<ScheduleInfo> ret = scheduleService.getScheduleInfo(start, end, id, place, occupation);
        if (ret == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(ret);
        }
    }

    @GetMapping("freeEmployees")
    public ResponseEntity<List<EmployeeInfo>> getFreeEmployee(
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam String place,
            @RequestParam String occupation
    ) {
        List<EmployeeInfo> infos = scheduleService.getFreeEmployee(start, end, place, occupation);
        if (infos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(infos);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> postScheduleInfo(@RequestBody ScheduleInfo2 p) {
        return new ResponseEntity<>(scheduleService.postScheduleInfo(p));
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteScheduleInfo(@RequestParam String id) {
        return new ResponseEntity<>(scheduleService.deleteScheduleInfo(id));
    }
}
