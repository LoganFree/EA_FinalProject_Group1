package com.budgetbuddy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public @Data class Earning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long earnID;
    private boolean earnIsYearly;
    private Double earnAmount;
    private double weeklyHours;
    private String earnDate;

    public LocalDate getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(earnDate, formatter);
    }
}
