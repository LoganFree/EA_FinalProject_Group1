package com.budgetbuddy.service;

import com.budgetbuddy.dto.Bill;

import java.util.List;

public interface IBillService {
    /**
     * Fetch a specimen with a given ID.
     * @param id a unique identifier for a specimen.
     * @return the matching specimen, or null if no match is found.
     */
    Bill fetchById(int id);

    void delete(int id) throws Exception;

    Bill save(Bill bill) throws Exception;

    List<Bill> fetchAll();


}
