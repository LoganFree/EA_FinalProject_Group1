package com.budgetbuddy.service.BillService;

import com.budgetbuddy.dto.Bill;

import java.time.LocalDate;
import java.util.List;

public interface IBillService {

    //Bill management
    Bill save(Bill bill);

    void deleteBill(int id);

    List<Bill> getAllBills();

    List<Bill> getWeeklyBills(LocalDate startDate, LocalDate endDate);

}