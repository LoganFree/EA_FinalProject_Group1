package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EarningDAOStub implements IEarningDAO{


    @Override
    public Earning save(Earning earning) {
        return null;
    }

    @Override
    public void deleteEarning(long id) {

    }

    @Override
    public List<Earning> getEarning() {
        return List.of();
    }
}
