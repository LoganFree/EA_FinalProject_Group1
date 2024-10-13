package com.budgetbuddy.dto;

import lombok.Data;

import java.util.Date;

public @Data class Bill {
    private int billID;
    private double billAmount;
    private Date billDueDate;
    private String billDescription;
}
