package com.budgetbuddy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public @Data class Earning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int earnId;
    private boolean earnIsYearly;
    private Double earnAmount;
    private double weeklyHours;
}
