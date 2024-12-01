package com.budgetbuddy.service.ExpenseService;

import com.budgetbuddy.dao.CategoryDAO.ICategoryDAO;
import com.budgetbuddy.dao.ExpenseDAO.IExpenseDAO;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("category")
    @Override
    public List<Category> getCategories() throws Exception{
        return categoryDAO.getCategories();
    }
    
    //@Cacheable("expenses")
    @Override
    public List<Expense> getAllExpenses() {
        return expenseDAO.getAllExpenses();
    }

    //@CachePut(value = "expenses", key = "#expense")
    @Override
    public Expense save(Expense expense) {
        return expenseDAO.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    //@CacheEvict(value = "expenses", key = "#id")
    @Override
    public void deleteExpense(int id) {
        expenseDAO.deleteExpense(id);
    }

    @Override
    public Double calculateTotalExpense() {
        return (double) 0;
    }

}