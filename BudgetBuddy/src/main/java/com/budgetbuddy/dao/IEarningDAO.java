package com.budgetbuddy.dao;
import com.budgetbuddy.dto.Earning;

import java.util.List;

public interface IEarningDAO {
        Earning save(Earning earning) throws Exception;

        List<Earning> fetchAll();

       Earning fetch(int id);

        void delete(int id);
    }
