package com.example.employee.dto;
import lombok.Data;

@Data
public class AllEmployeeInfoOutDto {
    private String id;
    private String name;
    private String gender;
    private String occupation;
    private String birthday;
    private Double attendance_rate;
    private Double award_times;
    private String avatar;
}
