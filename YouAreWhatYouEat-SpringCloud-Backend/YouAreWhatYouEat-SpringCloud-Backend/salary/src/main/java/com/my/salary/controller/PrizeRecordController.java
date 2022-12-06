package com.my.salary.controller;

import com.my.salary.dto.SalaryRecord.PostSalaryRecord;
import com.my.salary.dto.SalaryRecord.SalaryRecord;
import com.my.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/prizeRecord")
public class PrizeRecordController {

    private final SalaryService salaryService;

    @Autowired
    public PrizeRecordController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("")
    public ResponseEntity<List<SalaryRecord>> getSalaryRecord(
            @RequestParam String occupation,
            @RequestParam String id,
            @RequestParam String time_start,
            @RequestParam String time_end
    ) {
        List<SalaryRecord> records = salaryService.getSalaryRecord(occupation, id, time_start, time_end);
        if (records == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(records);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> postOneSalaryRecord(@RequestBody PostSalaryRecord p) {
        return new ResponseEntity<>(salaryService.postOneSalaryRecordInfo(p));
    }
}
