package com.budgetbuddy.dao.ExpenseDAO;

import com.budgetbuddy.dto.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stub implementation of the {@link IExpenseDAO} interface for managing expenses.
 * <p>
 * This class provides a simple in-memory storage mechanism for {@link Expense} objects, primarily for testing purposes.
 * It uses a {@link HashMap} to simulate a database.
 * </p>
 *
 * <p>
 * The class is annotated with {@link Repository} to indicate its role in the persistence layer, although it does not interact with an actual database.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

@Repository
public class ExpenseDAOStub implements IExpenseDAO {

    Map<Integer, Expense> expenses = new HashMap<>();

    @Override
    public Expense save(Expense expense) {
        //validate the input
        if (expense == null || expense.getExpAmount() <= 0)
        {
            throw new IllegalArgumentException("Invalid expense");
        }
        //save the input
        Integer expId = expense.getExpID();
        expenses.put(expId, expense);
        return expense;
    }

    @Override
    public void deleteExpense(int id) {
        expenses.remove(id);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses.values());
    }

}
