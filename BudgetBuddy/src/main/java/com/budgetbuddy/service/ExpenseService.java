package com.budgetbuddy.service;

import com.budgetbuddy.dao.IExpenseDAO;
import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements IExpenseService {
    private IExpenseDAO expenseDAO;

    public ExpenseService (IExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @Override
    public List<Expense> getExpensesByCategory(String category) {
        return expenseDAO.getExpenseByCategory(category);
    }

    @Override
    public Expense save(Expense expense) {
        return expenseDAO.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }


    @Override
    public void deleteExpense(long id) {

    }

    @Override
    public List<Expense> getAllExpenses() {
        return List.of();
    }

    @Override
    public Double calculateTotalExpense() {
        return (double) 0;
    }
}
