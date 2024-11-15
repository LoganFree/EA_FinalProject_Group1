package com.budgetbuddy.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data class BillItem {
    private int billID;
    private double billAmount;
    private LocalDate billDueDate;
    private String billDescription;

    public BillItem(double amount, String description, LocalDate duedate) {
        this.billAmount = amount;
        this.billDescription = description;
        this.billDueDate = duedate;
    }
}
