package com.budgetbuddy;

import com.budgetbuddy.dao.CategoryDAO.CategoryDAO;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Category;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.service.BillService.BillService;
import com.budgetbuddy.service.ExpenseService.ExpenseService;
import com.budgetbuddy.service.TempDataService;
import com.budgetbuddy.service.WeekDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
