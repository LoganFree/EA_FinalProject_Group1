package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Expense {

    private int expID;
    private double expAmount;
    private String expCategory;
    private String expDescription;
}

