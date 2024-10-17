package com.budgetbuddy.service;

import com.budgetbuddy.dao.IBillDAO;
import com.budgetbuddy.dto.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceStub implements IBillService {
    @Autowired
    private IBillDAO billDAO;

    public BillServiceStub() { }
    public BillServiceStub(IBillDAO billDAO)
    {
        this.billDAO = billDAO;
    }
    @Override
    public Bill fetchById(int id) {
        Bill foundBill = billDAO.fetch(id);
        return foundBill;
    }
    @Override
    public void delete(int id) throws Exception{
        billDAO.delete(id);
    }
    @Override
    public Bill save(Bill bill) throws Exception{
        return billDAO.save(bill);
    }
    @Override
    public List<Bill> fetchAll() {
        return billDAO.fetchAll();
    }


}
