package com.my.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInfo2 {
    @JsonProperty("time_start")
    private String timeStart;

    @JsonProperty("time_end")
    private String timeEnd;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("place")
    private String place;

    @JsonProperty("employee_ids")
    private List<String> employee_ids = new ArrayList<>();
}
