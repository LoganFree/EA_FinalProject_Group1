package com.budgetbuddy.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

public @Data class Bill {
    private int billID;
    private double billAmount;
    private String billDueDate;
    private String billDescription;
}
