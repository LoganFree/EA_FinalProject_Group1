package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link IEarningDAO} interface for managing earnings in the database.
 * <p>
 * This class utilizes Spring Data JPA to interact with the database for CRUD operations related to earnings.
 * It serves as the SQL-based data access layer for the application.
 * </p>
 *
 * <p>
 * The class is annotated with {@link Repository} to indicate its role in the persistence layer.
 * It uses the {@link EarningRepository} for database operations.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
@Repository("earningDAO")
public class EarningSQLDAO implements IEarningDAO {

    @Autowired
    EarningRepository earningRepo;

    @Override
    public Earning save(Earning earning) {
        return earningRepo.save(earning);
    }

    @Override
    public void deleteEarning(long id) {
    }

    @Override
    public List<Earning> getAllEarnings() {
        List<Earning> earnings = new ArrayList<>();
        earningRepo.findAll().forEach(earnings::add);
        return earnings;
    }

}
