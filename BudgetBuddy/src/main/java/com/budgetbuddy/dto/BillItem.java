package com.budgetbuddy.dto;

import lombok.Data;

/**
 * Represents an item on a bill in the Budget Buddy application.
 * <p>
 * This class contains details about a bill item, including the amount, due date, and description.
 * It is used to capture and manage individual items associated with a bill.
 * </p>
 *
 * <p>
 * The class uses {@link lombok.Data} to automatically generate getters, setters, equals, hashCode,
 * and toString methods.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
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
