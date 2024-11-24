package com.budgetbuddy;

import com.budgetbuddy.dao.CategoryDAO.CategoryDAO;
import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.service.EarningService.EarningService;
import com.budgetbuddy.service.BillService.BillService;
import com.budgetbuddy.service.ExpenseService.ExpenseService;
import com.budgetbuddy.service.TempDataService;
import com.budgetbuddy.service.WeekDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;
import retrofit2.Call;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for handling web requests related to the BudgetBuddy application.
 * <p>
 * This class manages user interactions for adding, managing, and deleting earnings, bills, and expenses.
 * It also provides functionality for viewing the dashboard, selecting weeks, and fetching data from the database.
 * </p>
 *
 * @author Mckelvin Ofosu-Frimpong
 */
@Controller
public class BudgetBuddyController {
    @Autowired
    EarningService earningService;
    @Autowired
    BillService billService;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    private WeekDayService weekDayService;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private TempDataService tempDataService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //call for CSS
    @RequestMapping("/fragments/styles.css")
    public String styles(Model model) {
        return "fragments/styles.css";
    }

    //START PAGE
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("page", "home");
        return "startpage";
    }

    //ENTRY FORM
    @RequestMapping("/entry-form")
    public String entryForm(Model model) {
        model.addAttribute("page", "entry");

        // pull out Earning from database
        List<Earning> earnings = earningService.getAllEarnings();
        model.addAttribute("earnings", earnings);

        return "entryform";
    }

    // called when an earning is added on the entry form
    @RequestMapping(value = "/entry-form/save-earning")
    public String saveEarning(Earning earning, Model model) {
        model.addAttribute("page", "entry");
        try {
            // Save the earning using the earningService
            earningService.save(earning);
        } catch (Exception e) {
            // Catch any exception, including potential database or general errors
            log.error("An error occurred while saving the earning: ", e);
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            return "entryform";  // Redirect back to the entry form with an error message
        }
        return "redirect:/entry-form";  // Redirect to the entry form after successfully saving the earning
    }


    // called when managing Bill
    @RequestMapping("/entry-form/mng-bill")
    public String mngBill(Model model)
    {
        model.addAttribute("page", "entry");

        // create default test values
        Bill bill = new Bill();
        model.addAttribute("bill",bill);

        // pull out Bill from database
        List<Bill> bills = billService.getAllBills();
        model.addAttribute("bills", bills);

        return "mngbill";
    }

    // called when a bill is added on the entry form
    @RequestMapping(value = "/entry-form/save-bill")
    public String saveBill(Bill bill, Model model) {
        model.addAttribute("page", "entry");

        try {
            billService.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/entry-form/mng-bill";
        }
        return "redirect:/entry-form/mng-bill";
    }

    // called when a Bill is deleted
    @DeleteMapping("/entry-form/mng-bill/delete/bill")
    public ResponseEntity<String> deleteBill(@RequestParam("id") int id) {
        log.debug("Entering delete Bill endpoint");
        try {
            // Attempt to delete the bill
            billService.deleteBill(id);
            log.info("Bill with ID " + id + " was deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            // Handle specific exception for data integrity violation (e.g., foreign key constraints)
            log.error("Error: Bill ID " + id + " is referenced elsewhere and cannot be deleted.", e);
            return new ResponseEntity<>("Bill cannot be deleted due to integrity constraints.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Catch all other exceptions
            log.error("Unexpected error while deleting Bill ID " + id + ": " + e.getMessage(), e);
            return new ResponseEntity<>("An unexpected error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // called when managing Expense
    @RequestMapping("/entry-form/mng-exp")
    public String mngExp(Model model) throws Exception {
        model.addAttribute("page", "entry");

        Expense expense = new Expense();
        model.addAttribute("expense", expense);

        // pull out Expense from database
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);

        // get Categories for dropdown
        List<Category> categories = expenseService.getCategories(); // No parameters
        model.addAttribute("categories", categories);

        return "mngexp";
    }

    // called when an expense is added on the entry form
    @RequestMapping("/entry-form/save-exp")
    public String saveExp(Expense expense, Model model) {
        model.addAttribute("page", "entry");

        try {
            expenseService.save(expense);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/entry-form/mng-exp";
        }
        return "redirect:/entry-form/mng-exp";
    }

    // called when an Expense is deleted
    @DeleteMapping("/entry-form/mng-exp/delete/exp")
    public ResponseEntity<String> deleteExpense(@RequestParam("id") int id) {
        log.debug("Entering delete Expense endpoint");
        try {
            expenseService.deleteExpense(id);
            log.info("Expense with ID " + id + " was deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to delete Expense with ID " + id + ", message:" + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DASHBOARD
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("page", "dashboard");

        //get temp data from DAO
        model.addAttribute("expenses", tempDataService.getExpenses());
        model.addAttribute("bills", tempDataService.getBills());

        return "dashboard";
    }

    // called when a week is selected in the dashboard
    @PostMapping("/week-selected")
    public String getWeekDates(@RequestParam("week") String week, Model model) {
        //set page to active
        model.addAttribute("page", "dashboard");

        //call weekService
        String formattedWeek = weekDayService.getWeekDates(week);
        model.addAttribute("weekDays", formattedWeek);
        model.addAttribute("selectedWeek", week);

        return "dashboard";
    }

    // List all Bills from database
    @GetMapping("/all-bill")
    @ResponseBody
    public List<Bill> fetchBill()
    {
        return billService.getAllBills();
    }

    // List all Expenses from database
    @GetMapping("/all-exp")
    @ResponseBody
    public List<Expense> fetchExpense()
    {
        return expenseService.getAllExpenses();
    }
}
