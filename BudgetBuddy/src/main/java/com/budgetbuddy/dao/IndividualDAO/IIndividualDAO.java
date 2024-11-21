package com.budgetbuddy.dao.IndividualDAO;


import com.budgetbuddy.dto.Individual;


import java.util.List;


public interface IIndividualDAO {
    Individual save(Individual individual);
    Individual updateIndividual(Individual individual);
    Individual getIndividualById(long id);
    void deleteIndividual(long id);
    List<Individual> getAllIndividuals();
    double calculateTotalIndividual();
}

