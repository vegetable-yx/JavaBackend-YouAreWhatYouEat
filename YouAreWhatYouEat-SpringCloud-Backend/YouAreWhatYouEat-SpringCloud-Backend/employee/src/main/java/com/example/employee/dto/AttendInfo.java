package com.example.employee.dto;

import lombok.Data;

@Data
public class AttendInfo {
    private String plan_id;
    private String time_start;
    private String time_end;
    private String place;
    private boolean attendance;
}
