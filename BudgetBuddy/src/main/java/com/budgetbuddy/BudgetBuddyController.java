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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        //setBillAmount(100.0);
        //bill.setBillDueDate("2024-10-10");
        //bill.setBillDescription("Test");
        List<Bill> bills = billService.getAllBills();

        model.addAttribute("bills", bills);
        model.addAttribute("bill", bill);

        //model.addAttribute("page", "entry");
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
