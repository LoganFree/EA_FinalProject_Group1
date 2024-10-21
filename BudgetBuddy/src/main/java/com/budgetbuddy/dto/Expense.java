package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Expense {
    private int expID;
    private Double expAmount;
    private Category expCategory;
    private String expDescription;
}
