package com.budgetbuddy.service;

import com.budgetbuddy.dto.Earning;

import java.util.List;

public interface IEarningService {

    //Earning management
    Earning createEarning(Earning earning);
    Earning updateEarning(Earning earning);
    Earning getEarningById(long id);
    void deleteEarning(long id);
    List<Earning> getAllEarnings();
    double calculateTotalEarning();

}
