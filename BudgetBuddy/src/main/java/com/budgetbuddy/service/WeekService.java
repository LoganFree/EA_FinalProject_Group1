package com.budgetbuddy.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Service
public class WeekService {

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

            return String.format("Days of Week: %d/%d-%d/%d",
                    monday.getMonthValue(), monday.getDayOfMonth(),
                    endDate.getMonthValue(), endDate.getDayOfMonth());
        } else {
            return "No week selected";
        }
    }
}
