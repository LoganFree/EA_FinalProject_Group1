package com.budgetbuddy;

import com.budgetbuddy.dao.CategoryDAO.CategoryDAO;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.service.BillService.BillService;
import com.budgetbuddy.service.ExpenseService.ExpenseService;
import com.budgetbuddy.service.WeekDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BudgetBuddyController {
    @Autowired
    BillService billService;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    private WeekDayService weekDayService;
    @Autowired
    private CategoryDAO categoryDAO;

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private View error;

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

        return "entryform";
    }

    @RequestMapping("/entry-form/mng-bill")
    public String mngBill(Model model)
    {
        model.addAttribute("page", "entry");

        //create default test values
        Bill bill = new Bill();

        List<Bill> bills = billService.getAllBills();

        model.addAttribute("bills", bills);
        model.addAttribute("bill",bill);

        return "mngbill";
    }

    //called when a bill is added on the entry form
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

    @DeleteMapping("/entry-form/mng-bill/delete/bill")
    public ResponseEntity<String> deleteBill(@RequestParam("id") int id) {
        log.debug("Entering delete Bill endpoint");
        try {
            billService.deleteBill(id);
            log.info("Bill with ID " + id + " was deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to delete Bill with ID " + id + ", message:" + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/entry-form/mng-exp")
    public String mngExp(Model model) throws Exception {
        model.addAttribute("page", "entry");

        Expense expense = new Expense();
        model.addAttribute("expense", expense);

        //pull out expense from database
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);

        // Get categories for dropdown
        List<Category> categories = expenseService.getCategories(); // No parameters
        model.addAttribute("categories", categories);

        return "mngexp";
    }

    //called when an expense is added on the entry form
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

    // DASHBOARD
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("page", "dashboard");

        // Get temp data from DAO

        return "dashboard";
    }

    // Called when a week is selected in the dashboard
    @RequestMapping("/week-selected")
    public String getWeekData(@RequestParam("week") String week, Model model) {
        model.addAttribute("page", "dashboard");

        try {
            // Get week start and end dates
            LocalDate[] weekDates = weekDayService.getStartAndEndDates(week);
            LocalDate startDate = weekDates[0];
            LocalDate endDate = weekDates[1];

            // Retrieve weekly bills
            List<Bill> weeklyBills = billService.getWeeklyBills(startDate, endDate);

            // Retrieve weekly expenses
            List<Expense> weeklyExpenses = expenseService.getAllExpenses().stream()
                    .filter(expense -> weekDayService.isDateWithinRange(expense.getExpDate(), startDate, endDate))
                    .collect(Collectors.toList());

            // Add data to the model
            model.addAttribute("weekDays", weekDayService.getWeekDates(week)); // e.g., "10/1-10/7"
            model.addAttribute("selectedWeek", week);
            model.addAttribute("weeklyBills", weeklyBills);
            model.addAttribute("weeklyExpenses", weeklyExpenses);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Invalid week format or data processing error.");
        }

        return "dashboard";
    }

    //List all Bills from database
    @GetMapping("/allbills")
    @ResponseBody
    public List<Bill> fetchBill()
    {
        return billService.getAllBills();
    }

    //List all Expenses from database
    @GetMapping("/allexps")
    @ResponseBody
    public List<Expense> fetchExpense()
    {
        return expenseService.getAllExpenses();
    }
}
