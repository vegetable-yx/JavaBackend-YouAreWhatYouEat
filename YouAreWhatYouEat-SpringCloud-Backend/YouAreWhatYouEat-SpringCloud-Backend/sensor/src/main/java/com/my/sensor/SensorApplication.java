package com.my.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SensorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorApplication.class, args);
    }

}
