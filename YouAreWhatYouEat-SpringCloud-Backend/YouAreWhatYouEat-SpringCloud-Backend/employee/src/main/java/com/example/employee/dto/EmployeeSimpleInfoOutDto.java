package com.example.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeSimpleInfoOutDto {
    @JsonProperty("employee_id")
    private int id;
    @JsonProperty("employee_name")
    private String name;
}
