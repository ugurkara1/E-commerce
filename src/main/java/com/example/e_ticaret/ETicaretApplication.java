package com.example.e_ticaret;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.repository")
@ComponentScan(basePackages = { "com.example.controller","com.example.model","com.example.service","com.example.dto"})
public class ETicaretApplication {
    public static void main(String[] args) {
        SpringApplication.run(ETicaretApplication.class, args);
        System.out.println("Welcome to the Spring Boot first Application");
    }
}
