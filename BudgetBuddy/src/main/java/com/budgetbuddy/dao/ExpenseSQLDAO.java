package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Expense;

import java.util.List;

public class ExpenseSQLDAO implements IExpenseDAO {

    @Override
    public Expense save(Expense expense) {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public List<Expense> getExpenseByCategory(String category) {
        return List.of();
    }

    @Override
    public void deleteExpense(long id) {

    }

    @Override
    public List<Expense> getAllExpenses() {
        return List.of();
    }

    @Override
    public double calculateTotalExpense() {
        return 0;
    }
}
