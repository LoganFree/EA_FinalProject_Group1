package com.budgetbuddy;

import com.budgetbuddy.dao.CategoryDAO;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.service.BillService;
import com.budgetbuddy.service.WeekDayService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class BudgetBuddyController {
    @Autowired
    BillService billService;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private WeekDayService weekDayService;

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
        //bill.setBillDueDate(new Date("10-10-2024"));
        bill.setBillDescription("Test");
        model.addAttribute("page", "entry");
        model.addAttribute(bill);

        Expense expense = new Expense();
        model.addAttribute("categories", categoryDAO.getCategories());
        model.addAttribute(expense);
        return "entryform";
    }

    //called when a bill is added on the entry form
    @PostMapping("/saveBill")
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

    //DASHBOARD
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("page", "dashboard");
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

}
