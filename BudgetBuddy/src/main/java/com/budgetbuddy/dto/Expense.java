package com.budgetbuddy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public @Data class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expID;
    private double expAmount;
    private String expDate;
    private String expCategory;
    private String expDescription;
}

