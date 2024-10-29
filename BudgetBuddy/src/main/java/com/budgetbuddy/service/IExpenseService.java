package com.budgetbuddy.service;

import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IExpenseService {

    //Expense management
    Expense save(Expense expense);
    Expense updateExpense(Expense expense);
    List<Expense> getExpensesByCategory(String category);
    void deleteExpense(long id);
    List<Expense> getAllExpenses();
    Double calculateTotalExpense();

}
