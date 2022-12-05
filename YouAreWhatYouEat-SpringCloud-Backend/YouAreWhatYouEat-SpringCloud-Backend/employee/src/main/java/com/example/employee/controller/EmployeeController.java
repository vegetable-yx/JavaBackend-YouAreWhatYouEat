package com.example.employee.controller;

import com.example.employee.EmployeeApplication;
import com.example.employee.dto.AllEmployeeInfoDto;
import com.example.employee.dto.OneEmployeeDto;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.entity.PrizeEntity;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employee info
    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public ResponseEntity<List<AllEmployeeInfoDto>> getAllEmployees(
            @RequestParam(value = "token", required = false) String token
    )
    {
        // check token
        // ......

        List<AllEmployeeInfoDto> result = employeeService.getAllEmployeeInfo();
        return new ResponseEntity<List<AllEmployeeInfoDto>>(result, HttpStatus.OK);
    }

    // add a new employee
    @RequestMapping(value = "employees", method = RequestMethod.POST)
    public ResponseEntity addEmployee(@ModelAttribute OneEmployeeDto employeeDto)
    {
        if (employeeService.addEmployee(employeeDto)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // update a new employee
    @RequestMapping(value = "employees", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@ModelAttribute OneEmployeeDto employeeDto)
    {
        if (employeeService.updateEmployee(employeeDto)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // delete a new employee
    @RequestMapping(value = "employees", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(
            @RequestParam(required = true) String id
    )
    {
        if (employeeService.deleteEmployee(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
