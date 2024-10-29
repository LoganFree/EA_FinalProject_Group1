package com.budgetbuddy.service;

import com.budgetbuddy.dto.BillItem;
import com.budgetbuddy.dto.ExpenseItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempDataService {

    private List<ExpenseItem> expenses = new ArrayList<>();
    private List<BillItem> bills = new ArrayList<>();

    public TempDataService() {
        // Initialize temp data
        expenses.add(new ExpenseItem(70, "Groceries"));
        expenses.add(new ExpenseItem(48,"Entertainment"));
        expenses.add(new ExpenseItem(28,"Movies" ));
        expenses.add(new ExpenseItem(20,"Game"));

        bills.add(new BillItem(28, "Car Bill", "23"));
        bills.add(new BillItem(20, "Car Insurance", "23"));
    }

    public List<ExpenseItem> getExpenses() {
        return expenses;
    }

    public List<BillItem> getBills() {
        return bills;
    }
}