package com.my.sensor.service.impl;

import com.my.sensor.dto.*;
import com.my.sensor.entity.SensorLogEntity;
import com.my.sensor.entity.SensorsEntity;
import com.my.sensor.repository.SensorLogsRepository;
import com.my.sensor.repository.SensorsRepository;
import com.my.sensor.service.SensorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Service
public class SensorServiceImpl implements SensorService {

    @Resource
    private SensorsRepository sensorsRepository;

    @Resource
    private SensorLogsRepository sensorLogsRepository;

    @Override
    public SensorRawDataMessage getRawData(String begin, String end) {
        SensorRawDataMessage ret = new SensorRawDataMessage();
        try {
            List<SensorsEntity> sensors = sensorsRepository.findAll();
            for (SensorsEntity s : sensors) {
                SensorLogMessage slm = new SensorLogMessage();
                slm.setSensorId(s.getSensId());
                slm.setSensorModel(s.getSensModel());
                slm.setSensorLocation(s.getSensLocation());
                slm.setSensorType(s.getSensType());

                Timestamp beginTime = begin == null ? Timestamp.valueOf("0001-01-01 00:00:00") : Timestamp.valueOf(begin);
                Timestamp endTime = end == null ? Timestamp.valueOf("9999-12-31 23:59:59") : Timestamp.valueOf(end);

                List<SensorLogEntity> sl = sensorLogsRepository.findBySlogTimeBetween(beginTime, endTime);
                for (SensorLogEntity slg : sl) {
                    slm.getLog().add(new SensorLogRecord(slg));
                }
                ret.getDate().add(slm);
            }
        }
        catch (Exception e) {
            return null;
        }
        return ret;
    }

    @Override
    public SensorsGetUsedResourcesMessage getUsedResource(String begin, String end) {
        SensorsGetUsedResourcesMessage ret = new SensorsGetUsedResourcesMessage();
        Timestamp beginTime = begin == null ? Timestamp.valueOf("0001-01-01 00:00:00") : Timestamp.valueOf(begin);
        Timestamp endTime = end == null ? Timestamp.valueOf("9999-12-31 23:59:59") : Timestamp.valueOf(end);
        ret.setBegin(beginTime);
        ret.setEnd(endTime);
        try {
            List<SensorsEntity> sensors = sensorsRepository.findAll();
            HashMap<String, BigDecimal> typeConsumption = new HashMap<>();
            for (SensorsEntity sensor : sensors) {
                String type = sensor.getSensType();
                BigDecimal consumption = BigDecimal.valueOf(0);
                List<SensorLogEntity> logs = sensor.getSensorLogs()
                        .stream()
                        .filter(log -> log.getSlogTime().after(beginTime) && log.getSlogTime().before(endTime))
                        .toList();
                Optional<SensorLogEntity> max = logs.stream().max((log1, log2) -> log1.getSlogValue().compareTo(log2.getSlogValue()));
                Optional<SensorLogEntity> min = logs.stream().min((log1, log2) -> log1.getSlogValue().compareTo(log2.getSlogValue()));
                if (max.isPresent() && min.isPresent()) {
                    consumption = consumption.add(max.get().getSlogValue().subtract(min.get().getSlogValue()));
                }
                if (typeConsumption.containsKey(type)) {
                    BigDecimal lastConsumption = typeConsumption.get(type);
                    typeConsumption.put(
                            type,
                            lastConsumption.add(consumption)
                    );
                }
                else {
                    typeConsumption.put(type, consumption);
                }
            }
            for (String type : typeConsumption.keySet()) {
                ret.getData().add(new SensorUsedRecord(type, typeConsumption.get(type)));
            }
        }
        catch (Exception e) {
            return null;
        }
        return ret;
    }
}
