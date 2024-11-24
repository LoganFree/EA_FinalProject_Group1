package com.budgetbuddy.service;

import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
public class WeekDayService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getWeekDates(String week) {
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
    }

    public double calculateTotalBillsForWeek(String week, List<Bill> bills) {
        LocalDate[] weekDates = getStartAndEndDates(week);
        LocalDate startDate = weekDates[0];
        LocalDate endDate = weekDates[1];

        return bills.stream()
                .filter(bill -> isDateWithinRange(bill.getBillDueDate(), startDate, endDate))
                .mapToDouble(Bill::getBillAmount)
                .sum();
    }

    public double calculateTotalExpensesForWeek(String week, List<Expense> expenses) {
        LocalDate[] weekDates = getStartAndEndDates(week);
        LocalDate startDate = weekDates[0];
        LocalDate endDate = weekDates[1];

        return expenses.stream()
                .filter(expense -> isDateWithinRange(expense.getExpDate(), startDate, endDate))
                .mapToDouble(Expense::getExpAmount)
                .sum();
    }

    public boolean isDateWithinRange(String date, LocalDate startDate, LocalDate endDate) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);
            return !parsedDate.isBefore(startDate) && !parsedDate.isAfter(endDate);
        } catch (Exception e) {
            // Handle invalid date format if necessary
            return false;
        }
    }

    public LocalDate[] getStartAndEndDates(String week) {
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
            throw new IllegalArgumentException("Invalid week format");
        }
    }
}
