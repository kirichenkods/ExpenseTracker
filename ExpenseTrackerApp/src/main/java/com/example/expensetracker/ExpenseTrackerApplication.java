package com.example.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.example.expensetracker",
        "com.example.exchangerate",
        "com.example.domain",
        "com.example.kafkaproducer",
        "com.example.kafkaconsumer"
})
@EnableJpaRepositories(basePackages = {
        "com.example.expensetracker.repository"
})
@EntityScan(basePackages = {
        "com.example.expensetracker.entity",
        "com.example.domain.entity"
})
@EnableScheduling
public class ExpenseTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }

}
