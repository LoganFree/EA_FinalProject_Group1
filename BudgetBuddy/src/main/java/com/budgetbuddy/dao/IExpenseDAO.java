package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Expense;

import java.util.List;

public interface IExpenseDAO {
        Expense save(Expense expense) throws Exception;

        List<Expense> fetchAll();

        Expense fetch(long id);

        void delete(long id);

}
