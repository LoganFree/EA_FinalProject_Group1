package com.budgetbuddy.service;

import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempDataService {

    private List<Expense> expenses = new ArrayList<>();
    private List<Bill> bills = new ArrayList<>();

    public TempDataService() {
        // Initialize with some data
        expenses.add(new Expense(70, "Groceries"));
        expenses.add(new Expense(48,"Entertainment"));
        expenses.add(new Expense(28,"Movies" ));
        expenses.add(new Expense(20,"Game"));

        bills.add(new Bill(28, "Car Bill", "23"));
        bills.add(new Bill(20, "Car Insurance", "23"));
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public List<Bill> getBills() {
        return bills;
    }
}