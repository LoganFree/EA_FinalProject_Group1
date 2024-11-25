package com.budgetbuddy.service;

import com.budgetbuddy.dto.Earning;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public double calculateTotalBudgetForWeek(double totalBill, double totalExpense, Earning recentEarning)
    {
        // get most recent Earning amount
        double recentEarningAmount = recentEarning.getEarnAmount();

        // get most recent Earning type
        boolean recentEarningType = recentEarning.isEarnIsYearly();

        // is Yearly
        if(recentEarningType)
        {
            // divide most recent Earning amount by 52 to get weekly Earning amount
            double weeklyEarningAmount = recentEarningAmount / 52;

            // subtract bills and expenses from weekly Earning amount
            double weeklyBudget = (weeklyEarningAmount - totalBill) - totalExpense;

            // return weekly Budget
            return Math.round(weeklyBudget * 100.0) / 100.0;
        }
        // is Hourly
        else if (!recentEarningType)
        {
            // get weekly Hours for most recent Earning
            double weeklyHours = recentEarning.getWeeklyHours();

            // multiply most recent Earning amount by most the Earning's weekly hours to get weekly Earning amount
            double weeklyEarningAmount = recentEarningAmount * weeklyHours;

            // subtract bills and expenses from weekly Earning amount
            double weeklyBudget = (weeklyEarningAmount - totalBill) - totalExpense;

            // return weekly Budget
            return Math.round(weeklyBudget * 100.0) / 100.0;

        }

        return 0;
    }


}
