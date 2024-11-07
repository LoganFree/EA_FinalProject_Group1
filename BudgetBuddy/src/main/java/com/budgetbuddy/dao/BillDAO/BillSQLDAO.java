package com.budgetbuddy.dao.BillDAO;

import com.budgetbuddy.dto.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("billDAO")
public class BillSQLDAO implements IBillDAO {

    @Autowired
    BillRepository billRepo;

    @Override
    public Bill save(Bill bill) {
        return billRepo.save(bill);
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
