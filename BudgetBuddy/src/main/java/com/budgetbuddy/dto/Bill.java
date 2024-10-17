package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Bill {
    private int BillId;
    private String billAmount;
    private String billDueDate;
    private String billDescription;
}

