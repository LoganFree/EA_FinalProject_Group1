package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BillDAO implements IBillDAO {

    Map<Integer, Bill> bills = new HashMap<>();

    @Override
    public Bill save(Bill bill) {
        //validate the input
        if (bill == null || bill.getBillAmount() <= 0 || bill.getBillDueDate() == null) {
            throw new IllegalArgumentException("Invalid bill details");
        }
        Integer billId = bill.getBillID();
        bills.put(billId, bill);
        return bill;
    }

    @Override
    public Bill updateBill(Bill bill) {
        if (bills.containsKey(bill.getBillID())) {
            bills.put(bill.getBillID(), bill);
            return bill;
        } else {
            throw new IllegalArgumentException("Bill not found");
        }
    }

    @Override
    public Bill getBillById(long id) {
        Bill bill = bills.get((int)id);
        // Handle cases where the bill is not found
        if (bill == null) {

            throw new IllegalArgumentException("Bill not found");
        }
        return bill;
    }

    @Override
    public void deleteBill(long id) {
        // Handle cases where the bill is not found
        if (!bills.containsKey(id)) {

            throw new IllegalArgumentException("Bill not found");
        }
        bills.remove(id);

    }

    @Override
    public List<Bill> getAllBills() {
        return new ArrayList<>(bills.values());
    }

    @Override
    public double calculateTotalBill() {
        return bills.values().stream().mapToDouble(Bill::getBillAmount).sum();
    }
}