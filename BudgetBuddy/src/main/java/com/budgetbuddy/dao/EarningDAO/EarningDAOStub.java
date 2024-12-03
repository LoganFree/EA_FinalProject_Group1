package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A stub implementation of the {@link IEarningDAO} interface for managing earnings.
 * <p>
 * This class provides an in-memory data structure to simulate CRUD operations for earning records.
 * It is primarily used for testing and development purposes where a database connection is not required.
 * </p>
 *
 * <p>
 * Earnings are stored in a {@link HashMap} with their IDs as keys and {@link Earning} objects as values.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

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
