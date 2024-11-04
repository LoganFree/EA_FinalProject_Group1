package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
}
