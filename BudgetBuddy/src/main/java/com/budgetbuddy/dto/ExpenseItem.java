package com.budgetbuddy.dto;

import lombok.Data;

public @Data class ExpenseItem {
    private int expID;
    private double expAmount;
    private Category expCategory;
    private String expDescription;


    public ExpenseItem(double amount, String description) {
        this.expAmount = amount;
        this.expDescription = description;
    }
}

