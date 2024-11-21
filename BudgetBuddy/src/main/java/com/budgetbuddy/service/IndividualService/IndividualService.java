package com.budgetbuddy.service.IndividualService;


import com.budgetbuddy.dao.IndividualDAO.IIndividualDAO;
import com.budgetbuddy.dto.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class IndividualService implements IIndividualService {


    @Autowired
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


