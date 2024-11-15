package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExpenseDAO implements IExpenseDAO {
    Map<Integer, Expense> expenses = new HashMap<>();


    @Override
    public void validateExpense(Expense expense) {
        if (expense == null || expense.getExpAmount() <= 0 || expense.getExpCategory() == null || expense.getExpCategory().isEmpty()) {
            throw new IllegalArgumentException("Invalid expense details");
        }
    }

    @Override
    public Expense save(Expense expense) {
        validateExpense(expense);
        Integer expId = expense.getExpID();
        expenses.put(expId, expense);
        return expense;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public List<Expense> getExpenseByCategory(String category) {
        List<Expense> expenseList = new ArrayList<>();

        //validate the category
        if (category == null || category.isEmpty())
        {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }

        for (Expense expense : expenses.values()) {
            if (category.equals(expense.getExpCategory()))
            {
                expenseList.add(expense);
            }
        }

        return expenseList;
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
