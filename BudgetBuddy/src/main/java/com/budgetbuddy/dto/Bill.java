package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Bill {
    private int billID;
    private Double billAmount;
    private String billDueDate;
    private String billDescription;
}
