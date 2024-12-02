package com.budgetbuddy.service;

import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

/**
 * Service class for handling operations related to weekdays and weeks.
 * <p>
 * This service provides methods to retrieve specific week dates based on the given week number and year.
 * It can return the start and end date of the week in a formatted string.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

@Service
public class WeekDayService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getWeekDates(String week) {
        try {
            if (week != null && !week.isEmpty()) {
                String[] parts = week.split("-W");
                int year = Integer.parseInt(parts[0]);
                int weekNumber = Integer.parseInt(parts[1]);

                LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
                LocalDate monday = firstDayOfYear
                        .with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek()))
                        .plusWeeks(weekNumber - 1);

                LocalDate endDate = monday.plusDays(6);

                return String.format("%d/%d-%d/%d",
                        monday.getMonthValue(), monday.getDayOfMonth(),
                        endDate.getMonthValue(), endDate.getDayOfMonth());
            } else {
                return "No week selected";
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return "Invalid week format: " + e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    public double calculateTotalBillsForWeek(String week, List<Bill> bills) {
        try {
            LocalDate[] weekDates = getStartAndEndDates(week);
            LocalDate startDate = weekDates[0];
            LocalDate endDate = weekDates[1];

            return bills.stream()
                    .filter(bill -> isDateWithinRange(bill.getBillDueDate(), startDate, endDate))
                    .mapToDouble(Bill::getBillAmount)
                    .sum();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error calculating total bills: " + e.getMessage(), e);
        }
    }

    public double calculateTotalExpensesForWeek(String week, List<Expense> expenses) {
        try {
            LocalDate[] weekDates = getStartAndEndDates(week);
            LocalDate startDate = weekDates[0];
            LocalDate endDate = weekDates[1];

            return expenses.stream()
                    .filter(expense -> isDateWithinRange(expense.getExpDate(), startDate, endDate))
                    .mapToDouble(Expense::getExpAmount)
                    .sum();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error calculating total expenses: " + e.getMessage(), e);
        }
    }

    public boolean isDateWithinRange(String date, LocalDate startDate, LocalDate endDate) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);
            return !parsedDate.isBefore(startDate) && !parsedDate.isAfter(endDate);
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            System.err.println("Invalid date format for date: " + date);
            return false;
        }
    }

    public LocalDate[] getStartAndEndDates(String week) {
        try {
            if (week != null && !week.isEmpty()) {
                String[] parts = week.split("-W");
                int year = Integer.parseInt(parts[0]);
                int weekNumber = Integer.parseInt(parts[1]);

                LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
                LocalDate monday = firstDayOfYear
                        .with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek()))
                        .plusWeeks(weekNumber - 1);

                LocalDate endDate = monday.plusDays(6);

                return new LocalDate[]{monday, endDate};
            } else {
                throw new IllegalArgumentException("Week cannot be null or empty");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid week format: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while parsing week: " + e.getMessage(), e);
        }
    }
}
