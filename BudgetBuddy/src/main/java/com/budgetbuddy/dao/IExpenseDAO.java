package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IExpenseDAO {

        //Expense management
        Expense save(Expense expense);
        Expense updateExpense(Expense expense);
        List<Expense> getExpenseByCategory(String category);
        void deleteExpense(long id);
        List<Expense> getAllExpenses();
        double calculateTotalExpense();

}
