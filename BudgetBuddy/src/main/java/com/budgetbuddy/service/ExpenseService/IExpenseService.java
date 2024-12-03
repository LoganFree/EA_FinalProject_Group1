package com.budgetbuddy.service.ExpenseService;

import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IExpenseService {

    //Expense management
    Expense save(Expense expense);
    void deleteExpense(int id);
    List<Expense> getAllExpenses();
    List<Category> getCategories() throws Exception;
}
