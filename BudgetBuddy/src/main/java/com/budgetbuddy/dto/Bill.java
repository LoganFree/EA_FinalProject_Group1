package com.budgetbuddy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public @Data class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int billID;
    private double billAmount;
    private String billDueDate;
    private String billDescription;
}
