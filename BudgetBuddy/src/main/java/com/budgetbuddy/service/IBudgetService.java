package com.budgetbuddy.service;

import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IBudgetService {

    //Bill management
    Bill createBill(Bill bill);
    Bill updateBill(Bill bill);
    Bill getBillById(long id);
    void deleteBill(long id);
    List<Bill> getAllBills();
    double calculateTotalBill();

    //Earning management
    Earning createEarning(Earning earning);
    Earning updateEarning(Earning earning);
    Earning getEarningById(long id);
    void deleteEarning(long id);
    List<Earning> getAllEarnings();
    double calculateTotalEarning();

    //Expense management
    Expense createExpense(Expense expense);
    Expense updateExpense(Expense expense);
    Expense getExpenseById(long id);
    void deleteExpense(long id);
    List<Expense> getAllExpenses();
    double calculateTotalExpense();



}
