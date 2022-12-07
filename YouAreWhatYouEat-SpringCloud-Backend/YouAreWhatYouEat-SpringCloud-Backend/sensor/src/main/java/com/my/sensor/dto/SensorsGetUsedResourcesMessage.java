package com.my.sensor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorsGetUsedResourcesMessage {
    @JsonProperty("code")
    private Integer code = 200;

    @JsonProperty("begin")
    private Timestamp begin;

    @JsonProperty("end")
    private Timestamp end;

    @JsonProperty("data")
    private List<SensorUsedRecord> data = new ArrayList<>();
}
