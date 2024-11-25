package com.budgetbuddy;

import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.service.EarningService.EarningService;
import com.budgetbuddy.service.BillService.BillService;
import com.budgetbuddy.service.ExpenseService.ExpenseService;
import com.budgetbuddy.service.WeekDayService;
import com.budgetbuddy.service.CalculationService.CalculationService;
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
    EarningService earningService;
    @Autowired
    BillService billService;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    private WeekDayService weekDayService;
    @Autowired
    private CalculationService calculationService;

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
        return "entryform";
    }

    // called when an Earning is added
    @RequestMapping(value = "/entry-form/mng-earn/save-earning")
    public String saveEarning(Earning earning, Model model) {
        model.addAttribute("page", "entry");
        try {
            earningService.save(earning);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/entry-form";
        }
        return "redirect:/entry-form/mng-earn";
    }

    // called when managing Earning
    @RequestMapping("/entry-form/mng-earn")
    public String mngEarn(Model model)
    {
        model.addAttribute("page", "entry");

        // pull out Earning from database
        List<Earning> earnings = earningService.getAllEarnings();
        model.addAttribute("earnings", earnings);

        // pull out the most recent Earning
        Earning recentEarning = earningService.getMostRecentEarning();
        model.addAttribute("recentearning", recentEarning);

        return "mngearn";
    }

    // called when an Earning is deleted
    @DeleteMapping("/entry-form/mng-earn/delete/earn")
    public ResponseEntity<String> deleteEarn(@RequestParam("id") int id) {
        log.debug("Entering delete Earning endpoint");
        try {
            earningService.deleteEarning(id);
            log.info("Earning with ID " + id + " was deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to delete Earning with ID " + id + ", message:" + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    // called when a Bill is added
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

    // called when an Expense is added
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

    // DASHBOARD
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("page", "dashboard");

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

            //double totalExpenses = weekDayService.calculateTotalExpensesForWeek(week, weeklyExpenses);

            //double totalBills = weekDayService.calculateTotalBillsForWeek(week, weeklyBills);

            //model.addAttribute("totalBills", totalExpenses);
            //model.addAttribute("totalExpenses", totalBills);

            /*
            // pull out the most recent Earning
            Earning recentEarning = earningService.getMostRecentEarning();

            calculationService.calculateTotalBudgetForWeek(week, weeklyBills, weeklyExpenses, recentEarning);
            */

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Invalid week format or data processing error.");
        }

        return "dashboard";
    }

    // List all Bills from database
    @GetMapping("/all/earn")
    @ResponseBody
    public List<Earning> fetchEarning()
    {
        return earningService.getAllEarnings();
    }

    // List all Bills from database
    @GetMapping("/all/bill")
    @ResponseBody
    public List<Bill> fetchBill()
    {
        return billService.getAllBills();
    }

    // List all Expenses from database
    @GetMapping("/all/exp")
    @ResponseBody
    public List<Expense> fetchExpense()
    {
        return expenseService.getAllExpenses();
    }

}
