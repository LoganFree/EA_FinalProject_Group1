package com.budgetbuddy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Represents a bill in the Budget Buddy application.
 * <p>
 * This class is annotated with {@link Entity} to indicate that it is a JPA entity that can be persisted in a relational
 * database. It contains fields for the bill's ID, amount, due date, and description.
 * </p>
 *
 * <p>
 * The class uses {@link lombok.Data} to automatically generate boilerplate code such as getters, setters, equals, hashCode,
 * and toString methods.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
@Entity
public @Data class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int billID;
    private double billAmount;
    private String billDueDate;
    private String billDescription;
}
