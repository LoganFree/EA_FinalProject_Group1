package com.budgetbuddy.service;

public class CalculationService {

    private boolean earnIsYearly;
    private double earnAmount;
    private double weeklyHours;

    private double weeklyExpensesTotal;

    private double monthlyBill;

    public double weeklyBudget;



    public CalculationService(boolean earnIsSalary, double earnAmount, double weeklyHours) {

        //get base weekly budget
        if (earnIsYearly == true){
            //divide salary wage by how many weeks there are in a year
            //this yields the weekly budget
            weeklyBudget = earnAmount/52;
        }
        else if (earnIsYearly == false){
            //multiply weekly wage by how many hours are worked in a week
            //this yields the weekly budget
            weeklyBudget = earnAmount*weeklyHours;
        }


        //subtract weekly expenses




        //subtract monthly bill if it is due in the week
        //get from WeekDayService?



    }


}
