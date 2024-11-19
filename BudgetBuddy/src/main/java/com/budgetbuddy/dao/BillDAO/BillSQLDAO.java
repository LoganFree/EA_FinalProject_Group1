package com.budgetbuddy.dao.BillDAO;

import com.budgetbuddy.dto.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public Bill getBillById(int id) {
        return billRepo.findById(id).get();
    }

    @Override
    public void deleteBill(int id) {
        billRepo.deleteById(id);
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<Bill>();
        billRepo.findAll().forEach(bills::add);
        return bills;
    }

    @Override
    public double calculateTotalBill() {
        return 0;
    }
}
