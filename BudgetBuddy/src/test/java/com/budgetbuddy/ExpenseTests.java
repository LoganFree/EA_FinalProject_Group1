package com.budgetbuddy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.budgetbuddy.dao.ExpenseDAO.IExpenseDAO;
import com.budgetbuddy.dto.Bill;
import com.budgetbuddy.dto.Expense;
import com.budgetbuddy.service.ExpenseService.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExpenseTests {

    @InjectMocks
    private ExpenseService expenseService;

    @Mock
    private IExpenseDAO expenseDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveExpense() {
        Expense expense = new Expense();
        expense.setExpID(1);
        expense.setExpAmount(100.0);

        when(expenseDAO.save(any(Expense.class))).thenReturn(expense);

        Expense savedExpense = expenseService.save(expense);

        assertNotNull(savedExpense);
        assertEquals(1, savedExpense.getExpID());
        assertEquals(100.0, savedExpense.getExpAmount());
    }

    @Test
    public void testDeleteExpense() {
        int id = 1;

        doNothing().when(expenseDAO).deleteExpense(id);

        expenseService.deleteExpense(id);

        verify(expenseDAO, times(1)).deleteExpense(id);
    }

    @Test
    public void testGetAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        Expense expense1 = new Expense();
        expense1.setExpID(1);
        expense1.setExpDescription("Laptop");
        expense1.setExpAmount(600.0);

        Expense expense2 = new Expense();
        expense2.setExpID(2);
        expense2.setExpDescription("Groceries");
        expense2.setExpAmount(100.0);

        expenses.add(expense1);
        expenses.add(expense2);

        when(expenseDAO.getAllExpenses()).thenReturn(expenses);

        List<Expense> allExpenses = expenseService.getAllExpenses();

        assertNotNull(allExpenses);
        assertEquals(2, allExpenses.size());
        assertEquals("Laptop", allExpenses.get(0).getExpDescription());
        assertEquals("Groceries", allExpenses.get(1).getExpDescription());
    }

}
