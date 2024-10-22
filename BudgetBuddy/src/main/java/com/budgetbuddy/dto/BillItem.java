package com.budgetbuddy.dto;

import lombok.Data;

public @Data class BillItem {
    private int billID;
    private double billAmount;
    private String billDueDate;
    private String billDescription;

    public BillItem(double amount, String description, String duedate) {
        this.billAmount = amount;
        this.billDescription = description;
        this.billDueDate = duedate;
    }
}
