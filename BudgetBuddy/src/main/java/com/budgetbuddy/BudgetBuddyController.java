package com.budgetbuddy;

import com.budgetbuddy.dao.CategoryDAO.CategoryDAO;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.service.BillService.BillService;
import com.budgetbuddy.service.ExpenseService.ExpenseService;
import com.budgetbuddy.service.TempDataService;
import com.budgetbuddy.service.WeekDayService;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("/entry-form/mng-exp")
    public String mngExp(Model model) throws Exception {
        model.addAttribute("page", "entry");

        // Create default test values
        Expense expense = new Expense();
        expense.setExpAmount(100.0);
        expense.setExpCategory(null);
        expense.setExpDescription("test");
        model.addAttribute("expense", expense);

        // Get categories for dropdown
        List<Category> categories = expenseService.getCategories(); // No parameters
        model.addAttribute("categories", categories);

        return "mngexp";
    }

    @GetMapping("/bills")
    @ResponseBody
    public List<Bill> fetchBill()
    {
        return billService.getAllBills();
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

    @DeleteMapping("/entry-form/mng-bill/delete/bill")
    public ResponseEntity<String> deleteBill(@RequestParam("id") int id) {
        billService.deleteBill(id);
        return ResponseEntity.ok("Task deleted successfully!");
    }

    //called when an expense is added on the entry form
    @RequestMapping("/entry-form/save-exp")
    public String saveExp(Expense expense, Model model) {
        model.addAttribute("page", "entry");

        try {
            expenseService.save(expense);
        } catch (Exception e) {
            e.printStackTrace();
            return "mngexp";
        }
        return "mngexp";
    }

    //called when a bill is added on the entry form
    @RequestMapping(value = "/entry-form/save-bill")
    public String saveBill(Bill bill, Model model) {
        model.addAttribute("page", "entry");

        try {
            billService.save(bill);
            return "mngbill";
        } catch (Exception e) {
            e.printStackTrace();
            return "mngbill";
        }
    }

    //
    @GetMapping(value="/getallbills")
    public List<Bill> getAllBills()
    {
        return billService.getAllBills();
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

    //called when a week is selected in the dashboard
    @PostMapping("/week-selected")
    public String getWeekDates(@RequestParam("week") String week, Model model) {
        //set page to active
        model.addAttribute("page", "dashboard");

        //call weekService
        String formattedWeek = weekDayService.getWeekDates(week);
        model.addAttribute("weekDays", formattedWeek);
        model.addAttribute("selectedWeek", week);

        //get temp data from DAO
        model.addAttribute("expenses", tempDataService.getExpenses());
        model.addAttribute("bills", tempDataService.getBills());

        return "dashboard";
    }

}
