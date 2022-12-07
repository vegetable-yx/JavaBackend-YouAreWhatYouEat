package com.my.salary.service;

import com.my.salary.dto.Salary.PostSalaryInfo;
import com.my.salary.dto.Salary.SalaryInfo;
import com.my.salary.dto.SalaryRecord.PostSalaryRecord;
import com.my.salary.dto.SalaryRecord.SalaryRecord;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface SalaryService {
    List<SalaryInfo> getSalaryInfo();

    List<SalaryRecord> getSalaryRecord(String occupation, String id, String timeStart, String timeEnd);

    HttpStatus postOneSalaryInfo(PostSalaryInfo p);

    HttpStatus postOneSalaryRecordInfo(PostSalaryRecord p);

    HttpStatus deleteSalaryInfo(String occupation);
}
