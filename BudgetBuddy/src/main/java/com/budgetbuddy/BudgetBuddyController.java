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

import java.util.List;

@Controller
public class BudgetBuddyController {
    @Autowired
    private BillService billService;

    @RequestMapping("/")
    public String index(Model model)
    {
        return "startpage";
    }

    @PostMapping("/")
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill)
    {
        Bill createdBill = billService.save(bill);
        return ResponseEntity.ok(createdBill);
    }

}
