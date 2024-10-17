package com.budgetbuddy;

import com.budgetbuddy.dto.Bill;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BudgetBuddyController {

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("page", "home");
        return "startpage";
    }

    @RequestMapping("/entry-form")
    public String entryForm(Model model)
    {
        model.addAttribute("page", "entry");
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

    public Bill created

}
