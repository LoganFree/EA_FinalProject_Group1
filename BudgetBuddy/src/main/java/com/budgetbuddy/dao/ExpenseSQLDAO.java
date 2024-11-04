package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseSQLDAO implements IExpenseDAO {
    @Autowired
    ExpenseRepository expenseRepo;

    @Override
    public Expense save(Expense expense) {
        return expenseRepo.save(expense);
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
