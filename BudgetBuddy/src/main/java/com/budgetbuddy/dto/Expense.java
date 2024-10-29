package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Expense {
    private int expID;
    private double expAmount;
    private Category expCategory;
    private String expDescription;
}

