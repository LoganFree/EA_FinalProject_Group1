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
    public void deleteBill(int id) {
        billDAO.deleteBill(id);
    }

    @Override
    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    @Override
    // Method to get bills due within a specified weekly range
    public List<Bill> getWeeklyBills(LocalDate startDate, LocalDate endDate) {
        // Retrieve all bills from the data source
        List<Bill> allBills = billDAO.getAllBills();

        // Create a list to store bills within the specified weekly range
        List<Bill> weeklyBills = new ArrayList<>();

        // Iterate over all bills to filter them based on the due date
        for (Bill bill : allBills) {
            // Parse the bill's due date into a LocalDate object
            LocalDate dueDate = LocalDate.parse(bill.getBillDueDate());

            // Calculate the adjusted due date for the current and next months
            LocalDate billDueDateCurrentMonth = getValidBillDueDate(startDate, dueDate.getDayOfMonth());
            LocalDate billDueDateNextMonth = getValidBillDueDate(startDate.plusMonths(1), dueDate.getDayOfMonth());

            // Check if the bill's due date falls within the specified range
            if (isDateWithinRange(billDueDateCurrentMonth, startDate, endDate)
                    || isDateWithinRange(billDueDateNextMonth, startDate, endDate.plusDays(1))) {

                // Create a new Bill object for the weekly list with updated due date
                Bill weeklyBill = new Bill();
                weeklyBill.setBillID(bill.getBillID()); // Copy bill ID
                weeklyBill.setBillDescription(bill.getBillDescription()); // Copy description
                weeklyBill.setBillAmount(bill.getBillAmount()); // Copy amount

                // Set the due date, prioritizing the current month if valid
                weeklyBill.setBillDueDate(
                        billDueDateCurrentMonth.isBefore(startDate)
                                ? billDueDateNextMonth.toString()
                                : billDueDateCurrentMonth.toString()
                );

                // Add the bill to the weekly list
                weeklyBills.add(weeklyBill);
            }
        }
        // Return the list of bills due within the weekly range
        return weeklyBills;
    }

    // Helper method to get a valid due date for a bill, considering month length
    private LocalDate getValidBillDueDate(LocalDate monthDate, int dayOfMonth) {
        // Get the last day of the month for the given date
        int lastDayOfMonth = monthDate.lengthOfMonth();

        // Return a date with the given day or the last day of the month, whichever is earlier
        return LocalDate.of(monthDate.getYear(), monthDate.getMonthValue(), Math.min(dayOfMonth, lastDayOfMonth));
    }

    // Helper method to check if a date is within the specified range (inclusive of start date)
    private boolean isDateWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) && date.isBefore(endDate.plusDays(1));
    }

}
