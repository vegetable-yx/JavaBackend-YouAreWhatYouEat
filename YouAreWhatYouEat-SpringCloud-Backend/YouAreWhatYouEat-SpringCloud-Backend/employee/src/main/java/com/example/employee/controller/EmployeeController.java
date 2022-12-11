package com.example.employee.controller;

import com.example.employee.dto.*;
import com.example.employee.service.AuthenClient;
import com.example.employee.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    private AuthenClient authenClient;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employee info
    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public ResponseEntity<List<AllEmployeeInfoOutDto>> getAllEmployees(
            @RequestParam(value = "token", required = false) String token
    )
    {
        // check token
        if (token == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        ResponseEntity<TokenInfo> check = authenClient.checkToken(token);
        if (check.getBody() == null || !check.getBody().getActive())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<AllEmployeeInfoOutDto> result = employeeService.getAllEmployeeInfo();
        if (result.size() == 0) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<AllEmployeeInfoOutDto>>(result, HttpStatus.OK);
    }

    // Get all employee simple info
    @RequestMapping(value = "employeeInfo", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeSimpleInfoOutDto>> getAllEmployeeSimpleInfo()
    {
        List<EmployeeSimpleInfoOutDto> result = employeeService.getAllEmployeeSimpleInfo();
        if (result.size() == 0) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Get one employee info
    @RequestMapping(value = "employee", method = RequestMethod.GET)
    public ResponseEntity<OneEmployeeOutDto> getAllEmployees(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "token", required = false) String token
    )
    {
        // check token
        if (token == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        ResponseEntity<TokenInfo> check = authenClient.checkToken(token);
        if (check.getBody() == null || !check.getBody().getActive())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (id == null) id = check.getBody().getUsername();
        OneEmployeeOutDto result = employeeService.getOneEmployeeInfo(id);
        if (result == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // add a new employee
    @RequestMapping(value = "employees", method = RequestMethod.POST)
    public ResponseEntity addEmployee(@RequestBody OneEmployeeInDto employeeDto)
    {
        if (employeeService.addEmployee(employeeDto)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // update a new employee
    @RequestMapping(value = "employees", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@RequestBody OneEmployeeInDto employeeDto)
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
            @RequestParam(required = true) String id,
            @RequestParam(required = false) String token
    )
    {
        // check token
        if (token == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        ResponseEntity<TokenInfo> check = authenClient.checkToken(token);
        if (check.getBody() == null || !check.getBody().getActive())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (employeeService.deleteEmployee(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
