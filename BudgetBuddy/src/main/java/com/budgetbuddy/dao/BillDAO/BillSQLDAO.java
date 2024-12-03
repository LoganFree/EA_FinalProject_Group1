package com.budgetbuddy.dao.BillDAO;

import com.budgetbuddy.dto.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The Data Access Object (DAO) implementation for managing {@link Bill} entities in the database.
 * <p>
 * This class provides methods for CRUD operations on {@link Bill} using the Spring Data JPA repository.
 * It interacts with the database to save, retrieve, update, and delete bill records.
 * </p>
 *
 * <p>
 * This implementation uses the {@link BillRepository} interface to delegate database interactions.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

@Repository("billDAO")
public class BillSQLDAO implements IBillDAO {

    @Autowired
    BillRepository billRepo;

    @Override
    public Bill save(Bill bill) {
        return billRepo.save(bill);
    }

    @Override
    public Bill getBillById(int id) {
        return billRepo.findById(id).get();
    }

    @Override
    public void deleteBill(int id) {
        billRepo.deleteById(id);
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<Bill>();
        billRepo.findAll().forEach(bills::add);
        return bills;
    }

}
