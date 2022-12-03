package com.example.dishes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class DishesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DishesApplication.class, args);
    }

}
