package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void deleteEarning(long id) {}

    @Override
    public Double getEarning() {
        return null;
    }

}
