package com.budgetbuddy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Represents an expense in the Budget Buddy application.
 * <p>
 * This class is used to store information about an individual's expense, including the amount,
 * category, and description of the expense.
 * </p>
 *
 * <p>
 * The class uses {@link lombok.Data} to automatically generate getters, setters, equals, hashCode,
 * and toString methods.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
@Entity
public @Data class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expID;
    private double expAmount;
    private String expCategory;
    private String expDescription;
}

