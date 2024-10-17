package com.budgetbuddy.dto;

import lombok.Data;

public @Data class Bill {
    private int BillId;
    private String BillAmount;
    private String BillDueDate;
    private String BillDescription;
}

