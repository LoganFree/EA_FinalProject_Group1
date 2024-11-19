package com.budgetbuddy.dao.ExpenseDAO;

import com.budgetbuddy.dao.BillDAO.BillRepository;
import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("expenseDAO")
public class ExpenseSQLDAO implements IExpenseDAO {
    @Autowired
    ExpenseRepository expenseRepo;
    @Autowired
    private BillRepository billRepository;

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
    public void deleteExpense(int id) {
        expenseRepo.deleteById(id);
    }

    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        expenseRepo.findAll().forEach(expenses::add);
        return expenses;
    }

    @Override
    public double calculateTotalExpense() {
        return 0;
    }
}
