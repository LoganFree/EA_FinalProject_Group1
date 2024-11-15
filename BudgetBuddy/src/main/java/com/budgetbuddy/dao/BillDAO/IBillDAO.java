package com.budgetbuddy.dao.BillDAO;

import com.budgetbuddy.dto.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillDAO {

        //Bill management
        Bill save(Bill bill);
        Bill updateBill(Bill bill);
        Bill getBillById(int id);
        void deleteBill(int id);
        List<Bill> getAllBills();
        double calculateTotalBill();
    }

