package com.my.sensor.service;

import com.my.sensor.dto.SensorRawDataMessage;
import com.my.sensor.dto.SensorsGetUsedResourcesMessage;

public interface SensorService {
    public SensorRawDataMessage getRawData(String begin, String end);

    public SensorsGetUsedResourcesMessage getUsedResource(String begin, String end);
}
