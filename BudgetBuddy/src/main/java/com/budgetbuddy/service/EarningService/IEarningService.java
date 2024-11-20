package com.budgetbuddy.service.EarningService;

import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IEarningService {

    //Earning management
    Earning save(Earning earning);
    void deleteEarning(long id);
    List<Earning> getAllEarnings();

}
