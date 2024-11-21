package com.budgetbuddy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an earning in the Budget Buddy application.
 * <p>
 * This class is used to store information about an individual's earnings, including the amount,
 * whether it is a yearly earning, the number of weekly hours worked, and the date of the earning.
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
public @Data class Earning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int earnId;
    private boolean earnIsYearly;
    private Double earnAmount;
    private double weeklyHours;
    private String earnDate;

    public LocalDate getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(earnDate, formatter);
    }
}
