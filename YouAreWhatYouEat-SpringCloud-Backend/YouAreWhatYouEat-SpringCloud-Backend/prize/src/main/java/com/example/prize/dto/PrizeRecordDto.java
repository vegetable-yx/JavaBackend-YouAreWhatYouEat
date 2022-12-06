package com.example.prize.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrizeRecordDto {
    private String id;
    private String name;
    @JsonProperty("level")
    private String lv;
    @JsonProperty("time")
    private String prizeDatetime;
    private Double amount;
}
