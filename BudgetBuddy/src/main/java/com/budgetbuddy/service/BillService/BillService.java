package com.budgetbuddy.service.BillService;

import com.budgetbuddy.dao.BillDAO.IBillDAO;
import com.budgetbuddy.dto.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing and processing bills in the Budget Buddy application.
 * <p>
 * This service acts as an intermediary between the controller and the DAO, providing higher-level
 * methods for saving, updating, deleting, and retrieving bills.
 * </p>
 *
 * <p>
 * The {@link BillService} class is annotated with {@link org.springframework.stereotype.Service},
 * indicating that it is a service component in the Spring context.
 * </p>
 *
 * @author Logan Freeman, Loc Nguyen, Anthony Johnson, Alex Brooksbank, Mckelvin Ofosu-Frimpong
 */

@Service
public class BillService implements IBillService {

    @Autowired
    private IBillDAO billDAO;

    public BillService(IBillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    public Bill save(Bill bill) {
        return billDAO.save(bill);
    }

    @Override
    public Bill getBillById(int id) {
        return billDAO.getBillById(id);
    }

    @Override
    public void deleteBill(int id) {
        billDAO.deleteBill(id);
    }

    @Override
    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    @Override
    public List<Bill> getWeeklyBills(LocalDate startDate, LocalDate endDate) {
        List<Bill> allBills = billDAO.getAllBills();
        List<Bill> weeklyBills = new ArrayList<>();

        for (Bill bill : allBills) {
            LocalDate dueDate = LocalDate.parse(bill.getBillDueDate());

            LocalDate billDueDateCurrentMonth = getValidBillDueDate(startDate, dueDate.getDayOfMonth());
            LocalDate billDueDateNextMonth = getValidBillDueDate(startDate.plusMonths(1), dueDate.getDayOfMonth());

            if (isDateWithinRange(billDueDateCurrentMonth, startDate, endDate)
                    || isDateWithinRange(billDueDateNextMonth, startDate, endDate.plusDays(1))) {
                Bill weeklyBill = new Bill();
                weeklyBill.setBillID(bill.getBillID());
                weeklyBill.setBillDescription(bill.getBillDescription());
                weeklyBill.setBillAmount(bill.getBillAmount());
                weeklyBill.setBillDueDate(billDueDateCurrentMonth.isBefore(startDate) ? billDueDateNextMonth.toString() : billDueDateCurrentMonth.toString());
                weeklyBills.add(weeklyBill);
            }
        }
        return weeklyBills;
    }

    private LocalDate getValidBillDueDate(LocalDate monthDate, int dayOfMonth) {
        int lastDayOfMonth = monthDate.lengthOfMonth();
        return LocalDate.of(monthDate.getYear(), monthDate.getMonthValue(), Math.min(dayOfMonth, lastDayOfMonth));
    }

    private boolean isDateWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) && date.isBefore(endDate.plusDays(1));
    }
}
