package com.budgetbuddy.dao.ExpenseDAO;

import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IExpenseDAO {

        //Expense management
        Expense save(Expense expense);
        void deleteExpense(int id);
        List<Expense> getAllExpenses();

}
