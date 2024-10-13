package com.budgetbuddy.service;

import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IExpenseService {

    //Expense management
    Expense createExpense(Expense expense);
    Expense updateExpense(Expense expense);
    Expense getExpenseById(long id);
    void deleteExpense(long id);
    List<Expense> getAllExpenses();
    double calculateTotalExpense();


}