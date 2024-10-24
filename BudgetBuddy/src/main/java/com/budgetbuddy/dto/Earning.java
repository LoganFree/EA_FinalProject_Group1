package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Earning {
    private boolean earnIsYearly;
    private double earnAmount;
    private double weeklyHours;
}
