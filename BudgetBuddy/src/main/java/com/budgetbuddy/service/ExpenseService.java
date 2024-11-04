package com.budgetbuddy.service;

import com.budgetbuddy.dao.CategoryDAO;
import com.budgetbuddy.dao.ICategoryDAO;
import com.budgetbuddy.dao.IExpenseDAO;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements IExpenseService {
    @Autowired
    private IExpenseDAO expenseDAO;

    @Autowired
    private ICategoryDAO categoryDAO;

    @Override
    public List<Expense> getExpensesByCategory(String category) {
        return expenseDAO.getExpenseByCategory(category);
    }

    public List<Category> getCategories() throws Exception{
        return categoryDAO.getCategories();
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
