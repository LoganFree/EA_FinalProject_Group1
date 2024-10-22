package com.budgetbuddy;

import com.budgetbuddy.dao.CategoryDAO;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.service.BillService;
import com.budgetbuddy.service.ExpenseService;
import com.budgetbuddy.service.TempDataService;
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
    CategoryDAO categoryDAO = new CategoryDAO();

    @Autowired
    private TempDataService tempDataService;

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

        Bill bill = new Bill(100.00,"Test", null);
        model.addAttribute(bill);

        Expense expense = new Expense(100.00,"Test");

        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);
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

    //DASHBOARD
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("page", "dashboard");

        model.addAttribute("expenses", tempDataService.getExpenses());
        model.addAttribute("bills", tempDataService.getBills());

        return "dashboard";
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

        model.addAttribute("expenses", tempDataService.getExpenses());
        model.addAttribute("bills", tempDataService.getBills());

        return "dashboard";
    }

}
