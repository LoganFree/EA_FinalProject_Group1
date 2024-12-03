package com.budgetbuddy.dao.ExpenseDAO;

import com.budgetbuddy.dao.BillDAO.BillRepository;
import com.budgetbuddy.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link IExpenseDAO} interface for managing {@link Expense} entities using a SQL database.
 * <p>
 * This class interacts with a database using {@link ExpenseRepository} and {@link BillRepository} to perform CRUD operations
 * on {@link Expense} objects. It is annotated with {@link Repository} to mark it as a Spring bean in the persistence layer.
 * </p>
 *
 * <p>
 * The class supports basic operations such as saving, deleting, and retrieving expenses, but some methods (e.g., updating and
 * calculating totals) are either not yet implemented or require further business logic.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

@Repository("expenseDAO")
public class ExpenseSQLDAO implements IExpenseDAO {
    @Autowired
    ExpenseRepository expenseRepo;
    @Autowired
    private BillRepository billRepository;

    @Override
    public Expense save(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Override
    public void deleteExpense(int id) {
        expenseRepo.deleteById(id);
    }

    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        expenseRepo.findAll().forEach(expenses::add);
        return expenses;
    }

}
