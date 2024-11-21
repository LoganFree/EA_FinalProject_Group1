package com.budgetbuddy.service.EarningService;

import com.budgetbuddy.dao.EarningDAO.EarningRepository;
import com.budgetbuddy.dao.EarningDAO.IEarningDAO;
import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing earnings in the Budget Buddy application.
 * <p>
 * This service acts as an intermediary between the controller and the DAO, providing higher-level
 * methods for saving, deleting, and retrieving earnings.
 * </p>
 *
 * <p>
 * The {@link EarningService} class is annotated with {@link org.springframework.stereotype.Service},
 * indicating that it is a service component in the Spring context.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
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
    public List<Earning> getAllEarnings() {
        // Retrieve all Earnings from the DAO
        return earningDAO.getAllEarnings();
    }

}

