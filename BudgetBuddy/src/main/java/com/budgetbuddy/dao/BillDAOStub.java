package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BillDAOStub implements IBillDAO{
    // List<Specimen> allSpecimens = new ArrayList<Specimen>();
    Map<Integer , Bill> allBills = new HashMap<>();
    @Override
    public Bill save(Bill bill) throws Exception {
        // allSpecimens.add(specimen);
        Integer billID = Integer.parseInt(String.valueOf(bill.getBillId()));
        allBills.put(billID, bill);
        return bill;
    }
    @Override
    public List<Bill> fetchAll(){
        List<Bill> returnsBills = new ArrayList(allBills.values());
        return returnsBills;
    }
    @Override
    public Bill fetch(int id) {
        return allBills.get(id);
    }
    @Override
    public void delete(int id) {
        allBills.remove(id);
    }
}
