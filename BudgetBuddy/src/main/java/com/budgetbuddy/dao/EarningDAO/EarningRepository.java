package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EarningRepository extends CrudRepository<Earning, Integer> {

    default Earning findMostRecentEarning() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Earning> earnings = findAllByOrderByEarnIDDesc(pageable);
        return earnings.isEmpty() ? null : earnings.get(0);
    }

    List<Earning> findAllByOrderByEarnIDDesc(Pageable pageable);

}
