package com.budgetbuddy.service;

import com.budgetbuddy.dao.IBillDAO;
import com.budgetbuddy.dto.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements IBillService {

    private IBillDAO billDAO;

    public BillService(IBillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    public Bill save(Bill bill) {
        // Save the bill to the DAO
        return billDAO.save(bill);
    }

    @Override
    public Bill updateBill(Bill bill) {
        // Call the DAO to update the bill
        return billDAO.updateBill(bill);
    }

    @Override
    public Bill getBillById(long id) {
        // Retrieve a bill by its ID from the DAO
        return billDAO.getBillById(id);
    }

    @Override
    public void deleteBill(long id) {
        // Delete the bill by its ID using the DAO
        billDAO.deleteBill(id);
    }

    @Override
    public List<Bill> getAllBills() {
        // Retrieve all bills from the DAO
        return billDAO.getAllBills();
    }

    @Override
    public double calculateTotalBill() {
        // Calculate the total amount of all bills
        return billDAO.calculateTotalBill();
    }

    // New method to create a default Bill with preset values
    public Bill createDefaultBill() {
        Bill bill = new Bill();
        bill.setBillAmount(100.0);          // Default amount
        bill.setBillDueDate(null);           // Default due date (set a date if desired)
        bill.setBillDescription("test");    // Default description
        return bill;
    }

}
