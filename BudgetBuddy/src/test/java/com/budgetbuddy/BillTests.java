package com.budgetbuddy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.budgetbuddy.dao.BillDAO.IBillDAO;
import com.budgetbuddy.service.BillService.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import com.budgetbuddy.dto.Bill;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BillTests {

    @InjectMocks
    private BillService billService;

    @Mock
    private IBillDAO billDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveBill() {
        Bill bill = new Bill();
        bill.setBillID(1);
        bill.setBillDescription("Electricity");
        bill.setBillAmount(100.0);
        bill.setBillDueDate("2024-12-15");

        when(billDAO.save(any(Bill.class))).thenReturn(bill);

        Bill savedBill = billService.save(bill);

        assertNotNull(savedBill);
        assertEquals(1, savedBill.getBillID());
        assertEquals("Electricity", savedBill.getBillDescription());
        assertEquals(100.0, savedBill.getBillAmount());
        assertEquals("2024-12-15", savedBill.getBillDueDate());
    }

    @Test
    public void testDeleteBill() {
        int id = 1;

        doNothing().when(billDAO).deleteBill(id);

        billService.deleteBill(id);

        verify(billDAO, times(1)).deleteBill(id);
    }

    @Test
    public void testGetAllBills() {
        List<Bill> bills = new ArrayList<>();
        Bill bill1 = new Bill();
        bill1.setBillID(1);
        bill1.setBillDescription("Internet");
        bill1.setBillAmount(60.0);
        bill1.setBillDueDate("2024-12-25");

        Bill bill2 = new Bill();
        bill2.setBillID(2);
        bill2.setBillDescription("Rent");
        bill2.setBillAmount(1200.0);
        bill2.setBillDueDate("2024-12-01");

        bills.add(bill1);
        bills.add(bill2);

        when(billDAO.getAllBills()).thenReturn(bills);

        List<Bill> allBills = billService.getAllBills();

        assertNotNull(allBills);
        assertEquals(2, allBills.size());
        assertEquals("Internet", allBills.get(0).getBillDescription());
        assertEquals("Rent", allBills.get(1).getBillDescription());
    }

}
