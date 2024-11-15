package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BillDAO implements IBillDAO {

    private final Map<Integer, Bill> bills = new HashMap<>();

    @Override
    public Bill save(Bill bill) {
        validateBill(bill);
        bills.computeIfAbsent(bill.getBillID(), id -> bill);
        return bill;
    }

    @Override
    public Bill updateBill(Bill bill) {
        if (!bills.containsKey(bill.getBillID())) {
            throw new IllegalArgumentException("Bill not found");
        }
        bills.put(bill.getBillID(), bill);
        return bill;
    }

    @Override
    public Bill getBillById(long id) {
        return bills.get((int) id);
    }

    @Override
    public void deleteBill(long id) {
        if (bills.remove((int) id) == null) {
            throw new IllegalArgumentException("Bill not found to delete");
        }
    }

    @Override
    public List<Bill> getAllBills() {
        return new ArrayList<>(bills.values());
    }

    @Override
    public double calculateTotalBill() {
        return bills.values().stream().mapToDouble(Bill::getBillAmount).sum();
    }

    private void validateBill(Bill bill) {
        if (bill == null || bill.getBillAmount() <= 0 || bill.getBillDueDate() == null) {
            throw new IllegalArgumentException("Invalid bill details");
        }
    }
}