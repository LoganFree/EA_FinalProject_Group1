package com.budgetbuddy.service;

import com.budgetbuddy.dao.ExpenseDAO;
import com.budgetbuddy.dao.IExpenseDAO;
import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private ExpenseDAO expenseDAO;

    public List<Expense> getExpensesByCategory(String category) {
        return expenseDAO.getExpenseByCategory(category);
    }

}
