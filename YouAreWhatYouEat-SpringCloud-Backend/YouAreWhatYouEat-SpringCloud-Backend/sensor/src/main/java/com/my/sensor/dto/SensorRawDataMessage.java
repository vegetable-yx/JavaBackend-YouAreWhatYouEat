package com.my.sensor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorRawDataMessage {
    @JsonProperty("code")
    private Integer code = 200;

    @JsonProperty("data")
    private List<SensorLogMessage> date = new ArrayList<>();
}
