package com.budgetbuddy.service.BillService;

import com.budgetbuddy.dto.Bill;

import java.util.List;

public interface IBillService {

    //Bill management
    Bill save(Bill bill);
    Bill updateBill(Bill bill);
    Bill getBillById(int id);
    void deleteBill(int id);
    List<Bill> getAllBills();
    double calculateTotalBill();
}
