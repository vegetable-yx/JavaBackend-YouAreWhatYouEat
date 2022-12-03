package com.example.orderlists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderListsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderListsApplication.class, args);
    }

}
