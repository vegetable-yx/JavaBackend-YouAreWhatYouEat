package com.my.sensor.controller;

import com.my.sensor.dto.SensorRawDataMessage;
import com.my.sensor.dto.SensorsGetUsedResourcesMessage;
import com.my.sensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/sensors")
@CrossOrigin
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/rawdata")
    public ResponseEntity<SensorRawDataMessage> getSensorsRawData(@RequestParam String begin, @RequestParam String end) {
        SensorRawDataMessage msg = sensorService.getRawData(begin, end);
        if (msg == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(msg);
        }
    }

    @GetMapping("/used")
    public ResponseEntity<SensorsGetUsedResourcesMessage> getUsedResources(@RequestParam String begin, @RequestParam String end) {
        SensorsGetUsedResourcesMessage msg = sensorService.getUsedResource(begin, end);
        if (msg == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            return ResponseEntity.ok(msg);
        }
    }
}
