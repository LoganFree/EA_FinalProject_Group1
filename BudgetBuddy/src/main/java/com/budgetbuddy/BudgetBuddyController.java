package com.budgetbuddy;

import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.service.BillService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class BudgetBuddyController {
    @Autowired
    BillService billService;

    @RequestMapping("/")
    public String index(Model model)
    {


        model.addAttribute("page", "home");
        return "startpage";
    }

    @RequestMapping("/entry-form")
    public String entryForm(Model model)
    {
        Bill bill = new Bill();
        bill.setBillAmount(100.00);
        //bill.setBillDueDate(new Date("10-10-2024"));
        bill.setBillDescription("Test");

        model.addAttribute("page", "entry");
        model.addAttribute(bill);
        return "entryform";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model)
    {
        model.addAttribute("page", "dashboard");
        return "dashboard";
    }


    @RequestMapping("/fragments/styles.css")
    public String styles(Model model)
    {
        return "fragments/styles.css";
    }

    @RequestMapping("/saveBill")
    public String saveBill(Bill bill)
    {
        try {
            billService.save(bill);
        } catch (Exception e) {
            //   throw new RuntimeException(e);
            e.printStackTrace();
            return "entryform";
        }
        return "entryform";
    }


}
