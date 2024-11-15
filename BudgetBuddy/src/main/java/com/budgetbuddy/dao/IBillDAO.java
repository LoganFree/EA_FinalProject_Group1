package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillDAO {

        //Bill management
        void validateBill(Bill bill);
        Bill save(Bill bill);
        Bill updateBill(Bill bill);
        Bill getBillById(long id);
        void deleteBill(long id);
        List<Bill> getAllBills();
        double calculateTotalBill();
    }

