package com.budgetbuddy.service.EarningService;

import com.budgetbuddy.dao.EarningDAO.IEarningDAO;
import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.service.ExpenseService.IEarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarningService implements IEarningService {

    @Autowired
    private IEarningDAO earningDAO;

    public EarningService(IEarningDAO earningDAO) {
        this.earningDAO = earningDAO;
    }

    @Override
    public Earning save(Earning earning) {
        // Save the bill to the DAO
        return earningDAO.save(earning);
    }

    @Override
    public void deleteEarning(long id) {
        // Delete the bill by its ID using the DAO
        earningDAO.deleteEarning(id);
    }

    @Override
    public List<Earning> getEarning() {
        // Retrieve all bills from the DAO
        return earningDAO.getEarning();
    }

}

