package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Expense {
    private double expAmount;
    private String expCategory;
    private String expDescription;
}
