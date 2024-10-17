package com.budgetbuddy.dao;
import com.budgetbuddy.dto.Earning;

import java.util.List;

public interface IEarningDAO {
        Earning save(Earning earning) throws Exception;

        List<Earning> fetchAll();

       Earning fetch(long id);

        void delete(long id);
    }
