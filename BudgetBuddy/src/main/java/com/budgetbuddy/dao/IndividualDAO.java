package com.budgetbuddy.dao;

import com.budgetbuddy.dto.Individual;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
    public class IndividualDAO implements IIndividualDAO {

        Map<Integer, Individual> individuals = new HashMap<>();

        @Override
        public Individual save(Individual individual) {
            //validate the input
            if (individual == null || individual.getIndividualAmount() <= 0 || individual.getIndividualWeekDate() == null) {
                throw new IllegalArgumentException("Invalid individual purchase details");
            }
            Integer individualId = individual.getIndividualID();
            individuals.put(individualId, individual);
            return individual;
        }

        @Override
        public Individual updateIndividual(Individual individual) {
            if (individuals.containsKey(individual.getIndividualID())) {
                individuals.put(individual.getIndividualID(), individual);
                return individual;
            } else {
                throw new IllegalArgumentException("Individual Purchase not found");
            }
        }

        @Override
        public Individual getIndividualById(long id) {
            return individuals.get((int) id);
        }

        @Override
        public void deleteIndividual(long id) {
            individuals.remove((int) id);
        }

        @Override
        public List<Individual> getAllIndividuals() {
            return new ArrayList<>(individuals.values());
        }

        @Override
        public double calculateTotalIndividual() {
            return individuals.values().stream().mapToDouble(Individual::getIndividualAmount).sum();
        }
    }
