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
public class ScheduleInfo {
    @JsonProperty("plan_id")
    private String planId;

    @JsonProperty("time_start")
    private String timeStart;

    @JsonProperty("time_end")
    private String timeEnd;

    @JsonProperty("place")
    private String place;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("peoples")
    private List<PeopleInfo> people = new ArrayList<>();
}
