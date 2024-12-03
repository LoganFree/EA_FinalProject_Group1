package com.budgetbuddy.dao.ExpenseDAO;

import com.budgetbuddy.dto.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
}
