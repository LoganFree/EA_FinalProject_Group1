package com.budgetbuddy;

import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.service.BillService;
import com.budgetbuddy.service.WeekService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BudgetBuddyController {

    @Autowired
    BillService billService;

    @Autowired
    private WeekService weekService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("page", "home");
        return "startpage";
    }

    @RequestMapping("/entry-form")
    public String entryForm(Model model) {
        Bill bill = new Bill();
        bill.setBillAmount(100.00);
        //bill.setBillDueDate(new Date("10-10-2024"));
        bill.setBillDescription("Test");
        model.addAttribute("page", "entry");
        model.addAttribute(bill);
        return "entryform";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("page", "dashboard");
        return "dashboard";
    }

    @RequestMapping("/fragments/styles.css")
    public String styles(Model model) {
        return "fragments/styles.css";
    }

    @RequestMapping("/saveBill")
    public String saveBill(Bill bill) {
        try {
            billService.save(bill);
        } catch (Exception e) {
            //   throw new RuntimeException(e);
            e.printStackTrace();
            return "entryform";
        }
        return "entryform";
    }

    @PostMapping("/week-selected")
    public String getWeekDates(@RequestParam("week") String week, Model model) {
        String formattedWeek = weekService.getWeekDates(week);
        model.addAttribute("weekDays", formattedWeek);
        model.addAttribute("selectedWeek", week);
        return "dashboard"; // Ensure "dashboard.html" is in src/main/resources/templates
    }
}
