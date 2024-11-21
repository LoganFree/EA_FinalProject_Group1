package com.budgetbuddy.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Service class for handling operations related to weekdays and weeks.
 * <p>
 * This service provides methods to retrieve specific week dates based on the given week number and year.
 * It can return the start and end date of the week in a formatted string.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
@Service
public class WeekDayService {

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
}
