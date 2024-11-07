package com.budgetbuddy.dao.BillDAO;

import com.budgetbuddy.dto.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
