package com.my.sensor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorLogMessage {
    @JsonProperty("sensor_id")
    private BigInteger sensorId;

    @JsonProperty("sensor_type")
    private String sensorType;

    @JsonProperty("sensor_model")
    private String sensorModel;

    @JsonProperty("sensor_location")
    private String sensorLocation;

    @JsonProperty("log")
    private List<SensorLogRecord> log = new ArrayList<>();
}
