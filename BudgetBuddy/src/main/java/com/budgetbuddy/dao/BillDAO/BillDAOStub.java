package com.budgetbuddy.dao.BillDAO;

import com.budgetbuddy.dto.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A stub implementation of the {IBillDAO} interface for managing bill data.
 *
 * <p>
 * This class provides in-memory storage for bills using a {@link HashMap}. It is intended for
 * testing purposes and simulates CRUD operations on bill data.
 * </p>
 *
 * <p>
 * Features include saving, updating, retrieving, deleting bills, and calculating the total
 * bill amount.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
@Repository
public class BillDAOStub implements IBillDAO {

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
    public Bill getBillById(int id) {
        return bills.get(id);
    }

    @Override
    public void deleteBill(int id) {
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