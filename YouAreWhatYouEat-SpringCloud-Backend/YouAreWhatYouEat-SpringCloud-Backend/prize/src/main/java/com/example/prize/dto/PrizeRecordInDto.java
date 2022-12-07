package com.example.prize.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrizeRecordInDto {
    @JsonProperty("id")
    private String employeeId;
    @JsonProperty("level")
    private String lv;
    @JsonProperty("time")
    private String prizeDatetime;
}
