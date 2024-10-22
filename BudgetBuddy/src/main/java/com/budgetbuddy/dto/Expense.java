package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Expense {
    private int expID;
    private Double expAmount;
    private Category expCategory;
    private String expDescription;

    /*Category category*/
    public Expense(double amount, String description) {
        this.expAmount = amount;
        this.expDescription = description;

    }
}

