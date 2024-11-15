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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

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
    @Autowired
    private TempDataService tempDataService;

    //call for CSS
    @RequestMapping("/fragments/styles.css")
    public String styles(Model model) {
        return "fragments/styles.css";
    }

    //START PAGE
    @RequestMapping("/")
    public String index(Model model)
    {
        //set page to active
        model.addAttribute("page", "home");

        return "startpage";
    }

    //ENTRY FORM
    @RequestMapping("/entry-form")
    public String entryForm(Model model)
    {
        //set page to active
        model.addAttribute("page", "entry");

        return "entryform";
    }

    @RequestMapping("/entry-form/mng-exp")
    public String mngExp(Model model) throws Exception {
        model.addAttribute("page", "entry");

        // Create default test values for Expense
        Expense expense = new Expense();
        expense.setExpAmount(100.0);
        expense.setExpCategory(null);
        expense.setExpDescription("test");

        model.addAttribute("expense", expense);

        // Get categories for dropdown using ExpenseService
        List<Category> categories = expenseService.getCategories();
        model.addAttribute("categories", categories);

        return "mngexp";
    }

    // ENTRY FORM - Bill Management
    @RequestMapping("/entry-form/mng-bill")
    public String mngBill(Model model) {
        // Set the page to active
        model.addAttribute("page", "entry");

        // Use BillService to create a default Bill and add it to the model
        Bill defaultBill = billService.createDefaultBill();
        model.addAttribute("bill", defaultBill);

        return "mngbill";
    }

    //called when an expense is added on the entry form
    @RequestMapping("/entry-form/save-exp")
    public String saveExp(Expense expense, Model model) {
        //set page to active
        model.addAttribute("page", "entry");

        try {
            //save expense
            //ADD BREAK POINT HERE TO SEE SAVED DATA
            expenseService.save(expense);
        } catch (Exception e) {
            e.printStackTrace();
            return "mngexp";
        }
        return "mngexp";
    }

    //called when a bill is added on the entry form
    @RequestMapping("/entry-form/save-bill")
    public String saveBill(Bill bill, Model model) {
        //set page to active
        model.addAttribute("page", "entry");

        try {
            //save bill
            //ADD BREAK POINT HERE TO SEE SAVED DATA
            billService.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
            return "mngbill";
        }
        return "mngbill";
    }

    //DASHBOARD
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        //set page to active
        model.addAttribute("page", "dashboard");

        //get temp data from TempDataService
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

        //get temp data from TempDataService
        model.addAttribute("expenses", tempDataService.getExpenses());
        model.addAttribute("bills", tempDataService.getBills());

        return "dashboard";
    }

}
