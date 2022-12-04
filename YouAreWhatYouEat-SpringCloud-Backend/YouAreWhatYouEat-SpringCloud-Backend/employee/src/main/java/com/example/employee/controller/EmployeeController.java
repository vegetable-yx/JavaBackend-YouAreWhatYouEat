package com.example.employee.controller;

import com.example.employee.EmployeeApplication;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.entity.PrizeEntity;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "employeePrize", method = RequestMethod.GET)
    public ResponseEntity<PrizeEntity> getOneEmployeePrize(
            @RequestParam(value = "id") int id
    )
    {
        return new ResponseEntity(employeeService.getPrizesByEmployeeId(id), HttpStatus.OK);
    }
}
