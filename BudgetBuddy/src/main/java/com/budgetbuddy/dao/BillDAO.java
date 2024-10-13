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
        return bills.get((int) id);
    }

    @Override
    public void deleteBill(long id) {
        bills.remove((int) id);
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