package com.example.vip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VipApplication {

    public static void main(String[] args) {
        SpringApplication.run(VipApplication.class, args);
    }

}
