package com.budgetbuddy;

import com.budgetbuddy.dao.CategoryDAO;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.service.BillService;
//import com.budgetbuddy.service.ExpenseService;
import com.budgetbuddy.service.WeekDayService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Controller
public class BudgetBuddyController {
    @Autowired
    BillService billService;
    //ExpenseService expenseService;
    @Autowired
    private WeekDayService weekDayService;

    @Autowired
    private CategoryDAO categoryDAO;

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
        Bill bill = new Bill();
        bill.setBillAmount(100.00);
        //bill.setBillDueDate(new Date("2024-10-10"));
        bill.setBillDescription("Test");
        model.addAttribute("page", "entry");
        model.addAttribute(bill);

        Expense expense = new Expense();
        expense.setExpAmount(100.00);
        expense.setExpCategory(new Category("n/a"));
        expense.setExpDescription("Test");
        model.addAttribute("categories");
        model.addAttribute(expense);
        return "entryform";
    }

    //called when a bill is added on the entry form
    @RequestMapping("/saveBill")
    public String saveBill(Bill bill, Model model) {
        model.addAttribute("page", "entry");

        try {
            billService.save(bill);
        } catch (Exception e) {
            //   throw new RuntimeException(e);
            e.printStackTrace();
            return "entryform";
        }
        return "entryform";
    }

    /*@PostMapping("/saveExpense")
    public String saveExpense(@RequestParam("expenseAmount") double expAmount,
                              @RequestParam("expenseCategory") String expCategory,
                              @RequestParam("expenseDescription") String expDescription,
                              Model model) {
        Expense expense = new Expense(expAmount, expCategory, expDescription);
        expenseService.saveExpense(expense);

        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "entryform";
    }*/

    List<ExpenseItem> expenses = new ArrayList<>();
    List<BillItem> bills = new ArrayList<>();


    //DASHBOARD
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("page", "dashboard");

        expenses.add(new ExpenseItem("Groceries", 70));
        expenses.add(new ExpenseItem("Entertainment", 48));
        expenses.add(new ExpenseItem("Movies", 28));
        expenses.add(new ExpenseItem("Game", 20));

        bills.add(new BillItem("Car Bill", 28, 23));
        bills.add(new BillItem("Car Insurance", 20, 23));

        model.addAttribute("expenses", expenses);
        model.addAttribute("bills", bills);

        return "dashboard";
    }

    public static class ExpenseItem {
        private String description;
        private double amount;

        public ExpenseItem(String description, double amount) {
            this.description = description;
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
        }
    }

    public static class BillItem {
        private String description;
        private double amount;
        private int duedate;

        public BillItem(String description, double amount, int duedate) {
            this.description = description;
            this.amount = amount;
            this.duedate = duedate;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
        }

        public int getDueDate() {
            return duedate;
        }
    }

    //called when a week is selected in the dashboard
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

}
