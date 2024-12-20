package com.budgetbuddy.service;

import com.budgetbuddy.dto.Earning;
import org.springframework.stereotype.Service;

/**
 * This is a Service class for calculating the budget based on earnings and expenses.
 * <p>
 * This service provides methods for calculating a weekly budget, which can be based on either
 * yearly salary or weekly earnings, as well as handling the impact of weekly expenses and monthly bills.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

@Service
public class CalculationService {

    public double calculateTotalBudgetForWeek(double totalBill, double totalExpense, Earning recentEarning) {
        // Validate inputs
        if (totalBill < 0 || totalExpense < 0) {
            throw new IllegalArgumentException("Total bill and expense must be non-negative.");
        }

        if (recentEarning == null) {
            throw new NullPointerException("Recent earning cannot be null.");
        }

        if (recentEarning.getEarnAmount() < 0) {
            throw new IllegalArgumentException("Earning amount must be non-negative.");
        }

        if (!recentEarning.isEarnIsYearly() && recentEarning.getWeeklyHours() <= 0) {
            throw new IllegalArgumentException("Weekly hours must be positive for hourly earnings.");
        }

        // get most recent Earning amount
        double recentEarningAmount = recentEarning.getEarnAmount();

        // get most recent Earning type
        boolean recentEarningType = recentEarning.isEarnIsYearly();

        // is Yearly
        if (recentEarningType) {
            // divide most recent Earning amount by 52 to get weekly Earning amount
            double weeklyEarningAmount = recentEarningAmount / 52;

            // subtract bills and expenses from weekly Earning amount
            double weeklyBudget = (weeklyEarningAmount - totalBill) - totalExpense;

            // return weekly Budget
            return Math.round(weeklyBudget * 100.0) / 100.0;
        }
        // is Hourly
        else if (!recentEarningType) {
            // get weekly Hours for most recent Earning
            double weeklyHours = recentEarning.getWeeklyHours();

            // multiply most recent Earning amount by the Earning's weekly hours to get weekly Earning amount
            double weeklyEarningAmount = recentEarningAmount * weeklyHours;

            // subtract bills and expenses from weekly Earning amount
            double weeklyBudget = (weeklyEarningAmount - totalBill) - totalExpense;

            // return weekly Budget
            return Math.round(weeklyBudget * 100.0) / 100.0;

        }

        return 0;
    }

}
