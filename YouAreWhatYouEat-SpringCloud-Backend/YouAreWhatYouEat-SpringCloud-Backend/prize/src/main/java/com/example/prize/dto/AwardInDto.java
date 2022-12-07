package com.example.prize.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AwardInDto {
    @JsonProperty("level")
    String lv;
    Double amount;
}
