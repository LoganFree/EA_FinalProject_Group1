package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
