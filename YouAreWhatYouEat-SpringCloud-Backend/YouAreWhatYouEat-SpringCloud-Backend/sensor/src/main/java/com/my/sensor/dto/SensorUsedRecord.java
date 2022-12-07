package com.my.sensor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorUsedRecord {
    @JsonProperty("type")
    private String type;

    @JsonProperty("consumption")
    private BigDecimal consumption;
}
