package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("BillDAO")
public class BillSQLDAO implements IBillDAO{

    @Autowired
    BillRepository billRepo;

    @Override
    public Bill save(Bill bill) {
        Bill createdBill = billRepo.save(bill);
        return createdBill;
    }

    @Override
    public Bill updateBill(Bill bill) {
        return null;
    }

    @Override
    public Bill getBillById(long id) {
        return null;
    }

    @Override
    public void deleteBill(long id) {

    }

    @Override
    public List<Bill> getAllBills() {
        return List.of();
    }

    @Override
    public double calculateTotalBill() {
        return 0;
    }
}
