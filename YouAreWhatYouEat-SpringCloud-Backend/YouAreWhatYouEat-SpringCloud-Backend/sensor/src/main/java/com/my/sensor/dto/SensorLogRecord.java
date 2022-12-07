package com.my.sensor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.sensor.entity.SensorLogEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorLogRecord {
    @JsonProperty("time")
    private Timestamp time;

    @JsonProperty("value")
    private BigDecimal value;

    public SensorLogRecord(SensorLogEntity s) {
        this.time = s.getSlogTime();
        this.value = s.getSlogValue();
    }
}
