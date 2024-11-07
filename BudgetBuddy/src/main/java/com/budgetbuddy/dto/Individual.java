package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Individual {
    private int individualID;
    private double individualAmount;
    private String individualDueDate;
    private String individualDescription;
}
