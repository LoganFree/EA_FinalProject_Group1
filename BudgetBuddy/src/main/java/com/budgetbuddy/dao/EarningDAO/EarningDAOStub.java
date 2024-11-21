package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EarningDAOStub implements IEarningDAO {

    Map<Integer, Earning> earnings = new HashMap<>();

    @Override
    public Earning save(Earning earning) {
        return null;
    }

    @Override
    public void deleteEarning(int id) {
        earnings.remove(id);
    }

    @Override
    public List<Earning> getAllEarnings() {
        return new ArrayList<>(earnings.values());
    }

}
