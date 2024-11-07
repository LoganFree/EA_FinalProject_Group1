package com.budgetbuddy.service.ExpenseService;

import com.budgetbuddy.dto.Earning;

import java.util.List;

public interface IEarningService {

    //Earning management
    Earning save(Earning earning);
    void deleteEarning(long id);
    List<Earning> getEarning();

}
