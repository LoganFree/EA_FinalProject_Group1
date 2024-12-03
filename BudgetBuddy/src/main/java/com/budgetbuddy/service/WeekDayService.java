package com.budgetbuddy.service;

import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.List;

/**
 * Service class for handling operations related to weekdays and weeks.
 * <p>
 * This service provides methods to retrieve specific week dates based on the given week number and year.
 * It can return the start and end date of the week in a formatted string.
 * </p>
 *
 * @autor Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

@Service
public class WeekDayService {

    // Formatter for parsing and formatting dates in "yyyy-MM-dd" format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Method to get the start and end dates of a week based on a week identifier (e.g., "2024-W01")
    public String getWeekDates(String week) {
        try {
            // Ensure the week string is valid
            if (week != null && !week.isEmpty()) {
                // Split the input string into year and week number
                String[] parts = week.split("-W");
                int year = Integer.parseInt(parts[0]);
                int weekNumber = Integer.parseInt(parts[1]);

                // Calculate the first day of the specified week
                LocalDate date = LocalDate.now()
                        .withYear(year)
                        .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber)
                        .with(WeekFields.ISO.getFirstDayOfWeek());

                // Get the start and end dates of the week
                LocalDate startOfWeek = date.with(WeekFields.ISO.getFirstDayOfWeek());
                LocalDate endOfWeek = startOfWeek.plusDays(6);

                // Return the week range in "MM/dd-MM/dd" format
                return String.format("%d/%d-%d/%d",
                        startOfWeek.getMonthValue(), startOfWeek.getDayOfMonth(),
                        endOfWeek.getMonthValue(), endOfWeek.getDayOfMonth());
            } else {
                // Handle null or empty week string
                return "No week selected";
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Handle invalid week format
            return "Invalid week format: " + e.getMessage();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    // Method to calculate the total bills for a given week
    public double calculateTotalBillsForWeek(String week, List<Bill> bills) {
        try {
            // Get the start and end dates for the specified week
            LocalDate[] weekDates = getStartAndEndDates(week);
            LocalDate startDate = weekDates[0];
            LocalDate endDate = weekDates[1];

            // Filter and sum bill amounts within the week range
            return bills.stream()
                    .filter(bill -> isDateWithinRange(bill.getBillDueDate(), startDate, endDate))
                    .mapToDouble(Bill::getBillAmount)
                    .sum();
        } catch (IllegalArgumentException e) {
            // Handle invalid input or date range issues
            throw new RuntimeException("Error calculating total bills: " + e.getMessage(), e);
        }
    }

    // Method to calculate the total expenses for a given week
    public double calculateTotalExpensesForWeek(String week, List<Expense> expenses) {
        try {
            // Get the start and end dates for the specified week
            LocalDate[] weekDates = getStartAndEndDates(week);
            LocalDate startDate = weekDates[0];
            LocalDate endDate = weekDates[1];

            // Filter and sum expense amounts within the week range
            return expenses.stream()
                    .filter(expense -> isDateWithinRange(expense.getExpDate(), startDate, endDate))
                    .mapToDouble(Expense::getExpAmount)
                    .sum();
        } catch (IllegalArgumentException e) {
            // Handle invalid input or date range issues
            throw new RuntimeException("Error calculating total expenses: " + e.getMessage(), e);
        }
    }

    // Method to check if a date is within a specific range
    public boolean isDateWithinRange(String date, LocalDate startDate, LocalDate endDate) {
        try {
            // Parse the date string into a LocalDate
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);

            // Check if the date falls between startDate and endDate (inclusive)
            return !parsedDate.isBefore(startDate) && !parsedDate.isAfter(endDate);
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            System.err.println("Invalid date format for date: " + date);
            return false;
        }
    }

    // Helper method to parse the start and end dates of a week from a week identifier
    public LocalDate[] getStartAndEndDates(String week) {
        try {
            // Ensure the week string is valid
            if (week != null && !week.isEmpty()) {
                // Split the input string into year and week number
                String[] parts = week.split("-W");
                int year = Integer.parseInt(parts[0]);
                int weekNumber = Integer.parseInt(parts[1]);

                // Calculate the first day of the specified week
                LocalDate date = LocalDate.now()
                        .withYear(year)
                        .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber)
                        .with(WeekFields.ISO.getFirstDayOfWeek());

                // Get the start and end dates of the week
                LocalDate startOfWeek = date.with(WeekFields.ISO.getFirstDayOfWeek());
                LocalDate endOfWeek = startOfWeek.plusDays(6);

                // Return the start and end dates as an array
                return new LocalDate[]{startOfWeek, endOfWeek};
            } else {
                // Handle null or empty week string
                throw new IllegalArgumentException("Week cannot be null or empty");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Handle invalid week format
            throw new IllegalArgumentException("Invalid week format: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other unexpected exceptions
            throw new RuntimeException("An unexpected error occurred while parsing week: " + e.getMessage(), e);
        }
    }
}

