package com.budgetbuddy.dto;

import lombok.Data;

/**
 * Represents an item of an expense in the Budget Buddy application.
 * <p>
 * This class is used to store details of a single expense item, including the amount,
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
public @Data class ExpenseItem {
    private int expID;
    private double expAmount;
    private Category expCategory;
    private String expDescription;


    public ExpenseItem(double amount, String description) {
        this.expAmount = amount;
        this.expDescription = description;
    }
}

