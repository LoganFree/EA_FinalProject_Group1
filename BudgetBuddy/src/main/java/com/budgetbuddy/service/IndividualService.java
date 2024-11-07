package com.budgetbuddy.service;

import com.budgetbuddy.dao.IIndividualDAO;
import com.budgetbuddy.dto.Individual;

import java.util.List;

public class IndividualService implements IIndividualService {
    private IIndividualDAO individualDAO;

    public IndividualService(IIndividualDAO individualDAO) {
        this.individualDAO = individualDAO;
    }

    @Override
    public Individual save(Individual individual) {
        // Save the individual to the DAO
        return individualDAO.save(individual);
    }

    @Override
    public Individual updateIndividual(Individual individual) {
        // Call the DAO to update the individual
        return individualDAO.updateIndividual(individual);
    }

    @Override
    public Individual getIndividualById(long id) {
        // Retrieve individual by its ID from the DAO
        return individualDAO.getIndividualById(id);
    }

    @Override
    public void deleteIndividual(long id) {
        // Delete the individual by its ID using the DAO
        individualDAO.deleteIndividual(id);
    }

    @Override
    public List<Individual> getAllIndividuals() {
        // Retrieve all individuals from the DAO
        return individualDAO.getAllIndividuals();
    }

    @Override
    public double calculateTotalIndividual() {
        // Calculate the total amount of all individuals
        return individualDAO.calculateTotalIndividual();
    }
}

