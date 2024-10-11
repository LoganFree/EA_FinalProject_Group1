package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Bill;

import java.util.List;

public interface IBillDAO {

        Bill save(Bill bill) throws Exception;

        List<Bill> fetchAll();

        Bill fetch(int id);

        void delete(int id);
    }

