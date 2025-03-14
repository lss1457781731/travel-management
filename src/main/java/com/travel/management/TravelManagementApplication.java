package com.travel.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.travel.management.mapper")
public class TravelManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelManagementApplication.class, args);
    }
} 