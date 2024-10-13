package com.budgetbuddy.dao;
import com.budgetbuddy.dto.Earning;

import java.util.List;

public interface IEarningDAO {
        //Earning management
        Earning save(Earning earning);
        Earning updateEarning(Earning earning);
        Earning getEarningById(long id);
        void deleteEarning(long id);
        List<Earning> getAllEarnings();
        double calculateTotalEarning();

    }
