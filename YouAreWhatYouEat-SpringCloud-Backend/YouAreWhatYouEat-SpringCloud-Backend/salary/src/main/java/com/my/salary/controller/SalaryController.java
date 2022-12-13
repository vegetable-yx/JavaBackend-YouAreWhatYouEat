package com.my.salary.controller;

import com.my.salary.dto.Salary.PostSalaryInfo;
import com.my.salary.dto.Salary.SalaryInfo;
import com.my.salary.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/api/v1/salary")

public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("")
    public ResponseEntity<List<SalaryInfo>> getSalaryInfo() {
        List<SalaryInfo> infos = salaryService.getSalaryInfo();
        if (infos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(infos);
        }
    }

    @PostMapping("")
    public ResponseEntity<Objects> postOneSalary(@RequestBody PostSalaryInfo p) {
        return new ResponseEntity<>(salaryService.postOneSalaryInfo(p));
    }

    @DeleteMapping("")
    public ResponseEntity<Objects> deleteSalaryInfo(@RequestParam String occupation) {
        return new ResponseEntity<>(salaryService.deleteSalaryInfo(occupation));
    }
}
