package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Earning {
    private boolean earnIsSalary;
    private Double earnAmount;
    private Double weeklyHours;
}
