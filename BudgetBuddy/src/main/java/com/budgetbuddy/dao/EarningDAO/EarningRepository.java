package com.budgetbuddy.dao.EarningDAO;

import com.budgetbuddy.dto.Earning;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EarningRepository extends CrudRepository<Earning, Integer> {

    // Default method to find the most recent earning entry
    default Earning findMostRecentEarning() {
        // Create a Pageable object to limit the query to one result
        Pageable pageable = PageRequest.of(0, 1);

        // Retrieve earnings ordered by ID in descending order
        List<Earning> earnings = findAllByOrderByEarnIDDesc(pageable);

        // Return the first earning or null if the list is empty
        return earnings.isEmpty() ? null : earnings.get(0);
    }

    // Method to find all earnings ordered by ID in descending order with pagination
    List<Earning> findAllByOrderByEarnIDDesc(Pageable pageable);
}