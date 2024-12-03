package com.budgetbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BudgetBuddyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BudgetBuddyApplication.class, args);
    }
}

