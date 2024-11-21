package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("earningDAO")
public class EarningSQLDAO implements IEarningDAO {

    @Autowired
    EarningRepository earningRepo;

    @Override
    public Earning save(Earning earning) {
        return earningRepo.save(earning);
    }

    @Override
    public void deleteEarning(int id) {
        earningRepo.deleteById(id);
    }

    @Override
    public List<Earning> getAllEarnings() {
        List<Earning> earnings = new ArrayList<>();
        earningRepo.findAll().forEach(earnings::add);
        return earnings;
    }

}
