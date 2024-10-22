package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Bill {
    private int billID;
    private double billAmount;
    private String billDueDate;
    private String billDescription;

    public Bill(double amount, String description, String duedate) {
        this.billAmount = amount;
        this.billDescription = description;
        this.billDueDate = duedate;
    }
}
