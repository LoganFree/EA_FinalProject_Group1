package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDAO implements IExpenseDAO {
    Map<Integer, Expense> expenses = new HashMap<>();

    @Override
    public Expense save(Expense expense) {
        //validate the input
        if (expense == null || expense.getExpAmount() <= 0)
        {
            throw new IllegalArgumentException("Invalid expense");
        }
        //save the input
        Integer expId = expense.getExpID();
        expenses.put(expId, expense);
        return expense;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense getExpenseByCategory(String category) {
        return expenses.get(Integer.parseInt(category));
    }

    @Override
    public void deleteExpense(long id) {
        expenses.remove((int) id);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses.values());
    }

    @Override
    public double calculateTotalExpense() {
        return 0;
    }
}
