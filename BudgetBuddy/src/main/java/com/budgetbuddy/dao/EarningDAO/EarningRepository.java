package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import org.springframework.data.repository.CrudRepository;

public interface EarningRepository extends CrudRepository<Earning, Integer> {
}
